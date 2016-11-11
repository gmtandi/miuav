package com.fimi.soul.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import it.p074a.p075a.C2799f;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import p147m.framework.ui.widget.asyncview.AsyncImageView;

public class UIRefreshEXPView extends ExpandableListView implements OnScrollListener {
    private static final String f10531a = "MM-dd HH:mm";
    private static final int f10532b = 3;
    private static final int f10533c = 0;
    private static final int f10534d = 1;
    private static final int f10535e = 2;
    private static final int f10536f = 3;
    private static final int f10537g = 4;
    private static final int f10538h = 1;
    private static final int f10539i = 2;
    private static final int f10540j = 3;
    private RotateAnimation f10541A;
    private boolean f10542B;
    private int f10543C;
    private int f10544D;
    private int f10545E;
    private boolean f10546F;
    private int f10547G;
    private int f10548H;
    private int f10549I;
    private boolean f10550J;
    private bu f10551K;
    private bt f10552L;
    private bs f10553M;
    private int f10554k;
    private int f10555l;
    private boolean f10556m;
    private boolean f10557n;
    private boolean f10558o;
    private boolean f10559p;
    private LayoutInflater f10560q;
    private LinearLayout f10561r;
    private TextView f10562s;
    private TextView f10563t;
    private ImageView f10564u;
    private ProgressBar f10565v;
    private View f10566w;
    private ProgressBar f10567x;
    private TextView f10568y;
    private RotateAnimation f10569z;

    public UIRefreshEXPView(Context context) {
        super(context);
        this.f10556m = false;
        this.f10557n = false;
        this.f10558o = true;
        this.f10559p = false;
        m12670a(context);
    }

