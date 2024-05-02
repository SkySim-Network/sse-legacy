/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;

@CommandParameters(description="Modify your mana amount.", permission="spt.item")
public class ManaCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (args.length != 1) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        Player player = sender.getPlayer();
        UUID uuid = player.getUniqueId();
        if (!Repeater.MANA_MAP.containsKey(uuid)) {
            throw new CommandFailException("Something went wrong!");
        }
        int set = Integer.parseInt(args[0]);
        Repeater.MANA_MAP.remove(uuid);
        Repeater.MANA_MAP.put(uuid, set);
        this.send(ChatColor.GREEN + "Your mana is now " + ChatColor.AQUA + set + ".");
    }
}

