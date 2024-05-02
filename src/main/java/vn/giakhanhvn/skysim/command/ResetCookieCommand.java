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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="bruhbu", aliases="resetcb")
public class ResetCookieCommand
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
            this.send(ChatColor.RED + "Invaild Syntax! You need to provide a player");
            return;
        }
        Player target = Bukkit.getPlayer((String)args[0]);
        if (target == null) {
            this.send(ChatColor.RED + "Invaild Syntax! You need to provide a vaild player");
            return;
        }
        PlayerUtils.setCookieDurationTicks(target, 0L);
        this.send(Sputnik.trans("&aReseted " + target.getName() + "'s &dCookie Buff&a."));
        target.sendMessage(Sputnik.trans("&e[WARNING] ") + ChatColor.RED + player.getName() + " have reseted your Cookie Buff. If you believe this is an error, contact Admins.");
    }
}

