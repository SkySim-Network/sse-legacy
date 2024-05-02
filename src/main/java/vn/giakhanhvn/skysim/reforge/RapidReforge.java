/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.reforge;

import java.util.Collections;
import java.util.List;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.RarityValue;
import vn.giakhanhvn.skysim.reforge.Reforge;

public class RapidReforge
implements Reforge {
    @Override
    public String getName() {
        return "Rapid";
    }

    @Override
    public RarityValue<Double> getStrength() {
        return new RarityValue<Double>(2.0, 3.0, 4.0, 7.0, 10.0, 15.0);
    }

    @Override
    public RarityValue<Double> getCritDamage() {
        return new RarityValue<Double>(0.35, 0.45, 0.55, 0.65, 0.75, 0.9);
    }

    @Override
    public List<GenericItemType> getCompatibleTypes() {
        return Collections.singletonList(GenericItemType.RANGED_WEAPON);
    }
}

