package com.fimi.soul.view;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.fimi.soul.C1205R;

class au implements OnSeekBarChangeListener {
    final /* synthetic */ TextView f10703a;
    final /* synthetic */ at f10704b;

    au(at atVar, TextView textView) {
        this.f10704b = atVar;
        this.f10703a = textView;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (this.f10704b.f10699k) {
            int b = this.f10704b.f10696h + i;
            if (b <= 6) {
                this.f10703a.setText(b + this.f10704b.f10689a.getString(C1205R.string.speed_unit_m) + this.f10704b.f10689a.getString(C1205R.string.setting_speed_low));
            } else if (b <= 6 || b > 10) {
                this.f10703a.setText(b + this.f10704b.f10689a.getString(C1205R.string.speed_unit_m) + this.f10704b.f10689a.getString(C1205R.string.setting_speed_hight));
            } else {
                this.f10703a.setText(b + this.f10704b.f10689a.getString(C1205R.string.speed_unit_m) + this.f10704b.f10689a.getString(C1205R.string.setting_speed_standard));
            }
        } else {
            this.f10703a.setText((this.f10704b.f10696h + i) + this.f10704b.f10697i);
        }
        this.f10704b.f10702n.onProgressChanged(seekBar, i, z);
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        this.f10704b.f10702n.onStartTrackingTouch(seekBar);
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        this.f10704b.f10702n.onStopTrackingTouch(seekBar);
    }
}
