/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.end;

import vn.giakhanhvn.skysim.entity.end.BaseEnderman;

public class StrongEnderman
extends BaseEnderman {
    @Override
    public String getEntityName() {
        return "Enderman";
    }

    @Override
    public double getEntityMaxHealth() {
        return 9000.0;
    }

    @Override
    public double getDamageDealt() {
        return 700.0;
    }

    @Override
    public double getXPDropped() {
        return 36.0;
    }

    @Override
    public int mobLevel() {
        return 50;
    }
}

