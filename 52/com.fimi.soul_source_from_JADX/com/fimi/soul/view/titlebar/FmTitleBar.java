package com.fimi.soul.view.titlebar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fimi.kernel.p084e.C1184w;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.kernel.view.dialog.SampleDialogFragment;
import com.fimi.soul.C1205R;
import com.fimi.soul.base.BaseFimiActivity;
import com.fimi.soul.entity.FmMenuItem;
import com.fimi.soul.p087b.C1222k;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.tencent.mm.sdk.platformtools.Util;
import java.util.List;
import org.p122a.p123a.C2915a;
import p147m.framework.ui.widget.asyncview.AsyncImageView;

public class FmTitleBar extends LinearLayout {
    protected LinearLayout f11042a;
    protected LinearLayout f11043b;
    protected Button f11044c;
    protected Button f11045d;
    protected ImageView f11046e;
    protected ImageView f11047f;
    protected SimpleDraweeView f11048g;
    protected ImageView f11049h;
    protected LinearLayout f11050i;
    public int f11051j;
    public LayoutInflater f11052k;
    public int f11053l;
    public LayoutParams f11054m;
    public LayoutParams f11055n;
    public LayoutParams f11056o;
    public LayoutParams f11057p;
    public LayoutParams f11058q;
    SampleDialogFragment f11059r;
    private Activity f11060s;
    private LayoutParams f11061t;
    private LayoutParams f11062u;
    private PopupWindow f11063v;

    public FmTitleBar(Context context) {
        super(context);
        this.f11042a = null;
        this.f11043b = null;
        this.f11044c = null;
        this.f11045d = null;
        this.f11046e = null;
        this.f11047f = null;
        this.f11048g = null;
        this.f11049h = null;
        this.f11061t = null;
        this.f11062u = null;
        this.f11050i = null;
        this.f11051j = 1;
        this.f11054m = null;
        this.f11055n = null;
        this.f11056o = null;
        this.f11057p = null;
        this.f11058q = null;
        this.f11059r = null;
        m13018a(context);
    }

