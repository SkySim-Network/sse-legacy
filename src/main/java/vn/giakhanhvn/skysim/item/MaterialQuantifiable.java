/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;

public class MaterialQuantifiable {
    private SMaterial material;
    private int amount;

    public MaterialQuantifiable(SMaterial material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public MaterialQuantifiable(SMaterial material) {
        this(material, 1);
    }

    public MaterialQuantifiable setMaterial(SMaterial material) {
        this.material = material;
        return this;
    }

    public MaterialQuantifiable setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public boolean equals(Object o) {
        if (!(o instanceof MaterialQuantifiable)) {
            return false;
        }
        MaterialQuantifiable material = (MaterialQuantifiable)o;
        return material.material == this.material && material.amount == this.amount;
    }

    public MaterialQuantifiable clone() {
        return new MaterialQuantifiable(this.material, this.amount);
    }

    public String toString() {
        return "MQ{material=" + (this.material != null ? this.material.name() : "?") + ", amount=" + this.amount + "}";
    }

    public static MaterialQuantifiable of(ItemStack stack) {
        if (stack == null || stack.getType() == Material.AIR) {
            return new MaterialQuantifiable(SMaterial.AIR, stack != null ? stack.getAmount() : 1);
        }
        SItem found = SItem.find(stack);
        if (found == null) {
            found = SItem.of(SMaterial.getSpecEquivalent(stack.getType(), stack.getDurability()));
        }
        return new MaterialQuantifiable(found.getType(), stack.getAmount());
    }

    public static MaterialQuantifiable[] of(ItemStack[] stacks) {
        MaterialQuantifiable[] materials = new MaterialQuantifiable[stacks.length];
        for (int i = 0; i < stacks.length; ++i) {
            materials[i] = MaterialQuantifiable.of(stacks[i]);
        }
        return materials;
    }

    public SMaterial getMaterial() {
        return this.material;
    }

    public int getAmount() {
        return this.amount;
    }
}

