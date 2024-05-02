/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.dragon.protector;

import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.armor.ArmorSet;
import vn.giakhanhvn.skysim.item.dragon.protector.ProtectorDragonBoots;
import vn.giakhanhvn.skysim.item.dragon.protector.ProtectorDragonChestplate;
import vn.giakhanhvn.skysim.item.dragon.protector.ProtectorDragonHelmet;
import vn.giakhanhvn.skysim.item.dragon.protector.ProtectorDragonLeggings;

public class ProtectorDragonSet
implements ArmorSet {
    @Override
    public String getName() {
        return "Protective Blood";
    }

    @Override
    public String getDescription() {
        return "Increases the Defense bonus of each armor piece by 1% for each percent of missing Health.";
    }

    @Override
    public Class<? extends MaterialStatistics> getHelmet() {
        return ProtectorDragonHelmet.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getChestplate() {
        return ProtectorDragonChestplate.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getLeggings() {
        return ProtectorDragonLeggings.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getBoots() {
        return ProtectorDragonBoots.class;
    }
}

