package com.fimi.soul.biz.update;

/* renamed from: com.fimi.soul.biz.update.w */
class C1425w implements Runnable {
    final /* synthetic */ C1423u f6417a;

    C1425w(C1423u c1423u) {
        this.f6417a = c1423u;
    }

    public void run() {
        if (C1423u.f6381O != null) {
            this.f6417a.f6405G.m9569P().m9993a(C1423u.f6381O.m9707a());
        }
        this.f6417a.f6411P = this.f6417a.f6411P + 1;
        if (this.f6417a.f6411P >= 10) {
            this.f6417a.m7685a().sendEmptyMessage(2);
        }
    }
}
