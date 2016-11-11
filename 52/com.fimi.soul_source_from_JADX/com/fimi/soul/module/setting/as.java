package com.fimi.soul.module.setting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fimi.kernel.view.button.SwitchButton;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

class as {
    TextView f9266a;
    ImageView f9267b;
    TextView f9268c;
    TextView f9269d;
    TextView f9270e;
    ImageView f9271f;
    TextView f9272g;
    ImageView f9273h;
    RelativeLayout f9274i;
    SwitchButton f9275j;
    TextView f9276k;
    RelativeLayout f9277l;
    TextView f9278m;
    TextView f9279n;
    final /* synthetic */ am f9280o;

    private as(am amVar) {
        this.f9280o = amVar;
    }

    public View m11689a(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.f9280o.f9255M).inflate(C1205R.layout.adapt_setting, viewGroup, false);
        this.f9274i = (RelativeLayout) inflate.findViewById(C1205R.id.rl);
        this.f9266a = (TextView) inflate.findViewById(C1205R.id.setting_title_tv);
        this.f9267b = (ImageView) inflate.findViewById(C1205R.id.general_setting_iv);
        this.f9268c = (TextView) inflate.findViewById(C1205R.id.settig_coontent_tv);
        this.f9271f = (ImageView) inflate.findViewById(C1205R.id.setting_more_iv);
        this.f9269d = (TextView) inflate.findViewById(C1205R.id.switch_manul_tv);
        this.f9270e = (TextView) inflate.findViewById(C1205R.id.switch_manul_child_tv);
        this.f9272g = (TextView) inflate.findViewById(C1205R.id.setting_delcare);
        this.f9273h = (ImageView) inflate.findViewById(C1205R.id.setting_point);
        this.f9275j = (SwitchButton) inflate.findViewById(C1205R.id.switch_btn);
        this.f9276k = (TextView) inflate.findViewById(C1205R.id.btn_set_model);
        this.f9278m = (TextView) inflate.findViewById(C1205R.id.ev_v);
        this.f9279n = (TextView) inflate.findViewById(C1205R.id.light_v);
        this.f9277l = (RelativeLayout) inflate.findViewById(C1205R.id.ev_layout);
        be.m12359a(this.f9280o.f9255M.getAssets(), this.f9266a, this.f9267b, this.f9272g, this.f9269d, this.f9270e, this.f9276k, this.f9278m, this.f9279n);
        return inflate;
    }
}
