package com.fimi.soul.view;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.widget.Scroller;

@TargetApi(11)
final class ak {
    static {
        if (VERSION.SDK_INT < 11) {
            throw new RuntimeException("Should not get to HoneycombPlus class unless sdk is >= 11!");
        }
    }

    private ak() {
    }

    public static void m12738a(Scroller scroller, float f) {
        if (scroller != null) {
            scroller.setFriction(f);
        }
    }
}
