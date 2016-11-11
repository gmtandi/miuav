package com.facebook.imagepipeline.nativecode;

import android.os.Build.VERSION;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.webp.WebpSupportStatus;
import java.io.InputStream;
import java.io.OutputStream;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

@DoNotStrip
public class WebpTranscoder {

    /* renamed from: com.facebook.imagepipeline.nativecode.WebpTranscoder.1 */
    /* synthetic */ class C10391 {
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
        }
    }

    static {
        ImagePipelineNativeLoader.load();
    }

    public static boolean isWebpNativelySupported(ImageFormat imageFormat) {
        switch (C10391.$SwitchMap$com$facebook$imageformat$ImageFormat[imageFormat.ordinal()]) {
            case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                return VERSION.SDK_INT >= 14;
            case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
            case Type.BYTE /*3*/:
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                return WebpSupportStatus.sIsExtendedWebpSupported;
            case Type.INT /*5*/:
                return false;
            default:
                Preconditions.checkArgument(false);
                return false;
        }
    }

    @DoNotStrip
    private static native void nativeTranscodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int i);

    @DoNotStrip
    private static native void nativeTranscodeWebpToPng(InputStream inputStream, OutputStream outputStream);

    public static void transcodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int i) {
        nativeTranscodeWebpToJpeg((InputStream) Preconditions.checkNotNull(inputStream), (OutputStream) Preconditions.checkNotNull(outputStream), i);
    }

    public static void transcodeWebpToPng(InputStream inputStream, OutputStream outputStream) {
        nativeTranscodeWebpToPng((InputStream) Preconditions.checkNotNull(inputStream), (OutputStream) Preconditions.checkNotNull(outputStream));
    }
}
