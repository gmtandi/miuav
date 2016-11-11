package org.p122a.p123a.p180o;

import java.util.Collection;

/* renamed from: org.a.a.o.a */
public class C3234a {
    public static int m17883a(int i, String str) {
        if (i > 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative or zero");
    }

    public static long m17884a(long j, String str) {
        if (j > 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " may not be negative or zero");
    }

    public static <T extends CharSequence> T m17885a(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (!C3239f.m17910a(t)) {
            return t;
        } else {
            throw new IllegalArgumentException(str + " may not be empty");
        }
    }

    public static <T> T m17886a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str + " may not be null");
    }

    public static <E, T extends Collection<E>> T m17887a(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (!t.isEmpty()) {
            return t;
        } else {
            throw new IllegalArgumentException(str + " may not be empty");
        }
    }

    public static void m17888a(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void m17889a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static int m17890b(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    public static long m17891b(long j, String str) {
        if (j >= 0) {
            return j;
        }
        throw new IllegalArgumentException(str + " may not be negative");
    }

    public static <T extends CharSequence> T m17892b(T t, String str) {
        if (t == null) {
            throw new IllegalArgumentException(str + " may not be null");
        } else if (!C3239f.m17911b(t)) {
            return t;
        } else {
            throw new IllegalArgumentException(str + " may not be blank");
        }
    }
}
