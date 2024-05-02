/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockState
 *  org.bukkit.plugin.Plugin
 */
package vn.giakhanhvn.skysim.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.plugin.Plugin;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;

public final class StaticDragonManager {
    public static boolean ACTIVE = false;
    public static Map<UUID, List<Location>> EYES = new HashMap<UUID, List<Location>>();
    public static SEntity DRAGON = null;

    public static void endFight() {
        if (DRAGON == null) {
            return;
        }
        ACTIVE = false;
        for (List<Location> locations : EYES.values()) {
            for (Location location : locations) {
                Block b = location.getBlock();
                BlockState s = b.getState();
                s.setRawData((byte)0);
                s.update();
                b.removeMetadata("placer", (Plugin)SkySimEngine.getPlugin());
            }
        }
        EYES.clear();
        DRAGON = null;
    }
}

