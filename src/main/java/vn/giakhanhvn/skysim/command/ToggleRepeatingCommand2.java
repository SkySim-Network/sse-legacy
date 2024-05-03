/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;

@CommandParameters(description="Spec test command.", aliases="db:ssp")
public class ToggleRepeatingCommand2
extends SCommand {
    public Repeater repeater;

    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        if (player == null) {
            this.send(ChatColor.RED + "Something occurred while taking services from the API!");
        }
    }
}

