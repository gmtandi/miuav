package com.fimi.soul.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.android.volley.C0600v;
import com.android.volley.toolbox.C0590q;
import com.android.volley.toolbox.aj;
import com.fimi.kernel.p084e.C1184w;

public class bl {
    private Context f10092a;
    private int f10093b;
    private int f10094c;
    private int f10095d;
    private Drawable f10096e;
    private View f10097f;
    private Drawable f10098g;
    private Drawable f10099h;
    private C0600v f10100i;
    private C0590q f10101j;
    private bp f10102k;
    private bo f10103l;

    public bl(Context context) {
        this.f10092a = null;
        this.f10101j = null;
        this.f10102k = null;
        this.f10103l = null;
        this.f10092a = context;
        this.f10100i = aj.m5174a(context);
        this.f10101j = new C0590q(this.f10100i, bj.m12402a());
    }

    public static bl m12410a(Context context) {
        return new bl(context);
    }

    public int m12415a() {
        return this.f10093b;
    }

    public void m12416a(int i) {
        this.f10096e = this.f10092a.getResources().getDrawable(i);
    }

    public void m12417a(View view) {
        this.f10097f = view;
    }

    public void m12418a(ImageView imageView, String str) {
        if (C1184w.m8281b(str)) {
            if (this.f10099h != null) {
                if (this.f10097f != null) {
                    this.f10097f.setVisibility(4);
                }
                imageView.setVisibility(0);
                imageView.setImageDrawable(this.f10099h);
            }
            if (this.f10103l != null) {
                this.f10103l.m12435a();
                return;
            }
            return;
        }
        if (this.f10097f != null) {
            this.f10097f.setVisibility(0);
            imageView.setVisibility(4);
        } else if (this.f10096e != null) {
            imageView.setImageDrawable(this.f10096e);
            imageView.setVisibility(0);
        }
        imageView.setTag(str);
        this.f10101j.m5246a(str, new bm(this, imageView), this.f10093b, this.f10094c);
    }

    public void m12419a(bo boVar) {
        this.f10103l = boVar;
    }

    public void m12420a(bp bpVar) {
        this.f10102k = bpVar;
    }

    public void m12421a(String str) {
        if (!C1184w.m8281b(str)) {
            this.f10101j.m5246a(str, new bn(this), this.f10093b, this.f10094c);
        }
    }

    public int m12422b() {
        return this.f10094c;
    }

    public void m12423b(int i) {
        this.f10098g = this.f10092a.getResources().getDrawable(i);
    }

    public int m12424c() {
        return this.f10095d;
    }

    public void m12425c(int i) {
        this.f10099h = this.f10092a.getResources().getDrawable(i);
    }

    public bp m12426d() {
        return this.f10102k;
    }

    public void m12427d(int i) {
        this.f10093b = i;
    }

    public bo m12428e() {
        return this.f10103l;
    }

    public void m12429e(int i) {
        this.f10094c = i;
    }

    public void m12430f(int i) {
        this.f10095d = i;
    }
}
