package com.facebook.imageutils;

import com.facebook.common.logging.FLog;
import java.io.InputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

class TiffUtil {
    private static final Class<?> TAG;
    public static final int TIFF_BYTE_ORDER_BIG_END = 1296891946;
    public static final int TIFF_BYTE_ORDER_LITTLE_END = 1229531648;
    public static final int TIFF_TAG_ORIENTATION = 274;
    public static final int TIFF_TYPE_SHORT = 3;

    class TiffHeader {
        int byteOrder;
        int firstIfdOffset;
        boolean isLittleEndian;

        private TiffHeader() {
        }
    }

    static {
        TAG = TiffUtil.class;
    }

    TiffUtil() {
    }

    public static int getAutoRotateAngleFromOrientation(int i) {
        switch (i) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return 0;
            case TIFF_TYPE_SHORT /*3*/:
                return Opcodes.GETFIELD;
            case Type.FLOAT /*6*/:
                return 90;
            case Type.DOUBLE /*8*/:
                return 270;
            default:
                FLog.m7601i(TAG, "Unsupported orientation");
                return 0;
        }
    }

    private static int getOrientationFromTiffEntry(InputStream inputStream, int i, boolean z) {
        if (i < 10 || StreamProcessor.readPackedInt(inputStream, 2, z) != TIFF_TYPE_SHORT || StreamProcessor.readPackedInt(inputStream, 4, z) != 1) {
            return 0;
        }
        int readPackedInt = StreamProcessor.readPackedInt(inputStream, 2, z);
        StreamProcessor.readPackedInt(inputStream, 2, z);
        return readPackedInt;
    }

    private static int moveToTiffEntryWithTag(InputStream inputStream, int i, boolean z, int i2) {
        if (i < 14) {
            return 0;
        }
        int readPackedInt = StreamProcessor.readPackedInt(inputStream, 2, z);
        int i3 = i - 2;
        while (true) {
            int i4 = readPackedInt - 1;
            if (readPackedInt <= 0 || i3 < 12) {
                return 0;
            }
            readPackedInt = i3 - 2;
            if (StreamProcessor.readPackedInt(inputStream, 2, z) == i2) {
                return readPackedInt;
            }
            inputStream.skip(10);
            i3 = readPackedInt - 10;
            readPackedInt = i4;
        }
    }

    public static int readOrientationFromTIFF(InputStream inputStream, int i) {
        TiffHeader tiffHeader = new TiffHeader();
        int readTiffHeader = readTiffHeader(inputStream, i, tiffHeader);
        int i2 = tiffHeader.firstIfdOffset - 8;
        if (readTiffHeader == 0 || i2 > readTiffHeader) {
            return 0;
        }
        inputStream.skip((long) i2);
        return getOrientationFromTiffEntry(inputStream, moveToTiffEntryWithTag(inputStream, readTiffHeader - i2, tiffHeader.isLittleEndian, TIFF_TAG_ORIENTATION), tiffHeader.isLittleEndian);
    }

    private static int readTiffHeader(InputStream inputStream, int i, TiffHeader tiffHeader) {
        if (i <= 8) {
            return 0;
        }
        tiffHeader.byteOrder = StreamProcessor.readPackedInt(inputStream, 4, false);
        int i2 = i - 4;
        if (tiffHeader.byteOrder == TIFF_BYTE_ORDER_LITTLE_END || tiffHeader.byteOrder == TIFF_BYTE_ORDER_BIG_END) {
            tiffHeader.isLittleEndian = tiffHeader.byteOrder == TIFF_BYTE_ORDER_LITTLE_END;
            tiffHeader.firstIfdOffset = StreamProcessor.readPackedInt(inputStream, 4, tiffHeader.isLittleEndian);
            int i3 = i2 - 4;
            if (tiffHeader.firstIfdOffset >= 8 && tiffHeader.firstIfdOffset - 8 <= i3) {
                return i3;
            }
            FLog.m7593e(TAG, "Invalid offset");
            return 0;
        }
        FLog.m7593e(TAG, "Invalid TIFF header");
        return 0;
    }
}
