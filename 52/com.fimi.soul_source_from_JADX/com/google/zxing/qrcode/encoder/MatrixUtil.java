package com.google.zxing.qrcode.encoder;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitArray;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.smile.SmileConstants;

public final class MatrixUtil {
    private static final int[][] HORIZONTAL_SEPARATION_PATTERN;
    private static final int[][] POSITION_ADJUSTMENT_PATTERN;
    private static final int[][] POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE;
    private static final int[][] POSITION_DETECTION_PATTERN;
    private static final int[][] TYPE_INFO_COORDINATES;
    private static final int TYPE_INFO_MASK_PATTERN = 21522;
    private static final int TYPE_INFO_POLY = 1335;
    private static final int VERSION_INFO_POLY = 7973;
    private static final int[][] VERTICAL_SEPARATION_PATTERN;

    static {
        POSITION_DETECTION_PATTERN = new int[][]{new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
        HORIZONTAL_SEPARATION_PATTERN = new int[][]{new int[]{0, 0, 0, 0, 0, 0, 0, 0}};
        int[][] iArr = new int[7][];
        iArr[0] = new int[]{0};
        iArr[1] = new int[]{0};
        iArr[2] = new int[]{0};
        iArr[3] = new int[]{0};
        iArr[4] = new int[]{0};
        iArr[5] = new int[]{0};
        iArr[6] = new int[]{0};
        VERTICAL_SEPARATION_PATTERN = iArr;
        POSITION_ADJUSTMENT_PATTERN = new int[][]{new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
        POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE = new int[][]{new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, Opcodes.FSUB, -1, -1}, new int[]{6, 28, 54, 80, Opcodes.FMUL, -1, -1}, new int[]{6, 32, 58, 84, Opcodes.FDIV, -1, -1}, new int[]{6, 30, 58, 86, Opcodes.FREM, -1, -1}, new int[]{6, 34, 62, 90, Opcodes.FNEG, -1, -1}, new int[]{6, 26, 50, 74, 98, Opcodes.ISHR, -1}, new int[]{6, 30, 54, 78, Opcodes.FSUB, Opcodes.IAND, -1}, new int[]{6, 26, 52, 78, Opcodes.IMUL, Opcodes.IXOR, -1}, new int[]{6, 30, 56, 82, Opcodes.IDIV, Opcodes.I2F, -1}, new int[]{6, 34, 60, 86, Opcodes.IREM, Opcodes.L2D, -1}, new int[]{6, 30, 58, 86, Opcodes.FREM, Opcodes.D2I, -1}, new int[]{6, 34, 62, 90, Opcodes.FNEG, Opcodes.I2C, -1}, new int[]{6, 30, 54, 78, Opcodes.FSUB, Opcodes.IAND, Opcodes.FCMPG}, new int[]{6, 24, 50, 76, Opcodes.FSUB, SmileConstants.TOKEN_PREFIX_TINY_UNICODE, Opcodes.IFNE}, new int[]{6, 28, 54, 80, Opcodes.FMUL, Opcodes.IINC, Opcodes.IFLE}, new int[]{6, 32, 58, 84, Opcodes.FDIV, Opcodes.L2I, Opcodes.IF_ICMPGE}, new int[]{6, 26, 54, 82, Opcodes.FDIV, Opcodes.L2D, Opcodes.IF_ACMPNE}, new int[]{6, 30, 58, 86, Opcodes.FREM, Opcodes.D2I, Opcodes.TABLESWITCH}};
        TYPE_INFO_COORDINATES = new int[][]{new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};
    }

    private MatrixUtil() {
    }

    public static void buildMatrix(BitArray bitArray, ErrorCorrectionLevel errorCorrectionLevel, int i, int i2, ByteMatrix byteMatrix) {
        clearMatrix(byteMatrix);
        embedBasicPatterns(i, byteMatrix);
        embedTypeInfo(errorCorrectionLevel, i2, byteMatrix);
        maybeEmbedVersionInfo(i, byteMatrix);
        embedDataBits(bitArray, i2, byteMatrix);
    }

    public static int calculateBCHCode(int i, int i2) {
        int findMSBSet = findMSBSet(i2);
        int i3 = i << (findMSBSet - 1);
        while (findMSBSet(i3) >= findMSBSet) {
            i3 ^= i2 << (findMSBSet(i3) - findMSBSet);
        }
        return i3;
    }

    public static void clearMatrix(ByteMatrix byteMatrix) {
        byteMatrix.clear((byte) -1);
    }

    public static void embedBasicPatterns(int i, ByteMatrix byteMatrix) {
        embedPositionDetectionPatternsAndSeparators(byteMatrix);
        embedDarkDotAtLeftBottomCorner(byteMatrix);
        maybeEmbedPositionAdjustmentPatterns(i, byteMatrix);
        embedTimingPatterns(byteMatrix);
    }

    private static void embedDarkDotAtLeftBottomCorner(ByteMatrix byteMatrix) {
        if (byteMatrix.get(8, byteMatrix.getHeight() - 8) == null) {
            throw new WriterException();
        }
        byteMatrix.set(8, byteMatrix.getHeight() - 8, 1);
    }

    public static void embedDataBits(BitArray bitArray, int i, ByteMatrix byteMatrix) {
        int width = byteMatrix.getWidth() - 1;
        int height = byteMatrix.getHeight() - 1;
        int i2 = -1;
        int i3 = 0;
        while (width > 0) {
            int i4;
            int i5;
            if (width == 6) {
                i4 = height;
                i5 = width - 1;
                height = i3;
            } else {
                i4 = height;
                i5 = width;
                height = i3;
            }
            while (i4 >= 0 && i4 < byteMatrix.getHeight()) {
                for (i3 = 0; i3 < 2; i3++) {
                    int i6 = i5 - i3;
                    if (isEmpty(byteMatrix.get(i6, i4))) {
                        boolean z;
                        if (height < bitArray.getSize()) {
                            width = height + 1;
                            z = bitArray.get(height);
                        } else {
                            width = height;
                            z = false;
                        }
                        if (i != -1 && MaskUtil.getDataMaskBit(i, i6, i4)) {
                            z = !z;
                        }
                        byteMatrix.set(i6, i4, z);
                        height = width;
                    }
                }
                i4 += i2;
            }
            i2 = -i2;
            width = i5 - 2;
            i3 = height;
            height = i4 + i2;
        }
        if (i3 != bitArray.getSize()) {
            throw new WriterException(new StringBuffer().append("Not all bits consumed: ").append(i3).append('/').append(bitArray.getSize()).toString());
        }
    }

    private static void embedHorizontalSeparationPattern(int i, int i2, ByteMatrix byteMatrix) {
        if (HORIZONTAL_SEPARATION_PATTERN[0].length == 8 && HORIZONTAL_SEPARATION_PATTERN.length == 1) {
            int i3 = 0;
            while (i3 < 8) {
                if (isEmpty(byteMatrix.get(i + i3, i2))) {
                    byteMatrix.set(i + i3, i2, HORIZONTAL_SEPARATION_PATTERN[0][i3]);
                    i3++;
                } else {
                    throw new WriterException();
                }
            }
            return;
        }
        throw new WriterException("Bad horizontal separation pattern");
    }

    private static void embedPositionAdjustmentPattern(int i, int i2, ByteMatrix byteMatrix) {
        if (POSITION_ADJUSTMENT_PATTERN[0].length == 5 && POSITION_ADJUSTMENT_PATTERN.length == 5) {
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = 0;
                while (i4 < 5) {
                    if (isEmpty(byteMatrix.get(i + i4, i2 + i3))) {
                        byteMatrix.set(i + i4, i2 + i3, POSITION_ADJUSTMENT_PATTERN[i3][i4]);
                        i4++;
                    } else {
                        throw new WriterException();
                    }
                }
            }
            return;
        }
        throw new WriterException("Bad position adjustment");
    }

