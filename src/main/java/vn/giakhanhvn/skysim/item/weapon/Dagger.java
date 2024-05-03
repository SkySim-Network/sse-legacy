/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.weapon;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;

public class Dagger
implements ToolStatistics,
MaterialFunction {
    @Override
    public int getBaseDamage() {
        return 50;
    }

    @Override
    public double getBaseSpeed() {
        return 0.4;
    }

    @Override
    public String getDisplayName() {
        return "Hunter Knife";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.WEAPON;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.SWORD;
    }
}

