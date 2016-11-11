package com.facebook.imagepipeline.webp;

import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.util.Base64;

public class WebpSupportStatus {
    private static final int EXTENDED_WEBP_HEADER_LENGTH = 21;
    private static final int SIMPLE_WEBP_HEADER_LENGTH = 20;
    private static final String VP8X_WEBP_BASE64 = "UklGRkoAAABXRUJQVlA4WAoAAAAQAAAAAAAAAAAAQUxQSAwAAAARBxAR/Q9ERP8DAABWUDggGAAAABQBAJ0BKgEAAQAAAP4AAA3AAP7mtQAAAA==";
    private static final byte[] WEBP_NAME_BYTES;
    private static final byte[] WEBP_RIFF_BYTES;
    private static final byte[] WEBP_VP8L_BYTES;
    private static final byte[] WEBP_VP8X_BYTES;
    private static final byte[] WEBP_VP8_BYTES;
    public static final boolean sIsExtendedWebpSupported;
    public static final boolean sIsSimpleWebpSupported;
    public static final boolean sIsWebpSupportRequired;

    static {
        boolean z = true;
        sIsWebpSupportRequired = VERSION.SDK_INT <= 17;
        if (VERSION.SDK_INT < 14) {
            z = false;
        }
        sIsSimpleWebpSupported = z;
        sIsExtendedWebpSupported = isExtendedWebpSupported();
        WEBP_RIFF_BYTES = asciiBytes("RIFF");
        WEBP_NAME_BYTES = asciiBytes("WEBP");
        WEBP_VP8_BYTES = asciiBytes("VP8 ");
        WEBP_VP8L_BYTES = asciiBytes("VP8L");
        WEBP_VP8X_BYTES = asciiBytes("VP8X");
    }

    private static byte[] asciiBytes(String str) {
        try {
            return str.getBytes("ASCII");
        } catch (Throwable e) {
            throw new RuntimeException("ASCII not found!", e);
        }
    }

    public static boolean isAnimatedWebpHeader(byte[] bArr, int i) {
        return matchBytePattern(bArr, i + 12, WEBP_VP8X_BYTES) && ((bArr[i + SIMPLE_WEBP_HEADER_LENGTH] & 2) == 2);
    }

    public static boolean isExtendedWebpHeader(byte[] bArr, int i, int i2) {
        return i2 >= EXTENDED_WEBP_HEADER_LENGTH && matchBytePattern(bArr, i + 12, WEBP_VP8X_BYTES);
    }

    public static boolean isExtendedWebpHeaderWithAlpha(byte[] bArr, int i) {
        return matchBytePattern(bArr, i + 12, WEBP_VP8X_BYTES) && ((bArr[i + SIMPLE_WEBP_HEADER_LENGTH] & 16) == 16);
    }

    private static boolean isExtendedWebpSupported() {
        if (VERSION.SDK_INT < 17) {
            return false;
        }
        if (VERSION.SDK_INT == 17) {
            byte[] decode = Base64.decode(VP8X_WEBP_BASE64, 0);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(decode, 0, decode.length, options);
            if (!(options.outHeight == 1 && options.outWidth == 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLosslessWebpHeader(byte[] bArr, int i) {
        return matchBytePattern(bArr, i + 12, WEBP_VP8L_BYTES);
    }

    public static boolean isSimpleWebpHeader(byte[] bArr, int i) {
        return matchBytePattern(bArr, i + 12, WEBP_VP8_BYTES);
    }

    public static boolean isWebpHeader(byte[] bArr, int i, int i2) {
        return i2 >= SIMPLE_WEBP_HEADER_LENGTH && matchBytePattern(bArr, i, WEBP_RIFF_BYTES) && matchBytePattern(bArr, i + 8, WEBP_NAME_BYTES);
    }

    public static boolean isWebpPlatformSupported(byte[] bArr, int i, int i2) {
        return isSimpleWebpHeader(bArr, i) ? sIsSimpleWebpSupported : isLosslessWebpHeader(bArr, i) ? sIsExtendedWebpSupported : (!isExtendedWebpHeader(bArr, i, i2) || isAnimatedWebpHeader(bArr, i)) ? false : sIsExtendedWebpSupported;
    }

    private static boolean matchBytePattern(byte[] bArr, int i, byte[] bArr2) {
        if (bArr2.length + i > bArr.length) {
            return false;
        }
        for (int i2 = 0; i2 < bArr2.length; i2++) {
            if (bArr[i2 + i] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }
}
