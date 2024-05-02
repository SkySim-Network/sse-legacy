/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.reforge;

import java.util.Collections;
import java.util.List;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.RarityValue;
import vn.giakhanhvn.skysim.reforge.Reforge;

public class Fabled
implements Reforge {
    @Override
    public String getName() {
        return "Fabled";
    }

    @Override
    public RarityValue<Double> getStrength() {
        return new RarityValue<Double>(30.0, 35.0, 40.0, 50.0, 60.0, 75.0);
    }

    @Override
    public RarityValue<Double> getCritDamage() {
        return new RarityValue<Double>(0.15, 0.2, 0.25, 0.32, 0.4, 0.5);
    }

    @Override
    public List<GenericItemType> getCompatibleTypes() {
        return Collections.singletonList(GenericItemType.WEAPON);
    }
}

