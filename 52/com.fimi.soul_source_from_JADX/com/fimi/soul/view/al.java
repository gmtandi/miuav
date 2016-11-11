package com.fimi.soul.view;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.widget.Scroller;

@TargetApi(14)
final class al {
    static {
        if (VERSION.SDK_INT < 14) {
            throw new RuntimeException("Should not get to IceCreamSandwichPlus class unless sdk is >= 14!");
        }
    }

    private al() {
    }

    public static float m12739a(Scroller scroller) {
        return scroller.getCurrVelocity();
    }
}
