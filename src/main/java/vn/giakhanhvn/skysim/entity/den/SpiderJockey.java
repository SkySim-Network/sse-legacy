/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.den;

import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.EntityStatistics;
import vn.giakhanhvn.skysim.entity.JockeyStatistics;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.entity.den.BaseSpider;

public class SpiderJockey
extends BaseSpider
implements JockeyStatistics {
    @Override
    public SEntityType getPassenger() {
        return SEntityType.JOCKEY_SKELETON;
    }

    @Override
    public String getEntityName() {
        return "Spider";
    }

    @Override
    public double getEntityMaxHealth() {
        return 180.0;
    }

    @Override
    public double getDamageDealt() {
        return 30.0;
    }

    @Override
    public double getXPDropped() {
        return 8.0;
    }

    public static class JockeySkeleton
    implements EntityStatistics,
    EntityFunction {
        @Override
        public String getEntityName() {
            return "Jockey Skeleton";
        }

        @Override
        public double getEntityMaxHealth() {
            return 250.0;
        }

        @Override
        public double getDamageDealt() {
            return 38.0;
        }

        @Override
        public double getXPDropped() {
            return 6.0;
        }
    }
}

