/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.user.User;

@CommandParameters(description="bruhbu", aliases="datarec")
public class InvRecovery
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        User user = sender.getUser();
        if (!player.isOp()) {
            this.send(ChatColor.RED + "No permission to execute this command!");
            return;
        }
        if (args.length == 0) {
            this.send(ChatColor.RED + "Invaild Syntax! You need to provide a player name");
            return;
        }
        Player target = Bukkit.getPlayer((String)args[0]);
        if (target != null) {
            User user1 = User.getUser(target.getUniqueId());
            try {
                user1.loadPlayerData();
                user.send("&aSuccess!");
                user1.send("&eData Recovered, now disconnect and join back.");
            }
            catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
                user.send("&cError! Check Console!");
            }
        } else {
            this.send(ChatColor.RED + "Invaild Syntax! You need to provide a vaild player name.");
            return;
        }
    }
}

