/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  me.libraryaddict.disguise.disguisetypes.PlayerDisguise
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  org.bukkit.Bukkit
 *  org.bukkit.Effect
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
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
 *  org.bukkit.util.EulerAngle
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.dungeons.watcher;

import com.google.common.util.concurrent.AtomicDouble;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.EnumWatcherType;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.HeadsOnWall;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class WatcherLivid
extends BaseZombie {
    @Override
    public String getEntityName() {
        return Sputnik.trans("&4&lMaster Livid");
    }

    @Override
    public double getEntityMaxHealth() {
        return 9.5E8;
    }

    @Override
    public double getDamageDealt() {
        return 6000000.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        HeadsOnWall h = new HeadsOnWall(EnumWatcherType.LIVID);
        final PlayerDisguise p = Sputnik.applyPacketNPC((Entity)entity, h.value, h.signature, true);
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 87);
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("LD", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("WATCHER_E", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        p.setReplaceSounds(true);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (((CraftZombie)entity).getTarget() != null) {
                    entity.getEquipment().setItemInHand(SItem.of(SMaterial.LIVID_DAGGER).getStack());
                    p.getWatcher().setRightClicking(true);
                    WatcherLivid.this.throwThickAssBone((Entity)entity);
                    SUtil.delay(() -> p.getWatcher().setRightClicking(false), 10L);
                    SUtil.delay(() -> entity.getEquipment().setItemInHand(SItem.of(SMaterial.SHADOW_FURY).getStack()), 30L);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 150L, 150L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (int j = 0; j < 5; ++j) {
                    entity.getWorld().spigot().playEffect(entity.getLocation().clone().add(0.0, 0.1, 0.0), Effect.WITCH_MAGIC, 0, 1, (float)SUtil.random(-1, 1), (float)SUtil.random(-1, 2), (float)SUtil.random(-1, 1), 0.0f, 1, 20);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 10L, 10L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (((CraftZombie)entity).getTarget() != null) {
                    Location lc = ((CraftZombie)entity).getTarget().getLocation();
                    entity.getEquipment().setItemInHand(SItem.of(SMaterial.SHADOW_FURY).getStack());
                    p.getWatcher().setRightClicking(true);
                    SUtil.delay(() -> p.getWatcher().setRightClicking(false), 10L);
                    ((CraftZombie)entity).getTarget().getWorld().playSound(((CraftZombie)entity).getTarget().getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                    ((CraftZombie)entity).getHandle().setPositionRotation(lc.getX(), lc.getY(), lc.getZ(), lc.getYaw(), lc.getPitch());
                    for (int j = 0; j < 20; ++j) {
                        entity.getWorld().spigot().playEffect(entity.getLocation().clone().add(0.0, 1.75, 0.0), Effect.LARGE_SMOKE, 0, 1, (float)SUtil.random(-1, 1), (float)SUtil.random(-1, 2), (float)SUtil.random(-1, 1), 0.0f, 1, 20);
                        entity.getWorld().spigot().playEffect(entity.getLocation().clone().add(0.0, 1.75, 0.0), Effect.WITCH_MAGIC, 0, 1, (float)SUtil.random(-1, 1), (float)SUtil.random(-1, 2), (float)SUtil.random(-1, 1), 0.0f, 1, 20);
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 220L, 220L);
        new BukkitRunnable(){

            public void run() {
                EntityLiving nms = ((CraftLivingEntity)entity).getHandle();
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (Entity entities : entity.getWorld().getNearbyEntities(entity.getLocation().add(entity.getLocation().getDirection().multiply(1.0)), 1.5, 1.5, 1.5)) {
                    Player target;
                    if (!(entities instanceof Player) || (target = (Player)entities).getGameMode() == GameMode.CREATIVE || target.getGameMode() == GameMode.SPECTATOR || target.hasMetadata("NPC") || target.getNoDamageTicks() == 7 || SUtil.random(0, 10) > 8) continue;
                    entity.teleport(entity.getLocation().setDirection(target.getLocation().subtract(entities.getLocation()).toVector()));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer)players).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((net.minecraft.server.v1_8_R3.Entity)((CraftLivingEntity)entity).getHandle(), 0));
                    }
                    nms.r((net.minecraft.server.v1_8_R3.Entity)((CraftPlayer)target).getHandle());
                    break;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 3L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SItem.of(SMaterial.DIAMOND_SWORD).getStack(), null, null, null, null);
    }

    @Override
    public void onDamage(SEntity sEntity, Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
        LivingEntity en = sEntity.getEntity();
        Vector v = new Vector(0, 0, 0);
        SUtil.delay(() -> WatcherLivid.lambda$onDamage$0((Entity)en, v), 1L);
    }

    @Override
    public void onAttack(EntityDamageByEntityEvent e) {
        if (SUtil.random(0, 5) == 1) {
            ((LivingEntity)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 5));
        }
    }

    @Override
    public void onDeath(SEntity sEntity, Entity killed, Entity damager) {
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
    public double getXPDropped() {
        return 304.0;
    }

    @Override
    public double getMovementSpeed() {
        return 0.4;
    }

    @Override
    public int mobLevel() {
        return 540;
    }

    public void throwThickAssBone(final Entity e) {
        final Vector throwVec = e.getLocation().add(e.getLocation().getDirection().multiply(10)).toVector().subtract(e.getLocation().toVector()).normalize().multiply(1.2);
        Location throwLoc = e.getLocation().add(0.0, 0.5, 0.0);
        final ArmorStand armorStand1 = (ArmorStand)e.getWorld().spawnEntity(throwLoc, EntityType.ARMOR_STAND);
        armorStand1.getEquipment().setItemInHand(SItem.of(SMaterial.LIVID_DAGGER).getStack());
        armorStand1.setGravity(false);
        armorStand1.setVisible(false);
        armorStand1.setMarker(true);
        final Vector teleportTo = e.getLocation().getDirection().normalize().multiply(1);
        final Vector[] previousVector = new Vector[]{throwVec};
        new BukkitRunnable(){
            private int run = -1;

            public void run() {
                boolean back;
                int angle;
                Vector newVector;
                int ran;
                int i = ran = 0;
                int num = 90;
                Object loc = null;
                ++this.run;
                if (this.run > 100) {
                    this.cancel();
                    return;
                }
                for (int j = 0; j < 10; ++j) {
                    armorStand1.getWorld().spigot().playEffect(armorStand1.getLocation().clone().add(0.0, 1.75, 0.0), Effect.CRIT, 0, 1, (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(0.0, 0.5), (float)SUtil.random(-0.5, 0.5), 0.0f, 1, 20);
                }
                Location locof = armorStand1.getLocation();
                locof.setY(locof.getY() + 1.0);
                if (locof.getBlock().getType() != Material.AIR) {
                    armorStand1.remove();
                    this.cancel();
                    return;
                }
                double xPos = armorStand1.getRightArmPose().getX();
                armorStand1.setRightArmPose(new EulerAngle(xPos + 0.7, 0.0, 0.0));
                previousVector[0] = newVector = new Vector(throwVec.getX(), previousVector[0].getY() - 0.03, throwVec.getZ());
                armorStand1.setVelocity(newVector);
                if (i < 13) {
                    angle = i * 20 + num;
                    back = false;
                } else {
                    angle = i * 20 - num;
                    back = true;
                }
                if (locof.getBlock().getType() != Material.AIR && locof.getBlock().getType() != Material.WATER) {
                    armorStand1.remove();
                    this.cancel();
                    return;
                }
                if (i % 2 == 0 && i < 13) {
                    armorStand1.teleport(armorStand1.getLocation().add(teleportTo).multiply(1.0));
                    armorStand1.teleport(armorStand1.getLocation().add(teleportTo).multiply(1.0));
                } else if (i % 2 == 0) {
                    armorStand1.teleport(armorStand1.getLocation().subtract(loc.getDirection().normalize().multiply(1)));
                    armorStand1.teleport(armorStand1.getLocation().subtract(loc.getDirection().normalize().multiply(1)));
                }
                for (Entity en : armorStand1.getNearbyEntities(1.0, 1.0, 1.0)) {
                    if (!(en instanceof Player)) continue;
                    Player p = (Player)en;
                    p.getWorld().playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                    User.getUser(p.getUniqueId()).damage(p.getMaxHealth() * 25.0 / 100.0, EntityDamageEvent.DamageCause.ENTITY_ATTACK, e);
                    p.damage(1.0E-5);
                    armorStand1.remove();
                    this.cancel();
                    break;
                }
            }

            public synchronized void cancel() throws IllegalStateException {
                super.cancel();
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
    }

    private static /* synthetic */ void lambda$onDamage$0(Entity en, Vector v) {
        en.setVelocity(v);
    }
}
