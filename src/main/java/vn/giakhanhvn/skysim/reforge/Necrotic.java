/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.reforge;

import java.util.Collections;
import java.util.List;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.RarityValue;
import vn.giakhanhvn.skysim.reforge.Reforge;

public class Necrotic
implements Reforge {
    @Override
    public String getName() {
        return "Necrotic";
    }

    @Override
    public RarityValue<Double> getIntelligence() {
        return new RarityValue<Double>(30.0, 60.0, 90.0, 120.0, 150.0, 200.0);
    }

    @Override
    public List<GenericItemType> getCompatibleTypes() {
        return Collections.singletonList(GenericItemType.ARMOR);
    }
}

