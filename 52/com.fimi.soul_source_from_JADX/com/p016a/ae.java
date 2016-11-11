package com.p016a;

import android.content.Context;
import com.xiaomi.market.sdk.C2537j;
import java.util.List;

/* renamed from: com.a.ae */
public class ae {
    private C0261v f525a;
    private Context f526b;

    public ae(Context context) {
        this.f526b = context;
        this.f525a = m996a(this.f526b);
    }

    private C0261v m996a(Context context) {
        try {
            return new C0261v(context, C0263y.m2053c());
        } catch (Throwable th) {
            C0247g.m1917a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    public ag m997a() {
        try {
            if (this.f525a == null) {
                this.f525a = m996a(this.f526b);
            }
            List c = this.f525a.m2052c("1=1", new af());
            if (c.size() > 0) {
                return (ag) c.get(0);
            }
        } catch (Throwable th) {
            C0247g.m1917a(th, "UpdateLogDB", "getUpdateLog");
        }
        return null;
    }

    public void m998a(ag agVar) {
        if (agVar != null) {
            try {
                if (this.f525a == null) {
                    this.f525a = m996a(this.f526b);
                }
                C0239w afVar = new af();
                afVar.m965a((Object) agVar);
                String str = "1=1";
                List c = this.f525a.m2052c(str, new af());
                if (c == null || c.size() == 0) {
                    this.f525a.m2045a(afVar);
                } else {
                    this.f525a.m2051b(str, afVar);
                }
            } catch (Throwable th) {
                C0247g.m1917a(th, "UpdateLogDB", C2537j.av);
            }
        }
    }
}
