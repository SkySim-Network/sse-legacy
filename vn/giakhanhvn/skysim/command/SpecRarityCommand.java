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
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;

@CommandParameters(description="Modifies the rarity of an item.", aliases="rar", permission="spt.item")
public class SpecRarityCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (args.length > 1) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        Player player = sender.getPlayer();
        if (!player.isOp()) {
            return;
        }
        ItemStack stack = player.getInventory().getItemInHand();
        if (stack == null) {
            throw new CommandFailException(ChatColor.RED + "You don't have anything in your hand!");
        }
        SItem sItem = SItem.find(stack);
        if (sItem == null) {
            throw new CommandFailException(ChatColor.RED + "That item is not executable!");
        }
        if (args.length == 0) {
            this.send("Your " + sItem.getType().getDisplayName(sItem.getVariant()) + ChatColor.GRAY + " is " + sItem.getRarity().getDisplay() + ChatColor.GRAY + ".");
            return;
        }
        Rarity prev = sItem.getRarity();
        switch (args[0]) {
            case "up": 
            case "upgrade": {
                sItem.upgradeRarity();
                this.send("Your " + sItem.getType().getDisplayName(sItem.getVariant()) + ChatColor.GRAY + " has been upgraded. (" + prev.getDisplay() + ChatColor.GRAY + " \u279c " + sItem.getRarity().getDisplay() + ChatColor.GRAY + ")");
                return;
            }
            case "down": 
            case "downgrade": {
                sItem.downgradeRarity();
                this.send("Your " + sItem.getType().getDisplayName(sItem.getVariant()) + ChatColor.GRAY + " has been downgraded. (" + prev.getDisplay() + ChatColor.GRAY + " \u279c " + sItem.getRarity().getDisplay() + ChatColor.GRAY + ")");
                return;
            }
        }
        Rarity chosen = Rarity.getRarity(args[0]);
        if (chosen == null) {
            throw new CommandFailException(ChatColor.RED + "That rarity does not exist, sucks to be you!");
        }
        sItem.setRarity(chosen);
        this.send("Your " + sItem.getType().getDisplayName(sItem.getVariant()) + ChatColor.GRAY + "'s rarity has been modified. (" + prev.getDisplay() + ChatColor.GRAY + " \u279c " + sItem.getRarity().getDisplay() + ChatColor.GRAY + ")");
    }
}

