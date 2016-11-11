package it.sephiroth.android.library.widget;

/* renamed from: it.sephiroth.android.library.widget.b */
class C2821b implements Runnable {
    final /* synthetic */ AbsHListView f14529a;

    C2821b(AbsHListView absHListView) {
        this.f14529a = absHListView;
    }

    public void run() {
        if (this.f14529a.f14377Q) {
            AbsHListView absHListView = this.f14529a;
            this.f14529a.f14378R = false;
            absHListView.f14377Q = false;
            this.f14529a.setChildrenDrawnWithCacheEnabled(false);
            if ((this.f14529a.getPersistentDrawingCache() & 2) == 0) {
                this.f14529a.setChildrenDrawingCacheEnabled(false);
            }
            if (!this.f14529a.isAlwaysDrawnWithCacheEnabled()) {
                this.f14529a.invalidate();
            }
        }
    }
}
