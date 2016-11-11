package com.fimi.soul.module.setting.newhand;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.fimi.soul.C1205R;
import com.p054c.p055a.p056a.p057a.p060b.C0874c;
import com.p054c.p055a.p063b.C0924d;
import com.p054c.p055a.p063b.C0934f;
import com.p054c.p055a.p063b.C0936g;
import com.p054c.p055a.p063b.C0941l;
import com.p054c.p055a.p063b.p064a.C0902h;
import java.util.ArrayList;
import java.util.List;

public class LoopWidget extends FrameLayout {
    private int f9357a;
    private C0936g f9358b;
    private List<ImageView> f9359c;
    private Context f9360d;
    private ViewPager f9361e;
    private boolean f9362f;
    private int f9363g;
    private int f9364h;
    private LinearLayout f9365i;
    private List<ImageView> f9366j;
    private Handler f9367k;
    private C0924d f9368l;
    private final Runnable f9369m;

    public LoopWidget(Context context) {
        this(context, null);
    }

    public LoopWidget(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoopWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f9367k = new Handler();
        this.f9369m = new C1869k(this);
        this.f9360d = context;
        m11783a(context);
        m11768a();
    }

    private void m11768a() {
        this.f9359c = new ArrayList();
        this.f9366j = new ArrayList();
        this.f9364h = 400;
    }

    private void m11769a(int[] iArr) {
        int i;
        this.f9357a = iArr.length;
        for (i = 0; i < this.f9357a; i++) {
            View imageView = new ImageView(this.f9360d);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            imageView.setImageResource(C1205R.drawable.dot_blur);
            this.f9365i.addView(imageView, layoutParams);
            this.f9366j.add(imageView);
        }
        ((ImageView) this.f9366j.get(0)).setImageResource(C1205R.drawable.dot_focus);
        for (i = 0; i <= this.f9357a + 1; i++) {
            ImageView imageView2 = new ImageView(this.f9360d);
            imageView2.setScaleType(ScaleType.FIT_XY);
            if (i == 0) {
                imageView2.setImageResource(iArr[this.f9357a - 1]);
            } else if (i == this.f9357a + 1) {
                imageView2.setImageResource(iArr[0]);
            } else {
                imageView2.setImageResource(iArr[i - 1]);
            }
            this.f9359c.add(imageView2);
        }
    }

    private void m11770a(String[] strArr) {
        int i;
        this.f9357a = strArr.length;
        for (i = 0; i < this.f9357a; i++) {
            View imageView = new ImageView(this.f9360d);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            imageView.setImageResource(C1205R.drawable.dot_blur);
            this.f9365i.addView(imageView, layoutParams);
            this.f9366j.add(imageView);
        }
        ((ImageView) this.f9366j.get(0)).setImageResource(C1205R.drawable.dot_focus);
        for (i = 0; i <= this.f9357a + 1; i++) {
            ImageView imageView2 = new ImageView(this.f9360d);
            imageView2.setScaleType(ScaleType.FIT_XY);
            if (i == 0) {
                this.f9358b.m7415a(strArr[this.f9357a - 1], imageView2, this.f9368l);
            } else if (i == this.f9357a + 1) {
                this.f9358b.m7415a(strArr[0], imageView2, this.f9368l);
            } else {
                this.f9358b.m7415a(strArr[i - 1], imageView2, this.f9368l);
            }
            this.f9359c.add(imageView2);
        }
    }

    private void m11774b() {
        this.f9359c.clear();
        View inflate = LayoutInflater.from(this.f9360d).inflate(C1205R.layout.kanner_layout, this, true);
        this.f9361e = (ViewPager) inflate.findViewById(C1205R.id.vp);
        this.f9365i = (LinearLayout) inflate.findViewById(C1205R.id.ll_dot);
        this.f9365i.setVisibility(8);
        this.f9365i.removeAllViews();
    }

    private void m11776c() {
        this.f9361e.setAdapter(new C1870l(this));
        this.f9361e.setFocusable(true);
        this.f9361e.setCurrentItem(1);
        this.f9363g = 1;
        this.f9361e.setOnPageChangeListener(new C1871m(this));
        m11778d();
    }

    private void m11778d() {
        this.f9362f = true;
        this.f9367k.postDelayed(this.f9369m, 2000);
    }

    public void m11783a(Context context) {
        C0936g.m7404a().m7413a(new C0941l(context).m7481b(3).m7468a().m7484b(new C0874c()).m7475a(C0902h.LIFO).m7480b().m7486c());
        this.f9368l = new C0934f().m7391b(true).m7398d(true).m7396d();
        this.f9358b = C0936g.m7404a();
    }

    public void setImagesRes(int[] iArr) {
        m11774b();
        m11769a(iArr);
        m11776c();
    }

    public void setImagesUrl(String[] strArr) {
        m11774b();
        m11770a(strArr);
        m11776c();
    }
}
