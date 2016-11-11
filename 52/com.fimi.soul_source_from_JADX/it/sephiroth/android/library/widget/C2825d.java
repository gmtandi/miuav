package it.sephiroth.android.library.widget;

import android.view.View;

/* renamed from: it.sephiroth.android.library.widget.d */
class C2825d extends C2824t implements Runnable {
    final /* synthetic */ AbsHListView f14535a;

    private C2825d(AbsHListView absHListView) {
        this.f14535a = absHListView;
        super(null);
    }

    public void run() {
        if (this.f14535a.isPressed() && this.f14535a.aM >= 0) {
            View childAt = this.f14535a.getChildAt(this.f14535a.aM - this.f14535a.av);
            if (this.f14535a.aJ) {
                this.f14535a.setPressed(false);
                if (childAt != null) {
                    childAt.setPressed(false);
                    return;
                }
                return;
            }
            if (m16373b() ? this.f14535a.m16140c(childAt, this.f14535a.aM, this.f14535a.aN) : false) {
                this.f14535a.setPressed(false);
                childAt.setPressed(false);
            }
        }
    }
}
