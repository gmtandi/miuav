package com.fimi.soul.module.setting;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.p107c.p108a.p109a.C1448k;
import com.fimi.soul.drone.p107c.p108a.p109a.ba;

class ao implements OnClickListener {
    TextView f9263a;
    TextView f9264b;
    final /* synthetic */ am f9265c;

    public ao(am amVar, TextView textView, TextView textView2) {
        this.f9265c = amVar;
        this.f9263a = textView;
        this.f9264b = textView2;
    }

    public void onClick(View view) {
        if (this.f9265c.f9257O.m9569P().m9995a() && this.f9265c.f9257O.m9570Q()) {
            this.f9263a.setSelected(false);
            this.f9264b.setSelected(false);
            this.f9263a.setTextColor(this.f9265c.f9255M.getResources().getColor(C1205R.color.white_half));
            this.f9264b.setTextColor(this.f9265c.f9255M.getResources().getColor(C1205R.color.white_half));
            ba baVar = new ba();
            baVar.f6719d = C1448k.f6847b;
            baVar.f6720e = (byte) 1;
            switch (view.getId()) {
                case C1205R.id.ev_v:
                    this.f9263a.setSelected(true);
                    this.f9263a.setTextColor(this.f9265c.f9255M.getResources().getColor(C1205R.color.onmarker_color));
                    baVar.f6721f = (byte) 1;
                    break;
                case C1205R.id.light_v:
                    this.f9264b.setSelected(true);
                    this.f9264b.setTextColor(this.f9265c.f9255M.getResources().getColor(C1205R.color.onmarker_color));
                    baVar.f6721f = (byte) 2;
                    break;
            }
            this.f9265c.f9257O.m9569P().m9993a(baVar.m9691a());
            return;
        }
        ak.m8084a(this.f9265c.f9255M, this.f9265c.f9255M.getString(C1205R.string.right_scroll_mode_des));
    }
}
