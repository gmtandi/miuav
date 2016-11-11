package com.xiaomi.mistatistic.sdk.data;

import com.xiaomi.mipush.sdk.MiPushClient;

/* renamed from: com.xiaomi.mistatistic.sdk.data.g */
public class C2620g extends C2614a {
    private long f12999b;
    private long f13000c;
    private String f13001d;

    public C2620g(long j, long j2, String str) {
        this.f12999b = j;
        this.f13000c = j2;
        this.f13001d = str;
    }

    public C2621h m14819a() {
        C2621h c2621h = new C2621h();
        c2621h.f13002a = m14820b();
        c2621h.f13003b = this.a;
        c2621h.f13006e = this.f12999b + MiPushClient.ACCEPT_TIME_SEPARATOR + this.f13000c;
        c2621h.f13007f = this.f13001d;
        return c2621h;
    }

    public String m14820b() {
        return "mistat_session";
    }
}
