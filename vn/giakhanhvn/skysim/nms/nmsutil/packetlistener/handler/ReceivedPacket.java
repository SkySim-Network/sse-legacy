/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Cancellable
 */
package vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.handler;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.channel.ChannelWrapper;
import vn.giakhanhvn.skysim.nms.nmsutil.packetlistener.handler.PacketAbstract;

public class ReceivedPacket
extends PacketAbstract {
    public ReceivedPacket(Object packet, Cancellable cancellable, Player player) {
        super(packet, cancellable, player);
    }

    public ReceivedPacket(Object packet, Cancellable cancellable, ChannelWrapper channelWrapper) {
        super(packet, cancellable, channelWrapper);
    }
}

