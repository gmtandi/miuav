package com.facebook.imagepipeline.animated.base;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.facebook.common.references.CloseableReference;

public interface AnimatedDrawableCachingBackend extends AnimatedDrawableBackend {
    void appendDebugOptionString(StringBuilder stringBuilder);

    AnimatedDrawableCachingBackend forNewBounds(Rect rect);

    CloseableReference<Bitmap> getBitmapForFrame(int i);

    CloseableReference<Bitmap> getPreviewBitmap();
}
