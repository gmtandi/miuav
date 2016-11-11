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

public class NewHandFourFragment extends BaseNewHandFragment {
    TextView f9395c;
    TextView f9396d;
    TextView f9397e;
    TextView f9398f;
    TextView f9399g;
    TextView f9400h;
    TextView f9401i;
    TextView f9402j;
    TextView f9403k;
    Button f9404l;

    private SpannableString m11794a(int i) {
        String string = getString(i);
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(C1205R.color.white)), string.length() - 2, string.length(), 33);
        return spannableString;
    }

    protected View m11795a(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(C1205R.layout.fragment_newhand_four, null);
    }

    protected void m11796a() {
        this.f9395c.setText(Constants.VIA_TO_TYPE_QZONE);
        this.f9397e.setText(C1205R.string.about_left_stick);
        this.f9401i.setText(C1205R.string.new_hand_four_tip_one);
        this.f9402j.setText(C1205R.string.new_hand_four_tip_two);
    }

    protected void m11797a(View view) {
        this.f9395c = (TextView) view.findViewById(C1205R.id.tv_seq);
        this.f9396d = (TextView) view.findViewById(C1205R.id.tv_total);
        this.f9397e = (TextView) view.findViewById(C1205R.id.tv_title);
        this.f9398f = (TextView) view.findViewById(C1205R.id.tv_number_one);
        this.f9401i = (TextView) view.findViewById(C1205R.id.tv_tip_one);
        this.f9399g = (TextView) view.findViewById(C1205R.id.tv_number_two);
        this.f9402j = (TextView) view.findViewById(C1205R.id.tv_tip_two);
        this.f9400h = (TextView) view.findViewById(C1205R.id.tv_number_three);
        this.f9404l = (Button) view.findViewById(C1205R.id.btn_next);
        this.f9404l.setOnClickListener(this);
        this.f9403k = (TextView) view.findViewById(C1205R.id.tv_tip_four);
        be.m12368b(this.a.getAssets(), this.f9395c, this.f9396d);
        be.m12359a(this.a.getAssets(), this.f9397e, this.f9398f, this.f9399g, this.f9400h, this.f9401i, this.f9402j, this.f9403k, this.f9404l);
    }

    protected String m11798b() {
        return NewHandFourFragment.class.getName().toLowerCase();
    }

    public void onClick(View view) {
        super.onClick(view);
        if (view == this.f9404l) {
            Log.i("zhej", "current:" + ((NewHandActivity) getActivity()).m11784a().getCurrentItem());
            ((NewHandActivity) getActivity()).m11784a().setCurrentItem(((NewHandActivity) getActivity()).m11784a().getCurrentItem() + 1, true);
        }
    }
}
