package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

class aw implements OnClickListener {
    final /* synthetic */ ap f10707a;
    final /* synthetic */ at f10708b;

    aw(at atVar, ap apVar) {
        this.f10708b = atVar;
        this.f10707a = apVar;
    }

    public void onClick(View view) {
        this.f10707a.dismiss();
        this.f10708b.f10701m.onClick(this.f10707a, -2);
    }
}
