package p147m.framework.ui.widget.pulltorefresh;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/* renamed from: m.framework.ui.widget.pulltorefresh.o */
class C2890o implements OnScrollListener {
    final /* synthetic */ C2889n f14685a;
    private int f14686b;
    private int f14687c;

    C2890o(C2889n c2889n) {
        this.f14685a = c2889n;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.f14686b = i;
        this.f14687c = i2;
        this.f14685a.m16654a(this.f14685a.f14681a, i, i2, i3);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.f14685a.f14683c = i == 2;
        if (i != 0) {
            return;
        }
        if (this.f14685a.f14684d != null) {
            this.f14685a.f14684d.m16601a(this.f14686b, this.f14687c);
        } else if (this.f14685a.f14682b != null) {
            this.f14685a.f14682b.notifyDataSetChanged();
        }
    }
}
