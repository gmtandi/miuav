package com.fimi.soul.view.sliding;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.fimi.kernel.p084e.C1181t;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"NewApi"})
public class FmSlidingTabView extends LinearLayout {
    public int f11001a;
    private Context f11002b;
    private Runnable f11003c;
    private OnPageChangeListener f11004d;
    private int f11005e;
    private int f11006f;
    private int f11007g;
    private int f11008h;
    private int f11009i;
    private LinearLayout f11010j;
    private HorizontalScrollView f11011k;
    private FmViewPager f11012l;
    private List<String> f11013m;
    private List<Drawable> f11014n;
    private ArrayList<Fragment> f11015o;
    private ArrayList<TextView> f11016p;
    private FmFragmentPagerAdapter f11017q;
    private OnClickListener f11018r;

    public class MyOnPageChangeListener implements OnPageChangeListener {
        final /* synthetic */ FmSlidingTabView f11000a;

        public MyOnPageChangeListener(FmSlidingTabView fmSlidingTabView) {
            this.f11000a = fmSlidingTabView;
        }

        public void onPageScrollStateChanged(int i) {
            if (this.f11000a.f11004d != null) {
                this.f11000a.f11004d.onPageScrollStateChanged(i);
            }
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (this.f11000a.f11004d != null) {
                this.f11000a.f11004d.onPageScrolled(i, f, i2);
            }
        }

        public void onPageSelected(int i) {
            this.f11000a.setCurrentItem(i);
            if (this.f11000a.f11004d != null) {
                this.f11000a.f11004d.onPageSelected(i);
            }
        }
    }

    public FmSlidingTabView(Fragment fragment) {
        super(fragment.getActivity());
        this.f11006f = -1;
        this.f11007g = 30;
        this.f11008h = ViewCompat.MEASURED_STATE_MASK;
        this.f11009i = ViewCompat.MEASURED_STATE_MASK;
        this.f11010j = null;
        this.f11011k = null;
        this.f11013m = null;
        this.f11014n = null;
        this.f11015o = null;
        this.f11016p = null;
        this.f11017q = null;
        this.f11018r = new C2033a(this);
        this.f11002b = fragment.getActivity();
        m12997a();
        if (VERSION.SDK_INT <= 17) {
            C1181t.m8234c(FmSlidingTabView.class, "AbSlidingTabView(Fragment fragment) \u8981\u6c42\u6700\u4f4eSDK\u7248\u672c17");
            return;
        }
        this.f11017q = new FmFragmentPagerAdapter(fragment.getChildFragmentManager(), this.f11015o);
        this.f11012l.setAdapter(this.f11017q);
        this.f11012l.setOnPageChangeListener(new MyOnPageChangeListener(this));
        this.f11012l.setOffscreenPageLimit(3);
        addView(this.f11012l, new LayoutParams(-1, -1));
    }

    public FmSlidingTabView(Context context) {
        this(context, null);
    }

