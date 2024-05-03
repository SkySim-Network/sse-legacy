/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 */
package vn.giakhanhvn.skysim.region;

import org.bukkit.Location;
import vn.giakhanhvn.skysim.region.RegionType;

public class RegionGenerator {
    private final String modificationType;
    private String name;
    private Location firstLocation;
    private Location secondLocation;
    private RegionType type;
    private int phase;

    public RegionGenerator(String modificationType, String name, RegionType type) {
        this.modificationType = modificationType;
        this.name = name;
        this.type = type;
        this.phase = 1;
    }

    public String getModificationType() {
        return this.modificationType;
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

    public int getPhase() {
        return this.phase;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstLocation(Location firstLocation) {
        this.firstLocation = firstLocation;
    }

    public void setSecondLocation(Location secondLocation) {
        this.secondLocation = secondLocation;
    }

    public void setType(RegionType type) {
        this.type = type;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }
}

