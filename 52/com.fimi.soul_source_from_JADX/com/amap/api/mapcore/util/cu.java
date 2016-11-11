package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.List;

public class cu {
    private ck f2352a;
    private Context f2353b;

    public cu(Context context, boolean z) {
        this.f2353b = context;
        this.f2352a = m3926a(this.f2353b, z);
    }

    private ck m3926a(Context context, boolean z) {
        try {
            return new ck(context, ck.m3884a(cr.class));
        } catch (Throwable th) {
            if (z) {
                th.printStackTrace();
                return null;
            }
            cb.m3809a(th, "SDKDB", "getDB");
            return null;
        }
    }

    public List<bv> m3927a() {
        List<bv> list = null;
        try {
            list = this.f2352a.m3894a(bv.m3762f(), bv.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return list;
    }

    public void m3928a(bv bvVar) {
        if (bvVar != null) {
            try {
                if (this.f2352a == null) {
                    this.f2352a = m3926a(this.f2353b, false);
                }
                String a = bv.m3759a(bvVar.m3763a());
                List b = this.f2352a.m3902b(a, bv.class);
                if (b == null || b.size() == 0) {
                    this.f2352a.m3895a((Object) bvVar);
                } else {
                    this.f2352a.m3899a(a, (Object) bvVar);
                }
            } catch (Throwable th) {
                cb.m3809a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }
}
