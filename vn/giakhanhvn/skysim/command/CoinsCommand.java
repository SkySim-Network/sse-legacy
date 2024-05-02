/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;

@CommandParameters(description="Modify your coin amount.", permission="spt.balance")
public class CoinsCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (args.length != 0 && args.length != 2) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        User user = sender.getUser();
        if (args.length == 0) {
            user.setPermanentCoins(!user.isPermanentCoins());
            this.send(ChatColor.GREEN + "Your coins are no" + (user.isPermanentCoins() ? "w" : " longer") + " permanent.");
            return;
        }
        long coins = Long.parseLong(args[1]);
        switch (args[0].toLowerCase()) {
            case "add": {
                user.addCoins(coins);
                this.send(ChatColor.GREEN + "You (or someone) have added " + ChatColor.GOLD + SUtil.commaify(coins) + ChatColor.GREEN + " to your purse.");
                return;
            }
            case "subtract": 
            case "sub": 
            case "take": {
                user.subCoins(coins);
                this.send(ChatColor.GREEN + "You (or someone) have subtracted " + ChatColor.GOLD + SUtil.commaify(coins) + ChatColor.GREEN + " from your Purse.");
                return;
            }
            case "set": {
                user.setCoins(coins);
                this.send(ChatColor.GREEN + "You (or someone) have set your Purse coins to " + ChatColor.GOLD + SUtil.commaify(coins) + ".");
                return;
            }
        }
        throw new CommandArgumentException();
    }
}

