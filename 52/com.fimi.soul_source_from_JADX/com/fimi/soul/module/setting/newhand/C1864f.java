package com.fimi.soul.module.setting.newhand;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/* renamed from: com.fimi.soul.module.setting.newhand.f */
class C1864f implements OnSeekBarChangeListener {
    final /* synthetic */ GpsSettingActivity f9504a;

    C1864f(GpsSettingActivity gpsSettingActivity) {
        this.f9504a = gpsSettingActivity;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.f9504a.f9354x = i + 30;
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
