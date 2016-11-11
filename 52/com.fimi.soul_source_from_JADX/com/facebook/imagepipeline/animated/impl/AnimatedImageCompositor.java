package com.facebook.imagepipeline.animated.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableFrameInfo.DisposalMethod;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

public class AnimatedImageCompositor {
    private final AnimatedDrawableBackend mAnimatedDrawableBackend;
    private final Callback mCallback;
    private final Paint mTransparentFillPaint;

    public interface Callback {
        CloseableReference<Bitmap> getCachedBitmap(int i);

        void onIntermediateResult(int i, Bitmap bitmap);
    }

    /* renamed from: com.facebook.imagepipeline.animated.impl.AnimatedImageCompositor.1 */
    /* synthetic */ class C10031 {
        static final /* synthetic */ int[] f5055x60fd27af;

        static {
            f5055x60fd27af = new int[FrameNeededResult.values().length];
            try {
                f5055x60fd27af[FrameNeededResult.REQUIRED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f5055x60fd27af[FrameNeededResult.NOT_REQUIRED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f5055x60fd27af[FrameNeededResult.ABORT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f5055x60fd27af[FrameNeededResult.SKIP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    enum FrameNeededResult {
        REQUIRED,
        NOT_REQUIRED,
        SKIP,
        ABORT
    }

    public AnimatedImageCompositor(AnimatedDrawableBackend animatedDrawableBackend, Callback callback) {
        this.mAnimatedDrawableBackend = animatedDrawableBackend;
        this.mCallback = callback;
        this.mTransparentFillPaint = new Paint();
        this.mTransparentFillPaint.setColor(0);
        this.mTransparentFillPaint.setStyle(Style.FILL);
        this.mTransparentFillPaint.setXfermode(new PorterDuffXfermode(Mode.SRC));
    }

    private void disposeToBackground(Canvas canvas, AnimatedDrawableFrameInfo animatedDrawableFrameInfo) {
        canvas.drawRect((float) animatedDrawableFrameInfo.xOffset, (float) animatedDrawableFrameInfo.yOffset, (float) (animatedDrawableFrameInfo.xOffset + animatedDrawableFrameInfo.width), (float) (animatedDrawableFrameInfo.yOffset + animatedDrawableFrameInfo.height), this.mTransparentFillPaint);
    }

    private FrameNeededResult isFrameNeededForRendering(int i) {
        AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(i);
        DisposalMethod disposalMethod = frameInfo.disposalMethod;
        return disposalMethod == DisposalMethod.DISPOSE_DO_NOT ? FrameNeededResult.REQUIRED : disposalMethod == DisposalMethod.DISPOSE_TO_BACKGROUND ? (frameInfo.xOffset == 0 && frameInfo.yOffset == 0 && frameInfo.width == this.mAnimatedDrawableBackend.getRenderedWidth() && frameInfo.height == this.mAnimatedDrawableBackend.getRenderedHeight()) ? FrameNeededResult.NOT_REQUIRED : FrameNeededResult.REQUIRED : disposalMethod == DisposalMethod.DISPOSE_TO_PREVIOUS ? FrameNeededResult.SKIP : FrameNeededResult.ABORT;
    }

    private int prepareCanvasWithClosestCachedFrame(int i, Canvas canvas) {
        for (int i2 = i; i2 >= 0; i2--) {
            switch (C10031.f5055x60fd27af[isFrameNeededForRendering(i2).ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(i2);
                    CloseableReference cachedBitmap = this.mCallback.getCachedBitmap(i2);
                    if (cachedBitmap == null) {
                        if (frameInfo.shouldBlendWithPreviousFrame) {
                            break;
                        }
                        return i2;
                    }
                    try {
                        canvas.drawBitmap((Bitmap) cachedBitmap.get(), 0.0f, 0.0f, null);
                        if (frameInfo.disposalMethod == DisposalMethod.DISPOSE_TO_BACKGROUND) {
                            disposeToBackground(canvas, frameInfo);
                        }
                        int i3 = i2 + 1;
                        cachedBitmap.close();
                        return i3;
                    } catch (Throwable th) {
                        cachedBitmap.close();
                    }
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    return i2 + 1;
                case Type.BYTE /*3*/:
                    return i2;
                default:
                    break;
            }
        }
        return 0;
    }

    public void renderFrame(int i, Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(0, Mode.SRC);
        int prepareCanvasWithClosestCachedFrame = (!this.mAnimatedDrawableBackend.getFrameInfo(i).shouldBlendWithPreviousFrame || i <= 0) ? i : prepareCanvasWithClosestCachedFrame(i - 1, canvas);
        while (prepareCanvasWithClosestCachedFrame < i) {
            AnimatedDrawableFrameInfo frameInfo = this.mAnimatedDrawableBackend.getFrameInfo(prepareCanvasWithClosestCachedFrame);
            DisposalMethod disposalMethod = frameInfo.disposalMethod;
            if (disposalMethod != DisposalMethod.DISPOSE_TO_PREVIOUS) {
                this.mAnimatedDrawableBackend.renderFrame(prepareCanvasWithClosestCachedFrame, canvas);
                this.mCallback.onIntermediateResult(prepareCanvasWithClosestCachedFrame, bitmap);
                if (disposalMethod == DisposalMethod.DISPOSE_TO_BACKGROUND) {
                    disposeToBackground(canvas, frameInfo);
                }
            }
            prepareCanvasWithClosestCachedFrame++;
        }
        this.mAnimatedDrawableBackend.renderFrame(i, canvas);
    }
}
