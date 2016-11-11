package com.facebook.imageutils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;
import android.support.v4.util.Pools.SynchronizedPool;
import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import org.codehaus.jackson.org.objectweb.asm.Type;

public final class BitmapUtil {
    public static final int ALPHA_8_BYTES_PER_PIXEL = 1;
    public static final int ARGB_4444_BYTES_PER_PIXEL = 2;
    public static final int ARGB_8888_BYTES_PER_PIXEL = 4;
    private static final SynchronizedPool<ByteBuffer> DECODE_BUFFERS;
    private static final int DECODE_BUFFER_SIZE = 16384;
    public static final float MAX_BITMAP_SIZE = 2048.0f;
    private static final int POOL_SIZE = 12;
    public static final int RGB_565_BYTES_PER_PIXEL = 2;

    /* renamed from: com.facebook.imageutils.BitmapUtil.1 */
    /* synthetic */ class C10781 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        static {
            $SwitchMap$android$graphics$Bitmap$Config = new int[Config.values().length];
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Config.ARGB_8888.ordinal()] = BitmapUtil.ALPHA_8_BYTES_PER_PIXEL;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Config.ALPHA_8.ordinal()] = BitmapUtil.RGB_565_BYTES_PER_PIXEL;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Config.RGB_565.ordinal()] = BitmapUtil.ARGB_8888_BYTES_PER_PIXEL;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    static {
        DECODE_BUFFERS = new SynchronizedPool(POOL_SIZE);
    }

    @Nullable
    public static Pair<Integer, Integer> decodeDimensions(InputStream inputStream) {
        Pair<Integer, Integer> pair = null;
        Preconditions.checkNotNull(inputStream);
        ByteBuffer byteBuffer = (ByteBuffer) DECODE_BUFFERS.acquire();
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(DECODE_BUFFER_SIZE);
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        try {
            options.inTempStorage = byteBuffer.array();
            BitmapFactory.decodeStream(inputStream, null, options);
            if (!(options.outWidth == -1 || options.outHeight == -1)) {
                pair = new Pair(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
            }
            DECODE_BUFFERS.release(byteBuffer);
            return pair;
        } catch (Throwable th) {
            DECODE_BUFFERS.release(byteBuffer);
        }
    }

    @Nullable
    public static Pair<Integer, Integer> decodeDimensions(byte[] bArr) {
        return decodeDimensions(new ByteArrayInputStream(bArr));
    }

    public static int getPixelSizeForBitmapConfig(Config config) {
        switch (C10781.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()]) {
            case ALPHA_8_BYTES_PER_PIXEL /*1*/:
                return ARGB_8888_BYTES_PER_PIXEL;
            case RGB_565_BYTES_PER_PIXEL /*2*/:
                return ALPHA_8_BYTES_PER_PIXEL;
            case Type.BYTE /*3*/:
            case ARGB_8888_BYTES_PER_PIXEL /*4*/:
                return RGB_565_BYTES_PER_PIXEL;
            default:
                throw new UnsupportedOperationException("The provided Bitmap.Config is not supported");
        }
    }

    public static int getSizeInByteForBitmap(int i, int i2, Config config) {
        return (i * i2) * getPixelSizeForBitmapConfig(config);
    }

    @SuppressLint({"NewApi"})
    public static int getSizeInBytes(@Nullable Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        if (VERSION.SDK_INT > 19) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException e) {
            }
        }
        return VERSION.SDK_INT >= POOL_SIZE ? bitmap.getByteCount() : bitmap.getWidth() * bitmap.getRowBytes();
    }
}
