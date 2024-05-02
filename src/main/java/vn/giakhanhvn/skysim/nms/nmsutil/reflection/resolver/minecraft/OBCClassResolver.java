/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.minecraft;

import vn.giakhanhvn.skysim.nms.nmsutil.reflection.minecraft.Minecraft;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.ClassResolver;

public class OBCClassResolver
extends ClassResolver {
    @Override
    public Class resolve(String ... names) throws ClassNotFoundException {
        for (int i = 0; i < names.length; ++i) {
            if (names[i].startsWith("org.bukkit.craftbukkit")) continue;
            names[i] = "org.bukkit.craftbukkit." + Minecraft.getVersion() + names[i];
        }
        return super.resolve(names);
    }
}

