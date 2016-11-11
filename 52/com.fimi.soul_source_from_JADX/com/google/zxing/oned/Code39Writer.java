package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public final class Code39Writer extends UPCEANWriter {
    private static void toIntArray(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            iArr[i2] = ((1 << i2) & i) == 0 ? 1 : 2;
        }
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Hashtable hashtable) {
        if (barcodeFormat == BarcodeFormat.CODE_39) {
            return super.encode(str, barcodeFormat, i, i2, hashtable);
        }
        throw new IllegalArgumentException(new StringBuffer().append("Can only encode CODE_39, but got ").append(barcodeFormat).toString());
    }

    public byte[] encode(String str) {
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException(new StringBuffer().append("Requested contents should be less than 80 digits long, but got ").append(length).toString());
        }
        int i;
        int[] iArr = new int[9];
        int i2 = length + 25;
        int i3 = 0;
        while (i3 < length) {
            toIntArray(Code39Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i3))], iArr);
            i = i2;
            for (int i4 : iArr) {
                i += i4;
            }
            i3++;
            i2 = i;
        }
        byte[] bArr = new byte[i2];
        toIntArray(Code39Reader.CHARACTER_ENCODINGS[39], iArr);
        i2 = UPCEANWriter.appendPattern(bArr, 0, iArr, 1);
        int[] iArr2 = new int[]{1};
        i = UPCEANWriter.appendPattern(bArr, i2, iArr2, 0) + i2;
        for (i2 = length - 1; i2 >= 0; i2--) {
            toIntArray(Code39Reader.CHARACTER_ENCODINGS["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i2))], iArr);
            i += UPCEANWriter.appendPattern(bArr, i, iArr, 1);
            i += UPCEANWriter.appendPattern(bArr, i, iArr2, 0);
        }
        toIntArray(Code39Reader.CHARACTER_ENCODINGS[39], iArr);
        i2 = UPCEANWriter.appendPattern(bArr, i, iArr, 1) + i;
        return bArr;
    }
}
