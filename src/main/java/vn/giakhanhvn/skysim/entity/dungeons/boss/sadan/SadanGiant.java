/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  net.minecraft.server.v1_8_R3.AttributeInstance
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.EntityLiving
 *  net.minecraft.server.v1_8_R3.EntityPlayer
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
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.FallingBlock
 *  org.bukkit.entity.Giant
 *  org.bukkit.entity.HumanEntity
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
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.dungeons.boss.sadan;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.ArrayList;
import net.minecraft.server.v1_8_R3.AttributeInstance;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityPlayer;
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
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Giant;
import org.bukkit.entity.HumanEntity;
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
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.DeadBodyMaker;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanFunction;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanHuman;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.GlobalBossBar;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.BlockFallAPI;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SadanGiant
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
    private GlobalBossBar bb;

    @Override
    public String getEntityName() {
        return Sputnik.trans("&c&lSadan");
    }

    @Override
    public double getEntityMaxHealth() {
        return 4.0E7;
    }

    @Override
    public double getDamageDealt() {
        return 55000.0;
    }

    public GlobalBossBar setBar(World w, String s) {
        this.bb = new GlobalBossBar(Sputnik.trans(s), w);
        for (Player p : w.getPlayers()) {
            this.bb.addPlayer(p);
        }
        return this.bb;
    }

    public void updateBar(double percent) {
        this.bb.setProgress(percent);
    }

    @Override
    public void onSpawn(final LivingEntity entity, SEntity sEntity) {
        if (entity.getWorld().getPlayers().size() == 0) {
            return;
        }
        GlobalBossBar boss = this.setBar(entity.getWorld(), Sputnik.trans("&c&lSadan"));
        e = entity;
        ((CraftZombie)entity).setBaby(false);
        AttributeInstance followRange = ((CraftLivingEntity)entity).getHandle().getAttributeInstance(GenericAttributes.FOLLOW_RANGE);
        followRange.setValue(500.0);
        Player p = (Player)entity.getWorld().getPlayers().get(SUtil.random(0, entity.getWorld().getPlayers().size() - 1));
        if (p != null && p.getGameMode() != GameMode.SPECTATOR && p.getGameMode() != GameMode.CREATIVE) {
            ((CraftZombie)entity).setTarget((LivingEntity)p);
        }
        SUtil.delay(() -> {
            this.shockWaveCD = false;
        }, 120L);
        SUtil.delay(() -> {
            this.terTossCD = false;
        }, 100L);
        SUtil.delay(() -> {
            this.laserActiveCD = false;
        }, 1L);
        SUtil.delay(() -> {
            this.swordSlamCD = false;
        }, 40L);
        entity.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)));
        Sputnik.applyPacketGiant((org.bukkit.entity.Entity)entity);
        EntityManager.DEFENSE_PERCENTAGE.put((org.bukkit.entity.Entity)entity, 55);
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("highername", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("Boss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("Giant_", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        new BukkitRunnable(){

            public void run() {
                CraftLivingEntity target = ((CraftZombie)entity).getTarget();
                if (entity.getHealth() > 0.0) {
                    SadanGiant.this.updateBar(entity.getHealth() / entity.getMaxHealth());
                } else {
                    SadanGiant.this.updateBar(9.990009990009992E-4);
                }
                if (entity.isDead()) {
                    SUtil.delay(() -> {
                        ArrayList<Player> plist = new ArrayList<Player>();
                        for (Player p : ((SadanGiant)SadanGiant.this).bb.players) {
                            plist.add(p);
                        }
                        plist.forEach(pl -> SadanGiant.this.bb.removePlayer((Player)pl));
                        SadanGiant.this.bb.setProgress(0.0);
                        SadanGiant.this.bb.cancel();
                    }, 250L);
                    this.cancel();
                    return;
                }
                if (!SadanGiant.this.laserActiveCD && !SadanGiant.this.laserActive && SUtil.random(1, 120) <= 6 && target != null) {
                    SadanGiant.this.laserActiveCD = true;
                    SadanGiant.this.laserActive = true;
                    SadanGiant.this.laser(entity);
                }
                if (!(SadanGiant.this.swordSlamCD || SadanGiant.this.swordActiv || SadanGiant.this.shockWave || SUtil.random(1, 140) > 7 || target == null)) {
                    SadanGiant.this.swordActiv = true;
                    SadanGiant.this.swordSlamCD = true;
                    SadanGiant.this.swordSlamAC(entity, (LivingEntity)target);
                }
                if (!(SadanGiant.this.shockWave || SadanGiant.this.shockWaveCD || SUtil.random(1, 100) > 5 || SadanGiant.this.swordActiv || target == null)) {
                    SadanGiant.this.shockWaveCD = true;
                    SadanGiant.this.shockWave = true;
                    Vector vec = new Vector(0, 0, 0);
                    vec.setY(2);
                    e.setVelocity(vec);
                    SUtil.delay(() -> SadanGiant.this.jumpAni(entity), 10L);
                }
                if (!SadanGiant.this.terToss && !SadanGiant.this.terTossCD && SUtil.random(1, 150) <= 4 && target != null) {
                    SadanGiant.this.terTossCD = true;
                    SadanGiant.this.terToss = true;
                    SUtil.delay(() -> SadanGiant.this.terToss = false, 300L);
                    SadanGiant.this.launchTerrain(entity);
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
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 8L);
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)), SadanGiant.b(12228503, Material.LEATHER_HELMET), SadanGiant.b(14751108, Material.LEATHER_CHESTPLATE), SadanGiant.c(Material.DIAMOND_LEGGINGS), SadanGiant.b(8991025, Material.LEATHER_BOOTS));
    }

    @Override
    public void onDeath(SEntity sEntity, final org.bukkit.entity.Entity killed, org.bukkit.entity.Entity damager) {
        Sputnik.zero(killed);
        SadanHuman.IsMusicPlaying.put(killed.getWorld().getUID(), false);
        SadanHuman.BossRun.put(killed.getWorld().getUID(), false);
        SUtil.delay(() -> SadanFunction.sendReMsg(true, killed.getWorld()), 30L);
        SUtil.delay(() -> SadanFunction.endRoom2(killed.getWorld()), 40L);
        final BukkitTask bkt = SadanHuman.playHBS(killed.getWorld());
        new BukkitRunnable(){

            public void run() {
                if (killed.getWorld() == null || killed.getWorld().getPlayers().size() == 0) {
                    bkt.cancel();
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        for (Player p : killed.getWorld().getPlayers()) {
            User user = User.getUser(p.getUniqueId());
            user.addBCollection(1);
        }
        this.aA(killed.getWorld());
        EntityPlayer a = DeadBodyMaker.spawn(killed.getLocation());
        ArmorStand as = (ArmorStand)killed.getWorld().spawn(killed.getLocation().clone().add(0.0, -0.2, 0.0), ArmorStand.class);
        final ArmorStand as2 = (ArmorStand)killed.getWorld().spawn(killed.getLocation().add(0.0, 0.1, 0.0), ArmorStand.class);
        as.setVisible(false);
        as.setGravity(false);
        as.setCustomNameVisible(true);
        as.setCustomName(Sputnik.trans("&e\ufd3e &c&lSadan &e0&c\u2764 &e\ufd3f"));
        as2.setVisible(false);
        as2.setGravity(false);
        as2.setCustomNameVisible(true);
        as2.setCustomName(Sputnik.trans("&f&lNOOOOOO!!! THIS IS IMPOSSIBLE!!!"));
        SUtil.broadcastWorld(Sputnik.trans("&c[BOSS] Sadan&f: NOOOOOO!!! THIS IS IMPOSSIBLE!!!"), killed.getWorld());
        new BukkitRunnable(){

            public void run() {
                as2.setCustomName(Sputnik.trans("&f&lFATHER, FORGIVE ME!!!"));
                SUtil.broadcastWorld(Sputnik.trans("&c[BOSS] Sadan&f: FATHER, FORGIVE ME!!!"), killed.getWorld());
                SUtil.delay(() -> as2.setCustomNameVisible(false), 60L);
                SUtil.delay(() -> as2.setCustomName(""), 60L);
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 35L);
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
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.buildColorStack(array_colors[0])), 20L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.buildColorStack(array_colors[1])), 40L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.buildColorStack(array_colors[2])), 60L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.buildColorStack(array_colors[3])), 80L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.buildColorStack(array_colors[4])), 100L);
        SUtil.delay(() -> this.laserAni(e), 105L);
        SUtil.delay(() -> {
            this.laserActive = false;
        }, 250L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.buildColorStack(array_colors[4])), 270L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.buildColorStack(array_colors[3])), 290L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.buildColorStack(array_colors[2])), 310L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.buildColorStack(array_colors[1])), 330L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.buildColorStack(array_colors[0])), 350L);
        SUtil.delay(() -> e.getEquipment().setHelmet(SadanGiant.b(15249075, Material.LEATHER_HELMET)), 370L);
        SUtil.delay(() -> {
            this.laserActiveCD = false;
        }, 650L);
    }

    public void aA(World world) {
        for (org.bukkit.entity.Entity entity : world.getEntities()) {
            if (entity instanceof HumanEntity) continue;
            entity.remove();
        }
    }

    public void jumpAni(final LivingEntity e) {
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (!SadanGiant.this.shockWave) {
                    this.cancel();
                    return;
                }
                if (e.isOnGround()) {
                    SadanGiant.this.shockWave = false;
                    SUtil.delay(() -> SadanGiant.this.shockWaveCD = false, 500L);
                    e.getWorld().playEffect(e.getLocation().add(0.0, 0.5, 0.0), Effect.EXPLOSION_HUGE, 3);
                    e.getWorld().playEffect(e.getLocation(), Effect.EXPLOSION_HUGE, 3);
                    e.getWorld().playSound(e.getLocation(), Sound.EXPLODE, 3.0f, 0.0f);
                    SUtil.delay(() -> e.getWorld().playSound(e.getLocation(), Sound.EXPLODE, 10.0f, 0.0f), 5L);
                    for (org.bukkit.entity.Entity entities : e.getNearbyEntities(15.0, 10.0, 15.0)) {
                        Player p;
                        Vector vec = new Vector(0, 0, 0);
                        if (entities.hasMetadata("NPC") || entities instanceof ArmorStand || entities.hasMetadata("highername") || !(entities instanceof Player)) continue;
                        if (entities.getLocation().distance(e.getLocation()) <= 5.0) {
                            p = (Player)entities;
                            p.sendMessage(Sputnik.trans("&c&lThe Giant One &chit you with &eStomp &cfor " + SUtil.commaify(Math.round(SadanFunction.dmgc(40000, p, (org.bukkit.entity.Entity)e).intValue())) + " &cdamage."));
                            continue;
                        }
                        p = (Player)entities;
                        p.sendMessage(Sputnik.trans("&c&lThe Giant One &chit you with &eStomp &cfor " + SUtil.commaify(Math.round(SadanFunction.dmgc(30000, p, (org.bukkit.entity.Entity)e).intValue())) + " &cdamage."));
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
                if (!SadanGiant.this.laserActive) {
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
                    SadanGiant.drawLine(loc1, en1, 0.0);
                    SadanGiant.drawLine(loc2, en2, 0.0);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 5L);
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (!SadanGiant.this.laserActive) {
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
                    SadanGiant.getEntity(loc1, en1, e);
                    SadanGiant.getEntity(loc2, en2, e);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 10L);
    }

    public void launchTerrain(final LivingEntity e) {
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (!SadanGiant.this.terToss) {
                    SUtil.delay(() -> SadanGiant.this.terTossCD = false, 550L);
                    this.cancel();
                    return;
                }
                CraftLivingEntity t = ((CraftZombie)e).getTarget();
                if (t != null) {
                    SadanGiant.this.throwTerrain(e, (org.bukkit.entity.Entity)t);
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
            for (org.bukkit.entity.Entity entity : ended.getWorld().getNearbyEntities(crystalLocation.clone().add(vector.clone().multiply((double)i / count)), 0.17, 0.0, 0.17)) {
                if (!(entity instanceof Player)) continue;
                Player p = (Player)entity;
                p.getWorld().playEffect(p.getLocation().clone().add(0.0, 2.2, 0.0), Effect.LAVA_POP, 10);
                p.getWorld().playEffect(p.getLocation().clone().add(0.0, 2.2, 0.0), Effect.LAVA_POP, 10);
                p.getWorld().playEffect(p.getLocation().clone().add(0.0, 2.2, 0.0), Effect.LAVA_POP, 10);
                p.getWorld().playEffect(p.getLocation().clone().add(0.0, 2.2, 0.0), Effect.LAVA_POP, 10);
                p.sendMessage(Sputnik.trans("&c&lThe Giant One &chit you with &eLaser Eyes &cfor " + SUtil.commaify(Math.round(SadanFunction.dmgc(16000, p, (org.bukkit.entity.Entity)e).intValue())) + " &cdamage."));
                return;
            }
        }
    }

    public static void applyEffect(PotionEffectType e, org.bukkit.entity.Entity en, int ticks, int amp) {
        ((LivingEntity)en).addPotionEffect(new PotionEffect(e, ticks, amp));
    }

    public static void createBlockTornado(org.bukkit.entity.Entity e, Material mat, byte id) {
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
        p.sendMessage(Sputnik.trans("&c&lThe Giant One &chit you with &eBoulder Toss &cfor " + SUtil.commaify(SadanFunction.dmgc(30000, p, (org.bukkit.entity.Entity)e)) + " &cdamage."));
    }

    public void throwTerrain(LivingEntity e, org.bukkit.entity.Entity target) {
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
            block.setMetadata("f", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
            block.setVelocity(Sputnik.calculateVelocityBlock(origin.toVector(), endPos.toVector(), 3));
        });
    }

    public static void playLaserSound(Player p, final org.bukkit.entity.Entity e) {
        new BukkitRunnable(){

            public void run() {
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void swordSlamAC(LivingEntity e, LivingEntity tar) {
        SUtil.delay(() -> this.swordSlamF(e, tar), 40L);
    }

    public void swordSlamF(LivingEntity e, LivingEntity tar) {
        SUtil.delay(() -> this.swordSlam(e, tar), 30L);
    }

    public void swordSlam(final LivingEntity e, final LivingEntity player) {
        if (e.isDead()) {
            return;
        }
        e.getEquipment().setItemInHand(null);
        final Giant armorStand = (Giant)player.getWorld().spawn(e.getLocation().add(0.0, 12.0, 0.0), Giant.class);
        armorStand.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)));
        Sputnik.applyPacketGiant((org.bukkit.entity.Entity)armorStand);
        armorStand.setCustomName("Dinnerbone");
        armorStand.setMetadata("GiantSword", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        armorStand.setMetadata("NoAffect", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        EntityManager.Woosh((LivingEntity)armorStand);
        EntityManager.noHit((org.bukkit.entity.Entity)armorStand);
        EntityManager.shutTheFuckUp((org.bukkit.entity.Entity)armorStand);
        Location firstLocation = e.getLocation().add(0.0, 12.0, 0.0);
        EntityLiving nms = ((CraftLivingEntity)e).getHandle();
        for (Player players : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)players).getHandle().playerConnection.sendPacket((Packet)new PacketPlayOutAnimation((Entity)((CraftLivingEntity)e).getHandle(), 0));
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
                if (!SadanGiant.this.swordActiv) {
                    this.cancel();
                    return;
                }
                if (armorStand.isOnGround()) {
                    SadanGiant.this.swordActiv = false;
                    SUtil.delay(() -> SadanGiant.this.swordSlamCD = false, 300L);
                    armorStand.remove();
                    Giant sword = (Giant)e.getWorld().spawnEntity(armorStand.getLocation(), EntityType.GIANT);
                    Sputnik.applyPacketGiant((org.bukkit.entity.Entity)sword);
                    sword.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 1));
                    EntityManager.noAI((org.bukkit.entity.Entity)sword);
                    EntityManager.noHit((org.bukkit.entity.Entity)sword);
                    EntityManager.shutTheFuckUp((org.bukkit.entity.Entity)sword);
                    sword.setCustomName("Dinnerbone");
                    sword.setMetadata("GiantSword", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
                    sword.setMetadata("NoAffect", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
                    ArmorStand stand = (ArmorStand)player.getWorld().spawnEntity(armorStand.getLocation(), EntityType.ARMOR_STAND);
                    stand.setVisible(false);
                    stand.setGravity(true);
                    stand.setPassenger((org.bukkit.entity.Entity)sword);
                    sword.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD)));
                    e.getWorld().playSound(player.getLocation(), Sound.ANVIL_LAND, 1.0f, 0.0f);
                    e.getWorld().playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 1.0f, 0.35f);
                    for (org.bukkit.entity.Entity entities : sword.getWorld().getNearbyEntities(sword.getLocation().add(sword.getLocation().getDirection().multiply(3)), 4.0, 4.0, 4.0)) {
                        Player p;
                        if (entities.hasMetadata("NPC") || entities instanceof ArmorStand || entities instanceof Giant || !(entities instanceof Player)) continue;
                        if (entities.getLocation().add(sword.getLocation().getDirection().multiply(3)).distance(sword.getLocation()) > 1.0) {
                            p = (Player)entities;
                            p.sendMessage(Sputnik.trans("&c&lThe Giant One &chit you with &eSword of 10,000 Truths &cfor " + SUtil.commaify(SadanFunction.dmgc(50000, p, (org.bukkit.entity.Entity)e)) + " &cdamage."));
                            continue;
                        }
                        p = (Player)entities;
                        p.sendMessage(Sputnik.trans("&c&lThe Giant One &chit you with &eSword of 10,000 Truths &cfor " + SUtil.commaify(SadanFunction.dmgc(55000, p, (org.bukkit.entity.Entity)e)) + " &cdamage."));
                    }
                    SUtil.delay(() -> sword.remove(), 35L);
                    SUtil.delay(() -> stand.remove(), 35L);
                    SUtil.delay(() -> e.getEquipment().setItemInHand(SUtil.enchant(new ItemStack(Material.DIAMOND_SWORD))), 35L);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    @Override
    public void onDamage(SEntity sEntity, org.bukkit.entity.Entity damager, EntityDamageByEntityEvent e, AtomicDouble damage) {
        e.getEntity().getWorld().playSound(e.getEntity().getLocation(), Sound.ENDERDRAGON_HIT, 1.0f, 0.3f);
    }
}

