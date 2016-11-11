package com.amap.api.services.core;

import org.p122a.p123a.p152c.p153a.C2924b;

public class ad {
    String f2973a;
    String f2974b;
    String f2975c;
    private boolean f2976d;
    private String f2977e;
    private String[] f2978f;

    /* renamed from: com.amap.api.services.core.ad.a */
    public class C0452a {
        private String f2967a;
        private String f2968b;
        private String f2969c;
        private boolean f2970d;
        private String f2971e;
        private String[] f2972f;

        public C0452a(String str, String str2, String str3) {
            this.f2970d = true;
            this.f2971e = C2924b.f14791c;
            this.f2972f = null;
            this.f2967a = str2;
            this.f2969c = str3;
            this.f2968b = str;
        }

        public C0452a m4490a(String str) {
            this.f2971e = str;
            return this;
        }

        public C0452a m4491a(boolean z) {
            this.f2970d = z;
            return this;
        }

        public C0452a m4492a(String[] strArr) {
            this.f2972f = (String[]) strArr.clone();
            return this;
        }

        public ad m4493a() {
            if (this.f2972f != null) {
                return new ad();
            }
            throw new C0495v("sdk packages is null");
        }
    }

    private ad(C0452a c0452a) {
        this.f2976d = true;
        this.f2977e = C2924b.f14791c;
        this.f2978f = null;
        this.f2973a = c0452a.f2967a;
        this.f2975c = c0452a.f2968b;
        this.f2974b = c0452a.f2969c;
        this.f2976d = c0452a.f2970d;
        this.f2977e = c0452a.f2971e;
        this.f2978f = c0452a.f2972f;
    }

    public String m4494a() {
        return this.f2975c;
    }

    public String m4495b() {
        return this.f2973a;
    }

    public String m4496c() {
        return this.f2974b;
    }

    public String m4497d() {
        return this.f2977e;
    }

    public boolean m4498e() {
        return this.f2976d;
    }

    public String[] m4499f() {
        return (String[]) this.f2978f.clone();
    }
}
