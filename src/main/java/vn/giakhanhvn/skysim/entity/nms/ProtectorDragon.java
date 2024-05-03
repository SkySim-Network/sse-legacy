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

public class ProtectorDragon
extends Dragon {
    public ProtectorDragon(World world) {
        super(world, 1.4, Dragon.DEFAULT_DAMAGE_DEGREE_RANGE, 300L);
    }

    public ProtectorDragon() {
        this((World)((CraftWorld)Bukkit.getWorlds().get(0)).getHandle());
    }

    @Override
    public String getEntityName() {
        return "Protector Dragon";
    }

    @Override
    public double getEntityMaxHealth() {
        return 9000000.0;
    }

    @Override
    public double getDamageDealt() {
        return 1300.0;
    }
}

