package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

class ba implements OnClickListener {
    final /* synthetic */ ap f10721a;
    final /* synthetic */ az f10722b;

    ba(az azVar, ap apVar) {
        this.f10722b = azVar;
        this.f10721a = apVar;
    }

    public void onClick(View view) {
        this.f10721a.dismiss();
        this.f10722b.f10719e.onClick(this.f10721a, -2);
    }
}
