/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.accessory;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.PlayerBoostStatistics;
import vn.giakhanhvn.skysim.item.Reforgable;
import vn.giakhanhvn.skysim.item.SkullStatistics;
import vn.giakhanhvn.skysim.item.SpecificItemType;

public interface AccessoryStatistics
extends PlayerBoostStatistics,
SkullStatistics,
Reforgable {
    @Override
    default public GenericItemType getType() {
        return GenericItemType.ACCESSORY;
    }

    @Override
    default public SpecificItemType getSpecificType() {
        return SpecificItemType.ACCESSORY;
    }
}

