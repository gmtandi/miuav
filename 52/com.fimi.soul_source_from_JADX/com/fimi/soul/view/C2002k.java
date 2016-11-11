package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.view.k */
class C2002k implements OnClickListener {
    final /* synthetic */ C2000i f10844a;

    C2002k(C2000i c2000i) {
        this.f10844a = c2000i;
    }

    public void onClick(View view) {
        if (this.f10844a.f10838d != null) {
            this.f10844a.dismiss();
            this.f10844a.f10838d.m8386a(-1);
        }
    }
}
