/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.Entity
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.entity.dungeons.boss.sadan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftZombie;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityEquipment;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.AnimationSequence;
import vn.giakhanhvn.skysim.entity.dungeons.boss.sadan.SadanFunction;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.GlobalBossBar;
import vn.giakhanhvn.skysim.entity.zombie.BaseZombie;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.BossBar;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.PlayJingle;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class SadanHuman
extends BaseZombie {
    private double y = 0.85;
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h = false;
    public static final Map<Entity, String> DIALOUGE_BOSS = new HashMap<Entity, String>();
    public static final Map<UUID, Boolean> SadanReach = new HashMap<UUID, Boolean>();
    @Deprecated
    public static final Map<World, Boolean> SadanP1 = new HashMap<World, Boolean>();
    @Deprecated
    public static final Map<World, Boolean> SadanP2 = new HashMap<World, Boolean>();
    public static final Map<UUID, Integer> SadanInterest = new HashMap<UUID, Integer>();
    public static final Map<UUID, Integer> SadanGiantsCount = new HashMap<UUID, Integer>();
    public static final Map<UUID, Boolean> IsMusicPlaying = new HashMap<UUID, Boolean>();
    public static final Map<UUID, Boolean> BBRunning = new HashMap<UUID, Boolean>();
    public static final Map<UUID, Boolean> BossRun = new HashMap<UUID, Boolean>();
    @Deprecated
    public static final Map<World, Byte> SadanCurrentPhase = new HashMap<World, Byte>();
    @Deprecated
    public static final Map<World, Boolean> IsSadanDied = new HashMap<World, Boolean>();
    private boolean phase1 = false;
    private boolean phase2 = false;
    private boolean phase3 = false;
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
        return 40000.0;
    }

    public GlobalBossBar setBar(World w, String s) {
        this.bb = new GlobalBossBar(Sputnik.trans(s), w);
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
        double height_;
        if (BBRunning.containsKey(entity.getWorld().getUID())) {
            if (BBRunning.get(entity.getWorld().getUID()).booleanValue()) {
                entity.remove();
                return;
            }
        } else {
            BBRunning.put(entity.getWorld().getUID(), true);
        }
        if (!entity.getWorld().getName().contains("f6")) {
            entity.remove();
            Bukkit.broadcastMessage((String)Sputnik.trans("&cAn Error has been occured while performing the /spawncustommob command! Please check the Console!"));
            return;
        }
        for (Player p : entity.getWorld().getPlayers()) {
            User user = User.getUser(p.getUniqueId());
            user.addBRun6(1);
        }
        BossRun.put(entity.getWorld().getUID(), true);
        SadanFunction.s_((Entity)entity);
        final BukkitTask bkt = SadanHuman.playHBS(entity.getWorld());
        this.sayPhase1Dialouge((Entity)entity);
        Location loc = new Location(entity.getWorld(), 191.5, 78.0, 296.5, 180.0f, 0.0f);
        entity.teleport(loc);
        Repeater.FloorLivingSec.put(entity.getWorld().getUID(), 0);
        SadanFunction.roomLoop(entity.getWorld());
        SadanInterest.put(entity.getWorld().getUID(), 105);
        SadanGiantsCount.put(entity.getWorld().getUID(), 4);
        final GlobalBossBar bb = this.setBar(entity.getWorld(), "&c&lSadan");
        new BukkitRunnable(){

            public void run() {
                SadanHuman.this.phase1 = true;
                bb.setTitle(Sputnik.trans("&c&lSadan's Interest Level"));
                bb.setProgress(1.0);
                SadanHuman.this.phase1((Entity)entity);
                bkt.cancel();
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 170L);
        new BukkitRunnable(){

            public void run() {
                if (entity.getWorld() == null || entity.getWorld().getPlayers().size() == 0 || entity.isDead()) {
                    bkt.cancel();
                    DIALOUGE_BOSS.remove(entity);
                    SadanReach.remove(entity.getWorld().getUID());
                    SadanInterest.remove(entity.getWorld().getUID());
                    SadanGiantsCount.remove(entity.getWorld().getUID());
                    BossRun.remove(entity.getWorld().getUID());
                    IsMusicPlaying.remove(entity.getWorld().getUID());
                    this.cancel();
                    return;
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        new BukkitRunnable(){

            public void run() {
                double result;
                if (entity.isDead()) {
                    ArrayList<Player> plist = new ArrayList<Player>();
                    for (Player p : bb.players) {
                        plist.add(p);
                    }
                    plist.forEach(pl -> bb.removePlayer((Player)pl));
                    bb.setProgress(0.0);
                    bb.cancel();
                    this.cancel();
                    return;
                }
                if (SadanHuman.this.phase1) {
                    if (SadanInterest.get(entity.getWorld().getUID()) > 0) {
                        int interest = SadanInterest.get(entity.getWorld().getUID());
                        result = (double)interest / 105.0;
                        bb.setProgress(result);
                    } else {
                        bb.setTitle(Sputnik.trans("&c&lSadan"));
                        bb.setProgress(1.0);
                        SadanHuman.this.phase1 = false;
                        SadanHuman.this.phase2((Entity)entity);
                        new BukkitRunnable(){

                            public void run() {
                                bb.setTitle(Sputnik.trans("&c&lSadan's Giants"));
                                bb.setProgress(1.0);
                            }
                        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 35L);
                    }
                }
                if (SadanHuman.this.phase2) {
                    if (SadanGiantsCount.get(entity.getWorld().getUID()) > 0) {
                        int giant = SadanGiantsCount.get(entity.getWorld().getUID());
                        result = (double)giant / 4.0;
                        bb.setProgress(result);
                    } else {
                        bb.setTitle(Sputnik.trans("&c&lSadan"));
                        bb.setProgress(1.0);
                        SadanHuman.this.phase2 = false;
                        SadanHuman.this.phase3((Entity)entity);
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
        ((CraftZombie)entity).setBaby(false);
        Sputnik.applyPacketNPC((Entity)entity, "ewogICJ0aW1lc3RhbXAiIDogMTYxMjAyOTExOTA2MiwKICAicHJvZmlsZUlkIiA6ICI5ZDQyNWFiOGFmZjg0MGU1OWM3NzUzZjc5Mjg5YjMyZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUb21wa2luNDIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmEwNmNiMGM0NzFjMWM5YmMxNjlhZjI3MGNkNDY2ZWE3MDE5NDY3NzYwNTZlNDcyZWNkYWViNDlmMGY0YTRkYyIKICAgIH0KICB9Cn0=", "mpSbfgDmWvoHASfqQ+poj2b4Y0QEZYh4QlqcCqHrZ4DNKY7mIenlbY2s7Ptmhb46dONt5OVfHb1pDLDlCPnYP9QYDXhl/wR99wxA4F7HHjs1g1omvZBfGRCvwHU/Bc3aWhjlaKZCVotf0snzrPTWIHFYnQoVLnhXoz19b3SQfdztIipZoZFKgMxwM2l4y+hBS9p7b/u2loz6/kVLBiLxzzYtAayF+ekma+bWlQcqhdsaf/BAJJSjh/UtipZLvAo4L2E2JlBsoKhj9PVSRVk4eAS1KE7p9Dupbrr/Ypj4bYVpUH5KhMJlQn7vCGoWILwd1NjFWk6KVlGUCag8/3pE1BNeD5d3QOfiVCkFH/rofRfS0/w0Nv8ROK0JQP/cFaAQ3kQ2ilvifF0kzPiA1M7si22lbXGyLqhQAVFsNSgKIU0Fe2qfD536Rr+kkBc/sVAzfVh4ajfsOXtMuMoZGIDJULpA1RD9qsybGvl7kkVQd2jPzlvZD8Ef8ZW8wr64Lu+/zZEj30zISIKZiwIsMKM2vOO7eqbfTs+tu0BNKKjiRg7uLF0qhyCpQrlJENzFud04ZiaTyI1Btt2LpOHQmKASWfg7/TEr8rPVPWiVqRBPCpHe5xJlAtQc2+PrtBO8u+qG3TTRKVci2a+Mpx1SwuPtMY2ZRj1NmYW3yBuu9pQnvlg=", true);
        EntityManager.DEFENSE_PERCENTAGE.put((Entity)entity, 100);
        entity.setMetadata("SlayerBoss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("LD", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("h_sadan", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("Boss", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        EntityManager.noAI((Entity)entity);
        EntityManager.noHit((Entity)entity);
        EntityManager.shutTheFuckUp((Entity)entity);
        entity.setMetadata("GiantSword", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        entity.setMetadata("NoAffect", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        net.minecraft.server.v1_8_R3.Entity e = ((CraftEntity)entity).getHandle();
        final double height = height_ = e.getBoundingBox().e - e.getBoundingBox().b;
        final ArmorStand hologram_d = (ArmorStand)entity.getWorld().spawn(entity.getLocation().add(0.0, height + 0.22, 0.0), ArmorStand.class);
        hologram_d.setVisible(false);
        hologram_d.setGravity(false);
        hologram_d.setSmall(false);
        hologram_d.setMarker(true);
        hologram_d.setBasePlate(false);
        hologram_d.setCustomNameVisible(true);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                Sputnik.applyPacketNPC((Entity)entity, "ewogICJ0aW1lc3RhbXAiIDogMTYxMjAyOTExOTA2MiwKICAicHJvZmlsZUlkIiA6ICI5ZDQyNWFiOGFmZjg0MGU1OWM3NzUzZjc5Mjg5YjMyZSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUb21wa2luNDIiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmEwNmNiMGM0NzFjMWM5YmMxNjlhZjI3MGNkNDY2ZWE3MDE5NDY3NzYwNTZlNDcyZWNkYWViNDlmMGY0YTRkYyIKICAgIH0KICB9Cn0=", "mpSbfgDmWvoHASfqQ+poj2b4Y0QEZYh4QlqcCqHrZ4DNKY7mIenlbY2s7Ptmhb46dONt5OVfHb1pDLDlCPnYP9QYDXhl/wR99wxA4F7HHjs1g1omvZBfGRCvwHU/Bc3aWhjlaKZCVotf0snzrPTWIHFYnQoVLnhXoz19b3SQfdztIipZoZFKgMxwM2l4y+hBS9p7b/u2loz6/kVLBiLxzzYtAayF+ekma+bWlQcqhdsaf/BAJJSjh/UtipZLvAo4L2E2JlBsoKhj9PVSRVk4eAS1KE7p9Dupbrr/Ypj4bYVpUH5KhMJlQn7vCGoWILwd1NjFWk6KVlGUCag8/3pE1BNeD5d3QOfiVCkFH/rofRfS0/w0Nv8ROK0JQP/cFaAQ3kQ2ilvifF0kzPiA1M7si22lbXGyLqhQAVFsNSgKIU0Fe2qfD536Rr+kkBc/sVAzfVh4ajfsOXtMuMoZGIDJULpA1RD9qsybGvl7kkVQd2jPzlvZD8Ef8ZW8wr64Lu+/zZEj30zISIKZiwIsMKM2vOO7eqbfTs+tu0BNKKjiRg7uLF0qhyCpQrlJENzFud04ZiaTyI1Btt2LpOHQmKASWfg7/TEr8rPVPWiVqRBPCpHe5xJlAtQc2+PrtBO8u+qG3TTRKVci2a+Mpx1SwuPtMY2ZRj1NmYW3yBuu9pQnvlg=", true);
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2000L);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    hologram_d.remove();
                    this.cancel();
                    return;
                }
                if (DIALOUGE_BOSS.containsKey(entity)) {
                    hologram_d.setCustomNameVisible(true);
                    hologram_d.setCustomName(Sputnik.trans("&f&l" + DIALOUGE_BOSS.get(entity)));
                } else {
                    hologram_d.setCustomNameVisible(false);
                    hologram_d.setCustomName("");
                }
                hologram_d.teleport(entity.getLocation().clone().add(0.0, height + 0.22, 0.0));
                hologram_d.teleport(entity.getLocation().clone().add(0.0, height + 0.22, 0.0));
                hologram_d.teleport(entity.getLocation().clone().add(0.0, height + 0.22, 0.0));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void t(ArmorStand respawnAnchor) {
    }

    @Override
    public SEntityEquipment getEntityEquipment() {
        return new SEntityEquipment(null, null, null, null, null);
    }

    @Override
    public void onDeath(SEntity sEntity, Entity killed, Entity damager) {
    }

    @Override
    public double getXPDropped() {
        return 0.0;
    }

    public void phase1(final Entity entity) {
        this.function(entity);
        IsMusicPlaying.put(entity.getWorld().getUID(), true);
        PlayJingle.play(PlayJingle.SkySimSong.DUNGEONS_DRAMA, (byte)100, new Location(entity.getWorld(), 191.0, 69.0, 266.0), 1000);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    SadanHuman.this.phase1 = false;
                    SadanInterest.put(entity.getWorld().getUID(), 0);
                    SadanFunction.endPhase1(entity.getWorld());
                    this.cancel();
                    return;
                }
                if (!SadanInterest.containsKey(entity.getWorld().getUID())) {
                    SadanInterest.put(entity.getWorld().getUID(), 105);
                }
                if (SadanInterest.get(entity.getWorld().getUID()) <= 0) {
                    SadanHuman.this.phase1 = false;
                    SadanInterest.put(entity.getWorld().getUID(), 0);
                    SadanFunction.endPhase1(entity.getWorld());
                    this.cancel();
                    return;
                }
                if (SadanInterest.get(entity.getWorld().getUID()) <= 84 && SadanInterest.get(entity.getWorld().getUID()) >= 75 && !SadanHuman.this.a) {
                    SadanHuman.this.a = true;
                    SadanHuman.this.sendDelayed(entity, "My Terracotta army uses some of the finest souls: old warriors and wizards!", 60);
                }
                if (SadanInterest.get(entity.getWorld().getUID()) <= 63 && SadanInterest.get(entity.getWorld().getUID()) >= 55 && !SadanHuman.this.b) {
                    SadanHuman.this.b = true;
                    SadanHuman.this.sendDelayed(entity, "You will be humbled, reckless humans!", 60);
                }
                if (SadanInterest.get(entity.getWorld().getUID()) <= 42 && SadanInterest.get(entity.getWorld().getUID()) >= 35 && !SadanHuman.this.c) {
                    SadanHuman.this.c = true;
                    SadanHuman.this.sendDelayed(entity, "Can you at least enjoy my science? pushing the boundaries of necromancy!", 60);
                }
                if (SadanInterest.get(entity.getWorld().getUID()) <= 21 && SadanInterest.get(entity.getWorld().getUID()) >= 15 && !SadanHuman.this.d) {
                    SadanHuman.this.d = true;
                    SadanHuman.this.sendDelayed(entity, "Corpses are deficient, full of weaknesses. Statues are stronger, faster, easier to repair.", 60);
                }
                if (SadanInterest.get(entity.getWorld().getUID()) <= 10 && SadanInterest.get(entity.getWorld().getUID()) >= 5 && !SadanHuman.this.e) {
                    SadanHuman.this.e = true;
                    SadanHuman.this.sendDelayed(entity, "Can't you see there's no winning? This room will be your tomb.", 60);
                }
                SadanInterest.put(entity.getWorld().getUID(), SadanInterest.get(entity.getWorld().getUID()) - 1);
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 20L);
    }

    public void f(Entity entity) {
        if (entity.isDead()) {
            return;
        }
        if (SadanGiantsCount.containsKey(entity.getWorld().getUID())) {
            if (SadanGiantsCount.get(entity.getWorld().getUID()) == 4) {
                int rand = SUtil.random(0, 2);
                if (SUtil.random(0, 2) == 0) {
                    this.sendDelayed(entity, "How many players does it take to kill a Giant?", 60);
                } else if (rand == 1) {
                    this.sendDelayed(entity, "Boulders, lasers, stomping, and giant swords! Oh my!", 60);
                } else {
                    this.sendDelayed(entity, "Which one is your favorite? Those laser eyes... beautiful!", 60);
                }
            } else if (SadanGiantsCount.get(entity.getWorld().getUID()) <= 3 && SadanGiantsCount.get(entity.getWorld().getUID()) > 0) {
                this.sendDelayed(entity, "My first time using them, extremely interesting!", 60);
            }
        }
    }

    public void phase2(final Entity entity) {
        this.phase2 = true;
        this.sayPhase2Dialouge(entity);
        SUtil.delay(() -> SadanFunction.release(entity.getWorld()), 40L);
        SUtil.delay(() -> this.f(entity), SUtil.random(700, 1000));
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (SadanGiantsCount.get(entity.getWorld().getUID()) <= 0) {
                    this.cancel();
                    return;
                }
                if (SadanGiantsCount.get(entity.getWorld().getUID()) == 3 && !SadanHuman.this.f) {
                    SadanHuman.this.f = true;
                    SadanHuman.this.sendDelayed(entity, SadanHuman.this.randP1(), 60);
                }
                if (SadanGiantsCount.get(entity.getWorld().getUID()) == 2 && !SadanHuman.this.g) {
                    SadanHuman.this.g = true;
                    SadanHuman.this.sendDelayed(entity, SadanHuman.this.randP2(), 60);
                }
                if (SadanGiantsCount.get(entity.getWorld().getUID()) == 1 && !SadanHuman.this.h) {
                    SadanHuman.this.h = true;
                    SadanHuman.this.sendDelayed(entity, SadanHuman.this.randP3(), 60);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
    }

    public void function(Entity e) {
        for (Entity e2 : e.getWorld().getEntities()) {
            if (!e2.hasMetadata("t_sadan_p1_1")) continue;
            e2.remove();
            new SEntity(new Location(e2.getWorld(), e2.getLocation().getX(), e2.getLocation().getY(), e2.getLocation().getZ(), e2.getLocation().getYaw(), 0.0f), SEntityType.TERRACOTTA_SADAN, new Object[0]);
        }
    }

    public void phase3(final Entity entity) {
        this.phase3 = true;
        AnimationSequence.chainAnimation(entity.getWorld());
        this.sayPhase3Dialouge(entity);
        new BukkitRunnable(){

            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                if (SadanReach.containsKey(entity.getWorld().getUID())) {
                    if (!SadanReach.get(entity.getWorld().getUID()).booleanValue()) {
                        this.cancel();
                        return;
                    }
                    if (SadanReach.get(entity.getWorld().getUID()).booleanValue()) {
                        SadanHuman.this.a(entity);
                        SadanReach.put(entity.getWorld().getUID(), false);
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
    }

    public void a(final Entity e) {
        e.setMetadata("RMHN", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        final Location l = e.getLocation();
        l.setPitch(-27.0f);
        l.setYaw(-180.0f);
        new BukkitRunnable(){

            public void run() {
                Vector teleportTo = l.getDirection().normalize().multiply(1);
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (String.valueOf(e.getLocation().getX()).contains("191") && String.valueOf(e.getLocation().getZ()).contains("285")) {
                    SadanHuman.this.b(e);
                    this.cancel();
                    return;
                }
                e.teleport(e.getLocation().add(teleportTo).multiply(1.0));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
    }

    public void b(final Entity e) {
        final Location l = e.getLocation();
        l.setPitch(90.0f);
        l.setYaw(-180.0f);
        new BukkitRunnable(){

            public void run() {
                Vector teleportTo = l.getDirection().normalize().multiply(1);
                if (e.isDead()) {
                    this.cancel();
                    return;
                }
                if (String.valueOf(e.getLocation().getY()).contains("79")) {
                    new BukkitRunnable(){

                        public void run() {
                            new SEntity(new Location(e.getWorld(), e.getLocation().getX(), 69.0, e.getLocation().getZ(), 0.0f, 0.0f), SEntityType.GIANT_SADAN, new Object[0]);
                            e.getWorld().playSound(e.getLocation(), Sound.ENDERDRAGON_GROWL, 100.0f, 0.85f);
                            for (Entity e1 : e.getWorld().getEntities()) {
                                if (!e1.hasMetadata("dummyforphase3")) continue;
                                e1.remove();
                            }
                            e.remove();
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 3L);
                    SadanReach.remove(e.getWorld().getUID());
                    this.cancel();
                    return;
                }
                e.teleport(e.getLocation().add(teleportTo).multiply(1.0));
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
    }

    public void sayPhase1Dialouge(Entity e) {
        this.say("So you made it all the way here...and you wish to defy me? Sadan?!", e);
        this.sd_("The audacity! I have been the ruler of these floors for a hundred years!", e, 60);
        this.sd_("I am the bridge between this realm and the world below! You shall not pass!", e, 120);
        SUtil.delay(() -> DIALOUGE_BOSS.remove(e), 180L);
    }

    public void sendDelayed(Entity e, String msg, int delay) {
        this.say(msg, e);
        SUtil.delay(() -> DIALOUGE_BOSS.remove(e), delay);
    }

    public void sayPhase2Dialouge(Entity e) {
        this.say("ENOUGH!", e);
        this.sd_("My giants! Unleashed!", e, 40);
        SUtil.delay(() -> DIALOUGE_BOSS.remove(e), 80L);
    }

    public void sayPhase3Dialouge(Entity e) {
        this.say("You did it. I understand now, you have earned my respect.", e);
        this.sd_("If only you had become my disciples instead of this incompetent bunch.", e, 60);
        this.sd_("Maybe in another life. Until then, meet my ultimate corpse.", e, 120);
        this.sd_("I'm sorry but I need to concentrate. I wish it didn't have to come to this.", e, 180);
        SUtil.delay(() -> DIALOUGE_BOSS.remove(e), 240L);
    }

    public void sd_(String message, Entity e, int delay) {
        if (e.isDead()) {
            return;
        }
        SUtil.delay(() -> this.say(message, e), delay);
    }

    public void say(String message, Entity e) {
        if (e.isDead()) {
            return;
        }
        DIALOUGE_BOSS.put(e, message);
        for (Player p : e.getWorld().getPlayers()) {
            p.sendMessage(Sputnik.trans("&c[BOSS] Sadan&f: " + message));
        }
    }

    public void cd_(Entity e) {
        if (DIALOUGE_BOSS.containsKey(e)) {
            DIALOUGE_BOSS.remove(e);
        }
    }

    public String randP1() {
        String fin = "";
        int rand = SUtil.random(0, 2);
        fin = rand == 0 ? "You killed my weakest Giant!" : (rand == 1 ? "You've managed to kill a Giant! Good luck with the rest of them!" : "That Giant was weak! I clearly screwed up during the necromancy process.");
        return fin;
    }

    public String randP2() {
        String fin = "";
        int rand = SUtil.random(0, 2);
        fin = rand == 0 ? "2 of my Giants down?! No matter, these last two are tough!" : (rand == 1 ? "I'm starting to sweat!" : "Uh oh! Half of my Giant creations have bit the dust! Whatever will I do?");
        return fin;
    }

    public String randP3() {
        String fin = "";
        int rand = SUtil.random(0, 1);
        if (rand == 0) {
            fin = "Whoops! There goes my third Giant!";
        } else if (rand == 1) {
            fin = "Down to one Giant!";
        }
        return fin;
    }

    public String randCom() {
        String fin = "";
        return fin;
    }

    public static BukkitTask playHBS(final World w) {
        return new BukkitRunnable(){

            public void run() {
                for (Player p : w.getPlayers()) {
                    p.playSound(p.getLocation(), "mob.guardian.elder.hit", 1.0f, 0.0f);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 19L);
    }
}

