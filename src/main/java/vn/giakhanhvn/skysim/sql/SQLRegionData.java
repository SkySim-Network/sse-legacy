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

    public boolean exists(String regionName) {
        boolean bl;
        block8: {
            Connection connection = SQLRegionData.plugin.sql.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `regions` WHERE name=?");
                statement.setString(1, regionName);
                ResultSet set = statement.executeQuery();
                bl = set.next();
                if (connection == null) break block8;
            }
            catch (Throwable throwable) {
                try {
                    if (connection != null) {
                        try {
                            connection.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
            connection.close();
        }
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Region get(String name) {
        if (!this.exists(name)) {
            return null;
        }
        try (Connection connection = SQLRegionData.plugin.sql.getConnection();){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `regions` WHERE name=?");
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();
            set.next();
            Location firstLocation = new Location(SQLRegionData.plugin.worldData.getWorld(set.getInt("world")), (double)set.getInt("x1"), (double)set.getInt("y1"), (double)set.getInt("z1"));
            Location secondLocation = new Location(SQLRegionData.plugin.worldData.getWorld(set.getInt("world")), (double)set.getInt("x2"), (double)set.getInt("y2"), (double)set.getInt("z2"));
            RegionType type = RegionType.getType(set.getString("type"));
            if (type == null) {
                Region region2 = null;
                return region2;
            }
            set.close();
            Region region = new Region(name, firstLocation, secondLocation, type);
            return region;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Region> getAllOfType(RegionType type) {
        ArrayList<Region> arrayList;
        block9: {
            Connection connection = SQLRegionData.plugin.sql.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `regions` WHERE type=?");
                statement.setInt(1, type.ordinal());
                ResultSet set = statement.executeQuery();
                ArrayList<Region> regions = new ArrayList<Region>();
                while (set.next()) {
                    String name = set.getString("name");
                    Location firstLocation = new Location(SQLRegionData.plugin.worldData.getWorld(set.getInt("world")), (double)set.getInt("x1"), (double)set.getInt("y1"), (double)set.getInt("z1"));
                    Location secondLocation = new Location(SQLRegionData.plugin.worldData.getWorld(set.getInt("world")), (double)set.getInt("x2"), (double)set.getInt("y2"), (double)set.getInt("z2"));
                    regions.add(new Region(name, firstLocation, secondLocation, type));
                }
                set.close();
                arrayList = regions;
                if (connection == null) break block9;
            }
            catch (Throwable throwable) {
                try {
                    if (connection != null) {
                        try {
                            connection.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
            connection.close();
        }
        return arrayList;
    }

    public List<Region> getAll() {
        ArrayList<Region> arrayList;
        block9: {
            Connection connection = SQLRegionData.plugin.sql.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `regions`");
                ResultSet set = statement.executeQuery();
                ArrayList<Region> regions = new ArrayList<Region>();
                while (set.next()) {
                    regions.add(this.get(set.getString("name")));
                }
                set.close();
                arrayList = regions;
                if (connection == null) break block9;
            }
            catch (Throwable throwable) {
                try {
                    if (connection != null) {
                        try {
                            connection.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
            connection.close();
        }
        return arrayList;
    }

    public Region create(String name, Location firstLocation, Location secondLocation, RegionType type) {
        Region region;
        block9: {
            if (this.exists(name = name.toLowerCase())) {
                return this.get(name);
            }
            Connection connection = SQLRegionData.plugin.sql.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO `regions` (`name`, `x1`, `y1`, `z1`, `x2`, `y2`, `z2`, `world`, `type`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
                statement.setString(1, name);
                statement.setInt(2, (int)firstLocation.getX());
                statement.setInt(3, (int)firstLocation.getY());
                statement.setInt(4, (int)firstLocation.getZ());
                statement.setInt(5, (int)secondLocation.getX());
                statement.setInt(6, (int)secondLocation.getY());
                statement.setInt(7, (int)secondLocation.getZ());
                statement.setInt(8, SQLRegionData.plugin.worldData.getWorldID(firstLocation.getWorld()));
                statement.setString(9, type.name());
                statement.execute();
                region = new Region(name, firstLocation, secondLocation, type);
                if (connection == null) break block9;
            }
            catch (Throwable throwable) {
                try {
                    if (connection != null) {
                        try {
                            connection.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    return null;
                }
            }
            connection.close();
        }
        return region;
    }

    public void save(Region region) {
        try (Connection connection = SQLRegionData.plugin.sql.getConnection();){
            PreparedStatement statement = connection.prepareStatement("UPDATE `regions` SET x1=?, y1=?, z1=?, x2=?, y2=?, z2=?, world=?, type=? WHERE name=?");
            statement.setInt(1, (int)region.getFirstLocation().getX());
            statement.setInt(2, (int)region.getFirstLocation().getY());
            statement.setInt(3, (int)region.getFirstLocation().getZ());
            statement.setInt(4, (int)region.getSecondLocation().getX());
            statement.setInt(5, (int)region.getSecondLocation().getY());
            statement.setInt(6, (int)region.getSecondLocation().getZ());
            statement.setInt(7, SQLRegionData.plugin.worldData.getWorldID(region.getFirstLocation().getWorld()));
            statement.setString(8, region.getType().name());
            statement.setString(9, region.getName());
            statement.execute();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(Region region) {
        try (Connection connection = SQLRegionData.plugin.sql.getConnection();){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM `regions` WHERE name=?");
            statement.setString(1, region.getName());
            statement.execute();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public int getRegionCount() {
        int n;
        block8: {
            Connection connection = SQLRegionData.plugin.sql.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS rows FROM `regions`");
                ResultSet set = statement.executeQuery();
                set.next();
                int count = set.getInt("rows");
                set.close();
                n = count;
                if (connection == null) break block8;
            }
            catch (Throwable throwable) {
                try {
                    if (connection != null) {
                        try {
                            connection.close();
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                    }
                    throw throwable;
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    return 0;
                }
            }
            connection.close();
        }
        return n;
    }
}

