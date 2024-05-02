/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Effect
 *  org.bukkit.GameMode
 *  org.bukkit.Instrument
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Note
 *  org.bukkit.Note$Tone
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.ArmorStand
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Projectile
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageCause
 *  org.bukkit.event.player.PlayerChangedWorldEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.event.player.PlayerMoveEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.event.player.PlayerTeleportEvent
 *  org.bukkit.event.player.PlayerTeleportEvent$TeleportCause
 *  org.bukkit.metadata.FixedMetadataValue
 *  org.bukkit.metadata.MetadataValue
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.dimoon.listeners;

import java.io.IOException;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.dimoon.Altar;
import vn.giakhanhvn.skysim.dimoon.Arena;
import vn.giakhanhvn.skysim.dimoon.Dimoon;
import vn.giakhanhvn.skysim.dimoon.utils.Utils;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class PlayerListener
implements Listener {
    @EventHandler
    public void onPlayerDealDamage(EntityDamageByEntityEvent event) {
        Projectile projectile;
        Player damager = null;
        int bonusDamage = 0;
        if (event.getDamager() instanceof Player) {
            damager = (Player)event.getDamager();
            bonusDamage += 3;
        } else if (event.getDamager() instanceof Projectile && (projectile = (Projectile)event.getDamager()).getShooter() instanceof Player) {
            damager = (Player)projectile.getShooter();
        }
        if (damager != null && event.getEntity().hasMetadata("Dimoon")) {
            Dimoon dimoon = SkySimEngine.getPlugin().dimoon;
            event.setDamage(0.0);
            int damage = 1 + dimoon.getParkoursCompleted() + bonusDamage;
            dimoon.damage(damage, damager.getName());
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        final SkySimEngine plugin = SkySimEngine.getPlugin();
        if (plugin.dimoon != null) {
            final Arena arena = plugin.arena;
            if (player.getWorld() == plugin.dimoon.getEntity().getWorld()) {
                Location withoutY = player.getLocation().clone();
                withoutY.setY(plugin.dimoon.getEntity().getLocation().getY());
                if (withoutY.distance(plugin.dimoon.getEntity().getLocation()) < 40.0 && !plugin.dimoon.stunned) {
                    new BukkitRunnable(){
                        int counter = 0;

                        public void run() {
                            if (plugin.dimoon == null) {
                                this.cancel();
                                return;
                            }
                            Location withoutY = player.getLocation().clone();
                            withoutY.setY(plugin.dimoon.getEntity().getLocation().getY());
                            if (withoutY.distance(plugin.dimoon.getEntity().getLocation()) > 40.0 || player.isDead() || player.getGameMode() == GameMode.SPECTATOR) {
                                User.getUser(player.getUniqueId()).setInDanger(false);
                                PlayerListener.worldBorder(player, false);
                                this.cancel();
                                return;
                            }
                            User.getUser(player.getUniqueId()).setInDanger(true);
                            PlayerListener.worldBorder(player, true);
                            ++this.counter;
                            if (this.counter == 4) {
                                player.playNote(player.getLocation(), Instrument.PIANO, Note.natural((int)0, (Note.Tone)Note.Tone.A));
                            }
                            if (this.counter == 3) {
                                this.cancel();
                                User.getUser(player.getUniqueId()).damage(player.getMaxHealth() * 30.0 / 100.0, EntityDamageEvent.DamageCause.ENTITY_ATTACK, (Entity)SkySimEngine.getPlugin().dimoon.getEntity());
                            }
                        }
                    }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 20L);
                }
                if (withoutY.distance(plugin.dimoon.getEntity().getLocation()) > 60.0) {
                    new BukkitRunnable(){
                        int counter = 0;

                        public void run() {
                            if (plugin.dimoon == null) {
                                this.cancel();
                                return;
                            }
                            Location withoutY = player.getLocation().clone();
                            withoutY.setY(plugin.dimoon.getEntity().getLocation().getY());
                            if (withoutY.distance(plugin.dimoon.getEntity().getLocation()) < 60.0 || player.isDead()) {
                                User.getUser(player.getUniqueId()).setInDanger(false);
                                PlayerListener.worldBorder(player, false);
                                this.cancel();
                                return;
                            }
                            User.getUser(player.getUniqueId()).setInDanger(true);
                            PlayerListener.worldBorder(player, true);
                            ++this.counter;
                            if (this.counter == 4) {
                                player.playNote(player.getLocation(), Instrument.PIANO, Note.natural((int)0, (Note.Tone)Note.Tone.A));
                            }
                            if (this.counter == 3) {
                                this.cancel();
                                User.getUser(player.getUniqueId()).damage(player.getMaxHealth() * 30.0 / 100.0, EntityDamageEvent.DamageCause.ENTITY_ATTACK, (Entity)SkySimEngine.getPlugin().dimoon.getEntity());
                            }
                        }
                    }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 20L);
                }
                if (arena.highestY != -1 && player.getLocation().getY() >= (double)arena.highestY) {
                    for (Player p : plugin.dimoon.getEntity().getWorld().getPlayers()) {
                        User.getUser(player.getUniqueId()).setInDanger(false);
                        p.sendMessage(Utils.format("&a&lGOOD JOB! &ePlayer &b" + player.getName() + " &ehas completed the parkour dealing &610,000 &edamage, increasing all damages dealt by &61 &eand stunning the boss for the next &630 &eseconds!"));
                    }
                    Utils.bossMessage("That REALLY HURT! STOP!");
                    plugin.dimoon.getEntity().getWorld().playSound(plugin.dimoon.getEntity().getLocation(), Sound.WITHER_DEATH, 15.0f, 0.4f);
                    plugin.dimoon.getEntity().getWorld().playSound(plugin.dimoon.getEntity().getLocation(), Sound.EXPLODE, 10.0f, 0.0f);
                    plugin.dimoon.stunned = true;
                    plugin.dimoon.damage(10000, player.getName());
                    plugin.dimoon.completedParkour();
                    arena.highestY = -1;
                    arena.fixFloor();
                    arena.collapseParkour();
                    arena.getParkourTask().cancel();
                    new BukkitRunnable(){

                        public void run() {
                            if (plugin.dimoon == null) {
                                return;
                            }
                            for (Player p : plugin.dimoon.getEntity().getWorld().getPlayers()) {
                                p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 1.0f, 0.5f);
                                p.sendMessage(Utils.format("&c&lALERT! &eThe boss is about to reactivate its &cdeadly aura &ein &c10 &eseconds&e! RUN!"));
                            }
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 400L);
                    new BukkitRunnable(){

                        public void run() {
                            if (plugin.dimoon == null) {
                                return;
                            }
                            plugin.dimoon.stunned = false;
                            Utils.bossMessage("Nice try... Don't even... think of it again!");
                            plugin.dimoon.getEntity().getWorld().playSound(plugin.dimoon.getEntity().getLocation(), Sound.WITHER_DEATH, 15.0f, 1.5f);
                            for (Player p : plugin.dimoon.getEntity().getWorld().getPlayers()) {
                                p.sendMessage(Utils.format("&c&lALERT! &6Wither Aura &eactivated! Crossing those aura will deal &cinsane damage &eto you!"));
                            }
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 600L);
                    plugin.dimoon.getTasks().add(new BukkitRunnable(){

                        public void run() {
                            if (plugin.dimoon == null) {
                                return;
                            }
                            try {
                                arena.highestY = -1;
                                SkySimEngine.getPlugin().arena.pasteParkour();
                            }
                            catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 1800L));
                }
            } else {
                User.getUser(player.getUniqueId()).setInDanger(false);
            }
        }
    }

    public static void worldBorder(Player p, boolean on) {
    }

    @EventHandler
    public void onCatalPlace(PlayerInteractEvent e) {
        int slot;
        if (!e.getPlayer().getWorld().getName().contains("arena")) {
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.OBSIDIAN && (slot = this.getAltar(e.getClickedBlock())) != -1) {
            if (User.getUser(e.getPlayer().getUniqueId()).getCooldownAltar() > 0) {
                e.getPlayer().sendMessage(Sputnik.trans("&cYou need to wait &e" + User.getUser(e.getPlayer().getUniqueId()).getCooldownAltar() + "s &cto access the altar again!"));
                e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 0.0f);
                return;
            }
            SkySimEngine.getPlugin().sq.interactCatalyst(e.getPlayer(), slot, true);
            if (!SkySimEngine.getPlugin().altarCooldown) {
                this.updateCatalystsBlock(e.getPlayer().getWorld());
            }
        }
    }

    SItem getItemInHand(Player p) {
        return SItem.find(p.getItemInHand());
    }

    @EventHandler
    public void onMoveWorld(PlayerChangedWorldEvent e) {
        Player player = e.getPlayer();
        if (SkySimEngine.getPlugin().sq != null && !SkySimEngine.getPlugin().sq.isBossSpawned() && !SkySimEngine.getPlugin().sq.isBossSpawning()) {
            SkySimEngine.getPlugin().sq.pickupAllCatalysts(player);
            this.updateCatalystsBlock(Bukkit.getWorld((String)"arena"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (SkySimEngine.getPlugin().sq != null && !SkySimEngine.getPlugin().sq.isBossSpawned() && !SkySimEngine.getPlugin().sq.isBossSpawning()) {
            SkySimEngine.getPlugin().sq.pickupAllCatalysts(player);
            this.updateCatalystsBlock(Bukkit.getWorld((String)"arena"));
        }
    }

    public void pbA() {
        Block[] a = Altar.altarList;
        for (int i = 0; i < a.length; ++i) {
            Location loc = a[i].getLocation().clone().add(0.5, 1.0, 0.5);
            loc.getWorld().playSound(loc, Sound.GLASS, 1.0f, 1.0f);
            for (int j = 0; j < 30; ++j) {
                loc.getWorld().spigot().playEffect(loc, Effect.EXPLOSION, 0, 1, (float)SUtil.random(-0.5, 0.5), (float)SUtil.random(0.0, 0.5), (float)SUtil.random(-0.5, 0.5), 0.0f, 1, 100);
            }
        }
    }

    boolean isAltar(Block b) {
        Block[] a = Altar.altarList;
        for (int i = 0; i < a.length; ++i) {
            Location bl1 = b.getLocation();
            Location bl2 = a[i].getLocation();
            if (bl1.getX() != bl2.getX() || bl1.getY() != bl2.getY() || bl1.getZ() != bl2.getZ()) continue;
            return true;
        }
        return false;
    }

    int getAltar(Block b) {
        if (!this.isAltar(b)) {
            return -1;
        }
        Block[] a = Altar.altarList;
        for (int i = 0; i < a.length; ++i) {
            Location bl1 = b.getLocation();
            Location bl2 = a[i].getLocation();
            if (bl1.getX() != bl2.getX() || bl1.getY() != bl2.getY() || bl1.getZ() != bl2.getZ()) continue;
            return i;
        }
        return -1;
    }

    public void updateCatalystsBlock(World w) {
        if (SkySimEngine.getPlugin().altarCooldown) {
            return;
        }
        Block[] a = Altar.altarList;
        UUID[] cache = SkySimEngine.getPlugin().sq.__qch__;
        for (int i = 0; i < a.length; ++i) {
            Block c = w.getBlockAt(a[i].getLocation().clone().add(0.0, 1.0, 0.0));
            if (cache[i] != null) {
                if (c.getType() == Material.STAINED_GLASS) continue;
                this.spawnCatalCore(c.getLocation(), i);
                c.setType(Material.STAINED_GLASS);
                c.setData((byte)10);
                continue;
            }
            c.setType(Material.AIR);
            if (!this.isCatalCoreExist(w, i)) continue;
            this.removeCatalCore(w, i);
        }
    }

    void spawnCatalCore(Location loc, final int catalystID) {
        if (this.isCatalCoreExist(loc.getWorld(), catalystID)) {
            return;
        }
        loc.add(0.5, 0.0, 0.5);
        final ArmorStand core = (ArmorStand)loc.getWorld().spawn(loc.clone().add(0.0, -1.2, 0.0), ArmorStand.class);
        core.setVisible(false);
        core.setMarker(true);
        core.setGravity(false);
        core.setHelmet(SUtil.getSkullURLStack("a", "74e8ff30e3937098637c0af03a1f2a6b17f0e828ab2a57a267a01da484ba0c57", 1, ""));
        core.setMetadata("cid_" + catalystID, (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        core.setMetadata("inv", (MetadataValue)new FixedMetadataValue((Plugin)SkySimEngine.getPlugin(), (Object)true));
        new BukkitRunnable(){

            public void run() {
                if (core.isDead()) {
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 10; ++i) {
                    core.getWorld().spigot().playEffect(core.getLocation().add(0.0, 1.8, 0.0).clone().add(SUtil.random(-0.5, 0.5), SUtil.random(-0.5, 0.5), SUtil.random(-0.5, 0.5)), Effect.COLOURED_DUST, 0, 1, 0.68235296f, 0.1882353f, 0.6901961f, 1.0f, 0, 640);
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 6L);
        new BukkitRunnable(){
            double yaw;
            final double archived_yaw;
            {
                this.yaw = core.getLocation().getYaw();
                this.archived_yaw = core.getLocation().getYaw();
            }

            public void run() {
                if (core.isDead()) {
                    this.cancel();
                    return;
                }
                SkySimEngine sse = SkySimEngine.getPlugin();
                if (sse.sq != null) {
                    if (PlayerListener.this.s(catalystID) && !sse.sq.isBossSpawned()) {
                        this.yaw += 16.0;
                        Location nloc = core.getLocation();
                        nloc.setYaw((float)this.yaw);
                        core.teleport(nloc);
                        for (int i = 0; i < 3; ++i) {
                            core.getWorld().spigot().playEffect(core.getLocation().add(0.0, 1.8, 0.0).clone().add(SUtil.random(-0.5, 0.5), SUtil.random(-0.5, 0.5), SUtil.random(-0.5, 0.5)), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 640);
                        }
                    } else if (!PlayerListener.this.s(catalystID)) {
                        Location nloc = core.getLocation();
                        nloc.setYaw((float)this.archived_yaw);
                        core.teleport(nloc);
                    }
                    if (sse.sq.isBossSpawned()) {
                        core.setHelmet(null);
                    }
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 2L);
    }

    boolean s(int z) {
        if (z == 0 || z == 1) {
            return SkySimEngine.getPlugin().sq.isAcD();
        }
        if (z == 2 || z == 3) {
            return SkySimEngine.getPlugin().sq.isAcE();
        }
        if (z == 4 || z == 5) {
            return SkySimEngine.getPlugin().sq.isAcG();
        }
        if (z == 6 || z == 7) {
            return SkySimEngine.getPlugin().sq.isAcR();
        }
        return false;
    }

    boolean isCatalCoreExist(World w, int id) {
        for (Entity e : w.getEntities()) {
            if (!e.hasMetadata("cid_" + id)) continue;
            return true;
        }
        return false;
    }

    void removeCatalCore(World w, int id) {
        for (Entity e : w.getEntities()) {
            if (!e.hasMetadata("cid_" + id)) continue;
            e.remove();
        }
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getCause().equals((Object)PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            event.setCancelled(true);
        }
    }
}

