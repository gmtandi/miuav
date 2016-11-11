package com.fimi.soul.media.gallery;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.widget.Scroller;

@TargetApi(11)
final class ad {
    static {
        if (VERSION.SDK_INT < 11) {
            throw new RuntimeException("Should not get to HoneycombPlus class unless sdk is >= 11!");
        }
    }

    private ad() {
    }

    public static void m10751a(Scroller scroller, float f) {
        if (scroller != null) {
            scroller.setFriction(f);
        }
    }
}
