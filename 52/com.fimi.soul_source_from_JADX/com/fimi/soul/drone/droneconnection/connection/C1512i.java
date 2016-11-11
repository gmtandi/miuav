package com.fimi.soul.drone.droneconnection.connection;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.i */
class C1512i extends Handler {
    final /* synthetic */ C1498f f7136a;

    C1512i(C1498f c1498f) {
        this.f7136a = c1498f;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 1) {
            this.f7136a.f7087q.removeCallbacks(this.f7136a.f7088s);
            this.f7136a.f7078g = true;
        }
    }
}
