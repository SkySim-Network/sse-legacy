/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockState
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.region.RegionType;
import vn.giakhanhvn.skysim.util.SUtil;

public class Region {
    private static final Map<String, Region> REGION_CACHE = new HashMap<String, Region>();
    protected static final SkySimEngine plugin = SkySimEngine.getPlugin();
    protected final String name;
    @Setter
    protected Location firstLocation;
    @Setter
    protected Location secondLocation;
    @Setter
    protected RegionType type;
    private List<BlockState> capture;

    public Region(String name, Location firstLocation, Location secondLocation, RegionType type) {
        this.name = name.toLowerCase();
        this.firstLocation = firstLocation;
        this.secondLocation = secondLocation;
        this.type = type;
        this.capture = null;
        REGION_CACHE.put(this.name, this);
    }

    public void save() {
        Region.plugin.regionData.save(this);
    }

    public void delete() {
        REGION_CACHE.remove(this.name);
        Region.plugin.regionData.delete(this);
    }

    public static List<Entity> getPlayersWithinRegionType(RegionType type) {
        ArrayList<Entity> players = new ArrayList<Entity>();
        for (Region region : Region.getRegionsOfType(type)) {
            players.addAll(region.getPlayersWithinRegion());
        }
        return players;
    }

    public static Region getRegionOfEntity(Entity entity) {
        ArrayList<Region> possible = new ArrayList<Region>();
        for (Region region : Region.getRegions()) {
            if (!region.insideRegion(entity)) continue;
            possible.add(region);
        }
        possible.sort(Comparator.comparingInt(r -> r.getType().ordinal()));
        Collections.reverse(possible);
        return possible.size() != 0 ? (Region)possible.get(0) : null;
    }

    public static Region getQuickRegionOfEntity(Entity entity) {
        for (Region region : Region.getRegions()) {
            if (!region.insideRegion(entity)) continue;
            return region;
        }
        return null;
    }

    public static Region getRegionOfBlock(Block block) {
        ArrayList<Region> possible = new ArrayList<Region>();
        for (Region region : Region.getRegions()) {
            if (!region.insideRegion(block)) continue;
            possible.add(region);
        }
        possible.sort(Comparator.comparingInt(r -> r.getType().ordinal()));
        Collections.reverse(possible);
        return possible.size() != 0 ? (Region)possible.get(0) : null;
    }

    public boolean insideRegion(Entity entity) {
        List<Integer> bounds = this.getBounds();
        Location location = entity.getLocation();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        if (this.firstLocation == null || this.firstLocation.getWorld() == null) {
            return false;
        }
        return this.firstLocation.getWorld().getUID().equals(location.getWorld().getUID()) && x >= (double)bounds.get(0).intValue() && x <= (double)bounds.get(1).intValue() && y >= (double)bounds.get(2).intValue() && y <= (double)bounds.get(3).intValue() && z >= (double)bounds.get(4).intValue() && z <= (double)bounds.get(5).intValue();
    }

    public boolean insideRegion(Block block) {
        List<Integer> bounds = this.getBounds();
        Location location = block.getLocation();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        if (this.firstLocation == null || this.firstLocation.getWorld() == null) {
            return false;
        }
        return this.firstLocation.getWorld().getUID().equals(location.getWorld().getUID()) && x >= (double)bounds.get(0).intValue() && x <= (double)bounds.get(1).intValue() && y >= (double)bounds.get(2).intValue() && y <= (double)bounds.get(3).intValue() && z >= (double)bounds.get(4).intValue() && z <= (double)bounds.get(5).intValue();
    }

    public List<Location> getAvailableTeleportLocations() {
        ArrayList<Location> locations = new ArrayList<Location>();
        for (Location location : this.getLocations()) {
            Block block = location.getBlock();
            if (block.getType() == Material.AIR || block.getType() == Material.WATER) continue;
            Block above = location.clone().add(0.0, 1.0, 0.0).getBlock();
            Block top = location.clone().add(0.0, 2.0, 0.0).getBlock();
            if (above.getType() != Material.AIR && above.getType() != Material.WATER || top.getType() != Material.AIR && top.getType() != Material.WATER) continue;
            locations.add(above.getLocation());
        }
        return locations;
    }

    public List<Location> getLocations() {
        if (!this.firstLocation.getWorld().getName().equals(this.secondLocation.getWorld().getName())) {
            return null;
        }
        List<Integer> bounds = this.getBounds();
        World world = this.firstLocation.getWorld();
        ArrayList<Location> locations = new ArrayList<Location>();
        for (int y = bounds.get(2).intValue(); y <= bounds.get(3); ++y) {
            for (int x = bounds.get(0).intValue(); x <= bounds.get(1); ++x) {
                for (int z = bounds.get(4).intValue(); z <= bounds.get(5); ++z) {
                    locations.add(new Location(world, (double)x, (double)y, (double)z));
                }
            }
        }
        return locations;
    }

