/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.entity;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;

public class Nukekubi
implements SkullStatistics,
MaterialFunction {
    @Override
    public String getURL() {
        return "eb07594e2df273921a77c101d0bfdfa1115abed5b9b2029eb496ceba9bdbb4b3";
    }

    @Override
    public String getDisplayName() {
        return "Nukekubi";
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

