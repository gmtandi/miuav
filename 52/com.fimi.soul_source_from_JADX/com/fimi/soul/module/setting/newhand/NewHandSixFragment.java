package com.fimi.soul.module.setting.newhand;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import com.tencent.connect.common.Constants;

public class NewHandSixFragment extends BaseNewHandFragment {
    TextView f9432c;
    TextView f9433d;
    TextView f9434e;
    TextView f9435f;
    TextView f9436g;
    TextView f9437h;
    TextView f9438i;
    TextView f9439j;
    TextView f9440k;
    Button f9441l;

    protected View m11808a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(C1205R.layout.fragment_newhand_six, null);
    }

    protected void m11809a() {
        this.f9432c.setText(Constants.VIA_SHARE_TYPE_INFO);
        this.f9434e.setText(C1205R.string.about_motor);
        this.f9438i.setText(C1205R.string.new_hand_six_tip_one);
        this.f9439j.setText(C1205R.string.new_hand_six_tip_two);
        this.f9440k.setText(C1205R.string.new_hand_six_tip_three);
        this.f9441l.setOnClickListener(this);
    }

    protected void m11810a(View view) {
        this.f9432c = (TextView) view.findViewById(C1205R.id.tv_seq);
        this.f9433d = (TextView) view.findViewById(C1205R.id.tv_total);
        this.f9434e = (TextView) view.findViewById(C1205R.id.tv_title);
        this.f9435f = (TextView) view.findViewById(C1205R.id.tv_number_one);
        this.f9438i = (TextView) view.findViewById(C1205R.id.tv_tip_one);
        this.f9436g = (TextView) view.findViewById(C1205R.id.tv_number_two);
        this.f9439j = (TextView) view.findViewById(C1205R.id.tv_tip_two);
        this.f9437h = (TextView) view.findViewById(C1205R.id.tv_number_three);
        this.f9440k = (TextView) view.findViewById(C1205R.id.tv_tip_three);
        this.f9441l = (Button) view.findViewById(C1205R.id.btn_next);
        be.m12368b(this.a.getAssets(), this.f9432c, this.f9433d);
        be.m12359a(this.a.getAssets(), this.f9434e, this.f9435f, this.f9436g, this.f9437h, this.f9438i, this.f9439j, this.f9440k, this.f9441l);
    }

    protected String m11811b() {
        return NewHandSixFragment.class.getName().toLowerCase();
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view == this.f9441l) {
            Log.i("zhej", "current:" + ((NewHandActivity) getActivity()).m11784a().getCurrentItem());
            ((NewHandActivity) getActivity()).m11784a().setCurrentItem(((NewHandActivity) getActivity()).m11784a().getCurrentItem() + 1, true);
        }
    }
}
