/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 */
package vn.giakhanhvn.skysim.item;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import vn.giakhanhvn.skysim.item.Enchantable;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.PlayerBoostStatistics;
import vn.giakhanhvn.skysim.item.Rarity;

public interface WandStatistics
extends PlayerBoostStatistics,
Enchantable {
    @Override
    public String getDisplayName();

    @Override
    public Rarity getRarity();

    @Override
    public GenericItemType getType();

    @Override
    default public boolean isStackable() {
        return false;
    }

    @Override
    default public NBTTagCompound getData() {
        return new NBTTagCompound();
    }
}

