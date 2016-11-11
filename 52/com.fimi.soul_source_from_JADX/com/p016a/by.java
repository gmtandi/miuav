package com.p016a;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* renamed from: com.a.by */
class by implements ServiceConnection {
    final /* synthetic */ ca f721a;
    final /* synthetic */ bx f722b;

    by(bx bxVar, ca caVar) {
        this.f722b = bxVar;
        this.f721a = caVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f722b.f708e = fk.m1830a(iBinder);
        this.f721a.m1267a(0);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f722b.f708e = null;
    }
}
