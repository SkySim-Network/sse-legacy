/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 */
package vn.giakhanhvn.skysim.util;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import vn.giakhanhvn.skysim.util.NBTExplorer;
import vn.giakhanhvn.skysim.util.SLog;

public class SkySimAPI {
    public static void requestPlayerAPI(OfflinePlayer player) {
        Player p = player.getPlayer();
        PlayerInventory inv = p.getInventory();
        StringBuilder sb = new StringBuilder();
        for (int i = 39; i >= 0; --i) {
            ItemStack stack = inv.getItem(i);
            if (stack == null) continue;
            sb.append(NBTExplorer.NBTSaver(stack));
        }
        SLog.info(sb.toString());
    }
}

