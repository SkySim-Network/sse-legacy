/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.entity;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;

public class BS7
implements SkullStatistics,
MaterialFunction {
    @Override
    public String getURL() {
        return "7a2df315b43583b1896231b77bae1a507dbd7e43ad86c1cfbe3b2b8ef3430e9e";
    }

    @Override
    public String getDisplayName() {
        return "BZB7";
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

