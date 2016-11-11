package com.fimi.soul.drone.droneconnection.connection.p113c;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import com.hoho.android.usbserial.driver.CdcAcmSerialDriver;
import com.hoho.android.usbserial.driver.UsbId;
import com.hoho.android.usbserial.driver.UsbSerialDriver;
import com.hoho.android.usbserial.driver.UsbSerialProber;
import it.p074a.p075a.C2799f;
import java.io.IOException;
import java.lang.reflect.Array;

/* renamed from: com.fimi.soul.drone.droneconnection.connection.c.a */
class C1505a extends C1504c {
    private static final String f7107g;
    private static UsbSerialDriver f7108h;
    protected Context f7109a;
    UsbInterface[] f7110b;
    UsbEndpoint[][] f7111c;
    UsbDeviceConnection f7112d;
    private UsbDevice f7113i;

    static {
        f7107g = C1505a.class.getSimpleName();
        f7108h = null;
    }

    protected C1505a(Context context, int i) {
        super(context, i);
        this.f7113i = null;
        this.f7110b = null;
        this.f7111c = (UsbEndpoint[][]) Array.newInstance(UsbEndpoint.class, new int[]{20, 20});
        this.f7109a = context;
    }

    protected int m9956a(byte[] bArr) {
        try {
            int read = f7108h.read(bArr, C2799f.f14282t);
            return read == 0 ? -1 : read;
        } catch (Throwable e) {
            String str = "Error Reading: " + e.getMessage() + "\nAssuming inaccessible USB device.  Closing connection.";
            Log.e(f7107g, str, e);
            throw new IOException(str, e);
        }
    }

    protected void m9957a() {
        UsbManager usbManager = (UsbManager) this.f.getSystemService("usb");
        f7108h = UsbSerialProber.findFirstDevice(usbManager);
        if (f7108h == null) {
            if (usbManager != null) {
                for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
                    this.f7113i = usbDevice;
                    if (this.f7113i.getVendorId() == UsbId.VAN_OOIJEN_TECH_TEENSYDUINO_SERIAL && this.f7113i.getProductId() == 22336) {
                        break;
                    }
                }
            }
            if (this.f7113i != null && this.f7113i.getVendorId() == UsbId.VAN_OOIJEN_TECH_TEENSYDUINO_SERIAL && this.f7113i.getProductId() == 22336) {
                this.f7110b = new UsbInterface[this.f7113i.getInterfaceCount()];
                for (int i = 0; i < this.f7113i.getInterfaceCount(); i++) {
                    this.f7110b[i] = this.f7113i.getInterface(i);
                    for (int i2 = 0; i2 < this.f7110b[i].getEndpointCount(); i2++) {
                        this.f7111c[i][i2] = this.f7110b[i].getEndpoint(i2);
                    }
                }
                this.f7112d = usbManager.openDevice(this.f7113i);
            }
            if (this.f7112d != null) {
                f7108h = new CdcAcmSerialDriver(this.f7113i, this.f7112d);
            }
            if (f7108h != null) {
                m9958a(f7108h);
                return;
            }
            return;
        }
        m9958a(f7108h);
    }

    public void m9958a(UsbSerialDriver usbSerialDriver) {
        try {
            usbSerialDriver.open();
            usbSerialDriver.setParameters(this.e, 8, 1, 0);
        } catch (IOException e) {
            if (usbSerialDriver != null) {
                usbSerialDriver.close();
            }
            throw new IOException();
        }
    }

    protected void m9959b() {
        if (f7108h != null) {
            try {
                f7108h.close();
            } catch (IOException e) {
            }
            f7108h = null;
        }
    }

    protected void m9960b(byte[] bArr) {
        if (f7108h != null) {
            try {
                f7108h.write(bArr, C2799f.f14263a);
            } catch (Throwable e) {
                Log.e("USB", "Error Sending: " + e.getMessage(), e);
            }
        }
    }

    public String toString() {
        return f7107g;
    }
}
