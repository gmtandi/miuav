package com.facebook.imagepipeline.animated.base;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface AnimatedDrawableDiagnostics {
    void drawDebugOverlay(Canvas canvas, Rect rect);

    void incrementDrawnFrames(int i);

    void incrementDroppedFrames(int i);

    void onDrawMethodBegin();

    void onDrawMethodEnd();

    void onNextFrameMethodBegin();

    void onNextFrameMethodEnd();

    void onStartMethodBegin();

    void onStartMethodEnd();

    void setBackend(AnimatedDrawableCachingBackend animatedDrawableCachingBackend);
}
