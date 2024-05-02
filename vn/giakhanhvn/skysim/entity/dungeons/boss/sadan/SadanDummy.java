/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Color
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.dungeons.boss.sadan;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanHuman;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SadanDummy
extends BaseZombie {
    @Override
    public String getEntityName() {
        return Sputnik.trans("");
    }

    @Override
    public double getEntityMaxHealth() {
        return 4.0E7;
    }

    @Override
    public double getDamageDealt() {
        return 120000.0;
    }

    @Override
    public void onSpawn(LivingEntity entity, SEntity sEntity) {
        entity.teleport(new Location(entity.getWorld(), 191.5, 54.0, 266.5, 180.0f, 0.0f));
        Location l = entity.getLocation().clone();
        Sputnik.applyPacketGiant((Entity)entity);
        EntityManager.noAI((Entity)entity);
        EntityManager.noHit((Entity)entity);
        entity.setMetadata("GiantSword", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("NoAffect", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 100);
        EntityManager.shutTheFuckUp((Entity)entity);
        entity.setMetadata("dummyforphase3", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("notDisplay", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        SUtil.delay(() -> this.t(entity), 30L);
        SUtil.delay(() -> this.helmetcolor(entity), 220L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)), SadanDummy.b(0x522C2C, Material.LEATHER_HELMET), SadanDummy.b(14751108, Material.LEATHER_CHESTPLATE), SadanDummy.c(Material.DIAMOND_LEGGINGS), SadanDummy.b(8991025, Material.LEATHER_BOOTS));
    }

    public void helmetcolor(LivingEntity e) {
        int[] array_colors = new int[]{12228503, 0x855A5A, 6897985, 0x5C3333, 0x522C2C};
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy.buildColorStack(array_colors[4])), 1L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy.buildColorStack(array_colors[3])), 20L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy.buildColorStack(array_colors[2])), 30L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy.buildColorStack(array_colors[1])), 40L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy.buildColorStack(array_colors[0])), 60L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy.b(15249075, Material.LEATHER_HELMET)), 60L);
    }

    public void t(LivingEntity e) {
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 55.5, 266.5, 180.0f, 0.0f)), 1L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 57.5, 266.5, 180.0f, 0.0f)), 20L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 59.5, 266.5, 180.0f, 0.0f)), 40L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 61.5, 266.5, 180.0f, 0.0f)), 60L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 63.5, 266.5, 180.0f, 0.0f)), 80L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 65.5, 266.5, 180.0f, 0.0f)), 100L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 67.5, 266.5, 180.0f, 0.0f)), 120L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 69.5, 266.5, 180.0f, 0.0f)), 140L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 70.5, 266.5, 180.0f, 0.0f)), 160L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 70.75, 266.5, 180.0f, 0.0f)), 180L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 70.5, 266.5, 180.0f, 0.0f)), 181L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 70.25, 266.5, 180.0f, 0.0f)), 182L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 70.0, 266.5, 180.0f, 0.0f)), 183L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 69.75, 266.5, 180.0f, 0.0f)), 185L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 69.5, 266.5, 180.0f, 0.0f)), 186L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 69.25, 266.5, 180.0f, 0.0f)), 187L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.5, 69.0, 266.5, 180.0f, 0.0f)), 188L);
        SUtil.delay(() -> this.e(e), 200L);
        SUtil.delay(() -> this.r(e), 205L);
    }

    public void e(LivingEntity e) {
        Location l = e.getLocation();
        l.setYaw(60.0f);
        e.teleport(l);
    }

    public void r(final LivingEntity e) {
        final Location l = e.getLocation();
        l.setYaw(0.0f);
        new BukkitRunnable(){

            public void run() {
                Vector teleportTo = l.getDirection().normalize().multiply(1);
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (String.valueOf(e.getLocation().getX()).contains("191") && String.valueOf(e.getLocation().getZ()).contains("285")) {
                    SadanHuman.SadanReach.put(e.getWorld().getUID(), true);
                    this.cancel();
                    return;
                }
                e.teleport(e.getLocation().add(teleportTo).multiply(1.0));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 3L);
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
        return 0.0;
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
}

