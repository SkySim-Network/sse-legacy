/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.minecraft.server.v1_8_R3.Entity
 *  org.bukkit.Location
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.entity.zombie;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.extra.beam.Beam;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class TheWatcher
extends BaseZombie {
    public static final Map<Entity, String> DIALOUGE_BOSS = new HashMap<Entity, String>();

    @Override
    public String getEntityName() {
        return "";
    }

    @Override
    public double getEntityMaxHealth() {
        return 250.0;
    }

    @Override
    public double getDamageDealt() {
        return 200.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        double height_;
        entity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 10000000, 1));
        net.minecraft.server.v1_8_R3.Entity e = ((CraftEntity)entity).getHandle();
        final double height = height_ = e.getBoundingBox().e - e.getBoundingBox().b;
        final ArmorStand hologram = (ArmorStand)entity.getWorld().spawn(entity.getLocation().add(0.0, height, 0.0), ArmorStand.class);
        hologram.setVisible(false);
        hologram.setGravity(false);
        hologram.setSmall(false);
        hologram.setMarker(true);
        hologram.setBasePlate(false);
        hologram.setCustomNameVisible(true);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    new BukkitRunnable(){

                        public void run() {
                            hologram.remove();
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 20L);
                }
                if (hologram.isDead()) {
                    this.cancel();
                    return;
                }
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.teleport(entity.getLocation().clone().add(0.0, height, 0.0));
                hologram.setCustomName(Sputnik.trans("&e\ufd3e &c&lThe Watcher &e\ufd3f"));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 0L);
        final ArmorStand hologram_d = (ArmorStand)entity.getWorld().spawn(entity.getLocation().add(0.0, height + 0.3, 0.0), ArmorStand.class);
        hologram_d.setVisible(false);
        hologram_d.setGravity(false);
        hologram_d.setSmall(false);
        hologram_d.setMarker(true);
        hologram_d.setBasePlate(false);
        hologram_d.setCustomNameVisible(true);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    new BukkitRunnable(){

                        public void run() {
                            hologram_d.remove();
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 20L);
                }
                if (hologram.isDead()) {
                    this.cancel();
                    return;
                }
                if (DIALOUGE_BOSS.containsKey(entity)) {
                    hologram_d.setCustomNameVisible(true);
                    hologram_d.setCustomName(Sputnik.trans(DIALOUGE_BOSS.get(entity)));
                } else {
                    hologram_d.setCustomNameVisible(false);
                    hologram_d.setCustomName("");
                }
                hologram_d.teleport(entity.getLocation().clone().add(0.0, height + 0.3, 0.0));
                hologram_d.teleport(entity.getLocation().clone().add(0.0, height + 0.3, 0.0));
                hologram_d.teleport(entity.getLocation().clone().add(0.0, height + 0.3, 0.0));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        EntityManager.noAI((Entity)entity);
        entity.setMetadata("NoAffect", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("notDisplay", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        EntityManager.shutTheFuckUp((Entity)entity);
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 100);
        final ArmorStand stand = (ArmorStand)entity.getWorld().spawn(entity.getLocation(), ArmorStand.class);
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setMarker(true);
        stand.getEquipment().setHelmet(SItem.of(SMaterial.JERRY_HEAD).getStack());
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    new BukkitRunnable(){

                        public void run() {
                            stand.remove();
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 0L);
                }
                for (Entity target : stand.getNearbyEntities(20.0, 20.0, 20.0)) {
                    if (!(target instanceof Player)) continue;
                    entity.teleport(entity.getLocation().setDirection(target.getLocation().toVector().subtract(target.getLocation().toVector())));
                }
                if (hologram.isDead()) {
                    this.cancel();
                    return;
                }
                stand.teleport(entity.getLocation().clone().add(0.0, 0.0, 0.0));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public boolean hasNameTag() {
        return false;
    }

    @Override
    public boolean isVillager() {
        return false;
    }

    @Override
    public void onDamage(SEntity sEntity, Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
        if (damager instanceof Player) {
            Location loc = e.getEntity().getLocation().add(0.0, 1.2, 0.0);
            Beam beam = new Beam(loc, damager.getLocation().add(0.0, 1.0, 0.0));
            beam.start();
            User.getUser(damager.getUniqueId()).damage(SUtil.random(600, 1000), EntityDamageEvent.DamageCause.ENTITY_ATTACK, e.getEntity());
            DIALOUGE_BOSS.put(e.getEntity(), "&f&lI'm not your goal.");
            ((LivingEntity)damager).damage(0.1, e.getEntity());
            SUtil.delay(() -> beam.stop(), 30L);
            SUtil.delay(() -> DIALOUGE_BOSS.remove(e.getEntity()), 40L);
        }
    }

    @Override
    public double getXPDropped() {
        return 20.0;
    }
}

