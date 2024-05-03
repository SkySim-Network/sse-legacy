/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.entity;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;

public class RevenantHorrorHead2
implements SkullStatistics,
MaterialFunction {
    @Override
    public String getURL() {
        return "ba7bb34471508b4b20cb35c37a1dd6e8afa7f2d5388822138837caf59c2195d8";
    }

    @Override
    public String getDisplayName() {
        return "Revenant Horror Head 2";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EXCLUSIVE;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }
}

