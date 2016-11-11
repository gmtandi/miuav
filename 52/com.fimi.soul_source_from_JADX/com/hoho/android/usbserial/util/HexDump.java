package com.hoho.android.usbserial.util;

import com.tencent.mm.sdk.platformtools.Util;
import org.codehaus.jackson.smile.SmileConstants;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

public class HexDump {
    private static final char[] HEX_DIGITS;

    static {
        HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    public static String dumpHexString(byte[] bArr) {
        return dumpHexString(bArr, 0, bArr.length);
    }

    public static String dumpHexString(byte[] bArr, int i, int i2) {
        int i3 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        byte[] bArr2 = new byte[16];
        stringBuilder.append("\n0x");
        stringBuilder.append(toHexString(i));
        int i4 = i;
        int i5 = 0;
        while (i4 < i + i2) {
            if (i5 == 16) {
                stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                i5 = 0;
                while (i5 < 16) {
                    if (bArr2[i5] <= SmileConstants.TOKEN_LITERAL_EMPTY_STRING || bArr2[i5] >= (byte) 126) {
                        stringBuilder.append(".");
                    } else {
                        stringBuilder.append(new String(bArr2, i5, 1));
                    }
                    i5++;
                }
                stringBuilder.append("\n0x");
                stringBuilder.append(toHexString(i4));
                i5 = 0;
            }
            byte b = bArr[i4];
            stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuilder.append(HEX_DIGITS[(b >>> 4) & 15]);
            stringBuilder.append(HEX_DIGITS[b & 15]);
            int i6 = i5 + 1;
            bArr2[i5] = b;
            i4++;
            i5 = i6;
        }
        if (i5 != 16) {
            i6 = ((16 - i5) * 3) + 1;
            for (i4 = 0; i4 < i6; i4++) {
                stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
            while (i3 < i5) {
                if (bArr2[i3] <= SmileConstants.TOKEN_LITERAL_EMPTY_STRING || bArr2[i3] >= (byte) 126) {
                    stringBuilder.append(".");
                } else {
                    stringBuilder.append(new String(bArr2, i3, 1));
                }
                i3++;
            }
        }
        return stringBuilder.toString();
    }

    public static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((toByte(str.charAt(i)) << 4) | toByte(str.charAt(i + 1)));
        }
        return bArr;
    }

    private static int toByte(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 65) + 10;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        throw new RuntimeException("Invalid hex char '" + c + "'");
    }

    public static byte[] toByteArray(byte b) {
        return new byte[]{b};
    }

    public static byte[] toByteArray(int i) {
        return new byte[]{(byte) (i & Util.MASK_8BIT), (byte) ((i >> 8) & Util.MASK_8BIT), (byte) ((i >> 16) & Util.MASK_8BIT), (byte) ((i >> 24) & Util.MASK_8BIT)};
    }

    public static byte[] toByteArray(short s) {
        return new byte[]{(byte) (s & Util.MASK_8BIT), (byte) ((s >> 8) & Util.MASK_8BIT)};
    }

    public static String toHexString(byte b) {
        return toHexString(toByteArray(b));
    }

    public static String toHexString(int i) {
        return toHexString(toByteArray(i));
    }

    public static String toHexString(short s) {
        return toHexString(toByteArray(s));
    }

    public static String toHexString(byte[] bArr) {
        return toHexString(bArr, 0, bArr.length);
    }

    public static String toHexString(byte[] bArr, int i, int i2) {
        char[] cArr = new char[(i2 * 2)];
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            byte b = bArr[i4];
            int i5 = i3 + 1;
            cArr[i3] = HEX_DIGITS[(b >>> 4) & 15];
            i3 = i5 + 1;
            cArr[i5] = HEX_DIGITS[b & 15];
        }
        return new String(cArr);
    }
}
