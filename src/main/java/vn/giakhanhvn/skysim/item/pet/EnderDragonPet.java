/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.pet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.RarityValue;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.pet.Pet;
import vn.giakhanhvn.skysim.item.pet.PetAbility;
import vn.giakhanhvn.skysim.skill.CombatSkill;
import vn.giakhanhvn.skysim.skill.Skill;

public class EnderDragonPet
extends Pet {
    @Override
    public List<PetAbility> getPetAbilities(SItem instance) {
        RarityValue<Double> enderianMul = new RarityValue<Double>(0.1, 0.2, 0.2, 0.3, 0.3, 0.3);
        RarityValue<Double> savvyMul = new RarityValue<Double>(0.0, 0.0, 0.4, 0.5, 0.5, 0.5);
        int level = EnderDragonPet.getLevel(instance);
        final BigDecimal endstrike = new BigDecimal((double)level * 0.25).setScale(1, RoundingMode.HALF_EVEN);
        final BigDecimal aotd1 = new BigDecimal((double)level * 0.5).setScale(1, RoundingMode.HALF_EVEN);
        final BigDecimal aotd2 = new BigDecimal((double)level * 0.3).setScale(1, RoundingMode.HALF_EVEN);
        final BigDecimal buffstat = new BigDecimal((double)level * 0.1).setScale(2, RoundingMode.HALF_EVEN);
        ArrayList<PetAbility> abilities = new ArrayList<PetAbility>(Collections.singletonList(new PetAbility(){

            @Override
            public String getName() {
                return "End Strike";
            }

            @Override
            public List<String> getDescription(SItem instance) {
                return Arrays.asList("Deal +" + ChatColor.GREEN + endstrike.toPlainString() + "%" + ChatColor.GRAY + " more damage to", "end mobs.");
            }
        }));
        if (instance.getRarity().isAtLeast(Rarity.EPIC)) {
            abilities.add(new PetAbility(){

                @Override
                public String getName() {
                    return "One with the Dragons";
                }

                @Override
                public List<String> getDescription(SItem instance) {
                    return Arrays.asList("Buffs the Aspect of the", "Dragons sword by " + ChatColor.GREEN + aotd1.toPlainString() + ChatColor.RED + " \u2741", "" + ChatColor.RED + "Damage" + ChatColor.GRAY + " and " + ChatColor.GREEN + aotd2.toPlainString() + ChatColor.RED + " \u2741 Strength");
                }
            });
        }
        if (instance.getRarity().isAtLeast(Rarity.LEGENDARY)) {
            abilities.add(new PetAbility(){

                @Override
                public String getName() {
                    return "Superior";
                }

                @Override
                public List<String> getDescription(SItem instance) {
                    return Arrays.asList("Increases most stats by " + ChatColor.GREEN + buffstat.toPlainString() + "%");
                }
            });
        }
        return abilities;
    }

    @Override
    public Skill getSkill() {
        return CombatSkill.INSTANCE;
    }

    @Override
    public String getURL() {
        return "aec3ff563290b13ff3bcc36898af7eaa988b6cc18dc254147f58374afe9b21b9";
    }

    @Override
    public String getDisplayName() {
        return "Ender Dragon";
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.PET;
    }

    @Override
    public double getPerCritDamage() {
        return 0.005;
    }

    @Override
    public double getPerStrength() {
        return 0.5;
    }

    @Override
    public double getPerCritChance() {
        return 0.001;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public void particleBelowA(Player p, Location l) {
        p.spigot().playEffect(l, Effect.LARGE_SMOKE, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
        p.spigot().playEffect(l, Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
    }
}

