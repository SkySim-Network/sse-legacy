/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.GameMode
 *  org.bukkit.entity.Player
 *  org.bukkit.event.player.PlayerFishEvent
 *  org.bukkit.event.player.PlayerFishEvent$State
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 */
package vn.giakhanhvn.skysim.item.oddities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import vn.giakhanhvn.skysim.SkySimEngine;
import vn.giakhanhvn.skysim.item.FishingRodFunction;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.util.Sputnik;

public class GrapplingHook
implements MaterialStatistics,
FishingRodFunction {
    private static final List<UUID> COOLDOWN = new ArrayList<UUID>();

    @Override
    public void onFish(SItem instance, PlayerFishEvent e) {
        PlayerFishEvent.State state = e.getState();
        if (state != PlayerFishEvent.State.FAILED_ATTEMPT && state != PlayerFishEvent.State.IN_GROUND) {
            return;
        }
        final Player player = e.getPlayer();
        if (!Sputnik.tpAbilUsable(player)) {
            player.sendMessage(ChatColor.RED + "Dimoon destroyed your hook! Yikes!");
            return;
        }
        if (COOLDOWN.contains(player.getUniqueId())) {
            player.sendMessage(ChatColor.RED + "Whow! Slow down there!");
            return;
        }
        player.setVelocity(player.getLocation().toVector().subtract(e.getHook().getLocation().toVector()).multiply(-1.0).multiply(0.5).setY(0.9));
        if (player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
            COOLDOWN.add(player.getUniqueId());
            new BukkitRunnable(){

                public void run() {
                    COOLDOWN.remove(player.getUniqueId());
                }
            }.runTaskLater((Plugin)SkySimEngine.getPlugin(), 40L);
        }
    }

    @Override
    public String getDisplayName() {
        return "Grappling Hook";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public String getLore() {
        return "Travel around in style using this Grappling Hook. 2 Second Cooldown";
    }
}

