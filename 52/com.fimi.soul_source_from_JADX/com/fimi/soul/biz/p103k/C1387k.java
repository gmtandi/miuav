package com.fimi.soul.biz.p103k;

import android.os.Message;

/* renamed from: com.fimi.soul.biz.k.k */
class C1387k implements Runnable {
    final /* synthetic */ C1386j f6231a;
    private int f6232b;

    public C1387k(C1386j c1386j, int i) {
        this.f6231a = c1386j;
        this.f6232b = i;
    }

    public void run() {
        Message obtainMessage = this.f6231a.f6228g.obtainMessage();
        Object obj = null;
        if (this.f6232b == 0) {
            obj = this.f6231a.f6227f.m9053b(this.f6231a.f6230i);
        } else if (this.f6232b == 1) {
            obj = this.f6231a.f6227f.m9054c(this.f6231a.f6230i);
        } else if (this.f6232b == 2) {
            obj = this.f6231a.f6227f.m9055d(this.f6231a.f6230i);
        } else if (this.f6232b == 3) {
            obj = this.f6231a.f6227f.m9056e(this.f6231a.f6230i);
        }
        obtainMessage.what = this.f6232b;
        obtainMessage.obj = obj;
        this.f6231a.f6228g.sendMessage(obtainMessage);
    }
}
