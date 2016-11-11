package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;

class bb extends bg {
    private static boolean f3060a;

    static {
        f3060a = true;
    }

    protected bb(Context context) {
        super(context);
    }

    protected String m4646a() {
        return bf.f3076c;
    }

    protected boolean m4647a(Context context) {
        if (!f3060a) {
            return false;
        }
        f3060a = false;
        synchronized (Looper.getMainLooper()) {
            aq aqVar = new aq(context);
            as a = aqVar.m4559a();
            if (a == null) {
                return true;
            } else if (a.m4568a()) {
                a.m4567a(false);
                aqVar.m4560a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    protected int m4648b() {
        return 0;
    }
}
