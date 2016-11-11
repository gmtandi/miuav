package com.fimi.soul.view.photodraweeview;

import android.content.Context;
import android.graphics.RectF;
import android.support.v4.widget.ScrollerCompat;
import android.view.View;

/* renamed from: com.fimi.soul.view.photodraweeview.d */
class C2025d implements Runnable {
    final /* synthetic */ C2022a f10968a;
    private final ScrollerCompat f10969b;
    private int f10970c;
    private int f10971d;

    public C2025d(C2022a c2022a, Context context) {
        this.f10968a = c2022a;
        this.f10969b = ScrollerCompat.create(context);
    }

    public void m12968a() {
        this.f10969b.abortAnimation();
    }

    public void m12969a(int i, int i2, int i3, int i4) {
        RectF c = this.f10968a.m12962c();
        if (c != null) {
            int round;
            int i5;
            int round2;
            int i6;
            int round3 = Math.round(-c.left);
            if (((float) i) < c.width()) {
                round = Math.round(c.width() - ((float) i));
                i5 = 0;
            } else {
                round = round3;
                i5 = round3;
            }
            int round4 = Math.round(-c.top);
            if (((float) i2) < c.height()) {
                round2 = Math.round(c.height() - ((float) i2));
                i6 = 0;
            } else {
                round2 = round4;
                i6 = round4;
            }
            this.f10970c = round3;
            this.f10971d = round4;
            if (round3 != round || round4 != round2) {
                this.f10969b.fling(round3, round4, i3, i4, i5, round, i6, round2, 0, 0);
            }
        }
    }

    public void run() {
        if (!this.f10969b.isFinished()) {
            View a = this.f10968a.m12954a();
            if (a != null && this.f10969b.computeScrollOffset()) {
                int currX = this.f10969b.getCurrX();
                int currY = this.f10969b.getCurrY();
                this.f10968a.f10955u.postTranslate((float) (this.f10970c - currX), (float) (this.f10971d - currY));
                a.invalidate();
                this.f10970c = currX;
                this.f10971d = currY;
                this.f10968a.m12942a(a, (Runnable) this);
            }
        }
    }
}
