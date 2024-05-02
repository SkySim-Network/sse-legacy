/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.nms.pingrep.reflect;

import java.lang.reflect.Field;

public class ReflectUtils {
    public static Field getFirstFieldByType(Class<?> clazz, Class<?> type) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() != type) continue;
            return field;
        }
        return null;
    }
}

