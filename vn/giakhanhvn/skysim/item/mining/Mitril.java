/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.mining;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.util.Sputnik;

public class Mitril
implements MaterialStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Mithril";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public String getLore() {
        return Sputnik.trans("&7&o\"The man called it \"true-silver\" while the Dwarves, who loved it above all other things, had their own, secret name for it.\"");
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public boolean isEnchanted() {
        return false;
    }

    @Override
    public boolean isStackable() {
        return true;
    }
}

