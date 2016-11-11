package it.sephiroth.android.library.widget;

import android.view.View;
import android.widget.ListAdapter;

/* renamed from: it.sephiroth.android.library.widget.k */
class C2832k extends C2824t implements Runnable {
    int f14548a;
    final /* synthetic */ AbsHListView f14549b;

    private C2832k(AbsHListView absHListView) {
        this.f14549b = absHListView;
        super(null);
    }

    public void run() {
        if (!this.f14549b.aJ) {
            ListAdapter listAdapter = this.f14549b.f14362B;
            int i = this.f14548a;
            if (listAdapter != null && this.f14549b.aO > 0 && i != -1 && i < listAdapter.getCount() && m16373b()) {
                View childAt = this.f14549b.getChildAt(i - this.f14549b.av);
                if (childAt != null) {
                    this.f14549b.m16132a(childAt, i, listAdapter.getItemId(i));
                }
            }
        }
    }
}
