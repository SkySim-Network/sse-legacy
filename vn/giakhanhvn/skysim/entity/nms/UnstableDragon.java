/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.World
 *  org.apache.commons.lang3.Range
 *  org.bukkit.Bukkit
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 */
package vn.giakhanhvn.skysim.entity.nms;

import net.minecraft.server.v1_8_R3.World;
import org.apache.commons.lang3.Range;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import vn.giakhanhvn.skysim.entity.nms.Dragon;

public class UnstableDragon
extends Dragon {
    public UnstableDragon(World world) {
        super(world, 1.4, (Range<Double>)Range.between((Comparable)Double.valueOf(0.6), (Comparable)Double.valueOf(0.9)), 300L);
    }

    public UnstableDragon() {
        this((World)((CraftWorld)Bukkit.getWorlds().get(0)).getHandle());
    }

    @Override
    public String getEntityName() {
        return "Unstable Dragon";
    }

    @Override
    public double getEntityMaxHealth() {
        return 9000000.0;
    }

    @Override
    public double getDamageDealt() {
        return 1400.0;
    }
}

