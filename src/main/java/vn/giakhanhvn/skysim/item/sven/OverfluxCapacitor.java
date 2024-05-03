/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.sven;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Rarity;

public class OverfluxCapacitor
implements MaterialStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Overflux Capacitor";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public boolean isEnchanted() {
        return true;
    }

    @Override
    public boolean isStackable() {
        return false;
    }
}

