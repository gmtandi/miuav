package com.fimi.kernel.p073a;

import android.graphics.drawable.Drawable;

/* renamed from: com.fimi.kernel.a.c */
public class C1090c implements Comparable<C1090c> {
    public String f5064a;
    public String f5065b;
    public int f5066c;
    public int f5067d;
    public Drawable f5068e;
    public long f5069f;
    public String f5070g;
    public String f5071h;
    public String f5072i;

    public C1090c(String str, int i, int i2) {
        this.f5065b = str;
        this.f5066c = i;
        this.f5067d = i2;
    }

    public int m7671a(C1090c c1090c) {
        return this.f5065b.compareTo(c1090c.f5065b) == 0 ? this.f5069f < c1090c.f5069f ? 1 : this.f5069f == c1090c.f5069f ? 0 : -1 : this.f5065b.compareTo(c1090c.f5065b);
    }

    public /* synthetic */ int compareTo(Object obj) {
        return m7671a((C1090c) obj);
    }
}
