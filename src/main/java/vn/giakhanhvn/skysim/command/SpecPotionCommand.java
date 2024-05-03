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
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.potion.PotionEffect;
import vn.giakhanhvn.skysim.potion.PotionEffectType;

@CommandParameters(description="Adds an potion from Spec to the specified item.", aliases="spot", permission="spt.item")
public class SpecPotionCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (args.length != 3) {
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
        PotionEffectType type = PotionEffectType.getByNamespace(args[0]);
        if (type == null) {
            throw new CommandFailException(ChatColor.RED + "Invalid potion type, try again, now actually put a vaild potion type, nerd!");
        }
        long duration = Long.parseLong(args[1]);
        int level = Integer.parseInt(args[2]);
        if (level <= 0) {
            this.send(ChatColor.RED + "What's the point?");
            return;
        }
        if (level > 2000) {
            this.send(ChatColor.RED + "Cap reached! Limit is LVL 2000!");
            return;
        }
        if (type == PotionEffectType.FEROCITY && level > 15) {
            this.send(ChatColor.RED + "Cap reached! Limit is LVL 15!");
            return;
        }
        sItem.addPotionEffect(new PotionEffect(type, level, duration));
        this.send(ChatColor.GREEN + "Your " + sItem.getType().getDisplayName(sItem.getVariant()) + " now has " + type.getName() + " " + level + ChatColor.GREEN + " on it.");
    }
}

