package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;

abstract class CommonUsbSerialDriver implements UsbSerialDriver {
    public static final int DEFAULT_READ_BUFFER_SIZE = 16384;
    public static final int DEFAULT_WRITE_BUFFER_SIZE = 16384;
    protected final UsbDeviceConnection mConnection;
    protected final UsbDevice mDevice;
    protected byte[] mReadBuffer;
    protected final Object mReadBufferLock;
    protected byte[] mWriteBuffer;
    protected final Object mWriteBufferLock;

    public CommonUsbSerialDriver(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        this.mReadBufferLock = new Object();
        this.mWriteBufferLock = new Object();
        this.mDevice = usbDevice;
        this.mConnection = usbDeviceConnection;
        this.mReadBuffer = new byte[DEFAULT_WRITE_BUFFER_SIZE];
        this.mWriteBuffer = new byte[DEFAULT_WRITE_BUFFER_SIZE];
    }

    public abstract void close();

    public abstract boolean getCD();

    public abstract boolean getCTS();

    public abstract boolean getDSR();

    public abstract boolean getDTR();

    public final UsbDevice getDevice() {
        return this.mDevice;
    }

    public abstract boolean getRI();

    public abstract boolean getRTS();

    public abstract void open();

    public boolean purgeHwBuffers(boolean z, boolean z2) {
        return (z || z2) ? false : true;
    }

    public abstract int read(byte[] bArr, int i);

    public abstract void setDTR(boolean z);

    public abstract void setParameters(int i, int i2, int i3, int i4);

    public abstract void setRTS(boolean z);

    public final void setReadBufferSize(int i) {
        synchronized (this.mReadBufferLock) {
            if (i == this.mReadBuffer.length) {
                return;
            }
            this.mReadBuffer = new byte[i];
        }
    }

    public final void setWriteBufferSize(int i) {
        synchronized (this.mWriteBufferLock) {
            if (i == this.mWriteBuffer.length) {
                return;
            }
            this.mWriteBuffer = new byte[i];
        }
    }

    public abstract int write(byte[] bArr, int i);
}