    private static void embedPositionDetectionPattern(int i, int i2, ByteMatrix byteMatrix) {
        if (POSITION_DETECTION_PATTERN[0].length == 7 && POSITION_DETECTION_PATTERN.length == 7) {
            for (int i3 = 0; i3 < 7; i3++) {
                int i4 = 0;
                while (i4 < 7) {
                    if (isEmpty(byteMatrix.get(i + i4, i2 + i3))) {
                        byteMatrix.set(i + i4, i2 + i3, POSITION_DETECTION_PATTERN[i3][i4]);
                        i4++;
                    } else {
                        throw new WriterException();
                    }
                }
            }
            return;
        }
        throw new WriterException("Bad position detection pattern");
    }

    private static void embedPositionDetectionPatternsAndSeparators(ByteMatrix byteMatrix) {
        int length = POSITION_DETECTION_PATTERN[0].length;
        embedPositionDetectionPattern(0, 0, byteMatrix);
        embedPositionDetectionPattern(byteMatrix.getWidth() - length, 0, byteMatrix);
        embedPositionDetectionPattern(0, byteMatrix.getWidth() - length, byteMatrix);
        length = HORIZONTAL_SEPARATION_PATTERN[0].length;
        embedHorizontalSeparationPattern(0, length - 1, byteMatrix);
        embedHorizontalSeparationPattern(byteMatrix.getWidth() - length, length - 1, byteMatrix);
        embedHorizontalSeparationPattern(0, byteMatrix.getWidth() - length, byteMatrix);
        length = VERTICAL_SEPARATION_PATTERN.length;
        embedVerticalSeparationPattern(length, 0, byteMatrix);
        embedVerticalSeparationPattern((byteMatrix.getHeight() - length) - 1, 0, byteMatrix);
        embedVerticalSeparationPattern(length, byteMatrix.getHeight() - length, byteMatrix);
    }

