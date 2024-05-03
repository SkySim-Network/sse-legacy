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
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="The main command for SkySimEngine.", aliases="ssei")
public class SkySimEngineCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        this.send(ChatColor.RED + "Sky" + ChatColor.GREEN + "Sim" + ChatColor.GOLD + " Engine v" + plugin.getDescription().getVersion());
        this.send(ChatColor.GREEN + "Created by" + ChatColor.GOLD + " GiaKhanhVN " + ChatColor.GREEN + "for SkySim Usage and the \ncompatibility with highly modified version of Spigot!");
        this.send(Sputnik.trans("&aSome of the data structures and logic have been taken from &6super&a's (superischroma) project, thank you super, very cool!"));
    }
}

