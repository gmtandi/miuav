package com.fimi.soul.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.drone.C1433a;

public class UsbStatus extends Service {
    public static final String f9959a = "android.hardware.usb.action.USB_STATE";
    BroadcastReceiver f9960b;
    private DroidPlannerApp f9961c;
    private C1433a f9962d;

    public UsbStatus() {
        this.f9960b = new C1959z(this);
    }

    public void m12163a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(f9959a);
        registerReceiver(this.f9960b, intentFilter);
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.f9961c = (DroidPlannerApp) getApplication();
        this.f9962d = this.f9961c.f5570a;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        m12163a();
        return super.onStartCommand(intent, i, i2);
    }
}
