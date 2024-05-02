/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftEnderman
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.entity.dungeons.regularentity;

import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEnderman;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.end.BaseEnderman;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;

public class Fels
extends BaseEnderman {
    private boolean spawned = false;

    @Override
    public String getEntityName() {
        return "Fels";
    }

    @Override
    public double getEntityMaxHealth() {
        return 1.0E8;
    }

    @Override
    public double getDamageDealt() {
        return 1800000.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        final ArmorStand drop = (ArmorStand)entity.getWorld().spawn(entity.getLocation().clone().add(0.0, -1.4, 0.0), ArmorStand.class);
        drop.setVisible(false);
        drop.setGravity(false);
        drop.setCustomNameVisible(false);
        drop.getEquipment().setHelmet(SUtil.getSkullURLStack("asadas", "96c0b36d53fff69a49c7d6f3932f2b0fe948e032226d5e8045ec58408a36e951", 0, ""));
        entity.setMetadata("upsidedown", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("DungeonMobs", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 65);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000, 100));
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000, 100));
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (!Fels.this.spawned) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000, 100));
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000, 100));
                    ((CraftEnderman)entity).setTarget(null);
                    Collection ce = entity.getWorld().getNearbyEntities(entity.getLocation(), 0.5, 0.5, 0.5);
                    ce.removeIf(entity -> !(entity instanceof Player));
                    if (ce.size() > 0) {
                        drop.remove();
                        entity.removePotionEffect(PotionEffectType.INVISIBILITY);
                        entity.removePotionEffect(PotionEffectType.SLOW);
                        ArrayList ep = new ArrayList();
                        ce.addAll(ep);
                        Fels.this.spawned = true;
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 20L, 1L);
    }

    @Override
    public double getXPDropped() {
        return 320.0;
    }

    @Override
    public int mobLevel() {
        return 0;
    }
}

