package it.p074a.p075a;

import java.net.Socket;

/* renamed from: it.a.a.d */
class C2797d implements C2794j {
    private final String f14247a;
    private final int f14248b;
    private final C2787b f14249c;

    C2797d(C2787b c2787b, String str, int i) {
        this.f14249c = c2787b;
        this.f14247a = str;
        this.f14248b = i;
    }

    public Socket m15973a() {
        try {
            String c = C2787b.m15879b(this.f14249c).m15835a() ? this.f14247a : C2787b.m15880c(this.f14249c);
            Socket d = C2787b.m15879b(this.f14249c).m15841d(c, this.f14248b);
            return C2787b.m15878a(this.f14249c) ? C2787b.m15876a(this.f14249c, d, c, this.f14248b) : d;
        } catch (Throwable e) {
            throw new C2802k("Cannot connect to the remote server", e);
        }
    }

    public void m15974b() {
    }
}
