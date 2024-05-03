/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 */
package vn.giakhanhvn.skysim.nms.pingrep;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.Bukkit;
import vn.giakhanhvn.skysim.nms.pingrep.PingReply;
import vn.giakhanhvn.skysim.nms.pingrep.PongPacket;
import vn.giakhanhvn.skysim.nms.pingrep.ServerInfoPacket;

public class PingEvent {
    private PingReply reply;
    private boolean cancelEvent;
    private boolean cancelPong;

    public PingEvent(PingReply reply) {
        this.reply = reply;
    }

    public PingReply getReply() {
        return this.reply;
    }

    public void setCancelled(boolean cancel) {
        this.cancelEvent = cancel;
    }

    public void cancelPong(boolean cancel) {
        this.cancelPong = cancel;
    }

    public boolean isCancelled() {
        return this.cancelEvent;
    }

    public boolean isPongCancelled() {
        return this.cancelPong;
    }

    public ServerInfoPacket createNewPacket(PingReply reply) {
        try {
            String name = Bukkit.getServer().getClass().getPackage().getName();
            String version = name.substring(name.lastIndexOf(46) + 1);
            Class<?> packet = Class.forName("vn.giakhanhvn.skysim.nms.pingrep." + version + ".ServerInfoPacketHandler");
            Constructor<?> constructor = packet.getDeclaredConstructor(reply.getClass());
            return (ServerInfoPacket)constructor.newInstance(reply);
        }
        catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendPong() {
        try {
            String name = Bukkit.getServer().getClass().getPackage().getName();
            String version = name.substring(name.lastIndexOf(46) + 1);
            Class<?> packet = Class.forName("vn.giakhanhvn.skysim.nms.pingrep." + version + ".PongPacketHandler");
            Constructor<?> constructor = packet.getDeclaredConstructor(this.getClass());
            PongPacket pong = (PongPacket)constructor.newInstance(this);
            pong.send();
        }
        catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

