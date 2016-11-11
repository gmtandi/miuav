package com.fimi.kernel;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.fimi.kernel.c */
class C1158c extends Handler {
    final /* synthetic */ BaseFragment f5242a;

    C1158c(BaseFragment baseFragment) {
        this.f5242a = baseFragment;
    }

    public void handleMessage(Message message) {
        this.f5242a.m7662a(message);
        super.handleMessage(message);
    }
}
