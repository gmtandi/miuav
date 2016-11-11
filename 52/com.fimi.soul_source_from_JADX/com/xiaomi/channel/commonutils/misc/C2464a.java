package com.xiaomi.channel.commonutils.misc;

import com.xiaomi.market.sdk.C2537j;

/* renamed from: com.xiaomi.channel.commonutils.misc.a */
public class C2464a {
    public static final boolean f12420a;
    public static final boolean f12421b;
    public static final boolean f12422c;
    public static final boolean f12423d;
    public static boolean f12424e;
    public static final boolean f12425f;
    public static final boolean f12426g;
    private static int f12427h;

    static {
        boolean z = false;
        f12420a = C2537j.ap.contains("2A2FE0D7");
        boolean z2 = f12420a || "DEBUG".equalsIgnoreCase(C2537j.ap);
        f12421b = z2;
        f12422c = "LOGABLE".equalsIgnoreCase(C2537j.ap);
        f12423d = C2537j.ap.contains("YY");
        f12424e = C2537j.ap.equalsIgnoreCase("TEST");
        f12425f = "BETA".equalsIgnoreCase(C2537j.ap);
        if (C2537j.ap != null && C2537j.ap.startsWith("RC")) {
            z = true;
        }
        f12426g = z;
        f12427h = 1;
        if (C2537j.ap.equalsIgnoreCase("SANDBOX")) {
            f12427h = 2;
        } else if (C2537j.ap.equalsIgnoreCase("ONEBOX")) {
            f12427h = 3;
        } else {
            f12427h = 1;
        }
    }

    public static void m14129a(int i) {
        f12427h = i;
    }

    public static boolean m14130a() {
        return f12427h == 2;
    }

    public static boolean m14131b() {
        return f12427h == 3;
    }

    public static int m14132c() {
        return f12427h;
    }
}
