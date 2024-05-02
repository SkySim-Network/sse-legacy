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

public class BabyYeti
extends Pet {
    @Override
    public List<PetAbility> getPetAbilities(SItem instance) {
        RarityValue<Double> enderianMul = new RarityValue<Double>(0.1, 0.2, 0.2, 0.3, 0.3, 0.3);
        RarityValue<Double> savvyMul = new RarityValue<Double>(0.0, 0.0, 0.4, 0.5, 0.5, 0.5);
        int level = BabyYeti.getLevel(instance);
        final BigDecimal coldbreeze = new BigDecimal((double)level * 0.5).setScale(1, RoundingMode.HALF_EVEN);
        final BigDecimal yeti1 = new BigDecimal(level * 1).setScale(1, RoundingMode.HALF_EVEN);
        BigDecimal yeti2 = new BigDecimal(level * 1).setScale(1, RoundingMode.HALF_EVEN);
        final BigDecimal iceshield = new BigDecimal(level).setScale(2, RoundingMode.HALF_EVEN);
        ArrayList<PetAbility> abilities = new ArrayList<PetAbility>(Collections.singletonList(new PetAbility(){

            @Override
            public String getName() {
                return "Cold Breeze";
            }

            @Override
            public List<String> getDescription(SItem instance) {
                return Arrays.asList("Gives +" + ChatColor.GREEN + coldbreeze.toPlainString() + ChatColor.RED + " \u2741 Strength" + ChatColor.GRAY + " and", ChatColor.BLUE + "\u2620 Crit Damage" + ChatColor.GRAY + " when near snow");
            }
        }));
        if (instance.getRarity().isAtLeast(Rarity.EPIC)) {
            abilities.add(new PetAbility(){

                @Override
                public String getName() {
                    return "Ice Shield";
                }

                @Override
                public List<String> getDescription(SItem instance) {
                    return Arrays.asList("Gain " + ChatColor.GREEN + iceshield.toPlainString() + "%" + ChatColor.GRAY + " of your strength", "" + ChatColor.GRAY + "as " + ChatColor.GREEN + "\u2748 Defense");
                }
            });
        }
        if (instance.getRarity().isAtLeast(Rarity.LEGENDARY)) {
            abilities.add(new PetAbility(){

                @Override
                public String getName() {
                    return "Yeti Fury" + ChatColor.RED + ChatColor.BOLD + " COMING SOON!";
                }

                @Override
                public List<String> getDescription(SItem instance) {
                    return Arrays.asList("Buffs the Yeti sword  by " + ChatColor.GREEN + yeti1.toPlainString() + ChatColor.RED + " \u2741", "" + ChatColor.RED + "Damage" + ChatColor.GRAY + " and" + ChatColor.AQUA + " \u270e", ChatColor.AQUA + "Intelligence");
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
        return "ab126814fc3fa846dad934c349628a7a1de5b415021a03ef4211d62514d5";
    }

    @Override
    public String getDisplayName() {
        return "Baby Yeti";
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.PET;
    }

    @Override
    public double getPerStrength() {
        return 0.4;
    }

    @Override
    public double getPerIntelligence() {
        return 0.75;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public void particleBelowA(Player p, Location l) {
        p.spigot().playEffect(l, Effect.COLOURED_DUST, 0, 1, 1.0f, 1.0f, 1.0f, 1.0f, 0, 64);
        p.spigot().playEffect(l, Effect.COLOURED_DUST, 0, 1, 0.1882353f, 0.5411765f, 0.67058825f, 1.0f, 0, 64);
    }
}

