package com.xiaomi.mistatistic.sdk.controller;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import org.p122a.p123a.C2915a;

/* renamed from: com.xiaomi.mistatistic.sdk.controller.y */
class C2611y extends Handler {
    final /* synthetic */ C2610x f12985a;

    C2611y(C2610x c2610x, Looper looper) {
        this.f12985a = c2610x;
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 31415927:
                long a = C2605s.m14780a(C2588a.m14708a(), "session_begin", 0);
                long a2 = C2605s.m14780a(C2588a.m14708a(), "last_deactivate", 0);
                String a3 = C2605s.m14781a(C2588a.m14708a(), "pv_path", C2915a.f14760f);
                if (a > 0 && a2 > a) {
                    this.f12985a.m14802a(C2588a.m14708a(), a, a2);
                }
                if (!TextUtils.isEmpty(a3)) {
                    this.f12985a.m14803a(C2588a.m14708a(), a3);
                }
            default:
        }
    }
}
