package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

class af implements OnClickListener {
    final /* synthetic */ ac f10664a;
    final /* synthetic */ ad f10665b;

    af(ad adVar, ac acVar) {
        this.f10665b = adVar;
        this.f10664a = acVar;
    }

    public void onClick(View view) {
        this.f10664a.dismiss();
        this.f10665b.f10658d.onClick(this.f10664a, -2);
    }
}
