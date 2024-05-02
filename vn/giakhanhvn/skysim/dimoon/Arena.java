/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.sk89q.worldedit.Vector
 *  com.sk89q.worldedit.WorldEditException
 *  com.sk89q.worldedit.blocks.BaseBlock
 *  com.sk89q.worldedit.bukkit.BukkitWorld
 *  com.sk89q.worldedit.extent.clipboard.Clipboard
 *  com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat
 *  com.sk89q.worldedit.extent.clipboard.io.ClipboardReader
 *  com.sk89q.worldedit.util.io.Closer
 *  org.bukkit.Bukkit
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 *  org.bukkit.util.Vector
 */
package vn.giakhanhvn.skysim.dimoon;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.blocks.BaseBlock;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.util.io.Closer;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.dimoon.Altar;
import vn.giakhanhvn.skysim.dimoon.Dimoon;
import vn.giakhanhvn.skysim.dimoon.utils.Utils;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class Arena {
    private int currentParkour = 1;
    private List<Block> parkourBlocks = new ArrayList<Block>();
    private boolean isCollapsing = false;
    public int highestY = -1;
    private BukkitTask parkourTask;

    public void pasteParkour() throws IOException {
        File parkour = new File("plugins/dimoon/parkours/parkour" + this.currentParkour + ".schematic");
        final Dimoon dimoon = SkySimEngine.getPlugin().dimoon;
        if (dimoon == null) {
            return;
        }
        for (Player p : dimoon.getEntity().getWorld().getPlayers()) {
            p.playSound(p.getLocation(), Sound.WITHER_SPAWN, 1.0f, 1.0f);
            p.sendMessage(Utils.format("&c&lALERT! &eParkour level &a" + this.currentParkour + " &espawned in! Try your best, &dyou can do it!!"));
            p.sendMessage(Sputnik.trans("&eHint: &6Reach the &chighest point &6of the parkour to complete it!"));
        }
        Location location = dimoon.getEntity().getLocation();
        BukkitWorld world = new BukkitWorld(location.getWorld());
        Closer closer = Closer.create();
        FileInputStream fis = (FileInputStream)closer.register((Closeable)new FileInputStream(parkour));
        BufferedInputStream bis = (BufferedInputStream)closer.register((Closeable)new BufferedInputStream(fis));
        ClipboardReader reader = ClipboardFormat.SCHEMATIC.getReader((InputStream)bis);
        Clipboard clipboard = reader.read(world.getWorldData());
        int highestYC = this.highestY;
        Location minYLoc = null;
        int minY = Integer.MAX_VALUE;
        for (int x = 0; x < clipboard.getRegion().getWidth(); ++x) {
            for (int y = 0; y < clipboard.getRegion().getHeight(); ++y) {
                for (int z = 0; z < clipboard.getRegion().getLength(); ++z) {
                    Vector minimumPoint = clipboard.getMinimumPoint();
                    Vector clipboardLoc = new Vector(minimumPoint.getBlockX() + x, minimumPoint.getBlockY() + y, minimumPoint.getBlockZ() + z);
                    BaseBlock baseBlock = clipboard.getBlock(clipboardLoc);
                    if (baseBlock.getId() == 0) continue;
                    Location newLocation = location.clone().subtract(57.0, 0.0, 57.0).add((double)x, (double)y, (double)z);
                    Vector loc = new Vector(newLocation.getBlockX(), newLocation.getBlockY(), newLocation.getBlockZ());
                    try {
                        world.setBlock(loc, baseBlock);
                        if (y < minY && baseBlock.getId() != 165 && clipboard.getBlock(clipboardLoc.add(0, 1, 0)).getId() == 0 && newLocation.getBlock().getType().isSolid()) {
                            minY = y;
                            minYLoc = newLocation;
                        }
                        this.parkourBlocks.add(location.getWorld().getBlockAt(newLocation));
                        if (this.currentParkour == 1 && newLocation.getBlock().getType() == Material.BARRIER) {
                            newLocation.getBlock().setType(Material.AIR);
                        }
                        highestYC = Math.max(highestYC, loc.getBlockY());
                        continue;
                    }
                    catch (WorldEditException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        closer.close();
        ArrayList<Block> parkourList = new ArrayList<Block>(this.parkourBlocks);
        this.parkourBlocks.clear();
        for (Block b : parkourList) {
            if (b.getType().isSolid()) continue;
            this.parkourBlocks.add(b);
        }
        for (Block b : parkourList) {
            if (!b.getType().isSolid()) continue;
            this.parkourBlocks.add(b);
        }
        if (this.currentParkour != 1) {
            for (Player p : dimoon.getEntity().getWorld().getPlayers()) {
                p.teleport(minYLoc.add(0.0, 1.0, 0.0));
            }
        } else {
            for (Player p : dimoon.getEntity().getWorld().getPlayers()) {
                p.teleport(new Location(dimoon.getEntity().getWorld(), 234673.5, 155.0, 236425.5));
            }
        }
        int hc = highestYC;
        this.currentParkour = this.currentParkour % 3 + 1;
        SUtil.delay(() -> {
            this.highestY = hc;
        }, 1L);
        final SkySimEngine plugin = SkySimEngine.getPlugin();
        this.parkourTask = new BukkitRunnable(){

            public void run() {
                Arena.this.collapseParkour();
                Arena.this.collapseFloor();
                for (Player p : SkySimEngine.getPlugin().dimoon.getEntity().getWorld().getPlayers()) {
                    p.sendMessage(Utils.format("&cYou couldn't complete the parkour in time, the boss regained 5,000 HP that sucks!"));
                }
                SkySimEngine.getPlugin().arena.highestY = -1;
                dimoon.getTasks().add(new BukkitRunnable(){

                    public void run() {
                        try {
                            SkySimEngine.getPlugin().arena.pasteParkour();
                        }
                        catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 1200L));
                if (plugin.dimoon != null) {
                    plugin.dimoon.heal(5000);
                }
            }
        }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 12000L);
    }

    public void collapseParkour() {
        if (this.isCollapsing) {
            return;
        }
        this.isCollapsing = true;
        final Iterator<Block> iterator = this.parkourBlocks.iterator();
        SkySimEngine plugin = SkySimEngine.getPlugin();
        new BukkitRunnable(){

            public void run() {
                if (!iterator.hasNext()) {
                    Arena.this.isCollapsing = false;
                    this.cancel();
                    Arena.this.parkourBlocks.clear();
                    return;
                }
                Block block = (Block)iterator.next();
                if (block.getType().isSolid()) {
                    block.getWorld().spawnFallingBlock(block.getLocation().add(0.5, 0.0, 0.5), block.getType(), block.getData());
                    block.getWorld().playSound(block.getLocation(), Sound.ZOMBIE_WOODBREAK, 0.5f, 1.0f);
                }
                block.setType(Material.AIR);
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 1L, 1L);
    }

    public void collapseFloor() {
        Dimoon dimoon = SkySimEngine.getPlugin().dimoon;
        if (dimoon == null) {
            return;
        }
    }

    public void spawnDimoonaize(final Entity entity) {
        Utils.bossMessage("DIMOONAIZE! GO!");
        ArrayList<Location> locList = new ArrayList<Location>();
        Dimoon dimoon = SkySimEngine.getPlugin().dimoon;
        if (dimoon == null) {
            return;
        }
        for (int x = 0; x < 120; ++x) {
            for (int z = 0; z < 120; ++z) {
                Location location = dimoon.getEntity().getLocation().clone().add(60.0, 0.0, 60.0).subtract((double)x, 2.0, (double)z);
                Block block = location.getBlock();
                if (block.getType() != Material.BEACON) continue;
                locList.add(block.getLocation().add(0.5, 3.0, 0.5));
            }
        }
        int i = 0;
        for (final Location loc : locList) {
            SUtil.delay(() -> {
                World world = entity.getWorld();
                final org.bukkit.util.Vector[] velocity = new org.bukkit.util.Vector[]{loc.toVector().subtract(entity.getLocation().toVector()).normalize()};
                new BukkitRunnable(){
                    private Location particleLocation;
                    double multiplier;
                    {
                        this.particleLocation = entity.getLocation().add(0.0, 2.0, 0.0).clone();
                        this.multiplier = 1.0;
                    }

                    public void run() {
                        this.particleLocation.add(velocity[0]);
                        velocity[0] = loc.toVector().subtract(this.particleLocation.toVector()).normalize().multiply(this.multiplier);
                        loc.getWorld().spigot().playEffect(this.particleLocation, Effect.LARGE_SMOKE, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                        loc.getWorld().spigot().playEffect(this.particleLocation, Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                        loc.getWorld().playEffect(this.particleLocation, Effect.POTION_SWIRL, 0);
                        loc.getWorld().playEffect(this.particleLocation, Effect.FLYING_GLYPH, 0);
                        loc.getWorld().spigot().playEffect(this.particleLocation, Effect.COLOURED_DUST, 0, 1, 0.99607843f, 0.12941177f, 0.003921569f, 1.0f, 0, 64);
                        loc.getWorld().spigot().playEffect(this.particleLocation, Effect.COLOURED_DUST, 0, 1, 0.99607843f, 0.12941177f, 0.003921569f, 1.0f, 0, 64);
                        loc.getWorld().spigot().playEffect(this.particleLocation, Effect.LARGE_SMOKE, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                        loc.getWorld().spigot().playEffect(this.particleLocation, Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                        loc.getWorld().playEffect(this.particleLocation, Effect.POTION_SWIRL, 0);
                        loc.getWorld().playEffect(this.particleLocation, Effect.FLYING_GLYPH, 0);
                        loc.getWorld().spigot().playEffect(this.particleLocation, Effect.COLOURED_DUST, 0, 1, 0.99607843f, 0.12941177f, 0.003921569f, 1.0f, 0, 64);
                        loc.getWorld().spigot().playEffect(this.particleLocation, Effect.COLOURED_DUST, 0, 1, 0.99607843f, 0.12941177f, 0.003921569f, 1.0f, 0, 64);
                        if (this.particleLocation.distance(loc) < 1.5 || this.particleLocation.distance(loc) < 1.5) {
                            loc.getWorld().playEffect(loc.clone().add(0.0, -1.0, 0.0), Effect.EXPLOSION_HUGE, 0);
                            loc.getWorld().playSound(loc.clone().add(0.0, -1.0, 0.0), Sound.EXPLODE, 1.0f, 1.0f);
                            loc.getWorld().strikeLightningEffect(loc.clone().add(0.0, -1.0, 0.0));
                            new SEntity(loc.clone().add(0.0, -1.0, 0.0), SEntityType.DIMOON_MINIBOSS, new Object[0]);
                            this.cancel();
                        }
                    }
                }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, 1L);
            }, ++i * 5);
        }
    }

    public void fixFloor() {
        Dimoon dimoon = SkySimEngine.getPlugin().dimoon;
        if (dimoon == null) {
            return;
        }
        for (int x = 0; x < 120; ++x) {
            for (int z = 0; z < 120; ++z) {
                Location location = dimoon.getEntity().getLocation().clone().add(60.0, 0.0, 60.0).subtract((double)x, 2.0, (double)z);
                Block block = location.getBlock();
                if (block.getType() != Material.STAINED_CLAY) continue;
                block.setType(Material.SMOOTH_BRICK);
            }
        }
    }

    public static void cleanArena() {
        for (int i = 1; i < 4; ++i) {
            try {
                Arena.rp(i);
                continue;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        Sputnik.pasteSchematicRep("egg3", true, 234666.0f, 155.0f, 236479.0f, Bukkit.getWorld((String)"arena")).forEach(block -> block.setType(Material.AIR));
        Block[] a = Altar.altarList;
        for (int i = 0; i < a.length; ++i) {
            Block c = Bukkit.getWorld((String)"arena").getBlockAt(a[i].getLocation().clone().add(0.0, 1.0, 0.0));
            c.setType(Material.AIR);
        }
    }

    private static void rp(int s) throws IOException {
        File parkour = new File("plugins/dimoon/parkours/parkour" + s + ".schematic");
        Location location = new Location(Bukkit.getWorld((String)"arena"), 234668.5, 155.0, 236481.5);
        BukkitWorld world = new BukkitWorld(location.getWorld());
        Closer closer = Closer.create();
        FileInputStream fis = (FileInputStream)closer.register((Closeable)new FileInputStream(parkour));
        BufferedInputStream bis = (BufferedInputStream)closer.register((Closeable)new BufferedInputStream(fis));
        ClipboardReader reader = ClipboardFormat.SCHEMATIC.getReader((InputStream)bis);
        Clipboard clipboard = reader.read(world.getWorldData());
        for (int x = 0; x < clipboard.getRegion().getWidth(); ++x) {
            for (int y = 0; y < clipboard.getRegion().getHeight(); ++y) {
                for (int z = 0; z < clipboard.getRegion().getLength(); ++z) {
                    Vector minimumPoint = clipboard.getMinimumPoint();
                    Vector clipboardLoc = new Vector(minimumPoint.getBlockX() + x, minimumPoint.getBlockY() + y, minimumPoint.getBlockZ() + z);
                    BaseBlock baseBlock = clipboard.getBlock(clipboardLoc);
                    if (baseBlock.getId() == 0) continue;
                    Location newLocation = location.clone().subtract(57.0, 0.0, 57.0).add((double)x, (double)y, (double)z);
                    Vector loc = new Vector(newLocation.getBlockX(), newLocation.getBlockY(), newLocation.getBlockZ());
                    try {
                        world.setBlock(loc, baseBlock);
                        location.getWorld().getBlockAt(newLocation).setType(Material.AIR);
                        continue;
                    }
                    catch (WorldEditException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        closer.close();
    }

    public BukkitTask getParkourTask() {
        return this.parkourTask;
    }
}

