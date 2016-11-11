package com.fimi.soul.drone.droneconnection.connection.service;

import android.util.Log;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.service.e */
class C1526e implements Runnable {
    final /* synthetic */ C1523b f7162a;

    C1526e(C1523b c1523b) {
        this.f7162a = c1523b;
    }

    public void run() {
        this.f7162a.f7156a.f7147c.removeCallbacks(this);
        this.f7162a.f7156a.f7146b = false;
        String str = (String) this.f7162a.f7156a.f7148d.get();
        if (str != null) {
            Log.e(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2, str);
        }
    }
}
