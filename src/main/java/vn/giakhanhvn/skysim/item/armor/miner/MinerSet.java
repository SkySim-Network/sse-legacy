/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.armor.miner;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.armor.TickingSet;
import vn.giakhanhvn.skysim.item.armor.miner.MinerBoots;
import vn.giakhanhvn.skysim.item.armor.miner.MinerChestplate;
import vn.giakhanhvn.skysim.item.armor.miner.MinerHelmet;
import vn.giakhanhvn.skysim.item.armor.miner.MinerLeggings;
import vn.giakhanhvn.skysim.listener.PlayerListener;
import vn.giakhanhvn.skysim.region.Region;
import vn.giakhanhvn.skysim.user.DoublePlayerStatistic;
import vn.giakhanhvn.skysim.user.PlayerStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.util.Groups;

public class MinerSet
implements TickingSet {
    @Override
    public String getName() {
        return "Regeneration";
    }

    @Override
    public String getDescription() {
        return "Regenerates 5% of your max Health every second if you have been out of combat for the last 8 seconds.";
    }

    @Override
    public Class<? extends MaterialStatistics> getHelmet() {
        return MinerHelmet.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getChestplate() {
        return MinerChestplate.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getLeggings() {
        return MinerLeggings.class;
    }

    @Override
    public Class<? extends MaterialStatistics> getBoots() {
        return MinerBoots.class;
    }

    @Override
    public void tick(Player owner, SItem helmet, SItem chestplate, SItem leggings, SItem boots, List<AtomicInteger> counters) {
        Region region;
        PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(owner.getUniqueId());
        DoublePlayerStatistic defense = statistics.getDefense();
        PlayerListener.CombatAction action = PlayerListener.getLastCombatAction(owner);
        counters.get(0).incrementAndGet();
        if ((action == null || action.getTimeStamp() + 8000L <= System.currentTimeMillis() && helmet != null && chestplate != null && leggings != null && boots != null) && counters.get(0).get() >= 2) {
            owner.setHealth(Math.min(owner.getMaxHealth(), owner.getHealth() + owner.getMaxHealth() * 0.05));
            counters.get(0).set(0);
        }
        if ((region = Region.getRegionOfEntity((Entity)owner)) == null) {
            return;
        }
        if (!Groups.DEEP_CAVERNS_REGIONS.contains((Object)region.getType())) {
            return;
        }
        if (helmet != null) {
            defense.add(8, 45.0);
        }
        if (chestplate != null) {
            defense.add(8, 95.0);
        }
        if (leggings != null) {
            defense.add(8, 70.0);
        }
        if (boots != null) {
            defense.add(8, 45.0);
        }
    }
}

