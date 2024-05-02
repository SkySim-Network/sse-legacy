/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 */
package vn.giakhanhvn.skysim.item;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import vn.giakhanhvn.skysim.item.ItemData;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SkullStatistics;
import vn.giakhanhvn.skysim.util.SUtil;

public interface Rune
extends SkullStatistics,
MaterialFunction,
ItemData {
    @Override
    default public NBTTagCompound getData() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setByte("level", (byte)1);
        return compound;
    }

    @Override
    default public void onInstanceUpdate(SItem instance) {
        byte level = instance.getData().getByte("level");
        instance.setDisplayName(this.getDisplayName() + " " + SUtil.toRomanNumeral(level));
    }
}

