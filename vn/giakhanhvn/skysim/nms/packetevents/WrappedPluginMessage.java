/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.nms.packetevents;

import org.bukkit.entity.Player;

public class WrappedPluginMessage {
    private String channelName;
    private Player player;
    private byte[] message;

    public WrappedPluginMessage(String cn, Player p, byte[] msg) {
        this.channelName = cn;
        this.player = p;
        this.message = msg;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public Player getPlayer() {
        return this.player;
    }

    public byte[] getMessage() {
        return this.message;
    }
}

