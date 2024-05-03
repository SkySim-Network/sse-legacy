/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.minecraft.server.v1_8_R3.Entity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftSkeleton
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Skeleton$SkeletonType
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityTargetLivingEntityEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.entity;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSkeleton;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import vn.giakhanhvn.skysim.entity.EntityStatistics;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.util.Sputnik;

public interface EntityFunction {
    public static final Map<Entity, ArrayList<UUID>> FIRST_STRIKE_MAP = new HashMap<Entity, ArrayList<UUID>>();

    default public void onDeath(SEntity sEntity, Entity killed, Entity damager) {
    }

    default public void onDamage(SEntity sEntity, Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
    }

    default public List<EntityDrop> drops() {
        return new ArrayList<EntityDrop>();
    }

    default public boolean tick(LivingEntity entity) {
        return false;
    }

    default public void onSpawnNameTag(final LivingEntity entity, SEntity sEntity, SEntityType specType, Object ... params) {
        final ArmorStand hologram1 = (ArmorStand)entity.getWorld().spawn(entity.getLocation().add(0.0, 0.0, 0.0), ArmorStand.class);
        hologram1.setVisible(false);
        hologram1.setGravity(false);
        hologram1.setSmall(true);
        hologram1.setMarker(true);
        hologram1.setBasePlate(false);
        hologram1.setCustomNameVisible(false);
        entity.setPassenger((Entity)hologram1);
        if (!entity.hasMetadata("LD")) {
            hologram1.remove();
        }
        FIRST_STRIKE_MAP.put((Entity)entity, new ArrayList());
        if (entity.hasMetadata("notDisplay")) {
            return;
        }
        if (entity.getType() == EntityType.ARMOR_STAND) {
            return;
        }
        if (entity.getType() == EntityType.ENDER_DRAGON) {
            return;
        }
        if (entity.getType() == EntityType.ENDER_CRYSTAL) {
            return;
        }
        net.minecraft.server.v1_8_R3.Entity e = ((CraftEntity)entity).getHandle();
        double height_ = e.getBoundingBox().e - e.getBoundingBox().b;
        Object instance = specType.instance(params);
        final EntityStatistics statistics = (EntityStatistics)instance;
        if (entity.getType() == EntityType.SKELETON || entity.hasMetadata("SKEL")) {
            height_ += 0.2;
        }
        if (entity.getType() == EntityType.SKELETON && ((CraftSkeleton)entity).getSkeletonType() == Skeleton.SkeletonType.WITHER) {
            height_ -= 0.2;
        }
        if (entity.hasMetadata("LD")) {
            height_ -= 0.144;
        }
        if (entity.hasMetadata("highername")) {
            height_ += 10.5;
        }
        if (entity.hasMetadata("NNP")) {
            height_ += 0.8;
        }
        if (entity.hasMetadata("NNPS")) {
            height_ += 0.8;
        }
        final double height = height_;
        final ArmorStand hologram = (ArmorStand)entity.getWorld().spawn(entity.getLocation().add(0.0, height, 0.0), ArmorStand.class);
        hologram.setVisible(false);
        hologram.setGravity(false);
        hologram.setSmall(false);
        hologram.setMarker(true);
        hologram.setBasePlate(false);
        hologram.setCustomNameVisible(true);
        boolean hideLVLA = false;
        if (entity.hasMetadata("WATCHER_E")) {
            hideLVLA = true;
        }
        final boolean hideLVL = hideLVLA;
        new BukkitRunnable(){

            public void run() {
                if (entity.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                    hologram.setCustomNameVisible(false);
                } else {
                    hologram.setCustomNameVisible(true);
                }
                if (!hologram.getLocation().getChunk().isLoaded()) {
                    hologram.remove();
                    this.cancel();
                    return;
                }
                if (entity.isDead()) {
                    if (entity.hasMetadata("NNP")) {
                        hologram.remove();
                    }
                    if (entity.hasMetadata("h_sadan")) {
                        hologram.remove();
                    }
                    FIRST_STRIKE_MAP.remove(entity);
                    hologram.setCustomName(Sputnik.trans(Sputnik.entityNameTag(entity, Sputnik.buildcustomString(statistics.getEntityName(), statistics.mobLevel(), hideLVL))));
                    new BukkitRunnable(){

                        public void run() {
                            hologram.remove();
                            hologram1.remove();
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 20L);
                }
                if (hologram.isDead()) {
                    this.cancel();
                    return;
                }
                if (entity.hasMetadata("RMHN")) {
                    hologram1.remove();
                } else {
                    entity.setPassenger((Entity)hologram1);
                }
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                if (!entity.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                    if (entity.hasMetadata("Boss")) {
                        hologram.setCustomName(Sputnik.trans("&e\ufd3e " + Sputnik.entityNameTag(entity, Sputnik.buildcustomString(statistics.getEntityName(), statistics.mobLevel(), true)) + " &e\ufd3f"));
                    } else if (entity.hasMetadata("NNP")) {
                        hologram.setCustomName(statistics.getEntityName());
                    } else {
                        hologram.setCustomName(Sputnik.trans(Sputnik.entityNameTag(entity, Sputnik.buildcustomString(statistics.getEntityName(), statistics.mobLevel(), hideLVL))));
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 0L);
    }

    default public void onSpawn(LivingEntity entity, SEntity sEntity) {
    }

    default public void onAttack(EntityDamageByEntityEvent e) {
    }

    default public void onTarget(SEntity sEntity, EntityTargetLivingEntityEvent e) {
    }

    default public void onSpawnNameTag(LivingEntity entity, SEntityType specType, Object ... params) {
        this.onSpawnNameTag(entity, SEntity.findSEntity((Entity)entity), specType, params);
    }

    default public void onSpawn(LivingEntity entity) {
        this.onSpawn(entity, SEntity.findSEntity((Entity)entity));
    }
}

