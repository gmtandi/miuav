package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.entity.Plane;

/* renamed from: com.fimi.soul.biz.k.y */
class C1400y implements Runnable {
    final /* synthetic */ C1399x f6296a;
    private int f6297b;
    private Plane f6298c;

    public C1400y(C1399x c1399x, int i, Plane plane) {
        this.f6296a = c1399x;
        this.f6297b = i;
        this.f6298c = plane;
    }

    public void run() {
        Message obtainMessage = this.f6296a.f6293f.obtainMessage();
        Object obj = null;
        if (this.f6297b == 0) {
            obj = this.f6296a.f6292e.m9018a(this.f6296a.f6294g);
        } else if (this.f6297b == 1) {
            obj = this.f6296a.f6292e.m9085a(this.f6298c, this.f6296a.f6294g);
        } else if (this.f6297b == 2) {
        }
        obtainMessage.what = this.f6297b;
        obtainMessage.obj = obj;
        this.f6296a.f6293f.sendMessage(obtainMessage);
    }
}
