/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.armor;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.armor.ArmorSet;

public interface TickingSet
extends ArmorSet {
    public void tick(Player var1, SItem var2, SItem var3, SItem var4, SItem var5, List<AtomicInteger> var6);
}

