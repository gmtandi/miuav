package com.fimi.soul.view.photodraweeview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import android.view.View.OnLongClickListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

public class PhotoDraweeView extends SimpleDraweeView implements C2020f {
    private C2022a f10935e;

    public PhotoDraweeView(Context context) {
        super(context);
        m12931a();
    }

    public PhotoDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m12931a();
    }

    public PhotoDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12931a();
    }

    public PhotoDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context, genericDraweeHierarchy);
        m12931a();
    }

    protected void m12931a() {
        if (this.f10935e == null || this.f10935e.m12954a() == null) {
            this.f10935e = new C2022a(this);
        }
    }

    public void m12932a(float f, float f2, float f3, boolean z) {
        this.f10935e.m12958a(f, f2, f3, z);
    }

    public void m12933a(float f, boolean z) {
        this.f10935e.m12959a(f, z);
    }

    public void m12934a(int i, int i2) {
        this.f10935e.m12960a(i, i2);
    }

    public float getMaximumScale() {
        return this.f10935e.getMaximumScale();
    }

    public float getMediumScale() {
        return this.f10935e.getMediumScale();
    }

    public float getMinimumScale() {
        return this.f10935e.getMinimumScale();
    }

    public C1587g getOnPhotoTapListener() {
        return this.f10935e.getOnPhotoTapListener();
    }

    public C2028j getOnViewTapListener() {
        return this.f10935e.getOnViewTapListener();
    }

    public float getScale() {
        return this.f10935e.getScale();
    }

    protected void onAttachedToWindow() {
        m12931a();
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        this.f10935e.m12966g();
        super.onDetachedFromWindow();
    }

    protected void onDraw(@NonNull Canvas canvas) {
        int save = canvas.save();
        canvas.concat(this.f10935e.m12961b());
        super.onDraw(canvas);
        canvas.restoreToCount(save);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.f10935e.setAllowParentInterceptOnEdge(z);
    }

    public void setMaximumScale(float f) {
        this.f10935e.setMaximumScale(f);
    }

    public void setMediumScale(float f) {
        this.f10935e.setMediumScale(f);
    }

    public void setMinimumScale(float f) {
        this.f10935e.setMinimumScale(f);
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.f10935e.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.f10935e.setOnLongClickListener(onLongClickListener);
    }

    public void setOnPhotoTapListener(C1587g c1587g) {
        this.f10935e.setOnPhotoTapListener(c1587g);
    }

    public void setOnScaleChangeListener(C2027h c2027h) {
        this.f10935e.setOnScaleChangeListener(c2027h);
    }

    public void setOnViewTapListener(C2028j c2028j) {
        this.f10935e.setOnViewTapListener(c2028j);
    }

    public void setScale(float f) {
        this.f10935e.setScale(f);
    }

    public void setZoomTransitionDuration(long j) {
        this.f10935e.setZoomTransitionDuration(j);
    }
}
