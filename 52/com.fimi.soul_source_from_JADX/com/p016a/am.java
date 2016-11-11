package com.p016a;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Messenger;

/* renamed from: com.a.am */
class am implements ServiceConnection {
    final /* synthetic */ C0238a f554a;

    am(C0238a c0238a) {
        this.f554a = c0238a;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f554a.f488h = new Messenger(iBinder);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f554a.f488h = null;
    }
}
