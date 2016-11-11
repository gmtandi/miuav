package com.amap.api.mapcore.util;

import android.content.Context;
import com.xiaomi.market.sdk.C2537j;
import java.util.List;

public class cv {
    private ck f2354a;
    private Context f2355b;

    public cv(Context context) {
        this.f2355b = context;
        this.f2354a = m3929a(this.f2355b);
    }

    private ck m3929a(Context context) {
        try {
            return new ck(context, ck.m3884a(cr.class));
        } catch (Throwable th) {
            cb.m3809a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    public cw m3930a() {
        try {
            if (this.f2354a == null) {
                this.f2354a = m3929a(this.f2355b);
            }
            List b = this.f2354a.m3902b("1=1", cw.class);
            if (b.size() > 0) {
                return (cw) b.get(0);
            }
        } catch (Throwable th) {
            cb.m3809a(th, "UpdateLogDB", "getUpdateLog");
        }
        return null;
    }

    public void m3931a(cw cwVar) {
        if (cwVar != null) {
            try {
                if (this.f2354a == null) {
                    this.f2354a = m3929a(this.f2355b);
                }
                String str = "1=1";
                List b = this.f2354a.m3902b(str, cw.class);
                if (b == null || b.size() == 0) {
                    this.f2354a.m3895a((Object) cwVar);
                } else {
                    this.f2354a.m3899a(str, (Object) cwVar);
                }
            } catch (Throwable th) {
                cb.m3809a(th, "UpdateLogDB", C2537j.av);
            }
        }
    }
}
