/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.nms.nmsutil.reflection.minecraft;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.minecraft.Minecraft;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.ClassResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.ConstructorResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.FieldResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.MethodResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.ResolverQuery;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.minecraft.NMSClassResolver;

public class DataWatcher {
    static ClassResolver classResolver = new ClassResolver();
    static NMSClassResolver nmsClassResolver = new NMSClassResolver();
    static Class<?> ItemStack = nmsClassResolver.resolveSilent("ItemStack");
    static Class<?> ChunkCoordinates = nmsClassResolver.resolveSilent("ChunkCoordinates");
    static Class<?> BlockPosition = nmsClassResolver.resolveSilent("BlockPosition");
    static Class<?> Vector3f = nmsClassResolver.resolveSilent("Vector3f");
    static Class<?> DataWatcher = nmsClassResolver.resolveSilent("DataWatcher");
    static Class<?> Entity = nmsClassResolver.resolveSilent("Entity");
    static Class<?> TIntObjectMap = classResolver.resolveSilent("gnu.trove.map.TIntObjectMap", "net.minecraft.util.gnu.trove.map.TIntObjectMap");
    static ConstructorResolver DataWacherConstructorResolver = new ConstructorResolver(DataWatcher);
    static FieldResolver DataWatcherFieldResolver = new FieldResolver(DataWatcher);
    static MethodResolver TIntObjectMapMethodResolver = new MethodResolver(TIntObjectMap);
    static MethodResolver DataWatcherMethodResolver = new MethodResolver(DataWatcher);

    public static Object newDataWatcher(Object entity) throws ReflectiveOperationException {
        return DataWacherConstructorResolver.resolve(new Class[][]{{Entity}}).newInstance(entity);
    }

