package com.fimi.soul.biz.p103k;

import android.os.Message;
import com.fimi.soul.entity.AddPlane;

/* renamed from: com.fimi.soul.biz.k.b */
class C1378b implements Runnable {
    final /* synthetic */ C1376a f6127a;
    private int f6128b;
    private AddPlane f6129c;

    public C1378b(C1376a c1376a, int i, AddPlane addPlane) {
        this.f6127a = c1376a;
        this.f6128b = i;
        this.f6129c = addPlane;
    }

    public void run() {
        Message obtainMessage = this.f6127a.f6011b.obtainMessage();
        Object obj = null;
        if (this.f6128b == 1) {
            obj = this.f6127a.f6012c.m9021a(this.f6129c, this.f6127a.f6014f);
        }
        obtainMessage.what = this.f6128b;
        obtainMessage.obj = obj;
        this.f6127a.f6011b.sendMessage(obtainMessage);
    }
}
