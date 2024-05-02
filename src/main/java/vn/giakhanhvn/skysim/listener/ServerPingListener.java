/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.server.ServerListPingEvent
 */
package vn.giakhanhvn.skysim.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerListPingEvent;
import vn.giakhanhvn.skysim.listener.PListener;

public class ServerPingListener
extends PListener {
    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        e.setMotd(ChatColor.GOLD + "SkySim Version 0.7.0 BETA, Starting up...");
    }
}

