package com.fimi.soul.view.cropimage;

/* renamed from: com.fimi.soul.view.cropimage.a */
class C1991a implements Runnable {
    final /* synthetic */ C1994d f10805a;
    final /* synthetic */ boolean f10806b;
    final /* synthetic */ CropViewBase f10807c;

    C1991a(CropViewBase cropViewBase, C1994d c1994d, boolean z) {
        this.f10807c = cropViewBase;
        this.f10805a = c1994d;
        this.f10806b = z;
    }

    public void run() {
        this.f10807c.m12833a(this.f10805a, this.f10806b);
    }
}
