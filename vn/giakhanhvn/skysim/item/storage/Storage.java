/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  org.bukkit.entity.Player
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.Inventory
 */
package vn.giakhanhvn.skysim.item.storage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.ItemData;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Untradeable;

public abstract class Storage
implements MaterialStatistics,
MaterialFunction,
ItemData,
Untradeable {
    private static final Map<UUID, Inventory> OPENED_STORAGE_UNITS = new HashMap<UUID, Inventory>();

    public static Inventory getCurrentStorageOpened(Player player) {
        return OPENED_STORAGE_UNITS.get(player.getUniqueId());
    }

    public static void closeCurrentStorage(Player player) {
        OPENED_STORAGE_UNITS.remove(player.getUniqueId());
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public void onInteraction(PlayerInteractEvent e) {
        e.getPlayer().sendMessage(ChatColor.RED + "This item is currently disabled due to an exploit! Find out more " + ChatColor.WHITE + "https://bit.ly/skysim");
    }

    @Override
    public NBTTagCompound getData() {
        return new NBTTagCompound();
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    public abstract int getSlots();
}

