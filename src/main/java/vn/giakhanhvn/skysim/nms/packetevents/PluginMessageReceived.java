/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.event.Event
 *  org.bukkit.event.HandlerList
 */
package vn.giakhanhvn.skysim.nms.packetevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import vn.giakhanhvn.skysim.nms.packetevents.WrappedPluginMessage;

public class PluginMessageReceived
extends Event {
    private static final HandlerList handlers = new HandlerList();
    private WrappedPluginMessage a;

    public PluginMessageReceived(WrappedPluginMessage b) {
        this.a = b;
    }

    public WrappedPluginMessage getWrappedPluginMessage() {
        return this.a;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

