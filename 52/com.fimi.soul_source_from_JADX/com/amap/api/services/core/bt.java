package com.amap.api.services.core;

import com.p054c.p055a.p063b.p068d.C0921a;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;

public abstract class bt {
    int f2957e;
    int f2958f;
    HttpHost f2959g;

    public bt() {
        this.f2957e = C0921a.f4833b;
        this.f2958f = C0921a.f4833b;
        this.f2959g = null;
    }

    public final void m4444a(HttpHost httpHost) {
        this.f2959g = httpHost;
    }

    public abstract String m4445b();

    public final void m4446c(int i) {
        this.f2957e = i;
    }

    public abstract Map<String, String> c_();

    public final void m4447d(int i) {
        this.f2958f = i;
    }

    public abstract Map<String, String> d_();

    public abstract HttpEntity m4448e();

    public byte[] e_() {
        return null;
    }
}
