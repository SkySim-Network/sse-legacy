/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.wolf;

import vn.giakhanhvn.skysim.entity.Ageable;
import vn.giakhanhvn.skysim.entity.EntityStatistics;

public interface WolfStatistics
extends EntityStatistics,
Ageable {
    default public boolean isAngry() {
        return false;
    }
}

