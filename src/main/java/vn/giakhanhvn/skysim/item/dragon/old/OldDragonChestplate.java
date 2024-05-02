/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.dragon.old;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.armor.LeatherArmorStatistics;

public class OldDragonChestplate
implements MaterialFunction,
LeatherArmorStatistics {
    @Override
    public double getBaseHealth() {
        return 160.0;
    }

    @Override
    public double getBaseDefense() {
        return 150.0;
    }

    @Override
    public int getColor() {
        return 15787690;
    }

    @Override
    public String getDisplayName() {
        return "Old Dragon Chestplate";
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

