/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.revenant;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;

public class UndeadCatalyst
implements SkullStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Undead Catalyst";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public String getURL() {
        return "80625369b0a7b052632db6b926a87670219539922836ac5940be26d34bf14e10";
    }
}

