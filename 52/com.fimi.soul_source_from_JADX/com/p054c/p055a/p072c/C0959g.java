package com.p054c.p055a.p072c;

import android.graphics.Bitmap;
import com.p054c.p055a.p056a.p061b.C0875c;
import com.p054c.p055a.p063b.p064a.C0900f;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* renamed from: com.c.a.c.g */
public final class C0959g {
    private static final String f5049a = "_";
    private static final String f5050b = "x";

    private C0959g() {
    }

    public static String m7563a(String str, C0900f c0900f) {
        return f5049a + c0900f.m7206a() + f5050b + c0900f.m7209b();
    }

    public static Comparator<String> m7564a() {
        return new C0960h();
    }

    public static List<Bitmap> m7565a(String str, C0875c c0875c) {
        List<Bitmap> arrayList = new ArrayList();
        for (String str2 : c0875c.m7074a()) {
            if (str2.startsWith(str)) {
                arrayList.add(c0875c.m7073a(str2));
            }
        }
        return arrayList;
    }

    public static List<String> m7566b(String str, C0875c c0875c) {
        List<String> arrayList = new ArrayList();
        for (String str2 : c0875c.m7074a()) {
            if (str2.startsWith(str)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    public static void m7567c(String str, C0875c c0875c) {
        List<String> arrayList = new ArrayList();
        for (String str2 : c0875c.m7074a()) {
            if (str2.startsWith(str)) {
                arrayList.add(str2);
            }
        }
        for (String str22 : arrayList) {
            c0875c.m7076b(str22);
        }
    }
}
