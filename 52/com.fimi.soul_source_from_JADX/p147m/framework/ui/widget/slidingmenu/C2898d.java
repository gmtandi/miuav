package p147m.framework.ui.widget.slidingmenu;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: m.framework.ui.widget.slidingmenu.d */
class C2898d implements OnClickListener {
    final /* synthetic */ SlidingMenu f14726a;

    C2898d(SlidingMenu slidingMenu) {
        this.f14726a = slidingMenu;
    }

    public void onClick(View view) {
        C2908n c2908n = (C2908n) view.getTag();
        if (c2908n != null && this.f14726a.f14696e != null && !this.f14726a.f14696e.m16701b(c2908n)) {
            this.f14726a.postDelayed(new C2899e(this), 500);
        }
    }
}
