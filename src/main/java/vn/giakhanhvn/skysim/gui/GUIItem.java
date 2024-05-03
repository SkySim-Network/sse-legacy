/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.util.SUtil;

public interface GUIItem {
    public int getSlot();

    default public ItemStack getItem() {
        return new ItemStack(Material.AIR);
    }

    default public boolean canPickup() {
        return false;
    }

    public static GUIItem createLoadingItem(final Material type, final String name, final int slot) {
        return new GUIItem(){

            @Override
            public int getSlot() {
                return slot;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getSingleLoreStack(name, type, (short)0, 1, ChatColor.DARK_GRAY + "Loading...");
            }
        };
    }
}

