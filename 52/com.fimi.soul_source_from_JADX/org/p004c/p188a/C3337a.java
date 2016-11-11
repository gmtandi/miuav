package org.p004c.p188a;

import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3336a;
import org.p004c.p187f.C3320j;
import org.p004c.p187f.p192a.C3384i;

/* renamed from: org.c.a.a */
public class C3337a extends C3336a {
    private final boolean f15839a;
    private final boolean f15840b;

    public C3337a(boolean z, boolean z2) {
        this.f15839a = z;
        this.f15840b = z2;
    }

    public static C3336a m18411a() {
        return new C3337a(true, false);
    }

    private static C3319s m18412a(C3319s c3319s) {
        if (c3319s instanceof C3320j) {
            ((C3320j) c3319s).m18337a(new C3348b());
        }
        return c3319s;
    }

    public static C3336a m18413b() {
        return new C3337a(false, true);
    }

    protected C3319s m18414a(C3384i c3384i, Class<?> cls) {
        C3319s a = super.m18409a(c3384i, (Class) cls);
        return this.f15840b ? C3337a.m18412a(a) : a;
    }

    public C3319s m18415a(C3384i c3384i, Class<?>[] clsArr) {
        C3319s a = super.m18410a(c3384i, (Class[]) clsArr);
        return this.f15839a ? C3337a.m18412a(a) : a;
    }
}
