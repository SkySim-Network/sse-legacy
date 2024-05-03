/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.armor.necron;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.armor.LeatherArmorStatistics;

public class NecronLeggings
implements MaterialFunction,
LeatherArmorStatistics {
    @Override
    public double getBaseStrength() {
        return 40.0;
    }

    @Override
    public double getBaseCritDamage() {
        return 0.3;
    }

    @Override
    public double getBaseIntelligence() {
        return 10.0;
    }

    @Override
    public double getBaseHealth() {
        return 230.0;
    }

    @Override
    public double getBaseDefense() {
        return 125.0;
    }

    @Override
    public int getColor() {
        return 15162428;
    }

    @Override
    public String getDisplayName() {
        return "Necron's Leggings";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.LEGGINGS;
    }

    @Override
    public String getLore() {
        return null;
    }
}

