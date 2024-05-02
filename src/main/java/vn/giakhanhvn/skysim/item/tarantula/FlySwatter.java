/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.tarantula;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Rarity;

public class FlySwatter
implements MaterialStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Fly Swatter";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }
}

