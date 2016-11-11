package com.xiaomi.mistatistic.sdk.controller;

import org.codehaus.jackson.smile.SmileConstants;

public class ab {
    private static ab f12943a;
    private int f12944b;
    private long f12945c;
    private long f12946d;

    static {
        f12943a = null;
    }

    private ab() {
        this.f12944b = 3;
    }

    public static synchronized ab m14718a() {
        ab abVar;
        synchronized (ab.class) {
            if (f12943a == null) {
                f12943a = new ab();
            }
            abVar = f12943a;
        }
        return abVar;
    }

    public void m14720a(int i, long j) {
        C2589b.m14731a().m14734a(new ad(this, i, j));
    }

    public void m14721b() {
        C2589b.m14731a().m14734a(new ac(this));
    }

    public void m14722c() {
        this.f12946d = System.currentTimeMillis();
        if (this.f12944b == 4) {
            C2589b.m14731a().m14735a(new af(this), this.f12945c);
        }
        C2589b.m14733b().m14734a(new ag(this));
    }

    public boolean m14723d() {
        if (C2606t.m14792b()) {
            return false;
        }
        switch (this.f12944b) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                return true;
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return C2603q.m14775a(C2588a.m14708a());
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                return new C2595i().m14752d() >= 50;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return System.currentTimeMillis() - this.f12946d > this.f12945c;
            default:
                return false;
        }
    }

    public long m14724e() {
        return this.f12945c;
    }
}
