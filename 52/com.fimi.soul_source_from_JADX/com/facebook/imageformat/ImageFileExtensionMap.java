package com.facebook.imageformat;

import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class ImageFileExtensionMap {

    /* renamed from: com.facebook.imageformat.ImageFileExtensionMap.1 */
    /* synthetic */ class C09891 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imageformat$ImageFormat;

        static {
            $SwitchMap$com$facebook$imageformat$ImageFormat = new int[ImageFormat.values().length];
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.WEBP_SIMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.WEBP_LOSSLESS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.WEBP_EXTENDED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.WEBP_EXTENDED_WITH_ALPHA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.WEBP_ANIMATED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.JPEG.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.PNG.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.GIF.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormat.BMP.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    private ImageFileExtensionMap() {
    }

    public static String getFileExtension(ImageFormat imageFormat) {
        switch (C09891.$SwitchMap$com$facebook$imageformat$ImageFormat[imageFormat.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
            case Type.INT /*5*/:
                return "webp";
            case Type.FLOAT /*6*/:
                return "jpeg";
            case Type.LONG /*7*/:
                return "png";
            case Type.DOUBLE /*8*/:
                return "gif";
            case Type.ARRAY /*9*/:
                return "bmp";
            default:
                throw new UnsupportedOperationException("Unknown image format " + imageFormat.name());
        }
    }
}
