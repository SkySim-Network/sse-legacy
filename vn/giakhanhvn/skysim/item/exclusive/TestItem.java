/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.exclusive;

import vn.giakhanhvn.skysim.item.Enchantable;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;

public class TestItem
implements SkullStatistics,
MaterialFunction,
Enchantable {
    @Override
    public String getURL() {
        return "c0340923a6de4825a176813d133503eff186db0896e32b6704928c2a2bf68422";
    }

    @Override
    public String getDisplayName() {
        return "Test Item";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EXCLUSIVE;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }
}

