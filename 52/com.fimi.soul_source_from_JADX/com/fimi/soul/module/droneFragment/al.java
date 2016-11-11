package com.fimi.soul.module.droneFragment;

import com.fimi.soul.biz.p103k.as;
import com.xiaomi.mipush.sdk.MiPushClient;

class al implements as {
    final /* synthetic */ ShowDroneStatusFragment f8180a;

    al(ShowDroneStatusFragment showDroneStatusFragment) {
        this.f8180a = showDroneStatusFragment;
    }

    public void m11019a(String str, String str2) {
        this.f8180a.f8019A.setText(str2 + MiPushClient.ACCEPT_TIME_SEPARATOR + str);
    }
}
