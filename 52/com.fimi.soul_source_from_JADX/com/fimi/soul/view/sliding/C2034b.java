package com.fimi.soul.view.sliding;

import android.view.View;

/* renamed from: com.fimi.soul.view.sliding.b */
class C2034b implements Runnable {
    final /* synthetic */ View f11032a;
    final /* synthetic */ FmSlidingTabView f11033b;

    C2034b(FmSlidingTabView fmSlidingTabView, View view) {
        this.f11033b = fmSlidingTabView;
        this.f11032a = view;
    }

    public void run() {
        this.f11033b.f11011k.smoothScrollTo(this.f11032a.getLeft() - ((this.f11033b.getWidth() - this.f11032a.getWidth()) / 2), 0);
        this.f11033b.f11003c = null;
    }
}
