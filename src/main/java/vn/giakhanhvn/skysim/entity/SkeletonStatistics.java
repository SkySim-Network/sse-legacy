/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity;

import vn.giakhanhvn.skysim.entity.EntityStatistics;

public interface SkeletonStatistics
extends EntityStatistics {
    default public boolean isWither() {
        return false;
    }
}

