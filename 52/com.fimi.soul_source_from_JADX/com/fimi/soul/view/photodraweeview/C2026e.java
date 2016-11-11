package com.fimi.soul.view.photodraweeview;

import android.graphics.RectF;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: com.fimi.soul.view.photodraweeview.e */
public class C2026e implements OnDoubleTapListener {
    private C2022a f10972a;

    public C2026e(C2022a c2022a) {
        m12970a(c2022a);
    }

    public void m12970a(C2022a c2022a) {
        this.f10972a = c2022a;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.f10972a == null) {
            return false;
        }
        try {
            float scale = this.f10972a.getScale();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (scale < this.f10972a.getMediumScale()) {
                this.f10972a.m12958a(this.f10972a.getMediumScale(), x, y, true);
                return true;
            } else if (scale < this.f10972a.getMediumScale() || scale >= this.f10972a.getMaximumScale()) {
                this.f10972a.m12958a(this.f10972a.getMinimumScale(), x, y, true);
                return true;
            } else {
                this.f10972a.m12958a(this.f10972a.getMaximumScale(), x, y, true);
                return true;
            }
        } catch (Exception e) {
            return true;
        }
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (this.f10972a == null) {
            return false;
        }
        View a = this.f10972a.m12954a();
        if (a == null) {
            return false;
        }
        if (this.f10972a.getOnPhotoTapListener() != null) {
            RectF c = this.f10972a.m12962c();
            if (c != null) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (c.contains(x, y)) {
                    this.f10972a.getOnPhotoTapListener().m10755a(a, (x - c.left) / c.width(), (y - c.top) / c.height());
                    return true;
                }
            }
        }
        if (this.f10972a.getOnViewTapListener() == null) {
            return false;
        }
        this.f10972a.getOnViewTapListener().m12972a(a, motionEvent.getX(), motionEvent.getY());
        return true;
    }
}
