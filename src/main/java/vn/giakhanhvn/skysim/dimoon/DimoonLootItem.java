/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.dimoon;

import vn.giakhanhvn.skysim.item.SItem;

public class DimoonLootItem {
    private SItem item;
    private int chance;
    private int minimumWeight;
    private int amount = 1;
    private boolean isRandomizedAmount = false;

    public DimoonLootItem(SItem item, int chancePer, int minimumWeight) {
        this(item, 1, chancePer, minimumWeight, false);
    }

    public DimoonLootItem(SItem item, int amount, int chancePer, int minimumWeight) {
        this(item, amount, chancePer, minimumWeight, false);
    }

    public DimoonLootItem(SItem item, int amount, int chancePer, int minimumWeight, boolean randomAmount) {
        this.item = item;
        this.chance = chancePer;
        this.minimumWeight = minimumWeight;
        this.amount = amount;
        this.isRandomizedAmount = randomAmount;
    }

    public SItem getItem() {
        return this.item;
    }

    public int getChance() {
        return this.chance;
    }

    public int getMinimumWeight() {
        return this.minimumWeight;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isRandomizedAmount() {
        return this.isRandomizedAmount;
    }
}

