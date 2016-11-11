package com.fimi.soul.module.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.fimi.soul.module.login.r */
class C1805r extends AnimatorListenerAdapter {
    final /* synthetic */ UsbConnectFragment f8861a;

    C1805r(UsbConnectFragment usbConnectFragment) {
        this.f8861a = usbConnectFragment;
    }

    public void onAnimationStart(Animator animator) {
        super.onAnimationStart(animator);
        this.f8861a.f8833a.setVisibility(0);
    }
}
