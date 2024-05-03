/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Fireball
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.dimoon.abilities;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.dimoon.Dimoon;
import vn.giakhanhvn.skysim.dimoon.abilities.Ability;

public class FireRain
implements Ability {
    @Override
    public void activate(final Player player, final Dimoon dimoon) {
        new BukkitRunnable(){
            int counter = 0;

            public void run() {
                LivingEntity entity = dimoon.getEntity();
                Fireball fireball = (Fireball)entity.getWorld().spawn(entity.getEyeLocation().add(entity.getEyeLocation().getDirection().multiply(3.0)), Fireball.class);
                fireball.setMetadata("FBL", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
                fireball.setDirection(player.getLocation().getDirection().multiply(-1.0).normalize());
                ++this.counter;
                if (this.counter == 10) {
                    this.cancel();
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 20L);
    }

    @Override
    public int getCooldown() {
        return 60;
    }
}

