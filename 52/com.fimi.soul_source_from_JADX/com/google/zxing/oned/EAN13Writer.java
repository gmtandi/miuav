package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public final class EAN13Writer extends UPCEANWriter {
    private static final int codeWidth = 95;

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Hashtable hashtable) {
        if (barcodeFormat == BarcodeFormat.EAN_13) {
            return super.encode(str, barcodeFormat, i, i2, hashtable);
        }
        throw new IllegalArgumentException(new StringBuffer().append("Can only encode EAN_13, but got ").append(barcodeFormat).toString());
    }

    public byte[] encode(String str) {
        if (str.length() != 13) {
            throw new IllegalArgumentException(new StringBuffer().append("Requested contents should be 13 digits long, but got ").append(str.length()).toString());
        }
        int i;
        int parseInt;
        int i2 = EAN13Reader.FIRST_DIGIT_ENCODINGS[Integer.parseInt(str.substring(0, 1))];
        byte[] bArr = new byte[codeWidth];
        int appendPattern = UPCEANWriter.appendPattern(bArr, 0, UPCEANReader.START_END_PATTERN, 1) + 0;
        for (i = 1; i <= 6; i++) {
            parseInt = Integer.parseInt(str.substring(i, i + 1));
            if (((i2 >> (6 - i)) & 1) == 1) {
                parseInt += 10;
            }
            appendPattern += UPCEANWriter.appendPattern(bArr, appendPattern, UPCEANReader.L_AND_G_PATTERNS[parseInt], 0);
        }
        i = appendPattern + UPCEANWriter.appendPattern(bArr, appendPattern, UPCEANReader.MIDDLE_PATTERN, 0);
        for (parseInt = 7; parseInt <= 12; parseInt++) {
            i += UPCEANWriter.appendPattern(bArr, i, UPCEANReader.L_PATTERNS[Integer.parseInt(str.substring(parseInt, parseInt + 1))], 1);
        }
        parseInt = UPCEANWriter.appendPattern(bArr, i, UPCEANReader.START_END_PATTERN, 1) + i;
        return bArr;
    }
}
