/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.io.ByteArrayDataOutput
 *  com.google.common.io.ByteStreams
 *  me.skysim.SLog
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package vn.giakhanhvn.skysim.nms.nmsutil.apihelper;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.util.SLog;

public class SkySimBungee {
    private String channel;
    private List<String> servers = new ArrayList<String>();

    public SkySimBungee(String channel) {
        this.channel = channel;
    }

    public static SkySimBungee getNewBungee() {
        return new SkySimBungee("BungeeCord");
    }

    public void sendData(Player p, String subchannel, String args) {
        Player sender = null;
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(subchannel);
        if (args != null) {
            out.writeUTF(args);
        }
        if ((sender = p == null ? (Player)Iterables.getFirst((Iterable)Bukkit.getOnlinePlayers(), null) : p) != null) {
            sender.sendPluginMessage((Plugin)SkySimEngine.getPlugin(), this.channel, out.toByteArray());
        } else {
            SLog.warn((Object)"Player object mustn't be null!");
        }
    }
}

