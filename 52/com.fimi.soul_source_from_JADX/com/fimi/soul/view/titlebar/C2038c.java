package com.fimi.soul.view.titlebar;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/* renamed from: com.fimi.soul.view.titlebar.c */
class C2038c implements OnItemClickListener {
    final /* synthetic */ OnItemClickListener f11068a;
    final /* synthetic */ FmTitleBar f11069b;

    C2038c(FmTitleBar fmTitleBar, OnItemClickListener onItemClickListener) {
        this.f11069b = fmTitleBar;
        this.f11068a = onItemClickListener;
    }

    @SuppressLint({"NewApi"})
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f11069b.f11059r.dismiss();
        this.f11068a.onItemClick(adapterView, view, i, j);
    }
}
