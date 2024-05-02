/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.armor.lapis;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.armor.LeatherArmorStatistics;

public class LapisArmorBoots
implements LeatherArmorStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Lapis Armor Boots";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.BOOTS;
    }

    @Override
    public double getBaseDefense() {
        return 20.0;
    }

    @Override
    public int getColor() {
        return 255;
    }
}

