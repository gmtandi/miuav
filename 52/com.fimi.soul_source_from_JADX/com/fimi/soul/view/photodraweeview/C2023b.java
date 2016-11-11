package com.fimi.soul.view.photodraweeview;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

/* renamed from: com.fimi.soul.view.photodraweeview.b */
class C2023b extends SimpleOnGestureListener {
    final /* synthetic */ C2022a f10961a;

    C2023b(C2022a c2022a) {
        this.f10961a = c2022a;
    }

    public void onLongPress(MotionEvent motionEvent) {
        super.onLongPress(motionEvent);
        if (this.f10961a.f10941B != null) {
            this.f10961a.f10941B.onLongClick(this.f10961a.m12954a());
        }
    }
}
