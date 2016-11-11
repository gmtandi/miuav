package com.hoho.android.usbserial.driver;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.util.Log;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.fimi.kernel.C1154b;
import com.tencent.mm.sdk.platformtools.Util;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class Cp2102SerialDriver extends CommonUsbSerialDriver {
    private static final int BAUD_RATE_GEN_FREQ = 3686400;
    private static final int CONTROL_WRITE_DTR = 256;
    private static final int CONTROL_WRITE_RTS = 512;
    private static final int DEFAULT_BAUD_RATE = 9600;
    private static final int FLUSH_READ_CODE = 10;
    private static final int FLUSH_WRITE_CODE = 5;
    private static final int MCR_ALL = 3;
    private static final int MCR_DTR = 1;
    private static final int MCR_RTS = 2;
    private static final int REQTYPE_HOST_TO_DEVICE = 65;
    private static final int SILABSER_FLUSH_REQUEST_CODE = 18;
    private static final int SILABSER_IFC_ENABLE_REQUEST_CODE = 0;
    private static final int SILABSER_SET_BAUDDIV_REQUEST_CODE = 1;
    private static final int SILABSER_SET_BAUDRATE = 30;
    private static final int SILABSER_SET_LINE_CTL_REQUEST_CODE = 3;
    private static final int SILABSER_SET_MHS_REQUEST_CODE = 7;
    private static final String TAG;
    private static final int UART_DISABLE = 0;
    private static final int UART_ENABLE = 1;
    private static final int USB_WRITE_TIMEOUT_MILLIS = 5000;
    private UsbEndpoint mReadEndpoint;
    private UsbEndpoint mWriteEndpoint;

    static {
        TAG = Cp2102SerialDriver.class.getSimpleName();
    }

    public Cp2102SerialDriver(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) {
        super(usbDevice, usbDeviceConnection);
    }

    public static Map<Integer, int[]> getSupportedDevices() {
        Map<Integer, int[]> linkedHashMap = new LinkedHashMap();
        Integer valueOf = Integer.valueOf(UsbId.VENDOR_SILAB);
        Object obj = new int[UART_ENABLE];
        obj[UART_DISABLE] = 60000;
        linkedHashMap.put(valueOf, obj);
        return linkedHashMap;
    }

    private void setBaudRate(int i) {
        if (this.mConnection.controlTransfer(REQTYPE_HOST_TO_DEVICE, SILABSER_SET_BAUDRATE, UART_DISABLE, UART_DISABLE, new byte[]{(byte) (i & Util.MASK_8BIT), (byte) ((i >> 8) & Util.MASK_8BIT), (byte) ((i >> 16) & Util.MASK_8BIT), (byte) ((i >> 24) & Util.MASK_8BIT)}, 4, USB_WRITE_TIMEOUT_MILLIS) < 0) {
            throw new IOException("Error setting baud rate.");
        }
    }

    private int setConfigSingle(int i, int i2) {
        return this.mConnection.controlTransfer(REQTYPE_HOST_TO_DEVICE, i, i2, UART_DISABLE, null, UART_DISABLE, USB_WRITE_TIMEOUT_MILLIS);
    }

    public void close() {
        setConfigSingle(UART_DISABLE, UART_DISABLE);
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
        return true;
    }

    public boolean getRI() {
        return false;
    }

    public boolean getRTS() {
        return true;
    }

    public void open() {
        int i = UART_DISABLE;
        int i2 = UART_DISABLE;
        while (i2 < this.mDevice.getInterfaceCount()) {
            try {
                if (this.mConnection.claimInterface(this.mDevice.getInterface(i2), true)) {
                    Log.d(TAG, "claimInterface " + i2 + " SUCCESS");
                } else {
                    Log.d(TAG, "claimInterface " + i2 + " FAIL");
                }
                i2 += UART_ENABLE;
            } catch (Throwable th) {
                close();
            }
        }
        UsbInterface usbInterface = this.mDevice.getInterface(this.mDevice.getInterfaceCount() - 1);
        while (i < usbInterface.getEndpointCount()) {
            UsbEndpoint endpoint = usbInterface.getEndpoint(i);
            if (endpoint.getType() == MCR_RTS) {
                if (endpoint.getDirection() == SmileConstants.TOKEN_PREFIX_TINY_UNICODE) {
                    this.mReadEndpoint = endpoint;
                } else {
                    this.mWriteEndpoint = endpoint;
                }
            }
            i += UART_ENABLE;
        }
        setConfigSingle(UART_DISABLE, UART_ENABLE);
        setConfigSingle(SILABSER_SET_MHS_REQUEST_CODE, 771);
        setConfigSingle(UART_ENABLE, BitmapCounterProvider.MAX_BITMAP_COUNT);
    }

    public boolean purgeHwBuffers(boolean z, boolean z2) {
        int i = UART_DISABLE;
        int i2 = z ? FLUSH_READ_CODE : UART_DISABLE;
        if (z2) {
            i = FLUSH_WRITE_CODE;
        }
        i |= i2;
        if (i != 0) {
            setConfigSingle(SILABSER_FLUSH_REQUEST_CODE, i);
        }
        return true;
    }

    public int read(byte[] bArr, int i) {
        synchronized (this.mReadBufferLock) {
            int bulkTransfer = this.mConnection.bulkTransfer(this.mReadEndpoint, this.mReadBuffer, Math.min(bArr.length, this.mReadBuffer.length), i);
            if (bulkTransfer < 0) {
                return UART_DISABLE;
            }
            System.arraycopy(this.mReadBuffer, UART_DISABLE, bArr, UART_DISABLE, bulkTransfer);
            return bulkTransfer;
        }
    }

    public void setDTR(boolean z) {
    }

    public void setParameters(int i, int i2, int i3, int i4) {
        int i5 = Opcodes.ACC_STRICT;
        int i6 = UART_DISABLE;
        setBaudRate(i);
        switch (i2) {
            case FLUSH_WRITE_CODE /*5*/:
                i5 = C1154b.f5235f;
                break;
            case Type.FLOAT /*6*/:
                i5 = 1536;
                break;
            case SILABSER_SET_MHS_REQUEST_CODE /*7*/:
                i5 = 1792;
                break;
        }
        setConfigSingle(SILABSER_SET_LINE_CTL_REQUEST_CODE, i5);
        switch (i4) {
            case UART_ENABLE /*1*/:
                i5 = 16;
                break;
            case MCR_RTS /*2*/:
                i5 = 32;
                break;
            default:
                i5 = UART_DISABLE;
                break;
        }
        setConfigSingle(SILABSER_SET_LINE_CTL_REQUEST_CODE, i5);
        switch (i3) {
            case MCR_RTS /*2*/:
                i6 = MCR_RTS;
                break;
        }
        setConfigSingle(SILABSER_SET_LINE_CTL_REQUEST_CODE, i6);
    }

    public void setRTS(boolean z) {
    }

    public int write(byte[] bArr, int i) {
        int i2 = UART_DISABLE;
        while (i2 < bArr.length) {
            synchronized (this.mWriteBufferLock) {
                byte[] bArr2;
                int min = Math.min(bArr.length - i2, this.mWriteBuffer.length);
                if (i2 == 0) {
                    bArr2 = bArr;
                } else {
                    System.arraycopy(bArr, i2, this.mWriteBuffer, UART_DISABLE, min);
                    bArr2 = this.mWriteBuffer;
                }
                int bulkTransfer = this.mConnection.bulkTransfer(this.mWriteEndpoint, bArr2, min, i);
            }
            if (bulkTransfer <= 0) {
                throw new IOException("Error writing " + min + " bytes at offset " + i2 + " length=" + bArr.length);
            }
            Log.d(TAG, "Wrote amt=" + bulkTransfer + " attempted=" + min);
            i2 += bulkTransfer;
        }
        return i2;
    }
}
