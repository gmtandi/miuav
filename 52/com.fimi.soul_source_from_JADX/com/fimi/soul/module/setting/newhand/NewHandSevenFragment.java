package com.fimi.soul.module.setting.newhand;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.module.droneui.FlightActivity;
import com.fimi.soul.utils.C1972l;
import com.fimi.soul.utils.be;

public class NewHandSevenFragment extends BaseNewHandFragment {
    TextView f9420c;
    TextView f9421d;
    TextView f9422e;
    TextView f9423f;
    TextView f9424g;
    TextView f9425h;
    TextView f9426i;
    TextView f9427j;
    TextView f9428k;
    TextView f9429l;
    TextView f9430m;
    Button f9431n;

    protected View m11804a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(C1205R.layout.fragment_newhand_seven, null);
    }

    protected void m11805a() {
        this.f9420c.setText(C1972l.f10193l);
        this.f9422e.setText(C1205R.string.ready_flying);
        this.f9426i.setText(C1205R.string.new_hand_seven_tip_one);
        this.f9427j.setText(C1205R.string.new_hand_seven_tip_two);
        this.f9428k.setText(C1205R.string.new_hand_seven_tip_three);
        this.f9430m.setText(C1205R.string.new_hand_seven_tip_four);
        this.f9431n.setOnClickListener(this);
    }

    protected void m11806a(View view) {
        this.f9420c = (TextView) view.findViewById(C1205R.id.tv_seq);
        this.f9421d = (TextView) view.findViewById(C1205R.id.tv_total);
        this.f9422e = (TextView) view.findViewById(C1205R.id.tv_title);
        this.f9423f = (TextView) view.findViewById(C1205R.id.tv_number_one);
        this.f9426i = (TextView) view.findViewById(C1205R.id.tv_tip_one);
        this.f9424g = (TextView) view.findViewById(C1205R.id.tv_number_two);
        this.f9427j = (TextView) view.findViewById(C1205R.id.tv_tip_two);
        this.f9425h = (TextView) view.findViewById(C1205R.id.tv_number_three);
        this.f9428k = (TextView) view.findViewById(C1205R.id.tv_tip_three);
        this.f9429l = (TextView) view.findViewById(C1205R.id.tv_number_four);
        this.f9430m = (TextView) view.findViewById(C1205R.id.tv_tip_four);
        this.f9431n = (Button) view.findViewById(C1205R.id.btn_next);
        be.m12368b(this.a.getAssets(), this.f9420c, this.f9421d);
        be.m12359a(this.a.getAssets(), this.f9422e, this.f9423f, this.f9424g, this.f9425h, this.f9426i, this.f9427j, this.f9428k, this.f9431n, this.f9429l, this.f9430m);
    }

    protected String m11807b() {
        return NewHandSevenFragment.class.getName().toLowerCase();
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view == this.f9431n) {
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
