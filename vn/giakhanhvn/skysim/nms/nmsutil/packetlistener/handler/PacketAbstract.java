/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Cancellable
 */
package vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.handler;

import java.util.ArrayList;
import java.util.Collection;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.channel.ChannelWrapper;
import vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.handler.SentPacket;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.FieldResolver;

public abstract class PacketAbstract {
    private Player player;
    private ChannelWrapper channelWrapper;
    private Object packet;
    private Cancellable cancellable;
    protected FieldResolver fieldResolver;

    public PacketAbstract(Object packet, Cancellable cancellable, Player player) {
        this.player = player;
        this.packet = packet;
        this.cancellable = cancellable;
        this.fieldResolver = new FieldResolver(packet.getClass());
    }

    public PacketAbstract(Object packet, Cancellable cancellable, ChannelWrapper channelWrapper) {
        this.channelWrapper = channelWrapper;
        this.packet = packet;
        this.cancellable = cancellable;
        this.fieldResolver = new FieldResolver(packet.getClass());
    }

    public void writeField(String field, Object value) {
        try {
            this.fieldResolver.resolve(field).set(this.getPacket(), value);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setPacketValueSilent(String field, Object value) {
        try {
            this.fieldResolver.resolve(field).set(this.getPacket(), value);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void write(int index, Object value) {
        try {
            this.fieldResolver.resolveIndex(index).set(this.getPacket(), value);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setPacketValueSilent(int index, Object value) {
        try {
            this.fieldResolver.resolveIndex(index).set(this.getPacket(), value);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public Object getPacketValue(String field) {
        try {
            return this.fieldResolver.resolve(field).get(this.getPacket());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getPacketValueSilent(String field) {
        try {
            return this.fieldResolver.resolve(field).get(this.getPacket());
        }
        catch (Exception exception) {
            return null;
        }
    }

    public Object read(int index) {
        try {
            return this.fieldResolver.resolveIndex(index).get(this.getPacket());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getPacketValueSilent(int index) {
        try {
            return this.fieldResolver.resolveIndex(index).get(this.getPacket());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public FieldResolver getFieldResolver() {
        return this.fieldResolver;
    }

    public void setCancelled(boolean b) {
        this.cancellable.setCancelled(b);
    }

    public boolean isCancelled() {
        return this.cancellable.isCancelled();
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean hasPlayer() {
        return this.player != null;
    }

    public ChannelWrapper<?> getChannel() {
        return this.channelWrapper;
    }

    public boolean hasChannel() {
        return this.channelWrapper != null;
    }

    public String getPlayername() {
        if (!this.hasPlayer()) {
            return null;
        }
        return this.player.getName();
    }

    public void setPacket(Object packet) {
        this.packet = packet;
    }

    public Object getPacket() {
        return this.packet;
    }

    public Player getPlayerInvolved() {
        Collection q = Bukkit.getOnlinePlayers();
        ArrayList t = new ArrayList(q);
        return (Player)t.get(0);
    }

    public String getPacketName() {
        return this.packet.getClass().getSimpleName();
    }

    public String toString() {
        return "Packet{ " + (this.getClass().equals(SentPacket.class) ? "[> OUT >]" : "[< IN <]") + " " + this.getPacketName() + " " + (this.hasPlayer() ? this.getPlayername() : (this.hasChannel() ? this.getChannel().channel() : "#server#")) + " }";
    }
}

