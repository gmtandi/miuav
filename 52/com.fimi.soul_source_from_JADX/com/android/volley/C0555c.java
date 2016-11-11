package com.android.volley;

import java.util.Collections;
import java.util.Map;

/* renamed from: com.android.volley.c */
public class C0555c {
    public byte[] f3506a;
    public String f3507b;
    public long f3508c;
    public long f3509d;
    public long f3510e;
    public long f3511f;
    public Map<String, String> f3512g;

    public C0555c() {
        this.f3512g = Collections.emptyMap();
    }

    public boolean m5072a() {
        return this.f3510e < System.currentTimeMillis();
    }

    public boolean m5073b() {
        return this.f3511f < System.currentTimeMillis();
    }
}
