/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.event.HandlerList
 *  org.bukkit.event.player.PlayerEvent
 */
package vn.giakhanhvn.skysim.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import vn.giakhanhvn.skysim.gui.GUI;

public class GUICloseEvent
extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();
    private final GUI closed;

    public GUICloseEvent(Player player, GUI opened) {
        super(player);
        this.closed = opened;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public GUI getClosed() {
        return this.closed;
    }
}

