/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.Cancellable
 */
package vn.giakhanhvn.skysim.nms.nmsutil.packetlistener;

public class Cancellable
implements org.bukkit.event.Cancellable {
    private boolean cancelled;

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean b) {
        this.cancelled = b;
    }
}

