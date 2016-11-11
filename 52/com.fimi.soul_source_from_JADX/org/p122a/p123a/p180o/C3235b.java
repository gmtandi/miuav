package org.p122a.p123a.p180o;

/* renamed from: org.a.a.o.b */
public class C3235b {
    public static void m17893a(CharSequence charSequence, String str) {
        if (C3239f.m17910a(charSequence)) {
            throw new IllegalStateException(str + " is empty");
        }
    }

    public static void m17894a(Object obj, String str) {
        if (obj == null) {
            throw new IllegalStateException(str + " is null");
        }
    }

    public static void m17895a(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static void m17896a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(String.format(str, objArr));
        }
    }

    public static void m17897b(CharSequence charSequence, String str) {
        if (C3239f.m17911b(charSequence)) {
            throw new IllegalStateException(str + " is blank");
        }
    }
}