    public void captureRegion() {
        if (!this.firstLocation.getWorld().getName().equals(this.secondLocation.getWorld().getName())) {
            return;
        }
        List<Integer> bounds = this.getBounds();
        World world = this.firstLocation.getWorld();
        ArrayList<BlockState> states = new ArrayList<BlockState>();
        for (int y = bounds.get(2).intValue(); y <= bounds.get(3); ++y) {
            for (int x = bounds.get(0).intValue(); x <= bounds.get(1); ++x) {
                for (int z = bounds.get(4).intValue(); z <= bounds.get(5); ++z) {
                    states.add(new Location(world, (double)x, (double)y, (double)z).getBlock().getState());
                }
            }
        }
        this.capture = states;
    }

    public void pasteRegionCapture() {
        if (this.capture == null) {
            return;
        }
        for (BlockState state : this.capture) {
            state.getBlock().setType(state.getType());
            state.setRawData(state.getRawData());
            state.update();
        }
        this.capture = null;
    }

    public Location getRandomLocation() {
        List<Integer> bounds = this.getBounds();
        return new Location(this.firstLocation.getWorld(), (double)SUtil.random(bounds.get(0), bounds.get(1)), (double)SUtil.random(bounds.get(2), bounds.get(3)), (double)SUtil.random(bounds.get(4), bounds.get(5)));
    }

    public Location getRandomAvailableLocation() {
        Location r = this.getRandomLocation();
        ArrayList<Location> possible = new ArrayList<Location>();
        for (int y = this.getBounds().get(3).intValue(); y >= this.getBounds().get(2); --y) {
            Block test = this.firstLocation.getWorld().getBlockAt(r.getBlockX(), y, r.getBlockZ());
            if (test.getType() == Material.AIR || test.getLocation().clone().add(0.0, 1.0, 0.0).getBlock().getType() != Material.AIR || test.getLocation().clone().add(0.0, 2.0, 0.0).getBlock().getType() != Material.AIR) continue;
            possible.add(test.getLocation().clone().add(0.0, 1.0, 0.0));
        }
        return !possible.isEmpty() ? (Location)SUtil.getRandom(possible) : null;
    }

    public List<Integer> getBounds() {
        int sx = Math.min(this.firstLocation.getBlockX(), this.secondLocation.getBlockX());
        int ex = Math.max(this.firstLocation.getBlockX(), this.secondLocation.getBlockX());
        int sy = Math.min(this.firstLocation.getBlockY(), this.secondLocation.getBlockY());
        int ey = Math.max(this.firstLocation.getBlockY(), this.secondLocation.getBlockY());
        int sz = Math.min(this.firstLocation.getBlockZ(), this.secondLocation.getBlockZ());
        int ez = Math.max(this.firstLocation.getBlockZ(), this.secondLocation.getBlockZ());
        return Arrays.asList(sx, ex, sy, ey, sz, ez);
    }

    public List<Entity> getPlayersWithinRegion() {
        ArrayList<Entity> entities = new ArrayList<>(this.firstLocation.getWorld().getEntitiesByClasses(Player.class));
        return entities.stream().filter(this::insideRegion).collect(Collectors.toList());
    }

    public static Region create(String name, Location firstLocation, Location secondLocation, RegionType type) {
        return Region.plugin.regionData.create(name, firstLocation, secondLocation, type);
    }

    public static Region get(String name) {
        if (REGION_CACHE.containsKey(name)) {
            return REGION_CACHE.get(name);
        }
        return Region.plugin.regionData.get(name);
    }

    public static List<Region> getRegions() {
        return new ArrayList<Region>(REGION_CACHE.values());
    }

    public static List<Region> getRegionsOfType(RegionType type) {
        return Region.getRegions().stream().filter(region -> region.getType() == type).collect(Collectors.toList());
    }

    public static void cacheRegions() {
        for (Region region : Region.plugin.regionData.getAll()) {
            REGION_CACHE.put(region.getName(), region);
        }
    }

    public String getName() {
        return this.name;
    }

    public Location getFirstLocation() {
        return this.firstLocation;
    }

    public Location getSecondLocation() {
        return this.secondLocation;
    }

    public RegionType getType() {
        return this.type;
    }

    public List<BlockState> getCapture() {
        return this.capture;
    }
}

