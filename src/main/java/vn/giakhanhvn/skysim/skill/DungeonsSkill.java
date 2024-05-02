/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.skill;

import java.util.List;

public interface DungeonsSkill {
    default public List<String> getPassive() {
        return null;
    }

    default public List<String> getGhost() {
        return null;
    }

    default public List<String> getOrb() {
        return null;
    }
}

