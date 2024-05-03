/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.oddities;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;

public class WardenHeart
implements MaterialStatistics,
MaterialFunction,
SkullStatistics {
    @Override
    public String getDisplayName() {
        return "Warden Heart";
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
    public boolean isEnchanted() {
        return true;
    }

    @Override
    public String getURL() {
        return "19a3b5fab01dfabf1f8a814440a8ce80de109b135a51aef225c9ee98fe447e0";
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public String getLore() {
        return "The heart of a powerful creature, dropped by the Atoned Horror.";
    }
}

