/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item;

import java.util.List;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.ItemCategory;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SpecificItemType;

public interface MaterialStatistics {
    public String getDisplayName();

    public Rarity getRarity();

    default public String getLore() {
        return null;
    }

    default public List<String> getListLore() {
        return null;
    }

    public GenericItemType getType();

    default public SpecificItemType getSpecificType() {
        return SpecificItemType.NONE;
    }

    default public boolean isStackable() {
        return true;
    }

    default public boolean isEnchanted() {
        return false;
    }

    default public boolean displayKills() {
        return false;
    }

    default public boolean displayCoins() {
        return false;
    }

    default public boolean displayRarity() {
        return true;
    }

    default public long getPrice() {
        return 1L;
    }

    default public long getValue() {
        return 1L;
    }

    default public ItemCategory getCategory() {
        return ItemCategory.TOOLS_MISC;
    }

    default public void load() {
    }
}

