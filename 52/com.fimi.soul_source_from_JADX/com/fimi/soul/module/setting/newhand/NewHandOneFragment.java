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
import java.util.Timer;

public class NewHandOneFragment extends BaseNewHandFragment {
    private static C1859a f9405n;
    TextView f9406c;
    TextView f9407d;
    TextView f9408e;
    TextView f9409f;
    TextView f9410g;
    TextView f9411h;
    TextView f9412i;
    TextView f9413j;
    TextView f9414k;
    Button f9415l;
    private ImageView f9416m;
    private boolean f9417o;
    private Timer f9418p;
    private AnimationDrawable f9419q;

    public NewHandOneFragment() {
        this.f9417o = false;
        this.f9419q = null;
    }

    public static NewHandOneFragment m11799a(C1859a c1859a) {
        f9405n = c1859a;
        return new NewHandOneFragment();
    }

    protected View m11800a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(C1205R.layout.fragment_newhand_one, null);
    }

    protected void m11801a() {
        this.f9406c.setText(Constants.VIA_TO_TYPE_QQ_GROUP);
        this.f9408e.setText(C1205R.string.new_hand_mode);
        this.f9412i.setText(C1205R.string.new_hand_one_tip_one);
        this.f9413j.setText(C1205R.string.new_hand_one_tip_two);
        this.f9414k.setText(C1205R.string.new_hand_one_tip_three);
        this.f9415l.setText(C1205R.string.new_hand_next);
        this.f9415l.setOnClickListener(this);
    }

    protected void m11802a(View view) {
        this.f9416m = (ImageView) view.findViewById(C1205R.id.img_left);
        this.f9416m.setImageDrawable(getActivity().getResources().getDrawable(C1205R.drawable.newhand_one_anim));
        ((AnimationDrawable) this.f9416m.getDrawable()).start();
        this.f9406c = (TextView) view.findViewById(C1205R.id.tv_seq);
        this.f9407d = (TextView) view.findViewById(C1205R.id.tv_total);
        this.f9408e = (TextView) view.findViewById(C1205R.id.tv_title);
        this.f9409f = (TextView) view.findViewById(C1205R.id.tv_number_one);
        this.f9412i = (TextView) view.findViewById(C1205R.id.tv_tip_one);
        this.f9410g = (TextView) view.findViewById(C1205R.id.tv_number_two);
        this.f9413j = (TextView) view.findViewById(C1205R.id.tv_tip_two);
        this.f9411h = (TextView) view.findViewById(C1205R.id.tv_number_three);
        this.f9414k = (TextView) view.findViewById(C1205R.id.tv_tip_three);
        this.f9415l = (Button) view.findViewById(C1205R.id.btn_next);
        be.m12368b(this.a.getAssets(), this.f9406c, this.f9407d);
        be.m12359a(this.a.getAssets(), this.f9408e, this.f9409f, this.f9410g, this.f9411h, this.f9412i, this.f9413j, this.f9414k, this.f9415l);
    }

    protected String m11803b() {
        return NewHandOneFragment.class.getName().toLowerCase();
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view == this.f9415l) {
            Log.i("zhej", "current:" + ((NewHandActivity) getActivity()).m11784a().getCurrentItem());
            ((NewHandActivity) getActivity()).m11784a().setCurrentItem(((NewHandActivity) getActivity()).m11784a().getCurrentItem() + 1, true);
        }
    }
}
