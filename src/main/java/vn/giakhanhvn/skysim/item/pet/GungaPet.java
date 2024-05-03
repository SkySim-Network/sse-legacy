/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.item.pet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bukkit.ChatColor;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.RarityValue;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.pet.Pet;
import vn.giakhanhvn.skysim.item.pet.PetAbility;
import vn.giakhanhvn.skysim.skill.CombatSkill;
import vn.giakhanhvn.skysim.skill.Skill;

public class GungaPet
extends Pet {
    @Override
    public List<PetAbility> getPetAbilities(SItem instance) {
        int level = GungaPet.getLevel(instance);
        RarityValue<Double> annihCh = new RarityValue<Double>(10.0, 10.0, 10.0, 8.0, 6.0, 6.0);
        RarityValue<Integer> gingaCh = new RarityValue<Integer>(50, 40, 30, 20, 10, 10);
        BigDecimal annih = BigDecimal.valueOf(1.0 / (annihCh.getForRarity(instance.getRarity()) - (double)level * 0.02)).setScale(1, RoundingMode.HALF_EVEN);
        BigDecimal pig = BigDecimal.valueOf(1.0 / (150.0 - (double)level)).setScale(3, RoundingMode.HALF_EVEN);
        ArrayList<PetAbility> abilities = new ArrayList<PetAbility>(Collections.singletonList(new PetAbility(){

            @Override
            public String getName() {
                return "Ig this is yours now";
            }

            @Override
            public List<String> getDescription(SItem instance) {
                return Arrays.asList(ChatColor.RED + "nulled lol");
            }
        }));
        return abilities;
    }

    @Override
    public Skill getSkill() {
        return CombatSkill.INSTANCE;
    }

    @Override
    public String getURL() {
        return "";
    }

    @Override
    public String getDisplayName() {
        return "null";
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.PET;
    }

    @Override
    public double getPerIntelligence() {
        return 0.0;
    }

    @Override
    public double getPerMagicFind() {
        return 0.0;
    }

    @Override
    public double getPerDefense() {
        return 0.0;
    }

    @Override
    public double getPerCritDamage() {
        return 0.0;
    }
}

