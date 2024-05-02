/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.armor;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.AbilityActivation;
import vn.giakhanhvn.skysim.item.FlightStatistics;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.TickingMaterial;
import vn.giakhanhvn.skysim.item.armor.LeatherArmorStatistics;

public class BigBounceBoots
implements LeatherArmorStatistics,
TickingMaterial,
FlightStatistics,
Ability {
    @Override
    public String getAbilityName() {
        return "Larger Double Jump";
    }

    @Override
    public String getAbilityDescription() {
        return "Allows you to Double Jump higher! " + ChatColor.RED + "Disabled!";
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 0;
    }

    @Override
    public AbilityActivation getAbilityActivation() {
        return AbilityActivation.FLIGHT;
    }

    @Override
    public void onAbilityUse(Player player, SItem sItem) {
    }

    @Override
    public int getManaCost() {
        return 80;
    }

    @Override
    public String getDisplayName() {
        return "Doubling Boots";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.BOOTS;
    }

    @Override
    public double getBaseDefense() {
        return 90.0;
    }

    @Override
    public double getBaseSpeed() {
        return 0.4;
    }

    @Override
    public double getBaseIntelligence() {
        return 0.0;
    }

    @Override
    public void tick(SItem item, Player owner) {
    }

    @Override
    public boolean enableFlight() {
        return false;
    }

    @Override
    public int getColor() {
        return 9548515;
    }
}

