package com.fimi.soul.biz.p103k;

import android.os.Message;
import java.util.TimerTask;

/* renamed from: com.fimi.soul.biz.k.ar */
class ar extends TimerTask {
    final /* synthetic */ aq f6087a;

    ar(aq aqVar) {
        this.f6087a = aqVar;
    }

    public void run() {
        String a = this.f6087a.m9222c();
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = a;
        this.f6087a.f6084e.sendMessage(obtain);
    }
}
