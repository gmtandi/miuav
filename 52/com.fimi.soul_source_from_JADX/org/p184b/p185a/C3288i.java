package org.p184b.p185a;

import org.p184b.C3275p;
import org.p184b.C3276b;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.b.a.i */
public class C3288i<T> extends C3276b<T> {
    private final String f15798a;

    public C3288i() {
        this("ANYTHING");
    }

    public C3288i(String str) {
        this.f15798a = str;
    }

    @C3315n
    public static C3275p<Object> m18163a(String str) {
        return new C3288i(str);
    }

    @C3315n
    public static C3275p<Object> m18164b() {
        return new C3288i();
    }

    public void m18165a(C3300k c3300k) {
        c3300k.m18222a(this.f15798a);
    }

    public boolean m18166a(Object obj) {
        return true;
    }
}