    private static void embedTimingPatterns(ByteMatrix byteMatrix) {
        int i = 8;
        while (i < byteMatrix.getWidth() - 8) {
            int i2 = (i + 1) % 2;
            if (isValidValue(byteMatrix.get(i, 6))) {
                if (isEmpty(byteMatrix.get(i, 6))) {
                    byteMatrix.set(i, 6, i2);
                }
                if (isValidValue(byteMatrix.get(6, i))) {
                    if (isEmpty(byteMatrix.get(6, i))) {
                        byteMatrix.set(6, i, i2);
                    }
                    i++;
                } else {
                    throw new WriterException();
                }
            }
            throw new WriterException();
        }
    }

    public static void embedTypeInfo(ErrorCorrectionLevel errorCorrectionLevel, int i, ByteMatrix byteMatrix) {
        BitArray bitArray = new BitArray();
        makeTypeInfoBits(errorCorrectionLevel, i, bitArray);
        for (int i2 = 0; i2 < bitArray.getSize(); i2++) {
            boolean z = bitArray.get((bitArray.getSize() - 1) - i2);
            byteMatrix.set(TYPE_INFO_COORDINATES[i2][0], TYPE_INFO_COORDINATES[i2][1], z);
            if (i2 < 8) {
                byteMatrix.set((byteMatrix.getWidth() - i2) - 1, 8, z);
            } else {
                byteMatrix.set(8, (byteMatrix.getHeight() - 7) + (i2 - 8), z);
            }
        }
    }

    private static void embedVerticalSeparationPattern(int i, int i2, ByteMatrix byteMatrix) {
        if (VERTICAL_SEPARATION_PATTERN[0].length == 1 && VERTICAL_SEPARATION_PATTERN.length == 7) {
            int i3 = 0;
            while (i3 < 7) {
                if (isEmpty(byteMatrix.get(i, i2 + i3))) {
                    byteMatrix.set(i, i2 + i3, VERTICAL_SEPARATION_PATTERN[i3][0]);
                    i3++;
                } else {
                    throw new WriterException();
                }
            }
            return;
        }
        throw new WriterException("Bad vertical separation pattern");
    }

    public static int findMSBSet(int i) {
        int i2 = 0;
        while (i != 0) {
            i >>>= 1;
            i2++;
        }
        return i2;
    }

    private static boolean isEmpty(int i) {
        return i == -1;
    }

    private static boolean isValidValue(int i) {
        return i == -1 || i == 0 || i == 1;
    }

    public static void makeTypeInfoBits(ErrorCorrectionLevel errorCorrectionLevel, int i, BitArray bitArray) {
        if (QRCode.isValidMaskPattern(i)) {
            int bits = (errorCorrectionLevel.getBits() << 3) | i;
            bitArray.appendBits(bits, 5);
            bitArray.appendBits(calculateBCHCode(bits, TYPE_INFO_POLY), 10);
            BitArray bitArray2 = new BitArray();
            bitArray2.appendBits(TYPE_INFO_MASK_PATTERN, 15);
            bitArray.xor(bitArray2);
            if (bitArray.getSize() != 15) {
                throw new WriterException(new StringBuffer().append("should not happen but we got: ").append(bitArray.getSize()).toString());
            }
            return;
        }
        throw new WriterException("Invalid mask pattern");
    }

    public static void makeVersionInfoBits(int i, BitArray bitArray) {
        bitArray.appendBits(i, 6);
        bitArray.appendBits(calculateBCHCode(i, VERSION_INFO_POLY), 12);
        if (bitArray.getSize() != 18) {
            throw new WriterException(new StringBuffer().append("should not happen but we got: ").append(bitArray.getSize()).toString());
        }
    }

    private static void maybeEmbedPositionAdjustmentPatterns(int i, ByteMatrix byteMatrix) {
        if (i >= 2) {
            int i2 = i - 1;
            int[] iArr = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[i2];
            int length = POSITION_ADJUSTMENT_PATTERN_COORDINATE_TABLE[i2].length;
            for (int i3 = 0; i3 < length; i3++) {
                for (i2 = 0; i2 < length; i2++) {
                    int i4 = iArr[i3];
                    int i5 = iArr[i2];
                    if (!(i5 == -1 || i4 == -1 || !isEmpty(byteMatrix.get(i5, i4)))) {
                        embedPositionAdjustmentPattern(i5 - 2, i4 - 2, byteMatrix);
                    }
                }
            }
        }
    }

    public static void maybeEmbedVersionInfo(int i, ByteMatrix byteMatrix) {
        if (i >= 7) {
            BitArray bitArray = new BitArray();
            makeVersionInfoBits(i, bitArray);
            int i2 = 17;
            int i3 = 0;
            while (i3 < 6) {
                int i4 = i2;
                for (i2 = 0; i2 < 3; i2++) {
                    boolean z = bitArray.get(i4);
                    i4--;
                    byteMatrix.set(i3, (byteMatrix.getHeight() - 11) + i2, z);
                    byteMatrix.set((byteMatrix.getHeight() - 11) + i2, i3, z);
                }
                i3++;
                i2 = i4;
            }
        }
    }
}
