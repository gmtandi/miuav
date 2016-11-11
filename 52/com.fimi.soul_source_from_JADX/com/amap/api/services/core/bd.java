package com.amap.api.services.core;

import android.content.Context;
import android.os.Looper;

class bd extends bg {
    private static boolean f3064a;

    static {
        f3064a = true;
    }

    protected bd(Context context) {
        super(context);
    }

    protected String m4655a() {
        return bf.f3075b;
    }

    protected boolean m4656a(Context context) {
        if (C0500z.m4896g(context) != 1 || !f3064a) {
            return false;
        }
        f3064a = false;
        synchronized (Looper.getMainLooper()) {
            aq aqVar = new aq(context);
            as a = aqVar.m4559a();
            if (a == null) {
                return true;
            } else if (a.m4570b()) {
                a.m4569b(false);
                aqVar.m4560a(a);
                return true;
            } else {
                return false;
            }
        }
    }

    protected int m4657b() {
        return 1;
    }
}
