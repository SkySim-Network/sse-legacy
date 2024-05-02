/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.tarantula;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Rarity;

public class DigestedMosquito
implements MaterialStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Digested Mosquito";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public boolean isStackable() {
        return false;
    }
}

