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

public class Wolf
extends BaseWolf {
    @Override
    public String getEntityName() {
        return "Wolf";
    }

    @Override
    public double getEntityMaxHealth() {
        return 250.0;
    }

    @Override
    public double getDamageDealt() {
        return 80.0;
    }

    @Override
    public List<EntityDrop> drops() {
        return Collections.singletonList(new EntityDrop(SMaterial.BONE, EntityDropType.GUARANTEED, 1.0));
    }

    @Override
    public double getXPDropped() {
        return 10.0;
    }

    @Override
    public boolean isAngry() {
        return true;
    }

    @Override
    public int mobLevel() {
        return 15;
    }
}

