package com.fimi.soul.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.fimi.kernel.C1189f;
import com.fimi.soul.C1205R;
import com.fimi.soul.biz.p096d.C1325k;
import com.fimi.soul.utils.be;
import com.tencent.open.yyb.TitleBar;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;

public class UpDownSliding extends RelativeLayout {
    private static final int f10609B = 1;
    private static final int f10610C = 2;
    private static final int f10611r = 0;
    private static final int f10612s = 1;
    private static final int f10613t = 2;
    private static final int f10614u = 3;
    private static int f10615y;
    private static int f10616z;
    private ci f10617A;
    private Handler f10618D;
    int f10619a;
    int f10620b;
    int f10621c;
    int f10622d;
    boolean f10623e;
    private String[] f10624f;
    private int[] f10625g;
    private int[] f10626h;
    private int[] f10627i;
    private LinearLayout[] f10628j;
    private ImageView f10629k;
    private VelocityTracker f10630l;
    private TextView[] f10631m;
    private cj f10632n;
    private Boolean f10633o;
    private ci f10634p;
    private final int f10635q;
    private LayoutParams f10636v;
    private LayoutParams f10637w;
    private LayoutParams f10638x;

    static {
        f10615y = -1;
        f10616z = -2130706433;
    }

    public UpDownSliding(Context context) {
        super(context);
        String[] strArr = new String[f10614u];
        strArr[f10611r] = getResources().getString(C1205R.string.record_title);
        strArr[f10612s] = getResources().getString(C1205R.string.photograph);
        strArr[f10613t] = getResources().getString(C1205R.string.stream_live);
        this.f10624f = strArr;
        this.f10623e = false;
        this.f10633o = Boolean.valueOf(false);
        this.f10634p = ci.TakePhoto;
        this.f10635q = 10;
        this.f10617A = ci.TakePhoto;
        this.f10618D = new ca(this);
        m12726a(context);
    }

    public UpDownSliding(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        String[] strArr = new String[f10614u];
        strArr[f10611r] = getResources().getString(C1205R.string.record_title);
        strArr[f10612s] = getResources().getString(C1205R.string.photograph);
        strArr[f10613t] = getResources().getString(C1205R.string.stream_live);
        this.f10624f = strArr;
        this.f10623e = false;
        this.f10633o = Boolean.valueOf(false);
        this.f10634p = ci.TakePhoto;
        this.f10635q = 10;
        this.f10617A = ci.TakePhoto;
        this.f10618D = new ca(this);
        m12726a(context);
    }

    public UpDownSliding(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        String[] strArr = new String[f10614u];
        strArr[f10611r] = getResources().getString(C1205R.string.record_title);
        strArr[f10612s] = getResources().getString(C1205R.string.photograph);
        strArr[f10613t] = getResources().getString(C1205R.string.stream_live);
        this.f10624f = strArr;
        this.f10623e = false;
        this.f10633o = Boolean.valueOf(false);
        this.f10634p = ci.TakePhoto;
        this.f10635q = 10;
        this.f10617A = ci.TakePhoto;
        this.f10618D = new ca(this);
        m12726a(context);
    }

    private void m12718a(ci ciVar, ci ciVar2) {
        if (ciVar == ci.Record && ciVar2 == ci.TakePhoto) {
            m12725a((int) f10613t, ch.UP, Boolean.valueOf(false));
        } else if (ciVar == ci.TakePhoto && ciVar2 == ci.Live) {
            m12725a((int) f10612s, ch.UP, Boolean.valueOf(false));
        } else if (ciVar == ci.Live && ciVar2 == ci.TakePhoto) {
            m12725a((int) f10612s, ch.DOWN, Boolean.valueOf(false));
        } else if (ciVar == ci.TakePhoto && ciVar2 == ci.Record) {
            m12725a((int) f10613t, ch.DOWN, Boolean.valueOf(false));
        } else if (ciVar == ci.Record && ciVar2 == ci.Live) {
            m12718a(ci.Record, ci.TakePhoto);
            this.f10618D.sendEmptyMessageAtTime(f10611r, 1000);
        } else if (ciVar == ci.Live && ciVar2 == ci.Record) {
            m12718a(ci.Live, ci.TakePhoto);
            this.f10618D.sendEmptyMessageDelayed(f10612s, 1000);
        }
    }

