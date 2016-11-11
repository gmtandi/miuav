package com.fimi.soul.module.update;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

public class AutoSelfErrorFrgment extends Fragment {
    private static int f9638n;
    Handler f9639a;
    private Button f9640b;
    private Button f9641c;
    private Button f9642d;
    private ImageView f9643e;
    private TextView f9644f;
    private TextView f9645g;
    private Context f9646h;
    private String f9647i;
    private String f9648j;
    private String f9649k;
    private boolean f9650l;
    private boolean f9651m;

    static {
        f9638n = 0;
    }

    public AutoSelfErrorFrgment() {
        this.f9650l = false;
        this.f9651m = false;
        this.f9639a = new C1909a(this);
    }

    private void m11909a() {
        this.f9643e.startAnimation(AnimationUtils.loadAnimation(this.f9646h, C1205R.anim.update_connect_defea_scale));
        Animation loadAnimation = AnimationUtils.loadAnimation(this.f9646h, C1205R.anim.update_connect_defea_alpha_trans);
        loadAnimation.setStartOffset(350);
        this.f9644f.startAnimation(loadAnimation);
        loadAnimation = AnimationUtils.loadAnimation(this.f9646h, C1205R.anim.update_connect_defea_alpha_trans);
        loadAnimation.setStartOffset(400);
        this.f9645g.startAnimation(loadAnimation);
        loadAnimation = AnimationUtils.loadAnimation(this.f9646h, C1205R.anim.update_connect_defea_alpha_trans);
        loadAnimation.setStartOffset(450);
        this.f9640b.startAnimation(loadAnimation);
        this.f9641c.startAnimation(loadAnimation);
    }

    public void m11910a(String str) {
        this.f9648j = str;
    }

    public void m11911a(boolean z) {
        this.f9651m = z;
    }

    public void m11912b(String str) {
        this.f9647i = str;
    }

    public void m11913b(boolean z) {
        this.f9650l = z;
    }

    public void m11914c(String str) {
        this.f9649k = str;
    }

    public void onActivityCreated(Bundle bundle) {
        int i = 0;
        super.onActivityCreated(bundle);
        this.f9645g.setText(this.f9648j);
        this.f9644f.setText(this.f9647i);
        this.f9640b.setText(this.f9649k);
        if (this.f9651m) {
            this.f9639a.sendEmptyMessageDelayed(f9638n, 2000);
            this.f9643e.setBackgroundResource(C1205R.drawable.newbie_finish_icon);
        }
        Button button = this.f9641c;
        int i2 = (this.f9650l || this.f9651m) ? 8 : 0;
        button.setVisibility(i2);
        button = this.f9640b;
        i2 = (this.f9650l || this.f9651m) ? 8 : 0;
        button.setVisibility(i2);
        Button button2 = this.f9642d;
        if (!this.f9650l || this.f9651m) {
            i = 8;
        }
        button2.setVisibility(i);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f9646h = activity;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.activity_update_connect_defea, viewGroup, false);
        this.f9644f = (TextView) inflate.findViewById(C1205R.id.update_fail_tv);
        this.f9645g = (TextView) inflate.findViewById(C1205R.id.fail_reason);
        this.f9643e = (ImageView) inflate.findViewById(C1205R.id.connect_defeat_iv);
        this.f9640b = (Button) inflate.findViewById(C1205R.id.noconnect_bt);
        this.f9641c = (Button) inflate.findViewById(C1205R.id.retry_btn);
        this.f9642d = (Button) inflate.findViewById(C1205R.id.ignore_bt);
        m11909a();
        be.m12359a(this.f9646h.getAssets(), this.f9644f, this.f9645g, this.f9641c, this.f9640b);
        return inflate;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f9639a.removeMessages(f9638n);
    }
}
