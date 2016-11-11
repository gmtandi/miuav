package com.fimi.kernel.p076b.p077a;

import android.os.Message;

/* renamed from: com.fimi.kernel.b.a.d */
class C1103d implements Runnable {
    final /* synthetic */ C1102c f5109a;

    C1103d(C1102c c1102c) {
        this.f5109a = c1102c;
    }

    public void run() {
        Message message = new Message();
        message.what = C1108i.ConnectServer.ordinal();
        if (this.f5109a.m7715e()) {
            message.arg1 = 1;
        } else {
            message.arg1 = 0;
        }
        this.f5109a.m7685a().sendMessage(message);
    }
}
