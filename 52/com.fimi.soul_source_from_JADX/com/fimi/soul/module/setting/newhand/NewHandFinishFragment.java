package com.fimi.soul.module.setting.newhand;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.utils.be;

public class NewHandFinishFragment extends BaseNewHandFragment {
    TextView f9381c;
    TextView f9382d;
    Button f9383e;
    Button f9384f;

    protected View m11785a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(C1205R.layout.fragment_newhand_finish, null);
    }

    protected void m11786a() {
    }

    protected void m11787a(View view) {
        this.f9381c = (TextView) view.findViewById(C1205R.id.tv_finish);
        this.f9382d = (TextView) view.findViewById(C1205R.id.tv_content);
        this.f9383e = (Button) view.findViewById(C1205R.id.btn_study_again);
        this.f9383e.setOnClickListener(this);
        this.f9384f = (Button) view.findViewById(C1205R.id.btn_try_fly);
        this.f9384f.setOnClickListener(this);
        be.m12359a(this.a.getAssets(), this.f9381c, this.f9382d, this.f9383e, this.f9384f);
    }

    protected String m11788b() {
        return NewHandFiveFragment.class.getName().toLowerCase();
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view == this.f9383e) {
            Fragment newHandOneFragment = new NewHandOneFragment();
            this.b.beginTransaction().replace(C1205R.id.root_layout, newHandOneFragment, newHandOneFragment.m11803b()).commitAllowingStateLoss();
        }
        if (view == this.f9384f) {
            C1189f.m8333c().m7933a(C1236a.f5621s, true);
            if (C1189f.m8333c().m7937d(C1236a.f5588L)) {
                getActivity().finish();
                return;
            }
            startActivity(new Intent(getActivity(), FlightActivity.class));
            getActivity().finish();
        }
    }
}
