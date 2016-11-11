package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.entity.HomePage;

/* renamed from: com.fimi.soul.biz.k.p */
class C1392p implements Runnable {
    final /* synthetic */ C1391o f6256a;
    private int f6257b;
    private HomePage f6258c;

    public C1392p(C1391o c1391o, int i, HomePage homePage) {
        this.f6256a = c1391o;
        this.f6257b = i;
        this.f6258c = homePage;
    }

    public void run() {
        Message obtainMessage = this.f6256a.f6254g.obtainMessage();
        Object obj = null;
        if (this.f6257b == 11) {
            obj = this.f6256a.f6255h.m9071a(this.f6258c, this.f6256a.f6252e);
        } else if (this.f6257b == 12) {
            obj = this.f6256a.f6255h.m9073b(this.f6258c, this.f6256a.f6252e);
        } else if (this.f6257b == 10) {
            obj = this.f6256a.f6255h.m9072b(this.f6256a.f6252e);
        }
        obtainMessage.what = this.f6257b;
        obtainMessage.obj = obj;
        this.f6256a.f6254g.sendMessage(obtainMessage);
    }
}
