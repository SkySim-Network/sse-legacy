/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.wolf;

import java.util.Collections;
import java.util.List;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.entity.wolf.BaseWolf;
import vn.giakhanhvn.skysim.item.SMaterial;

public class PackEnforcer
extends BaseWolf {
    @Override
    public String getEntityName() {
        return "Pack Enforcer";
    }

    @Override
    public double getEntityMaxHealth() {
        return 45000.0;
    }

    @Override
    public double getDamageDealt() {
        return 900.0;
    }

    @Override
    public List<EntityDrop> drops() {
        return Collections.singletonList(new EntityDrop(SMaterial.WOLF_TOOTH, EntityDropType.GUARANTEED, 1.0));
    }

    @Override
    public double getXPDropped() {
        return 150.0;
    }

    @Override
    public boolean isAngry() {
        return true;
    }
}

