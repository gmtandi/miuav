package com.amap.api.mapcore.util;

import android.text.TextUtils;
import com.p054c.p055a.p063b.p068d.C0921a;
import java.net.Proxy;
import java.util.Map;

public abstract class dj {
    int f2114g;
    int f2115h;
    Proxy f2116i;

    public dj() {
        this.f2114g = C0921a.f4833b;
        this.f2115h = C0921a.f4833b;
        this.f2116i = null;
    }

    public abstract String m3431a();

    public final void m3432a(int i) {
        this.f2114g = i;
    }

    public final void m3433a(Proxy proxy) {
        this.f2116i = proxy;
    }

    public byte[] a_() {
        return null;
    }

    public abstract Map<String, String> m3434b();

    public final void m3435b(int i) {
        this.f2115h = i;
    }

    public abstract Map<String, String> m3436c();

    String m3437f() {
        byte[] a_ = a_();
        if (a_ == null || a_.length == 0) {
            return m3431a();
        }
        Map b = m3434b();
        if (b == null) {
            return m3431a();
        }
        String a = df.m4011a(b);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(m3431a()).append("?").append(a);
        return stringBuffer.toString();
    }

    byte[] m3438g() {
        byte[] a_ = a_();
        if (a_ != null && a_.length != 0) {
            return a_;
        }
        String a = df.m4011a(m3434b());
        return !TextUtils.isEmpty(a) ? bx.m3778a(a) : a_;
    }
}
