package com.amap.api.mapcore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* renamed from: com.amap.api.mapcore.k */
public class C0319k extends C0318y implements ae {
    private ab f1943a;

    public C0319k(Context context) {
        this(context, null);
    }

    public C0319k(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1943a = null;
        this.f1943a = new AMapDelegateImp(this, context, attributeSet);
    }

    public ab m3244a() {
        return this.f1943a;
    }

    protected void onDetachedFromWindow() {
        this.f1943a.m2300g();
        super.onDetachedFromWindow();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return this.f1943a.m2278a(motionEvent);
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 8 || i == 4) {
            this.f1943a.m2300g();
        } else if (i == 0) {
            this.f1943a.m2297f();
        }
    }

    public void setZOrderOnTop(boolean z) {
    }
}
