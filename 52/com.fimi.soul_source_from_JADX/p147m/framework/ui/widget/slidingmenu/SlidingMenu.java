package p147m.framework.ui.widget.slidingmenu;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.lang.reflect.Method;
import java.util.HashMap;

/* renamed from: m.framework.ui.widget.slidingmenu.SlidingMenu */
public class SlidingMenu extends RelativeLayout {
    private C2897c f14692a;
    private HashMap<C2908n, View> f14693b;
    private OnClickListener f14694c;
    private OnTouchListener f14695d;
    private C2896b f14696e;
    private int f14697f;
    private int f14698g;
    private int f14699h;
    private boolean f14700i;
    private FrameLayout f14701j;
    private LinearLayout f14702k;
    private View f14703l;
    private C2895a f14704m;
    private LinearLayout f14705n;
    private View f14706o;

    public SlidingMenu(Context context) {
        super(context);
        m16664a(context);
    }

    public SlidingMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m16664a(context);
    }

    public SlidingMenu(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m16664a(context);
    }

    private void m16664a(Context context) {
        this.f14692a = new C2897c();
        this.f14693b = new HashMap();
        this.f14694c = new C2898d(this);
        this.f14695d = new C2900f(this);
        this.f14697f = context.getResources().getDisplayMetrics().widthPixels;
        this.f14698g = (int) (((float) this.f14697f) * this.f14692a.f14716c);
        this.f14699h = (this.f14697f - this.f14698g) / 2;
        setBackgroundResource(this.f14692a.f14717d);
        m16667b(context);
        m16668c(context);
        getViewTreeObserver().addOnGlobalLayoutListener(new C2901g(this));
    }

    private void m16665a(View view) {
        if (VERSION.SDK_INT >= 9) {
            try {
                Method method = View.class.getMethod("setOverScrollMode", new Class[]{Integer.TYPE});
                method.setAccessible(true);
                method.invoke(view, new Object[]{Integer.valueOf(2)});
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void m16667b(Context context) {
        this.f14701j = new C2903i(this, context);
        this.f14701j.setLayoutParams(new LayoutParams(this.f14698g, -1));
        addView(this.f14701j);
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        this.f14701j.addView(linearLayout);
        View scrollView = new ScrollView(context);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setVerticalFadingEdgeEnabled(false);
        m16665a(scrollView);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.weight = C2020f.f10933c;
        scrollView.setLayoutParams(layoutParams);
        linearLayout.addView(scrollView);
        this.f14702k = new LinearLayout(context);
        this.f14702k.setOrientation(1);
        this.f14702k.setLayoutParams(new LayoutParams(-1, -1));
        scrollView.addView(this.f14702k);
        this.f14703l = new View(context);
        this.f14703l.setBackgroundColor(0);
        this.f14703l.setLayoutParams(new LayoutParams(-1, -1));
        this.f14701j.addView(this.f14703l);
    }

    private void m16668c(Context context) {
        this.f14704m = new C2895a(this);
        this.f14704m.setHorizontalScrollBarEnabled(false);
        this.f14704m.setHorizontalFadingEdgeEnabled(false);
        m16665a(this.f14704m);
        this.f14704m.setLayoutParams(new LayoutParams(this.f14697f, -1));
        addView(this.f14704m);
        View linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LayoutParams(this.f14697f + this.f14698g, -1));
        this.f14704m.addView(linearLayout);
        View frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(this.f14698g, -1));
        linearLayout.addView(frameLayout);
        View imageView = new ImageView(context);
        imageView.setImageResource(this.f14692a.f14724k);
        imageView.setScaleType(ScaleType.FIT_XY);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -1);
        layoutParams.gravity = 5;
        imageView.setLayoutParams(layoutParams);
        frameLayout.addView(imageView);
        this.f14705n = new C2904j(this, context);
        this.f14705n.setBackgroundResource(this.f14692a.f14723j);
        this.f14705n.setLayoutParams(new LinearLayout.LayoutParams(this.f14697f, -1));
        linearLayout.addView(this.f14705n);
    }

    private void m16671d(Context context) {
        this.f14697f = context.getResources().getDisplayMetrics().widthPixels;
        this.f14698g = (int) (((float) this.f14697f) * this.f14692a.f14716c);
        this.f14699h = (this.f14697f - this.f14698g) / 2;
        setBackgroundResource(this.f14692a.f14717d);
        m16673e(context);
        m16675f(context);
    }

    private void m16673e(Context context) {
        ViewGroup.LayoutParams layoutParams = this.f14701j.getLayoutParams();
        layoutParams.width = this.f14698g;
        this.f14701j.setLayoutParams(layoutParams);
        this.f14702k.setPadding(this.f14692a.f14718e, this.f14692a.f14719f, this.f14692a.f14720g, this.f14692a.f14721h);
        if (this.f14696e != null) {
            View a = this.f14696e.m16688a();
            if (a != null) {
                ViewGroup.LayoutParams layoutParams2 = a.getLayoutParams();
                int i = -2;
                if (layoutParams2 != null) {
                    i = layoutParams2.height;
                }
                a.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
                ((LinearLayout) this.f14701j.getChildAt(0)).addView(a);
            }
        }
    }

    private void m16674f() {
        Context context = getContext();
        this.f14702k.removeAllViews();
        int b = this.f14696e.m16698b();
        for (int i = 0; i < b; i++) {
            int i2;
            this.f14702k.addView(this.f14696e.m16689a(i, this.f14702k));
            int a = this.f14696e.m16691a(i).m16705a();
            for (i2 = 0; i2 < a; i2++) {
                C2908n a2 = this.f14696e.m16692a(i, i2);
                View a3 = this.f14696e.m16690a(a2, this.f14702k);
                this.f14702k.addView(a3);
                this.f14702k.addView(m16676g(context));
                this.f14693b.put(a2, a3);
                a3.setTag(a2);
                a3.setOnClickListener(this.f14694c);
                a3.setOnTouchListener(this.f14695d);
            }
            i2 = this.f14702k.getChildCount();
            if (i2 > 0) {
                this.f14702k.removeViewAt(i2 - 1);
            }
        }
    }

    private void m16675f(Context context) {
        ViewGroup.LayoutParams layoutParams = this.f14704m.getLayoutParams();
        layoutParams.width = this.f14697f;
        this.f14704m.setLayoutParams(layoutParams);
        LinearLayout linearLayout = (LinearLayout) this.f14704m.getChildAt(0);
        ViewGroup.LayoutParams layoutParams2 = linearLayout.getLayoutParams();
        layoutParams2.width = this.f14697f + this.f14698g;
        linearLayout.setLayoutParams(layoutParams2);
        FrameLayout frameLayout = (FrameLayout) linearLayout.getChildAt(0);
        layoutParams2 = frameLayout.getLayoutParams();
        layoutParams2.width = this.f14698g;
        frameLayout.setLayoutParams(layoutParams2);
        layoutParams2 = this.f14705n.getLayoutParams();
        layoutParams2.width = this.f14697f;
        this.f14705n.setLayoutParams(layoutParams2);
        this.f14705n.setBackgroundResource(this.f14692a.f14723j);
        ((ImageView) frameLayout.getChildAt(0)).setImageResource(this.f14692a.f14724k);
        if (!this.f14700i) {
            getViewTreeObserver().addOnGlobalLayoutListener(new C2905k(this));
        }
    }

    private View m16676g(Context context) {
        View view = new View(context);
        view.setBackgroundResource(this.f14692a.f14725l);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, 2));
        return view;
    }

    View m16677a(C2908n c2908n) {
        return (View) this.f14693b.get(c2908n);
    }

    public void m16678a() {
        if (this.f14696e != null) {
            m16671d(getContext());
            m16674f();
        }
    }

    public void m16679a(int i, int i2) {
        this.f14692a.f14714a = i;
        this.f14692a.f14715b = i2;
    }

    public void m16680a(int i, int i2, int i3, int i4) {
        this.f14692a.f14718e = i;
        this.f14692a.f14719f = i2;
        this.f14692a.f14720g = i3;
        this.f14692a.f14721h = i4;
    }

    public void m16681b() {
        if (this.f14700i) {
            m16685d();
        } else {
            m16684c();
        }
    }

    public void m16682b(int i, int i2) {
        if (this.f14696e != null) {
            C2908n b = this.f14696e.m16700b(i, i2);
            if (b != null) {
                this.f14696e.m16701b(b);
            }
        }
    }

    public void m16683b(C2908n c2908n) {
        if (this.f14696e != null && c2908n != null) {
            this.f14696e.m16701b(c2908n);
        }
    }

    public void m16684c() {
        this.f14700i = true;
        this.f14704m.smoothScrollTo(0, 0);
        if (this.f14696e != null) {
            this.f14696e.m16697a(this.f14700i);
        }
    }

    public void m16685d() {
        this.f14700i = false;
        this.f14704m.smoothScrollTo(this.f14698g, 0);
        if (this.f14696e != null) {
            this.f14696e.m16697a(this.f14700i);
        }
    }

    public boolean m16686e() {
        return this.f14700i;
    }

    public View getBodyView() {
        return this.f14706o;
    }

    C2897c getMenuConfig() {
        return this.f14692a;
    }

    View getMenuCover() {
        return this.f14703l;
    }

    int getMenuWidth() {
        return this.f14698g;
    }

    int getShowMenuWidth() {
        return this.f14699h;
    }

    public void setAdapter(C2896b c2896b) {
        this.f14696e = c2896b;
        m16678a();
    }

    public void setBodyBackground(int i) {
        this.f14692a.f14723j = i;
    }

    public void setBodyView(View view) {
        this.f14706o = view;
        this.f14705n.removeAllViews();
        if (this.f14706o != null) {
            this.f14706o.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            this.f14705n.addView(this.f14706o);
        }
    }

    public void setMenuBackground(int i) {
        this.f14692a.f14717d = i;
    }

    public void setMenuDivider(int i) {
        this.f14692a.f14725l = i;
    }

    public void setMenuWeight(float f) {
        this.f14692a.f14716c = f;
    }

    public void setShadowRes(int i) {
        this.f14692a.f14724k = i;
    }

    public void setTtleHeight(int i) {
        this.f14692a.f14722i = i;
    }
}
