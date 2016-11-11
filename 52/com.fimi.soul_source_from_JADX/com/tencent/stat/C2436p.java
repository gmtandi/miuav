package com.tencent.stat;

import java.util.List;

/* renamed from: com.tencent.stat.p */
class C2436p implements Runnable {
    final /* synthetic */ List f12391a;
    final /* synthetic */ int f12392b;
    final /* synthetic */ C2434n f12393c;

    C2436p(C2434n c2434n, List list, int i) {
        this.f12393c = c2434n;
        this.f12391a = list;
        this.f12392b = i;
    }

    public void run() {
        this.f12393c.m14095b(this.f12391a, this.f12392b);
    }
}
