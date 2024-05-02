/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 */
package vn.giakhanhvn.skysim.nms.pingrep;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.nms.pingrep.PingInjector;
import vn.giakhanhvn.skysim.nms.pingrep.PingListener;
import vn.giakhanhvn.skysim.util.SLog;

public class PingAPI {
    private static List<PingListener> listeners;

    public static void register() {
        try {
            listeners = new ArrayList<PingListener>();
            Class<PingInjector> injector = PingInjector.class;
            Bukkit.getPluginManager().registerEvents((Listener)injector.newInstance(), (Plugin)SkySimEngine.getPlugin());
            SLog.info("Successfully hooked into " + Bukkit.getServer().getName());
        }
        catch (IllegalAccessException | IllegalArgumentException | InstantiationException | SecurityException e) {
            SLog.severe("Non compatible server version!");
            Bukkit.getPluginManager().disablePlugin((Plugin)SkySimEngine.getPlugin());
        }
    }

    public static void registerListener(PingListener listener) {
        listeners.add(listener);
    }

    public static List<PingListener> getListeners() {
        return listeners;
    }
}

