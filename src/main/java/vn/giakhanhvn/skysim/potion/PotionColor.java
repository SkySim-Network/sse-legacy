/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.potion;

public enum PotionColor {
    BLUE(0),
    PURPLE(1),
    LIGHT_BLUE(2),
    ORANGE(3),
    DARK_GREEN(4),
    RED(5),
    DARK_BLUE(6),
    GRAY(8),
    DARK_RED(9),
    DARK_GRAY(10),
    GREEN(11),
    BLOOD_RED(12),
    TWILIGHT_BLUE(13),
    LIGHT_GRAY(14);

    public static final short SPLASH = 16384;
    private final short data;

    private PotionColor(short data) {
        this.data = data;
    }

    public short getSplashData() {
        return (short)(this.data + 16384);
    }

    public short getData() {
        return this.data;
    }
}

