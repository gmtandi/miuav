package org.p122a.p123a.p124f.p165d;

import java.util.regex.Pattern;
import org.p122a.p123a.p150a.C2912b;

@C2912b
/* renamed from: org.a.a.f.d.a */
public class C3035a {
    private static final String f15086a = "(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
    private static final Pattern f15087b;
    private static final Pattern f15088c;
    private static final Pattern f15089d;
    private static final Pattern f15090e;
    private static final char f15091f = ':';
    private static final int f15092g = 7;

    static {
        f15087b = Pattern.compile("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
        f15088c = Pattern.compile("^::[fF]{4}:(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$");
        f15089d = Pattern.compile("^[0-9a-fA-F]{1,4}(:[0-9a-fA-F]{1,4}){7}$");
        f15090e = Pattern.compile("^(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)::(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)$");
    }

    private C3035a() {
    }

    public static boolean m17130a(String str) {
        return f15087b.matcher(str).matches();
    }

    public static boolean m17131b(String str) {
        return f15088c.matcher(str).matches();
    }

    public static boolean m17132c(String str) {
        return f15089d.matcher(str).matches();
    }

    public static boolean m17133d(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == f15091f) {
                i++;
            }
        }
        return i <= f15092g && f15090e.matcher(str).matches();
    }

    public static boolean m17134e(String str) {
        return C3035a.m17132c(str) || C3035a.m17133d(str);
    }
}
