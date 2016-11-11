package com.tencent.stat;

import java.util.List;

/* renamed from: com.tencent.stat.f */
class C2426f implements Runnable {
    final /* synthetic */ List f12370a;
    final /* synthetic */ C2407c f12371b;
    final /* synthetic */ C2424d f12372c;

    C2426f(C2424d c2424d, List list, C2407c c2407c) {
        this.f12372c = c2424d;
        this.f12370a = list;
        this.f12371b = c2407c;
    }

    public void run() {
        try {
            this.f12372c.m14071a(this.f12370a, this.f12371b);
        } catch (Object th) {
            C2424d.f12363c.m13978e(th);
        }
    }
}
