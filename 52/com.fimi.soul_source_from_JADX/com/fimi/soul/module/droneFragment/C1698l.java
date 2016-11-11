package com.fimi.soul.module.droneFragment;

import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.view.myhorizontalseebar.C1684l;
import com.fimi.soul.view.myhorizontalseebar.SeekBar;

/* renamed from: com.fimi.soul.module.droneFragment.l */
class C1698l implements C1684l {
    final /* synthetic */ C1697k f8321a;

    C1698l(C1697k c1697k) {
        this.f8321a = c1697k;
    }

    public void m11132a(SeekBar seekBar) {
        this.f8321a.f8286A = true;
    }

    public void m11133a(SeekBar seekBar, int i, boolean z) {
        if (this.f8321a.f8286A) {
            FlyActionBean j = this.f8321a.f8290E.m8582j();
            if (j != null) {
                this.f8321a.f8300f.setText(i + "deg");
                j.setStart_point_agle((short) i);
                this.f8321a.m11121a(false);
            }
        }
    }

    public void m11134b(SeekBar seekBar) {
        this.f8321a.f8286A = false;
    }
}
