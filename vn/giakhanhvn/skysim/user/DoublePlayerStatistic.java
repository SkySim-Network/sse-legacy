/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.user;

import java.util.ArrayList;
import java.util.Arrays;
import vn.giakhanhvn.skysim.user.PlayerStatistic;

public class DoublePlayerStatistic
implements PlayerStatistic<Double> {
    private final double defaultValue;
    private final ArrayList<Double> values;

    public DoublePlayerStatistic(double defaultValue) {
        this.defaultValue = defaultValue;
        this.values = new ArrayList(6);
        this.values.addAll(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
    }

    public DoublePlayerStatistic() {
        this(0.0);
    }

    @Override
    public Double addAll() {
        double result = this.defaultValue;
        for (Double value : this.values) {
            result += value.doubleValue();
        }
        return result;
    }

    @Override
    public void add(int slot, Double d) {
        this.set(slot, this.safeGet(slot) + d);
    }

    @Override
    public void sub(int slot, Double d) {
        this.set(slot, this.safeGet(slot) - d);
    }

    @Override
    public void zero(int slot) {
        this.set(slot, 0.0);
    }

    @Override
    public boolean contains(int slot) {
        return slot >= 0 && slot < this.values.size();
    }

    @Override
    public Double safeGet(int index) {
        if (index < 0 || index > this.values.size() - 1) {
            this.set(index, 0.0);
        }
        return this.values.get(index);
    }

    @Override
    public void set(int slot, Double d) {
        this.values.ensureCapacity(slot + 1);
        while (this.values.size() < slot + 1) {
            this.values.add(0.0);
        }
        this.values.set(slot, d);
    }

    @Override
    public int next() {
        return this.values.size();
    }

    @Override
    public Double getFor(int slot) {
        return this.safeGet(slot);
    }

    public ArrayList<Double> forInventory() {
        ArrayList<Double> list = new ArrayList<Double>();
        for (int i = 6; i < this.values.size(); ++i) {
            list.add(this.safeGet(i));
        }
        return list;
    }

    public double getDefaultValue() {
        return this.defaultValue;
    }
}

