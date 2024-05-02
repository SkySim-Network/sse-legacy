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

public class BlyatHelmet
implements ToolStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "\u269a Gagarin's Space Helmet";
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
        return 100.0;
    }

    @Override
    public double getBaseDefense() {
        return 14261.0;
    }

    @Override
    public double getBaseStrength() {
        return 4500.0;
    }

    @Override
    public double getBaseFerocity() {
        return 250.0;
    }

    @Override
    public String getLore() {
        return Sputnik.trans("&8Authentic &c\u272f USSR &8Product &81945/10 &8quality.");
    }
}

