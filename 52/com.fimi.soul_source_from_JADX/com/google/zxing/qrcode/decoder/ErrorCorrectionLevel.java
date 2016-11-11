package com.google.zxing.qrcode.decoder;

public final class ErrorCorrectionLevel {
    private static final ErrorCorrectionLevel[] FOR_BITS;
    public static final ErrorCorrectionLevel f11108H;
    public static final ErrorCorrectionLevel f11109L;
    public static final ErrorCorrectionLevel f11110M;
    public static final ErrorCorrectionLevel f11111Q;
    private final int bits;
    private final String name;
    private final int ordinal;

    static {
        f11109L = new ErrorCorrectionLevel(0, 1, "L");
        f11110M = new ErrorCorrectionLevel(1, 0, "M");
        f11111Q = new ErrorCorrectionLevel(2, 3, "Q");
        f11108H = new ErrorCorrectionLevel(3, 2, "H");
        FOR_BITS = new ErrorCorrectionLevel[]{f11110M, f11109L, f11108H, f11111Q};
    }

    private ErrorCorrectionLevel(int i, int i2, String str) {
        this.ordinal = i;
        this.bits = i2;
        this.name = str;
    }

    public static ErrorCorrectionLevel forBits(int i) {
        if (i >= 0 && i < FOR_BITS.length) {
            return FOR_BITS[i];
        }
        throw new IllegalArgumentException();
    }

    public int getBits() {
        return this.bits;
    }

    public String getName() {
        return this.name;
    }

    public int ordinal() {
        return this.ordinal;
    }

    public String toString() {
        return this.name;
    }
}
