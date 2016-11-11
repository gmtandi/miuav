package com.tencent.stat.common;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.common.a */
public class C2408a {
    static C2410c f12307a;
    private static StatLogger f12308d;
    private static JSONObject f12309e;
    Integer f12310b;
    String f12311c;

    static {
        f12308d = C2418k.m14018b();
        f12309e = null;
    }

    public C2408a(Context context) {
        this.f12310b = null;
        this.f12311c = null;
        try {
            C2408a.m13982a(context);
            this.f12310b = C2418k.m14044q(context.getApplicationContext());
            this.f12311c = C2418k.m14043p(context);
        } catch (Object th) {
            f12308d.m13978e(th);
        }
    }

    static synchronized C2410c m13982a(Context context) {
        C2410c c2410c;
        synchronized (C2408a.class) {
            if (f12307a == null) {
                f12307a = new C2410c(null);
            }
            c2410c = f12307a;
        }
        return c2410c;
    }

    public static void m13983a(Context context, Map<String, String> map) {
        if (map != null) {
            Map hashMap = new HashMap(map);
            if (f12309e == null) {
                f12309e = new JSONObject();
            }
            for (Entry entry : hashMap.entrySet()) {
                f12309e.put((String) entry.getKey(), entry.getValue());
            }
        }
    }

    public void m13984a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (f12307a != null) {
                f12307a.m13985a(jSONObject2);
            }
            C2418k.m14014a(jSONObject2, "cn", this.f12311c);
            if (this.f12310b != null) {
                jSONObject2.put("tn", this.f12310b);
            }
            jSONObject.put("ev", jSONObject2);
            if (f12309e != null && f12309e.length() > 0) {
                jSONObject.put("eva", f12309e);
            }
        } catch (Object th) {
            f12308d.m13978e(th);
        }
    }
}
