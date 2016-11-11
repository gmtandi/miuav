package com.fimi.soul.media.gallery;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.widget.Scroller;

@TargetApi(14)
final class ae {
    static {
        if (VERSION.SDK_INT < 14) {
            throw new RuntimeException("Should not get to IceCreamSandwichPlus class unless sdk is >= 14!");
        }
    }

    private ae() {
    }

    public static float m10752a(Scroller scroller) {
        return scroller.getCurrVelocity();
    }
}
