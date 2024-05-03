/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.reforge;

import java.util.Collections;
import java.util.List;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.RarityValue;
import vn.giakhanhvn.skysim.reforge.Reforge;

public class WitheredReforge
implements Reforge {
    @Override
    public String getName() {
        return "Withered";
    }

    @Override
    public RarityValue<Double> getStrength() {
        return new RarityValue<Double>(60.0, 75.0, 90.0, 110.0, 135.0, 170.0);
    }

    @Override
    public List<GenericItemType> getCompatibleTypes() {
        return Collections.singletonList(GenericItemType.WEAPON);
    }
}

