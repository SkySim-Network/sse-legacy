/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.orb;

public interface OrbBuff {
    public String getBuffName();

    public String getBuffDescription();

    default public String getCustomOrbName() {
        return null;
    }
}

