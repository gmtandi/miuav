package com.fimi.soul.module.droneFragment;

import android.widget.PopupWindow.OnDismissListener;
import com.fimi.soul.C1205R;

/* renamed from: com.fimi.soul.module.droneFragment.r */
class C1704r implements OnDismissListener {
    final /* synthetic */ C1697k f8327a;

    C1704r(C1697k c1697k) {
        this.f8327a = c1697k;
    }

    public void onDismiss() {
        this.f8327a.f8313s.setBackgroundResource(C1205R.drawable.bg_fly_combobox_more_down);
    }
}
