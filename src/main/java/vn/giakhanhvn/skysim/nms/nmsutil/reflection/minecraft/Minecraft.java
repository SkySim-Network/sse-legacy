/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.entity.Entity
 */
package vn.giakhanhvn.skysim.nms.nmsutil.reflection.minecraft;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import sun.reflect.ConstructorAccessor;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.ConstructorResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.FieldResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.MethodResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.minecraft.NMSClassResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.resolver.minecraft.OBCClassResolver;
import vn.giakhanhvn.skysim.nms.nmsutil.reflection.util.AccessUtil;

public class Minecraft {
    static final Pattern NUMERIC_VERSION_PATTERN = Pattern.compile("v([0-9])_([0-9]*)_R([0-9])");
    public static final Version VERSION;
    private static NMSClassResolver nmsClassResolver;
    private static OBCClassResolver obcClassResolver;
    private static Class<?> NmsEntity;
    private static Class<?> CraftEntity;

    public static String getVersion() {
        return VERSION.name() + ".";
    }

    public static Object getHandle(Object object) throws ReflectiveOperationException {
        Method method;
        try {
            method = AccessUtil.setAccessible(object.getClass().getDeclaredMethod("getHandle", new Class[0]));
        }
        catch (ReflectiveOperationException e) {
            method = AccessUtil.setAccessible(CraftEntity.getDeclaredMethod("getHandle", new Class[0]));
        }
        return method.invoke(object, new Object[0]);
    }

    public static Entity getBukkitEntity(Object object) throws ReflectiveOperationException {
        Method method;
        try {
            method = AccessUtil.setAccessible(NmsEntity.getDeclaredMethod("getBukkitEntity", new Class[0]));
        }
        catch (ReflectiveOperationException e) {
            method = AccessUtil.setAccessible(CraftEntity.getDeclaredMethod("getHandle", new Class[0]));
        }
        return (Entity)method.invoke(object, new Object[0]);
    }

    public static Object getHandleSilent(Object object) {
        try {
            return Minecraft.getHandle(object);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Object newEnumInstance(Class clazz, Class[] types, Object[] values) throws ReflectiveOperationException {
        Constructor constructor = new ConstructorResolver(clazz).resolve(new Class[][]{types});
        Field accessorField = new FieldResolver(Constructor.class).resolve("constructorAccessor");
        ConstructorAccessor constructorAccessor = (ConstructorAccessor)accessorField.get(constructor);
        if (constructorAccessor == null) {
            new MethodResolver(Constructor.class).resolve("acquireConstructorAccessor").invoke((Object)constructor, new Object[0]);
            constructorAccessor = (ConstructorAccessor)accessorField.get(constructor);
        }
        return constructorAccessor.newInstance(values);
    }

    static {
        nmsClassResolver = new NMSClassResolver();
        obcClassResolver = new OBCClassResolver();
        VERSION = Version.getVersion();
        System.out.println("[SkySim Reflection Injector] Version is " + (Object)((Object)VERSION));
        try {
            NmsEntity = nmsClassResolver.resolve("Entity");
            CraftEntity = obcClassResolver.resolve("entity.CraftEntity");
        }
        catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static enum Version {
        UNKNOWN(-1){

            @Override
            public boolean matchesPackageName(String packageName) {
                return false;
            }
        }
        ,
        v1_7_R1(10701),
        v1_7_R2(10702),
        v1_7_R3(10703),
        v1_7_R4(10704),
        v1_8_R1(10801),
        v1_8_R2(10802),
        v1_8_R3(10803),
        v1_8_R4(10804),
        v1_9_R1(10901),
        v1_9_R2(10902),
        v1_10_R1(11001),
        v1_11_R1(11101),
        v1_12_R1(11201);

        private int version;

        private Version(int version) {
            this.version = version;
        }

        public int version() {
            return this.version;
        }

        public boolean olderThan(Version version) {
            return this.version() < version.version();
        }

        public boolean newerThan(Version version) {
            return this.version() >= version.version();
        }

        public boolean inRange(Version oldVersion, Version newVersion) {
            return this.newerThan(oldVersion) && this.olderThan(newVersion);
        }

        public boolean matchesPackageName(String packageName) {
            return packageName.toLowerCase().contains(this.name().toLowerCase());
        }

        public static Version getVersion() {
            String name = Bukkit.getServer().getClass().getPackage().getName();
            String versionPackage = name.substring(name.lastIndexOf(46) + 1) + ".";
            for (Version version : Version.values()) {
                if (!version.matchesPackageName(versionPackage)) continue;
                return version;
            }
            System.err.println("[SkySim Reflection Injector] Failed to find version enum for '" + name + "'/'" + versionPackage + "'");
            System.out.println("[SkySim Reflection Injector] Generating dynamic constant...");
            Matcher matcher = NUMERIC_VERSION_PATTERN.matcher(versionPackage);
            while (matcher.find()) {
                String patchString;
                if (matcher.groupCount() < 3) continue;
                String majorString = matcher.group(1);
                String minorString = matcher.group(2);
                if (minorString.length() == 1) {
                    minorString = "0" + minorString;
                }
                if ((patchString = matcher.group(3)).length() == 1) {
                    patchString = "0" + patchString;
                }
                String numVersionString = majorString + minorString + patchString;
                int numVersion = Integer.parseInt(numVersionString);
                String packge = versionPackage.substring(0, versionPackage.length() - 1);
                try {
                    Version dynamicVersion;
                    Field valuesField = new FieldResolver(Version.class).resolve("$VALUES");
                    Version[] oldValues = (Version[])valuesField.get(null);
                    Version[] newValues = new Version[oldValues.length + 1];
                    System.arraycopy(oldValues, 0, newValues, 0, oldValues.length);
                    newValues[newValues.length - 1] = dynamicVersion = (Version)((Object)Minecraft.newEnumInstance(Version.class, new Class[]{String.class, Integer.TYPE, Integer.TYPE}, new Object[]{packge, newValues.length - 1, numVersion}));
                    valuesField.set(null, newValues);
                    System.out.println("[SkySim Reflection Injector] Injected dynamic version " + packge + " (#" + numVersion + ").");
                    System.out.println("[SkySim Reflection Injector] Please inform inventivetalent about the outdated version, as this is not guaranteed to work.");
                    return dynamicVersion;
                }
                catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                }
            }
            return UNKNOWN;
        }

        public String toString() {
            return this.name() + " (" + this.version() + ")";
        }
    }
}

