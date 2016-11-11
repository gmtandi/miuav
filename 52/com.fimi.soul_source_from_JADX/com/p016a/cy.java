package com.p016a;

import java.util.Locale;

/* renamed from: com.a.cy */
public class cy {
    private String f812a;
    private int f813b;
    private String f814c;
    private long f815d;

    public cy(String str, long j, int i, String str2) {
        this.f812a = str;
        this.f815d = j;
        this.f813b = i;
        this.f814c = str2;
    }

    public String m1418a() {
        return this.f812a;
    }

    public int m1419b() {
        return this.f813b;
    }

    public String toString() {
        return String.format(Locale.US, "##h=%s, n=%d, t=%d, ex=%s##", new Object[]{this.f812a, Integer.valueOf(this.f813b), Long.valueOf(this.f815d), this.f814c});
    }
}
