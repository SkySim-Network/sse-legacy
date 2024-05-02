/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.nms;

import java.util.UUID;

public interface SlayerBoss {
    public UUID getSpawnerUUID();

    default public int getTier() {
        return 1;
    }
}

