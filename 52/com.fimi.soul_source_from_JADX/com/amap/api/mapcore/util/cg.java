package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

public class cg extends ci {
    private static boolean f2338a;

    static {
        f2338a = true;
    }

    protected cg(int i) {
        super(i);
    }

    protected String m3871a(String str) {
        return bs.m3730c(str + bx.m3769a(new Date().getTime()));
    }

    protected String m3872a(List<bv> list) {
        return null;
    }

    protected boolean m3873a(Context context) {
        if (!f2338a) {
            return false;
        }
        f2338a = false;
        synchronized (Looper.getMainLooper()) {
            cv cvVar = new cv(context);
            cw a = cvVar.m3930a();
            if (a == null) {
                return true;
            } else if (a.m3933a()) {
                a.m3932a(false);
                cvVar.m3931a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
