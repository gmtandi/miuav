package it.sephiroth.android.library.widget;

/* renamed from: it.sephiroth.android.library.widget.t */
class C2824t {
    private int f14533a;
    final /* synthetic */ AbsHListView f14534c;

    private C2824t(AbsHListView absHListView) {
        this.f14534c = absHListView;
    }

    public void m16372a() {
        this.f14533a = this.f14534c.getWindowAttachCount();
    }

    public boolean m16373b() {
        return this.f14534c.hasWindowFocus() && this.f14534c.getWindowAttachCount() == this.f14533a;
    }
}
