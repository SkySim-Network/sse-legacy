/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 */
package vn.giakhanhvn.skysim.item.armor;

import net.md_5.bungee.api.ChatColor;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;

public class CrownOfGreed
implements ToolStatistics {
    @Override
    public String getDisplayName() {
        return "Crown of Greed";
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
        return SpecificItemType.HELMET;
    }

    @Override
    public double getBaseDefense() {
        return 90.0;
    }

    @Override
    public double getBaseHealth() {
        return 130.0;
    }

    @Override
    public double getBaseMagicFind() {
        return 0.04;
    }

    @Override
    public String getLore() {
        return ChatColor.translateAlternateColorCodes((char)'&', (String)"Hits have &c+25% &7base damage, but cost the weapon's base damage in &6coins &7from your purse.");
    }
}

