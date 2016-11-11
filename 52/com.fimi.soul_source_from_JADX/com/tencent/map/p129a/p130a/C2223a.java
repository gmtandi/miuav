package com.tencent.map.p129a.p130a;

import android.content.Context;
import com.tencent.map.p131b.C2250f;

/* renamed from: com.tencent.map.a.a.a */
public class C2223a {
    private static C2250f f11522a;
    private static C2223a f11523b;

    static {
        f11522a = C2250f.m13418a();
    }

    public static synchronized C2223a m13348a() {
        C2223a c2223a;
        synchronized (C2223a.class) {
            if (f11523b == null) {
                f11523b = new C2223a();
            }
            c2223a = f11523b;
        }
        return c2223a;
    }

    public boolean m13349a(Context context, C2224b c2224b) {
        return f11522a.m13464a(context, c2224b);
    }

    public boolean m13350a(String str, String str2) {
        return f11522a.m13465a(str, str2);
    }

    public void m13351b() {
        f11522a.m13466b();
    }
}
