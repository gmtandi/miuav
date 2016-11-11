package com.google.zxing.qrcode.encoder;

import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public final class MaskUtil {
    private MaskUtil() {
    }

    public static int applyMaskPenaltyRule1(ByteMatrix byteMatrix) {
        return applyMaskPenaltyRule1Internal(byteMatrix, true) + applyMaskPenaltyRule1Internal(byteMatrix, false);
    }

    private static int applyMaskPenaltyRule1Internal(ByteMatrix byteMatrix, boolean z) {
        byte b = (byte) -1;
        int height = z ? byteMatrix.getHeight() : byteMatrix.getWidth();
        int width = z ? byteMatrix.getWidth() : byteMatrix.getHeight();
        byte[][] array = byteMatrix.getArray();
        int i = 0;
        int i2 = 0;
        while (i < height) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < width) {
                int i5;
                byte b2 = z ? array[i][i3] : array[i3][i];
                if (b2 == b) {
                    i5 = i4 + 1;
                    if (i5 == 5) {
                        i2 += 3;
                    } else if (i5 > 5) {
                        i2++;
                    }
                } else {
                    byte b3 = b2;
                    i5 = 1;
                    b = b3;
                }
                i3++;
                i4 = i5;
            }
            i++;
        }
        return i2;
    }

    public static int applyMaskPenaltyRule2(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        int i2 = 0;
        while (i < height - 1) {
            int i3 = 0;
            while (i3 < width - 1) {
                byte b = array[i][i3];
                if (b == array[i][i3 + 1] && b == array[i + 1][i3] && b == array[i + 1][i3 + 1]) {
                    i2 += 3;
                }
                i3++;
            }
            i++;
        }
        return i2;
    }

    public static int applyMaskPenaltyRule3(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        int i2 = 0;
        while (i < height) {
            int i3 = 0;
            while (i3 < width) {
                if (i3 + 6 < width && array[i][i3] == (byte) 1 && array[i][i3 + 1] == null && array[i][i3 + 2] == (byte) 1 && array[i][i3 + 3] == (byte) 1 && array[i][i3 + 4] == (byte) 1 && array[i][i3 + 5] == null && array[i][i3 + 6] == (byte) 1 && ((i3 + 10 < width && array[i][i3 + 7] == null && array[i][i3 + 8] == null && array[i][i3 + 9] == null && array[i][i3 + 10] == null) || (i3 - 4 >= 0 && array[i][i3 - 1] == null && array[i][i3 - 2] == null && array[i][i3 - 3] == null && array[i][i3 - 4] == null))) {
                    i2 += 40;
                }
                if (i + 6 < height && array[i][i3] == (byte) 1 && array[i + 1][i3] == null && array[i + 2][i3] == (byte) 1 && array[i + 3][i3] == (byte) 1 && array[i + 4][i3] == (byte) 1 && array[i + 5][i3] == null && array[i + 6][i3] == (byte) 1 && ((i + 10 < height && array[i + 7][i3] == null && array[i + 8][i3] == null && array[i + 9][i3] == null && array[i + 10][i3] == null) || (i - 4 >= 0 && array[i - 1][i3] == null && array[i - 2][i3] == null && array[i - 3][i3] == null && array[i - 4][i3] == null))) {
                    i2 += 40;
                }
                i3++;
            }
            i++;
        }
        return i2;
    }

    public static int applyMaskPenaltyRule4(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int i = 0;
        for (int i2 = 0; i2 < height; i2++) {
            for (int i3 = 0; i3 < width; i3++) {
                if (array[i2][i3] == 1) {
                    i++;
                }
            }
        }
        return (Math.abs((int) (((((double) i) / ((double) (byteMatrix.getHeight() * byteMatrix.getWidth()))) * 100.0d) - 50.0d)) / 5) * 10;
    }

    public static boolean getDataMaskBit(int i, int i2, int i3) {
        if (QRCode.isValidMaskPattern(i)) {
            int i4;
            switch (i) {
                case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                    i4 = (i3 + i2) & 1;
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    i4 = i3 & 1;
                    break;
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    i4 = i2 % 3;
                    break;
                case Type.BYTE /*3*/:
                    i4 = (i3 + i2) % 3;
                    break;
                case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                    i4 = ((i3 >>> 1) + (i2 / 3)) & 1;
                    break;
                case Type.INT /*5*/:
                    i4 = i3 * i2;
                    i4 = (i4 % 3) + (i4 & 1);
                    break;
                case Type.FLOAT /*6*/:
                    i4 = i3 * i2;
                    i4 = ((i4 % 3) + (i4 & 1)) & 1;
                    break;
                case Type.LONG /*7*/:
                    i4 = (((i3 * i2) % 3) + ((i3 + i2) & 1)) & 1;
                    break;
                default:
                    throw new IllegalArgumentException(new StringBuffer().append("Invalid mask pattern: ").append(i).toString());
            }
            return i4 == 0;
        } else {
            throw new IllegalArgumentException("Invalid mask pattern");
        }
    }
}
