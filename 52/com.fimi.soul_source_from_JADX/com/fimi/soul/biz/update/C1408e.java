package com.fimi.soul.biz.update;

import com.fimi.soul.drone.p116g.C1543c;

/* renamed from: com.fimi.soul.biz.update.e */
class C1408e implements Runnable {
    final /* synthetic */ C1404a f6353a;

    C1408e(C1404a c1404a) {
        this.f6353a = c1404a;
    }

    public void run() {
        this.f6353a.f6328o = 0;
        this.f6353a.f6329q = true;
        String format = String.format("http://%s:8080/upgrade", new Object[]{C1543c.f7237l});
        while (this.f6353a.f6329q) {
            try {
                this.f6353a.f6324j.m7905a(format, new C1409f(this));
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
