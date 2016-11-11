package com.tencent.stat.common;

import android.content.Context;

/* renamed from: com.tencent.stat.common.f */
public class C2413f {
    static long f12331a;

    static {
        f12331a = -1;
    }

    static long m13992a(Context context, String str) {
        return C2423p.m14060a(context, str, f12331a);
    }

    static void m13993a(Context context, String str, long j) {
        C2423p.m14064b(context, str, j);
    }

    public static synchronized boolean m13994a(Context context) {
        boolean z;
        synchronized (C2413f.class) {
            long a = C2413f.m13992a(context, "1.6.2_begin_protection");
            long a2 = C2413f.m13992a(context, "1.6.2_end__protection");
            if (a <= 0 || a2 != f12331a) {
                if (a == f12331a) {
                    C2413f.m13993a(context, "1.6.2_begin_protection", System.currentTimeMillis());
                }
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public static synchronized void m13995b(Context context) {
        synchronized (C2413f.class) {
            if (C2413f.m13992a(context, "1.6.2_end__protection") == f12331a) {
                C2413f.m13993a(context, "1.6.2_end__protection", System.currentTimeMillis());
            }
        }
    }
}
