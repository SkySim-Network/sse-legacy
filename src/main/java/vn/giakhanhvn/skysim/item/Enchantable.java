/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 */
package vn.giakhanhvn.skysim.item;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import vn.giakhanhvn.skysim.item.ItemData;

public interface Enchantable
extends ItemData {
    @Override
    default public NBTTagCompound getData() {
        return new NBTTagCompound();
    }
}

