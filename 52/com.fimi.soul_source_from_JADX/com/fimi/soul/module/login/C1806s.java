package com.fimi.soul.module.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.fimi.soul.module.login.s */
class C1806s extends AnimatorListenerAdapter {
    final /* synthetic */ UsbConnectFragment f8862a;

    C1806s(UsbConnectFragment usbConnectFragment) {
        this.f8862a = usbConnectFragment;
    }

    public void onAnimationStart(Animator animator) {
        super.onAnimationStart(animator);
        this.f8862a.f8835c.setVisibility(0);
    }
}
