/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item;

import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.SItem;

public interface TickingMaterial
extends MaterialFunction {
    default public void tick(SItem item, Player owner) {
    }

    default public long getInterval() {
        return 2L;
    }
}

