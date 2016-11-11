package com.fimi.soul.drone.droneconnection.connection.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.service.i */
class C1530i implements ServiceConnection {
    final /* synthetic */ C1522a f7169a;

    C1530i(C1522a c1522a) {
        this.f7169a = c1522a;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f7169a.f7153i = (C1531j) iBinder;
        this.f7169a.m10022j();
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f7169a.m10023k();
    }
}
