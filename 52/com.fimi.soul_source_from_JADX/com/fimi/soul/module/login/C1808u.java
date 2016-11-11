package com.fimi.soul.module.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.fimi.soul.module.login.u */
class C1808u extends AnimatorListenerAdapter {
    final /* synthetic */ UsbConnectFragment f8864a;

    C1808u(UsbConnectFragment usbConnectFragment) {
        this.f8864a = usbConnectFragment;
    }

    public void onAnimationStart(Animator animator) {
        super.onAnimationStart(animator);
        this.f8864a.f8837e.setVisibility(0);
    }
}
