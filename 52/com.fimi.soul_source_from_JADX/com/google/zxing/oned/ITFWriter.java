package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public final class ITFWriter extends UPCEANWriter {
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Hashtable hashtable) {
        if (barcodeFormat == BarcodeFormat.ITF) {
            return super.encode(str, barcodeFormat, i, i2, hashtable);
        }
        throw new IllegalArgumentException(new StringBuffer().append("Can only encode ITF, but got ").append(barcodeFormat).toString());
    }

    public byte[] encode(String str) {
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException(new StringBuffer().append("Requested contents should be less than 80 digits long, but got ").append(length).toString());
        }
        int i;
        byte[] bArr = new byte[((length * 9) + 9)];
        int appendPattern = UPCEANWriter.appendPattern(bArr, 0, new int[]{1, 1, 1, 1}, 1);
        for (int i2 = 0; i2 < length; i2 += 2) {
            int digit = Character.digit(str.charAt(i2), 10);
            int digit2 = Character.digit(str.charAt(i2 + 1), 10);
            int[] iArr = new int[18];
            for (i = 0; i < 5; i++) {
                iArr[i << 1] = ITFReader.PATTERNS[digit][i];
                iArr[(i << 1) + 1] = ITFReader.PATTERNS[digit2][i];
            }
            appendPattern += UPCEANWriter.appendPattern(bArr, appendPattern, iArr, 1);
        }
        i = UPCEANWriter.appendPattern(bArr, appendPattern, new int[]{3, 1, 1}, 1) + appendPattern;
        return bArr;
    }
}
