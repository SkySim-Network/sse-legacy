/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.nether;

import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.SlimeStatistics;

public class MediumMagmaCube
implements SlimeStatistics,
EntityFunction {
    @Override
    public String getEntityName() {
        return "Magma Cube";
    }

    @Override
    public double getEntityMaxHealth() {
        return 250.0;
    }

    @Override
    public double getDamageDealt() {
        return 120.0;
    }

    @Override
    public double getXPDropped() {
        return 4.0;
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public boolean split() {
        return false;
    }
}

