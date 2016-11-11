package com.tencent.stat;

/* renamed from: com.tencent.stat.v */
class C2442v implements Runnable {
    final /* synthetic */ int f12405a;
    final /* synthetic */ C2434n f12406b;

    C2442v(C2434n c2434n, int i) {
        this.f12406b = c2434n;
        this.f12405a = i;
    }

    public void run() {
        int a = StatConfig.m13896a();
        int i = this.f12405a == -1 ? this.f12406b.f12386b : this.f12405a;
        int i2 = i / a;
        int i3 = i % a;
        for (i = 0; i < i2 + 1; i++) {
            this.f12406b.m14091b(a);
        }
        if (i3 > 0) {
            this.f12406b.m14091b(i3);
        }
    }
}
