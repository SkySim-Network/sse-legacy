/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.minecraft.server.v1_8_R3.AttributeInstance
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.GenericAttributes
 *  net.minecraft.server.v1_8_R3.Packet
 *  net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
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
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.dungeons.boss.sadan;

import com.google.common.util.concurrent.AtomicDouble;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanFunction;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanHuman;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class LASRGiant
extends BaseZombie {
    private static LivingEntity e;
    private boolean laserActiveCD = true;
    private boolean laserActive = false;

    @Override
    public String getEntityName() {
        return Sputnik.trans("&4&lL.A.S.R.");
    }

    @Override
    public double getEntityMaxHealth() {
        return 2.5E7;
    }

    @Override
    public double getDamageDealt() {
        return 25000.0;
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        if (entity.getWorld().getPlayers().size() == 0) {
            return;
        }
        e = entity;
        ((CraftZombie)entity).setBaby(false);
        Player p = (Player)entity.getWorld().getPlayers().get(SUtil.random(0, entity.getWorld().getPlayers().size() - 1));
        if (p != null && p.getGameMode() != GameMode.SPECTATOR && p.getGameMode() != GameMode.CREATIVE) {
            ((CraftZombie)entity).setTarget((LivingEntity)p);
        }
        AttributeInstance followRange = ((CraftLivingEntity)entity).getHandle().getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
        followRange.setValue(500.0);
        SUtil.delay(() -> {
            this.laserActiveCD = false;
        }, 10L);
        Sputnik.applyPacketGiant((Entity)entity);
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 60);
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("highername", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("Giant_", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        final EntityLiving nmsr = ((CraftLivingEntity)entity).getHandle();
        nmsr.getBoundingBox().grow(5.0, 5.0, 5.0);
        new BukkitRunnable(){

            public void run() {
                CraftLivingEntity target = ((CraftZombie)entity).getTarget();
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                nmsr.getBoundingBox().grow(5.0, 5.0, 5.0);
                if (!LASRGiant.this.laserActiveCD && !LASRGiant.this.laserActive && SUtil.random(1, 120) <= 6 && target != null) {
                    LASRGiant.this.laserActiveCD = true;
                    LASRGiant.this.laserActive = true;
                    LASRGiant.this.laser(entity);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        new BukkitRunnable(){

            public void run() {
                EntityLiving nms = ((CraftLivingEntity)entity).getHandle();
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                for (Entity entities : entity.getWorld().getNearbyEntities(entity.getLocation().add(entity.getLocation().getDirection().multiply(1.0)), 1.5, 1.5, 1.5)) {
                    Player target;
                    if (!(entities instanceof Player) || (target = (Player)entities).getGameMode() == GameMode.CREATIVE || target.getGameMode() == GameMode.SPECTATOR || target.hasMetadata("NPC")) continue;
                    entity.teleport(entity.getLocation().setDirection(target.getLocation().toVector().subtract(target.getLocation().toVector())));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        ((CraftPlayer)players).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((net.minecraft.server.v1_8_R3.Entity)((CraftLivingEntity)entity).getHandle(), 0));
                    }
                    nms.r((net.minecraft.server.v1_8_R3.Entity)((CraftPlayer)target).getHandle());
                    break;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 5L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(null, LASRGiant.b(12228503, Material.LEATHER_HELMET), LASRGiant.b(12228503, Material.LEATHER_CHESTPLATE), LASRGiant.b(12228503, Material.LEATHER_LEGGINGS), LASRGiant.b(12228503, Material.LEATHER_BOOTS));
    }

    @Override
    public void onDeath(SEntity sEntity, Entity killed, Entity damager) {
        Sputnik.zero(killed);
        if (SadanHuman.SadanGiantsCount.containsKey(killed.getWorld().getUID())) {
            SadanHuman.SadanGiantsCount.put(killed.getWorld().getUID(), SadanHuman.SadanGiantsCount.get(killed.getWorld().getUID()) - 1);
        }
    }

    public void setArmorHex(LivingEntity e, int i) {
        e.getEquipment().setHelmet(LASRGiant.buildColorStackH(i));
        e.getEquipment().setChestplate(LASRGiant.buildColorStackC(i));
        e.getEquipment().setLeggings(LASRGiant.buildColorStackL(i));
        e.getEquipment().setBoots(LASRGiant.buildColorStackB(i));
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
    public boolean isBaby() {
        return false;
    }

    @Override
    public double getXPDropped() {
        return 0.0;
    }

    @Override
    public double getMovementSpeed() {
        return 0.35;
    }

    public void laser(LivingEntity e) {
        int[] array_colors = new int[]{12228503, 0x855A5A, 6897985, 0x5C3333, 0x522C2C};
        SUtil.delay(() -> this.setArmorHex(e, array_colors[0]), 20L);
        SUtil.delay(() -> this.setArmorHex(e, array_colors[1]), 40L);
        SUtil.delay(() -> this.setArmorHex(e, array_colors[2]), 60L);
        SUtil.delay(() -> this.setArmorHex(e, array_colors[3]), 80L);
        SUtil.delay(() -> this.setArmorHex(e, array_colors[4]), 100L);
        SUtil.delay(() -> this.laserAni(e), 105L);
        SUtil.delay(() -> {
            this.laserActive = false;
        }, 250L);
        SUtil.delay(() -> this.setArmorHex(e, array_colors[4]), 270L);
        SUtil.delay(() -> this.setArmorHex(e, array_colors[3]), 290L);
        SUtil.delay(() -> this.setArmorHex(e, array_colors[2]), 310L);
        SUtil.delay(() -> this.setArmorHex(e, array_colors[1]), 330L);
        SUtil.delay(() -> this.setArmorHex(e, array_colors[0]), 350L);
        SUtil.delay(() -> {
            this.laserActiveCD = false;
        }, 500L);
    }

    public void laserAni(final LivingEntity e) {
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (!LASRGiant.this.laserActive) {
                    this.cancel();
                    return;
                }
                CraftLivingEntity target = ((CraftZombie)e).getTarget();
                float angle_1 = e.getEyeLocation().getYaw() / 60.0f;
                Location loc1 = e.getEyeLocation().add(Math.cos(angle_1), 0.0, Math.sin(angle_1));
                Location loc2 = e.getEyeLocation().subtract(Math.cos(angle_1), 0.0, Math.sin(angle_1));
                loc1.add(0.0, 9.5, 0.0);
                loc2.add(0.0, 9.5, 0.0);
                if (target != null) {
                    if (target.getLocation().distance(e.getLocation()) < 1.0 || target.getLocation().distance(e.getLocation()) > 100.0) {
                        return;
                    }
                    Location loc1_ = target.getLocation();
                    Location loc2_ = target.getLocation();
                    Location en1 = loc1_.add(0.0, 0.5, 0.0);
                    Location en2 = loc2_.add(0.0, 0.5, 0.0);
                    LASRGiant.drawLine(loc1, en1, 0.0);
                    LASRGiant.drawLine(loc2, en2, 0.0);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 5L);
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (!LASRGiant.this.laserActive) {
                    this.cancel();
                    return;
                }
                CraftLivingEntity target = ((CraftZombie)e).getTarget();
                float angle_1 = e.getEyeLocation().getYaw() / 60.0f;
                Location loc1 = e.getEyeLocation().add(Math.cos(angle_1), 0.0, Math.sin(angle_1));
                Location loc2 = e.getEyeLocation().subtract(Math.cos(angle_1), 0.0, Math.sin(angle_1));
                loc1.add(0.0, 9.5, 0.0);
                loc2.add(0.0, 9.5, 0.0);
                if (target != null) {
                    if (target.getLocation().distance(e.getLocation()) < 1.0 || target.getLocation().distance(e.getLocation()) > 100.0) {
                        return;
                    }
                    Location loc1_ = target.getLocation();
                    Location loc2_ = target.getLocation();
                    Location en1 = loc1_.add(0.0, 0.5, 0.0);
                    Location en2 = loc2_.add(0.0, 0.5, 0.0);
                    LASRGiant.getEntity(loc1, en1, e);
                    LASRGiant.getEntity(loc2, en2, e);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 10L);
    }

    public static ItemStack buildColorStackH(int hexcolor) {
        ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_HELMET), Color.fromRGB((int)hexcolor));
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    public static ItemStack buildColorStackC(int hexcolor) {
        ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_CHESTPLATE), Color.fromRGB((int)hexcolor));
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    public static ItemStack buildColorStackL(int hexcolor) {
        ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_LEGGINGS), Color.fromRGB((int)hexcolor));
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    public static ItemStack buildColorStackB(int hexcolor) {
        ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_BOOTS), Color.fromRGB((int)hexcolor));
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    public static ItemStack b(int hexcolor, Material m) {
        ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(m), Color.fromRGB((int)hexcolor));
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    public static ItemStack c(Material m) {
        ItemStack stack = new ItemStack(m);
        ItemMeta itemMeta = stack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        stack.setItemMeta(itemMeta);
        return stack;
    }

    public static void drawLine(Location point1, Location point2, double space) {
        Location blockLocation = point1;
        Location crystalLocation = point2;
        Vector vector = blockLocation.clone().add(0.1, 0.0, 0.1).toVector().subtract(crystalLocation.clone().toVector());
        double count = 90.0;
        for (int i = 1; i <= (int)count; ++i) {
            point1.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), Effect.COLOURED_DUST, 0, 1, 0.8627451f, 0.03529412f, 0.007843138f, 1.0f, 0, 64);
            point1.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), Effect.COLOURED_DUST, 0, 1, 1.0196079f, 0.03529412f, 0.007843138f, 1.0f, 0, 64);
        }
    }

    public static void getEntity(Location finaldestination, Location ended, LivingEntity e) {
        Location blockLocation = finaldestination;
        Location crystalLocation = ended;
        Vector vector = blockLocation.clone().add(0.1, 0.1, 0.1).toVector().subtract(crystalLocation.clone().toVector());
        double count = 90.0;
        for (int i = 1; i <= (int)count; ++i) {
            for (Entity entity : ended.getWorld().getNearbyEntities(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), 0.17, 0.0, 0.17)) {
                if (!(entity instanceof Player)) continue;
                Player p = (Player)entity;
                p.getWorld().playEffect(p.getLocation().clone().add(0.0, 2.2, 0.0), Effect.LAVA_POP, 10);
                p.getWorld().playEffect(p.getLocation().clone().add(0.0, 2.2, 0.0), Effect.LAVA_POP, 10);
                p.getWorld().playEffect(p.getLocation().clone().add(0.0, 2.2, 0.0), Effect.LAVA_POP, 10);
                p.getWorld().playEffect(p.getLocation().clone().add(0.0, 2.2, 0.0), Effect.LAVA_POP, 10);
                p.sendMessage(Sputnik.trans("&4&lL.A.S.R. &chit you with &eLaser Eyes &cfor " + SUtil.commaify(SadanFunction.dmgc(15000, p, (Entity)e)) + " &cdamage."));
                return;
            }
        }
    }

    public static void applyEffect(PotionEffectType e, Entity en, int ticks, int amp) {
        ((LivingEntity)en).addPotionEffect(new PotionEffect(e, ticks, amp));
    }

    @Override
    public void onDamage(SEntity sEntity, Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
        e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ZOMBIE_HURT, 1.0f, 0.0f);
    }
}

