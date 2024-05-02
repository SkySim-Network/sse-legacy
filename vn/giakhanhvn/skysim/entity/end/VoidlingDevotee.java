/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.end;

import java.util.Arrays;
import java.util.List;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.entity.end.BaseEnderman;
import vn.giakhanhvn.skysim.item.SMaterial;

public class VoidlingDevotee
extends BaseEnderman {
    @Override
    public String getEntityName() {
        return "Voidling Devotee";
    }

    @Override
    public double getEntityMaxHealth() {
        return 2.5E7;
    }

    @Override
    public double getDamageDealt() {
        return 5000.0;
    }

    @Override
    public double getXPDropped() {
        return 1500.0;
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(SMaterial.NULL_SPHERE, EntityDropType.GUARANTEED, 1.0));
    }
}

