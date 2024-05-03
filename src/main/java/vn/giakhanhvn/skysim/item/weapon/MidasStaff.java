/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.item.weapon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.util.BlockFallAPI;
import vn.giakhanhvn.skysim.util.SLog;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;
import vn.giakhanhvn.skysim.util.SputnikPlayer;

public class MidasStaff
implements ToolStatistics,
MaterialFunction,
Ability {
    @Override
    public String getAbilityName() {
        return "Molten Wave";
    }

    @Override
    public String getAbilityDescription() {
        return Sputnik.trans("&7Cast a wave of molten gold in the direction you are facing! Deals up to &c32,000 &7damage.");
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 20;
    }

    @Override
    public String getLore() {
        return Sputnik.trans("The &3ability damage bonus &7of this item is dependent on the price paid in the &5Dark &5Auction&7! The maximum bonus of this item is &326000 &7if the bid was &6100,000,000 Coins &7or higher!");
    }

    @Override
    public void onAbilityUse(final Player player, final SItem sItem) {
        Location location = player.getLocation();
        location.setPitch(0.0f);
        ArmorStand stand = (ArmorStand)player.getWorld().spawn(location.add(0.0, 2.0, 0.0), ArmorStand.class);
        stand.setVisible(false);
        stand.setGravity(false);
        stand.setMarker(true);
        stand.setMetadata("GoldMol", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        ArmorStand stand1 = (ArmorStand)player.getWorld().spawn(stand.getLocation().add(stand.getLocation().getDirection().multiply(1)), ArmorStand.class);
        stand1.setVisible(false);
        stand1.setGravity(false);
        stand1.setMarker(true);
        stand1.setMetadata("GoldMol", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        ArmorStand stand2 = (ArmorStand)player.getWorld().spawn(stand1.getLocation().add(stand1.getLocation().getDirection().multiply(1)), ArmorStand.class);
        stand2.setVisible(false);
        stand2.setGravity(false);
        stand2.setMarker(true);
        stand2.setMetadata("GoldMol", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        ArmorStand stand3 = (ArmorStand)player.getWorld().spawn(stand2.getLocation().add(stand2.getLocation().getDirection().multiply(1)), ArmorStand.class);
        stand3.setVisible(false);
        stand3.setGravity(false);
        stand3.setMetadata("GoldMol", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        ArmorStand stand4 = (ArmorStand)player.getWorld().spawn(stand3.getLocation().add(stand3.getLocation().getDirection().multiply(1)), ArmorStand.class);
        stand4.setVisible(false);
        stand4.setGravity(false);
        stand4.setMetadata("GoldMol", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        ArmorStand stand5 = (ArmorStand)player.getWorld().spawn(stand4.getLocation().add(stand4.getLocation().getDirection().multiply(1)), ArmorStand.class);
        stand5.setVisible(false);
        stand5.setGravity(false);
        stand5.setMetadata("GoldMol", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        ArmorStand stand6 = (ArmorStand)player.getWorld().spawn(stand5.getLocation().add(stand5.getLocation().getDirection().multiply(1)), ArmorStand.class);
        stand6.setVisible(false);
        stand6.setGravity(false);
        stand6.setMetadata("GoldMol", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        ArmorStand stand7 = (ArmorStand)player.getWorld().spawn(stand6.getLocation().add(stand6.getLocation().getDirection().multiply(1)), ArmorStand.class);
        stand7.setVisible(false);
        stand7.setGravity(false);
        stand7.setMetadata("GoldMol", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        ArmorStand stand8 = (ArmorStand)player.getWorld().spawn(stand7.getLocation().add(stand7.getLocation().getDirection().multiply(1)), ArmorStand.class);
        stand8.setVisible(false);
        stand8.setGravity(false);
        stand8.setMetadata("GoldMol", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        ArmorStand stand9 = (ArmorStand)player.getWorld().spawn(stand8.getLocation().add(stand8.getLocation().getDirection().multiply(1)), ArmorStand.class);
        stand9.setVisible(false);
        stand9.setGravity(false);
        stand9.setMetadata("GoldMol", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        SUtil.delay(() -> BlockFallAPI.sendBlock(stand1.getLocation(), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 3L);
        float angle = stand1.getEyeLocation().getYaw() / 60.0f;
        Location loc1_1 = stand1.getEyeLocation().add(Math.cos(angle), 0.0, Math.sin(angle));
        Location loc2_1 = stand1.getEyeLocation().subtract(Math.cos(angle), 0.0, Math.sin(angle));
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc1_1.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 3L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc2_1.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 3L);
        SUtil.delay(() -> Sputnik.midasFlame((Entity)stand1), 3L);
        SUtil.delay(() -> player.getWorld().playSound(stand1.getLocation(), Sound.DIG_GRASS, 1.0f, 0.5f), 3L);
        SUtil.delay(() -> stand1.remove(), 3L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(stand2.getLocation(), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 6L);
        float angle_1 = stand2.getEyeLocation().getYaw() / 60.0f;
        Location loc1_2 = stand2.getEyeLocation().add(Math.cos(angle_1), 0.0, Math.sin(angle_1));
        Location loc2_2 = stand2.getEyeLocation().subtract(Math.cos(angle_1), 0.0, Math.sin(angle_1));
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc1_2.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 6L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc2_2.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 6L);
        SUtil.delay(() -> Sputnik.midasCalcDamage((Entity)stand2, player, sItem), 6L);
        SUtil.delay(() -> Sputnik.midasFlame((Entity)stand2), 6L);
        SUtil.delay(() -> player.getWorld().playSound(stand2.getLocation(), Sound.DIG_GRASS, 1.0f, 0.5f), 6L);
        SUtil.delay(() -> stand2.remove(), 6L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(stand3.getLocation(), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 9L);
        float angle_2 = stand3.getEyeLocation().getYaw() / 60.0f;
        Location loc1_3 = stand3.getEyeLocation().add(Math.cos(angle_2), 0.0, Math.sin(angle_2));
        Location loc2_3 = stand3.getEyeLocation().subtract(Math.cos(angle_2), 0.0, Math.sin(angle_2));
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc1_3.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 9L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc2_3.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 9L);
        SUtil.delay(() -> player.getWorld().playSound(stand3.getLocation(), Sound.DIG_GRASS, 1.0f, 0.5f), 9L);
        SUtil.delay(() -> stand3.remove(), 9L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(stand4.getLocation(), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 11L);
        float angle_3 = stand4.getEyeLocation().getYaw() / 60.0f;
        Location loc1_4 = stand4.getEyeLocation().add(Math.cos(angle_3), 0.0, Math.sin(angle_3));
        Location loc2_4 = stand4.getEyeLocation().subtract(Math.cos(angle_3), 0.0, Math.sin(angle_3));
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc1_4.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 11L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc2_4.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 11L);
        SUtil.delay(() -> Sputnik.midasFlame((Entity)stand4), 11L);
        SUtil.delay(() -> player.getWorld().playSound(stand4.getLocation(), Sound.DIG_GRASS, 1.0f, 0.5f), 11L);
        SUtil.delay(() -> stand4.remove(), 11L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(stand5.getLocation(), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 14L);
        float angle_4 = stand5.getEyeLocation().getYaw() / 60.0f;
        Location loc1_5 = stand5.getEyeLocation().add(Math.cos(angle_4), 0.0, Math.sin(angle_4));
        Location loc2_5 = stand5.getEyeLocation().subtract(Math.cos(angle_4), 0.0, Math.sin(angle_4));
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc1_5.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 14L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc2_5.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 14L);
        SUtil.delay(() -> Sputnik.midasCalcDamage((Entity)stand5, player, sItem), 14L);
        SUtil.delay(() -> player.getWorld().playSound(stand5.getLocation(), Sound.DIG_GRASS, 1.0f, 0.5f), 14L);
        SUtil.delay(() -> stand5.remove(), 14L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(stand6.getLocation(), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 17L);
        float angle_5 = stand6.getEyeLocation().getYaw() / 60.0f;
        Location loc1_6 = stand6.getEyeLocation().add(Math.cos(angle_5), 0.0, Math.sin(angle_5));
        Location loc2_6 = stand6.getEyeLocation().subtract(Math.cos(angle_5), 0.0, Math.sin(angle_5));
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc1_6.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 17L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc2_6.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 17L);
        SUtil.delay(() -> Sputnik.midasFlame((Entity)stand6), 17L);
        SUtil.delay(() -> player.getWorld().playSound(stand6.getLocation(), Sound.DIG_GRASS, 1.0f, 0.5f), 17L);
        SUtil.delay(() -> stand6.remove(), 17L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(stand7.getLocation(), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 20L);
        float angle_6 = stand7.getEyeLocation().getYaw() / 60.0f;
        Location loc1_7 = stand7.getEyeLocation().add(Math.cos(angle_6), 0.0, Math.sin(angle_6));
        Location loc2_7 = stand7.getEyeLocation().subtract(Math.cos(angle_6), 0.0, Math.sin(angle_6));
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc1_7.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 20L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc2_7.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 20L);
        SUtil.delay(() -> player.getWorld().playSound(stand7.getLocation(), Sound.DIG_GRASS, 1.0f, 0.5f), 20L);
        SUtil.delay(() -> stand7.remove(), 20L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(stand8.getLocation(), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 23L);
        float angle_7 = stand8.getEyeLocation().getYaw() / 60.0f;
        Location loc1_8 = stand8.getEyeLocation().add(Math.cos(angle_7), 0.0, Math.sin(angle_7));
        Location loc2_8 = stand8.getEyeLocation().subtract(Math.cos(angle_7), 0.0, Math.sin(angle_7));
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc1_8.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 23L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc2_8.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 23L);
        SUtil.delay(() -> Sputnik.midasCalcDamage((Entity)stand8, player, sItem), 23L);
        SUtil.delay(() -> Sputnik.midasFlame((Entity)stand8), 23L);
        SUtil.delay(() -> player.getWorld().playSound(stand8.getLocation(), Sound.DIG_GRASS, 1.0f, 0.5f), 23L);
        SUtil.delay(() -> stand8.remove(), 23L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(stand9.getLocation(), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 26L);
        float angle_8 = stand9.getEyeLocation().getYaw() / 60.0f;
        Location loc1_9 = stand9.getEyeLocation().add(Math.cos(angle_8), 0.0, Math.sin(angle_8));
        Location loc2_9 = stand9.getEyeLocation().subtract(Math.cos(angle_8), 0.0, Math.sin(angle_8));
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc1_9.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 26L);
        SUtil.delay(() -> BlockFallAPI.sendBlock(loc2_9.add(0.0, -1.7, 0.0), Material.GOLD_BLOCK, (byte)0, player.getWorld(), 15), 26L);
        SUtil.delay(() -> stand9.remove(), 26L);
        SUtil.delay(() -> stand.remove(), 10L);
        new BukkitRunnable(){

            public void run() {
                if (Sputnik.MidasStaff.containsKey(sItem) && Sputnik.MidasStaffDmg.containsKey(sItem)) {
                    if (PlayerUtils.Debugmsg.debugmsg) {
                        SLog.info("[DEBUG] " + player.getName() + " have dealt " + (float)Sputnik.MidasStaffDmg.get(sItem).longValue() / (float)Sputnik.MidasStaff.get(sItem).intValue() + " for each enemies and hit total of " + (long)Sputnik.MidasStaff.get(sItem).intValue() + " enemies, for a total of " + (double)Sputnik.MidasStaffDmg.get(sItem).longValue() + " damage! (Molten Wave Ability)");
                    }
                    if (Sputnik.MidasStaff.get(sItem) > 1) {
                        SputnikPlayer.sendTranslated(player, "&7Your Molten Wave hit&c " + String.valueOf(Sputnik.MidasStaff.get(sItem)) + " &7enemies for &c" + SUtil.commaify(Sputnik.MidasStaffDmg.get(sItem)) + " &7damage.");
                    } else if (Sputnik.MidasStaff.get(sItem) == 1) {
                        SputnikPlayer.sendTranslated(player, "&7Your Molten Wave hit&c " + String.valueOf(Sputnik.MidasStaff.get(sItem)) + " &7enemy for &c" + SUtil.commaify(Sputnik.MidasStaffDmg.get(sItem)) + " &7damage.");
                    }
                }
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 26L);
    }

    @Override
    public int getManaCost() {
        return 500;
    }

    @Override
    public String getDisplayName() {
        return "Midas Staff";
    }

    @Override
    public int getBaseDamage() {
        return 130;
    }

    @Override
    public double getBaseStrength() {
        return 150.0;
    }

    @Override
    public double getBaseIntelligence() {
        return 50.0;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.WEAPON;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.SWORD;
    }
}

