package com.amap.api.mapcore;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* renamed from: com.amap.api.mapcore.j */
public class C0317j extends GLSurfaceView implements ae {
    private ab f1930a;

    public C0317j(Context context) {
        this(context, null);
    }

    public C0317j(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1930a = null;
        this.f1930a = new AMapDelegateImp(this, context, attributeSet);
    }

    public ab m3230a() {
        return this.f1930a;
    }

    protected void onDetachedFromWindow() {
        this.f1930a.m2300g();
        super.onDetachedFromWindow();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return this.f1930a.m2278a(motionEvent);
    }

    protected void onWindowVisibilityChanged(int i) {
        if (i == 8 || i == 4) {
            this.f1930a.m2300g();
        } else if (i == 0) {
            this.f1930a.m2297f();
        }
        super.onWindowVisibilityChanged(i);
    }
}
