package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.view.q */
class C2030q implements OnClickListener {
    final /* synthetic */ C2017n f10984a;
    final /* synthetic */ C2018o f10985b;

    C2030q(C2018o c2018o, C2017n c2017n) {
        this.f10985b = c2018o;
        this.f10984a = c2017n;
    }

    public void onClick(View view) {
        this.f10985b.f10927k.onClick(this.f10984a, -2);
        this.f10984a.dismiss();
    }
}
