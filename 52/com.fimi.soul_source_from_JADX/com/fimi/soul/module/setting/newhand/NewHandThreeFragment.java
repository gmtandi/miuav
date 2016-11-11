package com.fimi.soul.module.setting.newhand;

import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import com.tencent.connect.common.Constants;

public class NewHandThreeFragment extends BaseNewHandFragment {
    TextView f9445c;
    TextView f9446d;
    TextView f9447e;
    TextView f9448f;
    TextView f9449g;
    TextView f9450h;
    TextView f9451i;
    TextView f9452j;
    TextView f9453k;
    TextView f9454l;
    TextView f9455m;
    Button f9456n;
    private ImageView f9457o;

    protected View m11816a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(C1205R.layout.fragment_newhand_three, null);
    }

    protected void m11817a() {
        this.f9445c.setText(Constants.VIA_TO_TYPE_QQ_DISCUSS_GROUP);
        this.f9447e.setText(C1205R.string.new_hand_about_drone);
        this.f9451i.setText(C1205R.string.new_hand_three_tip_one);
        this.f9452j.setText(C1205R.string.new_hand_three_tip_two);
        this.f9453k.setText(C1205R.string.new_hand_three_tip_three);
        this.f9456n.setOnClickListener(this);
    }

    protected void m11818a(View view) {
        this.f9457o = (ImageView) view.findViewById(C1205R.id.left_iv);
        this.f9457o.setBackgroundResource(C1205R.drawable.newhand_three_anim);
        ((AnimationDrawable) this.f9457o.getBackground()).start();
        this.f9445c = (TextView) view.findViewById(C1205R.id.tv_seq);
        this.f9446d = (TextView) view.findViewById(C1205R.id.tv_total);
        this.f9447e = (TextView) view.findViewById(C1205R.id.tv_title);
        this.f9448f = (TextView) view.findViewById(C1205R.id.tv_number_one);
        this.f9451i = (TextView) view.findViewById(C1205R.id.tv_tip_one);
        this.f9449g = (TextView) view.findViewById(C1205R.id.tv_number_two);
        this.f9452j = (TextView) view.findViewById(C1205R.id.tv_tip_two);
        this.f9450h = (TextView) view.findViewById(C1205R.id.tv_number_three);
        this.f9453k = (TextView) view.findViewById(C1205R.id.tv_tip_three);
        this.f9456n = (Button) view.findViewById(C1205R.id.btn_next);
        this.f9454l = (TextView) view.findViewById(C1205R.id.tv_plane_header);
        this.f9455m = (TextView) view.findViewById(C1205R.id.tv_plane_tail);
        be.m12368b(this.a.getAssets(), this.f9445c, this.f9446d);
        be.m12359a(this.a.getAssets(), this.f9447e, this.f9448f, this.f9449g, this.f9450h, this.f9451i, this.f9452j, this.f9453k, this.f9456n, this.f9454l, this.f9455m);
    }

    protected String m11819b() {
        return NewHandThreeFragment.class.getName().toLowerCase();
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view == this.f9456n) {
            Log.i("zhej", "current:" + ((NewHandActivity) getActivity()).m11784a().getCurrentItem());
            ((NewHandActivity) getActivity()).m11784a().setCurrentItem(((NewHandActivity) getActivity()).m11784a().getCurrentItem() + 1, true);
        }
    }
}
