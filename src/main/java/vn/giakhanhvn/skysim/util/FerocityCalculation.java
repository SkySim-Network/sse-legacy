/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.EnderDragon
 *  org.bukkit.entity.Enderman
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.entity.nms.VoidgloomSeraph;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.util.SUtil;

public class FerocityCalculation {
    public static final Map<UUID, Integer> ONE_TYPE_FEROCITY_BONUS_ENDERMAN = new HashMap<UUID, Integer>();

    @Deprecated
    public static void test(int i) {
    }

    public static Integer ferocityStrikeNext(int ferocity) {
        if (ferocity == 0) {
            return 0;
        }
        int feroDiv = Math.round(ferocity / 100) + 1;
        return feroDiv;
    }

    public static Integer ferocityStrikeCurrent(int ferocity) {
        if (ferocity < 100) {
            return 0;
        }
        int feroDiv = Math.round(ferocity / 100);
        return feroDiv;
    }

    public static Double ferocityPercentCurrent(int ferocity) {
        double returnamount = 0.0;
        int feroMinus = Math.round(ferocity / 100) * 100;
        int feroLater = ferocity - feroMinus;
        returnamount = feroMinus >= 100 ? 100.0 : (double)ferocity;
        return returnamount;
    }

    public static Double ferocityPercentNext(int ferocity) {
        int feroMinus = Math.round(ferocity / 100) * 100;
        double feroLater = ferocity - feroMinus;
        if (feroLater > 100) {
            feroLater = 0;
        }
        return feroLater;
    }

    public static void activeFerocityTimes(Player p, LivingEntity damaged, int finalDamage, boolean crit) {
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(p.getUniqueId());
        int finalhittime = 0;
        double fer = statistics.getFerocity().addAll();
        int plus_in_calc = 0;
        if (damaged instanceof EnderDragon) {
            double formula = (double)finalDamage / ((EnderDragon)damaged).getMaxHealth() * 100.0;
            finalDamage = formula < 10.0 ? (finalDamage *= 1) : (formula > 10.0 && formula < 15.0 ? (finalDamage -= finalDamage * 90 / 100) : (formula > 15.0 && formula < 20.0 ? (finalDamage -= finalDamage * 99 / 100) : (formula > 20.0 && formula <= 25.0 ? (int)((double)finalDamage - (double)finalDamage * 99.9 / 100.0) : (formula > 25.0 ? (finalDamage *= 0) : (finalDamage *= 1)))));
        }
        if (ONE_TYPE_FEROCITY_BONUS_ENDERMAN.containsKey(p.getUniqueId())) {
            plus_in_calc = ONE_TYPE_FEROCITY_BONUS_ENDERMAN.get(p.getUniqueId());
        }
        if (damaged.hasMetadata("VWE")) {
            fer += (double)plus_in_calc;
        }
        if (damaged instanceof Enderman) {
            fer += (double)plus_in_calc;
            if (damaged.hasMetadata("Voidgloom")) {
                if (!VoidgloomSeraph.HIT_SHIELD.containsKey(damaged)) {
                    fer = fer * 25.0 / 100.0;
                } else {
                    int hitshield = VoidgloomSeraph.HIT_SHIELD.get(damaged);
                    if (hitshield <= 0) {
                        fer = fer * 25.0 / 100.0;
                    }
                }
            }
        }
        double hittimebase = FerocityCalculation.ferocityPercentNext((int)Math.round(fer));
        finalhittime = FerocityCalculation.ferocityStrikeCurrent((int)Math.round(fer));
        int chance = SUtil.random(0, 100);
        if (!((double)chance > hittimebase)) {
            finalhittime = FerocityCalculation.ferocityStrikeNext((int)Math.round(fer) + 1);
        }
        PlayerListener.ferocityActive(finalhittime, p, finalDamage, (Entity)damaged, crit);
    }
}

