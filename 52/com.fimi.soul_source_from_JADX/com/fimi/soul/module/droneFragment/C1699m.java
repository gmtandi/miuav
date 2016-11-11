package com.fimi.soul.module.droneFragment;

import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import com.fimi.soul.C1205R;
import com.fimi.soul.entity.FlyActionBean;

/* renamed from: com.fimi.soul.module.droneFragment.m */
class C1699m implements OnCheckedChangeListener {
    final /* synthetic */ C1697k f8322a;

    C1699m(C1697k c1697k) {
        this.f8322a = c1697k;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FlyActionBean j = this.f8322a.f8290E.m8582j();
        if (j != null) {
            switch (i) {
                case C1205R.id.clockwise:
                    j.setPara1(3);
                    break;
                case C1205R.id.anticlockwise:
                    j.setPara1(1);
                    break;
            }
            this.f8322a.m11127f();
            this.f8322a.m11121a(false);
        }
    }
}
