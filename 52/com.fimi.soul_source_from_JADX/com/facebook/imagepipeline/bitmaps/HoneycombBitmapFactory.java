package com.facebook.imagepipeline.bitmaps;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import javax.annotation.concurrent.ThreadSafe;

@TargetApi(11)
@ThreadSafe
public class HoneycombBitmapFactory extends PlatformBitmapFactory {
    private final EmptyJpegGenerator mJpegGenerator;
    private final PlatformDecoder mPurgeableDecoder;

    public HoneycombBitmapFactory(EmptyJpegGenerator emptyJpegGenerator, PlatformDecoder platformDecoder) {
        this.mJpegGenerator = emptyJpegGenerator;
        this.mPurgeableDecoder = platformDecoder;
    }

    public CloseableReference<Bitmap> createBitmap(int i, int i2, Config config) {
        CloseableReference generate = this.mJpegGenerator.generate((short) i, (short) i2);
        EncodedImage encodedImage;
        try {
            encodedImage = new EncodedImage(generate);
            encodedImage.setImageFormat(ImageFormat.JPEG);
            CloseableReference<Bitmap> decodeJPEGFromEncodedImage = this.mPurgeableDecoder.decodeJPEGFromEncodedImage(encodedImage, config, ((PooledByteBuffer) generate.get()).size());
            ((Bitmap) decodeJPEGFromEncodedImage.get()).eraseColor(0);
            EncodedImage.closeSafely(encodedImage);
            generate.close();
            return decodeJPEGFromEncodedImage;
        } catch (Throwable th) {
            generate.close();
        }
    }
}
