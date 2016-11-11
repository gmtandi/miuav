package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.view.w */
class C2042w implements OnClickListener {
    final /* synthetic */ C2017n f11086a;
    final /* synthetic */ C2040u f11087b;

    C2042w(C2040u c2040u, C2017n c2017n) {
        this.f11087b = c2040u;
        this.f11086a = c2017n;
    }

    public void onClick(View view) {
        this.f11087b.f11082k.onClick(this.f11086a, -2);
        this.f11086a.dismiss();
    }
}
