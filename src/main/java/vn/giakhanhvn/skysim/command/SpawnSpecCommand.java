/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="Spawn a mob from Spec.", aliases="scm", permission="spt.spawn")
public class SpawnSpecCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        SEntity entity;
        if (args.length == 0) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        Player player = sender.getPlayer();
        if (SEntityType.getEntityType(args[0]) == null) {
            player.sendMessage(Sputnik.trans("&cMob not found or it get banned!"));
            return;
        }
        SEntityType type = SEntityType.getEntityType(args[0]);
        if (type.toString().toLowerCase().contains("banned") || type.toString().toLowerCase().contains("test")) {
            player.sendMessage(Sputnik.trans("&cMob not found or it get banned!"));
            return;
        }
        switch (type) {
            case REVENANT_HORROR: 
            case SVEN_PACKMASTER: 
            case TARANTULA_BROODFATHER: 
            case VOIDGLOOM_SERAPH: 
            case ATONED_HORROR: 
            case CRIMSON_SATHANAS: {
                if (args.length != 2) {
                    throw new CommandArgumentException();
                }
                int tier = Integer.parseInt(args[1]);
                entity = new SEntity((Entity)player, type, tier, player.getUniqueId());
                break;
            }
            default: {
                entity = new SEntity((Entity)player, type, new Object[0]);
            }
        }
        this.send(ChatColor.GREEN + "Success! You have spawned a(n) " + ChatColor.GOLD + entity.getStatistics().getEntityName());
    }
}

