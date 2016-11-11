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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.fimi.soul.C1205R;
import it.p074a.p075a.C2799f;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import p147m.framework.ui.widget.asyncview.AsyncImageView;

public class UIRefreshListView extends ListView implements OnScrollListener {
    private static final String f10570a = "MM-dd HH:mm";
    private static final int f10571b = 3;
    private static final int f10572c = 0;
    private static final int f10573d = 1;
    private static final int f10574e = 2;
    private static final int f10575f = 3;
    private static final int f10576g = 4;
    private static final int f10577h = 1;
    private static final int f10578i = 2;
    private static final int f10579j = 3;
    private RotateAnimation f10580A;
    private boolean f10581B;
    private int f10582C;
    private int f10583D;
    private int f10584E;
    private boolean f10585F;
    private int f10586G;
    private int f10587H;
    private int f10588I;
    private boolean f10589J;
    private bz f10590K;
    private by f10591L;
    private bx f10592M;
    private int f10593k;
    private int f10594l;
    private boolean f10595m;
    private boolean f10596n;
    private boolean f10597o;
    private boolean f10598p;
    private LayoutInflater f10599q;
    private LinearLayout f10600r;
    private TextView f10601s;
    private TextView f10602t;
    private ImageView f10603u;
    private ProgressBar f10604v;
    private View f10605w;
    private ProgressBar f10606x;
    private TextView f10607y;
    private RotateAnimation f10608z;

    public UIRefreshListView(Context context) {
        super(context);
        this.f10595m = false;
        this.f10596n = false;
        this.f10597o = true;
        this.f10598p = false;
        m12694a(context);
    }

