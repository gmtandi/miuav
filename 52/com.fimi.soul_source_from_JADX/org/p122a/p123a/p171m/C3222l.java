package org.p122a.p123a.p171m;

import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.m.l */
public class C3222l {
    private final int f15687a;
    private final int f15688b;
    private final int f15689c;
    private final int f15690d;

    public C3222l(int i, int i2, int i3, int i4) {
        this.f15687a = i;
        this.f15688b = i2;
        this.f15689c = i3;
        this.f15690d = i4;
    }

    public int m17853a() {
        return this.f15687a;
    }

    public int m17854b() {
        return this.f15688b;
    }

    public int m17855c() {
        return this.f15689c;
    }

    public int m17856d() {
        return this.f15690d;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[leased: ");
        stringBuilder.append(this.f15687a);
        stringBuilder.append("; pending: ");
        stringBuilder.append(this.f15688b);
        stringBuilder.append("; available: ");
        stringBuilder.append(this.f15689c);
        stringBuilder.append("; max: ");
        stringBuilder.append(this.f15690d);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
