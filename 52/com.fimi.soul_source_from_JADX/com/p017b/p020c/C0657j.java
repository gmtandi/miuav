package com.p017b.p020c;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import com.p017b.p018a.C0620b;

/* renamed from: com.b.c.j */
class C0657j implements AnimatorListener {
    final /* synthetic */ C0620b f3979a;
    final /* synthetic */ C0656i f3980b;

    C0657j(C0656i c0656i, C0620b c0620b) {
        this.f3980b = c0656i;
        this.f3979a = c0620b;
    }

    public void onAnimationCancel(Animator animator) {
        this.f3979a.m5566c(null);
    }

    public void onAnimationEnd(Animator animator) {
        this.f3979a.m5565b(null);
    }

    public void onAnimationRepeat(Animator animator) {
        this.f3979a.m5567d(null);
    }

    public void onAnimationStart(Animator animator) {
        this.f3979a.m5564a(null);
    }
}
