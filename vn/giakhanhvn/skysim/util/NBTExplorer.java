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
import org.json.JSONObject;

public class NBTExplorer {
    public static JSONObject NBTSaver(org.bukkit.inventory.ItemStack i) {
        JSONObject jo = new JSONObject();
        ItemStack stack = CraftItemStack.asNMSCopy((org.bukkit.inventory.ItemStack)i);
        NBTTagCompound compound = stack.getTag();
        String finalnbt = "";
        StringBuilder sb = new StringBuilder();
        int c = 34;
        if (compound != null) {
            for (String key : compound.c()) {
                jo.put(key, compound.get(key).toString());
            }
        }
        return jo;
    }
}

