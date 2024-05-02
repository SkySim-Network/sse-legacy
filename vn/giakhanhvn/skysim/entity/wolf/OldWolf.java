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

public class OldWolf
extends BaseWolf {
    @Override
    public String getEntityName() {
        return "Old Wolf";
    }

    @Override
    public double getEntityMaxHealth() {
        return 15000.0;
    }

    @Override
    public double getDamageDealt() {
        return 720.0;
    }

    @Override
    public List<EntityDrop> drops() {
        return Collections.singletonList(new EntityDrop(SMaterial.BONE, EntityDropType.GUARANTEED, 1.0));
    }

    @Override
    public double getXPDropped() {
        return 40.0;
    }

    @Override
    public boolean isAngry() {
        return true;
    }

    @Override
    public int mobLevel() {
        return 50;
    }
}

