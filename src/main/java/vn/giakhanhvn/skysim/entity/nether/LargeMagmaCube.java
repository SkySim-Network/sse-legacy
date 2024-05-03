/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftMagmaCube
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Fireball
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.event.entity.EntityTargetLivingEntityEvent
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.entity.nether;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftMagmaCube;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SlimeStatistics;

public class LargeMagmaCube
implements SlimeStatistics,
EntityFunction {
    @Override
    public String getEntityName() {
        return "Magma Cube";
    }

    @Override
    public double getEntityMaxHealth() {
        return 300.0;
    }

    @Override
    public double getDamageDealt() {
        return 150.0;
    }

    @Override
    public double getXPDropped() {
        return 4.0;
    }

    @Override
    public void onTarget(SEntity sEntity, EntityTargetLivingEntityEvent e) {
        final LivingEntity entity = (LivingEntity)e.getEntity();
        LivingEntity found = e.getTarget();
        new BukkitRunnable(){
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                CraftEntity target = ((CraftMagmaCube)entity).getHandle().getGoalTarget().getBukkitEntity();
                if (!found.equals(target)) {
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 3; ++i) {
                    new BukkitRunnable(){
                        public void run() {
                            if (entity.isDead()) {
                                this.cancel();
                                return;
                            }
                            Fireball fireball = (Fireball)entity.getWorld().spawn(entity.getEyeLocation().add(entity.getEyeLocation().getDirection().multiply(3.0)), Fireball.class);
                            fireball.setMetadata("magma", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)sEntity));
                            fireball.setDirection(target.getLocation().getDirection().multiply(-1.0).normalize());
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), (long)((i + 1) * 10));
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 60L, 100L);
    }

    @Override
    public int getSize() {
        return 6;
    }

    @Override
    public boolean split() {
        return false;
    }
}

