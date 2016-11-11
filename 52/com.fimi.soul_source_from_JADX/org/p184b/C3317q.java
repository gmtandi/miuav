package org.p184b;

import org.p122a.p123a.C2915a;

/* renamed from: org.b.q */
public class C3317q {
    public static <T> void m18309a(T t, C3275p<? super T> c3275p) {
        C3317q.m18310a(C2915a.f14760f, t, c3275p);
    }

    public static <T> void m18310a(String str, T t, C3275p<? super T> c3275p) {
        if (!c3275p.m18107a(t)) {
            C3300k c3318s = new C3318s();
            c3318s.m18222a(str).m18222a("\nExpected: ").m18225a((C3274r) c3275p).m18222a("\n     but: ");
            c3275p.m18106a(t, c3318s);
            throw new AssertionError(c3318s.toString());
        }
    }

    public static void m18311a(String str, boolean z) {
        if (!z) {
            throw new AssertionError(str);
        }
    }
}
