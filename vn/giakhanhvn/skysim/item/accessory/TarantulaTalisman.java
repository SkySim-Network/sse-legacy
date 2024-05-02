/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.accessory;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.accessory.AccessoryFunction;
import vn.giakhanhvn.skysim.item.accessory.AccessoryStatistics;

public class TarantulaTalisman
implements AccessoryStatistics,
AccessoryFunction {
    private static final Map<UUID, Integer> HITS = new HashMap<UUID, Integer>();

    @Override
    public String getDisplayName() {
        return "Tarantula Talisman";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
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
    public String getURL() {
        return "442cf8ce487b78fa203d56cf01491434b4c33e5d236802c6d69146a51435b03d";
    }

    @Override
    public void onDamageInInventory(SItem weapon, Player damager, Entity damaged, SItem accessory, AtomicDouble damage) {
        HITS.put(damager.getUniqueId(), HITS.getOrDefault(damager.getUniqueId(), 0) + 1);
        if (HITS.get(damager.getUniqueId()) >= 10) {
            damage.addAndGet(damage.get() * 0.1);
            HITS.remove(damager.getUniqueId());
        }
    }
}

