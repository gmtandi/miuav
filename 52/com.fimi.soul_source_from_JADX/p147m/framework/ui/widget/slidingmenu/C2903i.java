package p147m.framework.ui.widget.slidingmenu;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* renamed from: m.framework.ui.widget.slidingmenu.i */
class C2903i extends FrameLayout {
    final /* synthetic */ SlidingMenu f14731a;

    C2903i(SlidingMenu slidingMenu, Context context) {
        this.f14731a = slidingMenu;
        super(context);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !this.f14731a.f14700i ? true : super.onInterceptTouchEvent(motionEvent);
    }
}
