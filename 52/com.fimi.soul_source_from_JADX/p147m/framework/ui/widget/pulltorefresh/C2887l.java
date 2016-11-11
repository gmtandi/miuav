package p147m.framework.ui.widget.pulltorefresh;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/* renamed from: m.framework.ui.widget.pulltorefresh.l */
class C2887l implements OnScrollListener {
    final /* synthetic */ C2886k f14677a;
    private int f14678b;
    private int f14679c;

    C2887l(C2886k c2886k) {
        this.f14677a = c2886k;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.f14678b = i;
        this.f14679c = i2;
        this.f14677a.m16635a(this.f14677a.f14673a, i, i2, i3);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.f14677a.f14675c = i == 2;
        if (i != 0) {
            return;
        }
        if (this.f14677a.f14676d != null) {
            this.f14677a.f14676d.m16601a(this.f14678b, this.f14679c);
        } else if (this.f14677a.f14674b != null) {
            this.f14677a.f14674b.m16600b();
        }
    }
}
