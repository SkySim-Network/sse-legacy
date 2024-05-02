/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.item;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.util.SUtil;

public enum ItemCategory {
    WEAPONS("Weapons", ChatColor.GOLD, 1),
    ARMOR("Armor", ChatColor.AQUA, 11),
    ACCESSORIES("Accessories", ChatColor.DARK_GREEN, 13),
    CONSUMABLES("Consumables", ChatColor.RED, 14),
    BLOCKS("Blocks", ChatColor.YELLOW, 12),
    TOOLS_MISC("Tools & Misc", ChatColor.LIGHT_PURPLE, 10);

    private final String name;
    private final ChatColor text;
    private final short item;

    private ItemCategory(String name, ChatColor text, short item) {
        this.name = name;
        this.text = text;
        this.item = item;
    }

    public String getName() {
        return this.name;
    }

    public String getColoredName() {
        return this.text + this.name;
    }

    public ItemStack getStainedGlassPane() {
        return SUtil.createColoredStainedGlassPane(this.item, " ");
    }
}

