package p147m.framework.ui.widget.pulltorefresh;

import android.content.Context;
import android.view.View;

/* renamed from: m.framework.ui.widget.pulltorefresh.g */
public abstract class C2882g {
    private Context f14664a;
    private PullToRefreshView f14665b;

    public C2882g(PullToRefreshView pullToRefreshView) {
        this.f14664a = pullToRefreshView.getContext();
        this.f14665b = pullToRefreshView;
    }

    public Context m16603a() {
        return this.f14664a;
    }

    public abstract void m16604a(int i);

    protected PullToRefreshView m16605b() {
        return this.f14665b;
    }

    public void m16606c() {
        this.f14665b.m16578a();
    }

    public abstract View m16607d();

    public abstract C2875q m16608e();

    public abstract boolean m16609f();

    public abstract void m16610g();

    public void m16611h() {
    }
}
