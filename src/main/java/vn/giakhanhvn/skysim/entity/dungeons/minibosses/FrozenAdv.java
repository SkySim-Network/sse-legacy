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
 *  org.bukkit.Color
 *  org.bukkit.Effect
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.projectiles.ProjectileSource
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.EulerAngle
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.dungeons.minibosses;

import com.google.common.util.concurrent.AtomicDouble;
import de.slikey.effectlib.effect.ConeEffect;
import de.slikey.effectlib.util.ParticleEffect;
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
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class FrozenAdv
extends BaseZombie {
    private boolean isEating = false;
    private boolean isBowing = false;
    private boolean EatingCooldown = false;
    private boolean CDDR = false;
    private boolean CDLA = false;

    @Override
    public String getEntityName() {
        return Sputnik.trans("&d&lFrozen Adventurer");
    }

    @Override
    public double getEntityMaxHealth() {
        return 7.0E8;
    }

    @Override
    public double getDamageDealt() {
        return 6000000.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        ((CraftZombie)entity).setBaby(false);
        AttributeInstance followRange = ((CraftLivingEntity)entity).getHandle().getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
        followRange.setValue(40.0);
        PlayerDisguise pl = Sputnik.applyPacketNPC((org.bukkit.entity.Entity)entity, "ewogICJ0aW1lc3RhbXAiIDogMTYwMTUyOTc4ODY1OSwKICAicHJvZmlsZUlkIiA6ICIyMWUzNjdkNzI1Y2Y0ZTNiYjI2OTJjNGEzMDBhNGRlYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJHZXlzZXJNQyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZGVlNTAxODFkYmMzMzZkMGQ5MDM1ZjYyMjQ0OGM0M2RhYTdmZGI1ZDdjYWFhOTFmNjdiM2JjNjQ4NmMzMjEwIgogICAgfQogIH0KfQ==", "i0cF74RyF/YAl8m2VathjBpRKlY93rrqnBx/fZPwzaXhL+KLGGhGEJc0SPSzDqpQDXXQKeMO2qKQwDsIbXrNQT0TUMzjFObzPznx5LVNrjZIs9xYpOyh6olmPOxKb8S/5DKKIbtm1ZfejK4KFLuz1OP4idcgPf+xzhoXsPfX8KdbWXoTu192zQ/L6lo0N2dAMzjz6ymELXkpl05gruONtSF01OjcyvVL80lWR5YyecoycxqFPpVXOhxAYYa2PoircLwMg2Vtmkck0/u0gniDt3EEkZkQ44CjT/9bxjf4LEkeHMdnXkt/KYaTk934/eSgr8dL6zlU7v/IyX6Jn3vceQQz9XF04Q+COBsxfjvExc7/Awti+8OJASCvlWoBHL2jqQbXDKXk/OJgjh6F8rPECljiqrdfmEC+W3lM/mc8WBX1KheHtiZiMlyYPOZQ4hCdCJoiHi+jxIhV56UVvu911lhsyRB4ovyb6JWqty/9ztN8spEA4Mxk0xIcK7aVJ3nb8XrfCsMRC17oAwd6W79qSGKxmLJxTg25w+HJ1Sj4JRrLcD4Ix505ptLAdyGdd17xr5oXZ7G4cT3vm19sR1SqPYuyjHV9S1eJBtJAo7kFhFcoAKGBp8MdHXZ4MTZPZZSXdOwPGcYavANN7NA3EPecvfqBUCh9e3IhXJOP70Huv5A=", true);
        final PlayerWatcher skywatch = pl.getWatcher();
        CraftLivingEntity target = ((CraftZombie)entity).getTarget();
        EntityManager.DEFENSE_PERCENTAGE.put((org.bukkit.entity.Entity)entity, 87);
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("LD", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (FrozenAdv.this.isEating) {
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 65, 4));
                    entity.getEquipment().setItemInHand(new ItemStack(Material.GOLDEN_APPLE));
                    Sputnik.sendEatingAnimation(entity);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 4L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 20; ++i) {
                    entity.getWorld().spigot().playEffect(entity.getLocation().clone().add(0.0, 0.25, 0.0), Effect.FLAME, 0, 1, (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(0.0, 1.5), (float)SUtil.random(-0.5, 0.5), 0.0f, 1, 20);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 20L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (entity.getHealth() < entity.getMaxHealth() * 1.0 / 2.0 && !FrozenAdv.this.EatingCooldown && !FrozenAdv.this.isEating) {
                    FrozenAdv.this.EatingCooldown = true;
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 65, 4));
                    FrozenAdv.this.isBowing = false;
                    SUtil.delay(() -> FrozenAdv.this.isEating = true, 5L);
                    entity.getEquipment().setItemInHand(new ItemStack(Material.GOLDEN_APPLE));
                    new BukkitRunnable(){

                        public void run() {
                            if (entity.isDead()) {
                                return;
                            }
                            entity.getEquipment().setItemInHand(new ItemStack(Material.AIR));
                            entity.getWorld().playSound(entity.getLocation(), Sound.BURP, 1.0f, 1.0f);
                            double healamount = FrozenAdv.this.getEntityMaxHealth() * (double)SUtil.random(40, 60) / 100.0;
                            if (!entity.isDead()) {
                                entity.setHealth(Math.min(entity.getMaxHealth(), entity.getHealth() + healamount));
                            }
                            FrozenAdv.this.isEating = false;
                            SUtil.delay(() -> {
                                if (!FrozenAdv.this.isBowing) {
                                    entity.getEquipment().setItemInHand(SUtil.enchant(SItem.of(SMaterial.ICE_WAND).getStack()));
                                } else {
                                    entity.getEquipment().setItemInHand(SItem.of(SMaterial.BOW).getStack());
                                }
                            }, 5L);
                            SUtil.delay(() -> FrozenAdv.this.EatingCooldown = false, SUtil.random(600, 800));
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 60L);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 10L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    Sputnik.zero((org.bukkit.entity.Entity)entity);
                    this.cancel();
                    return;
                }
                CraftLivingEntity target1 = ((CraftZombie)entity).getTarget();
                if (target1 != null) {
                    if (target1.getLocation().distance(entity.getLocation()) >= 6.0 && target1.getLocation().distance(entity.getLocation()) < 16.0) {
                        entity.teleport(entity.getLocation().setDirection(target1.getLocation().toVector().subtract(entity.getLocation().toVector())));
                        FrozenAdv.sendHeadRotation((org.bukkit.entity.Entity)entity, entity.getLocation().getYaw(), entity.getLocation().getPitch());
                    }
                    if ((target1.getLocation().distance(entity.getLocation()) < 6.0 || target1.getLocation().distance(entity.getLocation()) > 16.0) && !FrozenAdv.this.isEating) {
                        SUtil.delay(() -> entity.getEquipment().setItemInHand(SUtil.enchant(SItem.of(SMaterial.ICE_WAND).getStack())), 0L);
                        FrozenAdv.this.isBowing = false;
                    }
                    if (target1.getLocation().distance(entity.getLocation()) >= 6.0 && target1.getLocation().distance(entity.getLocation()) < 16.0 && !FrozenAdv.this.isBowing && !FrozenAdv.this.isEating) {
                        FrozenAdv.this.isBowing = true;
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
                                if (!FrozenAdv.this.isBowing) {
                                    skywatch.setRightClicking(false);
                                    entity.removePotionEffect(PotionEffectType.SLOW);
                                    this.cancel();
                                    return;
                                }
                                if (this.t == 5) {
                                    if (!FrozenAdv.this.isBowing) {
                                        return;
                                    }
                                    skywatch.setRightClicking(true);
                                }
                                if (this.t == this.atkCharge) {
                                    if (!FrozenAdv.this.isBowing) {
                                        return;
                                    }
                                    skywatch.setRightClicking(false);
                                }
                                if (this.t >= this.atkCharge + 1) {
                                    if (!FrozenAdv.this.isBowing) {
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
                                    FrozenAdv.this.isBowing = false;
                                }
                            }
                        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
                    }
                    if (target1.getLocation().distance(entity.getLocation()) <= 10.0 && !FrozenAdv.this.isBowing && !FrozenAdv.this.isEating && SUtil.random(0, 100) < 10 && !FrozenAdv.this.CDLA) {
                        FrozenAdv.this.CDLA = true;
                        FrozenAdv.this.lightningPlayer((org.bukkit.entity.Entity)entity);
                        SUtil.delay(() -> FrozenAdv.this.CDLA = false, 300L);
                    }
                    if (target1.getLocation().distance(entity.getLocation()) <= 5.0 && !FrozenAdv.this.isBowing && !FrozenAdv.this.isEating) {
                        if (SUtil.random(0, 100) > 30) {
                            return;
                        }
                        if (FrozenAdv.this.CDDR) {
                            return;
                        }
                        FrozenAdv.this.CDDR = true;
                        skywatch.setRightClicking(true);
                        FrozenAdv.this.playPar(entity.getEyeLocation().setDirection(target1.getLocation().toVector().subtract(entity.getLocation().toVector())));
                        entity.getLocation().getWorld().playSound(entity.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                        for (org.bukkit.entity.Entity e : target1.getWorld().getNearbyEntities(entity.getLocation().add(entity.getLocation().getDirection().multiply(1)), 3.0, 3.0, 3.0)) {
                            if (!(e instanceof Player)) continue;
                            final Player player = (Player)e;
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 20));
                            double b = 0.0;
                            for (int i = 0; i < 2; ++i) {
                                final int d = i;
                                if (i == 0) {
                                    b = 0.2;
                                } else if (i == 1) {
                                    b = 0.4;
                                } else if (i == 2) {
                                    b = 0.6;
                                }
                                final ArmorStand stands = (ArmorStand)player.getWorld().spawn(player.getLocation().add(0.0, b + 1.0, 0.0), ArmorStand.class);
                                stands.setCustomNameVisible(false);
                                stands.setVisible(false);
                                stands.setArms(true);
                                stands.setMarker(true);
                                stands.setGravity(false);
                                stands.setRightArmPose(new EulerAngle(0.0, 0.0, 12.0));
                                stands.getEquipment().setItemInHand(new ItemStack(Material.PACKED_ICE));
                                SUtil.delay(() -> {
                                    stands.remove();
                                    player.removeMetadata("frozen", (Plugin)SkySimEngine.getPlugin());
                                }, 100L);
                                new BukkitRunnable(){

                                    public void run() {
                                        double c = 0.0;
                                        if (d == 0) {
                                            c = 0.2;
                                        } else if (d == 1) {
                                            c = 0.4;
                                        } else if (d == 2) {
                                            c = 0.6;
                                        }
                                        if (stands.isDead()) {
                                            player.removePotionEffect(PotionEffectType.SLOW);
                                            player.removeMetadata("frozen", (Plugin)SkySimEngine.getPlugin());
                                            this.cancel();
                                            return;
                                        }
                                        if (player.isDead() || entity.isDead()) {
                                            stands.remove();
                                            player.removeMetadata("frozen", (Plugin)SkySimEngine.getPlugin());
                                        }
                                        stands.teleport(player.getLocation().add(0.0, c + 1.0, 0.0));
                                    }
                                }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
                            }
                            PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
                            if (statistics == null) {
                                return;
                            }
                            double defense = statistics.getDefense().addAll();
                            int dmglater = (int)Math.round(FrozenAdv.this.getDamageDealt() * 3.0 - FrozenAdv.this.getDamageDealt() * 3.0 * (defense / (defense + 100.0)));
                            User.getUser(player.getUniqueId()).damage(dmglater, EntityDamageEvent.DamageCause.ENTITY_ATTACK, (org.bukkit.entity.Entity)entity);
                            player.setMetadata("frozen", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
                            ((LivingEntity)e).damage(1.0E-6, null);
                        }
                        SUtil.delay(() -> {
                            if (!FrozenAdv.this.isBowing) {
                                skywatch.setRightClicking(false);
                            }
                        }, 20L);
                        SUtil.delay(() -> FrozenAdv.this.CDDR = false, 200L);
                    }
                } else if (!FrozenAdv.this.isEating) {
                    FrozenAdv.this.isBowing = false;
                    entity.getEquipment().setItemInHand(SUtil.enchant(SItem.of(SMaterial.ICE_WAND).getStack()));
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
                if (((CraftZombie)entity).getTarget().getLocation().distance(entity.getLocation()) <= 4.0 || FrozenAdv.this.isEating || FrozenAdv.this.isBowing) {
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
                    if (FrozenAdv.this.isEating || FrozenAdv.this.isBowing || !(entities instanceof Player) || (target = (Player)entities).getGameMode() == GameMode.CREATIVE || target.getGameMode() == GameMode.SPECTATOR || target.hasMetadata("NPC") || target.getNoDamageTicks() == 7 || SUtil.random(0, 10) > 8) continue;
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
    public void onDamage(SEntity sEntity, org.bukkit.entity.Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
        LivingEntity en = sEntity.getEntity();
        Vector v = new Vector(0, 0, 0);
        SUtil.delay(() -> FrozenAdv.lambda$onDamage$0((org.bukkit.entity.Entity)en, v), 1L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(SItem.of(SMaterial.ICE_WAND).getStack()), SUtil.enchant(SUtil.getSkullURLStack("Frozen Blaze Helmet", "55a13bb48e3595b55de8dd6943fc38db5235371278c695bd453e49a0999", 1, "")), SUtil.enchant(FrozenAdv.st(10541807, Material.LEATHER_CHESTPLATE, "Frozen Blaze Chestplate")), SUtil.enchant(FrozenAdv.st(10541807, Material.LEATHER_LEGGINGS, "Frozen Blaze Leggings")), SUtil.enchant(FrozenAdv.st(10541807, Material.LEATHER_BOOTS, "Frozen Blaze Boots")));
    }

    public static ItemStack st(int hexcolor, Material m, String name) {
        ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(m), Color.fromRGB((int)hexcolor));
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
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
        return 0.0;
    }

    @Override
    public double getMovementSpeed() {
        return 0.25;
    }

    public void playPar(Location l) {
        ConeEffect Effect2 = new ConeEffect(SkySimEngine.effectManager);
        Effect2.setLocation(l.clone().add(l.getDirection().normalize().multiply(-0.25)).add(0.0, -0.1, 0.0));
        Effect2.particle = ParticleEffect.SNOW_SHOVEL;
        Effect2.color = Color.WHITE;
        Effect2.angularVelocity = 0.39269908169872414;
        Effect2.lengthGrow = 0.025f;
        Effect2.particles = 30;
        Effect2.period = 3;
        Effect2.iterations = 5;
        Effect2.start();
    }

    public void lightningPlayer(org.bukkit.entity.Entity en) {
        List<org.bukkit.entity.Entity> a = en.getNearbyEntities(10.0, 10.0, 10.0);
        a.removeIf(entity -> entity.getType() != EntityType.PLAYER);
        if (a.size() < 3) {
            for (int i = 0; i < SUtil.random(1, 3); ++i) {
                en.getWorld().strikeLightningEffect(new Location(en.getWorld(), en.getLocation().getX() + (double)SUtil.random(-2, 2), en.getLocation().getY(), en.getLocation().getZ() + (double)SUtil.random(-2, 2), en.getLocation().getYaw(), en.getLocation().getPitch()));
            }
        }
        for (org.bukkit.entity.Entity e : en.getNearbyEntities(10.0, 10.0, 10.0)) {
            if (!(e instanceof Player)) continue;
            Player p = (Player)e;
            p.getWorld().strikeLightningEffect(p.getLocation());
            User.getUser(p.getUniqueId()).damage(p.getMaxHealth() * 10.0 / 100.0, EntityDamageEvent.DamageCause.ENTITY_ATTACK, e);
        }
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

