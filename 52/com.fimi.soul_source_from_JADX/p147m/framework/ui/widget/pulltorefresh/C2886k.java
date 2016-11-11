package p147m.framework.ui.widget.pulltorefresh;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: m.framework.ui.widget.pulltorefresh.k */
public abstract class C2886k extends C2882g {
    private ScrollableGroupListView f14673a;
    private C2877b f14674b;
    private boolean f14675c;
    private C2880e f14676d;

    public C2886k(PullToRefreshView pullToRefreshView) {
        super(pullToRefreshView);
        this.f14673a = new ScrollableGroupListView(m16603a());
        this.f14673a.setOnScrollListener(new C2887l(this));
        this.f14674b = new C2888m(this);
        this.f14673a.setAdapter(this.f14674b);
    }

    public abstract View m16632a(int i, int i2, View view, ViewGroup viewGroup);

    public abstract View m16633a(int i, View view, ViewGroup viewGroup);

    public abstract Object m16634a(int i, int i2);

    public void m16635a(C2875q c2875q, int i, int i2, int i3) {
    }

    public abstract String m16636b(int i);

    public abstract int m16637c(int i);

    public void m16638c() {
        super.m16606c();
        this.f14674b.m16600b();
    }

    public C2875q m16639e() {
        return this.f14673a;
    }

    public boolean m16640f() {
        return this.f14673a.m16587a();
    }

    public abstract int m16641i();

    public GroupListView m16642j() {
        return this.f14673a;
    }

    public boolean m16643k() {
        return this.f14675c;
    }
}
