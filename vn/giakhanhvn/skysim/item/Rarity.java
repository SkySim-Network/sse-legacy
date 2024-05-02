/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.item;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.GREEN),
    RARE(ChatColor.BLUE),
    EPIC(ChatColor.DARK_PURPLE),
    LEGENDARY(ChatColor.GOLD),
    MYTHIC(ChatColor.LIGHT_PURPLE),
    SUPREME(ChatColor.AQUA),
    SPECIAL(ChatColor.RED),
    VERY_SPECIAL(ChatColor.RED),
    EXCLUSIVE(ChatColor.GRAY);

    private final ChatColor color;

    private Rarity(ChatColor color) {
        this.color = color;
    }

    public Rarity upgrade() {
        return Rarity.values()[Math.min(this.ordinal() + 1, Rarity.values().length - 1)];
    }

    public Rarity downgrade() {
        if (this.ordinal() - 1 < 0) {
            return this;
        }
        return Rarity.values()[this.ordinal() - 1];
    }

    public boolean isAtLeast(Rarity rarity) {
        return this.ordinal() >= rarity.ordinal();
    }

    public String getDisplay() {
        return "" + this.color + ChatColor.BOLD + this.name().replaceAll("_", " ");
    }

    public String getBoldedColor() {
        return "" + this.color + ChatColor.BOLD;
    }

    public static Rarity getRarity(String string) {
        try {
            return Rarity.valueOf(string.toUpperCase());
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public ChatColor getColor() {
        return this.color;
    }
}

