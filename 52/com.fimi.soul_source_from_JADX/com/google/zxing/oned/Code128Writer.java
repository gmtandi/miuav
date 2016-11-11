package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public final class Code128Writer extends UPCEANWriter {
    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Hashtable hashtable) {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.encode(str, barcodeFormat, i, i2, hashtable);
        }
        throw new IllegalArgumentException(new StringBuffer().append("Can only encode CODE_128, but got ").append(barcodeFormat).toString());
    }

    public byte[] encode(String str) {
        int i = 0;
        int length = str.length();
        if (length > 80) {
            throw new IllegalArgumentException(new StringBuffer().append("Requested contents should be less than 80 digits long, but got ").append(length).toString());
        }
        int i2;
        int i3 = 35;
        int i4 = 0;
        while (i4 < length) {
            i2 = i3;
            for (int i5 : Code128Reader.CODE_PATTERNS[str.charAt(i4) - 32]) {
                i2 += i5;
            }
            i4++;
            i3 = i2;
        }
        byte[] bArr = new byte[i3];
        i2 = UPCEANWriter.appendPattern(bArr, 0, Code128Reader.CODE_PATTERNS[Opcodes.IMUL], 1);
        i3 = Opcodes.IMUL;
        while (i < length) {
            i3 += (str.charAt(i) - 32) * (i + 1);
            i2 += UPCEANWriter.appendPattern(bArr, i2, Code128Reader.CODE_PATTERNS[str.charAt(i) - 32], 1);
            i++;
        }
        i3 = UPCEANWriter.appendPattern(bArr, i2, Code128Reader.CODE_PATTERNS[i3 % Opcodes.DSUB], 1) + i2;
        i3 += UPCEANWriter.appendPattern(bArr, i3, Code128Reader.CODE_PATTERNS[Opcodes.FMUL], 1);
        return bArr;
    }
}
