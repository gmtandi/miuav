package com.android.volley;

/* renamed from: com.android.volley.i */
class C0561i implements Runnable {
    final /* synthetic */ C0559g f3531a;
    private final C0570r f3532b;
    private final C0604z f3533c;
    private final Runnable f3534d;

    public C0561i(C0559g c0559g, C0570r c0570r, C0604z c0604z, Runnable runnable) {
        this.f3531a = c0559g;
        this.f3532b = c0570r;
        this.f3533c = c0604z;
        this.f3534d = runnable;
    }

    public void run() {
        if (this.f3532b.m5122m()) {
            this.f3532b.m5110b("canceled-at-delivery");
            return;
        }
        if (this.f3533c.m5294a()) {
            this.f3532b.m5109b(this.f3533c.f3687a);
        } else {
            this.f3532b.m5108b(this.f3533c.f3689c);
        }
        if (this.f3533c.f3690d) {
            this.f3532b.m5106a("intermediate-response");
        } else {
            this.f3532b.m5110b("done");
        }
        if (this.f3534d != null) {
            this.f3534d.run();
        }
    }
}
