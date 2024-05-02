/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.util;

public interface TriConsumer<T, U, V> {
    public void accept(T var1, U var2, V var3);

    default public TriConsumer<T, U, V> andThen(TriConsumer<? super T, ? super U, ? super V> after) {
        return null;
    }
}

