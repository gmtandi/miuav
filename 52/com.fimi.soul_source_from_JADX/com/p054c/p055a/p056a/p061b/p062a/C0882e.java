package com.p054c.p055a.p056a.p061b.p062a;

import android.graphics.Bitmap;
import com.p054c.p055a.p056a.p061b.C0875c;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.c.a.a.b.a.e */
public class C0882e implements C0875c {
    private final C0875c f4728a;
    private final long f4729b;
    private final Map<String, Long> f4730c;

    public C0882e(C0875c c0875c, long j) {
        this.f4730c = Collections.synchronizedMap(new HashMap());
        this.f4728a = c0875c;
        this.f4729b = 1000 * j;
    }

    public Bitmap m7114a(String str) {
        Long l = (Long) this.f4730c.get(str);
        if (l != null && System.currentTimeMillis() - l.longValue() > this.f4729b) {
            this.f4728a.m7076b(str);
            this.f4730c.remove(str);
        }
        return this.f4728a.m7073a(str);
    }

    public Collection<String> m7115a() {
        return this.f4728a.m7074a();
    }

    public boolean m7116a(String str, Bitmap bitmap) {
        boolean a = this.f4728a.m7075a(str, bitmap);
        if (a) {
            this.f4730c.put(str, Long.valueOf(System.currentTimeMillis()));
        }
        return a;
    }

    public Bitmap m7117b(String str) {
        this.f4730c.remove(str);
        return this.f4728a.m7076b(str);
    }

    public void m7118b() {
        this.f4728a.m7077b();
        this.f4730c.clear();
    }
}
