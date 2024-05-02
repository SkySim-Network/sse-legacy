/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.entity.EntitySpawner;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.util.SUtil;

@CommandParameters(description="Manage entity spawners.", usage="/<command> [create <type> | delete <index>]", aliases="entityspawner,es,spawner,spawners", permission="spt.entity")
public class EntitySpawnersCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        Player player = sender.getPlayer();
        if (args.length == 0) {
            StringBuilder builder = new StringBuilder("Spawners:");
            List<EntitySpawner> spawners = EntitySpawner.getSpawners();
            for (int i = 0; i < spawners.size(); ++i) {
                EntitySpawner spawner = spawners.get(i);
                builder.append("\n ").append(i + 1).append(": ").append(SUtil.prettify(spawner.getLocation())).append(" (").append(spawner.getType().name()).append(")");
            }
            this.send(builder.toString());
            return;
        }
        if (args.length != 2) {
            throw new CommandArgumentException();
        }
        switch (args[0].toLowerCase()) {
            case "create": {
                SEntityType type = SEntityType.getEntityType(args[1]);
                if (type == null) {
                    throw new CommandFailException(ChatColor.RED + "That is not a valid entity type!");
                }
                EntitySpawner spawner = new EntitySpawner(type, player.getLocation());
                this.send(ChatColor.GREEN + "New entity spawner has been created at " + ChatColor.YELLOW + SUtil.prettify(spawner.getLocation()) + " with the type " + spawner.getType().getGenericInstance());
                break;
            }
            case "delete": {
                int index = Integer.parseInt(args[1]) - 1;
                List<EntitySpawner> spawners = EntitySpawner.getSpawners();
                if (index < 0 || index > spawners.size() - 1) {
                    throw new CommandFailException(ChatColor.RED + "There is no spawner at that location!");
                }
                spawners.remove(index).delete();
                this.send(ChatColor.GREEN + "Entity spawner deleted.");
                break;
            }
        }
    }
}

