/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity;

import vn.giakhanhvn.skysim.entity.Ageable;
import vn.giakhanhvn.skysim.entity.EntityStatistics;

public interface ZombieStatistics
extends EntityStatistics,
Ageable {
    default public boolean isVillager() {
        return false;
    }
}

