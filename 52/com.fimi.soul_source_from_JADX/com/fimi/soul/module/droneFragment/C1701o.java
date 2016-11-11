package com.fimi.soul.module.droneFragment;

import com.fimi.soul.entity.FlyActionBean;
import com.fimi.soul.view.myhorizontalseebar.C1684l;
import com.fimi.soul.view.myhorizontalseebar.SeekBar;

/* renamed from: com.fimi.soul.module.droneFragment.o */
class C1701o implements C1684l {
    final /* synthetic */ C1697k f8324a;

    C1701o(C1697k c1697k) {
        this.f8324a = c1697k;
    }

    public void m11138a(SeekBar seekBar) {
        this.f8324a.f8319y = true;
    }

    public void m11139a(SeekBar seekBar, int i, boolean z) {
        if (this.f8324a.f8319y) {
            FlyActionBean j = this.f8324a.f8290E.m8582j();
            if (j != null) {
                if (i < 1) {
                    i = 1;
                }
                this.f8324a.f8296b.setText(i + "m/s");
                j.setSpeek(i);
                this.f8324a.m11115o();
            }
        }
    }

    public void m11140b(SeekBar seekBar) {
        this.f8324a.f8319y = false;
        if (((double) this.f8324a.f8306l.getProgress()) * 1.5d < ((double) (seekBar.getProgress() * seekBar.getProgress()))) {
            int progress = ((int) (((double) (seekBar.getProgress() * seekBar.getProgress())) / 1.5d)) + 1;
            this.f8324a.f8306l.setProgress(progress);
            this.f8324a.f8297c.setText(progress + "m");
            FlyActionBean j = this.f8324a.f8290E.m8582j();
            if (j != null) {
                j.setRidus(progress);
                this.f8324a.m11123b(j);
                this.f8324a.m11121a(false);
            }
        }
    }
}
