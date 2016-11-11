package com.fimi.soul.p087b;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fimi.kernel.view.button.SwitchButton;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

/* renamed from: com.fimi.soul.b.h */
class C1219h {
    TextView f5497a;
    TextView f5498b;
    ImageView f5499c;
    RelativeLayout f5500d;
    SwitchButton f5501e;
    final /* synthetic */ C1214c f5502f;

    private C1219h(C1214c c1214c) {
        this.f5502f = c1214c;
    }

    public View m8462a(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.f5502f.f5482o).inflate(C1205R.layout.item_setting, viewGroup, false);
        this.f5500d = (RelativeLayout) inflate.findViewById(C1205R.id.rl);
        this.f5497a = (TextView) inflate.findViewById(C1205R.id.setting_title_tv);
        this.f5499c = (ImageView) inflate.findViewById(C1205R.id.setting_arrow);
        this.f5498b = (TextView) inflate.findViewById(C1205R.id.settig_coontent_tv);
        this.f5501e = (SwitchButton) inflate.findViewById(C1205R.id.switch_btn);
        be.m12359a(this.f5502f.f5482o.getAssets(), this.f5497a, this.f5498b);
        return inflate;
    }
}
