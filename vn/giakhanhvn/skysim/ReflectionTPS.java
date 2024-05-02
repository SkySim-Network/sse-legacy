/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim;

public class ReflectionTPS
implements Runnable {
    public static int TICK_COUNT = 0;
    public static long[] TICKS = new long[600];
    public static long LAST_TICK = 0L;

    public static double getTPS() {
        return ReflectionTPS.getTPS(100);
    }

    public static double getTPS(int ticks) {
        if (TICK_COUNT < ticks) {
            return 20.0;
        }
        int target = (TICK_COUNT - 1 - ticks) % TICKS.length;
        long elapsed = System.currentTimeMillis() - TICKS[target];
        return (double)ticks / ((double)elapsed / 1000.0);
    }

    public static long getElapsed(int tickID) {
        if (TICK_COUNT - tickID >= TICKS.length) {
            // empty if block
        }
        long time = TICKS[tickID % TICKS.length];
        return System.currentTimeMillis() - time;
    }

    @Override
    public void run() {
        ReflectionTPS.TICKS[ReflectionTPS.TICK_COUNT % ReflectionTPS.TICKS.length] = System.currentTimeMillis();
        ++TICK_COUNT;
    }
}

