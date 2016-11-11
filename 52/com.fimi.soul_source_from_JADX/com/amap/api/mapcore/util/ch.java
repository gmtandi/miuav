package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.util.List;

public class ch extends ci {
    private static boolean f2339a;

    static {
        f2339a = true;
    }

    protected ch(int i) {
        super(i);
    }

    protected String m3874a(List<bv> list) {
        return null;
    }

    protected boolean m3875a(Context context) {
        if (bq.m3703m(context) != 1 || !f2339a) {
            return false;
        }
        f2339a = false;
        synchronized (Looper.getMainLooper()) {
            cv cvVar = new cv(context);
            cw a = cvVar.m3930a();
            if (a == null) {
                return true;
            } else if (a.m3935b()) {
                a.m3934b(false);
                cvVar.m3931a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
