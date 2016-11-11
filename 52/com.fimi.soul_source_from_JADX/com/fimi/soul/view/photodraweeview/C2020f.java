package com.fimi.soul.view.photodraweeview;

import android.view.GestureDetector.OnDoubleTapListener;
import android.view.View.OnLongClickListener;

/* renamed from: com.fimi.soul.view.photodraweeview.f */
public interface C2020f {
    public static final float f10931a = 3.0f;
    public static final float f10932b = 1.75f;
    public static final float f10933c = 1.0f;
    public static final long f10934d = 200;

    void m12928a(float f, float f2, float f3, boolean z);

    void m12929a(float f, boolean z);

    void m12930a(int i, int i2);

    float getMaximumScale();

    float getMediumScale();

    float getMinimumScale();

    C1587g getOnPhotoTapListener();

    C2028j getOnViewTapListener();

    float getScale();

    void setAllowParentInterceptOnEdge(boolean z);

    void setMaximumScale(float f);

    void setMediumScale(float f);

    void setMinimumScale(float f);

    void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener);

    void setOnLongClickListener(OnLongClickListener onLongClickListener);

    void setOnPhotoTapListener(C1587g c1587g);

    void setOnScaleChangeListener(C2027h c2027h);

    void setOnViewTapListener(C2028j c2028j);

    void setScale(float f);

    void setZoomTransitionDuration(long j);
}
