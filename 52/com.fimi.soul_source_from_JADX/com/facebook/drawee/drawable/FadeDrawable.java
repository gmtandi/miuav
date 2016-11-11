package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.Arrays;
import org.codehaus.jackson.smile.SmileConstants;

public class FadeDrawable extends ArrayDrawable {
    @VisibleForTesting
    public static final int TRANSITION_NONE = 2;
    @VisibleForTesting
    public static final int TRANSITION_RUNNING = 1;
    @VisibleForTesting
    public static final int TRANSITION_STARTING = 0;
    @VisibleForTesting
    int mAlpha;
    @VisibleForTesting
    int[] mAlphas;
    @VisibleForTesting
    int mDurationMs;
    @VisibleForTesting
    boolean[] mIsLayerOn;
    private final Drawable[] mLayers;
    @VisibleForTesting
    int mPreventInvalidateCount;
    @VisibleForTesting
    int[] mStartAlphas;
    @VisibleForTesting
    long mStartTimeMs;
    @VisibleForTesting
    int mTransitionState;

    public FadeDrawable(Drawable[] drawableArr) {
        boolean z = true;
        super(drawableArr);
        if (drawableArr.length < TRANSITION_RUNNING) {
            z = false;
        }
        Preconditions.checkState(z, "At least one layer required!");
        this.mLayers = drawableArr;
        this.mStartAlphas = new int[drawableArr.length];
        this.mAlphas = new int[drawableArr.length];
        this.mAlpha = Util.MASK_8BIT;
        this.mIsLayerOn = new boolean[drawableArr.length];
        this.mPreventInvalidateCount = 0;
        resetInternal();
    }

    private void drawDrawableWithAlpha(Canvas canvas, Drawable drawable, int i) {
        if (drawable != null && i > 0) {
            this.mPreventInvalidateCount += TRANSITION_RUNNING;
            drawable.mutate().setAlpha(i);
            this.mPreventInvalidateCount--;
            drawable.draw(canvas);
        }
    }

    private void resetInternal() {
        this.mTransitionState = TRANSITION_NONE;
        Arrays.fill(this.mStartAlphas, 0);
        this.mStartAlphas[0] = Util.MASK_8BIT;
        Arrays.fill(this.mAlphas, 0);
        this.mAlphas[0] = Util.MASK_8BIT;
        Arrays.fill(this.mIsLayerOn, false);
        this.mIsLayerOn[0] = true;
    }

    private boolean updateAlphas(float f) {
        int i = 0;
        boolean z = true;
        while (i < this.mLayers.length) {
            this.mAlphas[i] = (int) ((((float) ((this.mIsLayerOn[i] ? TRANSITION_RUNNING : -1) * Util.MASK_8BIT)) * f) + ((float) this.mStartAlphas[i]));
            if (this.mAlphas[i] < 0) {
                this.mAlphas[i] = 0;
            }
            if (this.mAlphas[i] > 255) {
                this.mAlphas[i] = Util.MASK_8BIT;
            }
            if (this.mIsLayerOn[i] && this.mAlphas[i] < 255) {
                z = false;
            }
            if (!this.mIsLayerOn[i] && this.mAlphas[i] > 0) {
                z = false;
            }
            i += TRANSITION_RUNNING;
        }
        return z;
    }

    public void beginBatchMode() {
        this.mPreventInvalidateCount += TRANSITION_RUNNING;
    }

    public void draw(Canvas canvas) {
        int i = TRANSITION_NONE;
        int i2 = 0;
        boolean z = true;
        switch (this.mTransitionState) {
            case SmileConstants.TOKEN_PREFIX_SHARED_STRING_SHORT /*0*/:
                System.arraycopy(this.mAlphas, 0, this.mStartAlphas, 0, this.mLayers.length);
                this.mStartTimeMs = getCurrentTimeMs();
                boolean updateAlphas = updateAlphas(this.mDurationMs == 0 ? C2020f.f10933c : 0.0f);
                this.mTransitionState = updateAlphas ? TRANSITION_NONE : TRANSITION_RUNNING;
                z = updateAlphas;
                break;
            case TRANSITION_RUNNING /*1*/:
                Preconditions.checkState(this.mDurationMs > 0);
                boolean updateAlphas2 = updateAlphas(((float) (getCurrentTimeMs() - this.mStartTimeMs)) / ((float) this.mDurationMs));
                if (!updateAlphas2) {
                    i = TRANSITION_RUNNING;
                }
                this.mTransitionState = i;
                z = updateAlphas2;
                break;
        }
        while (i2 < this.mLayers.length) {
            drawDrawableWithAlpha(canvas, this.mLayers[i2], (this.mAlphas[i2] * this.mAlpha) / Util.MASK_8BIT);
            i2 += TRANSITION_RUNNING;
        }
        if (!z) {
            invalidateSelf();
        }
    }

    public void endBatchMode() {
        this.mPreventInvalidateCount--;
        invalidateSelf();
    }

    public void fadeInAllLayers() {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, true);
        invalidateSelf();
    }

    public void fadeInLayer(int i) {
        this.mTransitionState = 0;
        this.mIsLayerOn[i] = true;
        invalidateSelf();
    }

    public void fadeOutAllLayers() {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, false);
        invalidateSelf();
    }

    public void fadeOutLayer(int i) {
        this.mTransitionState = 0;
        this.mIsLayerOn[i] = false;
        invalidateSelf();
    }

    public void fadeToLayer(int i) {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, false);
        this.mIsLayerOn[i] = true;
        invalidateSelf();
    }

    public void fadeUpToLayer(int i) {
        this.mTransitionState = 0;
        Arrays.fill(this.mIsLayerOn, 0, i + TRANSITION_RUNNING, true);
        Arrays.fill(this.mIsLayerOn, i + TRANSITION_RUNNING, this.mLayers.length, false);
        invalidateSelf();
    }

    public void finishTransitionImmediately() {
        this.mTransitionState = TRANSITION_NONE;
        for (int i = 0; i < this.mLayers.length; i += TRANSITION_RUNNING) {
            this.mAlphas[i] = this.mIsLayerOn[i] ? Util.MASK_8BIT : 0;
        }
        invalidateSelf();
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    protected long getCurrentTimeMs() {
        return SystemClock.uptimeMillis();
    }

    public int getTransitionDuration() {
        return this.mDurationMs;
    }

    @VisibleForTesting
    public int getTransitionState() {
        return this.mTransitionState;
    }

    public void invalidateSelf() {
        if (this.mPreventInvalidateCount == 0) {
            super.invalidateSelf();
        }
    }

    public boolean isLayerOn(int i) {
        return this.mIsLayerOn[i];
    }

    public void reset() {
        resetInternal();
        invalidateSelf();
    }

    public void setAlpha(int i) {
        if (this.mAlpha != i) {
            this.mAlpha = i;
            invalidateSelf();
        }
    }

    public void setTransitionDuration(int i) {
        this.mDurationMs = i;
        if (this.mTransitionState == TRANSITION_RUNNING) {
            this.mTransitionState = 0;
        }
    }
}
