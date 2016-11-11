package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.fimi.soul.view.j */
class C2001j implements OnClickListener {
    final /* synthetic */ C2000i f10843a;

    C2001j(C2000i c2000i) {
        this.f10843a = c2000i;
    }

    public void onClick(View view) {
        if (this.f10843a.f10838d != null) {
            this.f10843a.dismiss();
            this.f10843a.f10838d.m8386a(-2);
        }
    }
}
