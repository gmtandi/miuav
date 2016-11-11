package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.AMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/* renamed from: com.amap.api.mapcore.util.r */
public class C0399r {
    private static C0399r f2516a;
    private dn f2517b;
    private LinkedHashMap<String, dp> f2518c;
    private boolean f2519d;

    private C0399r(boolean z, int i) {
        this.f2518c = new LinkedHashMap();
        this.f2519d = true;
        if (z) {
            try {
                this.f2517b = dn.m4036a(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static C0399r m4186a(int i) {
        return C0399r.m4187a(true, i);
    }

    private static synchronized C0399r m4187a(boolean z, int i) {
        C0399r c0399r;
        synchronized (C0399r.class) {
            try {
                if (f2516a == null) {
                    f2516a = new C0399r(z, i);
                } else if (z) {
                    if (f2516a.f2517b == null) {
                        f2516a.f2517b = dn.m4036a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            c0399r = f2516a;
        }
        return c0399r;
    }

    public void m4188a() {
        synchronized (this.f2518c) {
            if (this.f2518c.size() < 1) {
                return;
            }
            for (Entry entry : this.f2518c.entrySet()) {
                entry.getKey();
                ((C0396n) entry.getValue()).m4172b();
            }
            this.f2518c.clear();
        }
    }

    public void m4189a(C0384q c0384q) {
        synchronized (this.f2518c) {
            C0396n c0396n = (C0396n) this.f2518c.get(c0384q.m4060b());
            if (c0396n == null) {
                return;
            }
            c0396n.m4172b();
        }
    }

    public void m4190a(C0384q c0384q, Context context, AMap aMap) {
        if (this.f2517b == null) {
        }
        if (!this.f2518c.containsKey(c0384q.m4060b())) {
            C0396n c0396n = new C0396n((ag) c0384q, context.getApplicationContext(), aMap);
            synchronized (this.f2518c) {
                this.f2518c.put(c0384q.m4060b(), c0396n);
            }
        }
        this.f2517b.m4043a((dp) this.f2518c.get(c0384q.m4060b()));
    }

    public void m4191b() {
        m4188a();
        dn dnVar = this.f2517b;
        dn.m4037a();
        this.f2517b = null;
        f2516a = null;
    }

    public void m4192b(C0384q c0384q) {
        C0396n c0396n = (C0396n) this.f2518c.get(c0384q.m4060b());
        if (c0396n != null) {
            synchronized (this.f2518c) {
                c0396n.m4173c();
                this.f2518c.remove(c0384q.m4060b());
            }
        }
    }
}