    public static Object setValue(Object dataWatcher, int index, Object dataWatcherObject, Object value) throws ReflectiveOperationException {
        if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
            return V1_8.setValue(dataWatcher, index, value);
        }
        return V1_9.setValue(dataWatcher, dataWatcherObject, value);
    }

    public static Object setValue(Object dataWatcher, int index, V1_9.ValueType type, Object value) throws ReflectiveOperationException {
        return vn.giakhanhvn.skysim.nms.nmsutil.reflection.minecraft.DataWatcher.setValue(dataWatcher, index, type.getType(), value);
    }

    public static Object setValue(Object dataWatcher, int index, Object value, FieldResolver dataWatcherObjectFieldResolver, String ... dataWatcherObjectFieldNames) throws ReflectiveOperationException {
        if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
            return V1_8.setValue(dataWatcher, index, value);
        }
        Object dataWatcherObject = dataWatcherObjectFieldResolver.resolve(dataWatcherObjectFieldNames).get(null);
        return V1_9.setValue(dataWatcher, dataWatcherObject, value);
    }

    @Deprecated
    public static Object getValue(DataWatcher dataWatcher, int index) throws ReflectiveOperationException {
        if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
            return V1_8.getValue(dataWatcher, index);
        }
        return V1_9.getValue((Object)dataWatcher, index);
    }

    public static Object getValue(Object dataWatcher, int index, V1_9.ValueType type) throws ReflectiveOperationException {
        return vn.giakhanhvn.skysim.nms.nmsutil.reflection.minecraft.DataWatcher.getValue(dataWatcher, index, type.getType());
    }

    public static Object getValue(Object dataWatcher, int index, Object dataWatcherObject) throws ReflectiveOperationException {
        if (Minecraft.VERSION.olderThan(Minecraft.Version.v1_9_R1)) {
            return V1_8.getWatchableObjectValue(V1_8.getValue(dataWatcher, index));
        }
        return V1_9.getValue(dataWatcher, dataWatcherObject);
    }

    public static int getValueType(Object value) {
        int type = 0;
        if (value instanceof Number) {
            if (value instanceof Byte) {
                type = 0;
            } else if (value instanceof Short) {
                type = 1;
            } else if (value instanceof Integer) {
                type = 2;
            } else if (value instanceof Float) {
                type = 3;
            }
        } else if (value instanceof String) {
            type = 4;
        } else if (value != null && value.getClass().equals(ItemStack)) {
            type = 5;
        } else if (value != null && (value.getClass().equals(ChunkCoordinates) || value.getClass().equals(BlockPosition))) {
            type = 6;
        } else if (value != null && value.getClass().equals(Vector3f)) {
            type = 7;
        }
        return type;
    }

    private DataWatcher() {
    }

    public static class V1_8 {
        static Class<?> WatchableObject = nmsClassResolver.resolveSilent("WatchableObject", "DataWatcher$WatchableObject");
        static ConstructorResolver WatchableObjectConstructorResolver;
        static FieldResolver WatchableObjectFieldResolver;

        public static Object newWatchableObject(int index, Object value) throws ReflectiveOperationException {
            return V1_8.newWatchableObject(vn.giakhanhvn.skysim.nms.nmsutil.reflection.minecraft.DataWatcher.getValueType(value), index, value);
        }

        public static Object newWatchableObject(int type, int index, Object value) throws ReflectiveOperationException {
            if (WatchableObjectConstructorResolver == null) {
                WatchableObjectConstructorResolver = new ConstructorResolver(WatchableObject);
            }
            return WatchableObjectConstructorResolver.resolve(new Class[][]{{Integer.TYPE, Integer.TYPE, Object.class}}).newInstance(type, index, value);
        }

        public static Object setValue(Object dataWatcher, int index, Object value) throws ReflectiveOperationException {
            int type = vn.giakhanhvn.skysim.nms.nmsutil.reflection.minecraft.DataWatcher.getValueType(value);
            Map map = (Map)DataWatcherFieldResolver.resolveByLastType(Map.class).get(dataWatcher);
            map.put(index, V1_8.newWatchableObject(type, index, value));
            return dataWatcher;
        }

        public static Object getValue(Object dataWatcher, int index) throws ReflectiveOperationException {
            Map map = (Map)DataWatcherFieldResolver.resolveByLastType(Map.class).get(dataWatcher);
            return map.get(index);
        }

        public static int getWatchableObjectIndex(Object object) throws ReflectiveOperationException {
            if (WatchableObjectFieldResolver == null) {
                WatchableObjectFieldResolver = new FieldResolver(WatchableObject);
            }
            return WatchableObjectFieldResolver.resolve("b").getInt(object);
        }

        public static int getWatchableObjectType(Object object) throws ReflectiveOperationException {
            if (WatchableObjectFieldResolver == null) {
                WatchableObjectFieldResolver = new FieldResolver(WatchableObject);
            }
            return WatchableObjectFieldResolver.resolve("a").getInt(object);
        }

        public static Object getWatchableObjectValue(Object object) throws ReflectiveOperationException {
            if (WatchableObjectFieldResolver == null) {
                WatchableObjectFieldResolver = new FieldResolver(WatchableObject);
            }
            return WatchableObjectFieldResolver.resolve("c").get(object);
        }
    }

    public static class V1_9 {
        static Class<?> DataWatcherItem = nmsClassResolver.resolveSilent("DataWatcher$Item");
        static Class<?> DataWatcherObject = nmsClassResolver.resolveSilent("DataWatcherObject");
        static ConstructorResolver DataWatcherItemConstructorResolver;
        static FieldResolver DataWatcherItemFieldResolver;
        static FieldResolver DataWatcherObjectFieldResolver;

        public static Object newDataWatcherItem(Object dataWatcherObject, Object value) throws ReflectiveOperationException {
            if (DataWatcherItemConstructorResolver == null) {
                DataWatcherItemConstructorResolver = new ConstructorResolver(DataWatcherItem);
            }
            return DataWatcherItemConstructorResolver.resolveFirstConstructor().newInstance(dataWatcherObject, value);
        }

        public static Object setItem(Object dataWatcher, int index, Object dataWatcherObject, Object value) throws ReflectiveOperationException {
            return V1_9.setItem(dataWatcher, index, V1_9.newDataWatcherItem(dataWatcherObject, value));
        }

        public static Object setItem(Object dataWatcher, int index, Object dataWatcherItem) throws ReflectiveOperationException {
            Map map = (Map)DataWatcherFieldResolver.resolveByLastTypeSilent(Map.class).get(dataWatcher);
            map.put(index, dataWatcherItem);
            return dataWatcher;
        }

        public static Object setValue(Object dataWatcher, Object dataWatcherObject, Object value) throws ReflectiveOperationException {
            DataWatcherMethodResolver.resolve("set").invoke(dataWatcher, dataWatcherObject, value);
            return dataWatcher;
        }

        public static Object getItem(Object dataWatcher, Object dataWatcherObject) throws ReflectiveOperationException {
            return DataWatcherMethodResolver.resolve(new ResolverQuery("c", DataWatcherObject)).invoke(dataWatcher, dataWatcherObject);
        }

        public static Object getValue(Object dataWatcher, Object dataWatcherObject) throws ReflectiveOperationException {
            return DataWatcherMethodResolver.resolve("get").invoke(dataWatcher, dataWatcherObject);
        }

        public static Object getValue(Object dataWatcher, ValueType type) throws ReflectiveOperationException {
            return V1_9.getValue(dataWatcher, type.getType());
        }

        public static Object getItemObject(Object item) throws ReflectiveOperationException {
            if (DataWatcherItemFieldResolver == null) {
                DataWatcherItemFieldResolver = new FieldResolver(DataWatcherItem);
            }
            return DataWatcherItemFieldResolver.resolve("a").get(item);
        }

        public static int getItemIndex(Object dataWatcher, Object item) throws ReflectiveOperationException {
            int index = -1;
            Map<Object , Object> map = (Map)DataWatcherFieldResolver.resolveByLastTypeSilent(Map.class).get(dataWatcher);
            for (Map.Entry<Object , Object> entry : map.entrySet()) {
                if (!entry.getValue().equals(item)) continue;
                index = (Integer)entry.getKey();
                break;
            }
            return index;
        }

        public static Type getItemType(Object item) throws ReflectiveOperationException {
            Type[] actualTypes;
            Type type;
            if (DataWatcherObjectFieldResolver == null) {
                DataWatcherObjectFieldResolver = new FieldResolver(DataWatcherObject);
            }
            Object object = V1_9.getItemObject(item);
            Object serializer = DataWatcherObjectFieldResolver.resolve("b").get(object);
            Type[] genericInterfaces = serializer.getClass().getGenericInterfaces();
            if (genericInterfaces.length > 0 && (type = genericInterfaces[0]) instanceof ParameterizedType && (actualTypes = ((ParameterizedType)type).getActualTypeArguments()).length > 0) {
                return actualTypes[0];
            }
            return null;
        }

        public static Object getItemValue(Object item) throws ReflectiveOperationException {
            if (DataWatcherItemFieldResolver == null) {
                DataWatcherItemFieldResolver = new FieldResolver(DataWatcherItem);
            }
            return DataWatcherItemFieldResolver.resolve("b").get(item);
        }

        public static void setItemValue(Object item, Object value) throws ReflectiveOperationException {
            DataWatcherItemFieldResolver.resolve("b").set(item, value);
        }

        public static enum ValueType {
            ENTITY_FLAG("Entity", 57, 0),
            ENTITY_AIR_TICKS("Entity", 58, 1),
            ENTITY_NAME("Entity", 59, 2),
            ENTITY_NAME_VISIBLE("Entity", 60, 3),
            ENTITY_SILENT("Entity", 61, 4),
            ENTITY_as("EntityLiving", 2, 0),
            ENTITY_LIVING_HEALTH("EntityLiving", new String[]{"HEALTH"}),
            ENTITY_LIVING_f("EntityLiving", 4, 2),
            ENTITY_LIVING_g("EntityLiving", 5, 3),
            ENTITY_LIVING_h("EntityLiving", 6, 4),
            ENTITY_INSENTIENT_FLAG("EntityInsentient", 0, 0),
            ENTITY_SLIME_SIZE("EntitySlime", 0, 0),
            ENTITY_WITHER_a("EntityWither", 0, 0),
            ENTITY_WIHER_b("EntityWither", 1, 1),
            ENTITY_WITHER_c("EntityWither", 2, 2),
            ENTITY_WITHER_bv("EntityWither", 3, 3),
            ENTITY_WITHER_bw("EntityWither", 4, 4),
            ENTITY_AGEABLE_CHILD("EntityAgeable", 0, 0),
            ENTITY_HORSE_STATUS("EntityHorse", 3, 0),
            ENTITY_HORSE_HORSE_TYPE("EntityHorse", 4, 1),
            ENTITY_HORSE_HORSE_VARIANT("EntityHorse", 5, 2),
            ENTITY_HORSE_OWNER_UUID("EntityHorse", 6, 3),
            ENTITY_HORSE_HORSE_ARMOR("EntityHorse", 7, 4),
            ENTITY_HUMAN_ABSORPTION_HEARTS("EntityHuman", 0, 0),
            ENTITY_HUMAN_SCORE("EntityHuman", 1, 1),
            ENTITY_HUMAN_SKIN_LAYERS("EntityHuman", 2, 2),
            ENTITY_HUMAN_MAIN_HAND("EntityHuman", 3, 3);

            private Object type;

            private ValueType(String className, String[] fieldNames) {
                block2: {
                    try {
                        this.type = new FieldResolver(nmsClassResolver.resolve(className)).resolve(fieldNames).get(null);
                    }
                    catch (Exception e) {
                        if (!Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) break block2;
                        System.err.println("[SkySim Reflection Injector] Failed to find DataWatcherObject for " + className + " " + Arrays.toString(fieldNames));
                    }
                }
            }

            private ValueType(String className, int index) {
                block2: {
                    try {
                        this.type = new FieldResolver(nmsClassResolver.resolve(className)).resolveIndex(index).get(null);
                    }
                    catch (Exception e) {
                        if (!Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) break block2;
                        System.err.println("[SkySim Reflection Injector] Failed to find DataWatcherObject for " + className + " #" + index);
                    }
                }
            }

            private ValueType(String className, int index, int offset) {
                block3: {
                    int firstObject = 0;
                    try {
                        Class clazz = nmsClassResolver.resolve(className);
                        for (Field field : clazz.getDeclaredFields()) {
                            if ("DataWatcherObject".equals(field.getType().getSimpleName())) break;
                            ++firstObject;
                        }
                        this.type = new FieldResolver(clazz).resolveIndex(firstObject + offset).get(null);
                    }
                    catch (Exception e) {
                        if (!Minecraft.VERSION.newerThan(Minecraft.Version.v1_9_R1)) break block3;
                        System.err.println("[SkySim Reflection Injector] Failed to find DataWatcherObject for " + className + " #" + index + " (" + firstObject + "+" + offset + ")");
                    }
                }
            }

            public boolean hasType() {
                return this.getType() != null;
            }

            public Object getType() {
                return this.type;
            }
        }
    }
}

