/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 */
package vn.giakhanhvn.skysim.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.region.Region;
import vn.giakhanhvn.skysim.region.RegionType;

public class SQLRegionData {
    private static final SkySimEngine plugin = SkySimEngine.getPlugin();
    private final String SELECT = "SELECT * FROM `regions` WHERE name=?";
    private final String SELECT_TYPE = "SELECT * FROM `regions` WHERE type=?";
    private final String INSERT = "INSERT INTO `regions` (`name`, `x1`, `y1`, `z1`, `x2`, `y2`, `z2`, `world`, `type`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String UPDATE = "UPDATE `regions` SET x1=?, y1=?, z1=?, x2=?, y2=?, z2=?, world=?, type=? WHERE name=?";
    private final String COUNT = "SELECT COUNT(*) AS rows FROM `regions`";
    private final String DELETE = "DELETE FROM `regions` WHERE name=?";

    public boolean exists(final String regionName) {
        try (final Connection connection = SQLRegionData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `regions` WHERE name=?");
            statement.setString(1, regionName);
            final ResultSet set = statement.executeQuery();
            return set.next();
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Region get(final String name) {
        if (!this.exists(name)) {
            return null;
        }
        try (final Connection connection = SQLRegionData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `regions` WHERE name=?");
            statement.setString(1, name);
            final ResultSet set = statement.executeQuery();
            set.next();
            final Location firstLocation = new Location(SQLRegionData.plugin.worldData.getWorld(set.getInt("world")), set.getInt("x1"), set.getInt("y1"), set.getInt("z1"));
            final Location secondLocation = new Location(SQLRegionData.plugin.worldData.getWorld(set.getInt("world")), set.getInt("x2"), set.getInt("y2"), set.getInt("z2"));
            final RegionType type = RegionType.getType(set.getString("type"));
            if (type == null) {
                final Region region = null;
                if (connection != null) {
                    connection.close();
                }
                return region;
            }
            set.close();
            return new Region(name, firstLocation, secondLocation, type);
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Region> getAllOfType(final RegionType type) {
        try (final Connection connection = SQLRegionData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `regions` WHERE type=?");
            statement.setInt(1, type.ordinal());
            final ResultSet set = statement.executeQuery();
            final List<Region> regions = new ArrayList<Region>();
            while (set.next()) {
                final String name = set.getString("name");
                final Location firstLocation = new Location(SQLRegionData.plugin.worldData.getWorld(set.getInt("world")), set.getInt("x1"), set.getInt("y1"), set.getInt("z1"));
                final Location secondLocation = new Location(SQLRegionData.plugin.worldData.getWorld(set.getInt("world")), set.getInt("x2"), set.getInt("y2"), set.getInt("z2"));
                regions.add(new Region(name, firstLocation, secondLocation, type));
            }
            set.close();
            return regions;
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Region> getAll() {
        try (final Connection connection = SQLRegionData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `regions`");
            final ResultSet set = statement.executeQuery();
            final List<Region> regions = new ArrayList<Region>();
            while (set.next()) {
                regions.add(this.get(set.getString("name")));
            }
            set.close();
            return regions;
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Region create(String name, final Location firstLocation, final Location secondLocation, final RegionType type) {
        name = name.toLowerCase();
        if (this.exists(name)) {
            return this.get(name);
        }
        try (final Connection connection = SQLRegionData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("INSERT INTO `regions` (`name`, `x1`, `y1`, `z1`, `x2`, `y2`, `z2`, `world`, `type`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
            statement.setString(1, name);
            statement.setInt(2, (int) firstLocation.getX());
            statement.setInt(3, (int) firstLocation.getY());
            statement.setInt(4, (int) firstLocation.getZ());
            statement.setInt(5, (int) secondLocation.getX());
            statement.setInt(6, (int) secondLocation.getY());
            statement.setInt(7, (int) secondLocation.getZ());
            statement.setInt(8, SQLRegionData.plugin.worldData.getWorldID(firstLocation.getWorld()));
            statement.setString(9, type.name());
            statement.execute();
            return new Region(name, firstLocation, secondLocation, type);
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void save(final Region region) {
        try (final Connection connection = SQLRegionData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("UPDATE `regions` SET x1=?, y1=?, z1=?, x2=?, y2=?, z2=?, world=?, type=? WHERE name=?");
            statement.setInt(1, (int) region.getFirstLocation().getX());
            statement.setInt(2, (int) region.getFirstLocation().getY());
            statement.setInt(3, (int) region.getFirstLocation().getZ());
            statement.setInt(4, (int) region.getSecondLocation().getX());
            statement.setInt(5, (int) region.getSecondLocation().getY());
            statement.setInt(6, (int) region.getSecondLocation().getZ());
            statement.setInt(7, SQLRegionData.plugin.worldData.getWorldID(region.getFirstLocation().getWorld()));
            statement.setString(8, region.getType().name());
            statement.setString(9, region.getName());
            statement.execute();
        } catch (final SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(final Region region) {
        try (final Connection connection = SQLRegionData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM `regions` WHERE name=?");
            statement.setString(1, region.getName());
            statement.execute();
        } catch (final SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getRegionCount() {
        try (final Connection connection = SQLRegionData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS rows FROM `regions`");
            final ResultSet set = statement.executeQuery();
            set.next();
            final int count = set.getInt("rows");
            set.close();
            return count;
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
