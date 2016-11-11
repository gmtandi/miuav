package com.fimi.soul.module.calibcompass;

import android.os.Handler;
import android.os.Message;
import com.fimi.soul.module.p091a.C1664h;

/* renamed from: com.fimi.soul.module.calibcompass.o */
class C1681o extends Handler {
    final /* synthetic */ CaliCompassSecondFragment f7939a;

    C1681o(CaliCompassSecondFragment caliCompassSecondFragment) {
        this.f7939a = caliCompassSecondFragment;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        C1664h.m10813a(this.f7939a.b).m10824b((byte) 1, (byte) 1, (byte) 3);
    }
}
