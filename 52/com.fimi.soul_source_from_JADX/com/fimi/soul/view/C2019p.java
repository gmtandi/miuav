package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.view.p */
class C2019p implements OnClickListener {
    final /* synthetic */ C2017n f10929a;
    final /* synthetic */ C2018o f10930b;

    C2019p(C2018o c2018o, C2017n c2017n) {
        this.f10930b = c2018o;
        this.f10929a = c2017n;
    }

    public void onClick(View view) {
        this.f10930b.f10926j.onClick(this.f10929a, -1);
        this.f10929a.dismiss();
    }
}
