package com.p054c.p055a.p072c;

import com.p054c.p055a.p056a.p057a.C0864a;
import java.io.File;

/* renamed from: com.c.a.c.a */
public final class C0954a {
    private C0954a() {
    }

    public static File m7540a(String str, C0864a c0864a) {
        File a = c0864a.m7028a(str);
        return (a == null || !a.exists()) ? null : a;
    }

    public static boolean m7541b(String str, C0864a c0864a) {
        File a = c0864a.m7028a(str);
        return a != null && a.exists() && a.delete();
    }
}
