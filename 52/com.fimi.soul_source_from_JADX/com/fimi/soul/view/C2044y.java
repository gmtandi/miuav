package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.view.y */
class C2044y implements OnClickListener {
    final /* synthetic */ C2017n f11096a;
    final /* synthetic */ C2043x f11097b;

    C2044y(C2043x c2043x, C2017n c2017n) {
        this.f11097b = c2043x;
        this.f11096a = c2017n;
    }

    public void onClick(View view) {
        this.f11097b.f11094g.onClick(this.f11096a, -1);
        this.f11096a.dismiss();
    }
}
