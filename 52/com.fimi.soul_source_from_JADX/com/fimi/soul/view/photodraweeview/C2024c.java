package com.fimi.soul.view.photodraweeview;

import android.view.View;

/* renamed from: com.fimi.soul.view.photodraweeview.c */
class C2024c implements Runnable {
    final /* synthetic */ C2022a f10962a;
    private final float f10963b;
    private final float f10964c;
    private final long f10965d;
    private final float f10966e;
    private final float f10967f;

    public C2024c(C2022a c2022a, float f, float f2, float f3, float f4) {
        this.f10962a = c2022a;
        this.f10963b = f3;
        this.f10964c = f4;
        this.f10965d = System.currentTimeMillis();
        this.f10966e = f;
        this.f10967f = f2;
    }

    private float m12967a() {
        return this.f10962a.f10945k.getInterpolation(Math.min(C2020f.f10933c, (((float) (System.currentTimeMillis() - this.f10965d)) * C2020f.f10933c) / ((float) this.f10962a.f10949o)));
    }

    public void run() {
        View a = this.f10962a.m12954a();
        if (a != null) {
            float a2 = m12967a();
            this.f10962a.m12956a((this.f10966e + ((this.f10967f - this.f10966e) * a2)) / this.f10962a.getScale(), this.f10963b, this.f10964c);
            if (a2 < C2020f.f10933c) {
                this.f10962a.m12942a(a, (Runnable) this);
            }
        }
    }
}
