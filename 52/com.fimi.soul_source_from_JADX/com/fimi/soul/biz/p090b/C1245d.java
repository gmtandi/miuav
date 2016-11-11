package com.fimi.soul.biz.p090b;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.fimi.soul.biz.b.d */
class C1245d extends Handler {
    final /* synthetic */ C1244c f5662a;

    C1245d(C1244c c1244c) {
        this.f5662a = c1244c;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == this.f5662a.f5657n) {
            this.f5662a.f5658o = false;
        } else if (message.what == 11) {
            this.f5662a.f5659p = false;
        }
    }
}
