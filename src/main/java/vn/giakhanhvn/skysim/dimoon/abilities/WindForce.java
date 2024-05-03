/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.dimoon.abilities;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.dimoon.Dimoon;
import vn.giakhanhvn.skysim.dimoon.abilities.Ability;

public class WindForce
implements Ability {
    @Override
    public void activate(Player player, Dimoon dimoon) {
        LivingEntity entity = dimoon.getEntity();
        player.getWorld().playSound(dimoon.getEntity().getLocation(), Sound.WITHER_SHOOT, 5.0f, 1.0f);
        World world = entity.getWorld();
        Vector[] velocity = new Vector[]{player.getLocation().toVector().subtract(entity.getLocation().toVector()).normalize()};
        new BukkitRunnable(){
            private Location particleLocation;
            double multiplier;

            public void run() {
                this.particleLocation.add(velocity[0]);
                velocity[0] = player.getLocation().toVector().subtract(this.particleLocation.toVector()).normalize().multiply(this.multiplier);
                if (velocity[0].length() < 1.0 || this.multiplier == 1.0) {
                    this.cancel();
                    return;
                }
                this.multiplier -= 0.05;
                world.spigot().playEffect(this.particleLocation, Effect.FIREWORKS_SPARK, 24, 1, 0.0f, 0.0f, 0.0f, 1.0f, 0, 64);
                world.spigot().playEffect(this.particleLocation, Effect.HAPPY_VILLAGER, 24, 1, 0.0f, 0.0f, 0.0f, 1.0f, 0, 64);
                if (SkySimEngine.getPlugin().dimoon == null) {
                    this.cancel();
                    return;
                }
                if (this.particleLocation.distance(player.getEyeLocation()) < 1.5 || this.particleLocation.distance(player.getLocation()) < 1.5) {
                    player.getWorld().playEffect(this.particleLocation, Effect.EXPLOSION_HUGE, 0);
                    player.getWorld().playSound(this.particleLocation, Sound.EXPLODE, 1.0f, 1.0f);
                    player.setVelocity(new Vector(Math.random() - Math.random() * 2.0, Math.random() - Math.random() * 2.0, Math.random() - Math.random() * 2.0));
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
    }

    @Override
    public int getCooldown() {
        return 120;
    }
}

