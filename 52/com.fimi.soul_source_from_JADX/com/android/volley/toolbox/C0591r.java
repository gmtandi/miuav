package com.android.volley.toolbox;

import android.widget.ImageView;
import com.android.volley.ag;

/* renamed from: com.android.volley.toolbox.r */
final class C0591r implements C0575y {
    final /* synthetic */ int f3641a;
    final /* synthetic */ ImageView f3642b;
    final /* synthetic */ int f3643c;

    C0591r(int i, ImageView imageView, int i2) {
        this.f3641a = i;
        this.f3642b = imageView;
        this.f3643c = i2;
    }

    public void m5253a(ag agVar) {
        if (this.f3641a != 0) {
            this.f3642b.setImageResource(this.f3641a);
        }
    }

    public void m5254a(C0597x c0597x, boolean z) {
        if (c0597x.m5270b() != null) {
            this.f3642b.setImageBitmap(c0597x.m5270b());
        } else if (this.f3643c != 0) {
            this.f3642b.setImageResource(this.f3643c);
        }
    }
}
