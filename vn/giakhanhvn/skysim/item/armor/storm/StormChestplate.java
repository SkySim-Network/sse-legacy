/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.armor.storm;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.armor.LeatherArmorStatistics;

public class StormChestplate
implements MaterialFunction,
LeatherArmorStatistics {
    @Override
    public double getBaseIntelligence() {
        return 250.0;
    }

    @Override
    public double getBaseHealth() {
        return 260.0;
    }

    @Override
    public double getBaseDefense() {
        return 120.0;
    }

    @Override
    public int getColor() {
        return 1545156;
    }

    @Override
    public String getDisplayName() {
        return "Storm's Chestplate";
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
        return SpecificItemType.CHESTPLATE;
    }

    @Override
    public String getLore() {
        return null;
    }
}

