/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.command.CommandSender
 */
package vn.giakhanhvn.skysim.command;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.region.RegionGenerator;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="Manage world regions.", usage="", aliases="hub")
public class HubCommand
extends SCommand {
    public static Map<CommandSender, RegionGenerator> REGION_GENERATION_MAP = new HashMap<CommandSender, RegionGenerator>();

    @Override
    public void run(CommandSource sender, String[] args) {
        this.send(Sputnik.trans("&7Sending you to the Hub..."));
        Location l = new Location(Bukkit.getWorld((String)"world"), -2.5, 70.0, -68.5, 180.0f, 0.0f);
        if (sender.getPlayer() != null) {
            sender.getPlayer().teleport(l);
        }
    }
}

