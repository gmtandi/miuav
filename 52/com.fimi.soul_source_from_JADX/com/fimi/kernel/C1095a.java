package com.fimi.kernel;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.fimi.kernel.a */
class C1095a extends Handler {
    final /* synthetic */ BaseActivity f5092a;

    C1095a(BaseActivity baseActivity) {
        this.f5092a = baseActivity;
    }

    public void handleMessage(Message message) {
        this.f5092a.onHandleMessage(message);
        super.handleMessage(message);
    }
}
