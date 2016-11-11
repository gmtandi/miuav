package com.tencent.stat.p136a;

import android.content.Context;
import com.tencent.stat.common.C2418k;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.a.j */
public class C2403j extends C2394e {
    Long f12288a;
    String f12289l;
    String f12290m;

    public C2403j(Context context, String str, String str2, int i, Long l) {
        super(context, i);
        this.f12288a = null;
        this.f12290m = str;
        this.f12289l = str2;
        this.f12288a = l;
    }

    public C2399f m13958a() {
        return C2399f.PAGE_VIEW;
    }

    public boolean m13959a(JSONObject jSONObject) {
        C2418k.m14014a(jSONObject, "pi", this.f12289l);
        C2418k.m14014a(jSONObject, "rf", this.f12290m);
        if (this.f12288a != null) {
            jSONObject.put("du", this.f12288a);
        }
        return true;
    }
}
