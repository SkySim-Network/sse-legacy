/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.item.MaterialQuantifiable;
import vn.giakhanhvn.skysim.item.Recipe;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.util.SUtil;

public class ShapelessRecipe
extends Recipe<ShapelessRecipe> {
    private static final List<ShapelessRecipe> CACHED_RECIPES = new ArrayList<ShapelessRecipe>();
    private final List<MaterialQuantifiable> ingredientList = new ArrayList<MaterialQuantifiable>();

    public ShapelessRecipe(SItem result, boolean usesExchangeables) {
        super(result, usesExchangeables);
        CACHED_RECIPES.add(this);
    }

    public ShapelessRecipe(SItem result) {
        this(result, false);
    }

    public ShapelessRecipe(SMaterial material, int amount) {
        this(SUtil.setSItemAmount(SItem.of(material), amount));
    }

    public ShapelessRecipe(SMaterial material) {
        this(SItem.of(material));
    }

    @Override
    public ShapelessRecipe setResult(SItem result) {
        this.result = result;
        return this;
    }

    public ShapelessRecipe add(MaterialQuantifiable material) {
        this.ingredientList.add(material.clone());
        return this;
    }

    public ShapelessRecipe add(SMaterial material, int amount) {
        return this.add(new MaterialQuantifiable(material, amount));
    }

    @Override
    public List<MaterialQuantifiable> getIngredients() {
        return this.ingredientList;
    }

    public String toString() {
        return "ShapelessRecipe{" + this.ingredientList.toString() + "}";
    }

    protected static ShapelessRecipe parseShapelessRecipe(ItemStack[] stacks) {
        if (stacks.length != 9) {
            throw new UnsupportedOperationException("Recipe parsing requires a 9 element array!");
        }
        MaterialQuantifiable[] materials = SUtil.unnest(ShapelessRecipe.airless(new MaterialQuantifiable[][]{MaterialQuantifiable.of(Arrays.copyOfRange(stacks, 0, 3)), MaterialQuantifiable.of(Arrays.copyOfRange(stacks, 3, 6)), MaterialQuantifiable.of(Arrays.copyOfRange(stacks, 6, 9))}), MaterialQuantifiable.class);
        for (ShapelessRecipe recipe : CACHED_RECIPES) {
            List<MaterialQuantifiable> ingredients = recipe.getIngredientList();
            if (materials.length != ingredients.size()) continue;
            boolean found = true;
            MaterialQuantifiable[] copy = Arrays.copyOf(materials, materials.length);
            for (MaterialQuantifiable ingredient : ingredients) {
                if (ShapelessRecipe.contains(recipe.useExchangeables, copy, ingredient)) continue;
                found = false;
                break;
            }
            if (!found) continue;
            return recipe;
        }
        return null;
    }

    private static boolean contains(boolean usesExchangeables, MaterialQuantifiable[] grid, MaterialQuantifiable test) {
        List<SMaterial> exchangeables = ShapelessRecipe.getExchangeablesOf(test.getMaterial());
        for (int i = 0; i < grid.length; ++i) {
            MaterialQuantifiable material = grid[i];
            if (material == null) continue;
            if (usesExchangeables && exchangeables != null && exchangeables.contains((Object)material.getMaterial()) && material.getAmount() >= test.getAmount()) {
                grid[i] = null;
                return true;
            }
            if (material.getMaterial() != test.getMaterial() || material.getAmount() < test.getAmount()) continue;
            grid[i] = null;
            return true;
        }
        return false;
    }

    public List<MaterialQuantifiable> getIngredientList() {
        return this.ingredientList;
    }
}

