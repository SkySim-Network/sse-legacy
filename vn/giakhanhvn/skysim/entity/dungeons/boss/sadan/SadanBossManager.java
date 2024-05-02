/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.onarandombox.MultiverseCore.api.MVWorldManager
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.entity.dungeons.boss.sadan;

import com.onarandombox.MultiverseCore.api.MVWorldManager;
import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanFunction;
import vn.giakhanhvn.skysim.util.SLog;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SadanBossManager {
    public static void startFloor(ArrayList<Player> plist) {
        String worldname = "f6_" + SadanFunction.generateRandom();
        MVWorldManager worldManager = SkySimEngine.core.getMVWorldManager();
        worldManager.cloneWorld("f6", worldname);
        worldManager.loadWorld(worldname);
        World world = Bukkit.getWorld((String)worldname);
        for (Player tm : plist) {
            tm.teleport(new Location(world, 213.0, 71.0, 221.0, 0.0f, 0.0f));
        }
        Sputnik.RunThisSession.put(Bukkit.getServer(), Sputnik.rf_() + 1);
        SUtil.delay(() -> SadanBossManager.r(plist, world), 1L);
        SUtil.delay(() -> Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("rg flag __global__ -w " + world.getName() + " build deny")), 1L);
        SUtil.delay(() -> Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)("rg flag __global__ -w " + world.getName() + " pvp deny")), 2L);
        SUtil.delay(() -> new SEntity(new Location(world, 183.0, 100.0, 251.0), SEntityType.SADAN, new Object[0]), 1L);
    }

    public static void r(ArrayList<Player> plist, World world) {
        for (Player tm : plist) {
            tm.teleport(new Location(world, 191.5, 69.0, 199.5, 0.0f, 0.0f));
        }
    }

    public static void endFloor(World w) {
        if (w.getName().toLowerCase().contains("f6") && !w.getName().toLowerCase().equals("f6")) {
            for (Entity e : w.getEntities()) {
                if (e instanceof Player) continue;
                e.remove();
            }
            SkySimEngine.core.deleteWorld(w.getName());
            SLog.severe("[DUNGEON BOSSROOM] Deleted " + w.getName() + " and cleaned the memory !");
        }
    }
}

