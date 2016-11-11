package com.fimi.kernel.p076b.p081e;

import com.android.volley.C0566n;
import com.android.volley.C0570r;
import com.android.volley.C0604z;
import com.android.volley.toolbox.C0587m;
import com.fimi.kernel.p076b.C1153f;
import com.google.gson.Gson;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/* renamed from: com.fimi.kernel.b.e.a */
class C1144a<T> extends C0570r<T> {
    private Map<String, String> f5208a;
    private C1153f<T> f5209b;
    private Class<?> f5210c;
    private Gson f5211d;

    public C1144a(int i, String str, Map<String, String> map, C1153f<T> c1153f, Class<?> cls) {
        super(i, str, null);
        this.f5211d = new Gson();
        this.f5209b = c1153f;
        this.f5208a = map;
        this.f5210c = cls;
    }

    protected C0604z<T> m7899a(C0566n c0566n) {
        try {
            return C0604z.m5293a(this.f5211d.fromJson(new String(c0566n.f3542b, C0587m.m5227a(c0566n.f3543c)), this.f5210c), C0587m.m5226a(c0566n));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void m7900b(T t) {
        if (this.f5209b != null) {
            this.f5209b.m7924a(t);
        }
    }

    protected Map<String, String> m7901s() {
        return this.f5208a;
    }
}
