package com.amap.api.mapcore.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.amap.api.maps.model.GroundOverlayOptions;

/* renamed from: com.amap.api.mapcore.util.e */
public abstract class C0374e extends C0350b {
    protected float f2394h;
    protected float f2395i;
    protected float f2396j;
    protected float f2397k;
    private final float f2398l;
    private float f2399m;
    private float f2400n;
    private float f2401o;
    private float f2402p;

    public C0374e(Context context) {
        super(context);
        this.f2398l = (float) ViewConfiguration.get(context).getScaledEdgeSlop();
    }

    protected static float m3989a(MotionEvent motionEvent, int i) {
        return i < motionEvent.getPointerCount() ? (motionEvent.getRawX() - motionEvent.getX()) + motionEvent.getX(i) : 0.0f;
    }

    protected static float m3990b(MotionEvent motionEvent, int i) {
        return i < motionEvent.getPointerCount() ? (motionEvent.getRawY() - motionEvent.getY()) + motionEvent.getY(i) : 0.0f;
    }

    protected void m3991b(MotionEvent motionEvent) {
        super.m3509b(motionEvent);
        if (this.c != null) {
            MotionEvent motionEvent2 = this.c;
            this.f2401o = GroundOverlayOptions.NO_DIMENSION;
            this.f2402p = GroundOverlayOptions.NO_DIMENSION;
            float x = motionEvent2.getX(0);
            float y = motionEvent2.getY(0);
            float x2 = motionEvent2.getX(1);
            float y2 = motionEvent2.getY(1) - y;
            this.f2394h = x2 - x;
            this.f2395i = y2;
            y2 = motionEvent.getX(0);
            x = motionEvent.getY(0);
            x = motionEvent.getY(1) - x;
            this.f2396j = motionEvent.getX(1) - y2;
            this.f2397k = x;
        }
    }

    public float m3992c() {
        if (this.f2401o == GroundOverlayOptions.NO_DIMENSION) {
            float f = this.f2396j;
            float f2 = this.f2397k;
            this.f2401o = (float) Math.sqrt((double) ((f * f) + (f2 * f2)));
        }
        return this.f2401o;
    }

    protected boolean m3993d(MotionEvent motionEvent) {
        DisplayMetrics displayMetrics = this.a.getResources().getDisplayMetrics();
        this.f2399m = ((float) displayMetrics.widthPixels) - this.f2398l;
        this.f2400n = ((float) displayMetrics.heightPixels) - this.f2398l;
        float f = this.f2398l;
        float f2 = this.f2399m;
        float f3 = this.f2400n;
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        float a = C0374e.m3989a(motionEvent, 1);
        float b = C0374e.m3990b(motionEvent, 1);
        boolean z = rawX < f || rawY < f || rawX > f2 || rawY > f3;
        boolean z2 = a < f || b < f || a > f2 || b > f3;
        return z || z2;
    }
}
