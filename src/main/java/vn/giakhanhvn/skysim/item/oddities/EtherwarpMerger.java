/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.oddities;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;
import vn.giakhanhvn.skysim.util.Sputnik;

public class EtherwarpMerger
implements MaterialStatistics,
MaterialFunction,
SkullStatistics {
    @Override
    public String getDisplayName() {
        return "Etherwarp Merger";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
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
    public String getLore() {
        return Sputnik.trans("&8Crafted by the &8Etherchemist &8and dropped &8by the Enderseraph.");
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public String getURL() {
        return "3e5314f4919691ccbf807743dae47ae45ac2e3ff08f79eecdd452fe602eff7f6";
    }
}

