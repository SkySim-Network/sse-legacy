/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.World
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="bruhbu", aliases="hpb")
public class HotPotatoBookCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        World world = player.getWorld();
        SItem sitem = SItem.find(player.getItemInHand());
        if (args.length != 0 && args.length != 2) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        User user = sender.getUser();
        if (args.length == 0) {
            this.send(ChatColor.RED + "Invaild Syntax! The command is /hpb <set/add> <amount>");
            return;
        }
        int hpb = Integer.parseInt(args[1]);
        if (sitem == null) {
            this.send(ChatColor.RED + "You can't execute this command while not holding anything or a non-executable item.");
            return;
        }
        if (sitem.getType().getStatistics().getType() != GenericItemType.ARMOR && sitem.getType().getStatistics().getType() != GenericItemType.WEAPON && sitem.getType().getStatistics().getType() != GenericItemType.RANGED_WEAPON) {
            this.send(ChatColor.RED + "You can't put Hot Potato Books to this Item!");
            return;
        }
        switch (args[0].toLowerCase()) {
            case "add": {
                if (sitem.getDataInt("hpb") + hpb > 10) {
                    this.send(ChatColor.RED + "This item reaches it's limit. The maximum is 10 books!");
                    return;
                }
                if (hpb <= 0) {
                    this.send(ChatColor.RED + "Bruh, why? You can't do that");
                    return;
                }
                sitem.setDataInt("hpb", sitem.getDataInt("hpb") + hpb);
                this.send(Sputnik.trans("&e" + hpb + " &aHot Potato Book has been added into your " + sitem.getFullName()));
                return;
            }
            case "set": {
                if (hpb > 10) {
                    this.send(ChatColor.RED + "The maximum is 10 books applied!");
                    return;
                }
                if (hpb < 0) {
                    this.send(ChatColor.RED + "Bruh, why? You can't do that!");
                    return;
                }
                sitem.setDataInt("hpb", hpb);
                this.send(Sputnik.trans("&aYour " + sitem.getFullName() + " &aHot Potato Book has been set to &e" + hpb));
                return;
            }
            case "stalinpotatofield": {
                if (hpb > 2500) {
                    this.send(ChatColor.RED + "Hey, do you want the entire USSR to strave? The cap is 2,500 books! Yikes!");
                    return;
                }
                if (hpb < 0) {
                    this.send(ChatColor.RED + "Bruh, why? You can't do that! No more potato for you.");
                    return;
                }
                sitem.setDataInt("hpb", hpb);
                this.send(Sputnik.trans("&aYour " + sitem.getFullName() + " &aHot Potato Book has been set to &e" + SUtil.commaify(hpb) + "&a, bypassed the normal cap because of the glory of &eStalin's Potato Fields&a!"));
                return;
            }
        }
        throw new CommandArgumentException();
    }
}

