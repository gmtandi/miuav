package com.p016a;

import android.content.Context;
import java.util.List;

/* renamed from: com.a.ac */
public class ac {
    private C0261v f516a;
    private Context f517b;

    public ac(Context context, boolean z) {
        this.f517b = context;
        this.f516a = m983a(this.f517b, z);
    }

    private C0261v m983a(Context context, boolean z) {
        try {
            return new C0261v(context, C0263y.m2053c());
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
                return null;
            }
            C0247g.m1917a(th, "SDKDB", "getDB");
            return null;
        }
    }

    public List<gd> m984a() {
        List<gd> list = null;
        try {
            C0239w adVar = new ad();
            list = this.f516a.m2050b(ad.m989c(), adVar, true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return list;
    }

    public void m985a(gd gdVar) {
        if (gdVar != null) {
            try {
                if (this.f516a == null) {
                    this.f516a = m983a(this.f517b, false);
                }
                C0239w adVar = new ad();
                adVar.m965a((Object) gdVar);
                String a = ad.m986a(gdVar.m1938a());
                List c = this.f516a.m2052c(a, new ad());
                if (c == null || c.size() == 0) {
                    this.f516a.m2045a(adVar);
                } else {
                    this.f516a.m2051b(a, adVar);
                }
            } catch (Throwable th) {
                C0247g.m1917a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }
}
