/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.dimoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.dimoon.DimoonLootItem;
import vn.giakhanhvn.skysim.util.SUtil;

public class DimoonLootTable {
    public static List<DimoonLootItem> lowQualitylootTable = null;
    public static List<DimoonLootItem> highQualitylootTable = null;
    @Getter
    private int weight = 0;
    private Player p;
    private int cp;
    private int plm;

    public DimoonLootTable(Player p, int placement, int catalPlaced) {
        this.cp = catalPlaced;
        this.plm = placement;
        if (placement <= 0 || catalPlaced < 0) {
            return;
        }
        this.p = p;
        this.weight = catalPlaced * 100 + this.calPlacementWeight(placement);
    }

    int calPlacementWeight(int i) {
        if (i == 1) {
            return 500;
        }
        if (i == 2) {
            return 400;
        }
        if (i == 3) {
            return 300;
        }
        if (i == 4) {
            return 200;
        }
        if (i == 5) {
            return 100;
        }
        if (i > 5 && i <= 400) {
            return 400 / i;
        }
        return 0;
    }

    public List<List<DimoonLootItem>> roll() {
        int r;
        if (this.cp < 0 || this.plm <= 0) {
            return null;
        }
        ArrayList<DimoonLootItem> rolledHiItems = new ArrayList<DimoonLootItem>();
        ArrayList<DimoonLootItem> rolledLoItems = new ArrayList<DimoonLootItem>();
        List<DimoonLootItem> highQualityLootable = new ArrayList<>(highQualitylootTable);
        highQualityLootable.removeIf(item -> item.getMinimumWeight() > this.weight);
        List<DimoonLootItem> lowQualityLootable = new ArrayList<>(lowQualitylootTable);
        lowQualityLootable.removeIf(item -> item.getMinimumWeight() > this.weight);
        for (DimoonLootItem item2 : highQualityLootable) {
            r = SUtil.random(1, item2.getChance());
            if (r != 1) continue;
            rolledHiItems.add(item2);
            break;
        }
        for (DimoonLootItem item2 : lowQualityLootable) {
            r = SUtil.random(1, item2.getChance());
            if (r != 1) continue;
            rolledLoItems.add(item2);
            break;
        }
        return Arrays.asList(rolledHiItems, rolledLoItems);
    }

}

