package com.tencent.stat.p136a;

import android.content.Context;
import com.tencent.mm.sdk.platformtools.LocaleUtil;
import java.util.Map.Entry;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.tencent.stat.a.b */
public class C2396b extends C2394e {
    protected C2397c f12263a;
    private long f12264l;

    public C2396b(Context context, int i, String str) {
        super(context, i);
        this.f12263a = new C2397c();
        this.f12264l = -1;
        this.f12263a.f12265a = str;
    }

    public C2399f m13942a() {
        return C2399f.CUSTOM;
    }

    public void m13943a(long j) {
        this.f12264l = j;
    }

    public void m13944a(Properties properties) {
        if (properties != null) {
            this.f12263a.f12267c = (Properties) properties.clone();
        }
    }

    public void m13945a(String[] strArr) {
        if (strArr != null) {
            this.f12263a.f12266b = (String[]) strArr.clone();
        }
    }

    public boolean m13946a(JSONObject jSONObject) {
        jSONObject.put("ei", this.f12263a.f12265a);
        if (this.f12264l > 0) {
            jSONObject.put("du", this.f12264l);
        }
        if (this.f12263a.f12267c == null && this.f12263a.f12266b == null) {
            jSONObject.put("kv", new JSONObject());
        }
        if (this.f12263a.f12266b != null) {
            JSONArray jSONArray = new JSONArray();
            for (Object put : this.f12263a.f12266b) {
                jSONArray.put(put);
            }
            jSONObject.put(LocaleUtil.ARABIC, jSONArray);
        }
        if (this.f12263a.f12267c != null) {
            Object jSONObject2;
            JSONObject jSONObject3 = new JSONObject();
            try {
                for (Entry entry : this.f12263a.f12267c.entrySet()) {
                    jSONObject3.put(entry.getKey().toString(), entry.getValue().toString());
                }
                JSONObject jSONObject4 = jSONObject3;
            } catch (Exception e) {
                jSONObject2 = new JSONObject(this.f12263a.f12267c);
            }
            jSONObject.put("kv", jSONObject2);
        }
        return true;
    }
}
