package com.fimi.soul.module.flyplannermedia;

import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* renamed from: com.fimi.soul.module.flyplannermedia.b */
class C1762b implements OnTouchListener {
    final /* synthetic */ DroneBaseMediaFragment f8694a;

    C1762b(DroneBaseMediaFragment droneBaseMediaFragment) {
        this.f8694a = droneBaseMediaFragment;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        float y = motionEvent.getY();
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 2) {
            if (this.f8694a.f8648b - y < 0.0f) {
                this.f8694a.f8656j = true;
            } else {
                this.f8694a.f8656j = false;
            }
        }
        if (actionMasked == 0) {
            this.f8694a.f8648b = y;
            this.f8694a.f8657k = false;
        }
        if (actionMasked == 1) {
            this.f8694a.f8648b = 0.0f;
        }
        return false;
    }
}
