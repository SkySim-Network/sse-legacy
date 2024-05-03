/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.oddities;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;

public class DimoonFragment
implements SkullStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Dimoon's Microfragment";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
    }

    @Override
    public String getLore() {
        return null;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public boolean isStackable() {
        return true;
    }

    @Override
    public String getURL() {
        return "bb39c0e53e79e7ebd4db6c6d096c9d9a160cff27822c077fb8f5d45969cc4972";
    }
}

