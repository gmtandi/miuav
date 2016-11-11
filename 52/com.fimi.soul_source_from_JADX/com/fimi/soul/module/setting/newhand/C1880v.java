package com.fimi.soul.module.setting.newhand;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/* renamed from: com.fimi.soul.module.setting.newhand.v */
class C1880v implements OnSeekBarChangeListener {
    final /* synthetic */ C1874p f9599a;

    C1880v(C1874p c1874p) {
        this.f9599a = c1874p;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.f9599a.f9590w = i + 3;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
