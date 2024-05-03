/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Effect
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.FallingBlock
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityChangeBlockEvent
 *  org.bukkit.event.entity.EntityDeathEvent
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.dimoon.listeners;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.util.SUtil;

public class EntityListener
implements Listener {
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        FallingBlock fallingBlock;
        if (event.getEntity() instanceof FallingBlock && (fallingBlock = (FallingBlock)event.getEntity()).getWorld().getName().equalsIgnoreCase("arena")) {
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            fallingBlock.getWorld().playSound(fallingBlock.getLocation(), Sound.DIG_STONE, 1.0f, 1.0f);
            for (int i = 0; i < 20; ++i) {
                fallingBlock.getWorld().spigot().playEffect(fallingBlock.getLocation().clone().add(0.0, 0.0, 0.0), Effect.EXPLOSION, 0, 1, (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(-0.5, 0.5), 0.0f, 1, 100);
            }
            event.getBlock().getLocation().getWorld().getNearbyEntities(event.getBlock().getLocation(), 0.5, 0.5, 0.5).forEach(entity -> {
                if (entity instanceof LivingEntity) {
                    entity.setVelocity(new Vector(Math.random() - Math.random() * 2.0, Math.random() - Math.random() * 2.0, Math.random() - Math.random() * 2.0));
                }
            });
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().hasMetadata("Dimoon")) {
            event.getDrops().clear();
        }
    }
}

