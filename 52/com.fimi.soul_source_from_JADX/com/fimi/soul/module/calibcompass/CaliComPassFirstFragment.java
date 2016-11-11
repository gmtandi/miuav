package com.fimi.soul.module.calibcompass;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
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
import com.fimi.soul.module.p091a.C1664h;
import com.fimi.soul.utils.be;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import org.codehaus.jackson.smile.SmileConstants;

public class CaliComPassFirstFragment extends BaseCaliCompassFragment {
    C1668d f7862f;
    private TextView f7863g;
    private TextView f7864h;
    private Button f7865i;
    private boolean f7866j;
    private boolean f7867k;
    private Handler f7868l;
    private boolean f7869m;
    private boolean f7870n;

    public CaliComPassFirstFragment() {
        this.f7868l = new C1671b(this);
    }

    private void m10843a(View view) {
        this.f7863g = (TextView) view.findViewById(C1205R.id.tv_settingTitle);
        this.f7863g.setText(C1205R.string.compass_hor_calibration);
        this.f7864h = (TextView) view.findViewById(C1205R.id.showtitleone);
        this.f7865i = (Button) view.findViewById(C1205R.id.black_btn);
        this.f7865i.setOnClickListener(this);
        m10844a(this.f7864h);
        be.m12359a(getActivity().getAssets(), this.f7863g, this.f7864h, this.f7865i);
    }

    private void m10844a(TextView textView) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(C1205R.color.white));
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(getResources().getColor(C1205R.color.white));
        CharSequence spannableStringBuilder = new SpannableStringBuilder(textView.getText().toString());
        spannableStringBuilder.setSpan(foregroundColorSpan, 19, 21, 33);
        spannableStringBuilder.setSpan(foregroundColorSpan2, 27, textView.getText().length(), 33);
        textView.setText(spannableStringBuilder);
        m10846a(textView, (int) SmileConstants.TOKEN_PREFIX_TINY_UNICODE);
    }

    public void m10846a(TextView textView, int i) {
        textView.setTextColor(textView.getTextColors().withAlpha(i));
    }

    public void m10847a(boolean z) {
        this.f7869m = z;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f7862f = (C1668d) activity;
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
        View inflate = layoutInflater.inflate(C1205R.layout.cali_compass_first, null);
        m10843a(inflate);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f7862f = null;
    }

    public void onDroneEvent(C1584h c1584h, C1433a c1433a) {
        super.onDroneEvent(c1584h, c1433a);
        if (isVisible()) {
            switch (C1672c.f7930a[c1584h.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                    C1578u h = c1433a.m9605h();
                    if (!this.f7869m) {
                        return;
                    }
                    if (this.f7867k && h.m10605d() == (byte) 1 && h.m10607e() == (byte) 1 && h.m10601b() == (byte) 1 && h.m10603c() == (byte) 2) {
                        this.f7867k = false;
                        this.f7870n = true;
                        C1664h.m10813a(c1433a).m10824b((byte) 1, (byte) 1, (byte) 3);
                    } else if (h.m10605d() == (byte) 1 && h.m10607e() == (byte) 1 && h.m10601b() == null && h.m10603c() == (byte) 1) {
                        Fragment findFragmentByTag = this.c.findFragmentByTag(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2);
                        if (findFragmentByTag != null && (findFragmentByTag instanceof CaliCompassErrorFragment) && findFragmentByTag.isHidden()) {
                            ((CaliCompassErrorFragment) findFragmentByTag).m10874a(getString(C1205R.string.remindererror), false, false);
                            this.c.beginTransaction().hide(this).show(findFragmentByTag).commitAllowingStateLoss();
                        }
                    } else if (this.f7870n && h.m10605d() == (byte) 2 && h.m10607e() == (byte) 1 && h.m10601b() == (byte) 1 && h.m10603c() == (byte) 1) {
                        this.f7870n = false;
                        this.f7866j = false;
                        this.c.beginTransaction().hide(this).show(this.c.findFragmentByTag("thrid")).setCustomAnimations(17432578, 17432579).commitAllowingStateLoss();
                    } else if (h.m10605d() == (byte) 1 && h.m10607e() == (byte) 1 && h.m10601b() == (byte) 1 && h.m10603c() == (byte) 1 && !this.f7866j) {
                        this.f7866j = true;
                        this.f7870n = true;
                        C1160b.m7953b(getActivity()).m7959a(getString(C1205R.string.compass_hor));
                    }
                default:
            }
        }
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z) {
            this.f7866j = false;
            this.f7868l.sendEmptyMessageDelayed(0, 2000);
        }
    }

    public void onStart() {
        super.onStart();
    }
}