    public void m12725a(int i, ch chVar, Boolean bool) {
        int[] iArr;
        ValueAnimator ofInt;
        if (chVar == ch.DOWN) {
            iArr = new int[f10613t];
            iArr[f10611r] = this.f10636v.topMargin;
            iArr[f10612s] = this.f10627i[i];
            ofInt = ValueAnimator.ofInt(iArr);
        } else {
            iArr = new int[f10613t];
            iArr[f10611r] = this.f10636v.topMargin;
            iArr[f10612s] = this.f10627i[i - 1];
            ofInt = ValueAnimator.ofInt(iArr);
        }
        ValueAnimator ofInt2;
        switch (i) {
            case f10612s /*1*/:
                if (chVar == ch.DOWN) {
                    iArr = new int[f10613t];
                    iArr[f10611r] = f10611r;
                    iArr[f10612s] = this.f10626h[f10611r];
                    ofInt2 = ValueAnimator.ofInt(iArr);
                    this.f10631m[f10611r].setTextColor(f10616z);
                    this.f10631m[f10612s].setTextColor(f10615y);
                    this.f10631m[f10613t].setTextColor(f10616z);
                    this.f10634p = ci.TakePhoto;
                } else {
                    iArr = new int[f10613t];
                    iArr[f10611r] = this.f10626h[f10611r];
                    iArr[f10612s] = f10611r;
                    ofInt2 = ValueAnimator.ofInt(iArr);
                    this.f10631m[f10611r].setTextColor(f10616z);
                    this.f10631m[f10612s].setTextColor(f10616z);
                    this.f10631m[f10613t].setTextColor(f10615y);
                    this.f10634p = ci.Live;
                }
                ofInt2.addUpdateListener(new cc(this));
                ofInt.addUpdateListener(new cd(this));
                if (this.f10632n != null && bool.booleanValue()) {
                    this.f10632n.m11029a(this.f10634p);
                    C1189f.m8335e().m8021a(this.f10634p.m12815a());
                }
                ofInt2.setDuration(250);
                ofInt.setDuration(250);
                ofInt2.start();
                ofInt.start();
            case f10613t /*2*/:
                if (chVar == ch.DOWN) {
                    iArr = new int[f10613t];
                    iArr[f10611r] = this.f10626h[f10611r];
                    iArr[f10612s] = this.f10626h[f10612s];
                    ofInt2 = ValueAnimator.ofInt(iArr);
                    this.f10631m[f10611r].setTextColor(f10615y);
                    this.f10631m[f10612s].setTextColor(f10616z);
                    this.f10631m[f10613t].setTextColor(f10616z);
                    this.f10634p = ci.Record;
                } else {
                    iArr = new int[f10613t];
                    iArr[f10611r] = this.f10626h[f10612s];
                    iArr[f10612s] = this.f10626h[f10611r];
                    ofInt2 = ValueAnimator.ofInt(iArr);
                    this.f10631m[f10611r].setTextColor(f10616z);
                    this.f10631m[f10612s].setTextColor(f10615y);
                    this.f10631m[f10613t].setTextColor(f10616z);
                    this.f10634p = ci.TakePhoto;
                }
                ofInt2.setDuration(500);
                ofInt2.addUpdateListener(new ce(this));
                ofInt.addUpdateListener(new cf(this));
                if (this.f10632n != null && bool.booleanValue()) {
                    this.f10632n.m11029a(this.f10634p);
                    C1189f.m8335e().m8021a(this.f10634p.m12815a());
                }
                ofInt2.setDuration(250);
                ofInt.setDuration(250);
                ofInt2.start();
                ofInt.start();
            default:
        }
    }

