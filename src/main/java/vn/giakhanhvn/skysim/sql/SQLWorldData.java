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

    public boolean exists(final World world) {
        try (final Connection connection = SQLWorldData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `worlds` WHERE name=?");
            statement.setString(1, world.getName());
            final ResultSet set = statement.executeQuery();
            return set.next();
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean existsID(final int id) {
        try (final Connection connection = SQLWorldData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `worlds` WHERE id=?");
            statement.setInt(1, id);
            final ResultSet set = statement.executeQuery();
            return set.next();
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getWorldID(final World world) {
        try (final Connection connection = SQLWorldData.plugin.sql.getConnection()) {
            if (!this.exists(world)) {
                final PreparedStatement statement = connection.prepareStatement("INSERT INTO `worlds` (`id`, `name`) VALUES (?, ?);");
                statement.setInt(1, this.getWorldCount() + 1);
                statement.setString(2, world.getName());
                statement.execute();
            }
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `worlds` WHERE name=?");
            statement.setString(1, world.getName());
            final ResultSet set = statement.executeQuery();
            set.next();
            final int id = set.getInt("id");
            set.close();
            return id;
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public World getWorld(final int id) {
        try (final Connection connection = SQLWorldData.plugin.sql.getConnection()) {
            if (!this.existsID(id)) {
                final World world = null;
                if (connection != null) {
                    connection.close();
                }
                return world;
            }
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `worlds` WHERE id=?");
            statement.setInt(1, id);
            final ResultSet set = statement.executeQuery();
            set.next();
            final String name = set.getString("name");
            set.close();
            return Bukkit.getWorld(name);
        } catch (final SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getWorldCount() {
        try (final Connection connection = SQLWorldData.plugin.sql.getConnection()) {
            final PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS rows FROM `worlds`");
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
