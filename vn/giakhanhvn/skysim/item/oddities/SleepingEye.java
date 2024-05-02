/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.oddities;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;

public class SleepingEye
implements SkullStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Sleeping Eye";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public String getLore() {
        return "Keep this item in your inventory to recover your placed Summoning Eye when you leave or when you click the Ender Altar. This item becomes imbued with the magic of the Dragon when it spawns, turning it into a Remnant of the Eye.";
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public String getURL() {
        return "37c0d010dd0e512ffea108d7c5fe69d576c31ec266c884b51ec0b28cc457";
    }
}

