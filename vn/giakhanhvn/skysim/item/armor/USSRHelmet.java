/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.armor;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.util.Sputnik;

public class USSRHelmet
implements ToolStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Gagarin's Space Helmet";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.SPECIAL;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.HELMET;
    }

    @Override
    public double getBaseMagicFind() {
        return 0.01;
    }

    @Override
    public double getBaseDefense() {
        return 69.0;
    }

    @Override
    public double getBaseStrength() {
        return 30.0;
    }

    @Override
    public double getBaseFerocity() {
        return 10.0;
    }

    @Override
    public String getLore() {
        return Sputnik.trans("&7Given to people who donated or help us out in the development of SkySim, thank you very much! &c\u2764");
    }
}

