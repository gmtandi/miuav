package com.xiaomi.push.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.push.service.XMPushService.C2629e;

/* renamed from: com.xiaomi.push.service.e */
class C2649e extends Handler {
    final /* synthetic */ C2648d f13131a;

    C2649e(C2648d c2648d, Looper looper) {
        this.f13131a = c2648d;
        super(looper);
    }

    public void handleMessage(Message message) {
        this.f13131a.f13128b = true;
        this.f13131a.f13127a = System.currentTimeMillis();
        if (message.obj instanceof C2629e) {
            ((C2629e) message.obj).m14896c();
        }
        this.f13131a.f13128b = false;
    }
}
