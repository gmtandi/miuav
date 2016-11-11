package com.fimi.soul.module.calibcompass;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.C1578u;
import com.fimi.soul.utils.be;

public class CaliCompassSecondFragment extends BaseCaliCompassFragment {
    private TextView f7919f;
    private TextView f7920g;
    private Button f7921h;
    private Button f7922i;
    private Handler f7923j;

    public CaliCompassSecondFragment() {
        this.f7923j = new C1681o(this);
    }

    private void m10881a(View view) {
        this.f7919f = (TextView) view.findViewById(C1205R.id.tv_settingTitle);
        this.f7919f.setText(C1205R.string.compass_hor_calibration);
        this.f7920g = (TextView) view.findViewById(C1205R.id.showtitleone);
        this.f7921h = (Button) view.findViewById(C1205R.id.black_btn);
        this.f7921h.setOnClickListener(this);
        this.f7922i = (Button) view.findViewById(C1205R.id.nextstep);
        this.f7922i.setOnClickListener(this);
        be.m12359a(getActivity().getAssets(), this.f7919f, this.f7920g, this.f7922i, this.f7921h);
    }

    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case C1205R.id.black_btn:
                if (this.e != null) {
                    this.e.m10851a();
                }
            default:
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.cali_compass_second, null);
        m10881a(inflate);
        return inflate;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        super.onDroneEvent(c1584h, c1433a);
        if (isVisible() && c1584h == C1584h.CaliCompass) {
            C1578u h = c1433a.m9605h();
            if (h.m10605d() == 2 && h.m10607e() == (byte) 1 && h.m10601b() == (byte) 1 && h.m10603c() == (byte) 1) {
                C1683q.m10886a().m10887a("153");
                this.c.beginTransaction().hide(this).show(this.c.findFragmentByTag("thrid")).commitAllowingStateLoss();
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            this.f7923j.sendEmptyMessageDelayed(0, 2000);
        }
    }
}
