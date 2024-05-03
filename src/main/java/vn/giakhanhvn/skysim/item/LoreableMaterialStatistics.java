/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.item;

import java.util.List;
import vn.giakhanhvn.skysim.item.MaterialStatistics;
import vn.giakhanhvn.skysim.item.SItem;

public interface LoreableMaterialStatistics
extends MaterialStatistics {
    public List<String> getCustomLore(SItem var1);
}

