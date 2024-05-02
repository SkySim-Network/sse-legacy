/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityCreeper
 *  net.minecraft.server.v1_8_R3.World
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.craftbukkit.v1_8_R3.CraftWorld
 *  org.bukkit.entity.Creeper
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.event.Event
 *  org.bukkit.event.entity.CreatureSpawnEvent$SpawnReason
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.entity.nms;

import java.lang.reflect.Field;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityCreeper;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.EntityStatistics;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.caverns.CreeperFunction;
import vn.giakhanhvn.skysim.entity.nms.SNMSEntity;
import vn.giakhanhvn.skysim.event.CreeperIgniteEvent;

public class SneakyCreeper
extends EntityCreeper
implements EntityStatistics,
SNMSEntity,
CreeperFunction {
    public SneakyCreeper(World world) {
        super(world);
    }

    public SneakyCreeper() {
        this((World)((CraftWorld)Bukkit.getWorlds().get(0)).getHandle());
    }

    @Override
    public String getEntityName() {
        return "Sneaky Creeper";
    }

    @Override
    public double getEntityMaxHealth() {
        return 120.0;
    }

    @Override
    public double getDamageDealt() {
        return 80.0;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    public void t_() {
        try {
            Field f = EntityCreeper.class.getDeclaredField("fuseTicks");
            f.setAccessible(true);
            int fuseTicks = (Integer)f.get(this);
            if (this.cm() > 0 && fuseTicks == 0) {
                CreeperIgniteEvent ignite = new CreeperIgniteEvent((Creeper)this.getBukkitEntity());
                SkySimEngine.getPlugin().getServer().getPluginManager().callEvent((Event)ignite);
                if (ignite.isCancelled()) {
                    return;
                }
            }
        }
        catch (IllegalAccessException | NoSuchFieldException reflectiveOperationException) {
            // empty catch block
        }
        super.t_();
    }

    @Override
    public void onCreeperIgnite(final CreeperIgniteEvent e, final SEntity sEntity) {
        sEntity.setVisible(true);
        new BukkitRunnable(){

            public void run() {
                if (e.getEntity().isDead()) {
                    return;
                }
                sEntity.setVisible(false);
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 35L);
    }

    @Override
    public LivingEntity spawn(Location location) {
        this.world = ((CraftWorld)location.getWorld()).getHandle();
        this.setPosition(location.getX(), location.getY(), location.getZ());
        this.world.addEntity((Entity)this, CreatureSpawnEvent.SpawnReason.CUSTOM);
        return (LivingEntity)this.getBukkitEntity();
    }

    @Override
    public double getXPDropped() {
        return 8.0;
    }
}

