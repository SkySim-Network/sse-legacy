/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.entity.nms;

public class TieredValue<T> {
    private final T i;
    private final T ii;
    private final T iii;
    private final T iv;

    public TieredValue(T i, T ii, T iii, T iv) {
        this.i = i;
        this.ii = ii;
        this.iii = iii;
        this.iv = iv;
    }

    public T getByNumber(int n) {
        switch (n) {
            case 2: {
                return this.ii;
            }
            case 3: {
                return this.iii;
            }
            case 4: {
                return this.iv;
            }
        }
        return this.i;
    }

    public T getTierI() {
        return this.i;
    }

    public T getTierII() {
        return this.ii;
    }

    public T getTierIII() {
        return this.iii;
    }

    public T getTierIV() {
        return this.iv;
    }
}

