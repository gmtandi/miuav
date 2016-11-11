package com.fimi.soul.view.cropimage;

/* renamed from: com.fimi.soul.view.cropimage.b */
class C1992b implements Runnable {
    final /* synthetic */ float f10808a;
    final /* synthetic */ long f10809b;
    final /* synthetic */ float f10810c;
    final /* synthetic */ float f10811d;
    final /* synthetic */ float f10812e;
    final /* synthetic */ float f10813f;
    final /* synthetic */ CropViewBase f10814g;

    C1992b(CropViewBase cropViewBase, float f, long j, float f2, float f3, float f4, float f5) {
        this.f10814g = cropViewBase;
        this.f10808a = f;
        this.f10809b = j;
        this.f10810c = f2;
        this.f10811d = f3;
        this.f10812e = f4;
        this.f10813f = f5;
    }

    public void run() {
        float min = Math.min(this.f10808a, (float) (System.currentTimeMillis() - this.f10809b));
        this.f10814g.m12830a(this.f10810c + (this.f10811d * min), this.f10812e, this.f10813f);
        if (min < this.f10808a) {
            this.f10814g.f10798k.post(this);
        }
    }
}
