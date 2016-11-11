package com.fimi.soul.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.fimi.soul.biz.p096d.C1325k;

/* renamed from: com.fimi.soul.service.z */
class C1959z extends BroadcastReceiver {
    final /* synthetic */ UsbStatus f10002a;

    C1959z(UsbStatus usbStatus) {
        this.f10002a = usbStatus;
    }

    public void onReceive(Context context, Intent intent) {
        if (UsbStatus.f9959a.equals(intent.getAction()) && !intent.getExtras().getBoolean("connected")) {
            if (this.f10002a.f9962d.m9570Q() || this.f10002a.f9962d.m9569P().m9995a()) {
                this.f10002a.f9962d.m9569P().m9999d();
                C1325k.m8930a().m8934a(false);
            }
        }
    }
}
