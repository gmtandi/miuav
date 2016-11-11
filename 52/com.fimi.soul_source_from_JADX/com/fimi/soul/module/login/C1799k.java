package com.fimi.soul.module.login;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewStub;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.BreathLeapView;

/* renamed from: com.fimi.soul.module.login.k */
class C1799k implements Runnable {
    final /* synthetic */ LoginActivity f8855a;

    C1799k(LoginActivity loginActivity) {
        this.f8855a = loginActivity;
    }

    public void run() {
        ((ViewStub) this.f8855a.findViewById(C1205R.id.breathleap_vs)).inflate();
        this.f8855a.f8803j = (BreathLeapView) this.f8855a.findViewById(C1205R.id.breathleap);
        this.f8855a.f8786F.setBackgroundResource(C1205R.drawable.login_mask);
        if (this.f8855a.getIntent().getBooleanExtra("login", true)) {
            Drawable drawable = this.f8855a.getResources().getDrawable(C1205R.drawable.login_anim);
            this.f8855a.f8801h = (AnimationDrawable) drawable;
            this.f8855a.f8801h.setOneShot(true);
            this.f8855a.f8800g.setBackground(drawable);
        }
    }
}
