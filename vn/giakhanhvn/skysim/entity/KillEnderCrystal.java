/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 */
package vn.giakhanhvn.skysim.entity;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public final class KillEnderCrystal {
    public static void killEC(World world) {
        for (Entity entity : world.getEntities()) {
            if (entity.getType() != EntityType.ENDER_CRYSTAL) continue;
            entity.remove();
        }
    }
}

