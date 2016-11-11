package it.sephiroth.android.library.p143a.p145b;

import android.annotation.TargetApi;
import android.view.View;
import it.sephiroth.android.library.p143a.C2816d;

/* renamed from: it.sephiroth.android.library.a.b.a */
public class C2817a extends C2816d {
    public C2817a(View view) {
        super(view);
    }

    @TargetApi(14)
    public void m16049a(int i) {
        this.a.setScrollX(i);
    }

    @TargetApi(11)
    public boolean m16050a() {
        return this.a.isHardwareAccelerated();
    }
}
