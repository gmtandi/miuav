package com.fimi.soul.module.remote;

import android.os.Handler;
import android.os.Message;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.module.remote.h */
class C1827h extends Handler {
    final /* synthetic */ RemoteEndCaliFragment f9011a;

    C1827h(RemoteEndCaliFragment remoteEndCaliFragment) {
        this.f9011a = remoteEndCaliFragment;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.f9011a.e != null) {
            this.f9011a.e.m11561a(C1205R.id.endmidcalibration, C1205R.id.caliremotestart);
        }
    }
}
