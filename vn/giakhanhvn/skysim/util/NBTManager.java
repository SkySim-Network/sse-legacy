/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.ItemStack
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.util;

import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

public class NBTManager {
    private static NBTTagCompound getTag(org.bukkit.inventory.ItemStack item) {
        ItemStack itemNms = CraftItemStack.asNMSCopy((org.bukkit.inventory.ItemStack)item);
        NBTTagCompound tag = itemNms.hasTag() ? itemNms.getTag() : new NBTTagCompound();
        return tag;
    }

    private static org.bukkit.inventory.ItemStack setTag(org.bukkit.inventory.ItemStack item, NBTTagCompound tag) {
        ItemStack itemNms = CraftItemStack.asNMSCopy((org.bukkit.inventory.ItemStack)item);
        itemNms.setTag(tag);
        return CraftItemStack.asBukkitCopy((ItemStack)itemNms);
    }

    public static org.bukkit.inventory.ItemStack addString(org.bukkit.inventory.ItemStack item, String name, String value) {
        NBTTagCompound tag = NBTManager.getTag(item);
        tag.setString(name, value);
        return NBTManager.setTag(item, tag);
    }

    public static boolean hasString(org.bukkit.inventory.ItemStack item, String name, String string) {
        NBTTagCompound tag = NBTManager.getTag(item);
        return tag.hasKey(name);
    }

    public static String getString(org.bukkit.inventory.ItemStack item, String name) {
        NBTTagCompound tag = NBTManager.getTag(item);
        return tag.getString(name);
    }
}

