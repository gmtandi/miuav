package com.tencent.stat.p136a;

import android.content.Context;
import com.tencent.stat.common.C2418k;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.a.i */
public class C2402i extends C2394e {
    private static String f12285a;
    private String f12286l;
    private String f12287m;

    static {
        f12285a = null;
    }

    public C2402i(Context context, int i) {
        super(context, i);
        this.f12286l = null;
        this.f12287m = null;
        this.f12286l = C2418k.m14043p(context);
        if (f12285a == null) {
            f12285a = C2418k.m14040m(context);
        }
    }

    public C2399f m13955a() {
        return C2399f.NETWORK_MONITOR;
    }

    public void m13956a(String str) {
        this.f12287m = str;
    }

    public boolean m13957a(JSONObject jSONObject) {
        C2418k.m14014a(jSONObject, "op", f12285a);
        C2418k.m14014a(jSONObject, "cn", this.f12286l);
        jSONObject.put("sp", this.f12287m);
        return true;
    }
}
