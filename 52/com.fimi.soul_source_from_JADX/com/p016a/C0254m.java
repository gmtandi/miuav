package com.p016a;

import android.content.Context;
import android.os.Looper;
import java.util.List;

/* renamed from: com.a.m */
public class C0254m extends C0251r {
    private static boolean f1296a;

    static {
        f1296a = true;
    }

    protected C0254m(int i) {
        super(i);
    }

    protected String m2026a(List<gd> list) {
        return null;
    }

    protected boolean m2027a(Context context) {
        if (fw.m1888m(context) != 1 || !f1296a) {
            return false;
        }
        f1296a = false;
        synchronized (Looper.getMainLooper()) {
            ae aeVar = new ae(context);
            ag a = aeVar.m997a();
            if (a == null) {
                return true;
            } else if (a.m1008b()) {
                a.m1007b(false);
                aeVar.m998a(a);
                return true;
            } else {
                return false;
            }
        }
    }
}
