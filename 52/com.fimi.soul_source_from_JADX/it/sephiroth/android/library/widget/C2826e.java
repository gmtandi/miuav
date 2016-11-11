package it.sephiroth.android.library.widget;

import android.view.View;

/* renamed from: it.sephiroth.android.library.widget.e */
class C2826e extends C2824t implements Runnable {
    final /* synthetic */ AbsHListView f14536a;

    private C2826e(AbsHListView absHListView) {
        this.f14536a = absHListView;
        super(null);
    }

    public void run() {
        View childAt = this.f14536a.getChildAt(this.f14536a.f14379S - this.f14536a.av);
        if (childAt != null) {
            boolean c = (!m16373b() || this.f14536a.aJ) ? false : this.f14536a.m16140c(childAt, this.f14536a.f14379S, this.f14536a.f14362B.getItemId(this.f14536a.f14379S));
            if (c) {
                this.f14536a.f14384Z = -1;
                this.f14536a.setPressed(false);
                childAt.setPressed(false);
                return;
            }
            this.f14536a.f14384Z = 2;
        }
    }
}
