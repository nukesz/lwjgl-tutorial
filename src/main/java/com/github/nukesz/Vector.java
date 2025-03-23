package com.github.nukesz;

import java.util.Arrays;

public class Vector {

    public final double[] values;

    public Vector(int size) {
        values = new double[size];
    }

    public Vector(double... values) {
        this(values.length);
        System.arraycopy(values, 0, this.values, 0, values.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(values);
    }
}
