/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.Cancellable
 */
package vn.giakhanhvn.skysim.nms.nmsutil.packetlistener;

import org.bukkit.event.Cancellable;

public interface IPacketListener {
    public Object onPacketSend(Object var1, Object var2, Cancellable var3);

    public Object onPacketReceive(Object var1, Object var2, Cancellable var3);
}

