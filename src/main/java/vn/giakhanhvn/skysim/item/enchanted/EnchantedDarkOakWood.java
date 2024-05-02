/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.enchanted;

import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialQuantifiable;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.enchanted.EnchantedMaterialStatistics;

public class EnchantedDarkOakWood
implements EnchantedMaterialStatistics,
MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Enchanted Dark Oak Wood";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.UNCOMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public boolean isEnchanted() {
        return true;
    }

    @Override
    public MaterialQuantifiable getResult() {
        return new MaterialQuantifiable(SMaterial.ENCHANTED_DARK_OAK_WOOD);
    }

    @Override
    public SMaterial getCraftingMaterial() {
        return SMaterial.DARK_OAK_WOOD;
    }
}

