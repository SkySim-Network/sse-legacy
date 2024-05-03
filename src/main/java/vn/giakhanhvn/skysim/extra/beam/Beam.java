/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.extra.beam;

import com.google.common.base.Preconditions;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.extra.beam.LocationTargetBeam;

public class Beam {
    private final String worldname;
    private final double viewingRadiusSquared;
    private final long updateDelay;
    private boolean isActive;
    private final LocationTargetBeam beam;
    private Location startingPosition;
    private Location endingPosition;
    private final Set<UUID> viewers;
    private BukkitRunnable runnable;

    public Beam(Location startingPosition, Location endingPosition) {
        this(startingPosition, endingPosition, 100.0, 5L);
    }

    public Beam(Location startingPosition, Location endingPosition, double viewingRadius, long updateDelay) {
        Preconditions.checkNotNull((Object)startingPosition, (Object)"startingPosition cannot be null");
        Preconditions.checkNotNull((Object)endingPosition, (Object)"endingPosition cannot be null");
        Preconditions.checkState((boolean)startingPosition.getWorld().equals(endingPosition.getWorld()), (Object)"startingPosition and endingPosition must be in the same world");
        Preconditions.checkArgument((viewingRadius > 0.0 ? 1 : 0) != 0, (Object)"viewingRadius must be positive");
        Preconditions.checkArgument((updateDelay >= 1L ? 1 : 0) != 0, (Object)"viewingRadius must be a natural number");
        this.worldname = startingPosition.getWorld().getName();
        this.viewingRadiusSquared = viewingRadius * viewingRadius;
        this.updateDelay = updateDelay;
        this.isActive = false;
        this.beam = new LocationTargetBeam(startingPosition, endingPosition);
        this.startingPosition = startingPosition;
        this.endingPosition = endingPosition;
        this.viewers = new HashSet<UUID>();
    }

    public void start() {
        Preconditions.checkState((!this.isActive ? 1 : 0) != 0, (Object)"The beam must be disabled in order to start it");
        this.isActive = true;
        this.runnable = new BeamUpdater();
        this.runnable.runTaskTimer((Plugin)SkySimEngine.getPlugin(), 0L, this.updateDelay);
    }

    public void stop() {
        Preconditions.checkState((boolean)this.isActive, (Object)"The beam must be enabled in order to stop it");
        this.isActive = false;
        for (UUID uuid : this.viewers) {
            Player player = Bukkit.getPlayer((UUID)uuid);
            if (player == null || !player.getWorld().getName().equalsIgnoreCase(this.worldname) || !this.isCloseEnough(player.getLocation())) continue;
            this.beam.cleanup(player);
        }
        this.viewers.clear();
        this.runnable.cancel();
        this.runnable = null;
    }

    public void setStartingPosition(Location location) {
        Preconditions.checkArgument((boolean)location.getWorld().getName().equalsIgnoreCase(this.worldname), (Object)"location must be in the same world as this beam");
        this.startingPosition = location;
        Iterator<UUID> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            UUID uuid = iterator.next();
            Player player = Bukkit.getPlayer((UUID)uuid);
            if (!(player != null && player.isOnline() && player.getWorld().getName().equalsIgnoreCase(this.worldname) && this.isCloseEnough(player.getLocation()))) {
                iterator.remove();
                continue;
            }
            this.beam.setStartingPosition(player, location);
        }
    }

    public void setEndingPosition(Location location) {
        Preconditions.checkArgument((boolean)location.getWorld().getName().equalsIgnoreCase(this.worldname), (Object)"location must be in the same world as this beam");
        this.endingPosition = location;
        Iterator<UUID> iterator = this.viewers.iterator();
        while (iterator.hasNext()) {
            UUID uuid = iterator.next();
            Player player = Bukkit.getPlayer((UUID)uuid);
            if (!(player.isOnline() && player.getWorld().getName().equalsIgnoreCase(this.worldname) && this.isCloseEnough(player.getLocation()))) {
                iterator.remove();
                continue;
            }
            this.beam.setEndingPosition(player, location);
        }
    }

    public void update() {
        if (this.isActive) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                UUID uuid = player.getUniqueId();
                if (!player.getWorld().getName().equalsIgnoreCase(this.worldname)) {
                    this.viewers.remove(uuid);
                    continue;
                }
                if (this.isCloseEnough(player.getLocation())) {
                    if (this.viewers.contains(uuid)) continue;
                    this.beam.start(player);
                    this.viewers.add(uuid);
                    continue;
                }
                if (!this.viewers.contains(uuid)) continue;
                this.beam.cleanup(player);
                this.viewers.remove(uuid);
            }
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    public boolean isViewing(Player player) {
        return this.viewers.contains(player.getUniqueId());
    }

    private boolean isCloseEnough(Location location) {
        return this.startingPosition.distanceSquared(location) <= this.viewingRadiusSquared || this.endingPosition.distanceSquared(location) <= this.viewingRadiusSquared;
    }

    public boolean isShitAss() {
        return true;
    }

    private class BeamUpdater
    extends BukkitRunnable {
        private BeamUpdater() {
        }

        public void run() {
            Beam.this.update();
        }
    }
}

