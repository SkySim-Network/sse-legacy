/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Effect
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.orb;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.PlayerBoostStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.orb.PowerOrb;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;

public class ManaFluxPowerOrb
extends PowerOrb {
    @Override
    public String getAbilityDescription() {
        return "Place an orb for " + ChatColor.GREEN + "30s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. " + ChatColor.DARK_GRAY + "Costs " + ChatColor.DARK_GRAY + "50% of max mana. " + ChatColor.DARK_GRAY + "Only " + ChatColor.DARK_GRAY + "one orb applies per player.";
    }

    @Override
    public String getURL() {
        return "82ada1c7fcc8cf35defeb944a4f8ffa9a9d260560fc7f5f5826de8085435967c";
    }

    @Override
    public String getDisplayName() {
        return "Mana Flux Power Orb";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.RARE;
    }

    @Override
    public String getBuffName() {
        return "Mana Flux";
    }

    @Override
    public String getBuffDescription() {
        return "Grants " + ChatColor.AQUA + "+50% " + ChatColor.GRAY + "base mana regen. Heals " + ChatColor.RED + "2% " + ChatColor.GRAY + "of max " + ChatColor.RED + "\u2764 " + ChatColor.GRAY + "per second. Grants " + ChatColor.RED + "+10 \u2741 " + ChatColor.RED + "Strength";
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    protected void buff(Player player) {
        player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + player.getMaxHealth() * 0.02));
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
        PlayerUtils.boostPlayer(statistics, new PlayerBoostStatistics(){

            @Override
            public String getDisplayName() {
                return null;
            }

            @Override
            public Rarity getRarity() {
                return null;
            }

            @Override
            public GenericItemType getType() {
                return null;
            }

            @Override
            public double getBaseStrength() {
                return 10.0;
            }
        }, 20L);
        if (!player.hasMetadata("NPC")) {
            statistics.boostManaRegeneration(0.5, 20L);
        }
    }

    @Override
    protected long getOrbLifeTicks() {
        return 600L;
    }

    @Override
    protected void playEffect(Location location) {
        location.getWorld().spigot().playEffect(location, Effect.COLOURED_DUST, 0, 1, 0.1882353f, 0.5411765f, 0.8509804f, 1.0f, 0, 64);
    }
}

