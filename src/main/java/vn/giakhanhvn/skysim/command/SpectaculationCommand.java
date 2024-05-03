/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.ChatColor;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;

@CommandParameters(description="The main command for Spectaculation.", aliases="sse")
public class SpectaculationCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        this.send(ChatColor.RED + "Sky" + ChatColor.GREEN + "Sim" + ChatColor.GOLD + " Engine v" + plugin.getDescription().getVersion());
        this.send(ChatColor.GREEN + "Created by" + ChatColor.GOLD + " GiaKhanhVN " + ChatColor.GREEN + "for SkySim Usage and the \ncompatibility with highly modified version of Spigot!");
    }
}

