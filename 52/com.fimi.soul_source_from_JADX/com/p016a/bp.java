package com.p016a;

import android.text.TextUtils;
import com.fimi.kernel.p076b.p080d.C1142e;
import com.p054c.p055a.p063b.p068d.C0921a;
import java.net.Proxy;
import java.util.Map;

/* renamed from: com.a.bp */
public abstract class bp {
    int f550a;
    int f551b;
    Proxy f552c;

    public bp() {
        this.f550a = C0921a.f4833b;
        this.f551b = C0921a.f4833b;
        this.f552c = null;
    }

    public abstract Map<String, String> m1033a();

    public final void m1034a(int i) {
        this.f550a = i;
    }

    public final void m1035a(Proxy proxy) {
        this.f552c = proxy;
    }

    public abstract Map<String, String> m1036b();

    public final void m1037b(int i) {
        this.f551b = i;
    }

    public abstract String m1038c();

    public byte[] m1039d() {
        return null;
    }

    String m1040e() {
        byte[] d = m1039d();
        if (d == null || d.length == 0) {
            return m1038c();
        }
        Map b = m1036b();
        if (b == null) {
            return m1038c();
        }
        String a = bn.m1177a(b);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(m1038c()).append("?").append(a);
        return stringBuffer.toString();
    }

    byte[] m1041f() {
        byte[] d = m1039d();
        if (d != null && d.length != 0) {
            return d;
        }
        String a = bn.m1177a(m1036b());
        try {
            return !TextUtils.isEmpty(a) ? a.getBytes(C1142e.f5201a) : d;
        } catch (Throwable e) {
            Throwable th = e;
            d = a.getBytes();
            C0247g.m1917a(th, "Request", "getConnectionDatas");
            return d;
        }
    }
}
