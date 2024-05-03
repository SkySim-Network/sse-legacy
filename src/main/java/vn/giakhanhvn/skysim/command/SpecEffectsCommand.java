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
import vn.giakhanhvn.skysim.potion.ActivePotionEffect;
import vn.giakhanhvn.skysim.util.SUtil;

@CommandParameters(description="Get your current active potion effects.", aliases="effect", permission="spt.item")
public class SpecEffectsCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (args.length > 1) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("clear")) {
            sender.getUser().clearPotionEffects();
            this.send("Cleared active effects.");
            return;
        }
        this.send("Current active effects:");
        for (ActivePotionEffect effect : sender.getUser().getEffects()) {
            this.send(" - " + effect.getEffect().getType().getName() + " " + SUtil.toRomanNumeral(effect.getEffect().getLevel()) + ChatColor.GRAY + " (" + effect.getRemainingDisplay() + ")");
        }
    }
}

