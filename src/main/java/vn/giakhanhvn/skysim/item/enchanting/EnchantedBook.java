/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item.enchanting;

import vn.giakhanhvn.skysim.enchantment.Enchantment;
import vn.giakhanhvn.skysim.enchantment.EnchantmentType;
import vn.giakhanhvn.skysim.item.Enchantable;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.MaterialQuantifiable;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.item.ShapedRecipe;

public class EnchantedBook
implements MaterialStatistics,
MaterialFunction,
Enchantable {
    private static final MaterialQuantifiable PAPER_16 = new MaterialQuantifiable(SMaterial.PAPER, 16);

    @Override
    public String getDisplayName() {
        return "Enchanted Book";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public boolean isStackable() {
        return false;
    }

    @Override
    public String getLore() {
        return "Use this on an item in an Anvil to apply it!";
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }

    @Override
    public void onInstanceUpdate(SItem instance) {
        int max = 1;
        for (Enchantment enchantment : instance.getEnchantments()) {
            if (enchantment.getLevel() <= max) continue;
            max = enchantment.getLevel();
        }
        switch (max) {
            case 1: 
            case 2: 
            case 3: 
            case 4: {
                instance.setRarity(Rarity.COMMON, false);
                break;
            }
            case 5: {
                instance.setRarity(Rarity.UNCOMMON, false);
                break;
            }
            case 6: {
                instance.setRarity(Rarity.RARE, false);
                break;
            }
            case 7: {
                instance.setRarity(Rarity.EPIC, false);
                break;
            }
            case 8: {
                instance.setRarity(Rarity.LEGENDARY, false);
                break;
            }
            case 9: {
                instance.setRarity(Rarity.MYTHIC, false);
                break;
            }
            default: {
                instance.setRarity(Rarity.SUPREME, false);
            }
        }
    }

    @Override
    public void load() {
        SItem sitem = SItem.of(SMaterial.ENCHANTED_BOOK);
        sitem.addEnchantment(EnchantmentType.TURBO_GEM, 1);
        ShapedRecipe recipe = new ShapedRecipe(sitem);
        recipe.shape("123", "456", "789");
        recipe.set('1', SMaterial.HIDDEN_DIMOON_FRAG, 8);
        recipe.set('2', SMaterial.HIDDEN_DIMOON_FRAG, 8);
        recipe.set('3', SMaterial.HIDDEN_DIMOON_FRAG, 8);
        recipe.set('4', SMaterial.HIDDEN_DIMOON_FRAG, 8);
        recipe.set('5', SMaterial.HOT_POTATO_BOOK, 1);
        recipe.set('6', SMaterial.HIDDEN_DIMOON_FRAG, 8);
        recipe.set('7', SMaterial.HIDDEN_DIMOON_FRAG, 8);
        recipe.set('8', SMaterial.HIDDEN_DIMOON_FRAG, 8);
        recipe.set('9', SMaterial.HIDDEN_DIMOON_FRAG, 8);
    }
}

