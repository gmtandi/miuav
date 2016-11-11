package p147m.framework.ui.widget.slidingmenu;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* renamed from: m.framework.ui.widget.slidingmenu.k */
class C2905k implements OnGlobalLayoutListener {
    final /* synthetic */ SlidingMenu f14733a;

    C2905k(SlidingMenu slidingMenu) {
        this.f14733a = slidingMenu;
    }

    public void onGlobalLayout() {
        this.f14733a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        this.f14733a.post(new C2906l(this));
    }
}
