/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim;

import java.util.Arrays;
import java.util.List;
import vn.giakhanhvn.skysim.SkySimEngine;

public final class SkyBlockCalendar {
    public static final List<String> MONTH_NAMES = Arrays.asList("Early Spring", "Spring", "Late Spring", "Early Summer", "Summer", "Late Summer", "Early Autumn", "Autumn", "Late Autumn", "Early Winter", "Winter", "Late Winter");
    public static long ELAPSED = 0L;
    public static final int YEAR = 8928000;
    public static final int MONTH = 744000;
    public static final int DAY = 24000;

    private SkyBlockCalendar() {
    }

    public static int getYear() {
        return (int)(ELAPSED / 8928000L);
    }

    public static int getMonth() {
        return (int)(ELAPSED / 744000L) % 12 + 1;
    }

    public static int getDay() {
        return (int)(ELAPSED / 24000L) % 31 + 1;
    }

    public static String getMonthName(int month) {
        if (month < 1 || month > 12) {
            return "Unknown Month";
        }
        return MONTH_NAMES.get(month - 1);
    }

    public static String getMonthName() {
        return SkyBlockCalendar.getMonthName(SkyBlockCalendar.getMonth());
    }

    public static void saveElapsed() {
        SkySimEngine plugin = SkySimEngine.getPlugin();
        plugin.config.set("timeElapsed", ELAPSED);
        plugin.config.save();
    }
}

