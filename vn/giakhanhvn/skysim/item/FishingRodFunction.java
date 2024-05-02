/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.player.PlayerFishEvent
 */
package vn.giakhanhvn.skysim.item;

import org.bukkit.event.player.PlayerFishEvent;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.SItem;

public interface FishingRodFunction
extends MaterialFunction {
    public void onFish(SItem var1, PlayerFishEvent var2);
}

