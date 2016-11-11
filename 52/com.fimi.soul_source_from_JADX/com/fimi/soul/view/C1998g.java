package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/* renamed from: com.fimi.soul.view.g */
class C1998g implements OnClickListener {
    final /* synthetic */ CheckBox f10831a;
    final /* synthetic */ C1997f f10832b;

    C1998g(C1997f c1997f, CheckBox checkBox) {
        this.f10832b = c1997f;
        this.f10831a = checkBox;
    }

    public void onClick(View view) {
        if (this.f10832b.f10824c != null) {
            this.f10832b.f10824c.m8387a(-2, Boolean.valueOf(this.f10831a.isChecked()));
            this.f10832b.dismiss();
        }
    }
}
