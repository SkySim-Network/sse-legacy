/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.PacketStatusOutPong
 */
package vn.giakhanhvn.skysim.nms.pingrep;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.server.v1_8_R3.PacketStatusOutPong;
import vn.giakhanhvn.skysim.nms.pingrep.PingEvent;
import vn.giakhanhvn.skysim.nms.pingrep.PingReply;
import vn.giakhanhvn.skysim.nms.pingrep.PongPacket;

public class PongPacketHandler
extends PongPacket {
    public PongPacketHandler(PingEvent reply) {
        super(reply);
    }

    @Override
    public void send() {
        try {
            PingReply reply = this.getEvent().getReply();
            PacketStatusOutPong packet = new PacketStatusOutPong();
            Field field = this.getEvent().getReply().getClass().getDeclaredField("ctx");
            field.setAccessible(true);
            Object ctx = field.get(reply);
            Method writeAndFlush = ctx.getClass().getMethod("writeAndFlush", Object.class);
            writeAndFlush.setAccessible(true);
            writeAndFlush.invoke(ctx, packet);
        }
        catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

