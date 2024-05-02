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

public class renegade450Helmet
implements ToolStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "renegade450's Space Helmet";
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
        return 1.0;
    }

    @Override
    public double getBaseHealth() {
        return 1000.0;
    }

    @Override
    public double getBaseDefense() {
        return 1000.0;
    }

    @Override
    public double getBaseStrength() {
        return 450.0;
    }

    @Override
    public double getBaseFerocity() {
        return 25.0;
    }

    @Override
    public String getLore() {
        return Sputnik.trans("&7Given to &6renegade450 &7who donated like &c125M &7for the development of SkySim Network, thank you very much! &c\u2764");
    }
}

