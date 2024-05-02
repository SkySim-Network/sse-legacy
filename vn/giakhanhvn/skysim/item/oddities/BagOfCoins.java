/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 *  org.bukkit.ChatColor
 */
package vn.giakhanhvn.skysim.item.oddities;

import java.util.Collections;
import java.util.List;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.ItemData;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SkullStatistics;
import vn.giakhanhvn.skysim.util.SUtil;

public class BagOfCoins
implements SkullStatistics,
MaterialFunction,
ItemData {
    @Override
    public String getDisplayName() {
        return "Bag of Coins";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public void onInstanceUpdate(SItem instance) {
        long coins = instance.getDataLong("coins");
        if (coins < 10000L) {
            instance.setRarity(Rarity.COMMON, false);
        } else if (coins < 100000L) {
            instance.setRarity(Rarity.UNCOMMON, false);
        } else if (coins < 250000L) {
            instance.setRarity(Rarity.RARE, false);
        } else if (coins < 4000000L) {
            instance.setRarity(Rarity.EPIC, false);
        } else if (coins < 10000000L) {
            instance.setRarity(Rarity.LEGENDARY, false);
        } else if (coins < 25000000L) {
            instance.setRarity(Rarity.MYTHIC, false);
        } else if (coins < 100000000L) {
            instance.setRarity(Rarity.SUPREME, false);
        } else if (coins < 500000000L) {
            instance.setRarity(Rarity.SPECIAL, false);
        } else {
            instance.setRarity(Rarity.VERY_SPECIAL, false);
        }
    }

    @Override
    public NBTTagCompound getData() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setLong("coins", 1L);
        return compound;
    }

    @Override
    public List<String> getDataLore(String key, Object value) {
        if (!key.equals("coins")) {
            return null;
        }
        return Collections.singletonList(ChatColor.GOLD + "Contents: " + ChatColor.YELLOW + SUtil.commaify((Long)value) + " coins");
    }

    @Override
    public String getURL() {
        return "8381c529d52e03cd74c3bf38bb6ba3fde1337ae9bf50332faa889e0a28e8081f";
    }
}

