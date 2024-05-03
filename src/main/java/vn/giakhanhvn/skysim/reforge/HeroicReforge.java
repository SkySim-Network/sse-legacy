/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.reforge;

import java.util.Collections;
import java.util.List;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.RarityValue;
import vn.giakhanhvn.skysim.reforge.Reforge;

public class HeroicReforge
implements Reforge {
    @Override
    public String getName() {
        return "Heroic";
    }

    @Override
    public RarityValue<Double> getStrength() {
        return new RarityValue<Double>(15.0, 20.0, 25.0, 32.0, 40.0, 50.0);
    }

    @Override
    public RarityValue<Double> getIntelligence() {
        return new RarityValue<Double>(40.0, 50.0, 65.0, 80.0, 100.0, 125.0);
    }

    @Override
    public List<GenericItemType> getCompatibleTypes() {
        return Collections.singletonList(GenericItemType.WEAPON);
    }
}

