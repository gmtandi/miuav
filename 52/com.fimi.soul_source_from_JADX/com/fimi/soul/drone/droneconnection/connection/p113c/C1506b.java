package com.fimi.soul.drone.droneconnection.connection.p113c;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;
import com.fimi.soul.drone.droneconnection.connection.C1499a;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.c.b */
public class C1506b extends C1499a {
    private static final String f7114i;
    private static final int f7115j = 1027;
    protected int f7116h;
    private C1504c f7117k;

    static {
        f7114i = C1506b.class.getSimpleName();
    }

    public C1506b(Context context) {
        super(context);
        this.f7116h = 256000;
    }

    private static boolean m9961a(Context context) {
        HashMap deviceList = ((UsbManager) context.getSystemService("usb")).getDeviceList();
        if (deviceList == null || deviceList.isEmpty()) {
            return false;
        }
        for (Entry value : deviceList.entrySet()) {
            if (((UsbDevice) value.getValue()).getVendorId() == f7115j) {
                return true;
            }
        }
        return false;
    }

    protected int m9962a(byte[] bArr) {
        if (this.f7117k != null) {
            return this.f7117k.m9952a(bArr);
        }
        throw new IOException("Uninitialized usb connection.");
    }

    protected void m9963a() {
        if (this.f7117k != null) {
            this.f7117k.m9954b();
        }
    }

    protected void m9964b() {
        if (this.f7117k != null) {
            try {
                this.f7117k.m9953a();
                Log.d(f7114i, "Reusing previous usb connection.");
                return;
            } catch (Throwable e) {
                Log.e(f7114i, "Previous usb connection is not usable.", e);
                this.f7117k = null;
            }
        }
        if (this.f7117k == null) {
            C1504c c1505a = new C1505a(this.a, this.f7116h);
            c1505a.m9953a();
            this.f7117k = c1505a;
            Log.d(f7114i, "Using open-source usb connection.");
        }
    }

    protected void m9965c(byte[] bArr) {
        if (this.f7117k == null) {
            throw new IOException("Uninitialized usb connection.");
        }
        this.f7117k.m9955b(bArr);
    }

    public int m9966j() {
        return 1;
    }

    public String toString() {
        return this.f7117k == null ? f7114i : this.f7117k.toString();
    }
}
