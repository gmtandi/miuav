package com.amap.api.mapcore;

import javax.microedition.khronos.opengles.GL10;

/* renamed from: com.amap.api.mapcore.v */
public abstract class C0408v {
    private ab f2546a;

    public void destroy() {
        if (this.f2546a != null) {
            this.f2546a.m2256a(this);
        }
    }

    public abstract int getZIndex();

    public abstract void onDrawFrame(GL10 gl10);
}
