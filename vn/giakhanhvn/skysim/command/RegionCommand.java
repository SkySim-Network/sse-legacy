/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.CommandSender
 */
package vn.giakhanhvn.skysim.command;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import vn.giakhanhvn.skysim.command.CommandArgumentException;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.region.Region;
import vn.giakhanhvn.skysim.region.RegionGenerator;
import vn.giakhanhvn.skysim.region.RegionType;

@CommandParameters(description="Manage world regions.", usage="/<command> [create <name> <type> | update <name> [type] | delete <name>]", aliases="reg", permission="spt.region")
public class RegionCommand
extends SCommand {
    public static Map<CommandSender, RegionGenerator> REGION_GENERATION_MAP = new HashMap<CommandSender, RegionGenerator>();

    @Override
    public void run(CommandSource sender, String[] args) {
        Region region;
        String name;
        if (args.length == 3) {
            name = args[1];
            RegionType type = RegionType.valueOf(args[2].toUpperCase());
            switch (args[0].toLowerCase()) {
                case "create": {
                    if (name.length() > 100) {
                        throw new CommandFailException("Name too long!");
                    }
                    if (RegionCommand.plugin.regionData.exists(name)) {
                        throw new CommandFailException("There is already a region named that!");
                    }
                    REGION_GENERATION_MAP.put(sender.getSender(), new RegionGenerator("create", name, type));
                    this.send("Created a region named \"" + name + "\"");
                    this.send(ChatColor.DARK_AQUA + "Click the first corner of your region.");
                    return;
                }
            }
        }
        if (args.length == 2 && args[0].equalsIgnoreCase("delete")) {
            name = args[1];
            region = Region.get(name);
            if (region == null) {
                throw new CommandFailException("There is no region named that!");
            }
            region.delete();
            this.send("Deleted region \"" + name + "\" successfully.");
            return;
        }
        if (args.length == 2 || args.length == 3) {
            name = args[1];
            region = Region.get(name);
            if (region == null) {
                throw new CommandFailException("There is no region named that!");
            }
            RegionType type = region.getType();
            if (args.length == 3) {
                type = RegionType.valueOf(args[2].toUpperCase());
            }
            if (!args[0].equalsIgnoreCase("update")) {
                throw new CommandArgumentException();
            }
            REGION_GENERATION_MAP.put(sender.getSender(), new RegionGenerator("update", name, type));
            this.send("Updating \"" + name + "\"");
            this.send(ChatColor.DARK_AQUA + "Click the first corner of your region.");
            return;
        }
        if (args.length != 0) {
            throw new CommandArgumentException();
        }
        StringBuilder result = new StringBuilder().append("Regions");
        for (Region region2 : Region.getRegions()) {
            result.append("\n").append(" - ").append(region2.getName()).append(" (").append(region2.getType().name().toLowerCase()).append(")");
        }
        this.send(result.toString());
    }
}

