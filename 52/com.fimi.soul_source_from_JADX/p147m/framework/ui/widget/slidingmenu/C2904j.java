package p147m.framework.ui.widget.slidingmenu;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/* renamed from: m.framework.ui.widget.slidingmenu.j */
class C2904j extends LinearLayout {
    final /* synthetic */ SlidingMenu f14732a;

    C2904j(SlidingMenu slidingMenu, Context context) {
        this.f14732a = slidingMenu;
        super(context);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
