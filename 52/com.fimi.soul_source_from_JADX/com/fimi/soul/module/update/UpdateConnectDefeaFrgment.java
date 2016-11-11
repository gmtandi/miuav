package com.fimi.soul.module.update;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import com.fimi.soul.utils.be;

public class UpdateConnectDefeaFrgment extends Fragment implements OnClickListener {
    Handler f9720a;
    private Button f9721b;
    private Button f9722c;
    private Button f9723d;
    private ImageView f9724e;
    private TextView f9725f;
    private TextView f9726g;
    private Context f9727h;
    private String f9728i;
    private String f9729j;
    private String f9730k;
    private boolean f9731l;
    private boolean f9732m;
    private C1789m f9733n;
    private boolean f9734o;

    public UpdateConnectDefeaFrgment() {
        this.f9731l = false;
        this.f9732m = false;
        this.f9734o = false;
        this.f9720a = new C1920l(this);
    }

    private void m11950a() {
        this.f9724e.startAnimation(AnimationUtils.loadAnimation(this.f9727h, C1205R.anim.update_connect_defea_scale));
        Animation loadAnimation = AnimationUtils.loadAnimation(this.f9727h, C1205R.anim.update_connect_defea_alpha_trans);
        loadAnimation.setStartOffset(350);
        this.f9725f.startAnimation(loadAnimation);
        loadAnimation = AnimationUtils.loadAnimation(this.f9727h, C1205R.anim.update_connect_defea_alpha_trans);
        loadAnimation.setStartOffset(400);
        this.f9726g.startAnimation(loadAnimation);
        loadAnimation = AnimationUtils.loadAnimation(this.f9727h, C1205R.anim.update_connect_defea_alpha_trans);
        loadAnimation.setStartOffset(450);
        this.f9721b.startAnimation(loadAnimation);
        this.f9722c.startAnimation(loadAnimation);
    }

    public void m11951a(String str) {
        this.f9729j = str;
    }

    public void m11952a(boolean z) {
        this.f9732m = z;
    }

    public void m11953b(String str) {
        this.f9728i = str;
    }

    public void m11954b(boolean z) {
        this.f9734o = z;
    }

    public void m11955c(String str) {
        this.f9730k = str;
    }

    public void m11956c(boolean z) {
        this.f9731l = z;
    }

    public void onActivityCreated(Bundle bundle) {
        int i = 0;
        super.onActivityCreated(bundle);
        this.f9726g.setText(this.f9729j);
        this.f9725f.setText(this.f9728i);
        this.f9721b.setText(this.f9730k);
        if (this.f9732m) {
            this.f9720a.sendEmptyMessageDelayed(0, 2000);
            this.f9724e.setBackgroundResource(C1205R.drawable.newbie_finish_icon);
        }
        if (this.f9734o) {
            this.f9722c.setVisibility(0);
            this.f9722c.setText(getString(C1205R.string.begincalibration));
            this.f9721b.setVisibility(0);
            this.f9721b.setText(getString(C1205R.string.ignore));
            this.f9723d.setVisibility(8);
            return;
        }
        Button button = this.f9722c;
        int i2 = (this.f9731l || this.f9732m) ? 8 : 0;
        button.setVisibility(i2);
        button = this.f9721b;
        i2 = (this.f9731l || this.f9732m) ? 8 : 0;
        button.setVisibility(i2);
        Button button2 = this.f9723d;
        if (!this.f9731l || this.f9732m) {
            i = 8;
        }
        button2.setVisibility(i);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f9727h = activity;
        this.f9733n = (C1789m) activity;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1205R.id.retry_btn:
                this.f9733n.m11480e();
            case C1205R.id.noconnect_bt:
                this.f9733n.m11479d();
            case C1205R.id.ignore_bt:
                this.f9733n.m11479d();
            default:
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(C1205R.layout.activity_update_connect_defea, viewGroup, false);
        this.f9725f = (TextView) inflate.findViewById(C1205R.id.update_fail_tv);
        this.f9726g = (TextView) inflate.findViewById(C1205R.id.fail_reason);
        this.f9724e = (ImageView) inflate.findViewById(C1205R.id.connect_defeat_iv);
        this.f9721b = (Button) inflate.findViewById(C1205R.id.noconnect_bt);
        this.f9722c = (Button) inflate.findViewById(C1205R.id.retry_btn);
        this.f9723d = (Button) inflate.findViewById(C1205R.id.ignore_bt);
        this.f9721b.setOnClickListener(this);
        this.f9722c.setOnClickListener(this);
        this.f9723d.setOnClickListener(this);
        m11950a();
        be.m12359a(this.f9727h.getAssets(), this.f9725f, this.f9726g, this.f9722c, this.f9721b);
        return inflate;
    }
}
