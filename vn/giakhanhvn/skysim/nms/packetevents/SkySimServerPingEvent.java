/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.Cancellable
 *  org.bukkit.event.Event
 *  org.bukkit.event.HandlerList
 */
package vn.giakhanhvn.skysim.nms.packetevents;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import vn.giakhanhvn.skysim.nms.pingrep.PingEvent;
import vn.giakhanhvn.skysim.nms.pingrep.PingReply;

public class SkySimServerPingEvent
extends Event
implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private PingEvent a;

    public SkySimServerPingEvent(PingEvent event) {
        this.a = event;
    }

    public PingReply getPingReply() {
        return this.a.getReply();
    }

    public boolean isCancelled() {
        return this.a.isCancelled();
    }

    public void setCancelled(boolean cancel) {
        this.a.setCancelled(cancel);
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

