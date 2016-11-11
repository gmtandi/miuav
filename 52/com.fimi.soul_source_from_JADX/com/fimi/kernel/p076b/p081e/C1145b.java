package com.fimi.kernel.p076b.p081e;

import com.android.volley.C0558f;
import com.android.volley.C0570r;
import com.android.volley.C0600v;
import com.android.volley.toolbox.ab;
import com.android.volley.toolbox.ai;
import com.android.volley.toolbox.aj;
import com.fimi.kernel.C1189f;
import com.fimi.kernel.p076b.C1111a;
import com.fimi.kernel.p076b.C1153f;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.fimi.kernel.b.e.b */
public class C1145b implements C1111a {
    C0600v f5212a;

    public C1145b() {
        this.f5212a = null;
        this.f5212a = aj.m5174a(C1189f.m8327a());
    }

    private void m7902a(int i, String str, C1153f<String> c1153f) {
        C0570r aiVar = new ai(i, str, new C1149f(this, c1153f), new C1150g(this, c1153f));
        aiVar.m5100a(new C0558f(3000, 1, C2020f.f10933c));
        this.f5212a.m5279a(aiVar);
    }

    private <T> void m7903a(int i, String str, Map<String, String> map, C1153f<T> c1153f, Class<?> cls) {
        this.f5212a.m5279a(new C1144a(i, str, map, c1153f, cls));
    }

    private void m7904a(int i, String str, JSONObject jSONObject, C1153f<JSONObject> c1153f) {
        this.f5212a.m5279a(new ab(i, str, jSONObject, new C1151h(this, c1153f), null));
    }

    public void m7905a(String str, C1153f<String> c1153f) {
        m7902a(0, str, (C1153f) c1153f);
    }

    public <T> void m7906a(String str, C1153f<T> c1153f, Class<?> cls) {
        m7903a(0, str, null, c1153f, cls);
    }

    public void m7907a(String str, Map<String, String> map, C1153f<String> c1153f) {
        this.f5212a.m5279a(new C1148e(this, 1, str, new C1146c(this, c1153f), new C1147d(this, c1153f), map));
    }

    public <T> void m7908a(String str, Map<String, String> map, C1153f<T> c1153f, Class<?> cls) {
        m7903a(1, str, map, c1153f, cls);
    }

    public void m7909a(String str, JSONObject jSONObject, C1153f<JSONObject> c1153f) {
        m7904a(1, str, jSONObject, (C1153f) c1153f);
    }

    public void m7910b(String str, C1153f<String> c1153f) {
        m7902a(1, str, (C1153f) c1153f);
    }

    public <T> void m7911b(String str, C1153f<T> c1153f, Class<?> cls) {
        m7903a(1, str, null, c1153f, cls);
    }

    public void m7912b(String str, JSONObject jSONObject, C1153f<JSONObject> c1153f) {
        m7904a(0, str, jSONObject, (C1153f) c1153f);
    }

    public void m7913c(String str, C1153f<JSONObject> c1153f) {
        m7904a(1, str, null, (C1153f) c1153f);
    }

    public void m7914d(String str, C1153f<JSONObject> c1153f) {
        m7904a(0, str, null, (C1153f) c1153f);
    }
}
