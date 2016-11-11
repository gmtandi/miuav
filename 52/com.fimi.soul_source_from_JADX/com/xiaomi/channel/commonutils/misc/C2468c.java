package com.xiaomi.channel.commonutils.misc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.channel.commonutils.misc.C2467b.C2466b;

/* renamed from: com.xiaomi.channel.commonutils.misc.c */
class C2468c extends Handler {
    final /* synthetic */ C2467b f12435a;

    C2468c(C2467b c2467b, Looper looper) {
        this.f12435a = c2467b;
        super(looper);
    }

    public void handleMessage(Message message) {
        C2466b c2466b = (C2466b) message.obj;
        if (message.what == 0) {
            c2466b.m14134a();
        } else if (message.what == 1) {
            c2466b.m14136c();
        }
        super.handleMessage(message);
    }
}
