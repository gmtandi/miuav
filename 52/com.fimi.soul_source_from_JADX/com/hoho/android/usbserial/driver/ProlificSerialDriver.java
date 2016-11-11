package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.util.Log;
import com.tencent.mm.sdk.platformtools.Util;
import it.p074a.p075a.C2799f;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class ProlificSerialDriver extends CommonUsbSerialDriver {
    private static final int CONTROL_DTR = 1;
    private static final int CONTROL_RTS = 2;
    private static final int DEVICE_TYPE_0 = 1;
    private static final int DEVICE_TYPE_1 = 2;
    private static final int DEVICE_TYPE_HX = 0;
    private static final int FLUSH_RX_REQUEST = 8;
    private static final int FLUSH_TX_REQUEST = 9;
    private static final int INTERRUPT_ENDPOINT = 129;
    private static final int PROLIFIC_CTRL_OUT_REQTYPE = 33;
    private static final int PROLIFIC_VENDOR_IN_REQTYPE = 192;
    private static final int PROLIFIC_VENDOR_OUT_REQTYPE = 64;
    private static final int PROLIFIC_VENDOR_READ_REQUEST = 1;
    private static final int PROLIFIC_VENDOR_WRITE_REQUEST = 1;
    private static final int READ_ENDPOINT = 131;
    private static final int SET_CONTROL_REQUEST = 34;
    private static final int SET_LINE_REQUEST = 32;
    private static final int STATUS_BUFFER_SIZE = 10;
    private static final int STATUS_BYTE_IDX = 8;
    private static final int STATUS_FLAG_CD = 1;
    private static final int STATUS_FLAG_CTS = 128;
    private static final int STATUS_FLAG_DSR = 2;
    private static final int STATUS_FLAG_RI = 8;
    private static final int USB_READ_TIMEOUT_MILLIS = 1000;
    private static final int USB_RECIP_INTERFACE = 1;
    private static final int USB_WRITE_TIMEOUT_MILLIS = 5000;
    private static final int WRITE_ENDPOINT = 2;
    private final String TAG;
    private int mBaudRate;
    private int mControlLinesValue;
    private int mDataBits;
    private int mDeviceType;
    private UsbEndpoint mInterruptEndpoint;
    private int mParity;
    private UsbEndpoint mReadEndpoint;
    private IOException mReadStatusException;
    private volatile Thread mReadStatusThread;
    private final Object mReadStatusThreadLock;
    private int mStatus;
    private int mStopBits;
    boolean mStopReadStatusThread;
    private UsbEndpoint mWriteEndpoint;

    /* renamed from: com.hoho.android.usbserial.driver.ProlificSerialDriver.1 */
    class C21051 implements Runnable {
        C21051() {
        }

        public void run() {
            ProlificSerialDriver.this.readStatusThreadFunction();
        }
    }

    public ProlificSerialDriver(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        super(usbDevice, usbDeviceConnection);
        this.mDeviceType = DEVICE_TYPE_HX;
        this.mControlLinesValue = DEVICE_TYPE_HX;
        this.mBaudRate = -1;
        this.mDataBits = -1;
        this.mStopBits = -1;
        this.mParity = -1;
        this.mStatus = DEVICE_TYPE_HX;
        this.mReadStatusThread = null;
        this.mReadStatusThreadLock = new Object();
        this.mStopReadStatusThread = false;
        this.mReadStatusException = null;
        this.TAG = ProlificSerialDriver.class.getSimpleName();
    }

    private final void ctrlOut(int i, int i2, int i3, byte[] bArr) {
        outControlTransfer(PROLIFIC_CTRL_OUT_REQTYPE, i, i2, i3, bArr);
    }

    private void doBlackMagic() {
        vendorIn(33924, DEVICE_TYPE_HX, USB_RECIP_INTERFACE);
        vendorOut(1028, DEVICE_TYPE_HX, null);
        vendorIn(33924, DEVICE_TYPE_HX, USB_RECIP_INTERFACE);
        vendorIn(33667, DEVICE_TYPE_HX, USB_RECIP_INTERFACE);
        vendorIn(33924, DEVICE_TYPE_HX, USB_RECIP_INTERFACE);
        vendorOut(1028, USB_RECIP_INTERFACE, null);
        vendorIn(33924, DEVICE_TYPE_HX, USB_RECIP_INTERFACE);
        vendorIn(33667, DEVICE_TYPE_HX, USB_RECIP_INTERFACE);
        vendorOut(DEVICE_TYPE_HX, USB_RECIP_INTERFACE, null);
        vendorOut(USB_RECIP_INTERFACE, DEVICE_TYPE_HX, null);
        vendorOut(WRITE_ENDPOINT, this.mDeviceType == 0 ? 68 : 36, null);
    }

    private final int getStatus() {
        if (this.mReadStatusThread == null && this.mReadStatusException == null) {
            synchronized (this.mReadStatusThreadLock) {
                if (this.mReadStatusThread == null) {
                    byte[] bArr = new byte[STATUS_BUFFER_SIZE];
                    if (this.mConnection.bulkTransfer(this.mInterruptEndpoint, bArr, STATUS_BUFFER_SIZE, 100) != STATUS_BUFFER_SIZE) {
                        Log.w(this.TAG, "Could not read initial CTS / DSR / CD / RI status");
                    } else {
                        this.mStatus = bArr[STATUS_FLAG_RI] & Util.MASK_8BIT;
                    }
                    this.mReadStatusThread = new Thread(new C21051());
                    this.mReadStatusThread.setDaemon(true);
                    this.mReadStatusThread.start();
                }
            }
        }
        IOException iOException = this.mReadStatusException;
        if (this.mReadStatusException == null) {
            return this.mStatus;
        }
        this.mReadStatusException = null;
        throw iOException;
    }

    public static Map<Integer, int[]> getSupportedDevices() {
        Map<Integer, int[]> linkedHashMap = new LinkedHashMap();
        Integer valueOf = Integer.valueOf(UsbId.VENDOR_PROLIFIC);
        Object obj = new int[USB_RECIP_INTERFACE];
        obj[DEVICE_TYPE_HX] = 8963;
        linkedHashMap.put(valueOf, obj);
        return linkedHashMap;
    }

    private final byte[] inControlTransfer(int i, int i2, int i3, int i4, int i5) {
        byte[] bArr = new byte[i5];
        int controlTransfer = this.mConnection.controlTransfer(i, i2, i3, i4, bArr, i5, USB_READ_TIMEOUT_MILLIS);
        if (controlTransfer == i5) {
            return bArr;
        }
        Object[] objArr = new Object[WRITE_ENDPOINT];
        objArr[DEVICE_TYPE_HX] = Integer.valueOf(i3);
        objArr[USB_RECIP_INTERFACE] = Integer.valueOf(controlTransfer);
        throw new IOException(String.format("ControlTransfer with value 0x%x failed: %d", objArr));
    }

    private final void outControlTransfer(int i, int i2, int i3, int i4, byte[] bArr) {
        int length = bArr == null ? DEVICE_TYPE_HX : bArr.length;
        int controlTransfer = this.mConnection.controlTransfer(i, i2, i3, i4, bArr, length, USB_WRITE_TIMEOUT_MILLIS);
        if (controlTransfer != length) {
            Object[] objArr = new Object[WRITE_ENDPOINT];
            objArr[DEVICE_TYPE_HX] = Integer.valueOf(i3);
            objArr[USB_RECIP_INTERFACE] = Integer.valueOf(controlTransfer);
            throw new IOException(String.format("ControlTransfer with value 0x%x failed: %d", objArr));
        }
    }

    private final void readStatusThreadFunction() {
        while (!this.mStopReadStatusThread) {
            try {
                byte[] bArr = new byte[STATUS_BUFFER_SIZE];
                int bulkTransfer = this.mConnection.bulkTransfer(this.mInterruptEndpoint, bArr, STATUS_BUFFER_SIZE, C2799f.f14263a);
                if (bulkTransfer > 0) {
                    if (bulkTransfer == STATUS_BUFFER_SIZE) {
                        this.mStatus = bArr[STATUS_FLAG_RI] & Util.MASK_8BIT;
                    } else {
                        Object[] objArr = new Object[WRITE_ENDPOINT];
                        objArr[DEVICE_TYPE_HX] = Integer.valueOf(STATUS_BUFFER_SIZE);
                        objArr[USB_RECIP_INTERFACE] = Integer.valueOf(bulkTransfer);
                        throw new IOException(String.format("Invalid CTS / DSR / CD / RI status buffer received, expected %d bytes, but received %d", objArr));
                    }
                }
            } catch (IOException e) {
                this.mReadStatusException = e;
                return;
            }
        }
    }

    private void resetDevice() {
        purgeHwBuffers(true, true);
    }

    private void setControlLines(int i) {
        ctrlOut(SET_CONTROL_REQUEST, i, DEVICE_TYPE_HX, null);
        this.mControlLinesValue = i;
    }

    private final boolean testStatusFlag(int i) {
        return (getStatus() & i) == i;
    }

    private final byte[] vendorIn(int i, int i2, int i3) {
        return inControlTransfer(PROLIFIC_VENDOR_IN_REQTYPE, USB_RECIP_INTERFACE, i, i2, i3);
    }

    private final void vendorOut(int i, int i2, byte[] bArr) {
        outControlTransfer(PROLIFIC_VENDOR_OUT_REQTYPE, USB_RECIP_INTERFACE, i, i2, bArr);
    }

    public void close() {
        try {
            this.mStopReadStatusThread = true;
            synchronized (this.mReadStatusThreadLock) {
                if (this.mReadStatusThread != null) {
                    try {
                        this.mReadStatusThread.join();
                    } catch (Throwable e) {
                        Log.w(this.TAG, "An error occured while waiting for status read thread", e);
                    }
                }
            }
            resetDevice();
        } finally {
            this.mConnection.releaseInterface(this.mDevice.getInterface(DEVICE_TYPE_HX));
        }
    }

    public boolean getCD() {
        return testStatusFlag(USB_RECIP_INTERFACE);
    }

    public boolean getCTS() {
        return testStatusFlag(STATUS_FLAG_CTS);
    }

    public boolean getDSR() {
        return testStatusFlag(WRITE_ENDPOINT);
    }

    public boolean getDTR() {
        return (this.mControlLinesValue & USB_RECIP_INTERFACE) == USB_RECIP_INTERFACE;
    }

    public boolean getRI() {
        return testStatusFlag(STATUS_FLAG_RI);
    }

    public boolean getRTS() {
        return (this.mControlLinesValue & WRITE_ENDPOINT) == WRITE_ENDPOINT;
    }

    public void open() {
        int i = DEVICE_TYPE_HX;
        UsbInterface usbInterface = this.mDevice.getInterface(DEVICE_TYPE_HX);
        if (this.mConnection.claimInterface(usbInterface, true)) {
            while (i < usbInterface.getEndpointCount()) {
                try {
                    UsbEndpoint endpoint = usbInterface.getEndpoint(i);
                    switch (endpoint.getAddress()) {
                        case WRITE_ENDPOINT /*2*/:
                            this.mWriteEndpoint = endpoint;
                            break;
                        case INTERRUPT_ENDPOINT /*129*/:
                            this.mInterruptEndpoint = endpoint;
                            break;
                        case READ_ENDPOINT /*131*/:
                            this.mReadEndpoint = endpoint;
                            break;
                        default:
                            break;
                    }
                    i += USB_RECIP_INTERFACE;
                } catch (NoSuchMethodException e) {
                    Log.w(this.TAG, "Method UsbDeviceConnection.getRawDescriptors, required for PL2303 subtype detection, not available! Assuming that it is a HX device");
                    this.mDeviceType = DEVICE_TYPE_HX;
                } catch (Throwable e2) {
                    Log.e(this.TAG, "An unexpected exception occured while trying to detect PL2303 subtype", e2);
                } catch (Throwable th) {
                    try {
                        this.mConnection.releaseInterface(usbInterface);
                    } catch (Exception e3) {
                    }
                }
            }
            if (this.mDevice.getDeviceClass() == WRITE_ENDPOINT) {
                this.mDeviceType = USB_RECIP_INTERFACE;
            } else if (((byte[]) this.mConnection.getClass().getMethod("getRawDescriptors", new Class[DEVICE_TYPE_HX]).invoke(this.mConnection, new Object[DEVICE_TYPE_HX]))[7] == PROLIFIC_VENDOR_OUT_REQTYPE) {
                this.mDeviceType = DEVICE_TYPE_HX;
            } else if (this.mDevice.getDeviceClass() == 0 || this.mDevice.getDeviceClass() == Util.MASK_8BIT) {
                this.mDeviceType = WRITE_ENDPOINT;
            } else {
                Log.w(this.TAG, "Could not detect PL2303 subtype, Assuming that it is a HX device");
                this.mDeviceType = DEVICE_TYPE_HX;
            }
            setControlLines(this.mControlLinesValue);
            resetDevice();
            doBlackMagic();
            return;
        }
        throw new IOException("Error claiming Prolific interface 0");
    }

    public boolean purgeHwBuffers(boolean z, boolean z2) {
        if (z) {
            vendorOut(STATUS_FLAG_RI, DEVICE_TYPE_HX, null);
        }
        if (z2) {
            vendorOut(FLUSH_TX_REQUEST, DEVICE_TYPE_HX, null);
        }
        return true;
    }

    public int read(byte[] bArr, int i) {
        synchronized (this.mReadBufferLock) {
            int bulkTransfer = this.mConnection.bulkTransfer(this.mReadEndpoint, this.mReadBuffer, Math.min(bArr.length, this.mReadBuffer.length), i);
            if (bulkTransfer < 0) {
                return DEVICE_TYPE_HX;
            }
            System.arraycopy(this.mReadBuffer, DEVICE_TYPE_HX, bArr, DEVICE_TYPE_HX, bulkTransfer);
            return bulkTransfer;
        }
    }

    public void setDTR(boolean z) {
        setControlLines(z ? this.mControlLinesValue | USB_RECIP_INTERFACE : this.mControlLinesValue & -2);
    }

    public void setParameters(int i, int i2, int i3, int i4) {
        if (this.mBaudRate != i || this.mDataBits != i2 || this.mStopBits != i3 || this.mParity != i4) {
            byte[] bArr = new byte[7];
            bArr[DEVICE_TYPE_HX] = (byte) (i & Util.MASK_8BIT);
            bArr[USB_RECIP_INTERFACE] = (byte) ((i >> STATUS_FLAG_RI) & Util.MASK_8BIT);
            bArr[WRITE_ENDPOINT] = (byte) ((i >> 16) & Util.MASK_8BIT);
            bArr[3] = (byte) ((i >> 24) & Util.MASK_8BIT);
            switch (i3) {
                case USB_RECIP_INTERFACE /*1*/:
                    bArr[4] = (byte) 0;
                    break;
                case WRITE_ENDPOINT /*2*/:
                    bArr[4] = (byte) 2;
                    break;
                case Type.BYTE /*3*/:
                    bArr[4] = (byte) 1;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown stopBits value: " + i3);
            }
            switch (i4) {
                case DEVICE_TYPE_HX /*0*/:
                    bArr[5] = (byte) 0;
                    break;
                case USB_RECIP_INTERFACE /*1*/:
                    bArr[5] = (byte) 1;
                    break;
                case WRITE_ENDPOINT /*2*/:
                    bArr[5] = (byte) 2;
                    break;
                case Type.BYTE /*3*/:
                    bArr[5] = (byte) 3;
                    break;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    bArr[5] = (byte) 4;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown parity value: " + i4);
            }
            bArr[6] = (byte) i2;
            ctrlOut(SET_LINE_REQUEST, DEVICE_TYPE_HX, DEVICE_TYPE_HX, bArr);
            resetDevice();
            this.mBaudRate = i;
            this.mDataBits = i2;
            this.mStopBits = i3;
            this.mParity = i4;
        }
    }

    public void setRTS(boolean z) {
        setControlLines(z ? this.mControlLinesValue | WRITE_ENDPOINT : this.mControlLinesValue & -3);
    }

    public int write(byte[] bArr, int i) {
        int i2 = DEVICE_TYPE_HX;
        while (i2 < bArr.length) {
            synchronized (this.mWriteBufferLock) {
                byte[] bArr2;
                int min = Math.min(bArr.length - i2, this.mWriteBuffer.length);
                if (i2 == 0) {
                    bArr2 = bArr;
                } else {
                    System.arraycopy(bArr, i2, this.mWriteBuffer, DEVICE_TYPE_HX, min);
                    bArr2 = this.mWriteBuffer;
                }
                int bulkTransfer = this.mConnection.bulkTransfer(this.mWriteEndpoint, bArr2, min, i);
            }
            if (bulkTransfer <= 0) {
                throw new IOException("Error writing " + min + " bytes at offset " + i2 + " length=" + bArr.length);
            }
            i2 += bulkTransfer;
        }
        return i2;
    }
}
