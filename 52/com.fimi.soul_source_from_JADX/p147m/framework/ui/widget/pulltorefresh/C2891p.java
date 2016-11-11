package p147m.framework.ui.widget.pulltorefresh;

/* renamed from: m.framework.ui.widget.pulltorefresh.p */
class C2891p implements Runnable {
    final /* synthetic */ PullToRefreshView f14688a;

    C2891p(PullToRefreshView pullToRefreshView) {
        this.f14688a = pullToRefreshView;
    }

    public void run() {
        this.f14688a.m16576g();
        this.f14688a.m16575f();
    }
}
