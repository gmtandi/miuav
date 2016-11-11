package com.fimi.soul.module.droneFragment;

import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.view.myhorizontalseebar.C1684l;
import com.fimi.soul.view.myhorizontalseebar.SeekBar;

/* renamed from: com.fimi.soul.module.droneFragment.p */
class C1702p implements C1684l {
    final /* synthetic */ C1697k f8325a;

    C1702p(C1697k c1697k) {
        this.f8325a = c1697k;
    }

    public void m11141a(SeekBar seekBar) {
        this.f8325a.f8320z = true;
        this.f8325a.f8288C = ((int) (((double) (this.f8325a.f8305k.getProgress() * this.f8325a.f8305k.getProgress())) / 1.5d)) + 1;
        if (this.f8325a.f8288C < 1) {
            this.f8325a.f8288C = 1;
        }
    }

    public void m11142a(SeekBar seekBar, int i, boolean z) {
        if (this.f8325a.f8320z) {
            FlyActionBean j = this.f8325a.f8290E.m8582j();
            if (j != null) {
                if (i <= this.f8325a.f8288C) {
                    i = this.f8325a.f8288C;
                    if (i < 5) {
                        i = 5;
                    }
                }
                this.f8325a.f8297c.setText(i + "m");
                j.setRidus(i);
                this.f8325a.m11123b(j);
                this.f8325a.m11121a(false);
                this.f8325a.m11115o();
            }
        }
    }

    public void m11143b(SeekBar seekBar) {
        int i = 10;
        this.f8325a.f8320z = false;
        if (((double) this.f8325a.f8306l.getProgress()) * 1.5d < ((double) (seekBar.getProgress() * seekBar.getProgress()))) {
            int sqrt = (int) Math.sqrt(((double) this.f8325a.f8306l.getProgress()) * 1.5d);
            if (sqrt < 10) {
                i = sqrt;
            }
            this.f8325a.f8305k.setMax(i);
        }
    }
}
