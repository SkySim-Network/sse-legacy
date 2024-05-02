/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.den;

import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.EntityStatistics;

public class Silverfish
implements EntityStatistics,
EntityFunction {
    @Override
    public String getEntityName() {
        return "Silverfish";
    }

    @Override
    public double getEntityMaxHealth() {
        return 50.0;
    }

    @Override
    public double getDamageDealt() {
        return 20.0;
    }

    @Override
    public double getXPDropped() {
        return 5.4;
    }
}

