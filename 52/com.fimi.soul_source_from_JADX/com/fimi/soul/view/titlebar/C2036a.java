package com.fimi.soul.view.titlebar;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.view.titlebar.a */
class C2036a implements OnClickListener {
    final /* synthetic */ View f11064a;
    final /* synthetic */ View f11065b;
    final /* synthetic */ FmBottomBar f11066c;

    C2036a(FmBottomBar fmBottomBar, View view, View view2) {
        this.f11066c = fmBottomBar;
        this.f11064a = view;
        this.f11065b = view2;
    }

    public void onClick(View view) {
        this.f11066c.m13009a(this.f11064a, this.f11065b, true);
    }
}
