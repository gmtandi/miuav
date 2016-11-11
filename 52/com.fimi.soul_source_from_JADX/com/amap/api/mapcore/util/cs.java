package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.List;

public class cs {
    private ck f2351a;

    public cs(Context context) {
        try {
            this.f2351a = new ck(context, ck.m3884a(cr.class));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        }
    }

    private void m3920c(String str, Class<? extends ct> cls) {
        this.f2351a.m3898a(ct.m3908c(str), (Class) cls);
    }

    public List<? extends ct> m3921a(int i, Class<? extends ct> cls) {
        List<? extends ct> list = null;
        try {
            list = this.f2351a.m3902b(ct.m3907c(i), cls);
        } catch (Throwable th) {
            cb.m3809a(th, "LogDB", "ByState");
        }
        return list;
    }

    public void m3922a(ct ctVar) {
        if (ctVar != null) {
            String c = ct.m3908c(ctVar.m3912b());
            List a = this.f2351a.m3894a(c, ctVar.getClass(), true);
            if (a == null || a.size() == 0) {
                this.f2351a.m3897a((Object) ctVar, true);
                return;
            }
            Object obj = (ct) a.get(0);
            if (ctVar.m3909a() == 0) {
                obj.m3913b(obj.m3915c() + 1);
            } else {
                obj.m3913b(0);
            }
            this.f2351a.m3899a(c, obj);
        }
    }

    public void m3923a(String str, Class<? extends ct> cls) {
        try {
            m3920c(str, cls);
        } catch (Throwable th) {
            cb.m3809a(th, "LogDB", "delLog");
        }
    }

    public void m3924b(ct ctVar) {
        try {
            this.f2351a.m3899a(ct.m3908c(ctVar.m3912b()), (Object) ctVar);
        } catch (Throwable th) {
            cb.m3809a(th, "LogDB", "updateLogInfo");
        }
    }

    public void m3925b(String str, Class<? extends ct> cls) {
        try {
            m3920c(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
