/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.den;

import vn.giakhanhvn.skysim.entity.den.BaseSpider;

public class CaveSpider
extends BaseSpider {
    @Override
    public String getEntityName() {
        return "Cave Spider";
    }

    @Override
    public double getEntityMaxHealth() {
        return 12.0;
    }

    @Override
    public double getDamageDealt() {
        return 5.0;
    }

    @Override
    public double getXPDropped() {
        return 5.7;
    }
}

