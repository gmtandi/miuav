package com.fimi.soul.view.titlebar;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.view.titlebar.d */
class C2039d implements OnClickListener {
    final /* synthetic */ View f11070a;
    final /* synthetic */ FmTitleBar f11071b;

    C2039d(FmTitleBar fmTitleBar, View view) {
        this.f11071b = fmTitleBar;
        this.f11070a = view;
    }

    public void onClick(View view) {
        this.f11071b.m13021a(this.f11071b.f11044c, this.f11070a, true);
    }
}
