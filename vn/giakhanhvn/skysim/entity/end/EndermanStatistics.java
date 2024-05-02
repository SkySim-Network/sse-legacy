/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.material.MaterialData
 */
package vn.giakhanhvn.skysim.entity.end;

import org.bukkit.material.MaterialData;
import vn.giakhanhvn.skysim.entity.EntityStatistics;

public interface EndermanStatistics
extends EntityStatistics {
    default public MaterialData getCarriedMaterial() {
        return null;
    }
}

