package com.fimi.soul.module.setting.newhand;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import com.tencent.connect.common.Constants;

public class NewHandTwoFragment extends BaseNewHandFragment {
    TextView f9458c;
    TextView f9459d;
    TextView f9460e;
    TextView f9461f;
    TextView f9462g;
    TextView f9463h;
    TextView f9464i;
    TextView f9465j;
    TextView f9466k;
    TextView f9467l;
    TextView f9468m;
    Button f9469n;
    LoopWidget f9470o;

    protected View m11820a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(C1205R.layout.fragment_newhand_two, null);
    }

    protected void m11821a() {
        this.f9458c.setText(Constants.VIA_SSO_LOGIN);
        this.f9460e.setText(C1205R.string.new_hand_take_care);
        this.f9464i.setText(C1205R.string.new_hand_two_tip_one);
        this.f9465j.setText(C1205R.string.new_hand_two_tip_two);
        this.f9466k.setText(C1205R.string.new_hand_two_tip_three);
        this.f9468m.setText(C1205R.string.new_hand_two_tip_four);
        this.f9469n.setOnClickListener(this);
        int[] iArr = new int[]{C1205R.drawable.newhand_pic_one, C1205R.drawable.newhand_pic_two, C1205R.drawable.newhand_pic_three, C1205R.drawable.newhand_pic_four};
        this.f9470o.setVisibility(0);
        this.f9470o.setImagesRes(iArr);
    }

    protected void m11822a(View view) {
        this.f9458c = (TextView) view.findViewById(C1205R.id.tv_seq);
        this.f9459d = (TextView) view.findViewById(C1205R.id.tv_total);
        this.f9460e = (TextView) view.findViewById(C1205R.id.tv_title);
        this.f9461f = (TextView) view.findViewById(C1205R.id.tv_number_one);
        this.f9464i = (TextView) view.findViewById(C1205R.id.tv_tip_one);
        this.f9462g = (TextView) view.findViewById(C1205R.id.tv_number_two);
        this.f9465j = (TextView) view.findViewById(C1205R.id.tv_tip_two);
        this.f9463h = (TextView) view.findViewById(C1205R.id.tv_number_three);
        this.f9466k = (TextView) view.findViewById(C1205R.id.tv_tip_three);
        this.f9469n = (Button) view.findViewById(C1205R.id.btn_next);
        this.f9467l = (TextView) view.findViewById(C1205R.id.tv_number_four);
        this.f9468m = (TextView) view.findViewById(C1205R.id.tv_tip_four);
        this.f9470o = (LoopWidget) view.findViewById(C1205R.id.kanner);
        be.m12368b(this.a.getAssets(), this.f9458c, this.f9459d);
        be.m12359a(this.a.getAssets(), this.f9460e, this.f9461f, this.f9462g, this.f9463h, this.f9464i, this.f9465j, this.f9466k, this.f9467l, this.f9468m, this.f9469n);
    }

    protected String m11823b() {
        return NewHandTwoFragment.class.getName().toLowerCase();
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view == this.f9469n) {
            Log.i("zhej", "current:" + ((NewHandActivity) getActivity()).m11784a().getCurrentItem());
            ((NewHandActivity) getActivity()).m11784a().setCurrentItem(((NewHandActivity) getActivity()).m11784a().getCurrentItem() + 1, true);
        }
    }
}
