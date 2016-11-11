package com.fimi.soul.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.fimi.soul.base.C1236a;

/* renamed from: com.fimi.soul.service.b */
class C1935b implements ServiceConnection {
    final /* synthetic */ C1934a f9969a;

    C1935b(C1934a c1934a) {
        this.f9969a = c1934a;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C1236a.m8532a("onServiceConnected", CameraSocketService.class);
        C1934a.f9963a = ((C1940g) iBinder).m12179a();
        C1934a.f9963a.m12128a(C1934a.f9965e);
        C1934a.f9963a.m12133e();
        if (C1934a.f9965e != null) {
            C1934a.f9965e.m12180a(1, true, "onServiceConnected");
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        C1236a.m8532a("onServiceDisconnected", CameraSocketService.class);
        C1934a.f9963a = null;
    }
}
