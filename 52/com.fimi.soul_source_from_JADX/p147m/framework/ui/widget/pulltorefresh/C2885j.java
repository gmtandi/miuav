package p147m.framework.ui.widget.pulltorefresh;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/* renamed from: m.framework.ui.widget.pulltorefresh.j */
class C2885j implements OnScrollListener {
    final /* synthetic */ C2884i f14670a;
    private int f14671b;
    private int f14672c;

    C2885j(C2884i c2884i) {
        this.f14670a = c2884i;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.f14671b = i;
        this.f14672c = i2;
        this.f14670a.m16622a(this.f14670a.f14666a, i, i2, i3);
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.f14670a.f14668c = i == 2;
        if (i != 0) {
            return;
        }
        if (this.f14670a.f14669d != null) {
            this.f14670a.f14669d.m16601a(this.f14671b, this.f14672c);
        } else if (this.f14670a.f14667b != null) {
            this.f14670a.f14667b.notifyDataSetChanged();
        }
    }
}
