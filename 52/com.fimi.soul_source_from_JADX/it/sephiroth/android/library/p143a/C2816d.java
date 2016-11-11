package it.sephiroth.android.library.p143a;

import android.util.Log;
import android.view.View;

/* renamed from: it.sephiroth.android.library.a.d */
public class C2816d extends C2815c {
    public C2816d(View view) {
        super(view);
    }

    public void m16046a(int i) {
        Log.d("ViewHelper", "setScrollX: " + i);
        this.a.scrollTo(i, this.a.getScrollY());
    }

    public void m16047a(Runnable runnable) {
        this.a.post(runnable);
    }

    public boolean m16048a() {
        return false;
    }
}
