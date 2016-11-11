package org.p004c;

import java.util.Arrays;
import org.p184b.C3275p;
import org.p184b.C3311h;

/* renamed from: org.c.d */
public class C3486d {
    public static <T> void m19025a(T t, C3275p<T> c3275p) {
        if (!c3275p.m18107a(t)) {
            throw new C3520e((Object) t, (C3275p) c3275p);
        }
    }

    public static <T> void m19026a(String str, T t, C3275p<T> c3275p) {
        if (!c3275p.m18107a(t)) {
            throw new C3520e(str, t, c3275p);
        }
    }

    public static void m19027a(String str, Throwable th) {
        C3486d.m19026a(str, th, C3311h.m18278b());
    }

    public static void m19028a(String str, boolean z) {
        if (!z) {
            throw new C3520e(str);
        }
    }

    public static void m19029a(Throwable th) {
        C3486d.m19025a((Object) th, C3311h.m18278b());
    }

    public static void m19030a(boolean z) {
        C3486d.m19025a(Boolean.valueOf(z), C3311h.m18260a(Boolean.valueOf(true)));
    }

    public static void m19031a(Object... objArr) {
        C3486d.m19025a(Arrays.asList(objArr), C3311h.m18286c(C3311h.m18282c()));
    }

    public static void m19032b(String str, boolean z) {
        C3486d.m19028a(str, !z);
    }

    public static void m19033b(boolean z) {
        C3486d.m19030a(!z);
    }
}
