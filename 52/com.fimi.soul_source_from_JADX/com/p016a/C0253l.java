package com.p016a;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

/* renamed from: com.a.l */
public class C0253l extends C0251r {
    private static boolean f1295a;

    static {
        f1295a = true;
    }

    protected C0253l(int i) {
        super(i);
    }

    protected String m2023a(String str) {
        return fz.m1913c(str + gf.m1955a(new Date().getTime()));
    }

    protected String m2024a(List<gd> list) {
        return null;
    }

    protected boolean m2025a(Context context) {
        if (!f1295a) {
            return false;
        }
        f1295a = false;
        synchronized (Looper.getMainLooper()) {
            ae aeVar = new ae(context);
            ag a = aeVar.m997a();
            if (a == null) {
                return true;
            } else if (a.m1006a()) {
                a.m1005a(false);
                aeVar.m998a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
