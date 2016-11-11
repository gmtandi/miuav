package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;

class az extends bg {
    private static boolean f3048a;

    static {
        f3048a = true;
    }

    protected az(Context context) {
        super(context);
    }

    protected String m4608a() {
        return bf.f3077d;
    }

    protected boolean m4609a(Context context) {
        if (C0500z.m4896g(context) != 1 || !f3048a) {
            return false;
        }
        f3048a = false;
        synchronized (Looper.getMainLooper()) {
            aq aqVar = new aq(context);
            as a = aqVar.m4559a();
            if (a == null) {
                return true;
            } else if (a.m4572c()) {
                a.m4571c(false);
                aqVar.m4560a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    protected int m4610b() {
        return 2;
    }
}
