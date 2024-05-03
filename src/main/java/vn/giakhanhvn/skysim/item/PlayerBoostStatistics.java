/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item;

import vn.giakhanhvn.skysim.item.MaterialStatistics;

public interface PlayerBoostStatistics
extends MaterialStatistics {
    default public int getBaseDamage() {
        return 0;
    }

    default public double getBaseStrength() {
        return 0.0;
    }

    default public double getBaseCritChance() {
        return 0.0;
    }

    default public double getBaseCritDamage() {
        return 0.0;
    }

    default public double getBaseMagicFind() {
        return 0.0;
    }

    default public double getBaseIntelligence() {
        return 0.0;
    }

    default public double getBaseSpeed() {
        return 0.0;
    }

    default public double getBaseHealth() {
        return 0.0;
    }

    default public double getBaseDefense() {
        return 0.0;
    }

    default public double getBaseAttackSpeed() {
        return 0.0;
    }

    default public double getBaseFerocity() {
        return 0.0;
    }
}

