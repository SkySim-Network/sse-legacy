/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.entity;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;

public class RevenantHorrorHead
implements SkullStatistics,
MaterialFunction {
    @Override
    public String getURL() {
        return "d8bee23b5c726ae8e3d021e8b4f7525619ab102a4e04be983b61414349aaac67";
    }

    @Override
    public String getDisplayName() {
        return "Revenant Horror Head";
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