    public void m12726a(Context context) {
        int i;
        this.f10619a = (int) getResources().getDimension(C1205R.dimen.updownsliding_top_margin);
        this.f10621c = (int) getResources().getDimension(C1205R.dimen.updownsliding_left_margin);
        this.f10622d = (int) getResources().getDimension(C1205R.dimen.updownsliding_left_second_margin);
        this.f10620b = (int) getResources().getDimension(C1205R.dimen.updownaliding_textsize);
        int[] iArr = new int[f10614u];
        iArr[f10611r] = this.f10619a;
        iArr[f10612s] = this.f10619a * f10613t;
        iArr[f10613t] = this.f10619a * f10614u;
        this.f10625g = iArr;
        iArr = new int[f10614u];
        iArr[f10611r] = this.f10621c;
        iArr[f10612s] = this.f10621c + this.f10622d;
        iArr[f10613t] = this.f10621c;
        this.f10626h = iArr;
        iArr = new int[f10614u];
        iArr[f10611r] = f10611r;
        iArr[f10612s] = this.f10619a;
        iArr[f10613t] = this.f10619a * f10613t;
        this.f10627i = iArr;
        View relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new LayoutParams(-2, -2));
        this.f10628j = new LinearLayout[f10614u];
        this.f10631m = new TextView[f10614u];
        for (i = f10611r; i < this.f10624f.length; i += f10612s) {
            this.f10628j[i] = new LinearLayout(context);
            this.f10631m[i] = new TextView(context);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            this.f10631m[i].setTextColor(-1);
            this.f10631m[i].setText(this.f10624f[i]);
            this.f10631m[i].setTextSize(f10611r, (float) this.f10620b);
            this.f10631m[i].setTextColor(f10616z);
            this.f10631m[i].setLayoutParams(layoutParams);
            this.f10631m[i].setOnClickListener(new cb(this, i));
            AssetManager assets = context.getAssets();
            View[] viewArr = new View[f10612s];
            viewArr[f10611r] = this.f10631m[i];
            be.m12359a(assets, viewArr);
            this.f10631m[i].getPaint().setFakeBoldText(true);
            this.f10628j[i].setId(i + 100);
            this.f10628j[i].addView(this.f10631m[i]);
            layoutParams = new LayoutParams(-1, -1);
            layoutParams.topMargin = this.f10625g[i];
            layoutParams.leftMargin = this.f10626h[i];
            this.f10628j[i].setLayoutParams(layoutParams);
            relativeLayout.addView(this.f10628j[i]);
        }
        relativeLayout.measure(MeasureSpec.makeMeasureSpec(f10611r, f10611r), MeasureSpec.makeMeasureSpec(f10611r, f10611r));
        int measuredHeight = this.f10631m[f10611r].getMeasuredHeight();
        i = this.f10631m[f10611r].getMeasuredWidth();
        this.f10631m[f10612s].setTextColor(f10615y);
        if (C1325k.m8930a().m8942g()) {
            this.f10631m[f10613t].setVisibility(8);
        } else {
            this.f10631m[f10613t].setVisibility(f10611r);
        }
        this.f10629k = new ImageView(context);
        this.f10629k.setBackgroundResource(C1205R.drawable.mode_selected_dot);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        int dimension = (int) getResources().getDimension(C1205R.dimen.updownsliding_dot_diameter);
        layoutParams2.height = dimension;
        layoutParams2.width = dimension;
        layoutParams2.topMargin = ((this.f10619a * f10613t) + (measuredHeight / f10613t)) - (dimension / f10613t);
        layoutParams2.leftMargin = (int) ((((float) (i + this.f10626h[f10612s])) + getResources().getDimension(C1205R.dimen.updownsliding_dot_text)) + ((float) (dimension / 5)));
        this.f10629k.setLayoutParams(layoutParams2);
        relativeLayout.addView(this.f10629k);
        addView(relativeLayout);
        this.f10636v = (LayoutParams) this.f10628j[f10611r].getLayoutParams();
        this.f10638x = (LayoutParams) this.f10628j[f10612s].getLayoutParams();
        this.f10637w = (LayoutParams) this.f10628j[f10613t].getLayoutParams();
        LayoutParams layoutParams3 = (LayoutParams) relativeLayout.getLayoutParams();
        layoutParams3.height = ((this.f10619a * f10613t) + (measuredHeight / f10613t)) * f10613t;
        relativeLayout.setLayoutParams(layoutParams3);
    }

    public void m12727b(Context context) {
        if (C1325k.m8930a().m8942g()) {
            this.f10631m[f10613t].setVisibility(8);
        } else {
            this.f10631m[f10613t].setVisibility(f10611r);
        }
    }

    public ci getCurrentState() {
        ci ciVar = ci.Record;
        switch (C1189f.m8335e().m8025b()) {
            case f10611r /*0*/:
                return ci.Record;
            case f10612s /*1*/:
                return ci.TakePhoto;
            case f10613t /*2*/:
                return ci.Live;
            default:
                return ciVar;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case f10611r /*0*/:
                if (this.f10630l == null) {
                    this.f10630l = VelocityTracker.obtain();
                } else {
                    this.f10630l.clear();
                }
                this.f10630l.addMovement(motionEvent);
                this.f10623e = true;
                break;
            case f10612s /*1*/:
                this.f10630l.recycle();
                this.f10630l = null;
                break;
            case f10613t /*2*/:
                this.f10630l.addMovement(motionEvent);
                this.f10630l.computeCurrentVelocity(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER);
                if (Math.abs(this.f10630l.getYVelocity()) < TitleBar.SHAREBTN_RIGHT_MARGIN) {
                    return super.onTouchEvent(motionEvent);
                }
                if (this.f10623e) {
                    this.f10623e = false;
                    int i;
                    if (this.f10630l.getYVelocity() > 0.0f) {
                        i = f10611r;
                        while (i < this.f10627i.length) {
                            if (this.f10627i[i] == this.f10636v.topMargin) {
                                if (i != f10613t) {
                                    if (i + f10612s == f10612s) {
                                        this.f10617A = ci.TakePhoto;
                                    }
                                    if (i + f10612s == f10613t) {
                                        this.f10617A = ci.Record;
                                    }
                                    m12725a(i + f10612s, ch.DOWN, Boolean.valueOf(true));
                                    break;
                                }
                                return super.onTouchEvent(motionEvent);
                            }
                            i += f10612s;
                        }
                        i = f10611r;
                        if (i != f10613t) {
                            return super.onTouchEvent(motionEvent);
                        }
                        if (i + f10612s == f10612s) {
                            this.f10617A = ci.TakePhoto;
                        }
                        if (i + f10612s == f10613t) {
                            this.f10617A = ci.Record;
                        }
                        m12725a(i + f10612s, ch.DOWN, Boolean.valueOf(true));
                    } else if (this.f10630l.getYVelocity() < 0.0f) {
                        i = f10611r;
                        while (i < this.f10627i.length) {
                            if (this.f10627i[i] == this.f10636v.topMargin) {
                                if (i != 0) {
                                    if (i == f10612s) {
                                        this.f10617A = ci.Live;
                                        if (C1325k.m8930a().m8942g()) {
                                            return super.onTouchEvent(motionEvent);
                                        }
                                    }
                                    if (i == f10613t) {
                                        this.f10617A = ci.TakePhoto;
                                    }
                                    m12725a(i, ch.UP, Boolean.valueOf(true));
                                    break;
                                }
                                return super.onTouchEvent(motionEvent);
                            }
                            i += f10612s;
                        }
                        i = f10611r;
                        if (i != 0) {
                            return super.onTouchEvent(motionEvent);
                        }
                        if (i == f10612s) {
                            this.f10617A = ci.Live;
                            if (C1325k.m8930a().m8942g()) {
                                return super.onTouchEvent(motionEvent);
                            }
                        }
                        if (i == f10613t) {
                            this.f10617A = ci.TakePhoto;
                        }
                        m12725a(i, ch.UP, Boolean.valueOf(true));
                    }
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOnUpDownSliding(cj cjVar) {
        this.f10632n = cjVar;
    }

    public void setState(ci ciVar) {
        if (ciVar != this.f10617A) {
            this.f10617A = ciVar;
            if (this.f10633o.booleanValue()) {
                m12718a(this.f10634p, ciVar);
                return;
            }
            this.f10633o = Boolean.valueOf(true);
            switch (cg.f10751a[ciVar.ordinal()]) {
                case f10612s /*1*/:
                    this.f10636v.topMargin = this.f10625g[f10612s];
                    this.f10638x.topMargin = this.f10625g[f10613t];
                    this.f10637w.topMargin = this.f10619a * 4;
                    this.f10636v.leftMargin = this.f10626h[f10612s];
                    this.f10638x.leftMargin = this.f10626h[f10613t];
                    this.f10637w.leftMargin = f10611r;
                    this.f10631m[f10611r].setTextColor(f10615y);
                    this.f10631m[f10612s].setTextColor(f10616z);
                    this.f10631m[f10613t].setTextColor(f10616z);
                    this.f10634p = ci.Record;
                    break;
                case f10613t /*2*/:
                    this.f10636v.topMargin = this.f10625g[f10611r];
                    this.f10638x.topMargin = this.f10625g[f10612s];
                    this.f10637w.topMargin = this.f10625g[f10613t];
                    this.f10636v.leftMargin = this.f10626h[f10611r];
                    this.f10638x.leftMargin = this.f10626h[f10612s];
                    this.f10637w.leftMargin = this.f10626h[f10613t];
                    this.f10631m[f10611r].setTextColor(f10616z);
                    this.f10631m[f10612s].setTextColor(f10615y);
                    this.f10631m[f10613t].setTextColor(f10616z);
                    this.f10634p = ci.TakePhoto;
                    break;
                case f10614u /*3*/:
                    this.f10636v.topMargin = f10611r;
                    this.f10638x.topMargin = this.f10625g[f10611r];
                    this.f10637w.topMargin = this.f10625g[f10612s];
                    this.f10636v.leftMargin = f10611r;
                    this.f10638x.leftMargin = this.f10626h[f10611r];
                    this.f10637w.leftMargin = this.f10626h[f10612s];
                    this.f10631m[f10611r].setTextColor(f10616z);
                    this.f10631m[f10612s].setTextColor(f10616z);
                    this.f10631m[f10613t].setTextColor(f10615y);
                    this.f10634p = ci.Live;
                    break;
            }
            this.f10628j[f10611r].setLayoutParams(this.f10636v);
            this.f10628j[f10612s].setLayoutParams(this.f10638x);
            this.f10628j[f10613t].setLayoutParams(this.f10637w);
        }
    }
}
