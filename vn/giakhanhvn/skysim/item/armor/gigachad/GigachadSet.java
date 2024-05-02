/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.armor.gigachad;

import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.armor.ArmorSet;
import vn.giakhanhvn.skysim.item.armor.gigachad.GigachadBoots;
import vn.giakhanhvn.skysim.item.armor.gigachad.GigachadChestplate;
import vn.giakhanhvn.skysim.item.armor.gigachad.GigachadHelmet;
import vn.giakhanhvn.skysim.item.armor.gigachad.GigachadLeggings;
import vn.giakhanhvn.skysim.util.Sputnik;

public class GigachadSet
implements ArmorSet {
    @Override
    public String getName() {
        return "Gigablood";
    }

    @Override
    public String getDescription() {
        return Sputnik.trans("&7Increase most of your stats by &a20% &7since you're a true &cgigachad&7. Beautiful, my comrade!");
    }

    @Override
    public Class<? extends MaterialStatistics> getHelmet() {
        return GigachadHelmet.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getChestplate() {
        return GigachadChestplate.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getLeggings() {
        return GigachadLeggings.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getBoots() {
        return GigachadBoots.class;
    }
}

