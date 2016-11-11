package com.fimi.soul.module.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.fimi.soul.module.login.w */
class C1810w extends AnimatorListenerAdapter {
    final /* synthetic */ UsbConnectFragment f8866a;

    C1810w(UsbConnectFragment usbConnectFragment) {
        this.f8866a = usbConnectFragment;
    }

    public void onAnimationStart(Animator animator) {
        super.onAnimationStart(animator);
        this.f8866a.f8839g.setVisibility(0);
    }
}
