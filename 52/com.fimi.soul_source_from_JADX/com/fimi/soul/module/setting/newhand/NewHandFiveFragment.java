package com.fimi.soul.module.setting.newhand;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;
import com.tencent.connect.common.Constants;

public class NewHandFiveFragment extends BaseNewHandFragment {
    TextView f9385c;
    TextView f9386d;
    TextView f9387e;
    TextView f9388f;
    TextView f9389g;
    TextView f9390h;
    TextView f9391i;
    TextView f9392j;
    TextView f9393k;
    Button f9394l;

    private SpannableString m11789a(int i) {
        String string = getString(i);
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(C1205R.color.white)), string.length() - 2, string.length(), 33);
        return spannableString;
    }

    protected View m11790a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(C1205R.layout.fragment_newhand_five, null);
    }

    protected void m11791a() {
        this.f9385c.setText(Constants.VIA_SHARE_TYPE_TEXT);
        this.f9387e.setText(C1205R.string.about_right_stick);
        this.f9391i.setText(C1205R.string.new_hand_five_tip_one);
        this.f9392j.setText(C1205R.string.new_hand_five_tip_two);
    }

    protected void m11792a(View view) {
        this.f9385c = (TextView) view.findViewById(C1205R.id.tv_seq);
        this.f9386d = (TextView) view.findViewById(C1205R.id.tv_total);
        this.f9387e = (TextView) view.findViewById(C1205R.id.tv_title);
        this.f9388f = (TextView) view.findViewById(C1205R.id.tv_number_one);
        this.f9391i = (TextView) view.findViewById(C1205R.id.tv_tip_one);
        this.f9389g = (TextView) view.findViewById(C1205R.id.tv_number_two);
        this.f9392j = (TextView) view.findViewById(C1205R.id.tv_tip_two);
        this.f9390h = (TextView) view.findViewById(C1205R.id.tv_number_three);
        this.f9394l = (Button) view.findViewById(C1205R.id.btn_next);
        this.f9394l.setOnClickListener(this);
        this.f9393k = (TextView) view.findViewById(C1205R.id.tv_tip_four);
        be.m12368b(this.a.getAssets(), this.f9385c, this.f9386d);
        be.m12359a(this.a.getAssets(), this.f9387e, this.f9388f, this.f9389g, this.f9390h, this.f9391i, this.f9392j, this.f9393k, this.f9394l);
    }

    protected String m11793b() {
        return NewHandFiveFragment.class.getName().toLowerCase();
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view == this.f9394l) {
            Log.i("zhej", "current:" + ((NewHandActivity) getActivity()).m11784a().getCurrentItem());
            ((NewHandActivity) getActivity()).m11784a().setCurrentItem(((NewHandActivity) getActivity()).m11784a().getCurrentItem() + 1, true);
        }
    }
}
