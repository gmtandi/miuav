package com.fimi.kernel.p073a;

import com.xiaomi.mipush.sdk.MiPushClient;

/* renamed from: com.fimi.kernel.a.e */
public class C1092e {
    public double f5077a;
    public double f5078b;

    public C1092e(double d, double d2) {
        this.f5077a = d;
        this.f5078b = d2;
    }

    public boolean equals(Object obj) {
        C1092e c1092e = (C1092e) obj;
        return this.f5077a == c1092e.f5077a && this.f5078b == c1092e.f5078b;
    }

    public int hashCode() {
        return ((int) (this.f5077a * this.f5078b)) ^ 8;
    }

    public String toString() {
        return "(" + this.f5077a + MiPushClient.ACCEPT_TIME_SEPARATOR + this.f5078b + ")";
    }
}
