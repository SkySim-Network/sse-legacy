/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.accessory;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.accessory.AccessoryStatistics;
import vn.giakhanhvn.skysim.util.SUtil;
import vn.giakhanhvn.skysim.util.Sputnik;

public class AutoRecombobulator
implements AccessoryStatistics,
MaterialFunction {
    @Override
    public String getURL() {
        return "5dff8dbbab15bfbb11e23b1f50b34ef548ad9832c0bd7f5a13791adad0057e1b";
    }

    @Override
    public String getDisplayName() {
        return "Auto Recombobulator";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ACCESSORY;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.ACCESSORY;
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public String getLore() {
        return Sputnik.trans("Grants a &a1% &7chance to automatically recombobulate mob drops.");
    }

    @Override
    public void onKill(Entity damaged, Player damager, SItem item) {
        if (SUtil.random(1, 100) == 1) {
            if (item.getType().getStatistics().getType() == GenericItemType.PET) {
                return;
            }
            damager.sendMessage(Sputnik.trans("&eYour &6Auto-Recombobulator &erecombobulated " + item.getFullName() + "&e!"));
            item.setRecombobulated(true);
        }
    }
}

