package p147m.framework.ui.widget.pulltorefresh;

import android.widget.GridView;

/* renamed from: m.framework.ui.widget.pulltorefresh.i */
public abstract class C2884i extends C2883h {
    private ScrollableGridView f14666a;
    private C2879d f14667b;
    private boolean f14668c;
    private C2880e f14669d;

    public C2884i(PullToRefreshView pullToRefreshView) {
        super(pullToRefreshView);
        this.f14666a = new ScrollableGridView(m16603a());
        this.f14666a.setOnScrollListener(new C2885j(this));
        this.f14667b = new C2879d(this);
        this.f14666a.setAdapter(this.f14667b);
    }

    public void m16622a(C2875q c2875q, int i, int i2, int i3) {
    }

    public void m16623c() {
        super.m16606c();
        this.f14667b.notifyDataSetChanged();
    }

    public C2875q m16624e() {
        return this.f14666a;
    }

    public boolean m16625f() {
        return this.f14666a.m16584a();
    }

    public boolean m16626j() {
        return this.f14668c;
    }

    public GridView m16627k() {
        return this.f14666a;
    }
}
