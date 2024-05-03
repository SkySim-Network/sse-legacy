/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.minecraft.server.v1_8_R3.AttributeInstance
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.GenericAttributes
 *  net.minecraft.server.v1_8_R3.MathHelper
 *  net.minecraft.server.v1_8_R3.MobEffectList
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
 *  org.bukkit.Bukkit
 *  org.bukkit.Effect
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.SkullType
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockFace
 *  org.bukkit.block.Skull
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.EulerAngle
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.dungeons.boss.sadan;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.MobEffectList;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanHuman;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.gui.SkySimBrainCell;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.MagicFlowerPot;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.texture.ItemTexture;

public class TerracottaSadan
extends BaseZombie {
    private double y = 0.85;

    @Override
    public String getEntityName() {
        return Sputnik.trans("&6&lTerracotta");
    }

    @Override
    public double getEntityMaxHealth() {
        return 1.4E7;
    }

    @Override
    public double getDamageDealt() {
        return 20000.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        if (entity.getWorld().getPlayers().size() == 0) {
            return;
        }
        Player p = (Player)entity.getWorld().getPlayers().get(SUtil.random(0, entity.getWorld().getPlayers().size() - 1));
        if (p != null && p.getGameMode() != GameMode.SPECTATOR && p.getGameMode() != GameMode.CREATIVE) {
            ((CraftZombie)entity).setTarget((LivingEntity)p);
        }
        AttributeInstance followRange = ((CraftLivingEntity)entity).getHandle().getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
        followRange.setValue(500.0);
        ((CraftZombie)entity).setBaby(false);
        Sputnik.applyPacketNPC((org.bukkit.entity.Entity)entity, "Ethelian", null, false);
        SUtil.delay(() -> entity.getEquipment().setItemInHand(SItem.of(SMaterial.FLOWER_OF_TRUTH).getStack()), 10L);
        EntityManager.DEFENSE_PERCENTAGE.put((org.bukkit.entity.Entity)entity, 30);
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("LD", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("t_sadan", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                CraftLivingEntity target = ((CraftZombie)entity).getTarget();
                if (target != null && entity.getTicksLived() > 10) {
                    if (target.getWorld() != entity.getWorld()) {
                        return;
                    }
                    if (target.getLocation().distance(entity.getLocation()) >= 5.0) {
                        TerracottaSadan.this.throwRose(entity);
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 30L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                Sputnik.applyPacketNPC((org.bukkit.entity.Entity)entity, "Ethelian", null, false);
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1000L);
        SkySimBrainCell sbc = SkySimBrainCell.loadFromDB("localhost:27786/skysim/artifical_intelligence/cloud/");
        sbc.accessAIFrom(SkySimBrainCell.BrainCellFor.MELEE);
        sbc.accessAIFrom(SkySimBrainCell.BrainCellFor.MOVEMENT);
        sbc.accessAIFrom(SkySimBrainCell.BrainCellFor.ABILITY_USAGE);
        sbc.accessAIFrom(SkySimBrainCell.BrainCellFor.BOW_ATTACK);
        sbc.accessAIFrom(SkySimBrainCell.BrainCellFor.ENTITY_TRACKER);
        sbc.accessAIFrom(SkySimBrainCell.BrainCellFor.ATTACK_PLAYER);
        SkySimBrainCell.applyAIToNMSPlayer(this, 140, sbc);
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
                if (((CraftZombie)entity).getTarget().getLocation().distance(entity.getLocation()) <= 4.0) {
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
                            motX -= (double)(MathHelper.sin((float)f) * 0.6f);
                            motZ += (double)(MathHelper.cos((float)f) * 0.6f);
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
                    if (!(entities instanceof Player) || (target = (Player)entities).getGameMode() == GameMode.CREATIVE || target.getGameMode() == GameMode.SPECTATOR || target.hasMetadata("NPC")) continue;
                    entity.teleport(entity.getLocation().setDirection(target.getLocation().toVector().subtract(target.getLocation().toVector())));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer)players).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((Entity)((CraftLivingEntity)entity).getHandle(), 0));
                    }
                    nms.r((Entity)((CraftPlayer)target).getHandle());
                    break;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
    }

    public void t(ArmorStand respawnAnchor) {
    }

    @Override
    public void onDamage(SEntity sEntity, org.bukkit.entity.Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
    }

    @Override
    public void onDeath(SEntity sEntity, org.bukkit.entity.Entity killed, org.bukkit.entity.Entity damager) {
        Sputnik.zero(killed);
        Location l1 = killed.getLocation();
        if (l1.getBlock().getType() != Material.AIR) {
            double x = l1.getBlockX();
            double y = l1.getBlockY();
            double z = l1.getBlockZ();
            Location l2 = new Location(l1.getWorld(), 194.0, y, z);
            if (l2.getBlock().getType() != Material.AIR && (l2 = new Location(l1.getWorld(), 188.0, y, z)).getBlock().getType() != Material.AIR && (l2 = new Location(l1.getWorld(), 194.0, y + 1.0, z)).getBlock().getType() != Material.AIR) {
                l2 = new Location(l1.getWorld(), 188.0, y + 1.0, z);
            }
            l1 = l2;
        }
        if (SadanHuman.SadanInterest.containsKey(killed.getWorld().getUID())) {
            SadanHuman.SadanInterest.put(killed.getWorld().getUID(), SadanHuman.SadanInterest.get(killed.getWorld().getUID()) - 1);
        }
        killed.remove();
        final ArmorStand respawnAnchor = (ArmorStand)killed.getWorld().spawn(this.a(l1), ArmorStand.class);
        new BukkitRunnable(){

            public void run() {
                if (respawnAnchor.isDead()) {
                    this.cancel();
                    return;
                }
                respawnAnchor.getLocation().getBlock().getLocation().clone().getWorld().spigot().playEffect(respawnAnchor.getLocation().clone().add(0.0, TerracottaSadan.this.y, 0.0), Effect.LARGE_SMOKE, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
                respawnAnchor.getLocation().getBlock().getLocation().clone().getWorld().spigot().playEffect(respawnAnchor.getLocation().clone().add(0.0, TerracottaSadan.this.y, 0.0), Effect.WITCH_MAGIC, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 3L);
        respawnAnchor.setVisible(false);
        respawnAnchor.setMetadata("t_sadan", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        respawnAnchor.setGravity(false);
        respawnAnchor.setMarker(true);
        this.f(respawnAnchor.getLocation().add(0.0, this.y, 0.0));
        new BukkitRunnable(){

            public void run() {
                if (respawnAnchor.isDead()) {
                    this.cancel();
                    return;
                }
                TerracottaSadan.this.f(respawnAnchor.getLocation().clone().add(0.0, TerracottaSadan.this.y + 0.2, 0.0));
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 66L);
        new BukkitRunnable(){

            public void run() {
                if (respawnAnchor.isDead()) {
                    this.cancel();
                    return;
                }
                TerracottaSadan.this.f(respawnAnchor.getLocation().clone().add(0.0, TerracottaSadan.this.y + 0.2, 0.0));
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 131L);
        new BukkitRunnable(){

            public void run() {
                if (respawnAnchor.isDead()) {
                    this.cancel();
                    return;
                }
                TerracottaSadan.this.f(respawnAnchor.getLocation().clone().add(0.0, TerracottaSadan.this.y + 0.2, 0.0));
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 201L);
        this.sendPacketBlock(respawnAnchor.getLocation().getBlock().getLocation().clone().add(0.0, 0.0, 0.0), respawnAnchor.getWorld(), 0, killed.getLocation().getYaw(), (LivingEntity)killed, respawnAnchor);
        SUtil.delay(() -> respawnAnchor.remove(), 202L);
        new BukkitRunnable(){

            public void run() {
                if (respawnAnchor.isDead()) {
                    this.cancel();
                    return;
                }
                new SEntity(respawnAnchor.getLocation().clone(), SEntityType.TERRACOTTA_SADAN, new Object[0]);
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 201L);
    }

    public void summonOnLoc() {
    }

    public void f(Location loc) {
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.4, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.4, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.4, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
        new ParticleBuilder(ParticleEffect.ITEM_CRACK, loc).setParticleData(new ItemTexture(new ItemStack(Material.SOUL_SAND))).setOffset(new Vector((double)Sputnik.randomVector(), 0.3, (double)Sputnik.randomVector())).display();
    }

    public Location getBlockLoc(Block b) {
        Location l = b.getLocation();
        double x = l.getX() + 0.5;
        double z = l.getZ() + 0.5;
        if (l.getX() < 0.0) {
            x = l.getX() - 0.5;
        }
        if (l.getZ() < 0.0) {
            z = l.getZ() - 0.5;
        }
        Location loc = new Location(l.getWorld(), x, l.getY(), z);
        return loc;
    }

    public Location a(Location l) {
        double x = l.getBlockX();
        double z = l.getBlockZ();
        Location loc = new Location(l.getWorld(), x, l.getY(), z);
        Location loc2 = this.getBlockLoc2(loc);
        return loc2;
    }

    public Location getBlockLoc2(Location l) {
        double x = l.getX() + 0.5;
        double z = l.getZ() + 0.5;
        if (l.getX() < 0.0) {
            x = l.getX() - 0.5;
        }
        if (l.getZ() < 0.0) {
            z = l.getZ() - 0.5;
        }
        Location loc = new Location(l.getWorld(), x, l.getY(), z);
        return loc;
    }

    public void spawnHeadBlock(final Location loc, LivingEntity e, final ArmorStand s) {
        final Material perviousblock = loc.getBlock().getType();
        final byte data = loc.getBlock().getData();
        if (perviousblock != Material.AIR) {
            return;
        }
        new BukkitRunnable(){

            public void run() {
                if (s.isDead()) {
                    TerracottaSadan.this.r(loc.getBlock().getLocation(), perviousblock, data);
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        double rot = (e.getLocation().getYaw() - 90.0f) % 360.0f;
        if (rot < 0.0) {
            rot += 360.0;
        }
        BlockFace facingDirection;
        facingDirection = 0.0 <= rot && rot < 22.5 ? BlockFace.NORTH : (22.5 <= rot && rot < 67.5 ? BlockFace.NORTH_EAST : (67.5 <= rot && rot < 112.5 ? BlockFace.EAST : (112.5 <= rot && rot < 157.5 ? BlockFace.SOUTH_EAST : (157.5 <= rot && rot < 202.5 ? BlockFace.SOUTH : (202.5 <= rot && rot < 247.5 ? BlockFace.SOUTH_WEST : (247.5 <= rot && rot < 292.5 ? BlockFace.WEST : (292.5 <= rot && rot < 337.5 ? BlockFace.NORTH_WEST : (337.5 <= rot && rot < 360.0 ? BlockFace.NORTH : null))))))));
        Block b = loc.getBlock();
        b.setTypeIdAndData(Material.SKULL.getId(), (byte)1, true);
        Skull skull = (Skull)b.getState();
        skull.setSkullType(SkullType.PLAYER);
        skull.setOwner("Ethelian");
        skull.setRotation(facingDirection);
        skull.update(true);
        SUtil.delay(() -> b.getLocation().getBlock().setType(Material.AIR), 70L);
        SUtil.delay(() -> this.r(loc.getBlock().getLocation(), perviousblock, data), 70L);
    }

    public void sendPacketBlock(final Location loc, World w, int type, float yaw, LivingEntity e, final ArmorStand s) {
        final Material perviousblock = loc.getBlock().getType();
        final byte data = loc.getBlock().getData();
        new BukkitRunnable(){

            public void run() {
                if (s.isDead()) {
                    TerracottaSadan.this.r(loc.getBlock().getLocation(), perviousblock, data);
                    TerracottaSadan.this.removeItemOnGround(loc.getBlock().getLocation(), Material.RED_ROSE);
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        if (s.isDead()) {
            this.r(loc.getBlock().getLocation(), perviousblock, data);
            return;
        }
        loc.getBlock().setType(Material.FLOWER_POT);
        MagicFlowerPot.changePot(loc.getBlock(), true, s);
        loc.getBlock().getState().update(true);
        SUtil.delay(() -> this.removeItemOnGround(loc.getBlock().getLocation(), Material.RED_ROSE), 65L);
        SUtil.delay(() -> loc.getBlock().setType(Material.AIR), 64L);
        SUtil.delay(() -> this.spawnHeadBlock(loc.getBlock().getLocation().clone().add(0.0, 1.0, 0.0).getBlock().getLocation(), e, s), 130L);
        new BukkitRunnable(){

            public void run() {
                if (s.isDead()) {
                    this.cancel();
                    return;
                }
                loc.getBlock().setTypeIdAndData(159, (byte)12, true);
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 65L);
        SUtil.delay(() -> {
            this.y = 1.2;
        }, 65L);
        SUtil.delay(() -> {
            this.y = 1.7;
        }, 130L);
        SUtil.delay(() -> {
            this.y = 0.85;
        }, 200L);
        SUtil.delay(() -> this.r(loc.getBlock().getLocation(), perviousblock, data), 200L);
    }

    public void r(Location loc, Material perviousblock, byte data) {
        if (perviousblock != Material.FLOWER_POT && perviousblock != Material.SKULL) {
            loc.getBlock().setType(perviousblock);
            SUtil.delay(() -> loc.getBlock().setData(data), 1L);
        }
    }

    public void removeItemOnGround(Location l, Material mat) {
        for (org.bukkit.entity.Entity e : l.getWorld().getNearbyEntities(l, 4.0, 4.0, 4.0)) {
            if (!(e instanceof Item) || ((Item)e).getItemStack().getType() != mat) continue;
            e.remove();
        }
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(null, null, null, null, null);
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
        return 0.37;
    }

    public void throwRose(final LivingEntity e) {
        Location throwLoc = e.getLocation().add(0.0, 0.2, 0.0);
        Vector throwVec = e.getLocation().add(e.getLocation().getDirection().multiply(10)).toVector().subtract(e.getLocation().toVector()).normalize().multiply(1.2);
        e.getWorld().playSound(e.getLocation(), Sound.EAT, 1.0f, 1.0f);
        final ArmorStand armorStand1 = (ArmorStand)e.getWorld().spawnEntity(throwLoc, EntityType.ARMOR_STAND);
        armorStand1.setMetadata("ftd", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        armorStand1.getEquipment().setHelmet(SItem.of(SMaterial.RED_ROSE).getStack());
        armorStand1.setHeadPose(new EulerAngle((double)-92.55f, 0.0, 0.0));
        armorStand1.setGravity(false);
        armorStand1.setMarker(true);
        armorStand1.setVisible(false);
        Vector[] previousVector = new Vector[]{throwVec};
        ArrayList damaged = new ArrayList();
        new BukkitRunnable(){
            private int run = -1;
            private int ticklived = 0;

            public void run() {
                int ran;
                Vector teleportTo = armorStand1.getLocation().getDirection().normalize().multiply(1);
                ++this.ticklived;
                int i = ran = 0;
                int num = 90;
                Object loc = null;
                ++this.run;
                Location locof = armorStand1.getLocation();
                locof.setY(locof.getY() + 1.0);
                if (this.ticklived >= 18) {
                    armorStand1.remove();
                    armorStand1.getWorld().playEffect(locof, Effect.SNOWBALL_BREAK, 3);
                    armorStand1.getWorld().playEffect(locof, Effect.SNOWBALL_BREAK, 10);
                    armorStand1.getWorld().playEffect(locof, Effect.SNOWBALL_BREAK, 10);
                    armorStand1.getWorld().playEffect(locof, Effect.SNOWBALL_BREAK, 10);
                    this.cancel();
                    return;
                }
                if (armorStand1.isDead()) {
                    this.cancel();
                    return;
                }
                for (org.bukkit.entity.Entity e2 : armorStand1.getNearbyEntities(20.0, 20.0, 20.0)) {
                    if (!(e2 instanceof Player) || ((Player)e2).getGameMode() == GameMode.CREATIVE || ((CraftPlayer)e2).getGameMode() == GameMode.SPECTATOR || e2.hasMetadata("NPC")) continue;
                    armorStand1.teleport(armorStand1.getLocation().setDirection(e2.getLocation().toVector().subtract(armorStand1.getLocation().toVector())));
                }
               
                for (org.bukkit.entity.Entity e1 : armorStand1.getNearbyEntities(0.5, 0.5, 0.5)) {
                    if (!(e1 instanceof Player)) continue;
                    Player p = (Player)e1;
                    p.damage(55000.0, (org.bukkit.entity.Entity)e);
                    armorStand1.getWorld().playEffect(locof, Effect.SNOWBALL_BREAK, 3);
                    armorStand1.getWorld().playEffect(locof, Effect.SNOWBALL_BREAK, 10);
                    armorStand1.getWorld().playEffect(locof, Effect.SNOWBALL_BREAK, 10);
                    armorStand1.getWorld().playEffect(locof, Effect.SNOWBALL_BREAK, 10);
                    armorStand1.remove();
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 1L, 3L);
    }
}