    public UIRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10595m = false;
        this.f10596n = false;
        this.f10597o = true;
        this.f10598p = false;
        m12694a(context);
    }

    public UIRefreshListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10595m = false;
        this.f10596n = false;
        this.f10597o = true;
        this.f10598p = false;
        m12694a(context);
    }

    private void m12693a(int i) {
        if (i <= 0) {
            i = C2799f.f14257G;
        }
        Interpolator linearInterpolator = new LinearInterpolator();
        this.f10608z = new RotateAnimation(0.0f, -180.0f, f10577h, 0.5f, f10577h, 0.5f);
        this.f10608z.setInterpolator(linearInterpolator);
        this.f10608z.setDuration((long) i);
        this.f10608z.setFillAfter(true);
        this.f10580A = new RotateAnimation(-180.0f, 0.0f, f10577h, 0.5f, f10577h, 0.5f);
        this.f10580A.setInterpolator(linearInterpolator);
        this.f10580A.setDuration((long) i);
        this.f10580A.setFillAfter(true);
    }

    private void m12694a(Context context) {
        setCacheColorHint(context.getResources().getColor(AsyncImageView.f14604a));
        this.f10599q = LayoutInflater.from(context);
        m12703h();
        m12704i();
        setOnScrollListener(this);
        m12693a((int) f10572c);
    }

    private void m12695a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(f10572c, f10572c, layoutParams.width);
        int i = layoutParams.height;
        view.measure(childMeasureSpec, i > 0 ? MeasureSpec.makeMeasureSpec(i, 1073741824) : MeasureSpec.makeMeasureSpec(f10572c, f10572c));
    }

    private void m12703h() {
        this.f10600r = (LinearLayout) this.f10599q.inflate(C1205R.layout.rebound_listview_header, null);
        this.f10603u = (ImageView) this.f10600r.findViewById(C1205R.id.head_arrowImageView);
        this.f10603u.setMinimumWidth(70);
        this.f10603u.setMinimumHeight(50);
        this.f10604v = (ProgressBar) this.f10600r.findViewById(C1205R.id.head_progressBar);
        this.f10601s = (TextView) this.f10600r.findViewById(C1205R.id.head_tipsTextView);
        this.f10602t = (TextView) this.f10600r.findViewById(C1205R.id.head_lastUpdatedTextView);
        m12695a(this.f10600r);
        this.f10583D = this.f10600r.getMeasuredHeight();
        this.f10582C = this.f10600r.getMeasuredWidth();
        this.f10600r.setPadding(f10572c, this.f10583D * -1, f10572c, f10572c);
        this.f10600r.invalidate();
        Log.v("size", "width:" + this.f10582C + " height:" + this.f10583D);
        addHeaderView(this.f10600r, null, false);
        this.f10593k = f10579j;
    }

    private void m12704i() {
        this.f10605w = this.f10599q.inflate(C1205R.layout.rebound_listview_footer, null);
        this.f10605w.setVisibility(f10572c);
        this.f10606x = (ProgressBar) this.f10605w.findViewById(C1205R.id.pull_to_refresh_progress);
        this.f10607y = (TextView) this.f10605w.findViewById(C1205R.id.load_more);
        this.f10605w.setOnClickListener(new bw(this));
        addFooterView(this.f10605w);
        if (this.f10597o) {
            this.f10594l = f10579j;
        } else {
            this.f10594l = f10578i;
        }
    }

    private void m12705j() {
        if (this.f10595m) {
            switch (this.f10594l) {
                case f10577h /*1*/:
                    if (!this.f10607y.getText().equals(Integer.valueOf(C1205R.string.refresh_doing_end_refresh))) {
                        this.f10607y.setText(C1205R.string.refresh_doing_end_refresh);
                        this.f10607y.setVisibility(f10572c);
                        this.f10606x.setVisibility(f10572c);
                    }
                case f10578i /*2*/:
                    this.f10607y.setText(C1205R.string.refresh_end_click_load_more);
                    this.f10607y.setVisibility(f10572c);
                    this.f10606x.setVisibility(8);
                    this.f10605w.setVisibility(f10572c);
                case f10579j /*3*/:
                    this.f10607y.setText(C1205R.string.refresh_end_load_more);
                    this.f10607y.setVisibility(f10572c);
                    this.f10606x.setVisibility(8);
                    this.f10605w.setVisibility(f10572c);
                default:
            }
        }
    }

    private void m12706k() {
        switch (this.f10593k) {
            case f10572c /*0*/:
                this.f10603u.setVisibility(f10572c);
                this.f10604v.setVisibility(8);
                this.f10601s.setVisibility(f10572c);
                this.f10602t.setVisibility(f10572c);
                this.f10603u.clearAnimation();
                this.f10603u.startAnimation(this.f10608z);
                this.f10601s.setText(C1205R.string.refresh_release_refresh);
            case f10577h /*1*/:
                this.f10604v.setVisibility(8);
                this.f10601s.setVisibility(f10572c);
                this.f10602t.setVisibility(f10572c);
                this.f10603u.clearAnimation();
                this.f10603u.setVisibility(f10572c);
                if (this.f10585F) {
                    this.f10585F = false;
                    this.f10603u.clearAnimation();
                    this.f10603u.startAnimation(this.f10580A);
                    this.f10601s.setText(C1205R.string.refresh_pull_to_refresh);
                    return;
                }
                this.f10601s.setText(C1205R.string.refresh_pull_to_refresh);
            case f10578i /*2*/:
                this.f10600r.setPadding(f10572c, f10572c, f10572c, f10572c);
                this.f10604v.setVisibility(f10572c);
                this.f10603u.clearAnimation();
                this.f10603u.setVisibility(8);
                this.f10601s.setText(C1205R.string.refresh_doing_head_refresh);
                this.f10602t.setVisibility(f10572c);
            case f10579j /*3*/:
                this.f10600r.setPadding(f10572c, this.f10583D * -1, f10572c, f10572c);
                this.f10604v.setVisibility(8);
                this.f10603u.clearAnimation();
                this.f10603u.setImageResource(C1205R.drawable.uet_rebound_listview_arrow);
                this.f10601s.setText(C1205R.string.refresh_pull_to_refresh);
                this.f10602t.setVisibility(f10572c);
            default:
        }
    }

    private void m12707l() {
        if (this.f10590K != null) {
            this.f10590K.m12808a();
        }
    }

    private void m12708m() {
        if (this.f10591L != null) {
            this.f10607y.setText(C1205R.string.refresh_doing_end_refresh);
            this.f10607y.setVisibility(f10572c);
            this.f10606x.setVisibility(f10572c);
            this.f10591L.m12807a();
        }
    }

    public boolean m12709a() {
        return this.f10595m;
    }

    public boolean m12710b() {
        return this.f10596n;
    }

    public boolean m12711c() {
        return this.f10597o;
    }

    public boolean m12712d() {
        return this.f10598p;
    }

    public void m12713e() {
        if (this.f10598p) {
            setSelection(f10572c);
        }
        this.f10593k = f10579j;
        this.f10602t.setText(getResources().getString(C1205R.string.refresh_refresh_lasttime) + new SimpleDateFormat(f10570a, Locale.CHINA).format(new Date()));
        m12706k();
    }

    public void m12714f() {
        if (this.f10597o) {
            this.f10594l = f10579j;
        } else {
            this.f10594l = f10578i;
        }
        m12705j();
    }

    public void m12715g() {
        removeFooterView(this.f10605w);
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.f10586G = i;
        this.f10587H = (i + i2) - 2;
        this.f10588I = i3 - 2;
        if (i3 > i2) {
            this.f10589J = true;
        } else {
            this.f10589J = false;
        }
        if (this.f10592M != null) {
            this.f10592M.m12806a(absListView, i, i2, i3);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.f10595m) {
            if (this.f10587H == this.f10588I && i == 0 && this.f10594l != f10577h) {
                if (!this.f10597o) {
                    this.f10594l = f10578i;
                    m12705j();
                } else if (!this.f10596n) {
                    this.f10594l = f10577h;
                    m12708m();
                    m12705j();
                } else if (this.f10593k != f10578i) {
                    this.f10594l = f10577h;
                    m12708m();
                    m12705j();
                }
            }
        } else if (this.f10605w != null && this.f10605w.getVisibility() == 0) {
            System.out.println("this.removeFooterView(endRootView);...");
            this.f10605w.setVisibility(8);
            removeFooterView(this.f10605w);
        }
        if (this.f10592M != null) {
            this.f10592M.m12805a(absListView, i);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f10596n) {
            if (!this.f10595m || this.f10594l != f10577h) {
                switch (motionEvent.getAction()) {
                    case f10572c /*0*/:
                        if (this.f10586G == 0 && !this.f10581B) {
                            this.f10581B = true;
                            this.f10584E = (int) motionEvent.getY();
                            break;
                        }
                    case f10577h /*1*/:
                        if (!(this.f10593k == f10578i || this.f10593k == f10576g)) {
                            if (this.f10593k == f10579j) {
                            }
                            if (this.f10593k == f10577h) {
                                this.f10593k = f10579j;
                                m12706k();
                            }
                            if (this.f10593k == 0) {
                                this.f10593k = f10578i;
                                m12706k();
                                m12707l();
                            }
                        }
                        this.f10581B = false;
                        this.f10585F = false;
                        break;
                    case f10578i /*2*/:
                        int y = (int) motionEvent.getY();
                        if (!this.f10581B && this.f10586G == 0) {
                            this.f10581B = true;
                            this.f10584E = y;
                        }
                        if (!(this.f10593k == f10578i || !this.f10581B || this.f10593k == f10576g)) {
                            if (this.f10593k == 0) {
                                setSelection(f10572c);
                                if ((y - this.f10584E) / f10579j < this.f10583D && y - this.f10584E > 0) {
                                    this.f10593k = f10577h;
                                    m12706k();
                                } else if (y - this.f10584E <= 0) {
                                    this.f10593k = f10579j;
                                    m12706k();
                                }
                            }
                            if (this.f10593k == f10577h) {
                                setSelection(f10572c);
                                if ((y - this.f10584E) / f10579j >= this.f10583D) {
                                    this.f10593k = f10572c;
                                    this.f10585F = true;
                                    m12706k();
                                } else if (y - this.f10584E <= 0) {
                                    this.f10593k = f10579j;
                                    m12706k();
                                }
                            }
                            if (this.f10593k == f10579j && y - this.f10584E > 0) {
                                this.f10593k = f10577h;
                                m12706k();
                            }
                            if (this.f10593k == f10577h) {
                                this.f10600r.setPadding(f10572c, (this.f10583D * -1) + ((y - this.f10584E) / f10579j), f10572c, f10572c);
                            }
                            if (this.f10593k == 0) {
                                this.f10600r.setPadding(f10572c, ((y - this.f10584E) / f10579j) - this.f10583D, f10572c, f10572c);
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
        this.f10602t.setText(getResources().getString(C1205R.string.refresh_refresh_lasttime) + new SimpleDateFormat(f10570a, Locale.CHINA).format(new Date()));
        super.setAdapter(baseAdapter);
    }

    public void setAutoLoadMore(boolean z) {
        this.f10597o = z;
    }

    public void setCanLoadMore(boolean z) {
        this.f10595m = z;
        if (this.f10605w == null) {
            m12704i();
        }
        this.f10605w.post(new bv(this, z));
    }

    public void setCanRefresh(boolean z) {
        this.f10596n = z;
    }

    public void setListViewOnScrollListener(bx bxVar) {
        this.f10592M = bxVar;
    }

    public void setMoveToFirstItemAfterRefresh(boolean z) {
        this.f10598p = z;
    }

    public void setOnLoadListener(by byVar) {
        if (byVar != null) {
            this.f10591L = byVar;
            if (this.f10595m && getFooterViewsCount() == 0) {
                m12704i();
            }
        }
    }

    public void setOnRefreshListener(bz bzVar) {
        if (bzVar != null) {
            this.f10590K = bzVar;
        }
    }
}
