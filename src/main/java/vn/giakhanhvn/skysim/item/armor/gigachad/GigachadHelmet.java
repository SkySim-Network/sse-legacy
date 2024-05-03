/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.armor.gigachad;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SkullStatistics;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;

public class GigachadHelmet
implements MaterialFunction,
SkullStatistics,
ToolStatistics {
    @Override
    public double getBaseStrength() {
        return 100.0;
    }

    @Override
    public double getBaseCritChance() {
        return 0.1;
    }

    @Override
    public double getBaseCritDamage() {
        return 0.3;
    }

    @Override
    public double getBaseIntelligence() {
        return 100.0;
    }

    @Override
    public double getBaseMagicFind() {
        return 0.08;
    }

    @Override
    public double getBaseSpeed() {
        return 0.05;
    }

    @Override
    public double getBaseHealth() {
        return 150.0;
    }

    @Override
    public double getBaseDefense() {
        return 100.0;
    }

    @Override
    public String getURL() {
        return "b11777916e756443e30a043f2bc9cce0d0fe624bd6192d7ba23f96a1d71b9f3f";
    }

    @Override
    public String getDisplayName() {
        return "Gigachad Helmet";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.MYTHIC;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.HELMET;
    }

    @Override
    public String getLore() {
        return null;
    }
}

