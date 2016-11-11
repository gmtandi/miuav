package com.fimi.soul.view;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;

class bi extends SimpleSpringListener {
    final /* synthetic */ SwitchButtonStoke f10729a;

    bi(SwitchButtonStoke switchButtonStoke) {
        this.f10729a = switchButtonStoke;
    }

    public void onSpringUpdate(Spring spring) {
        this.f10729a.m12645a(spring.getCurrentValue());
    }
}
