/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.ChatColor;

public class PlayerNotFoundException
extends RuntimeException {
    public PlayerNotFoundException() {
        super(ChatColor.GRAY + "Player not found!");
    }
}

