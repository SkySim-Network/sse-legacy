/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 */
package vn.giakhanhvn.skysim.item;

import com.google.common.util.concurrent.AtomicDouble;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import vn.giakhanhvn.skysim.item.SItem;

public interface MaterialFunction {
    default public void onInteraction(PlayerInteractEvent e) {
    }

    default public void onInventoryClick(SItem instance, InventoryClickEvent e) {
    }

    default public void onDamage(Entity damaged, Player damager, AtomicDouble damage, SItem item) {
    }

    default public void onKill(Entity damaged, Player damager, SItem item) {
    }

    default public void whileHolding(Player holding) {
    }

    default public void onInstanceUpdate(SItem instance) {
    }
}

