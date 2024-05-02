/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.channel.ChannelDuplexHandler
 *  io.netty.channel.ChannelHandlerContext
 *  io.netty.channel.ChannelPromise
 *  net.minecraft.server.v1_8_R3.PacketStatusOutPong
 *  net.minecraft.server.v1_8_R3.PacketStatusOutServerInfo
 */
package vn.giakhanhvn.skysim.nms.pingrep;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.server.v1_8_R3.PacketStatusOutPong;
import net.minecraft.server.v1_8_R3.PacketStatusOutServerInfo;
import vn.giakhanhvn.skysim.nms.pingrep.PingAPI;
import vn.giakhanhvn.skysim.nms.pingrep.PingEvent;
import vn.giakhanhvn.skysim.nms.pingrep.PingListener;
import vn.giakhanhvn.skysim.nms.pingrep.PingReply;
import vn.giakhanhvn.skysim.nms.pingrep.ServerInfoPacketHandler;

public class DuplexHandler
extends ChannelDuplexHandler {
    private PingEvent event;

    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof PacketStatusOutServerInfo) {
            PacketStatusOutServerInfo packet = (PacketStatusOutServerInfo)msg;
            PingReply reply = ServerInfoPacketHandler.constructReply(packet, ctx);
            this.event = new PingEvent(reply);
            for (PingListener listener : PingAPI.getListeners()) {
                listener.onPing(this.event);
            }
            if (!this.event.isCancelled()) {
                super.write(ctx, (Object)ServerInfoPacketHandler.constructPacket(reply), promise);
            }
            return;
        }
        if (msg instanceof PacketStatusOutPong && this.event != null && this.event.isPongCancelled()) {
            return;
        }
        super.write(ctx, msg, promise);
    }

    public void close(ChannelHandlerContext ctx, ChannelPromise future) throws Exception {
        if (this.event == null || !this.event.isPongCancelled()) {
            super.close(ctx, future);
        }
    }
}

