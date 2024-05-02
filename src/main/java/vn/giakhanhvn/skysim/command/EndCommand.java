/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanBossManager;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="Spec test command.", aliases="fed")
public class EndCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        if (player.isOp()) {
            World world = player.getWorld();
            Bukkit.broadcastMessage((String)Sputnik.trans("&c[SYSTEM] &e" + player.getName() + " forcing the bossroom &c" + world.getName() + " &eto end."));
            SadanBossManager.endFloor(world);
        }
    }
}

