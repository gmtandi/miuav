package it.sephiroth.android.library.widget;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.ViewConfiguration;

/* renamed from: it.sephiroth.android.library.widget.f */
final class C2827f implements Runnable {
    final /* synthetic */ AbsHListView f14537a;

    C2827f(AbsHListView absHListView) {
        this.f14537a = absHListView;
    }

    public void run() {
        if (this.f14537a.f14384Z == 0) {
            this.f14537a.f14384Z = 1;
            View childAt = this.f14537a.getChildAt(this.f14537a.f14379S - this.f14537a.av);
            if (childAt != null && !childAt.hasFocusable()) {
                this.f14537a.f14392z = 0;
                if (this.f14537a.aJ) {
                    this.f14537a.f14384Z = 2;
                    return;
                }
                childAt.setPressed(true);
                this.f14537a.setPressed(true);
                this.f14537a.m16153h();
                this.f14537a.m16124a(this.f14537a.f14379S, childAt);
                this.f14537a.refreshDrawableState();
                int longPressTimeout = ViewConfiguration.getLongPressTimeout();
                boolean isLongClickable = this.f14537a.isLongClickable();
                if (this.f14537a.f14365E != null) {
                    Drawable current = this.f14537a.f14365E.getCurrent();
                    if (current != null && (current instanceof TransitionDrawable)) {
                        if (isLongClickable) {
                            ((TransitionDrawable) current).startTransition(longPressTimeout);
                        } else {
                            ((TransitionDrawable) current).resetTransition();
                        }
                    }
                }
                if (isLongClickable) {
                    if (this.f14537a.bi == null) {
                        this.f14537a.bi = new C2826e(null);
                    }
                    this.f14537a.bi.m16372a();
                    this.f14537a.postDelayed(this.f14537a.bi, (long) longPressTimeout);
                    return;
                }
                this.f14537a.f14384Z = 2;
            }
        }
    }
}
