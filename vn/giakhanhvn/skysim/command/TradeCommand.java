/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="Modify your absorption amount.", permission="spt.player")
public class TradeCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        Player player = sender.getPlayer();
        if (args.length != 1) {
            this.send(Sputnik.trans("&cWrong usage of command! It must be /trade <PLAYER>"));
            return;
        }
        Player target = Bukkit.getPlayer((String)args[0]);
        if (target == null) {
            this.send(Sputnik.trans("&cCannot find player named &f" + args[0] + "&c, maybe they've gone offline?"));
            return;
        }
        Sputnik.tradeIntitize(target, player);
    }
}

