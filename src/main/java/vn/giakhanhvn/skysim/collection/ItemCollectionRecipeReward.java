/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.collection;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.collection.ItemCollectionReward;
import vn.giakhanhvn.skysim.item.SMaterial;

public class ItemCollectionRecipeReward
extends ItemCollectionReward {
    private final SMaterial material;

    public ItemCollectionRecipeReward(SMaterial material) {
        super(ItemCollectionReward.Type.RECIPE);
        this.material = material;
    }

    @Override
    public void onAchieve(Player player) {
    }

    @Override
    public String toRewardString() {
        return ChatColor.GRAY + this.material.getDisplayName(this.material.getData()) + ChatColor.GRAY + " Recipe";
    }
}

