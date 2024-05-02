/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.bow;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.AbilityActivation;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;

public class EndStoneBow
implements ToolStatistics,
MaterialFunction,
Ability {
    @Override
    public String getAbilityName() {
        return "Extreme Focus";
    }

    @Override
    public String getAbilityDescription() {
        return "Consumes all your Mana, and your next hit will deal that much more Damage!";
    }

    @Override
    public void onAbilityUse(Player player, SItem sItem) {
        player.sendMessage(ChatColor.GRAY + "Incomplete ability.");
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return -1;
    }

    @Override
    public AbilityActivation getAbilityActivation() {
        return AbilityActivation.NO_ACTIVATION;
    }

    @Override
    public String getDisplayName() {
        return "End Stone Bow";
    }

    @Override
    public int getBaseDamage() {
        return 140;
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.RANGED_WEAPON;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.BOW;
    }
}

