package com.xiaomi.mistatistic.sdk.controller;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.k */
class C2597k extends Handler {
    final /* synthetic */ C2596j f12971a;

    C2597k(C2596j c2596j, Looper looper) {
        this.f12971a = c2596j;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1023:
                C2589b.m14733b().m14734a(new C2598l(this));
            default:
        }
    }
}
