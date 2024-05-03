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
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Giant
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
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
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

public class DiamondGiant
extends BaseZombie {
    private static LivingEntity e;
    private boolean swordActiv = false;
    private boolean swordSlamCD = true;

    @Override
    public String getEntityName() {
        return Sputnik.trans("&3&lThe Diamond Giant");
    }

    @Override
    public double getEntityMaxHealth() {
        return 2.5E7;
    }

    @Override
    public double getDamageDealt() {
        return 45000.0;
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
            this.swordSlamCD = false;
        }, 100L);
        entity.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)));
        Sputnik.applyPacketGiant((Entity)entity);
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 50);
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("highername", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("RedNameTag", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("Giant_", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        new BukkitRunnable(){

            public void run() {
                CraftLivingEntity target = ((CraftZombie)entity).getTarget();
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (!DiamondGiant.this.swordSlamCD && !DiamondGiant.this.swordActiv && SUtil.random(1, 140) <= 7 && target != null) {
                    DiamondGiant.this.swordActiv = true;
                    DiamondGiant.this.swordSlamCD = true;
                    DiamondGiant.this.swordSlamAC(entity, (LivingEntity)target);
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
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 8L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)), DiamondGiant.c(Material.DIAMOND_HELMET), DiamondGiant.c(Material.DIAMOND_CHESTPLATE), DiamondGiant.c(Material.DIAMOND_LEGGINGS), DiamondGiant.c(Material.DIAMOND_BOOTS));
    }

    @Override
    public void onDeath(SEntity sEntity, Entity killed, Entity damager) {
        Sputnik.zero(killed);
        if (SadanHuman.SadanGiantsCount.containsKey(killed.getWorld().getUID())) {
            SadanHuman.SadanGiantsCount.put(killed.getWorld().getUID(), SadanHuman.SadanGiantsCount.get(killed.getWorld().getUID()) - 1);
        }
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

    public static ItemStack buildColorStack(int hexcolor) {
        ItemStack stack = SUtil.applyColorToLeatherArmor(new ItemStack(Material.LEATHER_HELMET), Color.fromRGB((int)hexcolor));
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

    public static void applyEffect(PotionEffectType e, Entity en, int ticks, int amp) {
        ((LivingEntity)en).addPotionEffect(new PotionEffect(e, ticks, amp));
    }

    public void swordSlamAC(LivingEntity e, LivingEntity tar) {
        SUtil.delay(() -> this.swordSlamF(e, tar), 60L);
    }

    public void swordSlamF(LivingEntity e, LivingEntity tar) {
        this.swordSlam(e, tar);
    }

    public void swordSlam(final LivingEntity e, final LivingEntity player) {
        e.getEquipment().setItemInHand(null);
        final Giant armorStand = (Giant)player.getWorld().spawn(e.getLocation().add(0.0, 12.0, 0.0), Giant.class);
        armorStand.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)));
        Sputnik.applyPacketGiant((Entity)armorStand);
        armorStand.setCustomName("Dinnerbone");
        armorStand.setMetadata("GiantSword", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        armorStand.setMetadata("NoAffect", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        EntityManager.Woosh((LivingEntity)armorStand);
        EntityManager.noHit((Entity)armorStand);
        EntityManager.shutTheFuckUp((Entity)armorStand);
        Location firstLocation = e.getLocation().add(0.0, 12.0, 0.0);
        EntityLiving nms = ((CraftLivingEntity)e).getHandle();
        for (Player players : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)players).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((net.minecraft.server.v1_8_R3.Entity)((CraftLivingEntity)e).getHandle(), 0));
        }
        nms.r(((CraftEntity)e).getHandle());
        Location secondLocation = player.getLocation();
        Vector mobsVector = firstLocation.toVector();
        Vector vectorBetween = secondLocation.toVector().subtract(mobsVector);
        vectorBetween.divide(new Vector(10, 10, 10));
        vectorBetween.add(new Vector(0, 0, 0));
        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)SkySimEngine.getPlugin(), () -> armorStand.setVelocity(vectorBetween), 10L, 10L);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)SkySimEngine.getPlugin(), () -> Bukkit.getScheduler().cancelTask(id), 40L);
        new BukkitRunnable(){

            public void run() {
                if (!DiamondGiant.this.swordActiv) {
                    this.cancel();
                    return;
                }
                if (armorStand.isOnGround()) {
                    DiamondGiant.this.swordActiv = false;
                    SUtil.delay(() -> DiamondGiant.this.swordSlamCD = false, 300L);
                    armorStand.remove();
                    Giant sword = (Giant)e.getWorld().spawnEntity(armorStand.getLocation(), EntityType.GIANT);
                    Sputnik.applyPacketGiant((Entity)sword);
                    sword.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1));
                    EntityManager.noAI((Entity)sword);
                    EntityManager.noHit((Entity)sword);
                    EntityManager.shutTheFuckUp((Entity)sword);
                    sword.setCustomName("Dinnerbone");
                    sword.setMetadata("GiantSword", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
                    sword.setMetadata("NoAffect", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
                    ArmorStand stand = (ArmorStand)player.getWorld().spawnEntity(armorStand.getLocation(), EntityType.ARMOR_STAND);
                    stand.setVisible(false);
                    stand.setGravity(true);
                    stand.setPassenger((Entity)sword);
                    sword.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)));
                    e.getWorld().playSound(player.getLocation(), Sound.ANVIL_LAND, 1.0f, 0.0f);
                    e.getWorld().playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 1.0f, 0.35f);
                    for (Entity entities : sword.getWorld().getNearbyEntities(sword.getLocation().add(sword.getLocation().getDirection().multiply(3)), 7.0, 7.0, 7.0)) {
                        Player p;
                        if (entities.hasMetadata("NPC") || entities instanceof ArmorStand || entities instanceof Giant || !(entities instanceof Player)) continue;
                        if (entities.getLocation().add(sword.getLocation().getDirection().multiply(3)).distance(sword.getLocation()) > 2.0) {
                            p = (Player)entities;
                            p.sendMessage(Sputnik.trans("&3&lThe Diamond Giant &chit you with &eSword of 10,000 Truths &cfor " + SUtil.commaify(SadanFunction.dmgc(50000, p, (Entity)e)) + " &cdamage."));
                            continue;
                        }
                        p = (Player)entities;
                        p.sendMessage(Sputnik.trans("&3&lThe Diamond Giant &chit you with &eSword of 10,000 Truths &cfor " + SUtil.commaify(SadanFunction.dmgc(55000, p, (Entity)e)) + " &cdamage."));
                    }
                    SUtil.delay(() -> sword.remove(), 35L);
                    SUtil.delay(() -> stand.remove(), 35L);
                    SUtil.delay(() -> e.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD))), 35L);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    @Override
    public void onDamage(SEntity sEntity, Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
        e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ZOMBIE_HURT, 1.0f, 0.0f);
    }
}