    public FmSlidingTabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11006f = -1;
        this.f11007g = 30;
        this.f11008h = ViewCompat.MEASURED_STATE_MASK;
        this.f11009i = ViewCompat.MEASURED_STATE_MASK;
        this.f11010j = null;
        this.f11011k = null;
        this.f11013m = null;
        this.f11014n = null;
        this.f11015o = null;
        this.f11016p = null;
        this.f11017q = null;
        this.f11018r = new C2033a(this);
        this.f11002b = context;
        m12997a();
        this.f11017q = new FmFragmentPagerAdapter(((FragmentActivity) this.f11002b).getFragmentManager(), this.f11015o);
        this.f11012l.setAdapter(this.f11017q);
        this.f11012l.setOnPageChangeListener(new MyOnPageChangeListener(this));
        this.f11012l.setOffscreenPageLimit(3);
        addView(this.f11012l, new LayoutParams(-1, -1));
    }

    private void m12992a(String str, int i) {
        m12993a(str, i, null);
    }

    private void m12993a(String str, int i, Drawable drawable) {
        View fmTabItemView = new FmTabItemView(this.f11002b);
        if (this.f11006f != -1) {
            fmTabItemView.setTabBackgroundResource(this.f11006f);
        }
        if (drawable != null) {
            fmTabItemView.m13008a(null, drawable, null, null);
        }
        fmTabItemView.setTabTextColor(this.f11008h);
        fmTabItemView.setTabTextSize(this.f11007g);
        fmTabItemView.m13007a(i, str);
        this.f11016p.add(fmTabItemView.getTextView());
        fmTabItemView.setOnClickListener(this.f11018r);
        this.f11010j.addView(fmTabItemView, new LayoutParams(0, -1, C2020f.f10933c));
    }

    private void m12995b(int i) {
        View childAt = this.f11010j.getChildAt(i);
        if (this.f11003c != null) {
            removeCallbacks(this.f11003c);
        }
        this.f11003c = new C2034b(this, childAt);
        post(this.f11003c);
    }

    public void m12997a() {
        setOrientation(1);
        setBackgroundColor(Color.rgb(Util.MASK_8BIT, Util.MASK_8BIT, Util.MASK_8BIT));
        this.f11011k = new HorizontalScrollView(this.f11002b);
        this.f11011k.setHorizontalScrollBarEnabled(false);
        this.f11011k.setSmoothScrollingEnabled(true);
        this.f11010j = new LinearLayout(this.f11002b);
        this.f11010j.setOrientation(0);
        this.f11010j.setGravity(17);
        this.f11011k.addView(this.f11010j, new ViewGroup.LayoutParams(-2, -1));
        addView(this.f11011k, new ViewGroup.LayoutParams(-1, -2));
        this.f11012l = new FmViewPager(this.f11002b);
        this.f11012l.setId(1985);
        this.f11015o = new ArrayList();
        this.f11016p = new ArrayList();
        this.f11013m = new ArrayList();
        this.f11014n = new ArrayList();
        if (!(this.f11002b instanceof FragmentActivity)) {
            C1181t.m8234c(FmSlidingTabView.class, "\u6784\u9020AbSlidingTabView\u7684\u53c2\u6570context,\u5fc5\u987b\u662fFragmentActivity\u7684\u5b9e\u4f8b\u3002");
        }
    }

    public void m12998a(int i) {
        this.f11010j.removeViewAt(i);
        this.f11015o.remove(i);
        this.f11016p.remove(i);
        this.f11014n.remove(i);
        this.f11013m.remove(i);
        this.f11017q.notifyDataSetChanged();
        m13004b();
    }

    public void m12999a(int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < this.f11016p.size(); i5++) {
            ((TextView) this.f11016p.get(i5)).setPadding(i, i2, i3, i4);
        }
    }

    public void m13000a(String str, Fragment fragment) {
        this.f11013m.add(str);
        this.f11015o.add(fragment);
        this.f11017q.notifyDataSetChanged();
        m13004b();
    }

    public void m13001a(String str, Fragment fragment, Drawable drawable) {
        this.f11013m.add(str);
        this.f11015o.add(fragment);
        this.f11014n.add(drawable);
        this.f11017q.notifyDataSetChanged();
        m13004b();
    }

    public void m13002a(List<String> list, List<Fragment> list2) {
        this.f11013m.addAll(list);
        this.f11015o.addAll(list2);
        this.f11017q.notifyDataSetChanged();
        m13004b();
    }

    public void m13003a(List<String> list, List<Fragment> list2, List<Drawable> list3) {
        this.f11013m.addAll(list);
        this.f11015o.addAll(list2);
        this.f11014n.addAll(list3);
        this.f11017q.notifyDataSetChanged();
        m13004b();
    }

    public void m13004b() {
        this.f11010j.removeAllViews();
        this.f11016p.clear();
        int count = this.f11017q.getCount();
        for (int i = 0; i < count; i++) {
            if (this.f11014n.size() > 0) {
                m12993a((String) this.f11013m.get(i), i, (Drawable) this.f11014n.get(i));
            } else {
                m12992a((String) this.f11013m.get(i), i);
            }
        }
        if (this.f11005e > count) {
            this.f11005e = count - 1;
        }
        setCurrentItem(this.f11005e);
        requestLayout();
    }

    public void m13005c() {
        this.f11010j.removeAllViews();
        this.f11015o.clear();
        this.f11016p.clear();
        this.f11014n.clear();
        this.f11013m.clear();
        this.f11017q.notifyDataSetChanged();
        m13004b();
    }

    public ViewPager getViewPager() {
        return this.f11012l;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f11003c != null) {
            post(this.f11003c);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f11003c != null) {
            removeCallbacks(this.f11003c);
        }
    }

    public void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        this.f11011k.setFillViewport(z);
        int childCount = this.f11010j.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == C1186y.f5353a)) {
            this.f11001a = -1;
        } else if (childCount > 2) {
            this.f11001a = (int) (((float) MeasureSpec.getSize(i)) * 0.4f);
        } else {
            this.f11001a = MeasureSpec.getSize(i) / 2;
        }
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, i2);
        mode = getMeasuredWidth();
        if (z && measuredWidth != mode) {
            setCurrentItem(this.f11005e);
        }
    }

    public void setCurrentItem(int i) {
        if (this.f11012l == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.f11005e = i;
        int childCount = this.f11010j.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            FmTabItemView fmTabItemView = (FmTabItemView) this.f11010j.getChildAt(i2);
            boolean z = i2 == i;
            fmTabItemView.setSelected(z);
            if (z) {
                fmTabItemView.setTabTextColor(this.f11009i);
                m12995b(i);
            } else {
                fmTabItemView.setTabTextColor(this.f11008h);
            }
            i2++;
        }
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.f11004d = onPageChangeListener;
    }

    public void setSlidingEnabled(boolean z) {
        this.f11012l.setPagingEnabled(z);
    }

    public void setTabBackgroundResource(int i) {
        this.f11006f = i;
    }

    public void setTabLayoutBackgroundResource(int i) {
        this.f11010j.setBackgroundResource(i);
    }

    public void setTabSelectColor(int i) {
        this.f11009i = i;
    }

    public void setTabTextColor(int i) {
        this.f11008h = i;
    }

    public void setTabTextSize(int i) {
        this.f11007g = i;
    }
}
