package com.tencent.map.p131b;

import com.tencent.mm.sdk.platformtools.Util;

/* renamed from: com.tencent.map.b.a */
public final class C2228a {
    private static C2228a f11562a;

    /* renamed from: com.tencent.map.b.a.1 */
    class C22271 {
        final /* synthetic */ C2228a f11561a;

        private C22271(C2228a c2228a) {
            this.f11561a = c2228a;
        }

        public boolean m13360a(String str, String str2) {
            int a = C2228a.m13361a(this.f11561a, str);
            if (str2.charAt(4) != C2256i.f11705a.charAt(((((a * 9) + 10) / 3) + 36) & 31)) {
                return false;
            }
            if (str2.charAt(7) != C2256i.f11705a.charAt((((a * 5) + 11) / 5) & 31)) {
                return false;
            }
            if (str2.charAt(12) != C2256i.f11705a.charAt((((a + 10) / 3) << 3) & 31)) {
                return false;
            }
            if (str2.charAt(14) != C2256i.f11705a.charAt((((a * 3) + 19) / 9) & 31)) {
                return false;
            }
            if (str2.charAt(19) != C2256i.f11705a.charAt((((a * 3) + 39) / 8) & 31)) {
                return false;
            }
            if (str2.charAt(21) != C2256i.f11705a.charAt((((a / 23) + 67) / 7) & 31)) {
                return false;
            }
            if (str2.charAt(26) != C2256i.f11705a.charAt(((((a + 23) / 6) + 3) * 7) & 31)) {
                return false;
            }
            int i = 0;
            for (a = 0; a < str.length(); a++) {
                i = C2256i.f11706b[(i ^ C2256i.m13483a(str.charAt(a))) & Util.MASK_8BIT] ^ ((i >> 8) & Util.MASK_8BIT);
            }
            if (str2.charAt(0) != C2256i.f11705a.charAt(i & 31)) {
                return false;
            }
            return str2.charAt(1) == C2256i.f11705a.charAt((i >> 5) & 31);
        }
    }

    static {
        f11562a = null;
    }

    private C2228a() {
    }

    static /* synthetic */ int m13361a(C2228a c2228a, String str) {
        int i = 0;
        int length = str.length();
        int i2 = 0;
        while (i < length) {
            i2 += C2256i.m13483a(str.charAt(i));
            i++;
        }
        return ((length << 7) + length) ^ i2;
    }

    public static synchronized C2228a m13362a() {
        C2228a c2228a;
        synchronized (C2228a.class) {
            if (f11562a == null) {
                f11562a = new C2228a();
            }
            c2228a = f11562a;
        }
        return c2228a;
    }

    public final boolean m13363a(String str, String str2) {
        if (!C2256i.m13490a(str) || !C2256i.m13491b(str2) || !new C22271().m13360a(str, str2)) {
            return false;
        }
        boolean z;
        int i = 0;
        for (int i2 = 0; i2 < 27; i2++) {
            i = C2256i.f11706b[(i ^ C2256i.m13483a(str2.charAt(i2))) & Util.MASK_8BIT] ^ ((i >> 8) & Util.MASK_8BIT);
        }
        if (str2.charAt(27) != C2256i.f11705a.charAt(i & 31)) {
            z = false;
        } else {
            z = str2.charAt(28) == C2256i.f11705a.charAt((i >> 5) & 31);
        }
        return z;
    }
}
