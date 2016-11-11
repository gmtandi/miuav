package com.fimi.soul.media.gallery;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* renamed from: com.fimi.soul.media.gallery.z */
class C1612z implements OnTouchListener {
    final /* synthetic */ HorizontalListView f7835a;

    C1612z(HorizontalListView horizontalListView) {
        this.f7835a = horizontalListView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.f7835a.f7753l.onTouchEvent(motionEvent);
    }
}
