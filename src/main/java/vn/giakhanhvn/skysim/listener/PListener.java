/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 */
package vn.giakhanhvn.skysim.listener;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import vn.giakhanhvn.skysim.SkySimEngine;

public class PListener
implements Listener {
    private static int amount = 0;
    protected SkySimEngine plugin = SkySimEngine.getPlugin();

    protected PListener() {
        this.plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this.plugin);
        ++amount;
    }

    public static int getAmount() {
        return amount;
    }
}

