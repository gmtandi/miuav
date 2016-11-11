package com.amap.api.services.core;

import android.content.Context;
import com.xiaomi.market.sdk.C2537j;
import java.util.List;

public class aq {
    private ai f3020a;
    private Context f3021b;

    public aq(Context context) {
        this.f3021b = context;
        this.f3020a = m4558a(this.f3021b);
    }

    private ai m4558a(Context context) {
        try {
            return new ai(context);
        } catch (Throwable th) {
            ay.m4590a(th, "UpdateLogDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    public as m4559a() {
        try {
            if (this.f3020a == null) {
                this.f3020a = m4558a(this.f3021b);
            }
            List c = this.f3020a.m4526c("1=1", new ar());
            if (c.size() > 0) {
                return (as) c.get(0);
            }
        } catch (Throwable th) {
            ay.m4590a(th, "UpdateLogDB", "getUpdateLog");
            th.printStackTrace();
        }
        return null;
    }

    public void m4560a(as asVar) {
        if (asVar != null) {
            try {
                if (this.f3020a == null) {
                    this.f3020a = m4558a(this.f3021b);
                }
                ap arVar = new ar();
                arVar.m4509a(asVar);
                String str = "1=1";
                List c = this.f3020a.m4526c(str, new ar());
                if (c == null || c.size() == 0) {
                    this.f3020a.m4523a(arVar);
                } else {
                    this.f3020a.m4525b(str, arVar);
                }
            } catch (Throwable th) {
                ay.m4590a(th, "UpdateLogDB", C2537j.av);
                th.printStackTrace();
            }
        }
    }
}
