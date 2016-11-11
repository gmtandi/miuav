package com.facebook.imagepipeline.animated.impl;

import android.graphics.Canvas;
import android.graphics.Rect;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableCachingBackend;
import com.facebook.imagepipeline.animated.base.AnimatedDrawableDiagnostics;

public class AnimatedDrawableDiagnosticsNoop implements AnimatedDrawableDiagnostics {
    private static AnimatedDrawableDiagnosticsNoop sInstance;

    static {
        sInstance = new AnimatedDrawableDiagnosticsNoop();
    }

    public static AnimatedDrawableDiagnosticsNoop getInstance() {
        return sInstance;
    }

    public void drawDebugOverlay(Canvas canvas, Rect rect) {
    }

    public void incrementDrawnFrames(int i) {
    }

    public void incrementDroppedFrames(int i) {
    }

    public void onDrawMethodBegin() {
    }

    public void onDrawMethodEnd() {
    }

    public void onNextFrameMethodBegin() {
    }

    public void onNextFrameMethodEnd() {
    }

    public void onStartMethodBegin() {
    }

    public void onStartMethodEnd() {
    }

    public void setBackend(AnimatedDrawableCachingBackend animatedDrawableCachingBackend) {
    }
}
