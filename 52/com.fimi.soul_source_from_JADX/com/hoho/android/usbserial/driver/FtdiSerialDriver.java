package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.util.Log;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

public class FtdiSerialDriver extends CommonUsbSerialDriver {
    private static final boolean ENABLE_ASYNC_READS = false;
    public static final int FTDI_DEVICE_IN_REQTYPE = 192;
    public static final int FTDI_DEVICE_OUT_REQTYPE = 64;
    private static final int MODEM_STATUS_HEADER_LENGTH = 2;
    private static final int SIO_MODEM_CTRL_REQUEST = 1;
    private static final int SIO_RESET_PURGE_RX = 1;
    private static final int SIO_RESET_PURGE_TX = 2;
    private static final int SIO_RESET_REQUEST = 0;
    private static final int SIO_RESET_SIO = 0;
    private static final int SIO_SET_BAUD_RATE_REQUEST = 3;
    private static final int SIO_SET_DATA_REQUEST = 4;
    private static final int SIO_SET_FLOW_CTRL_REQUEST = 2;
    public static final int USB_ENDPOINT_IN = 128;
    public static final int USB_ENDPOINT_OUT = 0;
    public static final int USB_READ_TIMEOUT_MILLIS = 5000;
    public static final int USB_RECIP_DEVICE = 0;
    public static final int USB_RECIP_ENDPOINT = 2;
    public static final int USB_RECIP_INTERFACE = 1;
    public static final int USB_RECIP_OTHER = 3;
    public static final int USB_TYPE_CLASS = 0;
    public static final int USB_TYPE_RESERVED = 0;
    public static final int USB_TYPE_STANDARD = 0;
    public static final int USB_TYPE_VENDOR = 0;
    public static final int USB_WRITE_TIMEOUT_MILLIS = 5000;
    private final String TAG;
    private int mInterface;
    private int mMaxPacketSize;
    private DeviceType mType;

    enum DeviceType {
        TYPE_BM,
        TYPE_AM,
        TYPE_2232C,
        TYPE_R,
        TYPE_2232H,
        TYPE_4232H
    }

    public FtdiSerialDriver(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        super(usbDevice, usbDeviceConnection);
        this.TAG = FtdiSerialDriver.class.getSimpleName();
        this.mInterface = USB_TYPE_VENDOR;
        this.mMaxPacketSize = FTDI_DEVICE_OUT_REQTYPE;
        this.mType = null;
    }

    private long[] convertBaudrate(int i) {
        int i2;
        int i3;
        int i4 = 24000000 / i;
        int i5 = USB_TYPE_VENDOR;
        int i6 = USB_TYPE_VENDOR;
        int i7 = USB_TYPE_VENDOR;
        int[] iArr = new int[8];
        iArr[USB_RECIP_INTERFACE] = USB_RECIP_OTHER;
        iArr[USB_RECIP_ENDPOINT] = USB_RECIP_ENDPOINT;
        iArr[USB_RECIP_OTHER] = SIO_SET_DATA_REQUEST;
        iArr[SIO_SET_DATA_REQUEST] = USB_RECIP_INTERFACE;
        iArr[5] = 5;
        iArr[6] = 6;
        iArr[7] = 7;
        int i8 = USB_TYPE_VENDOR;
        while (i8 < USB_RECIP_ENDPOINT) {
            i2 = i4 + i8;
            if (i2 <= 8) {
                i2 = 8;
            } else if (this.mType != DeviceType.TYPE_AM && i2 < 12) {
                i2 = 12;
            } else if (i4 < 16) {
                i2 = 16;
            } else if (this.mType != DeviceType.TYPE_AM && i2 > 131071) {
                i2 = 131071;
            }
            i3 = (24000000 + (i2 / USB_RECIP_ENDPOINT)) / i2;
            int i9 = i3 < i ? i - i3 : i3 - i;
            if (i8 == 0 || i9 < i7) {
                if (i9 == 0) {
                    break;
                }
                i7 = i3;
                int i10 = i9;
                i9 = i2;
                i2 = i10;
            } else {
                i2 = i7;
                i9 = i5;
                i7 = i6;
            }
            i8 += USB_RECIP_INTERFACE;
            i5 = i9;
            i6 = i7;
            i7 = i2;
        }
        i3 = i6;
        i2 = i5;
        long j = (long) ((iArr[i2 & 7] << 14) | (i2 >> USB_RECIP_OTHER));
        if (j == 1) {
            j = 0;
        } else if (j == 16385) {
            j = 1;
        }
        long j2 = 65535 & j;
        j = (this.mType == DeviceType.TYPE_2232C || this.mType == DeviceType.TYPE_2232H || this.mType == DeviceType.TYPE_4232H) ? (((j >> 8) & 65535) & 65280) | 0 : (j >> 16) & 65535;
        long[] jArr = new long[USB_RECIP_OTHER];
        jArr[USB_TYPE_VENDOR] = (long) i3;
        jArr[USB_RECIP_INTERFACE] = j;
        jArr[USB_RECIP_ENDPOINT] = j2;
        return jArr;
    }

