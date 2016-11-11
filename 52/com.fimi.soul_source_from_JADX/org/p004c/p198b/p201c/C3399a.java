package org.p004c.p198b.p201c;

import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3340l;
import org.p004c.p198b.p199a.C3385a;

/* renamed from: org.c.b.c.a */
public class C3399a extends C3340l {
    private final Object f15898a;
    private final Class<?> f15899b;
    private final boolean f15900c;
    private volatile C3319s f15901d;

    public C3399a(Class<?> cls) {
        this(cls, true);
    }

    public C3399a(Class<?> cls, boolean z) {
        this.f15898a = new Object();
        this.f15899b = cls;
        this.f15900c = z;
    }

    public C3319s m18660a() {
        if (this.f15901d == null) {
            synchronized (this.f15898a) {
                if (this.f15901d == null) {
                    this.f15901d = new C3385a(this.f15900c).m18613c(this.f15899b);
                }
            }
        }
        return this.f15901d;
    }
}
