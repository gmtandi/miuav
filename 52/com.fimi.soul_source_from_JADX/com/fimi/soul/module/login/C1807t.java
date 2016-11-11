package com.fimi.soul.module.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.fimi.soul.module.login.t */
class C1807t extends AnimatorListenerAdapter {
    final /* synthetic */ UsbConnectFragment f8863a;

    C1807t(UsbConnectFragment usbConnectFragment) {
        this.f8863a = usbConnectFragment;
    }

    public void onAnimationStart(Animator animator) {
        super.onAnimationStart(animator);
        this.f8863a.f8836d.setVisibility(0);
    }
}