    public FmTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11042a = null;
        this.f11043b = null;
        this.f11044c = null;
        this.f11045d = null;
        this.f11046e = null;
        this.f11047f = null;
        this.f11048g = null;
        this.f11049h = null;
        this.f11061t = null;
        this.f11062u = null;
        this.f11050i = null;
        this.f11051j = 1;
        this.f11054m = null;
        this.f11055n = null;
        this.f11056o = null;
        this.f11057p = null;
        this.f11058q = null;
        this.f11059r = null;
        m13018a(context);
    }

    public static SampleDialogFragment m13013a(View view, int i, int i2, int i3, int i4, int i5, int i6) {
        FragmentActivity fragmentActivity = (FragmentActivity) view.getContext();
        SampleDialogFragment a = SampleDialogFragment.m8377a(1, C1205R.style.MyDialog, i5, i6);
        a.m8382a(view);
        FragmentTransaction beginTransaction = fragmentActivity.getFragmentManager().beginTransaction();
        beginTransaction.setCustomAnimations(i, i2, i3, i4);
        a.show(beginTransaction, "showDialog");
        a.setCancelable(true);
        return a;
    }

    public void m13014a() {
        this.f11050i.removeAllViews();
    }

    public void m13015a(int i) {
        this.f11050i.setVisibility(0);
        this.f11050i.addView(this.f11052k.inflate(i, null), this.f11054m);
    }

    public void m13016a(int i, int i2) {
        C1186y.m8302a(this.f11050i);
        C1186y.m8302a(this.f11046e);
        int measuredWidth = this.f11046e.getMeasuredWidth();
        int measuredWidth2 = this.f11050i.getMeasuredWidth();
        this.f11061t.rightMargin = 0;
        this.f11061t.leftMargin = 0;
        if (i == 1 || i == 17) {
            if (measuredWidth == 0 && measuredWidth2 == 0) {
                this.f11042a.setGravity(1);
                return;
            }
            if (i2 == 5) {
                if (measuredWidth2 != 0) {
                    this.f11044c.setPadding((measuredWidth2 / 3) * 2, 0, 0, 0);
                }
                this.f11044c.setGravity(17);
                this.f11050i.setHorizontalGravity(5);
            }
            if (i2 == 17 || i2 == 1) {
                this.f11042a.setGravity(1);
                this.f11050i.setHorizontalGravity(3);
                this.f11044c.setGravity(17);
                measuredWidth -= measuredWidth2;
                if (measuredWidth > 0) {
                    this.f11061t.rightMargin = measuredWidth;
                    return;
                }
                this.f11061t.leftMargin = Math.abs(measuredWidth);
            }
        } else if (i == 3 && i2 == 5) {
            this.f11042a.setGravity(3);
            this.f11050i.setHorizontalGravity(5);
        } else if (i == 5 && i2 == 5) {
            this.f11042a.setGravity(5);
            this.f11050i.setHorizontalGravity(5);
        } else if (i == 3 && i2 == 3) {
            this.f11042a.setGravity(3);
            this.f11050i.setHorizontalGravity(3);
        }
    }

    public void m13017a(int i, int i2, int i3, int i4) {
        this.f11061t.setMargins(i, i2, i3, i4);
    }

    public void m13018a(Context context) {
        this.f11060s = (Activity) context;
        setOrientation(1);
        setId(this.f11051j);
        this.f11052k = LayoutInflater.from(context);
        this.f11055n = new LayoutParams(-1, (int) context.getResources().getDimension(C1205R.dimen.activity_headtop_margin));
        this.f11054m = new LayoutParams(-1, -1);
        this.f11056o = new LayoutParams(-1, -2);
        this.f11057p = new LayoutParams(-2, -1);
        this.f11058q = new LayoutParams(-2, -2);
        this.f11058q.gravity = 16;
        this.f11061t = new LayoutParams(-2, -2, C2020f.f10933c);
        this.f11061t.gravity = 16;
        this.f11062u = new LayoutParams(-2, -2);
        this.f11062u.gravity = 16;
        if (VERSION.SDK_INT >= 19) {
            addView(new View(context), this.f11055n);
        }
        this.f11043b = new LinearLayout(context);
        this.f11043b.setOrientation(0);
        this.f11042a = new LinearLayout(context);
        this.f11042a.setOrientation(1);
        this.f11042a.setGravity(16);
        this.f11042a.setPadding(0, 0, 0, 0);
        this.f11044c = new Button(context);
        this.f11044c.setTextColor(Color.rgb(Util.MASK_8BIT, Util.MASK_8BIT, Util.MASK_8BIT));
        this.f11044c.setTextSize(16.0f);
        this.f11044c.setPadding(5, 0, 5, 0);
        this.f11044c.setGravity(16);
        this.f11044c.setBackgroundDrawable(null);
        this.f11044c.setSingleLine();
        this.f11042a.addView(this.f11044c, new LayoutParams(-2, -2, C2020f.f10933c));
        this.f11045d = new Button(context);
        this.f11045d.setTextColor(Color.rgb(Util.MASK_8BIT, Util.MASK_8BIT, Util.MASK_8BIT));
        this.f11045d.setTextSize(15.0f);
        this.f11045d.setPadding(6, 0, 5, 0);
        this.f11045d.setGravity(16);
        this.f11045d.setBackgroundDrawable(null);
        this.f11045d.setSingleLine();
        this.f11042a.addView(this.f11045d, new LayoutParams(-2, 0));
        this.f11046e = new ImageView(context);
        this.f11046e.setVisibility(8);
        this.f11049h = new ImageView(context);
        this.f11049h.setVisibility(8);
        this.f11047f = new ImageView(context);
        this.f11047f.setVisibility(8);
        this.f11048g = new SimpleDraweeView(context);
        this.f11048g.setHierarchy(new GenericDraweeHierarchyBuilder(getResources()).setPlaceholderImage(getResources().getDrawable(C1205R.drawable.defaultavatar)).setFailureImage(getResources().getDrawable(C1205R.drawable.defaultavatar)).setRoundingParams(RoundingParams.asCircle()).build());
        this.f11048g.setVisibility(8);
        this.f11043b.addView(this.f11046e, this.f11058q);
        this.f11043b.addView(this.f11048g, this.f11058q);
        this.f11043b.addView(this.f11049h, this.f11058q);
        this.f11043b.addView(this.f11047f, this.f11058q);
        this.f11043b.addView(this.f11042a, this.f11061t);
        this.f11050i = new LinearLayout(context);
        this.f11050i.setOrientation(0);
        this.f11050i.setGravity(5);
        this.f11050i.setPadding(0, 0, 0, 0);
        this.f11050i.setHorizontalGravity(5);
        this.f11050i.setGravity(16);
        this.f11050i.setVisibility(8);
        this.f11043b.addView(this.f11050i, this.f11062u);
        addView(this.f11043b, this.f11056o);
    }

    public void m13019a(Bitmap bitmap, int i, int i2) {
        this.f11058q.leftMargin = 10;
        this.f11046e.setVisibility(0);
        this.f11046e.setImageBitmap(bitmap);
        this.f11046e.getLayoutParams().height = i;
        this.f11046e.getLayoutParams().width = i2;
    }

    public void m13020a(View view) {
        this.f11054m.rightMargin = 20;
        this.f11050i.setVisibility(0);
        this.f11050i.addView(view, this.f11054m);
    }

    public void m13021a(View view, View view2, boolean z) {
        C1186y.m8302a(view2);
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = (getMeasuredHeight() - view.getMeasuredHeight()) / 2;
        if (view2.getMeasuredWidth() > view.getMeasuredWidth()) {
            measuredWidth = view2.getMeasuredWidth();
        }
        if (z) {
            this.f11063v = new PopupWindow(view2, measuredWidth + 10, -2, true);
        } else {
            this.f11063v = new PopupWindow(view2, -1, -2, true);
        }
        this.f11063v.setFocusable(true);
        this.f11063v.setOutsideTouchable(true);
        this.f11063v.setBackgroundDrawable(new ColorDrawable(AsyncImageView.f14604a));
        this.f11063v.showAsDropDown(view, 0, measuredHeight + 2);
    }

    @SuppressLint({"NewApi"})
    public void m13022a(List<FmMenuItem> list, OnItemClickListener onItemClickListener) {
        View inflate = this.f11052k.inflate(C1205R.layout.list_pop, null);
        this.f11059r = m13013a(inflate, C1205R.anim.push_up_in, C1205R.anim.push_up_out, C1205R.anim.push_up_in, C1205R.anim.push_up_out, 48, this.f11053l);
        ListView listView = (ListView) inflate.findViewById(C1205R.id.pop_list);
        inflate.findViewById(C1205R.id.title_bar).setBackgroundColor(this.f11053l);
        inflate.findViewById(C1205R.id.close).setOnClickListener(new C2037b(this));
        listView.setAdapter(new C1222k(this.f11060s, list));
        listView.setOnItemClickListener(new C2038c(this, onItemClickListener));
    }

    public SimpleDraweeView m13023b(int i, int i2) {
        this.f11058q.leftMargin = 15;
        this.f11058q.topMargin = 5;
        this.f11048g.setVisibility(0);
        this.f11048g.getLayoutParams().height = i2;
        this.f11048g.getLayoutParams().width = i;
        return this.f11048g;
    }

    @SuppressLint({"NewApi"})
    public void m13024b() {
        if (this.f11059r != null && this.f11059r.isVisible()) {
            this.f11059r.dismiss();
        }
    }

    public void m13025b(Bitmap bitmap, int i, int i2) {
        this.f11058q.leftMargin = 15;
        this.f11058q.topMargin = 5;
        this.f11048g.setVisibility(0);
        this.f11048g.setImageBitmap(bitmap);
        this.f11048g.getLayoutParams().height = i;
        this.f11048g.getLayoutParams().width = i2;
    }

    public void m13026b(View view) {
        this.f11058q.leftMargin = 10;
        this.f11043b.addView(view, this.f11058q);
    }

    public void m13027c() {
        if (this.f11063v != null) {
            this.f11063v.dismiss();
        }
    }

    public ImageView getLogoView() {
        return this.f11046e;
    }

    public ImageView getLogoView2() {
        return this.f11047f;
    }

    public LinearLayout getRightLayout() {
        return this.f11050i;
    }

    public Button getTitleSmallTextButton() {
        return this.f11045d;
    }

    public Button getTitleTextButton() {
        return this.f11044c;
    }

    public LinearLayout getTitleTextLayout() {
        return this.f11042a;
    }

    public void setChildViewFillParent(boolean z) {
        if (z) {
            this.f11061t = new LayoutParams(-2, -2, C2020f.f10933c);
            this.f11061t.gravity = 16;
            this.f11042a.setLayoutParams(this.f11061t);
            this.f11062u = new LayoutParams(-2, -2);
            this.f11062u.gravity = 16;
            this.f11050i.setLayoutParams(this.f11062u);
            return;
        }
        this.f11061t = new LayoutParams(-2, -2);
        this.f11061t.gravity = 16;
        this.f11042a.setLayoutParams(this.f11061t);
        this.f11062u = new LayoutParams(-2, -2, C2020f.f10933c);
        this.f11062u.gravity = 16;
        this.f11050i.setLayoutParams(this.f11062u);
    }

    public void setLogo(int i) {
        this.f11046e.setVisibility(0);
        this.f11046e.setBackgroundResource(i);
    }

    public void setLogo(Drawable drawable) {
        this.f11046e.setVisibility(0);
        this.f11046e.setBackgroundDrawable(drawable);
    }

    public void setLogo2(int i) {
        this.f11047f.setVisibility(0);
        this.f11047f.setBackgroundResource(i);
    }

    public void setLogo2(Drawable drawable) {
        this.f11047f.setVisibility(0);
        this.f11047f.setBackgroundDrawable(drawable);
    }

    public void setLogo2OnClickListener(OnClickListener onClickListener) {
        this.f11047f.setOnClickListener(onClickListener);
    }

    public void setLogo3(int i) {
        this.f11048g.setVisibility(0);
        this.f11048g.setBackgroundResource(i);
    }

    public void setLogo3(Drawable drawable) {
        this.f11048g.setVisibility(0);
        this.f11048g.setBackgroundDrawable(drawable);
    }

    public void setLogo3OnClickListener(OnClickListener onClickListener) {
        this.f11048g.setOnClickListener(onClickListener);
    }

    public void setLogoLine(int i) {
        this.f11049h.setVisibility(0);
        this.f11049h.setBackgroundResource(i);
    }

    public void setLogoLine(Drawable drawable) {
        this.f11049h.setVisibility(0);
        this.f11049h.setBackgroundDrawable(drawable);
    }

    public void setLogoOnClickListener(OnClickListener onClickListener) {
        this.f11046e.setOnClickListener(onClickListener);
    }

    public void setTitleBarBackground(int i) {
        setBackgroundResource(i);
    }

    public void setTitleBarBackgroundColor(int i) {
        this.f11053l = i;
        setBackgroundColor(i);
        ((BaseFimiActivity) this.f11060s).setStatusBarTintResource(i);
    }

    public void setTitleBarBackgroundDrawable(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setTitleBarLeftBackground(int i) {
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.f11044c.setCompoundDrawables(drawable, null, null, null);
    }

    public void setTitleColor(int i) {
        this.f11044c.setTextColor(i);
    }

    public void setTitleSmallText(int i) {
        this.f11045d.setLayoutParams(new LayoutParams(-2, -2));
        this.f11045d.setText(i);
    }

    public void setTitleSmallText(String str) {
        if (C1184w.m8281b(str)) {
            this.f11045d.setLayoutParams(new LayoutParams(-2, 0));
            this.f11045d.setText(C2915a.f14760f);
            return;
        }
        this.f11045d.setLayoutParams(new LayoutParams(-2, -2));
        this.f11045d.setText(str);
    }

    public void setTitleText(int i) {
        this.f11044c.setText(i);
    }

    public void setTitleText(String str) {
        this.f11044c.setText(str);
    }

    public void setTitleTextBackgroundDrawable(Drawable drawable) {
        this.f11044c.setBackgroundDrawable(drawable);
    }

    public void setTitleTextBackgroundResource(int i) {
        this.f11044c.setBackgroundResource(i);
    }

    public void setTitleTextBold(boolean z) {
        TextPaint paint = this.f11044c.getPaint();
        if (z) {
            paint.setFakeBoldText(true);
        } else {
            paint.setFakeBoldText(false);
        }
    }

    public void setTitleTextDropDown(View view) {
        if (view != null) {
            setTitleTextOnClickListener(new C2039d(this, view));
        }
    }

    public void setTitleTextOnClickListener(OnClickListener onClickListener) {
        this.f11044c.setOnClickListener(onClickListener);
    }

    public void setTitleTextSize(int i) {
        this.f11044c.setTextSize((float) i);
    }
}
