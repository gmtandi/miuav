package com.fimi.soul.view;

import android.view.View;
import android.view.View.OnClickListener;

class cb implements OnClickListener {
    final /* synthetic */ int f10745a;
    final /* synthetic */ UpDownSliding f10746b;

    cb(UpDownSliding upDownSliding, int i) {
        this.f10746b = upDownSliding;
        this.f10745a = i;
    }

    public void onClick(View view) {
        if (!this.f10746b.isEnabled() || this.f10745a == this.f10746b.f10634p.m12815a()) {
            return;
        }
        if (this.f10745a == ci.Record.m12815a() && this.f10746b.f10634p == ci.TakePhoto) {
            this.f10746b.m12725a(2, ch.DOWN, Boolean.valueOf(true));
        } else if (this.f10745a == ci.TakePhoto.m12815a() && this.f10746b.f10634p == ci.Record) {
            this.f10746b.m12725a(2, ch.UP, Boolean.valueOf(true));
        } else if (this.f10745a == ci.Record.m12815a() && this.f10746b.f10634p == ci.Live) {
            this.f10746b.m12725a(1, ch.DOWN, Boolean.valueOf(false));
            this.f10746b.f10618D.sendEmptyMessageAtTime(2, 1000);
        } else if (this.f10745a == ci.TakePhoto.m12815a() && this.f10746b.f10634p == ci.Live) {
            this.f10746b.m12725a(1, ch.DOWN, Boolean.valueOf(true));
        } else if (this.f10745a == ci.Live.m12815a() && this.f10746b.f10634p == ci.Record) {
            this.f10746b.m12725a(2, ch.UP, Boolean.valueOf(false));
            this.f10746b.f10618D.sendEmptyMessageAtTime(3, 1000);
        } else if (this.f10745a == ci.Live.m12815a() && this.f10746b.f10634p == ci.TakePhoto) {
            this.f10746b.m12725a(1, ch.UP, Boolean.valueOf(true));
        }
    }
}
