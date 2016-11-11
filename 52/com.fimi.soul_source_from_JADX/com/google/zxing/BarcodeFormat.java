package com.google.zxing;

import java.util.Hashtable;

public final class BarcodeFormat {
    public static final BarcodeFormat CODABAR;
    public static final BarcodeFormat CODE_128;
    public static final BarcodeFormat CODE_39;
    public static final BarcodeFormat CODE_93;
    public static final BarcodeFormat DATA_MATRIX;
    public static final BarcodeFormat EAN_13;
    public static final BarcodeFormat EAN_8;
    public static final BarcodeFormat ITF;
    public static final BarcodeFormat PDF417;
    public static final BarcodeFormat QR_CODE;
    public static final BarcodeFormat RSS14;
    public static final BarcodeFormat RSS_EXPANDED;
    public static final BarcodeFormat UPC_A;
    public static final BarcodeFormat UPC_E;
    public static final BarcodeFormat UPC_EAN_EXTENSION;
    private static final Hashtable VALUES;
    private final String name;

    static {
        VALUES = new Hashtable();
        QR_CODE = new BarcodeFormat("QR_CODE");
        DATA_MATRIX = new BarcodeFormat("DATA_MATRIX");
        UPC_E = new BarcodeFormat("UPC_E");
        UPC_A = new BarcodeFormat("UPC_A");
        EAN_8 = new BarcodeFormat("EAN_8");
        EAN_13 = new BarcodeFormat("EAN_13");
        UPC_EAN_EXTENSION = new BarcodeFormat("UPC_EAN_EXTENSION");
        CODE_128 = new BarcodeFormat("CODE_128");
        CODE_39 = new BarcodeFormat("CODE_39");
        CODE_93 = new BarcodeFormat("CODE_93");
        CODABAR = new BarcodeFormat("CODABAR");
        ITF = new BarcodeFormat("ITF");
        RSS14 = new BarcodeFormat("RSS14");
        PDF417 = new BarcodeFormat("PDF417");
        RSS_EXPANDED = new BarcodeFormat("RSS_EXPANDED");
    }

    private BarcodeFormat(String str) {
        this.name = str;
        VALUES.put(str, this);
    }

    public static BarcodeFormat valueOf(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException();
        }
        BarcodeFormat barcodeFormat = (BarcodeFormat) VALUES.get(str);
        if (barcodeFormat != null) {
            return barcodeFormat;
        }
        throw new IllegalArgumentException();
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
