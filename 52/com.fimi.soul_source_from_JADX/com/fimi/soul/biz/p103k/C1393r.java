package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.biz.p098j.C1330i;

/* renamed from: com.fimi.soul.biz.k.r */
class C1393r implements Runnable {
    int f6259a;
    Object f6260b;
    C1330i f6261c;
    final /* synthetic */ C1377q f6262d;

    public C1393r(C1377q c1377q, int i, Object obj, C1330i c1330i) {
        this.f6262d = c1377q;
        this.f6259a = i;
        this.f6260b = obj;
        this.f6261c = c1330i;
    }

    public void run() {
        Object obj = null;
        try {
            obj = this.f6262d.m9213b(this.f6260b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Message obtainMessage = this.f6262d.f6072h.obtainMessage();
        obtainMessage.what = this.f6259a;
        obtainMessage.obj = obj;
        this.f6262d.f6072h.sendMessage(obtainMessage);
    }
}
