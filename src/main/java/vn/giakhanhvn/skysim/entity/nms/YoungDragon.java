/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.World
 *  org.bukkit.Bukkit
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 */
package vn.giakhanhvn.skysim.entity.nms;

import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import vn.giakhanhvn.skysim.entity.nms.Dragon;

public class YoungDragon
extends Dragon {
    public YoungDragon(World world) {
        super(world, 1.8, Dragon.DEFAULT_DAMAGE_DEGREE_RANGE, 300L);
    }

    public YoungDragon() {
        this((World)((CraftWorld)Bukkit.getWorlds().get(0)).getHandle());
    }

    @Override
    public String getEntityName() {
        return "Young Dragon";
    }

    @Override
    public double getEntityMaxHealth() {
        return 7500000.0;
    }

    @Override
    public double getDamageDealt() {
        return 1600.0;
    }
}

