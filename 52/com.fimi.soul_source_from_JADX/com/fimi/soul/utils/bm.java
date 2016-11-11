package com.fimi.soul.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.android.volley.ag;
import com.android.volley.toolbox.C0575y;
import com.android.volley.toolbox.C0597x;
import com.fimi.kernel.p084e.C1181t;

class bm implements C0575y {
    final /* synthetic */ ImageView f10104a;
    final /* synthetic */ bl f10105b;

    bm(bl blVar, ImageView imageView) {
        this.f10105b = blVar;
        this.f10104a = imageView;
    }

    public void m12431a(ag agVar) {
        if (this.f10105b.f10098g != null) {
            this.f10104a.setImageDrawable(this.f10105b.f10098g);
        }
        if (this.f10105b.f10103l != null) {
            this.f10105b.f10103l.m12435a();
        }
        this.f10104a.setVisibility(0);
        if (this.f10105b.f10097f != null) {
            this.f10105b.f10097f.setVisibility(4);
        }
    }

    public void m12432a(C0597x c0597x, boolean z) {
        Bitmap b = c0597x.m5270b();
        if (this.f10105b.f10102k != null) {
            this.f10105b.f10102k.m12436a(b);
        }
        if (this.f10105b.f10103l != null) {
            this.f10105b.f10103l.m12435a();
        }
        C1181t.m8221a(bl.class, "\u83b7\u53d6\u5230\u56fe\u7247\uff1a" + b);
        if (c0597x.m5271c().equals(this.f10104a.getTag())) {
            if (b != null) {
                this.f10104a.setImageBitmap(b);
            } else if (this.f10105b.f10099h != null) {
                this.f10104a.setImageDrawable(this.f10105b.f10099h);
            }
            if (this.f10105b.f10097f != null) {
                this.f10105b.f10097f.setVisibility(4);
            }
            this.f10104a.setVisibility(0);
        }
    }
}
