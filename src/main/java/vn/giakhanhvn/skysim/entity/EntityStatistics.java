/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity;

import vn.giakhanhvn.skysim.entity.SEntityEquipment;

public interface EntityStatistics {
    public String getEntityName();

    public double getEntityMaxHealth();

    public double getDamageDealt();

    public double getXPDropped();

    default public SEntityEquipment getEntityEquipment() {
        return null;
    }

    default public double getMovementSpeed() {
        return -1.0;
    }

    default public int mobLevel() {
        return 0;
    }

    default public boolean dealsTrueDamage() {
        return false;
    }

    default public boolean hasNameTag() {
        return true;
    }

    default public boolean removeWhenFarAway() {
        return false;
    }

    default public boolean isVisible() {
        return true;
    }
}

