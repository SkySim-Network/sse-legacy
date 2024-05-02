/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Cancellable
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 *  org.bukkit.plugin.Plugin
 */
package vn.giakhanhvn.skysim.nms.nmsutil.packetlistener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import vn.giakhanhvn.skysim.nms.nmsutil.apihelper.API;
import vn.giakhanhvn.skysim.nms.nmsutil.apihelper.APIManager;
import vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.ChannelInjector;
import vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.IPacketListener;
import vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.channel.ChannelWrapper;
import vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.handler.PacketHandler;
import vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.handler.ReceivedPacket;
import vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.handler.SentPacket;
import vn.giakhanhvn.skysim.util.SLog;

public class PacketHelper
implements IPacketListener,
Listener,
API {
    private ChannelInjector channelInjector;
    public boolean injected = false;

    @Override
    public void load() {
        this.channelInjector = new ChannelInjector();
        this.injected = this.channelInjector.inject(this);
        if (this.injected) {
            this.channelInjector.addServerChannel();
            SLog.info("Injected custom channel handlers.");
        } else {
            SLog.severe("Failed to inject channel handlers");
        }
    }

    @Override
    public void init(Plugin plugin) {
        APIManager.registerEvents(this, this);
        SLog.info("Adding channels for online players...");
        for (Player player : Bukkit.getOnlinePlayers()) {
            this.channelInjector.addChannel(player);
        }
    }

    @Override
    public void disable(Plugin plugin) {
        if (!this.injected) {
            return;
        }
        SLog.info("Removing channels for online players...");
        for (Player player : Bukkit.getOnlinePlayers()) {
            this.channelInjector.removeChannel(player);
        }
        SLog.info("Removing packet handlers (" + PacketHandler.getHandlers().size() + ")...");
        while (!PacketHandler.getHandlers().isEmpty()) {
            PacketHandler.removeHandler(PacketHandler.getHandlers().get(0));
        }
    }

    public static boolean addPacketHandler(PacketHandler handler) {
        return PacketHandler.addHandler(handler);
    }

    public static boolean removePacketHandler(PacketHandler handler) {
        return PacketHandler.removeHandler(handler);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        this.channelInjector.addChannel(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        this.channelInjector.removeChannel(e.getPlayer());
    }

    @Override
    public Object onPacketReceive(Object sender, Object packet, Cancellable cancellable) {
        ReceivedPacket receivedPacket = sender instanceof Player ? new ReceivedPacket(packet, cancellable, (Player)sender) : new ReceivedPacket(packet, cancellable, (ChannelWrapper)sender);
        PacketHandler.notifyHandlers(receivedPacket);
        if (receivedPacket.getPacket() != null) {
            return receivedPacket.getPacket();
        }
        return packet;
    }

    @Override
    public Object onPacketSend(Object receiver, Object packet, Cancellable cancellable) {
        SentPacket sentPacket = receiver instanceof Player ? new SentPacket(packet, cancellable, (Player)receiver) : new SentPacket(packet, cancellable, (ChannelWrapper)receiver);
        PacketHandler.notifyHandlers(sentPacket);
        if (sentPacket.getPacket() != null) {
            return sentPacket.getPacket();
        }
        return packet;
    }
}

