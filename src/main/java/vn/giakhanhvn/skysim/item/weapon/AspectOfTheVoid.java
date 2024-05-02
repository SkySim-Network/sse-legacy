/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.Sound
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.weapon;

import java.util.Set;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.PlayerBoostStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.util.Sputnik;

public class AspectOfTheVoid
implements ToolStatistics,
MaterialFunction,
Ability {
    @Override
    public int getBaseDamage() {
        return 120;
    }

    @Override
    public double getBaseStrength() {
        return 100.0;
    }

    @Override
    public String getDisplayName() {
        return "Aspect of the Void";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.WEAPON;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.SWORD;
    }

    @Override
    public String getLore() {
        return null;
    }

    @Override
    public String getAbilityName() {
        return "Instant Transmission";
    }

    @Override
    public String getAbilityDescription() {
        return "Teleports you " + ChatColor.GREEN + "8 blocks " + ChatColor.GRAY + "ahead and gain " + ChatColor.GREEN + "+50 " + ChatColor.WHITE + "\u2726 " + ChatColor.WHITE + "Speed " + ChatColor.GRAY + "for " + ChatColor.GREEN + "3 seconds.";
    }

    @Override
    public void onAbilityUse(Player player, SItem sItem) {
        try {
            int f_ = 8;
            for (int range = 1; range < 8; ++range) {
                Location location = player.getTargetBlock((Set)null, range).getLocation();
                if (location.getBlock().getType() == Material.AIR) continue;
                f_ = range;
                break;
            }
            Location location = player.getTargetBlock((Set)null, f_ - 1).getLocation();
            location.setYaw(player.getLocation().getYaw());
            location.setPitch(player.getLocation().getPitch());
            location.add(0.5, 0.0, 0.5);
            if (f_ != 8) {
                player.sendMessage(ChatColor.RED + "There're blocks in the way!");
            }
            if (f_ > 1) {
                Sputnik.teleport(player, location);
            } else {
                Sputnik.teleport(player, player.getLocation());
            }
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3.0f, 1.0f);
        PlayerUtils.boostPlayer(PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId()), new PlayerBoostStatistics(){

            @Override
            public String getDisplayName() {
                return null;
            }

            @Override
            public Rarity getRarity() {
                return null;
            }

            @Override
            public String getLore() {
                return null;
            }

            @Override
            public GenericItemType getType() {
                return null;
            }

            @Override
            public double getBaseSpeed() {
                return 0.5;
            }
        }, 60L);
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 45;
    }
}

