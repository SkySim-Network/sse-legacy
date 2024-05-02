/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.dungeons.watcher;

import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.EntityStatistics;

public class WatcherParasiteFish
implements EntityStatistics,
EntityFunction {
    @Override
    public String getEntityName() {
        return "Parasite";
    }

    @Override
    public double getEntityMaxHealth() {
        return 6.0E7;
    }

    @Override
    public double getDamageDealt() {
        return 500000.0;
    }

    @Override
    public double getXPDropped() {
        return 90.0;
    }

    @Override
    public int mobLevel() {
        return 125;
    }
}

