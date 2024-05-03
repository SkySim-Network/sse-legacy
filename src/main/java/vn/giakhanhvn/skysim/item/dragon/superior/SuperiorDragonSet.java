/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.dragon.superior;

import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.armor.ArmorSet;
import vn.giakhanhvn.skysim.item.dragon.superior.SuperiorDragonBoots;
import vn.giakhanhvn.skysim.item.dragon.superior.SuperiorDragonChestplate;
import vn.giakhanhvn.skysim.item.dragon.superior.SuperiorDragonHelmet;
import vn.giakhanhvn.skysim.item.dragon.superior.SuperiorDragonLeggings;

public class SuperiorDragonSet
implements ArmorSet {
    @Override
    public String getName() {
        return "Superior Blood";
    }

    @Override
    public String getDescription() {
        return "All your stats are increased by 5% and Aspect of the Dragons ability deals 50% more Ability Damage.";
    }

    @Override
    public Class<? extends MaterialStatistics> getHelmet() {
        return SuperiorDragonHelmet.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getChestplate() {
        return SuperiorDragonChestplate.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getLeggings() {
        return SuperiorDragonLeggings.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getBoots() {
        return SuperiorDragonBoots.class;
    }
}

