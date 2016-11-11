package com.facebook.imagepipeline.animated.impl;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableCachingBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableDiagnostics;
import com.facebook.imagepipeline.animated.util.AnimatedDrawableUtil;

public class AnimatedDrawableDiagnosticsImpl implements AnimatedDrawableDiagnostics {
    private static final Class<?> TAG;
    private AnimatedDrawableCachingBackend mAnimatedDrawableBackend;
    private final AnimatedDrawableUtil mAnimatedDrawableUtil;
    private final TextPaint mDebugTextPaint;
    private final DisplayMetrics mDisplayMetrics;
    private final RollingStat mDrawnFrames;
    private final RollingStat mDroppedFramesStat;
    private long mLastTimeStamp;
    private final StringBuilder sbTemp;

    static {
        TAG = AnimatedDrawableDiagnostics.class;
    }

    public AnimatedDrawableDiagnosticsImpl(AnimatedDrawableUtil animatedDrawableUtil, DisplayMetrics displayMetrics) {
        this.mAnimatedDrawableUtil = animatedDrawableUtil;
        this.mDisplayMetrics = displayMetrics;
        this.mDroppedFramesStat = new RollingStat();
        this.mDrawnFrames = new RollingStat();
        this.sbTemp = new StringBuilder();
        this.mDebugTextPaint = new TextPaint();
        this.mDebugTextPaint.setColor(-16776961);
        this.mDebugTextPaint.setTextSize((float) convertDpToPx(14));
    }

    private int convertDpToPx(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, this.mDisplayMetrics);
    }

    public void drawDebugOverlay(Canvas canvas, Rect rect) {
        int i;
        int sum = this.mDroppedFramesStat.getSum(10);
        int sum2 = this.mDrawnFrames.getSum(10);
        sum += sum2;
        int convertDpToPx = convertDpToPx(10);
        int convertDpToPx2 = convertDpToPx(20);
        int convertDpToPx3 = convertDpToPx(5);
        if (sum > 0) {
            sum = (sum2 * 100) / sum;
            this.sbTemp.setLength(0);
            this.sbTemp.append(sum);
            this.sbTemp.append("%");
            canvas.drawText(this.sbTemp, 0, this.sbTemp.length(), (float) convertDpToPx, (float) convertDpToPx2, this.mDebugTextPaint);
            sum = ((int) (((float) convertDpToPx) + this.mDebugTextPaint.measureText(this.sbTemp, 0, this.sbTemp.length()))) + convertDpToPx3;
        } else {
            sum = convertDpToPx;
        }
        sum2 = this.mAnimatedDrawableBackend.getMemoryUsage();
        this.sbTemp.setLength(0);
        this.mAnimatedDrawableUtil.appendMemoryString(this.sbTemp, sum2);
        float measureText = this.mDebugTextPaint.measureText(this.sbTemp, 0, this.sbTemp.length());
        if (((float) sum) + measureText > ((float) rect.width())) {
            convertDpToPx2 = (int) (((float) convertDpToPx2) + (this.mDebugTextPaint.getTextSize() + ((float) convertDpToPx3)));
            i = convertDpToPx;
        } else {
            i = sum;
        }
        canvas.drawText(this.sbTemp, 0, this.sbTemp.length(), (float) i, (float) convertDpToPx2, this.mDebugTextPaint);
        sum = ((int) (((float) i) + measureText)) + convertDpToPx3;
        this.sbTemp.setLength(0);
        this.mAnimatedDrawableBackend.appendDebugOptionString(this.sbTemp);
        if (this.mDebugTextPaint.measureText(this.sbTemp, 0, this.sbTemp.length()) + ((float) sum) > ((float) rect.width())) {
            convertDpToPx2 = (int) (((float) convertDpToPx2) + (this.mDebugTextPaint.getTextSize() + ((float) convertDpToPx3)));
        } else {
            convertDpToPx = sum;
        }
        canvas.drawText(this.sbTemp, 0, this.sbTemp.length(), (float) convertDpToPx, (float) convertDpToPx2, this.mDebugTextPaint);
    }

    public void incrementDrawnFrames(int i) {
        this.mDrawnFrames.incrementStats(i);
    }

    public void incrementDroppedFrames(int i) {
        this.mDroppedFramesStat.incrementStats(i);
        if (i > 0) {
            FLog.m7618v(TAG, "Dropped %d frames", Integer.valueOf(i));
        }
    }

    public void onDrawMethodBegin() {
        this.mLastTimeStamp = SystemClock.elapsedRealtime();
    }

    public void onDrawMethodEnd() {
        FLog.m7618v(TAG, "draw took %d", Long.valueOf(SystemClock.elapsedRealtime() - this.mLastTimeStamp));
    }

    public void onNextFrameMethodBegin() {
        this.mLastTimeStamp = SystemClock.elapsedRealtime();
    }

    public void onNextFrameMethodEnd() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.mLastTimeStamp;
        if (elapsedRealtime > 3) {
            FLog.m7618v(TAG, "onNextFrame took %d", Long.valueOf(elapsedRealtime));
        }
    }

    public void onStartMethodBegin() {
        this.mLastTimeStamp = SystemClock.elapsedRealtime();
    }

    public void onStartMethodEnd() {
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.mLastTimeStamp;
        if (elapsedRealtime > 3) {
            FLog.m7618v(TAG, "onStart took %d", Long.valueOf(elapsedRealtime));
        }
    }

    public void setBackend(AnimatedDrawableCachingBackend animatedDrawableCachingBackend) {
        this.mAnimatedDrawableBackend = animatedDrawableCachingBackend;
    }
}
