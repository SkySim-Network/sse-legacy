/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.collection;

import java.util.ArrayList;
import java.util.Arrays;
import vn.giakhanhvn.skysim.collection.ItemCollectionReward;

public class ItemCollectionRewards
extends ArrayList<ItemCollectionReward> {
    private final int requirement;

    public ItemCollectionRewards(int requirement, ItemCollectionReward ... rewards) {
        super(Arrays.asList(rewards));
        this.requirement = requirement;
    }

    public int getRequirement() {
        return this.requirement;
    }
}

