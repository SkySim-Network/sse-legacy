/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.user;

public interface PlayerStatistic<T> {
    public static final int HELMET = 0;
    public static final int CHESTPLATE = 1;
    public static final int LEGGINGS = 2;
    public static final int BOOTS = 3;
    public static final int HAND = 4;
    public static final int SET = 5;
    public static final int BOOST = 6;
    public static final int PET = 7;
    public static final int MINER_BUFF = 8;
    public static final int OBSIDIAN_CHESTPLATE = 9;
    public static final int FARMING = 10;
    public static final int MINING = 11;
    public static final int COMBAT = 12;
    public static final int ENCHANTING = 14;
    public static final int FORAGING = 13;
    public static final int ADD_FOR_INVENTORY = 15;
    public static final int ADD_FOR_POTION_EFFECTS = 52;
    public static final int CRYSTALBUFF = 150;
    public static final int COOKIE_BUFF = 151;
    public static final int TEMPORARY_STATS = 152;
    public static final int FATAL_SLOT = 153;

    public T addAll();

    public void add(int var1, T var2);

    public void sub(int var1, T var2);

    public void zero(int var1);

    public boolean contains(int var1);

    public T safeGet(int var1);

    public void set(int var1, T var2);

    public int next();

    public T getFor(int var1);
}

