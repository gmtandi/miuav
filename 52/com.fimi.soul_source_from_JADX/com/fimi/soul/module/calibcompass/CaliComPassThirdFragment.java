package com.fimi.soul.module.calibcompass;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.fimi.kernel.p083d.C1160b;
import com.fimi.soul.C1205R;
import com.fimi.soul.drone.C1433a;
import com.fimi.soul.drone.C1584h;
import com.fimi.soul.drone.p117h.C1578u;
import com.fimi.soul.utils.be;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.smile.SmileConstants;

public class CaliComPassThirdFragment extends BaseCaliCompassFragment {
    C1669f f7871f;
    private TextView f7872g;
    private TextView f7873h;
    private Button f7874i;
    private boolean f7875j;

    private void m10848a(View view) {
        this.f7872g = (TextView) view.findViewById(C1205R.id.tv_settingTitle);
        this.f7872g.setText(C1205R.string.compass_ver_calibration);
        this.f7873h = (TextView) view.findViewById(C1205R.id.showtitleone);
        this.f7874i = (Button) view.findViewById(C1205R.id.black_btn);
        this.f7874i.setOnClickListener(this);
        m10849a(this.f7873h);
        be.m12359a(getActivity().getAssets(), this.f7872g, this.f7873h, this.f7874i);
    }

    private void m10849a(TextView textView) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(C1205R.color.white));
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(C1205R.color.white));
        CharSequence spannableStringBuilder = new SpannableStringBuilder(textView.getText().toString());
        spannableStringBuilder.setSpan(foregroundColorSpan, 19, 23, 33);
        spannableStringBuilder.setSpan(foregroundColorSpan2, 29, this.f7873h.getText().length(), 33);
        this.f7873h.setText(spannableStringBuilder);
        m10850a(this.f7873h, SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    public void m10850a(TextView textView, int i) {
        textView.setTextColor(textView.getTextColors().withAlpha(i));
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f7871f = (C1669f) activity;
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
        View inflate = layoutInflater.inflate(C1205R.layout.cali_compass_third, null);
        m10848a(inflate);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f7871f = null;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        super.onDroneEvent(c1584h, c1433a);
        if (isVisible()) {
            switch (C1673e.f7931a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    C1578u h = c1433a.m9605h();
                    if (this.f7871f != null) {
                        this.f7871f.m10853a(true);
                    }
                    if (h.m10605d() == (byte) 2 && h.m10607e() == (byte) 1 && h.m10601b() == (byte) 1 && h.m10603c() == (byte) 2) {
                        if (this.f7871f != null) {
                            this.f7871f.m10853a(false);
                        }
                        this.f7875j = false;
                        Fragment findFragmentByTag = this.c.findFragmentByTag(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
                        if (findFragmentByTag != null && (findFragmentByTag instanceof CaliCompassErrorFragment) && findFragmentByTag.isHidden()) {
                            ((CaliCompassErrorFragment) findFragmentByTag).m10874a(getString(C1205R.string.calicomsucess), true, false);
                            this.c.beginTransaction().hide(this).show(findFragmentByTag).commitAllowingStateLoss();
                        }
                    }
                default:
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            C1160b.m7951a(getActivity()).m7959a(getString(C1205R.string.compass_ver));
        }
    }
}
