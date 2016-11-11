package com.fimi.soul.module.calibcompass;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.fimi.soul.module.calibcompass.b */
class C1671b extends Handler {
    final /* synthetic */ CaliComPassFirstFragment f7929a;

    C1671b(CaliComPassFirstFragment caliComPassFirstFragment) {
        this.f7929a = caliComPassFirstFragment;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        this.f7929a.f7867k = true;
    }
}
