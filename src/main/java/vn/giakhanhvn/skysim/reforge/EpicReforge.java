/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.reforge;

import java.util.Collections;
import java.util.List;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.RarityValue;
import vn.giakhanhvn.skysim.reforge.Reforge;

public class EpicReforge
implements Reforge {
    @Override
    public String getName() {
        return "Epic";
    }

    @Override
    public RarityValue<Double> getStrength() {
        return new RarityValue<Double>(15.0, 20.0, 25.0, 32.0, 40.0, 50.0);
    }

    @Override
    public RarityValue<Double> getCritDamage() {
        return new RarityValue<Double>(0.1, 0.15, 0.2, 0.27, 0.35, 0.45);
    }

    @Override
    public List<GenericItemType> getCompatibleTypes() {
        return Collections.singletonList(GenericItemType.WEAPON);
    }
}

