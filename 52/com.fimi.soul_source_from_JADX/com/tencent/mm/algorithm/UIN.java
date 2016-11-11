package com.tencent.mm.algorithm;

import it.sephiroth.android.library.widget.ExpandableHListView;

public class UIN extends Number {
    private int f11753h;

    public UIN(int i) {
        this.f11753h = 0;
        this.f11753h = i;
    }

    public UIN(long j) {
        this.f11753h = 0;
        this.f11753h = (int) (-1 & j);
    }

    public static int valueOf(String str) {
        try {
            return new UIN(Long.valueOf(str).longValue()).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public double doubleValue() {
        return ((double) (((long) this.f11753h) | 0)) + 0.0d;
    }

    public float floatValue() {
        return (float) (((double) (((long) this.f11753h) | 0)) + 0.0d);
    }

    public int intValue() {
        return this.f11753h;
    }

    public long longValue() {
        return ((long) this.f11753h) & ExpandableHListView.aZ;
    }

    public String toString() {
        return String.valueOf(((long) this.f11753h) & ExpandableHListView.aZ);
    }

    public int value() {
        return this.f11753h;
    }
}
