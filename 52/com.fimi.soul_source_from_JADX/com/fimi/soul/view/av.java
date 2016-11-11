package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

class av implements OnClickListener {
    final /* synthetic */ ap f10705a;
    final /* synthetic */ at f10706b;

    av(at atVar, ap apVar) {
        this.f10706b = atVar;
        this.f10705a = apVar;
    }

    public void onClick(View view) {
        this.f10705a.dismiss();
        this.f10706b.f10700l.onClick(this.f10705a, -2);
    }
}
