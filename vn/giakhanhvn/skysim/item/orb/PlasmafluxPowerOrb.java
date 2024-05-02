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

public class PlasmafluxPowerOrb
extends PowerOrb {
    @Override
    public String getAbilityDescription() {
        return "Place an orb for " + ChatColor.GREEN + "60s " + ChatColor.GRAY + "buffing up to " + ChatColor.AQUA + "5 " + ChatColor.GRAY + "players within " + ChatColor.GREEN + "18 " + ChatColor.GRAY + "blocks. " + ChatColor.DARK_GRAY + "Costs " + ChatColor.DARK_GRAY + "50% of max mana. " + ChatColor.DARK_GRAY + "Only " + ChatColor.DARK_GRAY + "one orb applies per player.";
    }

    @Override
    public String getURL() {
        return "83ed4ce23933e66e04df16070644f7599eeb55307f7eafe8d92f40fb3520863c";
    }

    @Override
    public String getDisplayName() {
        return "Plasmaflux Power Orb";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public String getBuffName() {
        return "Plasmaflux";
    }

    @Override
    public String getBuffDescription() {
        return "Grants " + ChatColor.AQUA + "+125% " + ChatColor.GRAY + "base mana regen. Heals " + ChatColor.RED + "3% " + ChatColor.GRAY + "of max " + ChatColor.RED + "\u2764 " + ChatColor.GRAY + "per second. Increases all heals by " + ChatColor.GREEN + "7%" + ChatColor.GRAY + ". Grants " + ChatColor.RED + "+35 " + ChatColor.RED + "\u2741 Strength";
    }

    @Override
    public String getCustomOrbName() {
        return "" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Plasmaflux";
    }

    @Override
    protected void buff(Player player) {
        player.setHealth(Math.min(player.getMaxHealth(), player.getHealth() + player.getMaxHealth() * 0.03));
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
                return 35.0;
            }
        }, 20L);
        if (!player.hasMetadata("NPC")) {
            statistics.boostManaRegeneration(1.25, 20L);
            statistics.boostHealthRegeneration(0.075, 20L);
        }
    }

    @Override
    protected long getOrbLifeTicks() {
        return 1200L;
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    protected void playEffect(Location location) {
        location.getWorld().spigot().playEffect(location, Effect.COLOURED_DUST, 0, 1, 0.28235295f, 0.007843138f, 0.4f, 1.0f, 0, 64);
    }
}

