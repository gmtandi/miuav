package com.android.volley.toolbox;

import android.graphics.Bitmap;

/* renamed from: com.android.volley.toolbox.x */
public class C0597x {
    final /* synthetic */ C0590q f3654a;
    private Bitmap f3655b;
    private final C0575y f3656c;
    private final String f3657d;
    private final String f3658e;

    public C0597x(C0590q c0590q, Bitmap bitmap, String str, String str2, C0575y c0575y) {
        this.f3654a = c0590q;
        this.f3655b = bitmap;
        this.f3658e = str;
        this.f3657d = str2;
        this.f3656c = c0575y;
    }

    public void m5269a() {
        if (this.f3656c != null) {
            C0595v c0595v = (C0595v) this.f3654a.f3637d.get(this.f3657d);
            if (c0595v == null) {
                c0595v = (C0595v) this.f3654a.f3638e.get(this.f3657d);
                if (c0595v != null) {
                    c0595v.m5264b(this);
                    if (c0595v.f3653e.size() == 0) {
                        this.f3654a.f3638e.remove(this.f3657d);
                    }
                }
            } else if (c0595v.m5264b(this)) {
                this.f3654a.f3637d.remove(this.f3657d);
            }
        }
    }

    public Bitmap m5270b() {
        return this.f3655b;
    }

    public String m5271c() {
        return this.f3658e;
    }
}
