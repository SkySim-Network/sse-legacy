/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.entity;

import org.bukkit.ChatColor;

public enum EntityDropType {
    GUARANTEED(ChatColor.GREEN),
    COMMON(ChatColor.GREEN),
    OCCASIONAL(ChatColor.BLUE),
    RARE(ChatColor.GOLD),
    VERY_RARE(ChatColor.AQUA),
    EXTRAORDINARILY_RARE(ChatColor.DARK_PURPLE),
    CRAZY_RARE(ChatColor.LIGHT_PURPLE),
    INSANE_RARE(ChatColor.RED);

    private final ChatColor color;

    private EntityDropType(ChatColor color) {
        this.color = color;
    }

    public String getDisplay() {
        return "" + this.color + ChatColor.BOLD + this.name().replaceAll("_", " ");
    }

    public ChatColor getColor() {
        return this.color;
    }
}

