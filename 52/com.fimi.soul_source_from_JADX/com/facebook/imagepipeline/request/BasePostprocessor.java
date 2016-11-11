package com.facebook.imagepipeline.request;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import javax.annotation.Nullable;

public abstract class BasePostprocessor implements Postprocessor {
    public String getName() {
        return "Unknown postprocessor";
    }

    @Nullable
    public CacheKey getPostprocessorCacheKey() {
        return null;
    }

    public CloseableReference<Bitmap> process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
        CloseableReference createBitmap = platformBitmapFactory.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        try {
            process((Bitmap) createBitmap.get(), bitmap);
            CloseableReference<Bitmap> cloneOrNull = CloseableReference.cloneOrNull(createBitmap);
            return cloneOrNull;
        } finally {
            CloseableReference.closeSafely(createBitmap);
        }
    }

    public void process(Bitmap bitmap) {
    }

    public void process(Bitmap bitmap, Bitmap bitmap2) {
        Bitmaps.copyBitmap(bitmap, bitmap2);
        process(bitmap);
    }
}
