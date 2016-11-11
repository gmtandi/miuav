package com.tencent.stat;

import java.util.List;

/* renamed from: com.tencent.stat.q */
class C2437q implements Runnable {
    final /* synthetic */ List f12394a;
    final /* synthetic */ C2434n f12395b;

    C2437q(C2434n c2434n, List list) {
        this.f12395b = c2434n;
        this.f12394a = list;
    }

    public void run() {
        this.f12395b.m14094b(this.f12394a);
    }
}
