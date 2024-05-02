/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.World
 */
package vn.giakhanhvn.skysim.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.World;
import vn.giakhanhvn.skysim.SkySimEngine;

public class SQLWorldData {
    private static final SkySimEngine plugin = SkySimEngine.getPlugin();
    private final String SELECT = "SELECT * FROM `worlds` WHERE name=?";
    private final String SELECT_ID = "SELECT * FROM `worlds` WHERE id=?";
    private final String INSERT = "INSERT INTO `worlds` (`id`, `name`) VALUES (?, ?);";
    private final String COUNT = "SELECT COUNT(*) AS rows FROM `worlds`";

    public boolean exists(World world) {
        boolean bl;
        block8: {
            Connection connection = SQLWorldData.plugin.sql.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `worlds` WHERE name=?");
                statement.setString(1, world.getName());
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

    public boolean existsID(int id) {
        boolean bl;
        block8: {
            Connection connection = SQLWorldData.plugin.sql.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `worlds` WHERE id=?");
                statement.setInt(1, id);
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

    public int getWorldID(World world) {
        int n;
        block9: {
            Connection connection = SQLWorldData.plugin.sql.getConnection();
            try {
                PreparedStatement statement;
                if (!this.exists(world)) {
                    statement = connection.prepareStatement("INSERT INTO `worlds` (`id`, `name`) VALUES (?, ?);");
                    statement.setInt(1, this.getWorldCount() + 1);
                    statement.setString(2, world.getName());
                    statement.execute();
                }
                statement = connection.prepareStatement("SELECT * FROM `worlds` WHERE name=?");
                statement.setString(1, world.getName());
                ResultSet set = statement.executeQuery();
                set.next();
                int id = set.getInt("id");
                set.close();
                n = id;
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
                    return -1;
                }
            }
            connection.close();
        }
        return n;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public World getWorld(int id) {
        try (Connection connection = SQLWorldData.plugin.sql.getConnection();){
            if (!this.existsID(id)) {
                World world2 = null;
                return world2;
            }
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `worlds` WHERE id=?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            String name = set.getString("name");
            set.close();
            World world = Bukkit.getWorld((String)name);
            return world;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getWorldCount() {
        int n;
        block8: {
            Connection connection = SQLWorldData.plugin.sql.getConnection();
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS rows FROM `worlds`");
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

