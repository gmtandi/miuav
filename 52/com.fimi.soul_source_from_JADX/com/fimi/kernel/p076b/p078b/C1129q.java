package com.fimi.kernel.p076b.p078b;

import android.os.Message;
import com.fimi.kernel.C1099d;
import com.fimi.kernel.p076b.C1111a;
import com.fimi.kernel.p076b.C1153f;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.fimi.kernel.b.b.q */
public class C1129q extends C1099d implements C1111a {
    private volatile int f5180a;
    private Map<Integer, C1153f> f5181b;

    public C1129q() {
        this.f5180a = 0;
        this.f5181b = new HashMap();
    }

    private synchronized int m7830b() {
        this.f5180a++;
        return this.f5180a;
    }

    protected void m7833a(Message message) {
        C1153f c1153f = (C1153f) this.f5181b.get(Integer.valueOf(message.what));
        if (c1153f != null) {
            if (message.arg1 == 1) {
                c1153f.m7924a(message.obj);
            } else {
                c1153f.m7925b(message.obj);
            }
            this.f5181b.remove(c1153f);
        }
    }

    public void m7834a(String str, C1153f<String> c1153f) {
        m7687a(new C1130r(this, c1153f, str));
    }

    public <T> void m7835a(String str, C1153f<T> c1153f, Class<?> cls) {
    }

    public void m7836a(String str, Map<String, String> map, C1153f<String> c1153f) {
    }

    public <T> void m7837a(String str, Map<String, String> map, C1153f<T> c1153f, Class<?> cls) {
    }

    public void m7838a(String str, JSONObject jSONObject, C1153f<JSONObject> c1153f) {
    }

    public void m7839b(String str, C1153f<String> c1153f) {
    }

    public <T> void m7840b(String str, C1153f<T> c1153f, Class<?> cls) {
    }

    public void m7841b(String str, JSONObject jSONObject, C1153f<JSONObject> c1153f) {
    }

    public void m7842c(String str, C1153f<JSONObject> c1153f) {
    }

    public void m7843d(String str, C1153f<JSONObject> c1153f) {
    }
}
