/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.oddities;

import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.gui.CookieConfirmGUI;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.Untradeable;
import vn.giakhanhvn.skysim.util.Sputnik;

public class BoosterCookie
implements MaterialStatistics,
MaterialFunction,
Ability,
Untradeable {
    @Override
    public String getDisplayName() {
        return "Booster Cookie";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public String getAbilityName() {
        return "Drink!";
    }

    @Override
    public String getAbilityDescription() {
        return Sputnik.trans("&7Consume to gain the &dCookie Buff &7for &b2 &7days:");
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 8;
    }

    @Override
    public boolean displayCooldown() {
        return false;
    }

    @Override
    public int getManaCost() {
        return 0;
    }

    @Override
    public boolean isEnchanted() {
        return true;
    }

    @Override
    public boolean displayUsage() {
        return false;
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public void onAbilityUse(Player player, SItem sItem) {
        new CookieConfirmGUI(player.getInventory().getHeldItemSlot(), player.getInventory().getItemInHand()).open(player);
    }
}

