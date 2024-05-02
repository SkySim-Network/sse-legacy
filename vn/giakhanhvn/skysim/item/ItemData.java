/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_8_R3.NBTTagCompound
 */
package vn.giakhanhvn.skysim.item;

import java.util.List;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public interface ItemData {
    public NBTTagCompound getData();

    default public List<String> getDataLore(String key, Object value) {
        return null;
    }
}

