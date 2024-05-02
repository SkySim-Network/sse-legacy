/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.armor.vanilla.chainmail;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;

public class ChainmailChestplate
implements ToolStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Chainmail Chestplate";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.CHESTPLATE;
    }

    @Override
    public double getBaseDefense() {
        return 30.0;
    }
}

