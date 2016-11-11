package com.fimi.soul.biz.p103k;

import android.os.Message;

/* renamed from: com.fimi.soul.biz.k.an */
class an implements Runnable {
    final /* synthetic */ am f6061a;
    private int f6062b;
    private String f6063c;
    private String f6064d;

    public an(am amVar, int i, String str, String str2) {
        this.f6061a = amVar;
        this.f6062b = i;
        this.f6063c = str;
        this.f6064d = str2;
    }

    public void run() {
        Message obtainMessage = this.f6061a.f6060g.obtainMessage();
        Object obj = null;
        if (this.f6062b == 0) {
            obj = this.f6061a.f6058e.m9101a(this.f6063c, this.f6064d, this.f6061a.f6059f);
        }
        if (this.f6062b == 1) {
            obj = this.f6061a.f6058e.m9100a(this.f6061a.f6059f);
        }
        obtainMessage.what = this.f6062b;
        obtainMessage.obj = obj;
        this.f6061a.f6060g.sendMessage(obtainMessage);
    }
}
