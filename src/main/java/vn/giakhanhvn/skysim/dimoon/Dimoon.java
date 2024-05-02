/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  com.google.common.collect.Multimaps
 *  com.google.common.collect.Ordering
 *  com.google.common.collect.TreeMultimap
 *  com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer
 *  net.minecraft.server.v1_8_R3.Entity
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Effect
 *  org.bukkit.GameMode
 *  org.bukkit.Location
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package vn.giakhanhvn.skysim.dimoon;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;
import com.google.common.collect.TreeMultimap;
import com.xxmicloxx.NoteBlockAPI.songplayer.PositionSongPlayer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import lombok.Getter;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.dimoon.Arena;
import vn.giakhanhvn.skysim.dimoon.DimoonLootTable;
import vn.giakhanhvn.skysim.dimoon.SummoningSequence;
import vn.giakhanhvn.skysim.dimoon.abilities.Ability;
import vn.giakhanhvn.skysim.dimoon.abilities.FireRain;
import vn.giakhanhvn.skysim.dimoon.abilities.Healing;
import vn.giakhanhvn.skysim.dimoon.abilities.Void;
import vn.giakhanhvn.skysim.dimoon.abilities.WindForce;
import vn.giakhanhvn.skysim.dimoon.abilities.WitherBullet;
import vn.giakhanhvn.skysim.dimoon.listeners.PlayerListener;
import vn.giakhanhvn.skysim.dimoon.utils.Utils;
import vn.giakhanhvn.skysim.entity.dungeons.watcher.GlobalBossBar;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.EntityManager;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class Dimoon {
    public static final int MAX_HEALTH = 100000;
    @Getter
    private final LivingEntity entity;
    @Getter
    private int health = 100000;
    @Getter
    private final boolean diminiS = false;
    private boolean failed = false;
    @Getter
    private final List<Ability> abilities = Arrays.asList(new WindForce(), new WitherBullet(), new FireRain(), new Void(), new Healing());
    public boolean stunned = false;
    private PositionSongPlayer pls;
    @Getter
    private final TreeMultimap<Integer, String> damages = TreeMultimap.create((Comparator)Ordering.natural().reverse(), Ordering.arbitrary());
    @Getter
    private String lastBlow = "N/A";
    @Getter
    private int parkoursCompleted = 0;
    private final Map<Class<? extends Ability>, Integer> cooldowns = new HashMap<Class<? extends Ability>, Integer>();
    @Getter
    private final List<BukkitTask> tasks = new ArrayList<BukkitTask>();
    public static ArmorStand a = null;
    private final Map<Integer, String> messages = new TreeMap<Integer, String>(){
        {
            this.put(80000, "Beating me? Sound like gathering clouds from the sky!");
            this.put(60000, "Oh my, how bad are you at parkour?");
            this.put(50000, "You should give up now, im tired, please?");
            this.put(35000, "You're not even trying!");
            this.put(25000, "Just, giveup, please.");
            this.put(10000, "THIS ISN'T HAPPENING!");
            this.put(5000, "HEALING!");
            this.put(0, "THIS IS IMPOSSIBLE! I CAN'T BE DEFEATED!!!");
        }
    };

    public static Dimoon spawnDimoon() {
        Dimoon dimoon;
        SkySimEngine.getPlugin().dimoon = dimoon = new Dimoon();
        SkySimEngine.getPlugin().arena = new Arena();
        dimoon.stunned = true;
        new BukkitRunnable(){

            public void run() {
                Utils.bossMessage("Finally, after years, those crystals, they trapped me, finally...!");
            }
        }.runTaskLater(SkySimEngine.getPlugin(), 35L);
        new BukkitRunnable(){

            public void run() {
                Utils.bossMessage("Anyway, how did you get to this place? And you want to challenge me?");
            }
        }.runTaskLater(SkySimEngine.getPlugin(), 75L);
        new BukkitRunnable(){

            public void run() {
                Utils.bossMessage("Only best parkour players are able to pass. Let's see how you will handle this...");
            }
        }.runTaskLater(SkySimEngine.getPlugin(), 115L);
        new BukkitRunnable(){

            public void run() {
                Utils.bossMessage("Good luck! And don't die, seriously, you only have ONE chance.");
                SUtil.delay(() -> {
                    dimoon.stunned = false;
                    Utils.bossMessage("WITHER AURA!");
                    for (Player p : dimoon.getEntity().getWorld().getPlayers()) {
                        p.sendMessage(Utils.format("&c&lALERT! &6Wither Aura &eactivated! Crossing those aura will deal &cinsane damage &eto you!"));
                    }
                    dimoon.getEntity().getWorld().playSound(dimoon.getEntity().getLocation(), Sound.WITHER_DEATH, 15.0f, 1.5f);
                }, 100L);
                SUtil.delay(() -> {
                    if (SkySimEngine.getPlugin().arena != null) {
                        SkySimEngine.getPlugin().arena.spawnDimoonaize(dimoon.getEntity());
                        dimoon.getEntity().getWorld().playSound(dimoon.getEntity().getLocation(), Sound.WITHER_SPAWN, 15.0f, 1.5f);
                    }
                }, 200L);
            }
        }.runTaskLater(SkySimEngine.getPlugin(), 155L);
        return dimoon;
    }

    public int getPlayerPlacement(Player player) {
        Iterator iterator = this.getDamages().entries().iterator();
        int place = -1;
        int i = 0;
        while (iterator.hasNext()) {
            if (((Map.Entry)iterator.next()).getValue().equals(player.getName())) {
                place = i + 1;
                break;
            }
            ++i;
        }
        return place;
    }

    private Dimoon() {
        World world = Bukkit.getWorld("arena");
        this.entity = (LivingEntity)world.spawnEntity(new Location(world, 234668.5, 155.0, 236481.5), EntityType.WITHER);
        this.entity.setMaxHealth(100000.0);
        this.entity.setHealth(100000.0);
        SUtil.delay(() -> {
            this.pls = Sputnik.playNativeSound("proschanie", 200, 100, true, this.entity.getLocation());
        }, 20L);
        EntityManager.DEFENSE_PERCENTAGE.put(this.entity, 100);
        this.entity.setCustomName(Sputnik.trans("&c&lDimoon"));
        this.entity.setCustomNameVisible(false);
        this.entity.setRemoveWhenFarAway(false);
        this.entity.setMetadata("NoAffect", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        this.entity.setMetadata("Dimoon", new FixedMetadataValue(SkySimEngine.getPlugin(), true));
        Dimoon.noAI(this.entity);
        ArmorStand st = Sputnik.spawnStaticDialougeBox(this.entity, 3.5);
        st.setCustomName(Sputnik.trans("&4\ufd3e &c&lDimoon the Wither &4\ufd3f"));
        st.setCustomNameVisible(true);
        ArmorStand st2 = Sputnik.spawnStaticDialougeBox(this.entity, 3.9);
        st2.setCustomNameVisible(false);
        a = st2;
        for (Ability ability : this.abilities) {
            this.cooldowns.put(ability.getClass(), ability.getCooldown());
        }
        final GlobalBossBar bb = new GlobalBossBar(Utils.format("&c&lDimoon"), this.entity.getWorld());
        for (Player p : this.entity.getWorld().getPlayers()) {
            User.getUser(p.getUniqueId()).setInDanger(false);
            bb.addPlayer(p);
        }
        new BukkitRunnable(){
            final LivingEntity e;
            float cout;
            {
                this.e = Dimoon.this.entity;
                this.cout = this.e.getLocation().getYaw();
            }

            public void run() {
                if (Dimoon.this.health <= 0 || this.e.isDead()) {
                    this.cancel();
                    if (Dimoon.this.pls != null) {
                        Dimoon.this.pls.destroy();
                    }
                    SkySimEngine.getPlugin().arena.collapseParkour();
                    this.e.setHealth(0.0);
                    Dimoon dimoon = SkySimEngine.getPlugin().dimoon;
                    if (!dimoon.getLastBlow().contains("N/A")) {
                        for (Player player : dimoon.getEntity().getWorld().getPlayers()) {
                            if (Dimoon.this.damages.containsValue(player.getName())) continue;
                            Dimoon.this.damages.put(0, player.getName());
                        }
                    }
                    if (!Dimoon.this.failed) {
                        SUtil.delay(() -> {
                            PositionSongPlayer plz = Sputnik.playNativeSound("victory", 200, 100, true, Dimoon.this.entity.getLocation());
                            SUtil.delay(() -> plz.destroy(), 1395L);
                        }, 20L);
                        for (Player player : dimoon.getEntity().getWorld().getPlayers()) {
                            StringBuilder message = new StringBuilder();
                            message.append(ChatColor.GREEN).append(ChatColor.BOLD).append("\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
                            message.append(ChatColor.GOLD).append(ChatColor.BOLD).append("                    DIMOON THE WITHER DOWN!").append("\n \n");
                            message.append("            ").append(ChatColor.AQUA).append(dimoon.getLastBlow()).append(ChatColor.GRAY).append(" dealt the final blow.\n \n");
                            Iterator iterator = dimoon.getDamages().entries().iterator();
                            for (int i = 0; i < 3 && iterator.hasNext(); ++i) {
                                int place = i + 1;
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
                                Map.Entry entry = (Map.Entry)iterator.next();
                                message.append(" Damager").append(ChatColor.RESET).append(ChatColor.GRAY).append(" - ").append(ChatColor.GREEN).append((String)entry.getValue()).append(ChatColor.GRAY).append(" - ").append(ChatColor.YELLOW).append(Utils.commaInt((Integer)entry.getKey())).append("\n");
                            }
                            Set damageSet = ((HashMultimap)Multimaps.invertFrom(dimoon.getDamages(), (Multimap)HashMultimap.create())).get(player.getName());
                            int damageDealt = damageSet.iterator().hasNext() ? (Integer)damageSet.iterator().next() : 0;
                            message.append("\n \n").append("         ").append(ChatColor.RESET).append(ChatColor.YELLOW).append("Your Damage: ").append(ChatColor.GREEN + Utils.commaInt(damageDealt) + " Hits" + Sputnik.trans(" &7(Position #" + Dimoon.this.getPlayerPlacement(player) + ")")).append(ChatColor.RESET).append("\n \n");
                            message.append(ChatColor.GREEN).append(ChatColor.BOLD).append("\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
                            player.sendMessage(message.toString());
                        }
                        for (Player player : dimoon.getEntity().getWorld().getPlayers()) {
                            Object[] o = Dimoon.this.calculateDropChance(player, Dimoon.this.getPlayerPlacement(player));
                            if (o == null) continue;
                            ((List<List<DimoonLootItem>>)o[0]).forEach(lt -> lt.forEach(item -> {
                                int random = SUtil.random(8, 10 + (Integer)o[1] / 200);
                                ItemStack itemStack = item.getItem().getStack();
                                if (!item.isRandomizedAmount()) {
                                    itemStack.setAmount(item.getAmount());
                                } else {
                                    itemStack.setAmount(random);
                                }
                                if (DimoonLootTable.highQualitylootTable.contains(item)) {
                                    player.sendMessage(Sputnik.trans("&6&lCONGRATS! &eYou have obtained &8" + itemStack.getAmount() + "x " + item.getItem().getFullName() + "&e! &d&lGG!"));
                                    SUtil.broadcastExcept(Sputnik.trans("&6&lCONGRATS! &a" + player.getName() + " &ehas obtained &8" + itemStack.getAmount() + "x " + item.getItem().getFullName() + "&e! &d&lGG!"), player);
                                } else {
                                    player.sendMessage(Sputnik.trans("&eYou have obtained &8" + itemStack.getAmount() + "x " + item.getItem().getFullName() + "&e!"));
                                    SUtil.broadcastExcept(Sputnik.trans("&a" + player.getName() + " &ehas obtained &8" + itemStack.getAmount() + "x " + item.getItem().getFullName() + "&e!"), player);
                                }
                                Sputnik.smartGiveItem(itemStack, player);
                            }));
                        }
                    }
                    for (BukkitTask task : dimoon.getTasks()) {
                        task.cancel();
                    }
                    SkySimEngine.getPlugin().dimoon = null;
                    SkySimEngine.getPlugin().arena = null;
                    SkySimEngine.getPlugin().sq = new SummoningSequence(Bukkit.getWorld("arena"));
                    SkySimEngine.getPlugin().altarCooldown = true;
                    SUtil.delay(() -> {
                        SkySimEngine.getPlugin().altarCooldown = false;
                        new PlayerListener().updateCatalystsBlock(Bukkit.getWorld("arena"));
                        new PlayerListener().pbA();
                        SUtil.broadcastWorld(Sputnik.trans("&2\u262c &aAll Altars are now ready to use!"), Bukkit.getWorld("arena"));
                    }, 1400L);
                    return;
                }
                if (this.e.isDead()) {
                    this.cancel();
                    return;
                }
                for (Player p : Dimoon.this.entity.getWorld().getPlayers()) {
                    if (p.isOp()) continue;
                    p.setGameMode(GameMode.ADVENTURE);
                }
                for (org.bukkit.entity.Entity en : this.e.getNearbyEntities(100.0, 100.0, 100.0)) {
                    if (!(en instanceof Player)) continue;
                    Location r = this.e.getLocation().setDirection(en.getLocation().toVector().subtract(this.e.getLocation().toVector()));
                    Location lr = this.e.getLocation();
                    lr.setYaw(r.getYaw());
                    lr.setPitch(r.getPitch());
                    this.e.teleport(lr);
                    break;
                }
                Location loc = this.e.getLocation();
                loc.setYaw(this.cout);
                loc.setPitch(0.0f);
                loc.add(loc.getDirection().normalize().multiply(1.3));
                int stage = 3;
                this.e.getWorld().spigot().playEffect(loc, Effect.LARGE_SMOKE, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                this.e.getWorld().spigot().playEffect(loc.clone().add(0.0, 0.6, 0.0), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                if (stage >= 2) {
                    this.e.getWorld().spigot().playEffect(loc.clone().add(0.0, 1.2, 0.0), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                }
                if (stage == 3) {
                    this.e.getWorld().spigot().playEffect(loc.clone().add(0.0, 1.8, 0.0), Effect.LARGE_SMOKE, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                }
                this.cout += 18.0f;
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 1L);
        new BukkitRunnable(){

            public void run() {
                LivingEntity stand = Dimoon.this.entity;
                if (stand.isDead()) {
                    this.cancel();
                    return;
                }
                stand.getWorld().spigot().playEffect(stand.getLocation().clone().add(0.0, 0.75, 0.0), Effect.LARGE_SMOKE, 0, 1, (float)SUtil.random(-2, 2), (float)SUtil.random(-1.5, 1.5), (float)SUtil.random(-2, 2), 0.0f, 1, 20);
                stand.getWorld().spigot().playEffect(stand.getLocation().clone().add(0.0, 0.75, 0.0), Effect.WITCH_MAGIC, 0, 1, (float)SUtil.random(-2, 2), (float)SUtil.random(-1.5, 1.5), (float)SUtil.random(-2, 2), 0.0f, 1, 20);
                stand.getWorld().spigot().playEffect(stand.getLocation().clone().add(0.0, 0.75, 0.0), Effect.LARGE_SMOKE, 0, 1, (float)SUtil.random(-2, 2), (float)SUtil.random(-1.5, 1.5), (float)SUtil.random(-2, 2), 0.0f, 1, 20);
                stand.getWorld().spigot().playEffect(stand.getLocation().clone().add(0.0, 0.75, 0.0), Effect.WITCH_MAGIC, 0, 1, (float)SUtil.random(-2, 2), (float)SUtil.random(-1.5, 1.5), (float)SUtil.random(-2, 2), 0.0f, 1, 20);
                stand.getWorld().spigot().playEffect(new Location(stand.getWorld(), stand.getLocation().getX() + (double)SUtil.random(-2, 2), stand.getLocation().getY() + 1.75 + SUtil.random(-1.5, 1.5), stand.getLocation().getZ() + (double)SUtil.random(-2, 2)), Effect.COLOURED_DUST, 0, 1, 0.99607843f, 0.12941177f, 0.003921569f, 1.0f, 0, 64);
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 2L, 2L);
        new BukkitRunnable(){

            public void run() {
                LivingEntity stand = Dimoon.this.entity;
                if (stand.isDead()) {
                    this.cancel();
                    return;
                }
                if (!Dimoon.this.stunned) {
                    Dimoon.this.entity.getNearbyEntities(22.0, 22.0, 22.0).forEach(ent -> {
                        if (ent instanceof Player) {
                            ent.sendMessage(Sputnik.trans("&cA Magicial Force have pushed you away from Dimoon!"));
                            ent.setVelocity(Dimoon.this.entity.getLocation().toVector().subtract(ent.getLocation().toVector()).normalize().multiply(-1.0).multiply(3.0));
                        }
                    });
                }
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 5L, 5L);
        new BukkitRunnable(){

            public void run() {
                if (Dimoon.this.entity.isDead()) {
                    ArrayList<Player> plist = new ArrayList<Player>();
                    for (Player p : bb.players) {
                        plist.add(p);
                    }
                    plist.forEach(pl -> bb.removePlayer(pl));
                    bb.setProgress(0.0);
                    bb.cancel();
                    this.cancel();
                    return;
                }
                bb.setProgress((double)Dimoon.this.health / 100000.0);
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 1L, 1L);
        this.tasks.add(new BukkitRunnable(){

            public void run() {
                if (!Dimoon.this.stunned) {
                    int y;
                    Location particleLoc;
                    double z;
                    double x;
                    double degrees;
                    for (degrees = 0.0; degrees < 360.0; degrees += 2.0) {
                        x = Math.cos(Math.toRadians(degrees)) * 40.0;
                        z = Math.sin(Math.toRadians(degrees)) * 40.0;
                        particleLoc = Dimoon.this.entity.getLocation().clone().add(x, 0.0, z);
                        for (y = 0; y < 5; ++y) {
                            Dimoon.this.entity.getWorld().spigot().playEffect(new Location(Dimoon.this.entity.getWorld(), (float)particleLoc.getX(), (float)particleLoc.clone().add(0.0, y, 0.0).getY(), (float)particleLoc.getZ()), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                            Dimoon.this.entity.getWorld().spigot().playEffect(new Location(Dimoon.this.entity.getWorld(), (float)particleLoc.getX(), (float)particleLoc.clone().add(0.0, y, 0.0).getY(), (float)particleLoc.getZ()), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                        }
                    }
                    for (degrees = 0.0; degrees < 360.0; degrees += 1.0) {
                        x = Math.cos(Math.toRadians(degrees)) * 60.0;
                        z = Math.sin(Math.toRadians(degrees)) * 60.0;
                        particleLoc = Dimoon.this.entity.getLocation().clone().add(x, 0.0, z);
                        for (y = 0; y < 5; ++y) {
                            Dimoon.this.entity.getWorld().spigot().playEffect(new Location(Dimoon.this.entity.getWorld(), (float)particleLoc.getX(), (float)particleLoc.clone().add(0.0, y, 0.0).getY(), (float)particleLoc.getZ()), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                            Dimoon.this.entity.getWorld().spigot().playEffect(new Location(Dimoon.this.entity.getWorld(), (float)particleLoc.getX(), (float)particleLoc.clone().add(0.0, y, 0.0).getY(), (float)particleLoc.getZ()), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                        }
                    }
                }
                if (!Dimoon.this.stunned) {
                    for (Player p : Dimoon.this.entity.getWorld().getPlayers()) {
                        for (Ability ability : Dimoon.this.abilities) {
                            if (Dimoon.this.cooldowns.get(ability.getClass()) <= 1) {
                                Dimoon.this.cooldowns.replace(ability.getClass(), ability.getCooldown());
                                ability.activate(p, Dimoon.this);
                                continue;
                            }
                            Dimoon.this.cooldowns.replace(ability.getClass(), Dimoon.this.cooldowns.get(ability.getClass()) - 1);
                        }
                    }
                }
            }
        }.runTaskTimer(SkySimEngine.getPlugin(), 0L, 20L));
        this.tasks.add(new BukkitRunnable(){

            public void run() {
                try {
                    SkySimEngine.getPlugin().arena.pasteParkour();
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskLater(SkySimEngine.getPlugin(), 600L));
        this.tasks.add(new BukkitRunnable(){

            public void run() {
                for (Player p : Dimoon.this.entity.getWorld().getPlayers()) {
                    p.setGameMode(GameMode.SPECTATOR);
                }
                Dimoon.this.failed = true;
                Utils.bossMessage("I see, the humans are not actually that strong than i thought before...");
                SUtil.delay(() -> Utils.bossMessage("But, nice try though, Rest in peace"), 40L);
                SUtil.delay(() -> {
                    Dimoon.this.entity.remove();
                    for (Player p : Dimoon.this.entity.getWorld().getPlayers()) {
                        p.playSound(p.getLocation(), Sound.WITHER_DEATH, 1.0f, 1.0f);
                    }
                }, 100L);
                for (BukkitTask task : Dimoon.this.tasks) {
                    task.cancel();
                }
            }
        }.runTaskLater(SkySimEngine.getPlugin(), 54000L));
    }

    void func(Player p) {
        this.damage(12000, p.getName());
        for (Player pl : this.getEntity().getWorld().getPlayers()) {
            pl.sendMessage(Utils.format("&a&lGOOD JOB! &ePlayer &b" + p.getName() + " &ehas defeated one &6Dimooniaze &eand dealing &612,000 &edamage to the Boss!"));
        }
        Utils.bossMessage("That HURT!!");
        this.getEntity().getWorld().playSound(this.getEntity().getLocation(), Sound.WITHER_DEATH, 15.0f, 0.4f);
        this.getEntity().getWorld().playSound(this.getEntity().getLocation(), Sound.EXPLODE, 10.0f, 0.0f);
    }

    public void damage(int damage, String player) {
        for (Map.Entry<Integer, String> entry : this.messages.entrySet()) {
            if (this.health - damage >= entry.getKey() || this.health <= entry.getKey()) continue;
            this.messages.remove(entry.getKey());
            Utils.bossMessage(entry.getValue());
            break;
        }
        this.lastBlow = Bukkit.getPlayer(player).getName();
        if (this.damages.containsValue(player)) {
            Map.Entry<Integer , String> toReplace = null;
            for (Map.Entry<Integer , String> entry : this.damages.entries()) {
                if (!entry.getValue().equals(player)) continue;
                toReplace = entry;
                break;
            }
            this.damages.put(toReplace.getKey() + damage, player);
            this.damages.remove(toReplace.getKey(), toReplace.getValue());
        } else {
            this.damages.put(damage, player);
        }
        this.health -= damage;
        String hits = String.valueOf(this.health);
        if (hits.length() > 3) {
            hits = hits.substring(0, hits.length() - 3) + "," + hits.substring(hits.length() - 3);
        }
    }

    public void heal(int heal) {
        this.health += heal;
    }

    public void completedParkour() {
        ++this.parkoursCompleted;
    }

    private static void noAI(org.bukkit.entity.Entity bukkitEntity) {
        Entity nmsEntity = ((CraftEntity)bukkitEntity).getHandle();
        NBTTagCompound tag = nmsEntity.getNBTTag();
        if (tag == null) {
            tag = new NBTTagCompound();
        }
        nmsEntity.c(tag);
        tag.setInt("NoAI", 1);
        nmsEntity.f(tag);
    }

    Object[] calculateDropChance(Player p, int place) {
        if (place <= 0) {
            return null;
        }
        int catalPlaced = 0;
        UUID[] clist = SkySimEngine.getPlugin().sq.__qch__;
        for (UUID uuid : clist) {
            if (uuid != p.getUniqueId()) continue;
            ++catalPlaced;
        }
        DimoonLootTable ltable = new DimoonLootTable(p, place, catalPlaced);
        return new Object[]{ltable.roll(), ltable.getWeight()};
    }

}

