/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.md_5.bungee.api.ChatColor
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.armor.eleganttux;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.armor.TickingSet;
import vn.giakhanhvn.skysim.item.armor.eleganttux.ElegantTuxBoots;
import vn.giakhanhvn.skysim.item.armor.eleganttux.ElegantTuxChestplate;
import vn.giakhanhvn.skysim.item.armor.eleganttux.ElegantTuxLeggings;
import vn.giakhanhvn.skysim.item.armor.eleganttux.nullhelm;

public class ElegantTuxedo
implements TickingSet {
    @Override
    public String getName() {
        return "Dashing!";
    }

    @Override
    public String getDescription() {
        return "Max health set to " + ChatColor.RED + "250\u2764 " + ChatColor.GRAY + "Deal " + ChatColor.GREEN + "+150% " + ChatColor.GRAY + "more damage!";
    }

    @Override
    public Class<? extends MaterialStatistics> getHelmet() {
        return nullhelm.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getChestplate() {
        return ElegantTuxChestplate.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getLeggings() {
        return ElegantTuxLeggings.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getBoots() {
        return ElegantTuxBoots.class;
    }

    @Override
    public void tick(Player owner, SItem helmet, SItem chestplate, SItem leggings, SItem boots, List<AtomicInteger> counters) {
    }
}

