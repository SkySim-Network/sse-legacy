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
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="Adds an enchantment from Spec to the specified item.", aliases="meb", permission="spt.item")
public class MembersEnchantCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (args.length != 2) {
            throw new CommandArgumentException();
        }
        if (args.length == 0) {
            this.send(ChatColor.RED + "Wrong usage of the command! Usage /meb <enchant type> <level>!");
            return;
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        Player player = sender.getPlayer();
        if (Sputnik.isFullInv(player)) {
            this.send(ChatColor.RED + "Your inventory ran out of spaces! Clean it up!");
            return;
        }
        EnchantmentType type = EnchantmentType.getByNamespace(args[0]);
        if (type == null) {
            this.send(ChatColor.RED + "Invalid enchantment type! It might not get added yet.");
            return;
        }
        int i = Integer.parseInt(args[1]);
        if (i <= 0) {
            this.send(ChatColor.RED + "Are you serious? If you want to remove enchantments, use /rench");
            return;
        }
        if (type == EnchantmentType.TURBO_GEM) {
            this.send(ChatColor.RED + "This enchantment is not free! You need to DIMOON_SLAY_AND_CRAFT_MESSAGE_ACTION for this!");
            return;
        }
        if (type == EnchantmentType.LUCKINESS) {
            this.send(ChatColor.RED + "This enchantment is not free! Purchase it in the Community Shop.");
            return;
        }
        if (type == EnchantmentType.VICIOUS) {
            this.send(ChatColor.RED + "This enchantment is not free! You need to slay Revenant Horror V for it!");
            return;
        }
        if (type == EnchantmentType.CHIMERA) {
            this.send(ChatColor.RED + "This enchantment is not free! You need to slay Sven Packmaster IV for it!");
            return;
        }
        if (type == EnchantmentType.LEGION || type == EnchantmentType.FATAL_TEMPO) {
            this.send(ChatColor.RED + "This enchantment is not free! You need to slay Voidgloom Seraph IV for it!");
            return;
        }
        if (i > 5 && type == EnchantmentType.FIRST_STRIKE) {
            this.send(ChatColor.RED + "Too high enchantment level! This enchantment is capped at level 5 in case of /meb command! You need to slay Tarantula Broodfather IV to get a LVL VII version of this enchanment!");
            return;
        }
        if (type == EnchantmentType.EFFICIENCY) {
            this.send(ChatColor.RED + "This enchantment is banned due to rewamping in mining system!");
            return;
        }
        if (i > 2 && type == EnchantmentType.KNOCKBACK) {
            this.send(ChatColor.RED + "Too high enchantment level! This enchantment is capped at level 2!");
            return;
        }
        if (i > 1500) {
            this.send(ChatColor.RED + "Too high enchantment level! This enchantment is capped at 1,500.");
            return;
        }
        if (i > 320 && type == EnchantmentType.POWER) {
            this.send(ChatColor.RED + "Too high enchantment level! This enchantment is capped at level 320.");
            return;
        }
        if (i > 400 && type == EnchantmentType.SHARPNESS) {
            this.send(ChatColor.RED + "Too high enchantment level! This enchantment is capped at level 400.");
            return;
        }
        if (i > 1 && (type == EnchantmentType.ONE_FOR_ALL || type == EnchantmentType.TELEKINESIS || type == EnchantmentType.AQUA_INFINITY)) {
            this.send(ChatColor.RED + "Too high enchantment level! This enchantment is capped at level 1.");
            return;
        }
        if (i > 30 && (type == EnchantmentType.CRITICAL || type == EnchantmentType.EXECUTE || type == EnchantmentType.AIMING)) {
            this.send(ChatColor.RED + "Too high enchantment level! This enchantment is capped at level 30.");
            return;
        }
        if (i > 10 && (type == EnchantmentType.ENDER_SLAYER || type == EnchantmentType.SMITE || type == EnchantmentType.BANE_OF_ARTHROPODS || type == EnchantmentType.DRAGON_HUNTER)) {
            this.send(ChatColor.RED + "Too high enchantment level! This enchantment is capped at level 10.");
            return;
        }
        if (i > 8 && type == EnchantmentType.ULTIMATE_WISE) {
            this.send(ChatColor.RED + "Too high enchantment level! This enchantment is capped at level 8.");
            return;
        }
        if (i > 5 && (type == EnchantmentType.SOUL_EATER || type == EnchantmentType.LIFE_STEAL || type == EnchantmentType.AIMING || type == EnchantmentType.VAMPIRISM)) {
            this.send(ChatColor.RED + "Too high enchantment level! This enchantment is capped at level 5.");
            return;
        }
        SItem eBook = SItem.of(SMaterial.ENCHANTED_BOOK);
        eBook.addEnchantment(type, i);
        player.getInventory().addItem(new ItemStack[]{eBook.getStack()});
        this.send(Sputnik.trans("&aYou have been given the &dEnchanted Book &awith &9" + type.getName() + " &9" + SUtil.toRomanNumeral(i) + " &aon it. Use an Anvil to apply it to your items."));
    }

    public void stop() {
        this.send("");
    }
}