    public UIRefreshEXPView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10556m = false;
        this.f10557n = false;
        this.f10558o = true;
        this.f10559p = false;
        m12670a(context);
    }

    public UIRefreshEXPView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10556m = false;
        this.f10557n = false;
        this.f10558o = true;
        this.f10559p = false;
        m12670a(context);
    }

    private void m12669a(int i) {
        if (i <= 0) {
            i = C2799f.f14257G;
        }
        Interpolator linearInterpolator = new LinearInterpolator();
        this.f10569z = new RotateAnimation(0.0f, -180.0f, f10538h, 0.5f, f10538h, 0.5f);
        this.f10569z.setInterpolator(linearInterpolator);
        this.f10569z.setDuration((long) i);
        this.f10569z.setFillAfter(true);
        this.f10541A = new RotateAnimation(-180.0f, 0.0f, f10538h, 0.5f, f10538h, 0.5f);
        this.f10541A.setInterpolator(linearInterpolator);
        this.f10541A.setDuration((long) i);
        this.f10541A.setFillAfter(true);
    }

    private void m12670a(Context context) {
        setCacheColorHint(context.getResources().getColor(AsyncImageView.f14604a));
        this.f10560q = LayoutInflater.from(context);
        m12679h();
        m12680i();
        setOnScrollListener(this);
        m12669a((int) f10533c);
    }

    private void m12671a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(f10533c, f10533c, layoutParams.width);
        int i = layoutParams.height;
        view.measure(childMeasureSpec, i > 0 ? MeasureSpec.makeMeasureSpec(i, 1073741824) : MeasureSpec.makeMeasureSpec(f10533c, f10533c));
    }

    private void m12679h() {
        this.f10561r = (LinearLayout) this.f10560q.inflate(C1205R.layout.rebound_listview_header, null);
        this.f10564u = (ImageView) this.f10561r.findViewById(C1205R.id.head_arrowImageView);
        this.f10564u.setMinimumWidth(70);
        this.f10564u.setMinimumHeight(50);
        this.f10565v = (ProgressBar) this.f10561r.findViewById(C1205R.id.head_progressBar);
        this.f10562s = (TextView) this.f10561r.findViewById(C1205R.id.head_tipsTextView);
        this.f10563t = (TextView) this.f10561r.findViewById(C1205R.id.head_lastUpdatedTextView);
        m12671a(this.f10561r);
        this.f10544D = this.f10561r.getMeasuredHeight();
        this.f10543C = this.f10561r.getMeasuredWidth();
        this.f10561r.setPadding(f10533c, this.f10544D * -1, f10533c, f10533c);
        this.f10561r.invalidate();
        Log.v("size", "width:" + this.f10543C + " height:" + this.f10544D);
        addHeaderView(this.f10561r, null, false);
        this.f10554k = f10540j;
    }

    private void m12680i() {
        this.f10566w = this.f10560q.inflate(C1205R.layout.rebound_listview_footer, null);
        this.f10566w.setVisibility(f10533c);
        this.f10567x = (ProgressBar) this.f10566w.findViewById(C1205R.id.pull_to_refresh_progress);
        this.f10568y = (TextView) this.f10566w.findViewById(C1205R.id.load_more);
        this.f10566w.setOnClickListener(new br(this));
        addFooterView(this.f10566w);
        if (this.f10558o) {
            this.f10555l = f10540j;
        } else {
            this.f10555l = f10539i;
        }
    }

    private void m12681j() {
        if (this.f10556m) {
            switch (this.f10555l) {
                case f10538h /*1*/:
                    if (!this.f10568y.getText().equals(Integer.valueOf(C1205R.string.refresh_doing_end_refresh))) {
                        this.f10568y.setText(C1205R.string.refresh_doing_end_refresh);
                        this.f10568y.setVisibility(f10533c);
                        this.f10567x.setVisibility(f10533c);
                    }
                case f10539i /*2*/:
                    this.f10568y.setText(C1205R.string.refresh_end_click_load_more);
                    this.f10568y.setVisibility(f10533c);
                    this.f10567x.setVisibility(8);
                    this.f10566w.setVisibility(f10533c);
                case f10540j /*3*/:
                    this.f10568y.setText(C1205R.string.refresh_end_load_more);
                    this.f10568y.setVisibility(f10533c);
                    this.f10567x.setVisibility(8);
                    this.f10566w.setVisibility(f10533c);
                default:
            }
        }
    }

    private void m12682k() {
        switch (this.f10554k) {
            case f10533c /*0*/:
                this.f10564u.setVisibility(f10533c);
                this.f10565v.setVisibility(8);
                this.f10562s.setVisibility(f10533c);
                this.f10563t.setVisibility(f10533c);
                this.f10564u.clearAnimation();
                this.f10564u.startAnimation(this.f10569z);
                this.f10562s.setText(C1205R.string.refresh_release_refresh);
            case f10538h /*1*/:
                this.f10565v.setVisibility(8);
                this.f10562s.setVisibility(f10533c);
                this.f10563t.setVisibility(f10533c);
                this.f10564u.clearAnimation();
                this.f10564u.setVisibility(f10533c);
                if (this.f10546F) {
                    this.f10546F = false;
                    this.f10564u.clearAnimation();
                    this.f10564u.startAnimation(this.f10541A);
                    this.f10562s.setText(C1205R.string.refresh_pull_to_refresh);
                    return;
                }
                this.f10562s.setText(C1205R.string.refresh_pull_to_refresh);
            case f10539i /*2*/:
                this.f10561r.setPadding(f10533c, f10533c, f10533c, f10533c);
                this.f10565v.setVisibility(f10533c);
                this.f10564u.clearAnimation();
                this.f10564u.setVisibility(8);
                this.f10562s.setText(C1205R.string.refresh_doing_head_refresh);
                this.f10563t.setVisibility(f10533c);
            case f10540j /*3*/:
                this.f10561r.setPadding(f10533c, this.f10544D * -1, f10533c, f10533c);
                this.f10565v.setVisibility(8);
                this.f10564u.clearAnimation();
                this.f10564u.setImageResource(C1205R.drawable.uet_rebound_listview_arrow);
                this.f10562s.setText(C1205R.string.refresh_pull_to_refresh);
                this.f10563t.setVisibility(f10533c);
            default:
        }
    }

    private void m12683l() {
        if (this.f10551K != null) {
            this.f10551K.m12804a();
        }
    }

    private void m12684m() {
        if (this.f10552L != null) {
            this.f10568y.setText(C1205R.string.refresh_doing_end_refresh);
            this.f10568y.setVisibility(f10533c);
            this.f10567x.setVisibility(f10533c);
            this.f10552L.m12803a();
        }
    }

    public boolean m12685a() {
        return this.f10556m;
    }

    public boolean m12686b() {
        return this.f10557n;
    }

    public boolean m12687c() {
        return this.f10558o;
    }

    public boolean m12688d() {
        return this.f10559p;
    }

    public void m12689e() {
        if (this.f10559p) {
            setSelection(f10533c);
        }
        this.f10554k = f10540j;
        this.f10563t.setText(getResources().getString(C1205R.string.refresh_refresh_lasttime) + new SimpleDateFormat(f10531a, Locale.CHINA).format(new Date()));
        m12682k();
    }

    public void m12690f() {
        if (this.f10558o) {
            this.f10555l = f10540j;
        } else {
            this.f10555l = f10539i;
        }
        m12681j();
    }

    public void m12691g() {
        removeFooterView(this.f10566w);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.f10547G = i;
        this.f10548H = (i + i2) - 2;
        this.f10549I = i3 - 2;
        if (i3 > i2) {
            this.f10550J = true;
        } else {
            this.f10550J = false;
        }
        if (this.f10553M != null) {
            this.f10553M.m12802a(absListView, i, i2, i3);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.f10556m) {
            if (this.f10548H == this.f10549I && i == 0 && this.f10555l != f10538h) {
                if (!this.f10558o) {
                    this.f10555l = f10539i;
                    m12681j();
                } else if (!this.f10557n) {
                    this.f10555l = f10538h;
                    m12684m();
                    m12681j();
                } else if (this.f10554k != f10539i) {
                    this.f10555l = f10538h;
                    m12684m();
                    m12681j();
                }
            }
        } else if (this.f10566w != null && this.f10566w.getVisibility() == 0) {
            System.out.println("this.removeFooterView(endRootView);...");
            this.f10566w.setVisibility(8);
            removeFooterView(this.f10566w);
        }
        if (this.f10553M != null) {
            this.f10553M.m12801a(absListView, i);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f10557n) {
            if (!this.f10556m || this.f10555l != f10538h) {
                switch (motionEvent.getAction()) {
                    case f10533c /*0*/:
                        if (this.f10547G == 0 && !this.f10542B) {
                            this.f10542B = true;
                            this.f10545E = (int) motionEvent.getY();
                            break;
                        }
                    case f10538h /*1*/:
                        if (!(this.f10554k == f10539i || this.f10554k == f10537g)) {
                            if (this.f10554k == f10540j) {
                            }
                            if (this.f10554k == f10538h) {
                                this.f10554k = f10540j;
                                m12682k();
                            }
                            if (this.f10554k == 0) {
                                this.f10554k = f10539i;
                                m12682k();
                                m12683l();
                            }
                        }
                        this.f10542B = false;
                        this.f10546F = false;
                        break;
                    case f10539i /*2*/:
                        int y = (int) motionEvent.getY();
                        if (!this.f10542B && this.f10547G == 0) {
                            this.f10542B = true;
                            this.f10545E = y;
                        }
                        if (!(this.f10554k == f10539i || !this.f10542B || this.f10554k == f10537g)) {
                            if (this.f10554k == 0) {
                                setSelection(f10533c);
                                if ((y - this.f10545E) / f10540j < this.f10544D && y - this.f10545E > 0) {
                                    this.f10554k = f10538h;
                                    m12682k();
                                } else if (y - this.f10545E <= 0) {
                                    this.f10554k = f10540j;
                                    m12682k();
                                }
                            }
                            if (this.f10554k == f10538h) {
                                setSelection(f10533c);
                                if ((y - this.f10545E) / f10540j >= this.f10544D) {
                                    this.f10554k = f10533c;
                                    this.f10546F = true;
                                    m12682k();
                                } else if (y - this.f10545E <= 0) {
                                    this.f10554k = f10540j;
                                    m12682k();
                                }
                            }
                            if (this.f10554k == f10540j && y - this.f10545E > 0) {
                                this.f10554k = f10538h;
                                m12682k();
                            }
                            if (this.f10554k == f10538h) {
                                this.f10561r.setPadding(f10533c, (this.f10544D * -1) + ((y - this.f10545E) / f10540j), f10533c, f10533c);
                            }
                            if (this.f10554k == 0) {
                                this.f10561r.setPadding(f10533c, ((y - this.f10545E) / f10540j) - this.f10544D, f10533c, f10533c);
                                break;
                            }
                        }
                        break;
                }
            }
            return super.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        this.f10563t.setText(getResources().getString(C1205R.string.refresh_refresh_lasttime) + new SimpleDateFormat(f10531a, Locale.CHINA).format(new Date()));
        super.setAdapter(baseAdapter);
    }

    public void setAutoLoadMore(boolean z) {
        this.f10558o = z;
    }

    public void setCanLoadMore(boolean z) {
        this.f10556m = z;
        if (this.f10566w == null) {
            m12680i();
        }
        this.f10566w.post(new bq(this, z));
    }

    public void setCanRefresh(boolean z) {
        this.f10557n = z;
    }

    public void setListViewOnScrollListener(bs bsVar) {
        this.f10553M = bsVar;
    }

    public void setMoveToFirstItemAfterRefresh(boolean z) {
        this.f10559p = z;
    }

    public void setOnLoadListener(bt btVar) {
        if (btVar != null) {
            this.f10552L = btVar;
            if (this.f10556m && getFooterViewsCount() == 0) {
                m12680i();
            }
        }
    }

    public void setOnRefreshListener(bu buVar) {
        if (buVar != null) {
            this.f10551K = buVar;
        }
    }
}
