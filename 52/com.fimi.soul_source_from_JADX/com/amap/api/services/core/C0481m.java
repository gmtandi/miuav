package com.amap.api.services.core;

import android.os.HandlerThread;
import android.os.Message;

/* renamed from: com.amap.api.services.core.m */
class C0481m extends HandlerThread {
    final /* synthetic */ C0480l f3149a;

    C0481m(C0480l c0480l, String str) {
        this.f3149a = c0480l;
        super(str);
    }

    public void run() {
        String str = "run";
        Thread.currentThread().setName("ManifestConfigThread");
        Message message = new Message();
        try {
            message.obj = new C0482n(C0480l.f3146c).m4846a();
            message.what = 3;
            if (this.f3149a.f3147d != null) {
                this.f3149a.f3147d.sendMessage(message);
            }
        } catch (Throwable th) {
            message.what = 3;
            if (this.f3149a.f3147d != null) {
                this.f3149a.f3147d.sendMessage(message);
            }
        }
        try {
            C0481m.sleep(10000);
        } catch (Throwable th2) {
            C0471d.m4762a(th2, "ManifestConfig", "mVerfy");
        }
    }
}
