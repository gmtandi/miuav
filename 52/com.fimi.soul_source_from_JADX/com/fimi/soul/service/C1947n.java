package com.fimi.soul.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import com.fimi.soul.drone.droneconnection.connection.p111a.C1497a;
import com.fimi.soul.drone.p116g.C1543c;

/* renamed from: com.fimi.soul.service.n */
class C1947n extends BroadcastReceiver {
    final /* synthetic */ DroneConnectService f9986a;

    C1947n(DroneConnectService droneConnectService) {
        this.f9986a = droneConnectService;
    }

    public void onReceive(Context context, Intent intent) {
        if ("com.android.example.USB_PERMISSION".equals(intent.getAction())) {
            synchronized (this) {
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if (intent.getBooleanExtra("permission", false) && usbDevice != null) {
                    this.f9986a.m12137a(usbDevice);
                }
            }
        }
        if (DroneConnectService.f9924d.equals(intent.getAction()) && !this.f9986a.f9930a.m9569P().m9995a()) {
            C1497a.m9894a(C1543c.f7228c);
            this.f9986a.f9930a.m9569P().m9996b();
        } else if (DroneConnectService.f9923c.equals(intent.getAction()) && this.f9986a.f9930a.m9569P().m9995a()) {
            this.f9986a.f9930a.m9569P().m9999d();
        } else if (!DroneConnectService.f9928h.equals(intent.getAction()) || !C1543c.f7228c.equals(this.f9986a.f9930a.m9569P().m10000e())) {
            new C1948o(this).execute(new Void[0]);
        }
    }
}
