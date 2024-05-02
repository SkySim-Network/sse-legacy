/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.StringUtils
 *  org.bukkit.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.PlayerInventory
 */
package vn.giakhanhvn.skysim.command;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.item.SItem;

@CommandParameters(description="Sets data for a Spec item.", permission="spt.item")
public class DataCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        String key;
        if (args.length < 3) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        Player player = sender.getPlayer();
        PlayerInventory inv = player.getInventory();
        if (inv.getItemInHand() == null) {
            throw new CommandFailException(ChatColor.RED + "Error! Hold an item in your hand!");
        }
        SItem sItem = SItem.find(inv.getItemInHand());
        if (!sItem.hasDataFor(key = args[0])) {
            throw new CommandFailException(ChatColor.RED + "Error! This item does not have data for '" + key + "'");
        }
        String joined = StringUtils.join((Object[])args, (String)" ", (int)1, (int)(args.length - 1));
        switch (args[args.length - 1].toLowerCase()) {
            case "string": {
                sItem.setDataString(key, joined);
                break;
            }
            case "integer": 
            case "int": {
                sItem.setDataInt(key, Integer.parseInt(joined));
                break;
            }
            case "long": {
                sItem.setDataLong(key, Long.parseLong(joined));
                break;
            }
            case "boolean": 
            case "bool": {
                sItem.setDataBoolean(key, Boolean.parseBoolean(joined));
                break;
            }
            case "double": 
            case "d": {
                sItem.setDataDouble(key, Double.parseDouble(joined));
                break;
            }
            case "float": 
            case "f": {
                sItem.setDataFloat(key, Float.parseFloat(joined));
            }
        }
        sItem.update();
        this.send(ChatColor.GREEN + "'" + key + "' for this item has been set to '" + joined + "' as type '" + args[args.length - 1].toLowerCase() + "'");
    }
}

