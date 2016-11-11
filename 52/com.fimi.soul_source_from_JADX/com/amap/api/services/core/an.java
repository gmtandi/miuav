package com.amap.api.services.core;

import android.content.Context;
import java.util.List;

public class an {
    private ai f3011a;
    private Context f3012b;

    public an(Context context) {
        this.f3012b = context;
        this.f3011a = m4545a(this.f3012b);
    }

    private ai m4545a(Context context) {
        try {
            return new ai(context);
        } catch (Throwable th) {
            ay.m4590a(th, "SDKDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    public List<ad> m4546a() {
        List<ad> list = null;
        try {
            ap aoVar = new ao();
            list = this.f3011a.m4526c(ao.m4551c(), aoVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return list;
    }

    public void m4547a(ad adVar) {
        if (adVar != null) {
            try {
                if (this.f3011a == null) {
                    this.f3011a = m4545a(this.f3012b);
                }
                ap aoVar = new ao();
                aoVar.m4509a(adVar);
                String a = ao.m4548a(adVar.m4494a());
                List c = this.f3011a.m4526c(a, new ao());
                if (c == null || c.size() == 0) {
                    this.f3011a.m4523a(aoVar);
                } else {
                    this.f3011a.m4525b(a, aoVar);
                }
            } catch (Throwable th) {
                ay.m4590a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }
}
