/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package vn.giakhanhvn.skysim.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.entity.SEntity;
import vn.giakhanhvn.skysim.entity.SEntityType;
import vn.giakhanhvn.skysim.region.Region;
import vn.giakhanhvn.skysim.region.RegionType;
import vn.giakhanhvn.skysim.util.SUtil;

public class EntityPopulator {
    private static final List<EntityPopulator> POPULATORS = new ArrayList<EntityPopulator>();
    private final int amount;
    private final int max;
    private final long delay;
    private final SEntityType type;
    private final Predicate<World> condition;
    private final RegionType regionType;
    private BukkitTask task;
    private final List<SEntity> spawned;

    public static List<EntityPopulator> getPopulators() {
        return POPULATORS;
    }

    public EntityPopulator(int amount, int max, long delay, SEntityType type, RegionType regionType, Predicate<World> condition) {
        this.amount = amount;
        this.max = max;
        this.delay = delay;
        this.type = type;
        this.regionType = regionType;
        this.spawned = new ArrayList<SEntity>();
        this.condition = condition;
        POPULATORS.add(this);
    }

    public EntityPopulator(int amount, int max, long delay, SEntityType type, RegionType regionType) {
        this(amount, max, delay, type, regionType, null);
    }

    public void start() {
        this.task = new BukkitRunnable(){

            public void run() {
                EntityPopulator.this.spawned.removeIf(sEntity -> sEntity.getEntity().isDead());
                List<Region> regions = Region.getRegionsOfType(EntityPopulator.this.regionType);
                if (regions.isEmpty()) {
                    return;
                }
                if (Region.getPlayersWithinRegionType(EntityPopulator.this.regionType).isEmpty()) {
                    for (SEntity s : EntityPopulator.this.spawned) {
                        s.remove();
                    }
                    EntityPopulator.this.spawned.clear();
                    return;
                }
                if (EntityPopulator.this.condition != null && !EntityPopulator.this.condition.test(SUtil.getRandom(regions).getFirstLocation().getWorld())) {
                    return;
                }
                if (EntityPopulator.this.spawned.size() >= EntityPopulator.this.max) {
                    return;
                }
                for (int i = 0; i < EntityPopulator.this.amount; ++i) {
                    Location available;
                    int attempts = 0;
                    while ((available = SUtil.getRandom(regions).getRandomAvailableLocation()) == null && ++attempts <= 150) {
                    }
                    if (available == null) continue;
                    EntityPopulator.this.spawned.add(new SEntity(available.clone().add(0.5, 0.0, 0.5), EntityPopulator.this.type, new Object[0]));
                }
            }
        }.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, this.delay);
    }

    public void stop() {
        if (this.task == null) {
            return;
        }
        this.task.cancel();
    }

    public static void stopAll() {
        for (EntityPopulator populator : POPULATORS) {
            populator.stop();
        }
    }

    public RegionType getRegionType() {
        return this.regionType;
    }
}

