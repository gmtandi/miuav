package com.fimi.soul.module.login;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.ay;
import com.fimi.soul.utils.be;
import com.fimi.soul.view.az;

public class UsbConnectFragment extends Fragment implements Callback, OnClickListener {
    private TextView f8833a;
    private TextView f8834b;
    private RelativeLayout f8835c;
    private RelativeLayout f8836d;
    private RelativeLayout f8837e;
    private RelativeLayout f8838f;
    private RelativeLayout f8839g;
    private Context f8840h;
    private Button f8841i;
    private Button f8842j;
    private Button f8843k;
    private C1788y f8844l;
    private Handler f8845m;

    private void m11523a() {
        Animator ofFloat = ObjectAnimator.ofFloat(this.f8833a, "translationX", new float[]{0.0f, 1000.0f});
        Animator ofFloat2 = ObjectAnimator.ofFloat(this.f8835c, "translationX", new float[]{0.0f, 1000.0f});
        Animator ofFloat3 = ObjectAnimator.ofFloat(this.f8836d, "translationX", new float[]{0.0f, 1000.0f});
        Animator ofFloat4 = ObjectAnimator.ofFloat(this.f8837e, "translationX", new float[]{0.0f, 1000.0f});
        Animator ofFloat5 = ObjectAnimator.ofFloat(this.f8838f, "translationX", new float[]{0.0f, 1000.0f});
        Animator ofFloat6 = ObjectAnimator.ofFloat(this.f8839g, "translationX", new float[]{0.0f, 1000.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat6).with(ofFloat5).with(ofFloat4).with(ofFloat2).with(ofFloat3).with(ofFloat);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    private void m11524a(View view) {
        TextView textView = (TextView) view.findViewById(C1205R.id.declare_one_tv);
        TextView textView2 = (TextView) view.findViewById(C1205R.id.declare_two_tv);
        TextView textView3 = (TextView) view.findViewById(C1205R.id.declare_three_tv);
        TextView textView4 = (TextView) view.findViewById(C1205R.id.declare_four_tv);
        TextView textView5 = (TextView) view.findViewById(C1205R.id.declare_two_child_tv);
        TextView textView6 = (TextView) view.findViewById(C1205R.id.declare_three_child_tv);
        be.m12359a(this.f8840h.getAssets(), textView, textView2, textView3, textView4, textView5, textView6, this.f8841i, this.f8842j, this.f8833a);
    }

    private void m11526b(View view) {
        this.f8841i = (Button) view.findViewById(C1205R.id.next_bt);
        this.f8841i.setOnClickListener(this);
        this.f8842j = (Button) view.findViewById(C1205R.id.noconnect_bt);
        this.f8842j.setOnClickListener(this);
        this.f8835c = (RelativeLayout) view.findViewById(C1205R.id.usbconnect_one_rl);
        this.f8836d = (RelativeLayout) view.findViewById(C1205R.id.usbconnect_two_rl);
        this.f8837e = (RelativeLayout) view.findViewById(C1205R.id.usbconnect_three_rl);
        this.f8838f = (RelativeLayout) view.findViewById(C1205R.id.usbconnect_four_rl);
        this.f8839g = (RelativeLayout) view.findViewById(C1205R.id.usbconnect_button_rl);
        this.f8833a = (TextView) view.findViewById(C1205R.id.connectplane_tv);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f8833a, "translationX", new float[]{800.0f, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f8835c, "translationX", new float[]{800.0f, 0.0f});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.f8836d, "translationX", new float[]{800.0f, 0.0f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this.f8837e, "translationX", new float[]{800.0f, 0.0f});
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this.f8838f, "translationX", new float[]{800.0f, 0.0f});
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this.f8839g, "translationX", new float[]{800.0f, 0.0f});
        this.f8843k = (Button) view.findViewById(C1205R.id.next_bt);
        this.f8843k.setOnClickListener(this);
        ofFloat.setStartDelay(0);
        ofFloat2.setStartDelay(70);
        ofFloat3.setStartDelay(140);
        ofFloat4.setStartDelay(210);
        ofFloat5.setStartDelay(280);
        ofFloat6.setStartDelay(350);
        ofFloat.setDuration(250);
        ofFloat2.setDuration(250);
        ofFloat3.setDuration(250);
        ofFloat4.setDuration(250);
        ofFloat5.setDuration(250);
        ofFloat6.setDuration(250);
        ofFloat.addListener(new C1805r(this));
        ofFloat2.addListener(new C1806s(this));
        ofFloat3.addListener(new C1807t(this));
        ofFloat4.addListener(new C1808u(this));
        ofFloat5.addListener(new C1809v(this));
        ofFloat6.addListener(new C1810w(this));
        ay.m12293a(this.f8840h);
        if (getArguments() == null || !getArguments().getBoolean("isEnterLoginFragment", false)) {
            this.f8833a.setVisibility(0);
            this.f8835c.setVisibility(0);
            this.f8836d.setVisibility(0);
            this.f8837e.setVisibility(0);
            this.f8838f.setVisibility(0);
            this.f8839g.setVisibility(0);
            return;
        }
        ofFloat.start();
        ofFloat2.start();
        ofFloat3.start();
        ofFloat4.start();
        ofFloat5.start();
        ofFloat6.start();
    }

    public boolean handleMessage(Message message) {
        m11523a();
        this.f8844l.m11477b();
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (be.m12391i(this.f8840h).booleanValue()) {
            this.f8845m.sendMessageDelayed(Message.obtain(), 300);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8840h = activity;
        this.f8844l = (C1788y) activity;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.noconnect_bt:
                if (this.f8844l != null) {
                    this.f8844l.m11478c();
                }
            case C1205R.id.next_bt:
                if (be.m12391i(this.f8840h).booleanValue()) {
                    m11523a();
                    this.f8844l.m11477b();
                    return;
                }
                new az(getActivity()).m12791a(getString(C1205R.string.good), new C1811x(this)).m12788a().show();
            default:
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.fragment_usbconnect, viewGroup, false);
        m11526b(inflate);
        m11524a(inflate);
        this.f8845m = new Handler(this);
        return inflate;
    }

    public void onResume() {
        super.onResume();
    }
}
