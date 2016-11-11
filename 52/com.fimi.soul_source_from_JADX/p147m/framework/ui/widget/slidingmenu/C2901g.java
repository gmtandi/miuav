package p147m.framework.ui.widget.slidingmenu;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* renamed from: m.framework.ui.widget.slidingmenu.g */
class C2901g implements OnGlobalLayoutListener {
    final /* synthetic */ SlidingMenu f14729a;

    C2901g(SlidingMenu slidingMenu) {
        this.f14729a = slidingMenu;
    }

    public void onGlobalLayout() {
        this.f14729a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        this.f14729a.post(new C2902h(this));
    }
}
