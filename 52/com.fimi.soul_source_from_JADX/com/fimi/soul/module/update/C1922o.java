package com.fimi.soul.module.update;

import java.util.Timer;
import java.util.TimerTask;

/* renamed from: com.fimi.soul.module.update.o */
public class C1922o {
    Timer f9862a;
    C1900q f9863b;
    private final int f9864c;
    private int f9865d;

    public C1922o(int i, C1900q c1900q) {
        this.f9864c = 40;
        this.f9862a = new Timer();
        this.f9865d = i;
        this.f9863b = c1900q;
    }

    public void m12084a() {
        TimerTask c1923p = new C1923p(this);
        if (this.f9862a != null) {
            this.f9862a.schedule(c1923p, 100, 1000);
        }
    }

    public void m12085a(int i) {
        this.f9865d = i;
    }

    public void m12086b() {
        m12085a(0);
    }

    public int m12087c() {
        return this.f9865d;
    }

    public void m12088d() {
        this.f9865d = 0;
        if (this.f9862a != null) {
            this.f9862a.cancel();
            this.f9862a = null;
        }
    }
}
