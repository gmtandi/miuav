package com.fimi.soul.module.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fimi.kernel.p084e.ak;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.C1236a;
import com.fimi.soul.biz.p103k.ba;
import com.fimi.soul.entity.User;
import com.fimi.soul.module.setting.ShowTextActivity;
import com.fimi.soul.utils.am;
import com.fimi.soul.utils.as;
import com.fimi.soul.utils.be;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.account.openauth.XiaomiOAuthResults;

public class LoginFragment extends Fragment implements OnClickListener {
    private TextView f8811a;
    private Context f8812b;
    private TextView f8813c;
    private TextView f8814d;
    private Button f8815e;
    private Button f8816f;
    private TextView f8817g;
    private TextView f8818h;
    private TextView f8819i;
    private TextView f8820j;
    private RelativeLayout f8821k;
    private RelativeLayout f8822l;
    private LayoutTransition f8823m;
    private C1787p f8824n;
    private String f8825o;
    private XiaomiOAuthResults f8826p;
    private ba f8827q;
    private String f8828r;
    private User f8829s;
    private Boolean f8830t;
    private as f8831u;

    public LoginFragment() {
        this.f8825o = "http://dev.xiaomi.com";
        this.f8829s = new User();
        this.f8830t = Boolean.valueOf(false);
    }

    private void m11519a() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f8811a, "translationX", new float[]{this.f8811a.getTranslationX(), 1000.0f});
        ofFloat.setDuration(500);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.f8814d, "translationX", new float[]{this.f8814d.getTranslationX(), 1000.0f});
        ofFloat.setStartDelay(100);
        ofFloat.setDuration(500);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.f8815e, "translationX", new float[]{this.f8814d.getTranslationX(), 1000.0f});
        ofFloat.setStartDelay(200);
        ofFloat.setDuration(500);
        ofFloat.start();
        ofFloat = ObjectAnimator.ofFloat(this.f8816f, "translationX", new float[]{this.f8814d.getTranslationX(), 1000.0f});
        ofFloat.setStartDelay(300);
        ofFloat.setDuration(500);
        ofFloat.start();
        Animator ofFloat2 = ObjectAnimator.ofFloat(this.f8817g, "translationX", new float[]{this.f8814d.getTranslationX(), 1000.0f});
        Animator ofFloat3 = ObjectAnimator.ofFloat(this.f8818h, "translationX", new float[]{this.f8814d.getTranslationX(), 1000.0f});
        Animator ofFloat4 = ObjectAnimator.ofFloat(this.f8820j, "translationX", new float[]{this.f8814d.getTranslationX(), 1000.0f});
        Animator ofFloat5 = ObjectAnimator.ofFloat(this.f8819i, "translationX", new float[]{this.f8814d.getTranslationX(), 1000.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat2).with(ofFloat5).with(ofFloat3).with(ofFloat4);
        animatorSet.setStartDelay(400);
        animatorSet.setDuration(500);
        animatorSet.start();
        ofFloat = ObjectAnimator.ofFloat(this.f8813c, "translationX", new float[]{this.f8814d.getTranslationX(), 1000.0f});
        ofFloat.setStartDelay(500);
        ofFloat.setDuration(500);
        ofFloat.start();
        this.f8824n.m11476a();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8824n = (C1787p) activity;
        this.f8812b = activity;
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1205R.id.agreementshengming_tv:
                intent = new Intent(getActivity(), ShowTextActivity.class);
                intent.putExtra(ShowTextActivity.f9165c, C1236a.f5610h);
                intent.putExtra(ShowTextActivity.f9163a, C1205R.string.agreement_shengming);
                intent.putExtra(ShowTextActivity.f9164b, "file:///android_asset/statement.html");
                startActivity(intent);
            case C1205R.id.register_bt:
                if (be.m12370b(this.f8812b)) {
                    this.f8812b.startActivity(new Intent(this.f8812b, RegisterActivity.class));
                    return;
                }
                ak.m8083a(this.f8812b, (int) C1205R.string.login_result_net, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            case C1205R.id.login_bt:
                if (be.m12370b(this.f8812b)) {
                    this.f8822l.setVisibility(0);
                    this.f8831u.m12264a(this.f8812b, new C1803o(this));
                    return;
                }
                ak.m8083a(this.f8812b, (int) C1205R.string.login_result_net, (int) XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
            case C1205R.id.agreementprivacy_tv:
                intent = new Intent(getActivity(), ShowTextActivity.class);
                intent.putExtra(ShowTextActivity.f9165c, C1236a.f5611i);
                intent.putExtra(ShowTextActivity.f9163a, C1205R.string.agreement_privacy);
                intent.putExtra(ShowTextActivity.f9164b, "file:///android_asset/privacy.html");
                startActivity(intent);
            case C1205R.id.nologin_tv:
                if (!this.f8830t.booleanValue()) {
                    this.f8830t = Boolean.valueOf(true);
                    m11519a();
                }
            default:
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.fragment_login, viewGroup, false);
        this.f8813c = (TextView) inflate.findViewById(C1205R.id.nologin_tv);
        this.f8813c.setOnClickListener(this);
        this.f8811a = (TextView) inflate.findViewById(C1205R.id.welcom_tv);
        this.f8815e = (Button) inflate.findViewById(C1205R.id.login_bt);
        this.f8816f = (Button) inflate.findViewById(C1205R.id.register_bt);
        this.f8814d = (TextView) inflate.findViewById(C1205R.id.miaircraft_tv);
        this.f8814d = (TextView) inflate.findViewById(C1205R.id.miaircraft_tv);
        this.f8815e = (Button) inflate.findViewById(C1205R.id.login_bt);
        this.f8815e.setOnClickListener(this);
        this.f8820j = (TextView) inflate.findViewById(C1205R.id.agreement_tv);
        this.f8816f = (Button) inflate.findViewById(C1205R.id.register_bt);
        this.f8816f.setOnClickListener(this);
        this.f8817g = (TextView) inflate.findViewById(C1205R.id.agreementprivacy_tv);
        this.f8817g.getPaint().setFlags(8);
        this.f8817g.getPaint().setAntiAlias(true);
        this.f8817g.setOnClickListener(this);
        this.f8818h = (TextView) inflate.findViewById(C1205R.id.agreementshengming_tv);
        this.f8818h.getPaint().setFlags(8);
        this.f8818h.getPaint().setAntiAlias(true);
        this.f8818h.setOnClickListener(this);
        this.f8819i = (TextView) inflate.findViewById(C1205R.id.loginmillet_tv);
        this.f8821k = (RelativeLayout) inflate.findViewById(C1205R.id.login_rl);
        this.f8822l = (RelativeLayout) inflate.findViewById(C1205R.id.login_progress_rl);
        this.f8827q = ba.m9259a(this.f8812b);
        be.m12359a(this.f8812b.getAssets(), this.f8813c, this.f8811a, this.f8815e, this.f8816f, this.f8814d, this.f8817g, this.f8820j, this.f8818h, this.f8819i);
        this.f8831u = new am();
        return inflate;
    }
}
