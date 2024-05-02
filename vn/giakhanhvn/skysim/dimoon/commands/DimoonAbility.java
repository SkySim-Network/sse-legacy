/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.dimoon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.SkySimEngine;

public class DimoonAbility
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        SkySimEngine plugin = SkySimEngine.getPlugin();
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (plugin.dimoon != null) {
                plugin.dimoon.getAbilities().get(Integer.parseInt(args[0])).activate(player, plugin.dimoon);
            }
        }
        return true;
    }
}

