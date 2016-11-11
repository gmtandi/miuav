package p147m.framework.ui.widget.pulltorefresh;

import android.widget.ListView;

/* renamed from: m.framework.ui.widget.pulltorefresh.n */
public abstract class C2889n extends C2883h {
    private ScrollableListView f14681a;
    private C2879d f14682b;
    private boolean f14683c;
    private C2880e f14684d;

    public C2889n(PullToRefreshView pullToRefreshView) {
        super(pullToRefreshView);
        this.f14681a = new ScrollableListView(m16603a());
        this.f14681a.setOnScrollListener(new C2890o(this));
        this.f14682b = new C2879d(this);
        this.f14681a.setAdapter(this.f14682b);
    }

    public void m16654a(C2875q c2875q, int i, int i2, int i3) {
    }

    public void m16655c() {
        super.m16606c();
        this.f14682b.notifyDataSetChanged();
    }

    public C2875q m16656e() {
        return this.f14681a;
    }

    public boolean m16657f() {
        return this.f14681a.m16590a();
    }

    public boolean m16658j() {
        return this.f14683c;
    }

    public ListView m16659k() {
        return this.f14681a;
    }
}
