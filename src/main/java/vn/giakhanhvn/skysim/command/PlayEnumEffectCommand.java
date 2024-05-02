/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Effect
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;

@CommandParameters(description="Play a Bukkit enum sound.", usage="/playenumsound <sound>")
public class PlayEnumEffectCommand
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
        Effect effect = Effect.valueOf((String)args[0].toUpperCase());
        int count = 1;
        if (args.length == 2) {
            count = Integer.parseInt(args[1]);
        }
        for (int i = 0; i < count; ++i) {
            player.getWorld().playEffect(player.getLocation(), effect, (Object)effect.getData());
        }
        player.sendMessage(ChatColor.GRAY + "Played " + effect.name() + ".");
    }
}

