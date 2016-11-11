package com.hoho.android.usbserial.driver;

public interface UsbSerialDriver {
    public static final int DATABITS_5 = 5;
    public static final int DATABITS_6 = 6;
    public static final int DATABITS_7 = 7;
    public static final int DATABITS_8 = 8;
    public static final int FLOWCONTROL_NONE = 0;
    public static final int FLOWCONTROL_RTSCTS_IN = 1;
    public static final int FLOWCONTROL_RTSCTS_OUT = 2;
    public static final int FLOWCONTROL_XONXOFF_IN = 4;
    public static final int FLOWCONTROL_XONXOFF_OUT = 8;
    public static final int PARITY_EVEN = 2;
    public static final int PARITY_MARK = 3;
    public static final int PARITY_NONE = 0;
    public static final int PARITY_ODD = 1;
    public static final int PARITY_SPACE = 4;
    public static final int STOPBITS_1 = 1;
    public static final int STOPBITS_1_5 = 3;
    public static final int STOPBITS_2 = 2;

    void close();

    boolean getCD();

    boolean getCTS();

    boolean getDSR();

    boolean getDTR();

    boolean getRI();

    boolean getRTS();

    void open();

    boolean purgeHwBuffers(boolean z, boolean z2);

    int read(byte[] bArr, int i);

    void setDTR(boolean z);

    void setParameters(int i, int i2, int i3, int i4);

    void setRTS(boolean z);

    int write(byte[] bArr, int i);
}
