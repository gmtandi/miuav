package org.p122a.p137b.p182b;

import org.p122a.p137b.p183c.C3265c;

/* renamed from: org.a.b.b.b */
public class C3252b implements C3251j {
    protected boolean f15741a;
    protected boolean f15742b;
    protected int f15743c;

    public C3252b() {
        this(false, true);
    }

    public C3252b(boolean z, boolean z2) {
        this(z, z2, 0);
    }

    public C3252b(boolean z, boolean z2, int i) {
        this.f15741a = false;
        this.f15742b = true;
        this.f15741a = z;
        this.f15742b = z2;
        this.f15743c = i;
    }

    public C3249h m18003a(C3265c c3265c) {
        C3249h c3250a = new C3250a(c3265c, this.f15741a, this.f15742b);
        if (this.f15743c != 0) {
            c3250a.m17979c(this.f15743c);
        }
        return c3250a;
    }
}
