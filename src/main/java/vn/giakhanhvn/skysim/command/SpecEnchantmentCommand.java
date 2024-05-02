/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.item.SItem;

@CommandParameters(description="Adds an enchantment from Spec to the specified item.", aliases="sench", permission="spt.item")
public class SpecEnchantmentCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (args.length != 2) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        Player player = sender.getPlayer();
        ItemStack stack = player.getInventory().getItemInHand();
        if (stack == null) {
            throw new CommandFailException(ChatColor.RED + "You don't have anything in your hand!");
        }
        SItem sItem = SItem.find(stack);
        if (sItem == null) {
            throw new CommandFailException(ChatColor.RED + "That item is not executable!");
        }
        EnchantmentType type = EnchantmentType.getByNamespace(args[0]);
        if (type == null) {
            throw new CommandFailException(ChatColor.RED + "Invalid enchantment type!");
        }
        int i = Integer.parseInt(args[1]);
        if (i <= 0) {
            this.send(ChatColor.RED + "Are you serious? If you want to remove enchantments, use /re");
            return;
        }
        if (i > 10000000) {
            this.send(ChatColor.RED + "Too high enchantment level.");
            return;
        }
        if (i > 50 && type == EnchantmentType.CHIMERA) {
            this.send(ChatColor.RED + "Too high enchantment level.");
            return;
        }
        if (i > 100 && type == EnchantmentType.VICIOUS) {
            this.send(ChatColor.RED + "Too high enchantment level.");
            return;
        }
        if (i > 100 && type == EnchantmentType.LUCKINESS) {
            this.send(ChatColor.RED + "Too high enchantment level.");
            return;
        }
        if (i > 10 && type == EnchantmentType.LEGION) {
            this.send(ChatColor.RED + "Too high enchantment level.");
            return;
        }
        sItem.addEnchantment(type, i);
        this.send(ChatColor.GREEN + "Your " + sItem.getType().getDisplayName(sItem.getVariant()) + " now has " + type.getName() + " " + i + " on it.");
    }
}

