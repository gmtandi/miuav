package it.sephiroth.android.library.widget;

import android.view.View;

/* renamed from: it.sephiroth.android.library.widget.a */
class C2820a implements Runnable {
    final /* synthetic */ View f14404a;
    final /* synthetic */ C2832k f14405b;
    final /* synthetic */ AbsHListView f14406c;

    C2820a(AbsHListView absHListView, View view, C2832k c2832k) {
        this.f14406c = absHListView;
        this.f14404a = view;
        this.f14405b = c2832k;
    }

    public void run() {
        this.f14406c.f14384Z = -1;
        this.f14404a.setPressed(false);
        this.f14406c.setPressed(false);
        if (!this.f14406c.aJ) {
            this.f14405b.run();
        }
    }
}
