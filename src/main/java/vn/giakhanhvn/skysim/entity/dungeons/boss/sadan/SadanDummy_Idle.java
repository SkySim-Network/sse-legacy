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
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SadanDummy_Idle
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
        Location l = entity.getLocation();
        Sputnik.applyPacketGiant((Entity)entity);
        EntityManager.noAI((Entity)entity);
        EntityManager.noHit((Entity)entity);
        entity.setMetadata("GiantSword", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("NoAffect", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        EntityManager.shutTheFuckUp((Entity)entity);
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 100);
        l.setYaw(180.0f);
        entity.teleport(l);
        entity.setMetadata("notDisplay", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("dummy_r", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)), SadanDummy_Idle.b(0x522C2C, Material.LEATHER_HELMET), SadanDummy_Idle.b(14751108, Material.LEATHER_CHESTPLATE), SadanDummy_Idle.c(Material.DIAMOND_LEGGINGS), SadanDummy_Idle.b(8991025, Material.LEATHER_BOOTS));
    }

    public void laser(LivingEntity e) {
        int[] array_colors = new int[]{12100772, 12089721, 12080726, 0xBA3A3A, 12194322};
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy_Idle.buildColorStack(array_colors[4])), 270L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy_Idle.buildColorStack(array_colors[3])), 290L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy_Idle.buildColorStack(array_colors[2])), 310L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy_Idle.buildColorStack(array_colors[1])), 330L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy_Idle.buildColorStack(array_colors[0])), 350L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanDummy_Idle.b(15249075, Material.LEATHER_HELMET)), 370L);
    }

    public static void t(LivingEntity e) {
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.0, 54.0, 266.0)), 1L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.0, 56.0, 266.0)), 20L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.0, 58.0, 266.0)), 40L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.0, 60.0, 266.0)), 60L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.0, 62.0, 266.0)), 80L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.0, 64.0, 266.0)), 100L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.0, 66.0, 266.0)), 120L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.0, 68.0, 266.0)), 140L);
        SUtil.delay(() -> e.teleport(new Location(e.getWorld(), 191.0, 69.0, 266.0)), 160L);
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

