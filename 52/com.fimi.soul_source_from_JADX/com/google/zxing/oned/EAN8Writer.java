package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public final class EAN8Writer extends UPCEANWriter {
    private static final int codeWidth = 67;

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Hashtable hashtable) {
        if (barcodeFormat == BarcodeFormat.EAN_8) {
            return super.encode(str, barcodeFormat, i, i2, hashtable);
        }
        throw new IllegalArgumentException(new StringBuffer().append("Can only encode EAN_8, but got ").append(barcodeFormat).toString());
    }

    public byte[] encode(String str) {
        if (str.length() != 8) {
            throw new IllegalArgumentException(new StringBuffer().append("Requested contents should be 8 digits long, but got ").append(str.length()).toString());
        }
        int i;
        byte[] bArr = new byte[codeWidth];
        int appendPattern = UPCEANWriter.appendPattern(bArr, 0, UPCEANReader.START_END_PATTERN, 1) + 0;
        for (i = 0; i <= 3; i++) {
            appendPattern += UPCEANWriter.appendPattern(bArr, appendPattern, UPCEANReader.L_PATTERNS[Integer.parseInt(str.substring(i, i + 1))], 0);
        }
        int appendPattern2 = appendPattern + UPCEANWriter.appendPattern(bArr, appendPattern, UPCEANReader.MIDDLE_PATTERN, 0);
        for (i = 4; i <= 7; i++) {
            appendPattern2 += UPCEANWriter.appendPattern(bArr, appendPattern2, UPCEANReader.L_PATTERNS[Integer.parseInt(str.substring(i, i + 1))], 1);
        }
        i = UPCEANWriter.appendPattern(bArr, appendPattern2, UPCEANReader.START_END_PATTERN, 1) + appendPattern2;
        return bArr;
    }
}
