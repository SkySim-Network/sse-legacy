/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.PlayerInventory
 */
package vn.giakhanhvn.skysim.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class InventoryUpdate {
    public static void removeInventoryItems(PlayerInventory inv, Material type, int amount) {
        for (ItemStack is : inv.getContents()) {
            if (is == null || is.getType() != type) continue;
            int newamount = is.getAmount() - amount;
            if (newamount > 0) {
                is.setAmount(newamount);
                break;
            }
            inv.remove(is);
            amount = -newamount;
            if (amount == 0) break;
        }
    }

    public static void removeInventoryItemStack(PlayerInventory inv, String txt, int amount) {
        for (ItemStack is : inv.getContents()) {
            if (is == null || !is.getItemMeta().getDisplayName().contains(txt)) continue;
            int newamount = is.getAmount() - amount;
            if (newamount > 0) {
                is.setAmount(newamount);
                break;
            }
            inv.remove(is);
            amount = -newamount;
            if (amount == 0) break;
        }
    }
}

