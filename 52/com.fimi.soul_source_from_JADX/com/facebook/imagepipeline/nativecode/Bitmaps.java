package com.facebook.imagepipeline.nativecode;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.soloader.SoLoaderShim;
import com.facebook.imageutils.BitmapUtil;

@DoNotStrip
public class Bitmaps {
    static {
        SoLoaderShim.loadLibrary("bitmaps");
    }

    public static void copyBitmap(Bitmap bitmap, Bitmap bitmap2) {
        boolean z = true;
        Preconditions.checkArgument(bitmap2.getConfig() == bitmap.getConfig());
        Preconditions.checkArgument(bitmap.isMutable());
        Preconditions.checkArgument(bitmap.getWidth() == bitmap2.getWidth());
        if (bitmap.getHeight() != bitmap2.getHeight()) {
            z = false;
        }
        Preconditions.checkArgument(z);
        nativeCopyBitmap(bitmap, bitmap.getRowBytes(), bitmap2, bitmap2.getRowBytes(), bitmap.getHeight());
    }

    @DoNotStrip
    private static native void nativeCopyBitmap(Bitmap bitmap, int i, Bitmap bitmap2, int i2, int i3);

    @DoNotStrip
    private static native void nativePinBitmap(Bitmap bitmap);

    public static void pinBitmap(Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        nativePinBitmap(bitmap);
    }

    @TargetApi(19)
    public static void reconfigureBitmap(Bitmap bitmap, int i, int i2, Config config) {
        Preconditions.checkArgument(bitmap.getAllocationByteCount() >= (i * i2) * BitmapUtil.getPixelSizeForBitmapConfig(config));
        bitmap.reconfigure(i, i2, config);
    }
}
