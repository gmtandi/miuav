package com.fimi.soul.view.sliding;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.view.sliding.a */
class C2033a implements OnClickListener {
    final /* synthetic */ FmSlidingTabView f11031a;

    C2033a(FmSlidingTabView fmSlidingTabView) {
        this.f11031a = fmSlidingTabView;
    }

    public void onClick(View view) {
        this.f11031a.f11012l.setCurrentItem(((FmTabItemView) view).getIndex());
    }
}
