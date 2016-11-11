package com.fimi.soul.module.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.fimi.soul.module.login.v */
class C1809v extends AnimatorListenerAdapter {
    final /* synthetic */ UsbConnectFragment f8865a;

    C1809v(UsbConnectFragment usbConnectFragment) {
        this.f8865a = usbConnectFragment;
    }

    public void onAnimationStart(Animator animator) {
        super.onAnimationStart(animator);
        this.f8865a.f8838f.setVisibility(0);
    }
}
