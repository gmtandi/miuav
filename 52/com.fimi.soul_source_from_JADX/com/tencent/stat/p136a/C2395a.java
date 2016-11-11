package com.tencent.stat.p136a;

import android.content.Context;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.C2418k;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.a.a */
public class C2395a extends C2394e {
    Map<String, ?> f12262a;

    public C2395a(Context context, int i, Map<String, ?> map) {
        super(context, i);
        this.f12262a = null;
        this.f12262a = map;
    }

    public C2399f m13940a() {
        return C2399f.ADDITION;
    }

    public boolean m13941a(JSONObject jSONObject) {
        C2418k.m14014a(jSONObject, "qq", StatConfig.getQQ());
        if (this.f12262a != null && this.f12262a.size() > 0) {
            for (Entry entry : this.f12262a.entrySet()) {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            }
        }
        return true;
    }
}
