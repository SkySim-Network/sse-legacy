/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.item.weapon;

import org.bukkit.ChatColor;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;

public class Exterminator
implements ToolStatistics,
MaterialFunction {
    @Override
    public int getBaseDamage() {
        return 10;
    }

    @Override
    public double getBaseStrength() {
        return 1.0;
    }

    @Override
    public double getBaseCritDamage() {
        return 0.0;
    }

    @Override
    public String getDisplayName() {
        return "Remnant of The Exterminator";
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
        return ChatColor.RED + "Sky" + ChatColor.GREEN + "Sim" + ChatColor.GRAY + "'s Origin Weapon remnant. Rest In Peace, " + ChatColor.RED + "Exterminator" + ChatColor.GRAY + "!";
    }
}

