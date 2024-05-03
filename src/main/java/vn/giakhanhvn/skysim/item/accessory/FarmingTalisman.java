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
import vn.giakhanhvn.skysim.item.PlayerBoostStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.accessory.AccessoryFunction;
import vn.giakhanhvn.skysim.item.accessory.AccessoryStatistics;
import vn.giakhanhvn.skysim.region.Region;
import vn.giakhanhvn.skysim.region.RegionType;
import vn.giakhanhvn.skysim.user.PlayerUtils;

public class FarmingTalisman
implements AccessoryStatistics,
AccessoryFunction {
    @Override
    public String getDisplayName() {
        return "Farming Talisman";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public String getURL() {
        return "9af328c87b068509aca9834eface197705fe5d4f0871731b7b21cd99b9fddc";
    }

    @Override
    public void update(SItem instance, Player player, int accessorySlot) {
        Region region = Region.getQuickRegionOfEntity((Entity)player);
        if (region == null) {
            return;
        }
        if (region.getType() != RegionType.THE_BARN && region.getType() != RegionType.MUSHROOM_DESERT) {
            return;
        }
        PlayerUtils.addBoostStatistics(PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId()), accessorySlot, new PlayerBoostStatistics(){

            @Override
            public String getDisplayName() {
                return null;
            }

            @Override
            public Rarity getRarity() {
                return null;
            }

            @Override
            public GenericItemType getType() {
                return null;
            }

            @Override
            public double getBaseSpeed() {
                return 0.1;
            }
        });
    }
}

