package com.facebook.imageutils;

import com.facebook.common.internal.Preconditions;
import com.fimi.soul.drone.p107c.p108a.p109a.bj;
import com.fimi.soul.drone.p107c.p108a.p109a.bn;
import it.p074a.p075a.C2799f;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

public class JfifUtil {
    public static final int APP1_EXIF_MAGIC = 1165519206;
    public static final int MARKER_APP1 = 225;
    public static final int MARKER_EOI = 217;
    public static final int MARKER_ESCAPE_BYTE = 0;
    public static final int MARKER_FIRST_BYTE = 255;
    public static final int MARKER_RST0 = 208;
    public static final int MARKER_RST7 = 215;
    public static final int MARKER_SOFn = 192;
    public static final int MARKER_SOI = 216;
    public static final int MARKER_SOS = 218;
    public static final int MARKER_TEM = 1;

    private JfifUtil() {
    }

    public static int getAutoRotateAngleFromOrientation(int i) {
        return TiffUtil.getAutoRotateAngleFromOrientation(i);
    }

    public static int getOrientation(InputStream inputStream) {
        int i = MARKER_ESCAPE_BYTE;
        try {
            int moveToAPP1EXIF = moveToAPP1EXIF(inputStream);
            if (moveToAPP1EXIF != 0) {
                i = TiffUtil.readOrientationFromTIFF(inputStream, moveToAPP1EXIF);
            }
        } catch (IOException e) {
        }
        return i;
    }

    public static int getOrientation(byte[] bArr) {
        return getOrientation(new ByteArrayInputStream(bArr));
    }

    private static boolean isSOFn(int i) {
        switch (i) {
            case MARKER_SOFn /*192*/:
            case Opcodes.INSTANCEOF /*193*/:
            case Opcodes.MONITORENTER /*194*/:
            case Opcodes.MONITOREXIT /*195*/:
            case Opcodes.MULTIANEWARRAY /*197*/:
            case Opcodes.IFNULL /*198*/:
            case Opcodes.IFNONNULL /*199*/:
            case bj.f6779b /*201*/:
            case C2799f.f14283u /*202*/:
            case bn.f6797b /*203*/:
            case 205:
            case 206:
            case 207:
                return true;
            default:
                return false;
        }
    }

    private static int moveToAPP1EXIF(InputStream inputStream) {
        if (moveToMarker(inputStream, MARKER_APP1)) {
            int readPackedInt = StreamProcessor.readPackedInt(inputStream, 2, false) - 2;
            if (readPackedInt > 6) {
                int readPackedInt2 = StreamProcessor.readPackedInt(inputStream, 4, false);
                readPackedInt -= 4;
                int readPackedInt3 = StreamProcessor.readPackedInt(inputStream, 2, false);
                readPackedInt -= 2;
                if (readPackedInt2 == APP1_EXIF_MAGIC && readPackedInt3 == 0) {
                    return readPackedInt;
                }
            }
        }
        return MARKER_ESCAPE_BYTE;
    }

    public static boolean moveToMarker(InputStream inputStream, int i) {
        Preconditions.checkNotNull(inputStream);
        while (StreamProcessor.readPackedInt(inputStream, MARKER_TEM, false) == MARKER_FIRST_BYTE) {
            int i2 = MARKER_FIRST_BYTE;
            while (i2 == MARKER_FIRST_BYTE) {
                i2 = StreamProcessor.readPackedInt(inputStream, MARKER_TEM, false);
            }
            if ((i == MARKER_SOFn && isSOFn(i2)) || i2 == i) {
                return true;
            }
            if (!(i2 == MARKER_SOI || i2 == MARKER_TEM)) {
                if (i2 == MARKER_EOI || i2 == MARKER_SOS) {
                    return false;
                }
                inputStream.skip((long) (StreamProcessor.readPackedInt(inputStream, 2, false) - 2));
            }
        }
        return false;
    }
}
