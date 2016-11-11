package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnFocusChangeListener;

class aa implements OnFocusChangeListener {
    final /* synthetic */ C2045z f10653a;

    aa(C2045z c2045z) {
        this.f10653a = c2045z;
    }

    public void onFocusChange(View view, boolean z) {
        if (!z) {
            this.f10653a.dismiss();
        }
    }
}
