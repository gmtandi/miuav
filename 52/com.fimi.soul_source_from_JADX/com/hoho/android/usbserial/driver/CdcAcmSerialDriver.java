package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.util.Log;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class CdcAcmSerialDriver extends CommonUsbSerialDriver {
    private static final int GET_LINE_CODING = 33;
    private static final int SEND_BREAK = 35;
    private static final int SET_CONTROL_LINE_STATE = 34;
    private static final int SET_LINE_CODING = 32;
    private static final int USB_RECIP_INTERFACE = 1;
    private static final int USB_RT_ACM = 33;
    private final String TAG;
    private UsbEndpoint mControlEndpoint;
    private UsbInterface mControlInterface;
    private UsbInterface mDataInterface;
    private boolean mDtr;
    private UsbEndpoint mReadEndpoint;
    private boolean mRts;
    private UsbEndpoint mWriteEndpoint;

    public CdcAcmSerialDriver(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        super(usbDevice, usbDeviceConnection);
        this.TAG = CdcAcmSerialDriver.class.getSimpleName();
        this.mRts = false;
        this.mDtr = false;
    }

    public static Map<Integer, int[]> getSupportedDevices() {
        Map<Integer, int[]> linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Integer.valueOf(UsbId.VENDOR_ARDUINO), new int[]{USB_RECIP_INTERFACE, 67, 16, 66, 59, 68, 63, 68, UsbId.ARDUINO_LEONARDO});
        Integer valueOf = Integer.valueOf(UsbId.VENDOR_VAN_OOIJEN_TECH);
        Object obj = new int[USB_RECIP_INTERFACE];
        obj[0] = 1155;
        linkedHashMap.put(valueOf, obj);
        valueOf = Integer.valueOf(UsbId.VENDOR_ATMEL);
        obj = new int[USB_RECIP_INTERFACE];
        obj[0] = 8260;
        linkedHashMap.put(valueOf, obj);
        valueOf = Integer.valueOf(UsbId.VENDOR_LEAFLABS);
        obj = new int[USB_RECIP_INTERFACE];
        obj[0] = 4;
        linkedHashMap.put(valueOf, obj);
        return linkedHashMap;
    }

    private int sendAcmControlMessage(int i, int i2, byte[] bArr) {
        return this.mConnection.controlTransfer(USB_RT_ACM, i, i2, 0, bArr, bArr != null ? bArr.length : 0, FtdiSerialDriver.USB_WRITE_TIMEOUT_MILLIS);
    }

    private void setDtrRts() {
        int i = 0;
        int i2 = this.mRts ? 2 : 0;
        if (this.mDtr) {
            i = USB_RECIP_INTERFACE;
        }
        sendAcmControlMessage(SET_CONTROL_LINE_STATE, i2 | i, null);
    }

    public void close() {
        this.mConnection.close();
    }

    public boolean getCD() {
        return false;
    }

    public boolean getCTS() {
        return false;
    }

    public boolean getDSR() {
        return false;
    }

    public boolean getDTR() {
        return this.mDtr;
    }

    public boolean getRI() {
        return false;
    }

    public boolean getRTS() {
        return this.mRts;
    }

    public void open() {
        Log.d(this.TAG, "claiming interfaces, count=" + this.mDevice.getInterfaceCount());
        Log.d(this.TAG, "Claiming control interface.");
        this.mControlInterface = this.mDevice.getInterface(0);
        Log.d(this.TAG, "Control iface=" + this.mControlInterface);
        if (this.mConnection.claimInterface(this.mControlInterface, true)) {
            this.mControlEndpoint = this.mControlInterface.getEndpoint(0);
            Log.d(this.TAG, "Control endpoint direction: " + this.mControlEndpoint.getDirection());
            Log.d(this.TAG, "Claiming data interface.");
            this.mDataInterface = this.mDevice.getInterface(USB_RECIP_INTERFACE);
            Log.d(this.TAG, "data iface=" + this.mDataInterface);
            if (this.mConnection.claimInterface(this.mDataInterface, true)) {
                this.mReadEndpoint = this.mDataInterface.getEndpoint(USB_RECIP_INTERFACE);
                Log.d(this.TAG, "Read endpoint direction: " + this.mReadEndpoint.getDirection());
                this.mWriteEndpoint = this.mDataInterface.getEndpoint(0);
                Log.d(this.TAG, "Write endpoint direction: " + this.mWriteEndpoint.getDirection());
                return;
            }
            throw new IOException("Could not claim data interface.");
        }
        throw new IOException("Could not claim control interface.");
    }

    public /* bridge */ /* synthetic */ boolean purgeHwBuffers(boolean z, boolean z2) {
        return super.purgeHwBuffers(z, z2);
    }

    public int read(byte[] bArr, int i) {
        synchronized (this.mReadBufferLock) {
            int bulkTransfer = this.mConnection.bulkTransfer(this.mReadEndpoint, this.mReadBuffer, Math.min(bArr.length, this.mReadBuffer.length), i);
            if (bulkTransfer < 0) {
                return 0;
            }
            System.arraycopy(this.mReadBuffer, 0, bArr, 0, bulkTransfer);
            return bulkTransfer;
        }
    }

    public void setDTR(boolean z) {
        this.mDtr = z;
        setDtrRts();
    }

    public void setParameters(int i, int i2, int i3, int i4) {
        byte b;
        byte b2;
        switch (i3) {
            case USB_RECIP_INTERFACE /*1*/:
                b = (byte) 0;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                b = (byte) 2;
                break;
            case Type.BYTE /*3*/:
                b = (byte) 1;
                break;
            default:
                throw new IllegalArgumentException("Bad value for stopBits: " + i3);
        }
        switch (i4) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                b2 = (byte) 0;
                break;
            case USB_RECIP_INTERFACE /*1*/:
                b2 = (byte) 1;
                break;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                b2 = (byte) 2;
                break;
            case Type.BYTE /*3*/:
                b2 = (byte) 3;
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                b2 = (byte) 4;
                break;
            default:
                throw new IllegalArgumentException("Bad value for parity: " + i4);
        }
        sendAcmControlMessage(SET_LINE_CODING, 0, new byte[]{(byte) (i & Util.MASK_8BIT), (byte) ((i >> 8) & Util.MASK_8BIT), (byte) ((i >> 16) & Util.MASK_8BIT), (byte) ((i >> 24) & Util.MASK_8BIT), b, b2, (byte) i2});
    }

    public void setRTS(boolean z) {
        this.mRts = z;
        setDtrRts();
    }

    public int write(byte[] bArr, int i) {
        int i2 = 0;
        while (i2 < bArr.length) {
            synchronized (this.mWriteBufferLock) {
                byte[] bArr2;
                int min = Math.min(bArr.length - i2, this.mWriteBuffer.length);
                if (i2 == 0) {
                    bArr2 = bArr;
                } else {
                    System.arraycopy(bArr, i2, this.mWriteBuffer, 0, min);
                    bArr2 = this.mWriteBuffer;
                }
                int bulkTransfer = this.mConnection.bulkTransfer(this.mWriteEndpoint, bArr2, min, i);
            }
            if (bulkTransfer <= 0) {
                throw new IOException("Error writing " + min + " bytes at offset " + i2 + " length=" + bArr.length);
            }
            Log.d(this.TAG, "Wrote amt=" + bulkTransfer + " attempted=" + min);
            i2 += bulkTransfer;
        }
        return i2;
    }
}
