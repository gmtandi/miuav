package com.amap.api.mapcore.util;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.p122a.p123a.p152c.p153a.C2924b;

@cl(a = "a")
public class bv {
    String f2282a;
    String f2283b;
    String f2284c;
    @cm(a = "a1", b = 6)
    private String f2285d;
    @cm(a = "a2", b = 6)
    private String f2286e;
    @cm(a = "a6", b = 2)
    private int f2287f;
    @cm(a = "a3", b = 6)
    private String f2288g;
    @cm(a = "a4", b = 6)
    private String f2289h;
    @cm(a = "a5", b = 6)
    private String f2290i;
    private String f2291j;
    private String[] f2292k;

    /* renamed from: com.amap.api.mapcore.util.bv.a */
    public class C0363a {
        private String f2276a;
        private String f2277b;
        private String f2278c;
        private boolean f2279d;
        private String f2280e;
        private String[] f2281f;

        public C0363a(String str, String str2, String str3) {
            this.f2279d = true;
            this.f2280e = C2924b.f14791c;
            this.f2281f = null;
            this.f2276a = str2;
            this.f2278c = str3;
            this.f2277b = str;
        }

        public C0363a m3757a(String[] strArr) {
            this.f2281f = (String[]) strArr.clone();
            return this;
        }

        public bv m3758a() {
            if (this.f2281f != null) {
                return new bv();
            }
            throw new bk("sdk packages is null");
        }
    }

    private bv() {
        this.f2287f = 1;
        this.f2292k = null;
    }

    private bv(C0363a c0363a) {
        int i = 1;
        this.f2287f = 1;
        this.f2292k = null;
        this.f2282a = c0363a.f2276a;
        this.f2284c = c0363a.f2277b;
        this.f2283b = c0363a.f2278c;
        if (!c0363a.f2279d) {
            i = 0;
        }
        this.f2287f = i;
        this.f2291j = c0363a.f2280e;
        this.f2292k = c0363a.f2281f;
        this.f2286e = bx.m3779b(this.f2282a);
        this.f2285d = bx.m3779b(this.f2284c);
        this.f2288g = bx.m3779b(this.f2283b);
        this.f2289h = bx.m3779b(m3760a(this.f2292k));
        this.f2290i = bx.m3779b(this.f2291j);
    }

    public static String m3759a(String str) {
        Map hashMap = new HashMap();
        hashMap.put("a1", bx.m3779b(str));
        return ck.m3887a(hashMap);
    }

    private String m3760a(String[] strArr) {
        String str = null;
        if (strArr != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                for (String append : strArr) {
                    stringBuilder.append(append).append(";");
                }
                str = stringBuilder.toString();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return str;
    }

    private String[] m3761b(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String m3762f() {
        return "a6=1";
    }

    public String m3763a() {
        if (TextUtils.isEmpty(this.f2284c) && !TextUtils.isEmpty(this.f2285d)) {
            this.f2284c = bx.m3782c(this.f2285d);
        }
        return this.f2284c;
    }

    public void m3764a(boolean z) {
        this.f2287f = z ? 1 : 0;
    }

    public String m3765b() {
        if (TextUtils.isEmpty(this.f2282a) && !TextUtils.isEmpty(this.f2286e)) {
            this.f2282a = bx.m3782c(this.f2286e);
        }
        return this.f2282a;
    }

    public String m3766c() {
        if (TextUtils.isEmpty(this.f2283b) && !TextUtils.isEmpty(this.f2288g)) {
            this.f2283b = bx.m3782c(this.f2288g);
        }
        return this.f2283b;
    }

    public String m3767d() {
        if (TextUtils.isEmpty(this.f2291j) && !TextUtils.isEmpty(this.f2290i)) {
            this.f2291j = bx.m3782c(this.f2290i);
        }
        if (TextUtils.isEmpty(this.f2291j)) {
            this.f2291j = C2924b.f14791c;
        }
        return this.f2291j;
    }

    public String[] m3768e() {
        if ((this.f2292k == null || this.f2292k.length == 0) && !TextUtils.isEmpty(this.f2289h)) {
            this.f2292k = m3761b(bx.m3782c(this.f2289h));
        }
        return (String[]) this.f2292k.clone();
    }
}
