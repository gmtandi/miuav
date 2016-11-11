package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/* renamed from: com.fimi.soul.view.h */
class C1999h implements OnClickListener {
    final /* synthetic */ CheckBox f10833a;
    final /* synthetic */ C1997f f10834b;

    C1999h(C1997f c1997f, CheckBox checkBox) {
        this.f10834b = c1997f;
        this.f10833a = checkBox;
    }

    public void onClick(View view) {
        if (this.f10834b.f10824c != null) {
            this.f10834b.f10824c.m8387a(-1, Boolean.valueOf(this.f10833a.isChecked()));
            this.f10834b.dismiss();
        }
    }
}
