/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Color
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.FallingBlock
 *  org.bukkit.entity.Giant
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
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
package vn.giakhanhvn.skysim.entity.nms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
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
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.BlockFallAPI;
import vn.giakhanhvn.skysim.util.BossBar;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class Giant
extends BaseZombie {
    private static LivingEntity e;
    private boolean laserActiveCD = true;
    private boolean laserActive = false;
    private boolean shockWave = false;
    private boolean shockWaveCD = true;
    private boolean terToss = false;
    private boolean terTossCD = true;
    private boolean swordActiv = false;
    private boolean swordSlamCD = true;
    private BossBar bb;

    @Override
    public String getEntityName() {
        return Sputnik.trans("&4&lTerrorant");
    }

    @Override
    public double getEntityMaxHealth() {
        return 2.0E9;
    }

    @Override
    public double getDamageDealt() {
        return 1000000.0;
    }

    public BossBar setBar(World w, String s) {
        this.bb = new BossBar(Sputnik.trans(s));
        for (Player p : w.getPlayers()) {
            this.bb.addPlayer(p);
        }
        return this.bb;
    }

    public void removeAllBar(World w, BossBar b) {
        for (Player p : w.getPlayers()) {
            b.removePlayer(p);
        }
    }

    public void updateBar(double percent) {
        this.bb.setProgress(percent);
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        e = entity;
        final BossBar boss = this.setBar(entity.getWorld(), "&4&lTerrorant");
        ((CraftZombie)entity).setBaby(false);
        SUtil.delay(() -> {
            this.shockWaveCD = false;
        }, 400L);
        SUtil.delay(() -> {
            this.terTossCD = false;
        }, 200L);
        SUtil.delay(() -> {
            this.laserActiveCD = false;
        }, 300L);
        SUtil.delay(() -> {
            this.swordSlamCD = false;
        }, 100L);
        entity.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)));
        Sputnik.applyPacketGiant((Entity)entity);
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 60);
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("highername", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        new BukkitRunnable(){

            public void run() {
                if (entity.getHealth() > 0.0) {
                    Giant.this.updateBar(entity.getHealth() / entity.getMaxHealth());
                } else {
                    Giant.this.updateBar(9.990009990009992E-4);
                }
                CraftLivingEntity target = ((CraftZombie)entity).getTarget();
                if (entity.isDead()) {
                    SUtil.delay(() -> Giant.this.removeAllBar(entity.getWorld(), boss), 250L);
                }
                if (!Giant.this.laserActiveCD && !Giant.this.laserActive && SUtil.random(1, 120) <= 6 && target != null) {
                    Giant.this.laserActiveCD = true;
                    Giant.this.laserActive = true;
                    Giant.this.laser(entity);
                }
                if (!(Giant.this.swordSlamCD || Giant.this.swordActiv || Giant.this.shockWave || SUtil.random(1, 140) > 7 || target == null)) {
                    Giant.this.swordActiv = true;
                    Giant.this.swordSlamCD = true;
                    Giant.this.swordSlamAC(entity, (LivingEntity)target);
                }
                if (!(Giant.this.shockWave || Giant.this.shockWaveCD || SUtil.random(1, 100) > 5 || Giant.this.swordActiv)) {
                    Giant.this.shockWaveCD = true;
                    Giant.this.shockWave = true;
                    Vector vec = new Vector(0, 0, 0);
                    vec.setY(2);
                    e.setVelocity(vec);
                    SUtil.delay(() -> Giant.this.jumpAni(entity), 10L);
                }
                if (!Giant.this.terToss && !Giant.this.terTossCD && SUtil.random(1, 150) <= 4) {
                    Giant.this.terTossCD = true;
                    Giant.this.terToss = true;
                    SUtil.delay(() -> Giant.this.terToss = false, 300L);
                    e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 0));
                    Giant.this.launchTerrain(entity);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)), Giant.b(15249075, Material.LEATHER_HELMET), Giant.b(0xCF0000, Material.LEATHER_CHESTPLATE), Giant.c(Material.DIAMOND_LEGGINGS), Giant.b(0xE6E6E6, Material.LEATHER_BOOTS));
    }

    @Override
    public void onDeath(SEntity sEntity, Entity killed, Entity damager) {
        int place;
        StringBuilder message = new StringBuilder();
        message.append(ChatColor.GREEN).append(ChatColor.BOLD).append("\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\n");
        message.append(ChatColor.GOLD).append(ChatColor.BOLD).append("                 ").append(sEntity.getStatistics().getEntityName().toUpperCase()).append(" DOWN!\n \n");
        ArrayList<Map.Entry<UUID, Double>> damageDealt = new ArrayList<Map.Entry<UUID, Double>>(sEntity.getDamageDealt().entrySet());
        damageDealt.sort(Map.Entry.comparingByValue());
        Collections.reverse(damageDealt);
        String name = null;
        if (damager instanceof Player) {
            name = damager.getName();
        }
        if (damager instanceof Arrow && ((Arrow)damager).getShooter() instanceof Player) {
            name = ((Player)((Arrow)damager).getShooter()).getName();
        }
        if (name != null) {
            message.append("            ").append(ChatColor.GREEN).append(name).append(ChatColor.GRAY).append(" dealt the final blow.\n \n");
        }
        for (int i = 0; i < Math.min(3, damageDealt.size()); ++i) {
            message.append("\n");
            Map.Entry d = (Map.Entry)damageDealt.get(i);
            place = i + 1;
            switch (place) {
                case 1: {
                    message.append("        ").append(ChatColor.YELLOW);
                    break;
                }
                case 2: {
                    message.append("        ").append(ChatColor.GOLD);
                    break;
                }
                case 3: {
                    message.append("        ").append(ChatColor.RED);
                }
            }
            message.append(ChatColor.BOLD).append(place);
            switch (place) {
                case 1: {
                    message.append("st");
                    break;
                }
                case 2: {
                    message.append("nd");
                    break;
                }
                case 3: {
                    message.append("rd");
                }
            }
            message.append(" Damager").append(ChatColor.RESET).append(ChatColor.GRAY).append(" - ").append(ChatColor.GREEN).append(Bukkit.getOfflinePlayer((UUID)((UUID)d.getKey())).getName()).append(ChatColor.GRAY).append(" - ").append(ChatColor.YELLOW).append(SUtil.commaify(((Double)d.getValue()).intValue()));
        }
        message.append("\n \n").append("         ").append(ChatColor.RESET).append(ChatColor.YELLOW).append("Your Damage: ").append("%s").append(ChatColor.RESET).append("\n").append("             ").append(ChatColor.YELLOW).append("Runecrafting Experience: ").append(ChatColor.LIGHT_PURPLE).append("N/A").append(ChatColor.RESET).append("\n \n");
        message.append(ChatColor.GREEN).append(ChatColor.BOLD).append("\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
        for (Player player : Bukkit.getOnlinePlayers()) {
            place = -1;
            int damage = 0;
            for (int i = 0; i < damageDealt.size(); ++i) {
                Map.Entry d = (Map.Entry)damageDealt.get(i);
                if (!((UUID)d.getKey()).equals(player.getUniqueId())) continue;
                place = i + 1;
                damage = ((Double)d.getValue()).intValue();
            }
            if (!player.getWorld().getName().equals("gisland")) continue;
            player.sendMessage(String.format(message.toString(), place != -1 ? ChatColor.GREEN + SUtil.commaify(damage) + ChatColor.GRAY + " (Position #" + place + ")" : ChatColor.RED + "N/A" + ChatColor.GRAY + " (Position #N/A)"));
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
        return 0.3;
    }

    public void laser(LivingEntity e) {
        int[] array_colors = new int[]{15249075, 15178658, 14907008, 14634331, 14563143};
        Giant.applyEffect(PotionEffectType.SLOW, (Entity)e, 240, 1);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.buildColorStack(array_colors[0])), 20L);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.buildColorStack(array_colors[1])), 40L);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.buildColorStack(array_colors[2])), 60L);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.buildColorStack(array_colors[3])), 80L);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.buildColorStack(array_colors[4])), 100L);
        SUtil.delay(() -> this.laserAni(e), 105L);
        SUtil.delay(() -> {
            this.laserActive = false;
        }, 250L);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.buildColorStack(array_colors[4])), 270L);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.buildColorStack(array_colors[3])), 290L);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.buildColorStack(array_colors[2])), 310L);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.buildColorStack(array_colors[1])), 330L);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.buildColorStack(array_colors[0])), 350L);
        SUtil.delay(() -> e.getEquipment().setHelmet(Giant.b(15249075, Material.LEATHER_HELMET)), 370L);
        SUtil.delay(() -> {
            this.laserActiveCD = false;
        }, 950L);
    }

    public void jumpAni(final LivingEntity e) {
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (!Giant.this.shockWave) {
                    this.cancel();
                    return;
                }
                if (e.isOnGround()) {
                    Giant.this.shockWave = false;
                    SUtil.delay(() -> Giant.this.shockWaveCD = false, 500L);
                    e.getWorld().playEffect(e.getLocation().add(0.0, 0.5, 0.0), Effect.EXPLOSION_HUGE, 3);
                    e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION_HUGE, 3);
                    e.getWorld().playSound(e.getLocation(), Sound.EXPLODE, 3.0f, 0.0f);
                    SUtil.delay(() -> e.getWorld().playSound(e.getLocation(), Sound.EXPLODE, 10.0f, 0.0f), 5L);
                    SUtil.delay(() -> e.getWorld().playEffect(e.getLocation().add(0.0, 0.5, 0.0), Effect.EXPLOSION_HUGE, 3), 5L);
                    SUtil.delay(() -> e.getWorld().playEffect(e.getLocation().add(0.0, 0.5, 0.0), Effect.EXPLOSION_HUGE, 3), 7L);
                    Giant.createBlockTornado((Entity)e, e.getLocation().add(0.0, -1.0, 0.0).getBlock().getType(), e.getLocation().add(0.0, -1.0, 0.0).getBlock().getData());
                    new BukkitRunnable(){

                        public void run() {
                            Giant.createBlockTornado((Entity)e, e.getLocation().add(0.0, -1.0, 0.0).getBlock().getType(), e.getLocation().add(0.0, -1.0, 0.0).getBlock().getData());
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 5L);
                    new BukkitRunnable(){

                        public void run() {
                            Giant.createBlockTornado((Entity)e, e.getLocation().add(0.0, -2.0, 0.0).getBlock().getType(), e.getLocation().add(0.0, -2.0, 0.0).getBlock().getData());
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 6L);
                    new BukkitRunnable(){

                        public void run() {
                            Giant.createBlockTornado((Entity)e, e.getLocation().add(0.0, -2.0, 0.0).getBlock().getType(), e.getLocation().add(0.0, -2.0, 0.0).getBlock().getData());
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 7L);
                    new BukkitRunnable(){

                        public void run() {
                            Giant.createBlockTornado((Entity)e, e.getLocation().add(0.0, -1.0, 0.0).getBlock().getType(), e.getLocation().add(0.0, -1.0, 0.0).getBlock().getData());
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 8L);
                    SUtil.delay(() -> e.getWorld().playSound(e.getLocation(), Sound.EXPLODE, 3.0f, 0.0f), 5L);
                    SUtil.delay(() -> e.getWorld().playSound(e.getLocation(), Sound.EXPLODE, 3.0f, 0.0f), 10L);
                    for (Entity entities : e.getNearbyEntities(22.0, 10.0, 22.0)) {
                        double damage;
                        Player p;
                        Vector vec = new Vector(0, 0, 0);
                        if (entities.hasMetadata("NPC") || entities instanceof ArmorStand || entities instanceof org.bukkit.entity.Giant) continue;
                        if (entities.getLocation().distance(e.getLocation()) > 8.0) {
                            vec.setY(2.25);
                            vec.setX(0.25);
                            entities.setVelocity(vec);
                        } else {
                            vec.setY(2.5);
                            vec.setX(0.5);
                            entities.setVelocity(vec);
                        }
                        if (!(entities instanceof Player)) continue;
                        if (entities.getLocation().distance(e.getLocation()) <= 5.0) {
                            p = (Player)entities;
                            damage = (double)SUtil.random(100, 300) + p.getMaxHealth() * 90.0 / 100.0;
                            User.getUser(p.getUniqueId()).damage(damage, EntityDamageEvent.DamageCause.ENTITY_ATTACK, (Entity)e);
                            p.damage(1.0E-6, null);
                            p.sendMessage(Sputnik.trans("&7Terrorant's Shockwave Stomp have hit you for &c" + SUtil.commaify(Math.round(damage)) + " &7true damage."));
                            continue;
                        }
                        p = (Player)entities;
                        damage = (double)SUtil.random(100, 300) + p.getMaxHealth() * 10.0 / 100.0;
                        User.getUser(p.getUniqueId()).damage(damage, EntityDamageEvent.DamageCause.ENTITY_ATTACK, (Entity)e);
                        p.damage(1.0E-6, null);
                        p.sendMessage(Sputnik.trans("&7Terrorant's Shockwave Stomp have hit you for &c" + SUtil.commaify(Math.round(damage)) + " &7true damage."));
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void laserAni(final LivingEntity e) {
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (!Giant.this.laserActive) {
                    this.cancel();
                    return;
                }
                for (Entity p : e.getNearbyEntities(20.0, 20.0, 20.0)) {
                    if (!(p instanceof Player)) continue;
                    Player p1 = (Player)p;
                    p1.playSound(e.getLocation(), "mob.guardian.elder.idle", 0.3f, 2.0f);
                    p1.playSound(e.getLocation(), "mob.guardian.elder.idle", 0.3f, 0.0f);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (!Giant.this.laserActive) {
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
                    if (target.getLocation().distance(e.getLocation()) < 5.0 || target.getLocation().distance(e.getLocation()) > 30.0) {
                        return;
                    }
                    Location loc1_ = target.getLocation();
                    Location loc2_ = target.getLocation();
                    Location en1 = loc1_.add(0.0, 0.5, 0.0);
                    Location en2 = loc2_.add(0.0, 0.5, 0.0);
                    Giant.drawLine(loc1, en1, 0.0);
                    Giant.drawLine(loc2, en2, 0.0);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 5L);
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (!Giant.this.laserActive) {
                    this.cancel();
                    return;
                }
                for (Entity entity : e.getNearbyEntities(4.0, 10.0, 4.0)) {
                    if (!(entity instanceof Player)) continue;
                    double damage = (double)SUtil.random(100, 150) + ((LivingEntity)entity).getMaxHealth() * 5.0 / 100.0;
                    User.getUser(entity.getUniqueId()).damage(damage, EntityDamageEvent.DamageCause.ENTITY_ATTACK, (Entity)e);
                    ((Player)entity).sendMessage(Sputnik.trans("&7Terrorant's Laser Heat have hit you for &c" + SUtil.commaify(Math.round(damage)) + " &7true damage! Move away from it!"));
                    ((LivingEntity)entity).damage(1.0E-6, null);
                }
                CraftLivingEntity target = ((CraftZombie)e).getTarget();
                float angle_1 = e.getEyeLocation().getYaw() / 60.0f;
                Location loc1 = e.getEyeLocation().add(Math.cos(angle_1), 0.0, Math.sin(angle_1));
                Location loc2 = e.getEyeLocation().subtract(Math.cos(angle_1), 0.0, Math.sin(angle_1));
                loc1.add(0.0, 9.5, 0.0);
                loc2.add(0.0, 9.5, 0.0);
                if (target != null) {
                    if (target.getLocation().distance(e.getLocation()) < 5.0 || target.getLocation().distance(e.getLocation()) > 30.0) {
                        return;
                    }
                    Location loc1_ = target.getLocation();
                    Location loc2_ = target.getLocation();
                    Location en1 = loc1_.add(0.0, 0.5, 0.0);
                    Location en2 = loc2_.add(0.0, 0.5, 0.0);
                    Giant.getEntity(loc1, en1, e);
                    Giant.getEntity(loc2, en2, e);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 20L);
    }

    public void launchTerrain(final LivingEntity e) {
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (!Giant.this.terToss) {
                    SUtil.delay(() -> Giant.this.terTossCD = false, 550L);
                    this.cancel();
                    return;
                }
                CraftLivingEntity t = ((CraftZombie)e).getTarget();
                if (t != null) {
                    Giant.this.throwTerrain(e, (Entity)t);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 30L);
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
                double damage = (double)SUtil.random(200, 700) + p.getMaxHealth() * 1.0 / 100.0;
                User.getUser(p.getUniqueId()).damage(damage, EntityDamageEvent.DamageCause.ENTITY_ATTACK, (Entity)e);
                p.damage(1.0E-6, null);
                p.sendMessage(Sputnik.trans("&7Terrorant's Laser Eye have hit you for &c" + SUtil.commaify(Math.round(damage)) + " &7true damage."));
                return;
            }
        }
    }

    public static void applyEffect(PotionEffectType e, Entity en, int ticks, int amp) {
        ((LivingEntity)en).addPotionEffect(new PotionEffect(e, ticks, amp));
    }

    public static void createBlockTornado(Entity e, Material mat, byte id) {
        for (int i = 0; i <= 30; ++i) {
            int random = SUtil.random(0, 3);
            double range = 0.0;
            Location loc = e.getLocation().clone();
            loc.setYaw((float)SUtil.random(0, 360));
            if (random == 1) {
                range = 0.6;
            }
            if (random == 2) {
                range = 0.7;
            }
            if (random == 3) {
                range = 0.8;
            }
            Vector vec = loc.getDirection().normalize().multiply(range);
            vec.setY(1.1);
            BlockFallAPI.sendVelocityBlock(e.getLocation(), mat, id, e.getWorld(), 70, vec);
        }
    }

    public static void damagePlayer(Player p) {
        double damage = (double)SUtil.random(200, 700) + p.getMaxHealth() * 25.0 / 100.0;
        User.getUser(p.getUniqueId()).damage(damage, EntityDamageEvent.DamageCause.ENTITY_ATTACK, (Entity)e);
        p.damage(1.0E-6, null);
        p.sendMessage(Sputnik.trans("&7Terrorant's Terrain Toss have hit you for &c" + SUtil.commaify(Math.round(damage)) + " &7true damage."));
    }

    public void throwTerrain(LivingEntity e, Entity target) {
        Block b = target.getLocation().getBlock();
        World world = e.getWorld();
        Location startBlock = e.getLocation().add(e.getLocation().getDirection().multiply(2));
        ArrayList<Location> locationList = new ArrayList<Location>();
        ArrayList<Location> endList = new ArrayList<Location>();
        ArrayList blockTypes = new ArrayList();
        ArrayList launchTypes = new ArrayList();
        for (int length = -1; length < 2; ++length) {
            for (int height = -1; height < 2; ++height) {
                Location loc = startBlock.clone().add((double)length, 0.0, (double)height);
                Location end = b.getLocation().clone().add((double)length, 0.0, (double)height);
                locationList.add(loc);
                endList.add(end);
            }
        }
        locationList.add(startBlock.clone().add(0.0, 0.0, 2.0));
        locationList.add(startBlock.clone().add(0.0, 0.0, -2.0));
        locationList.add(startBlock.clone().add(2.0, 0.0, 0.0));
        locationList.add(startBlock.clone().add(-2.0, 0.0, 0.0));
        endList.add(b.getLocation().clone().add(0.0, 0.0, 2.0));
        endList.add(b.getLocation().clone().add(0.0, 0.0, -2.0));
        endList.add(b.getLocation().clone().add(2.0, 0.0, 0.0));
        endList.add(b.getLocation().clone().add(-2.0, 0.0, 0.0));
        locationList.add(startBlock.clone().add(0.0, -1.0, 0.0));
        locationList.add(startBlock.clone().add(1.0, -1.0, 0.0));
        locationList.add(startBlock.clone().add(-1.0, -1.0, 0.0));
        locationList.add(startBlock.clone().add(0.0, -1.0, 1.0));
        locationList.add(startBlock.clone().add(0.0, -1.0, -1.0));
        endList.add(b.getLocation().clone().add(0.0, -1.0, 0.0));
        endList.add(b.getLocation().clone().add(1.0, -1.0, 0.0));
        endList.add(b.getLocation().clone().add(-1.0, -1.0, 0.0));
        endList.add(b.getLocation().clone().add(0.0, -1.0, 1.0));
        endList.add(b.getLocation().clone().add(0.0, -1.0, -1.0));
        Byte blockData = 0;
        locationList.forEach(block -> {
            Location loc = block.getBlock().getLocation().clone().subtract(0.0, 1.0, 0.0);
            Material mat = loc.getBlock().getType();
            if (mat == Material.AIR) {
                mat = Material.STONE;
            }
            launchTypes.add(mat);
            blockTypes.add(block.getBlock().getType());
        });
        locationList.forEach(location -> {
            Material material = (Material)launchTypes.get(locationList.indexOf(location));
            Location origin = location.clone().add(0.0, 7.0, 0.0);
            int pos = locationList.indexOf(location);
            Location endPos = (Location)endList.get(pos);
            FallingBlock block = world.spawnFallingBlock(origin, material, blockData.byteValue());
            block.setDropItem(false);
            block.setMetadata("t", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
            block.setVelocity(Sputnik.calculateVelocityBlock(origin.toVector(), endPos.toVector(), 3));
        });
    }

    public static void playLaserSound(final Player p, final Entity e) {
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                p.playSound(p.getLocation(), "mob.guardian.elder.idle", 0.3f, 2.0f);
                p.playSound(p.getLocation(), "mob.guardian.elder.idle", 0.3f, 0.0f);
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void swordSlamAC(LivingEntity e, LivingEntity tar) {
        Giant.applyEffect(PotionEffectType.SLOW, (Entity)e, 60, 4);
        SUtil.delay(() -> this.swordSlamF(e, tar), 60L);
    }

    public void swordSlamF(LivingEntity e, LivingEntity tar) {
        Vector vec = new Vector(0, 0, 0);
        vec.setY(2);
        e.setVelocity(vec);
        SUtil.delay(() -> this.swordSlam(e, tar), 30L);
    }

    public void swordSlam(final LivingEntity e, final LivingEntity player) {
        e.getEquipment().setItemInHand(null);
        final org.bukkit.entity.Giant armorStand = (org.bukkit.entity.Giant)player.getWorld().spawn(e.getLocation().add(0.0, 5.0, 0.0), org.bukkit.entity.Giant.class);
        armorStand.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)));
        Sputnik.applyPacketGiant((Entity)armorStand);
        armorStand.setCustomName("Dinnerbone");
        armorStand.setMetadata("GiantSword", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        armorStand.setMetadata("NoAffect", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        EntityManager.Woosh((LivingEntity)armorStand);
        EntityManager.noHit((Entity)armorStand);
        EntityManager.shutTheFuckUp((Entity)armorStand);
        Location firstLocation = e.getLocation().add(0.0, 5.0, 0.0);
        Location secondLocation = player.getLocation();
        Vector from = firstLocation.toVector();
        Vector to = secondLocation.toVector();
        Vector direction = to.subtract(from);
        direction.normalize();
        direction.multiply(3);
        armorStand.setVelocity(direction);
        new BukkitRunnable(){

            public void run() {
                if (!Giant.this.swordActiv) {
                    this.cancel();
                    return;
                }
                if (armorStand.isOnGround()) {
                    Giant.this.swordActiv = false;
                    SUtil.delay(() -> Giant.this.swordSlamCD = false, 450L);
                    armorStand.remove();
                    org.bukkit.entity.Giant sword = (org.bukkit.entity.Giant)e.getWorld().spawnEntity(armorStand.getLocation(), EntityType.GIANT);
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
                    for (Entity entities : sword.getWorld().getNearbyEntities(sword.getLocation().add(sword.getLocation().getDirection().multiply(3)), 4.0, 4.0, 4.0)) {
                        double damage;
                        Player p;
                        if (entities.hasMetadata("NPC") || entities instanceof ArmorStand || entities instanceof org.bukkit.entity.Giant || !(entities instanceof Player)) continue;
                        if (entities.getLocation().add(sword.getLocation().getDirection().multiply(3)).distance(sword.getLocation()) > 1.0) {
                            p = (Player)entities;
                            damage = (double)SUtil.random(100, 300) + p.getMaxHealth() * 35.0 / 100.0;
                            User.getUser(p.getUniqueId()).damage(damage, EntityDamageEvent.DamageCause.ENTITY_ATTACK, (Entity)e);
                            p.damage(1.0E-6, null);
                            p.sendMessage(Sputnik.trans("&7Terrorant's &d&lSword Slam&7 have hit you for &c" + SUtil.commaify(Math.round(damage)) + " &7true damage."));
                            continue;
                        }
                        p = (Player)entities;
                        damage = (double)SUtil.random(100, 300) + p.getMaxHealth() * 90.0 / 100.0;
                        User.getUser(p.getUniqueId()).damage(damage, EntityDamageEvent.DamageCause.ENTITY_ATTACK, (Entity)e);
                        p.damage(1.0E-6, null);
                        p.sendMessage(Sputnik.trans("&7Terrorant's &d&lSword Slam&7 have hit you for &c" + SUtil.commaify(Math.round(damage)) + " &7true damage."));
                    }
                    SUtil.delay(() -> sword.remove(), 65L);
                    SUtil.delay(() -> stand.remove(), 65L);
                    SUtil.delay(() -> e.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD))), 60L);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }
}

