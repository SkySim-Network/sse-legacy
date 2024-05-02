/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.entity.skeleton;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.EntityFunction;
import vn.giakhanhvn.skysim.entity.EntityStatistics;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;

public class HighLevelSkeleton
implements EntityStatistics,
EntityFunction {
    @Override
    public String getEntityName() {
        return "Skeleton";
    }

    @Override
    public double getEntityMaxHealth() {
        return 200.0;
    }

    @Override
    public double getDamageDealt() {
        return 47.0;
    }

    @Override
    public double getXPDropped() {
        return 6.0;
    }

    @Override
    public void onDeath(SEntity sEntity, Entity killed, Entity damager) {
        final Item item = sEntity.getEntity().getWorld().dropItem(sEntity.getEntity().getLocation(), new ItemStack(Material.BONE, 2));
        new BukkitRunnable(){

            public void run() {
                if (item.isDead()) {
                    return;
                }
                Location spawn = item.getLocation().clone().add(0.0, 1.0, 0.0);
                for (int i = 0; i < 5; ++i) {
                    item.getWorld().spigot().playEffect(spawn, Effect.PARTICLE_SMOKE, 0, 1, 0.0f, 0.0f, 0.0f, 0.0f, 1, 20);
                }
                new SEntity(spawn, SEntityType.HIGH_LEVEL_SKELETON, new Object[0]);
                item.remove();
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 100L);
    }
}

