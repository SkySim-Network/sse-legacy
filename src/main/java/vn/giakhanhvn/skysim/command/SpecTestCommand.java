/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.user.User;

@CommandParameters(description="Spec test command.", aliases="setf6c")
public class SpecTestCommand
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
        if (player.isOp()) {
            int set = Integer.parseInt(args[0]);
            User user = User.getUser(player.getUniqueId());
            User.getUser(player.getUniqueId()).setBCollection(set);
        }
    }
}

