/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.PlayerInventory
 */
package vn.giakhanhvn.skysim.command;

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
import vn.giakhanhvn.skysim.item.SMaterial;

@CommandParameters(description="Gives an item from Spec.", aliases="sitem,specitem", permission="spt.item")
public class ItemCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (args.length < 1 || args.length > 2) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        Player player = sender.getPlayer();
        if (!player.isOp()) {
            return;
        }
        SMaterial material = SMaterial.getMaterial(args[0]);
        if (material == null) {
            throw new CommandFailException(ChatColor.RED + "Invalid material! That's item don't even exist!");
        }
        PlayerInventory inv = player.getInventory();
        if (inv.firstEmpty() == -1) {
            throw new CommandFailException(ChatColor.RED + "Not enough Inventory space, clean it up buddy.");
        }
        byte variant = 0;
        if (args.length == 2) {
            variant = Byte.parseByte(args[1]);
        }
        inv.setItem(inv.firstEmpty(), SItem.of(material, variant).getStack());
        this.send(ChatColor.GREEN + "You have received " + material.getDisplayName(variant));
    }
}

