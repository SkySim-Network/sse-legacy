/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.den;

import vn.giakhanhvn.skysim.entity.den.BaseSpider;

public class VoraciousSpider
extends BaseSpider {
    @Override
    public String getEntityName() {
        return "Voracious Spider";
    }

    @Override
    public double getEntityMaxHealth() {
        return 1000.0;
    }

    @Override
    public double getDamageDealt() {
        return 100.0;
    }

    @Override
    public double getXPDropped() {
        return 10.8;
    }

    @Override
    public int mobLevel() {
        return 10;
    }
}

