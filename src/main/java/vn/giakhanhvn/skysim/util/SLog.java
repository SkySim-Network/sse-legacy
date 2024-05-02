/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SLog {
    private static final Logger LOGGER = Logger.getLogger("Minecraft");
    private static final String PREFIX = "[SkySim Engine]";

    private static void log(Object o, Level l) {
        LOGGER.log(l, "[SkySim Engine] " + o);
    }

    public static void info(Object o) {
        SLog.log(o, Level.INFO);
    }

    public static void warn(Object o) {
        SLog.log(o, Level.WARNING);
    }

    public static void severe(Object o) {
        SLog.log(o, Level.SEVERE);
    }
}

