package com.fimi.kernel.view.button;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;

/* renamed from: com.fimi.kernel.view.button.b */
class C1195b extends SimpleSpringListener {
    final /* synthetic */ SwitchButton f5392a;

    C1195b(SwitchButton switchButton) {
        this.f5392a = switchButton;
    }

    public void onSpringUpdate(Spring spring) {
        this.f5392a.m8366a(spring.getCurrentValue());
    }
}
