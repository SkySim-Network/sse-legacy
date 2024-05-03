/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.EntityHuman
 *  org.bukkit.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import net.minecraft.server.v1_8_R3.EntityHuman;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.util.SputnikPlayer;

@CommandParameters(description="Modify your absorption amount.", permission="spt.player")
public class AbsorptionCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (args.length != 1) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        Player player = sender.getPlayer();
        EntityHuman human = ((CraftHumanEntity)player).getHandle();
        float f = Float.parseFloat(args[0]);
        SputnikPlayer.setCustomAbsorptionHP(player, f);
        this.send(ChatColor.GREEN + "You now have " + ChatColor.GOLD + f + ChatColor.GREEN + " absorption hearts.");
    }
}

