/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.reforge;

import vn.giakhanhvn.skysim.item.RarityValue;
import vn.giakhanhvn.skysim.reforge.Reforge;

public class GeniusReforge
implements Reforge {
    @Override
    public String getName() {
        return "Genius";
    }

    @Override
    public RarityValue<Double> getIntelligence() {
        return RarityValue.singleDouble(10000.0);
    }
}

