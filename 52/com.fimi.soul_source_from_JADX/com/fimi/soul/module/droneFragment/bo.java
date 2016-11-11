package com.fimi.soul.module.droneFragment;

import com.fimi.soul.view.myhorizontalseebar.C1684l;
import com.fimi.soul.view.myhorizontalseebar.SeekBar;

class bo implements C1684l {
    final /* synthetic */ bm f8267a;

    bo(bm bmVar) {
        this.f8267a = bmVar;
    }

    public void m11071a(SeekBar seekBar) {
    }

    public void m11072a(SeekBar seekBar, int i, boolean z) {
        if (i < 1) {
            i = 1;
        }
        this.f8267a.m11055k();
        this.f8267a.f8247h.setText(i + "m/s");
    }

    public void m11073b(SeekBar seekBar) {
        this.f8267a.m11066g();
    }
}
