package com.fimi.soul.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.fimi.soul.receiver.NetworkStateReceiver;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.service.d */
class C1937d extends BroadcastReceiver {
    final /* synthetic */ CameraSocketService f9974a;

    C1937d(CameraSocketService cameraSocketService) {
        this.f9974a = cameraSocketService;
    }

    public void onReceive(Context context, Intent intent) {
        if (NetworkStateReceiver.f9876a.equals(intent.getAction()) && be.m12370b(context)) {
            this.f9974a.f9917v = false;
        }
    }
}
