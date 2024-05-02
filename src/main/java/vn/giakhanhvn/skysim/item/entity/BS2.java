/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.entity;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;

public class BS2
implements SkullStatistics,
MaterialFunction {
    @Override
    public String getURL() {
        return "eef162def845aa3dc7d46cd08a7bf95bbdfd32d381215aa41bffad5224298728";
    }

    @Override
    public String getDisplayName() {
        return "BZB2";
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

