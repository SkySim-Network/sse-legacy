/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity;

import vn.giakhanhvn.skysim.entity.SEntity;

public final class StaticGiantManager {
    public static boolean ACTIVE = false;
    public static SEntity GIANT = null;

    public static void endFight() {
        if (GIANT == null) {
            return;
        }
        ACTIVE = false;
        GIANT = null;
    }
}

