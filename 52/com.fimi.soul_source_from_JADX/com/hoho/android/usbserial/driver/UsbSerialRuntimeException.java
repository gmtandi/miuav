package com.hoho.android.usbserial.driver;

public class UsbSerialRuntimeException extends RuntimeException {
    public UsbSerialRuntimeException(String str) {
        super(str);
    }

    public UsbSerialRuntimeException(String str, Throwable th) {
        super(str, th);
    }

    public UsbSerialRuntimeException(Throwable th) {
        super(th);
    }
}
