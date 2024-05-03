/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.end;

import vn.giakhanhvn.skysim.entity.end.BaseEnderman;

public class Enderman
extends BaseEnderman {
    @Override
    public String getEntityName() {
        return "Enderman";
    }

    @Override
    public double getEntityMaxHealth() {
        return 6000.0;
    }

    @Override
    public double getDamageDealt() {
        return 600.0;
    }

    @Override
    public double getXPDropped() {
        return 32.0;
    }

    @Override
    public int mobLevel() {
        return 45;
    }
}