    private final int filterStatusBytes(byte[] bArr, byte[] bArr2, int i, int i2) {
        int i3 = (i / i2) + USB_RECIP_INTERFACE;
        int i4 = USB_TYPE_VENDOR;
        while (i4 < i3) {
            int i5 = i4 == i3 + -1 ? (i % i2) - 2 : i2 - 2;
            if (i5 > 0) {
                System.arraycopy(bArr, (i4 * i2) + USB_RECIP_ENDPOINT, bArr2, (i2 - 2) * i4, i5);
            }
            i4 += USB_RECIP_INTERFACE;
        }
        return i - (i3 * USB_RECIP_ENDPOINT);
    }

    public static Map<Integer, int[]> getSupportedDevices() {
        Map<Integer, int[]> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Integer.valueOf(UsbId.VENDOR_FTDI), new int[]{UsbId.FTDI_FT232R, UsbId.FTDI_FT231X});
        return linkedHashMap;
    }

    private int setBaudRate(int i) {
        long[] convertBaudrate = convertBaudrate(i);
        long j = convertBaudrate[USB_TYPE_VENDOR];
        long j2 = convertBaudrate[USB_RECIP_INTERFACE];
        int controlTransfer = this.mConnection.controlTransfer(FTDI_DEVICE_OUT_REQTYPE, USB_RECIP_OTHER, (int) convertBaudrate[USB_RECIP_ENDPOINT], (int) j2, null, USB_TYPE_VENDOR, USB_WRITE_TIMEOUT_MILLIS);
        if (controlTransfer == 0) {
            return (int) j;
        }
        throw new IOException("Setting baudrate failed: result=" + controlTransfer);
    }

    public void close() {
        this.mConnection.close();
    }

    public boolean getCD() {
        return ENABLE_ASYNC_READS;
    }

    public boolean getCTS() {
        return ENABLE_ASYNC_READS;
    }

    public boolean getDSR() {
        return ENABLE_ASYNC_READS;
    }

    public boolean getDTR() {
        return ENABLE_ASYNC_READS;
    }

    public boolean getRI() {
        return ENABLE_ASYNC_READS;
    }

    public boolean getRTS() {
        return ENABLE_ASYNC_READS;
    }

    public void open() {
        int i = USB_TYPE_VENDOR;
        while (i < this.mDevice.getInterfaceCount()) {
            try {
                if (this.mConnection.claimInterface(this.mDevice.getInterface(i), true)) {
                    Log.d(this.TAG, "claimInterface " + i + " SUCCESS");
                    i += USB_RECIP_INTERFACE;
                } else {
                    throw new IOException("Error claiming interface " + i);
                }
            } catch (Throwable th) {
                close();
            }
        }
        reset();
    }

    public boolean purgeHwBuffers(boolean z, boolean z2) {
        int controlTransfer;
        if (z) {
            controlTransfer = this.mConnection.controlTransfer(FTDI_DEVICE_OUT_REQTYPE, USB_TYPE_VENDOR, USB_RECIP_INTERFACE, USB_TYPE_VENDOR, null, USB_TYPE_VENDOR, USB_WRITE_TIMEOUT_MILLIS);
            if (controlTransfer != 0) {
                throw new IOException("Flushing RX failed: result=" + controlTransfer);
            }
        }
        if (z2) {
            controlTransfer = this.mConnection.controlTransfer(FTDI_DEVICE_OUT_REQTYPE, USB_TYPE_VENDOR, USB_RECIP_ENDPOINT, USB_TYPE_VENDOR, null, USB_TYPE_VENDOR, USB_WRITE_TIMEOUT_MILLIS);
            if (controlTransfer != 0) {
                throw new IOException("Flushing RX failed: result=" + controlTransfer);
            }
        }
        return true;
    }

    public int read(byte[] bArr, int i) {
        int filterStatusBytes;
        UsbEndpoint endpoint = this.mDevice.getInterface(USB_TYPE_VENDOR).getEndpoint(USB_TYPE_VENDOR);
        synchronized (this.mReadBufferLock) {
            int bulkTransfer = this.mConnection.bulkTransfer(endpoint, this.mReadBuffer, Math.min(bArr.length, this.mReadBuffer.length), i);
            if (bulkTransfer < USB_RECIP_ENDPOINT) {
                throw new IOException("Expected at least 2 bytes");
            }
            filterStatusBytes = filterStatusBytes(this.mReadBuffer, bArr, bulkTransfer, endpoint.getMaxPacketSize());
        }
        return filterStatusBytes;
    }

    public void reset() {
        int controlTransfer = this.mConnection.controlTransfer(FTDI_DEVICE_OUT_REQTYPE, USB_TYPE_VENDOR, USB_TYPE_VENDOR, USB_TYPE_VENDOR, null, USB_TYPE_VENDOR, USB_WRITE_TIMEOUT_MILLIS);
        if (controlTransfer != 0) {
            throw new IOException("Reset failed: result=" + controlTransfer);
        }
        this.mType = DeviceType.TYPE_R;
    }

    public void setDTR(boolean z) {
    }

    public void setParameters(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        setBaudRate(i);
        switch (i4) {
            case USB_TYPE_VENDOR /*0*/:
                i5 = i2 | USB_TYPE_VENDOR;
                break;
            case USB_RECIP_INTERFACE /*1*/:
                i5 = i2 | Opcodes.ACC_NATIVE;
                break;
            case USB_RECIP_ENDPOINT /*2*/:
                i5 = i2 | Opcodes.ACC_INTERFACE;
                break;
            case USB_RECIP_OTHER /*3*/:
                i5 = i2 | 768;
                break;
            case SIO_SET_DATA_REQUEST /*4*/:
                i5 = i2 | SmileConstants.MAX_SHARED_STRING_VALUES;
                break;
            default:
                throw new IllegalArgumentException("Unknown parity value: " + i4);
        }
        switch (i3) {
            case USB_RECIP_INTERFACE /*1*/:
                i6 = i5 | USB_TYPE_VENDOR;
                break;
            case USB_RECIP_ENDPOINT /*2*/:
                i6 = i5 | Opcodes.ACC_SYNTHETIC;
                break;
            case USB_RECIP_OTHER /*3*/:
                i6 = i5 | Opcodes.ACC_STRICT;
                break;
            default:
                throw new IllegalArgumentException("Unknown stopBits value: " + i3);
        }
        i5 = this.mConnection.controlTransfer(FTDI_DEVICE_OUT_REQTYPE, SIO_SET_DATA_REQUEST, i6, USB_TYPE_VENDOR, null, USB_TYPE_VENDOR, USB_WRITE_TIMEOUT_MILLIS);
        if (i5 != 0) {
            throw new IOException("Setting parameters failed: result=" + i5);
        }
    }

    public void setRTS(boolean z) {
    }

    public int write(byte[] bArr, int i) {
        int i2 = USB_TYPE_VENDOR;
        UsbEndpoint endpoint = this.mDevice.getInterface(USB_TYPE_VENDOR).getEndpoint(USB_RECIP_INTERFACE);
        while (i2 < bArr.length) {
            synchronized (this.mWriteBufferLock) {
                byte[] bArr2;
                int min = Math.min(bArr.length - i2, this.mWriteBuffer.length);
                if (i2 == 0) {
                    bArr2 = bArr;
                } else {
                    System.arraycopy(bArr, i2, this.mWriteBuffer, USB_TYPE_VENDOR, min);
                    bArr2 = this.mWriteBuffer;
                }
                int bulkTransfer = this.mConnection.bulkTransfer(endpoint, bArr2, min, i);
            }
            if (bulkTransfer <= 0) {
                throw new IOException("Error writing " + min + " bytes at offset " + i2 + " length=" + bArr.length);
            }
            Log.d(this.TAG, "Wrote amtWritten=" + bulkTransfer + " attempted=" + min);
            i2 += bulkTransfer;
        }
        return i2;
    }
}
