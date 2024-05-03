/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  de.slikey.effectlib.effect.ConeEffect
 *  de.slikey.effectlib.util.ParticleEffect
 *  me.libraryaddict.disguise.disguisetypes.PlayerDisguise
 *  me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher
 *  net.minecraft.server.v1_8_R3.AttributeInstance
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.EntityZombie
 *  net.minecraft.server.v1_8_R3.GenericAttributes
 *  net.minecraft.server.v1_8_R3.MathHelper
 *  net.minecraft.server.v1_8_R3.MobEffectList
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
 *  org.bukkit.Bukkit
 *  org.bukkit.Effect
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.ThrownPotion
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.Potion
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.potion.PotionType
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.dimoon;

import com.google.common.util.concurrent.AtomicDouble;
import de.slikey.effectlib.effect.ConeEffect;
import de.slikey.effectlib.util.ParticleEffect;
import java.util.Arrays;
import java.util.List;
import me.libraryaddict.disguise.disguisetypes.PlayerDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.PlayerWatcher;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityZombie;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.MobEffectList;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.EntityDrop;
import vn.giakhanhvn.skysim.entity.EntityDropType;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class Dimoonizae
extends BaseZombie {
    private boolean isBowing = false;
    private boolean s = false;
    private Location spawnLoc = null;

    @Override
    public String getEntityName() {
        return Sputnik.trans("&e&lDimooniaze");
    }

    @Override
    public double getEntityMaxHealth() {
        return 5.0E8;
    }

    @Override
    public double getDamageDealt() {
        return 2500000.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        this.spawnLoc = entity.getLocation();
        ((CraftZombie)entity).setBaby(false);
        AttributeInstance followRange = ((CraftLivingEntity)entity).getHandle().getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
        followRange.setValue(40.0);
        PlayerDisguise pl = Sputnik.applyPacketNPC((org.bukkit.entity.Entity)entity, "ewogICJ0aW1lc3RhbXAiIDogMTYwNzI2OTE0ODI0NSwKICAicHJvZmlsZUlkIiA6ICJmMjc0YzRkNjI1MDQ0ZTQxOGVmYmYwNmM3NWIyMDIxMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJIeXBpZ3NlbCIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lMzgxYTE4NzVlZmViMmEyNjBkMzU3OTg0YzNlNzVhN2RmYmM3YTIyYjU4MmJmZTk5OTA0MjFjYmI0NDVhY2MiCiAgICB9CiAgfQp9", "IPtgN21aEt2XOntNnSueh3eJHFJOEwIad/ttKraizcl4ffFSymZQo3NhgUPbRnSy2lFNHR/kTl6fczwnRtwxBNmG/YeA7kyr225kgROEAnze8owdQSDkFhvcnP5QRn5oBW9Aa11ZZRlcHuMEUYyZptYkUo8VZ4EV6NSkxz6n5Vjph8KJc2qTLsFtSZIi9lvMAvZRuR4IaKZT85Pnf04Lqmsj1mXkV22pjG3t5MQECsAEmV6beLAVkSjpxQqEkr86+tCbeYRx77fZxxhswHhCZsTB50I5EpDJ3cx5pvvmz8LW2/KoEppXt+NAW/3P4aHuj34KFMp1+qJjSHStl9yKHEdCzTKUy2bh3b6cq6hr0WfCAOes1X0uObindLA6kP+y0UbtvPHGnuyslvQ47X5NdkT54oATW8wBWh85gPUEPLsBXO3VBvfK5omSgaSrC3D1xZbuFjdDYETg4BmTlJOnhoDHUTAvl/a9gQTnItyJkLvkoa/WNWlSk6lcNqz09X7VipnerY5XYad1A1T5XngOg6+TUBwW+XXcxxh5FDH3kMGLomDCbZjaiKm2QvPzVrHVtZ0DhdvQ3jzGa/oSyCbQCWWgusptxeMcOvgxtrjtb4ZxnFiR3H8XYPYWsSxl2zZUlq7Ias9MbKILu8CulOX90i3Gha27lSIaf+ksUO4vrII=", true);
        final PlayerWatcher skywatch = pl.getWatcher();
        CraftLivingEntity target = ((CraftZombie)entity).getTarget();
        EntityManager.DEFENSE_PERCENTAGE.put((org.bukkit.entity.Entity)entity, 70);
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("LD", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 20; ++i) {
                    entity.getWorld().spigot().playEffect(entity.getLocation().clone().add(0.0, 0.25, 0.0), Effect.WITCH_MAGIC, 0, 1, (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(0.0, 0.6), (float)SUtil.random(-0.5, 0.5), 0.0f, 1, 20);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 15L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    Sputnik.zero((org.bukkit.entity.Entity)entity);
                    this.cancel();
                    return;
                }
                CraftLivingEntity target1 = ((CraftZombie)entity).getTarget();
                if (entity.getFireTicks() > 0) {
                    entity.teleport(Dimoonizae.this.spawnLoc);
                }
                if (target1 != null) {
                    if (target1.getLocation().distance(entity.getLocation()) >= 6.0 && target1.getLocation().distance(entity.getLocation()) < 16.0) {
                        entity.teleport(entity.getLocation().setDirection(target1.getLocation().toVector().subtract(entity.getLocation().toVector())));
                        Sputnik.sendHeadRotation((org.bukkit.entity.Entity)entity, entity.getLocation().getYaw(), entity.getLocation().getPitch());
                    }
                    if (target1.getLocation().distance(entity.getLocation()) < 6.0 || target1.getLocation().distance(entity.getLocation()) > 16.0) {
                        SUtil.delay(() -> entity.getEquipment().setItemInHand(SUtil.enchant(SItem.of(SMaterial.DIAMOND_SWORD).getStack())), 0L);
                        Dimoonizae.this.isBowing = false;
                    }
                    if (target1.getLocation().distance(entity.getLocation()) >= 6.0 && target1.getLocation().distance(entity.getLocation()) < 16.0 && !Dimoonizae.this.isBowing) {
                        Dimoonizae.this.isBowing = true;
                        skywatch.setRightClicking(false);
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000, 4));
                        entity.getEquipment().setItemInHand(SItem.of(SMaterial.BOW).getStack());
                        new BukkitRunnable(){
                            int t = 0;
                            int atkCharge = 20;
                            double bowPower = 2.2;
                            boolean crit = true;
                            public void run() {
                                if (target1.getLocation().distance(entity.getLocation()) <= 10.0) {
                                    this.atkCharge = 10;
                                    this.bowPower = 1.1;
                                    this.crit = false;
                                }
                                ++this.t;
                                if (!Dimoonizae.this.isBowing) {
                                    skywatch.setRightClicking(false);
                                    entity.removePotionEffect(PotionEffectType.SLOW);
                                    this.cancel();
                                    return;
                                }
                                if (this.t == 5) {
                                    if (!Dimoonizae.this.isBowing) {
                                        return;
                                    }
                                    skywatch.setRightClicking(true);
                                }
                                if (this.t == this.atkCharge) {
                                    if (!Dimoonizae.this.isBowing) {
                                        return;
                                    }
                                    skywatch.setRightClicking(false);
                                }
                                if (this.t >= this.atkCharge + 1) {
                                    if (!Dimoonizae.this.isBowing) {
                                        return;
                                    }
                                    Location location = entity.getEyeLocation().add(entity.getEyeLocation().getDirection().toLocation(entity.getWorld()));
                                    Location l = location.clone();
                                    l.setYaw(location.getYaw());
                                    Arrow arr = entity.getWorld().spawnArrow(l, l.getDirection(), (float)this.bowPower, 1.6f);
                                    arr.setShooter((ProjectileSource)entity);
                                    if (!this.crit) {
                                        arr.setCritical(SUtil.random(0, 1) == 1);
                                    } else {
                                        arr.setCritical(true);
                                    }
                                    skywatch.setRightClicking(false);
                                    entity.removePotionEffect(PotionEffectType.SLOW);
                                    Dimoonizae.this.isBowing = false;
                                }
                            }
                        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
        new BukkitRunnable(){
            Location loc;
            final EntityLiving nms;
            {
                this.loc = entity.getLocation();
                this.nms = ((CraftLivingEntity)entity).getHandle();
            }

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                this.loc.setY(0.0);
                this.nms.setSprinting(false);
                Location loc2 = entity.getLocation();
                loc2.setY(0.0);
                if (entity.hasMetadata("frozen")) {
                    return;
                }
                if (((CraftZombie)entity).getTarget() == null) {
                    return;
                }
                if (((CraftZombie)entity).getTarget().getWorld() != entity.getWorld()) {
                    return;
                }
                if (((CraftZombie)entity).getTarget().getLocation().distance(entity.getLocation()) <= 4.0 || Dimoonizae.this.isBowing) {
                    return;
                }
                if (this.loc.distance(loc2) >= 0.2) {
                    this.nms.setSprinting(true);
                    if (entity.isOnGround() && this.loc.distance(loc2) >= 0.5) {
                        double motY = 0.4199999868869782;
                        double motX = 0.0;
                        double motZ = 0.0;
                        if (this.nms.hasEffect(MobEffectList.JUMP)) {
                            motY += (double)((float)(this.nms.getEffect(MobEffectList.JUMP).getAmplifier() + 1) * 0.2f);
                        }
                        if (this.nms.isSprinting()) {
                            float f = this.nms.yaw * 0.01745329f;
                            motX -= (double)(MathHelper.sin((float)f) * 0.9f);
                            motZ += (double)(MathHelper.cos((float)f) * 0.9f);
                        }
                        entity.setVelocity(new Vector(motX, motY, motZ));
                    }
                    this.loc = entity.getLocation().clone();
                    return;
                }
                this.nms.setSprinting(false);
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 7L);
        new BukkitRunnable(){

            public void run() {
                EntityLiving nms = ((CraftLivingEntity)entity).getHandle();
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (org.bukkit.entity.Entity entities : entity.getWorld().getNearbyEntities(entity.getLocation().add(entity.getLocation().getDirection().multiply(1.0)), 1.5, 1.5, 1.5)) {
                    Player target;
                    if (Dimoonizae.this.isBowing || !(entities instanceof Player) || (target = (Player)entities).getGameMode() == GameMode.CREATIVE || target.getGameMode() == GameMode.SPECTATOR || target.hasMetadata("NPC") || target.getNoDamageTicks() == 7 || SUtil.random(0, 10) > 8) continue;
                    entity.teleport(entity.getLocation().setDirection(target.getLocation().subtract(entities.getLocation()).toVector()));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer)players).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((Entity)((CraftLivingEntity)entity).getHandle(), 0));
                    }
                    nms.r((Entity)((CraftPlayer)target).getHandle());
                    break;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
    }

    @Override
    public void onDeath(SEntity sEntity, org.bukkit.entity.Entity killed, org.bukkit.entity.Entity damager) {
        if (SkySimEngine.getPlugin().dimoon == null) {
            return;
        }
        if (!(damager instanceof Player)) {
            return;
        }
        killed.getWorld().playEffect(killed.getLocation().add(0.0, 0.7, 0.0), Effect.EXPLOSION_HUGE, 0);
        killed.getWorld().playSound(killed.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
        SkySimEngine.getPlugin().dimoon.func((Player)damager);
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(SMaterial.HIDDEN_DIMOON_FRAG, EntityDropType.OCCASIONAL, 1.0));
    }

    @Override
    public void onDamage(SEntity sEntity, org.bukkit.entity.Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
        LivingEntity en = sEntity.getEntity();
        Vector v = new Vector(0, 0, 0);
        SUtil.delay(() -> Dimoonizae.lambda$onDamage$0((org.bukkit.entity.Entity)en, v), 1L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(SItem.of(SMaterial.DIAMOND_SWORD).getStack()), null, null, null, null);
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
        return 200.0;
    }

    @Override
    public double getMovementSpeed() {
        return 0.3;
    }

    public void playPar(Location l) {
        ConeEffect Effect2 = new ConeEffect(SkySimEngine.effectManager);
        Effect2.setLocation(l.clone().add(l.getDirection().normalize().multiply(-0.25)).add(0.0, -0.1, 0.0));
        Effect2.particle = ParticleEffect.FLAME;
        Effect2.angularVelocity = 0.39269908169872414;
        Effect2.lengthGrow = 0.025f;
        Effect2.particles = 30;
        Effect2.period = 3;
        Effect2.iterations = 5;
        Effect2.start();
    }

    public static void spawnHealthPotion(Location location, org.bukkit.entity.Entity en) {
        World world = location.getWorld();
        ItemStack item = new ItemStack(Material.POTION, 1);
        Potion pot = new Potion(1);
        pot.setType(PotionType.INSTANT_HEAL);
        pot.setSplash(true);
        pot.apply(item);
        ThrownPotion thrownPotion = (ThrownPotion)world.spawnEntity(location.clone().add(0.0, -0.5, 0.0), EntityType.SPLASH_POTION);
        thrownPotion.setShooter((ProjectileSource)en);
        thrownPotion.setItem(item);
    }

    public static ItemStack getPot() {
        ItemStack item = new ItemStack(Material.POTION, 1);
        Potion pot = new Potion(1);
        pot.setType(PotionType.INSTANT_HEAL);
        pot.setSplash(true);
        pot.apply(item);
        return item;
    }

    public static ItemStack getStrPot() {
        ItemStack item = new ItemStack(Material.POTION, 1);
        Potion pot = new Potion(1);
        pot.setType(PotionType.STRENGTH);
        pot.setSplash(false);
        pot.apply(item);
        return item;
    }

    public static void sendHeadRotation(org.bukkit.entity.Entity e, float yaw, float pitch) {
        EntityZombie pl = ((CraftZombie)e).getHandle();
        pl.setLocation(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), yaw, pitch);
        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport((Entity)pl);
        Sputnik.sendPacket(e.getWorld(), (Packet)packet);
    }

    private static /* synthetic */ void lambda$onDamage$0(org.bukkit.entity.Entity en, Vector v) {
        en.setVelocity(v);
    }
}

