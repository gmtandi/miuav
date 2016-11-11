package com.fimi.soul.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.IBinder;
import com.fimi.soul.base.DroidPlannerApp;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.droneconnection.connection.p111a.C1497a;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.receiver.NetworkStateReceiver;
import com.hoho.android.usbserial.driver.UsbId;
import com.hoho.android.usbserial.driver.UsbSerialProber;

public class DroneConnectService extends Service {
    public static final String f9923c = "android.hardware.usb.action.USB_DEVICE_DETACHED";
    public static final String f9924d = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
    public static final String f9925e = "USBHAVECONNECT";
    public static final String f9926f = "TCPSONNECTSUCESS";
    public static final String f9927g = "android.hardware.usb.action.USB_STATE";
    public static final String f9928h = "android.net.conn.TETHER_STATE_CHANGED";
    private static final String f9929l = "com.android.example.USB_PERMISSION";
    public C1433a f9930a;
    public DroidPlannerApp f9931b;
    BroadcastReceiver f9932i;
    private UsbManager f9933j;
    private PendingIntent f9934k;

    public DroneConnectService() {
        this.f9932i = new C1947n(this);
    }

    public void m12136a() {
        if (UsbSerialProber.findFirstDevice(this.f9933j) != null) {
            C1497a.m9894a(C1543c.f7228c);
            if (!this.f9930a.m9569P().m9995a()) {
                this.f9930a.m9569P().m9996b();
            }
        } else if (this.f9933j != null) {
            for (UsbDevice a : this.f9933j.getDeviceList().values()) {
                m12137a(a);
            }
            if (!this.f9930a.m9569P().m9995a()) {
                new C1946m(this).execute(new Void[0]);
            }
        }
    }

    public void m12137a(UsbDevice usbDevice) {
        if (usbDevice.getVendorId() == UsbId.VAN_OOIJEN_TECH_TEENSYDUINO_SERIAL && usbDevice.getProductId() == 22336) {
            if (!this.f9933j.hasPermission(usbDevice)) {
                this.f9934k = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(f9929l), 0);
                this.f9933j.requestPermission(usbDevice, this.f9934k);
            }
            C1497a.m9894a(C1543c.f7228c);
            if (!this.f9930a.m9569P().m9995a()) {
                this.f9930a.m9569P().m9996b();
            }
        }
    }

    public void m12138b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(f9924d);
        intentFilter.addAction(f9923c);
        intentFilter.addAction(f9925e);
        intentFilter.addAction(NetworkStateReceiver.f9876a);
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction(f9927g);
        intentFilter.addAction(f9926f);
        intentFilter.addAction(f9928h);
        intentFilter.addAction(f9929l);
        registerReceiver(this.f9932i, intentFilter);
    }

    public void m12139c() {
        unregisterReceiver(this.f9932i);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.f9931b = (DroidPlannerApp) getApplication();
        this.f9930a = this.f9931b.f5570a;
        this.f9933j = (UsbManager) getSystemService("usb");
        m12138b();
    }

    public void onDestroy() {
        super.onDestroy();
        m12139c();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        m12136a();
        return super.onStartCommand(intent, i, i2);
    }
}
