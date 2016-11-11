package com.fimi.kernel.p073a;

import com.xiaomi.mipush.sdk.MiPushClient;

/* renamed from: com.fimi.kernel.a.a */
public class C1088a {
    public C1092e f5058a;
    public double f5059b;

    public C1088a(C1092e c1092e, double d) {
        this.f5058a = c1092e;
        this.f5059b = d;
    }

    public String toString() {
        return "(" + this.f5058a.f5077a + MiPushClient.ACCEPT_TIME_SEPARATOR + this.f5058a.f5078b + "),r=" + this.f5059b;
    }
}
