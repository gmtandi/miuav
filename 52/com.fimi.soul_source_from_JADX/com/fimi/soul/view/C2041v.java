package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.view.v */
class C2041v implements OnClickListener {
    final /* synthetic */ C2017n f11084a;
    final /* synthetic */ C2040u f11085b;

    C2041v(C2040u c2040u, C2017n c2017n) {
        this.f11085b = c2040u;
        this.f11084a = c2017n;
    }

    public void onClick(View view) {
        this.f11085b.f11081j.onClick(this.f11084a, -1);
        this.f11084a.dismiss();
    }
}
