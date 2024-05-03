/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.util;

import java.util.ArrayList;

public class StackArrayList<T>
extends ArrayList<T> {
    public int push(T element) {
        this.add(element);
        return this.size() - 1;
    }

    public T shift() {
        if (this.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Could not shift because the Collection is empty");
        }
        Object el = this.get(0);
        this.remove(0);
        return (T)el;
    }

    public T pop() {
        if (this.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Could not pop off last element because the Collection is empty");
        }
        Object el = this.get(this.size() - 1);
        this.remove(this.size() - 1);
        return (T)el;
    }

    public T first() {
        if (this.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Could not get the first element because the Collection is empty");
        }
        return (T)this.get(0);
    }

    public T last() {
        if (this.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Could not get the last element because the Collection is empty");
        }
        return (T)this.get(this.size() - 1);
    }
}

