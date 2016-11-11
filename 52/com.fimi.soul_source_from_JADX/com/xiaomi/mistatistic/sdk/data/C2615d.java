package com.xiaomi.mistatistic.sdk.data;

import com.xiaomi.mistatistic.sdk.controller.C2601o;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.xiaomi.mistatistic.sdk.data.d */
public class C2615d extends C2614a {
    protected long f12991b;
    private String f12992c;
    private String f12993d;
    private Map f12994e;

    public C2615d(String str, String str2, long j) {
        this(str, str2, j, null);
    }

    public C2615d(String str, String str2, long j, Map map) {
        this.f12992c = str;
        this.f12993d = str2;
        this.f12991b = j;
        this.f12994e = map;
    }

    private String m14811a(Map map) {
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    JSONObject jSONObject = new JSONObject();
                    for (String str : map.keySet()) {
                        jSONObject.put(str, map.get(str));
                    }
                    return jSONObject.toString();
                }
            } catch (Throwable e) {
                new C2601o().m14770a("json error", e);
            }
        }
        return null;
    }

    public C2621h m14812a() {
        C2621h c2621h = new C2621h();
        c2621h.f13002a = this.f12992c;
        c2621h.f13004c = this.f12993d;
        c2621h.f13003b = this.a;
        c2621h.f13005d = m14813b();
        c2621h.f13006e = String.valueOf(this.f12991b);
        c2621h.f13007f = m14811a(this.f12994e);
        return c2621h;
    }

    public String m14813b() {
        return "numeric";
    }
}
