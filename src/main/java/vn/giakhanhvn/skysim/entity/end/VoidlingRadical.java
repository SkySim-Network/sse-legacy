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

public class VoidlingRadical
extends BaseEnderman {
    @Override
    public String getEntityName() {
        return "Voidling Radical";
    }

    @Override
    public double getEntityMaxHealth() {
        return 1.2E7;
    }

    @Override
    public double getDamageDealt() {
        return 6000.0;
    }

    @Override
    public double getXPDropped() {
        return 400.0;
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(SMaterial.NULL_SPHERE, EntityDropType.GUARANTEED, 1.0));
    }
}

