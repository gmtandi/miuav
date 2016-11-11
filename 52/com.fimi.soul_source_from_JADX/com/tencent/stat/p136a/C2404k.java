package com.tencent.stat.p136a;

import android.content.Context;
import com.tencent.stat.common.C2408a;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.a.k */
public class C2404k extends C2394e {
    private C2408a f12291a;
    private JSONObject f12292l;

    public C2404k(Context context, int i, JSONObject jSONObject) {
        super(context, i);
        this.f12292l = null;
        this.f12291a = new C2408a(context);
        this.f12292l = jSONObject;
    }

    public C2399f m13960a() {
        return C2399f.SESSION_ENV;
    }

    public boolean m13961a(JSONObject jSONObject) {
        if (this.f12255e != null) {
            jSONObject.put("ut", this.f12255e.getUserType());
        }
        if (this.f12292l != null) {
            jSONObject.put("cfg", this.f12292l);
        }
        this.f12291a.m13984a(jSONObject);
        return true;
    }
}
