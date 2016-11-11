package org.p122a.p123a.p152c.p161f;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* renamed from: org.a.a.c.f.c */
final class C2980c {
    private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> f14913a;

    static {
        f14913a = new C2981d();
    }

    C2980c() {
    }

    public static SimpleDateFormat m16920a(String str) {
        Map map;
        Map map2 = (Map) ((SoftReference) f14913a.get()).get();
        if (map2 == null) {
            HashMap hashMap = new HashMap();
            f14913a.set(new SoftReference(hashMap));
            map = hashMap;
        } else {
            map = map2;
        }
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) map.get(str);
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        map.put(str, simpleDateFormat);
        return simpleDateFormat;
    }

    public static void m16921a() {
        f14913a.remove();
    }
}
