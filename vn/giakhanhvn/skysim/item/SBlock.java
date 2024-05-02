/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  org.bukkit.Location
 *  org.bukkit.configuration.ConfigurationSection
 */
package vn.giakhanhvn.skysim.item;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.util.SUtil;

public class SBlock {
    protected static final SkySimEngine plugin = SkySimEngine.getPlugin();
    private final Location location;
    private SMaterial type;
    private NBTTagCompound data;

    public SBlock(Location location, SMaterial type, NBTTagCompound data) {
        this.location = location;
        this.type = type;
        this.data = data;
    }

    public float getDataFloat(String key) {
        return this.data.getFloat(key);
    }

    public long getDataLong(String key) {
        return this.data.getLong(key);
    }

    public double getDataDouble(String key) {
        return this.data.getDouble(key);
    }

    public String getDataString(String key) {
        return this.data.getString(key);
    }

    public static SBlock getBlock(Location location) {
        ConfigurationSection cs = SBlock.plugin.blocks.getConfigurationSection(SBlock.toLocationString(location));
        if (cs == null) {
            return null;
        }
        NBTTagCompound compound = new NBTTagCompound();
        for (String key : cs.getKeys(false)) {
            if (key.equals("type")) continue;
            compound.set(key, SUtil.getBaseFromObject(cs, key));
        }
        return new SBlock(location, SMaterial.getMaterial(cs.getString("type")), compound);
    }

    public void save() {
        SBlock.plugin.blocks.set(this.toLocationString() + ".type", this.type.name());
        for (String key : this.data.c()) {
            Object o = SUtil.getObjectFromCompound(this.data, key);
            if (o instanceof NBTTagCompound) continue;
            SBlock.plugin.blocks.set(this.toLocationString() + "." + key, o);
        }
        SBlock.plugin.blocks.save();
    }

    public void delete() {
        SBlock.plugin.blocks.set(this.toLocationString(), null);
        SBlock.plugin.blocks.save();
    }

    private String toLocationString() {
        return SBlock.toLocationString(this.location);
    }

    private static String toLocationString(Location location) {
        return location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ() + "," + location.getWorld().getName();
    }

    public Location getLocation() {
        return this.location;
    }

    public SMaterial getType() {
        return this.type;
    }

    public NBTTagCompound getData() {
        return this.data;
    }

    public void setType(SMaterial type) {
        this.type = type;
    }
}

