package com.fimi.soul.media.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import com.fimi.soul.C1205R;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HorizontalListView extends AdapterView<ListAdapter> {
    private static final int f7731e = -1;
    private static final int f7732f = 0;
    private static final float f7733g = 30.0f;
    private static final float f7734h = 0.009f;
    private static final String f7735i = "BUNDLE_ID_CURRENT_X";
    private static final String f7736j = "BUNDLE_ID_PARENT_STATE";
    private boolean f7737A;
    private af f7738B;
    private ag f7739C;
    private EdgeEffectCompat f7740D;
    private EdgeEffectCompat f7741E;
    private int f7742F;
    private boolean f7743G;
    private boolean f7744H;
    private OnClickListener f7745I;
    private DataSetObserver f7746J;
    private Runnable f7747K;
    protected Scroller f7748a;
    protected ListAdapter f7749b;
    protected int f7750c;
    protected int f7751d;
    private final ac f7752k;
    private GestureDetector f7753l;
    private int f7754m;
    private List<Queue<View>> f7755n;
    private boolean f7756o;
    private Rect f7757p;
    private View f7758q;
    private int f7759r;
    private Drawable f7760s;
    private Integer f7761t;
    private int f7762u;
    private int f7763v;
    private int f7764w;
    private int f7765x;
    private ah f7766y;
    private int f7767z;

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f7748a = new Scroller(getContext());
        this.f7752k = new ac();
        this.f7755n = new ArrayList();
        this.f7756o = false;
        this.f7757p = new Rect();
        this.f7758q = null;
        this.f7759r = f7732f;
        this.f7760s = null;
        this.f7761t = null;
        this.f7762u = Integer.MAX_VALUE;
        this.f7766y = null;
        this.f7767z = f7732f;
        this.f7737A = false;
        this.f7738B = null;
        this.f7739C = ag.SCROLL_STATE_IDLE;
        this.f7743G = false;
        this.f7744H = false;
        this.f7746J = new aa(this);
        this.f7747K = new ab(this);
        this.f7740D = new EdgeEffectCompat(context);
        this.f7741E = new EdgeEffectCompat(context);
        this.f7753l = new GestureDetector(context, this.f7752k);
        m10701a();
        m10715b();
        m10704a(context, attributeSet);
        setWillNotDraw(false);
        if (VERSION.SDK_INT >= 11) {
            ad.m10751a(this.f7748a, f7734h);
        }
    }

    private void m10701a() {
        setOnTouchListener(new C1612z(this));
    }

    private void m10702a(int i, int i2) {
        while ((i + i2) + this.f7759r < getWidth() && this.f7764w + 1 < this.f7749b.getCount()) {
            this.f7764w++;
            if (this.f7763v < 0) {
                this.f7763v = this.f7764w;
            }
            View view = this.f7749b.getView(this.f7764w, m10722c(this.f7764w), this);
            m10708a(view, (int) f7731e);
            i += (this.f7764w == 0 ? f7732f : this.f7759r) + view.getMeasuredWidth();
            m10737h();
        }
    }

    private void m10703a(int i, View view) {
        int itemViewType = this.f7749b.getItemViewType(i);
        if (m10726d(itemViewType)) {
            ((Queue) this.f7755n.get(itemViewType)).offer(view);
        }
    }

    private void m10704a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1205R.styleable.HorizontalListView);
            Drawable drawable = obtainStyledAttributes.getDrawable(1);
            if (drawable != null) {
                setDivider(drawable);
            }
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(3, f7732f);
            if (dimensionPixelSize != 0) {
                setDividerWidth(dimensionPixelSize);
            }
            obtainStyledAttributes.recycle();
        }
    }

    private void m10705a(Canvas canvas) {
        int save;
        int height;
        if (this.f7740D != null && !this.f7740D.isFinished() && m10738i()) {
            save = canvas.save();
            height = getHeight();
            canvas.rotate(-90.0f, 0.0f, 0.0f);
            canvas.translate((float) ((-height) + getPaddingBottom()), 0.0f);
            this.f7740D.setSize(getRenderHeight(), getRenderWidth());
            if (this.f7740D.draw(canvas)) {
                invalidate();
            }
            canvas.restoreToCount(save);
        } else if (this.f7741E != null && !this.f7741E.isFinished() && m10738i()) {
            save = canvas.save();
            height = getWidth();
            canvas.rotate(90.0f, 0.0f, 0.0f);
            canvas.translate((float) getPaddingTop(), (float) (-height));
            this.f7741E.setSize(getRenderHeight(), getRenderWidth());
            if (this.f7741E.draw(canvas)) {
                invalidate();
            }
            canvas.restoreToCount(save);
        }
    }

    private void m10706a(Canvas canvas, Rect rect) {
        if (this.f7760s != null) {
            this.f7760s.setBounds(rect);
            this.f7760s.draw(canvas);
        }
    }

    private void m10707a(View view) {
        LayoutParams b = m10714b(view);
        view.measure(b.width > 0 ? MeasureSpec.makeMeasureSpec(b.width, 1073741824) : MeasureSpec.makeMeasureSpec(f7732f, f7732f), ViewGroup.getChildMeasureSpec(this.f7742F, getPaddingTop() + getPaddingBottom(), b.height));
    }

    private void m10708a(View view, int i) {
        addViewInLayout(view, i, m10714b(view), true);
        m10707a(view);
    }

    private void m10712a(Boolean bool) {
        if (this.f7744H != bool.booleanValue()) {
            View view = this;
            while (view.getParent() instanceof View) {
                if ((view.getParent() instanceof ListView) || (view.getParent() instanceof ScrollView)) {
                    view.getParent().requestDisallowInterceptTouchEvent(bool.booleanValue());
                    this.f7744H = bool.booleanValue();
                    return;
                }
                view = (View) view.getParent();
            }
        }
    }

    private LayoutParams m10714b(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams == null ? new LayoutParams(-2, f7731e) : layoutParams;
    }

    private void m10715b() {
        this.f7763v = f7731e;
        this.f7764w = f7731e;
        this.f7754m = f7732f;
        this.f7750c = f7732f;
        this.f7751d = f7732f;
        this.f7762u = Integer.MAX_VALUE;
        setCurrentScrollState(ag.SCROLL_STATE_IDLE);
    }

    private void m10716b(int i) {
        this.f7755n.clear();
        for (int i2 = f7732f; i2 < i; i2++) {
            this.f7755n.add(new LinkedList());
        }
    }

    private void m10717b(int i, int i2) {
        while ((i + i2) - this.f7759r > 0 && this.f7763v >= 1) {
            this.f7763v += f7731e;
            View view = this.f7749b.getView(this.f7763v, m10722c(this.f7763v), this);
            m10708a(view, (int) f7732f);
            i -= this.f7763v == 0 ? view.getMeasuredWidth() : this.f7759r + view.getMeasuredWidth();
            this.f7754m -= i + i2 == 0 ? view.getMeasuredWidth() : this.f7759r + view.getMeasuredWidth();
        }
    }

    private void m10718b(Canvas canvas) {
        int childCount = getChildCount();
        Rect rect = this.f7757p;
        this.f7757p.top = getPaddingTop();
        this.f7757p.bottom = this.f7757p.top + getRenderHeight();
        for (int i = f7732f; i < childCount; i++) {
            if (i != childCount + f7731e || !m10739i(this.f7764w)) {
                View childAt = getChildAt(i);
                rect.left = childAt.getRight();
                rect.right = childAt.getRight() + this.f7759r;
                if (rect.left < getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                }
                if (rect.right > getWidth() - getPaddingRight()) {
                    rect.right = getWidth() - getPaddingRight();
                }
                m10706a(canvas, rect);
                if (i == 0 && childAt.getLeft() > getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                    rect.right = childAt.getLeft();
                    m10706a(canvas, rect);
                }
            }
        }
    }

    private int m10721c(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = f7732f; i3 < childCount; i3++) {
            getChildAt(i3).getHitRect(this.f7757p);
            if (this.f7757p.contains(i, i2)) {
                return i3;
            }
        }
        return f7731e;
    }

    private View m10722c(int i) {
        int itemViewType = this.f7749b.getItemViewType(i);
        return m10726d(itemViewType) ? (View) ((Queue) this.f7755n.get(itemViewType)).poll() : null;
    }

    private void m10723c() {
        m10715b();
        removeAllViewsInLayout();
        requestLayout();
    }

    private float m10725d() {
        return VERSION.SDK_INT >= 14 ? ae.m10752a(this.f7748a) : f7733g;
    }

    private boolean m10726d(int i) {
        return i < this.f7755n.size();
    }

    private void m10729e(int i) {
        int i2 = f7732f;
        View rightmostChild = getRightmostChild();
        m10702a(rightmostChild != null ? rightmostChild.getRight() : f7732f, i);
        rightmostChild = getLeftmostChild();
        if (rightmostChild != null) {
            i2 = rightmostChild.getLeft();
        }
        m10717b(i2, i);
    }

    private boolean m10730e() {
        if (!m10739i(this.f7764w)) {
            return false;
        }
        View rightmostChild = getRightmostChild();
        if (rightmostChild == null) {
            return false;
        }
        int i = this.f7762u;
        this.f7762u = ((rightmostChild.getRight() - getPaddingLeft()) + this.f7750c) - getRenderWidth();
        if (this.f7762u < 0) {
            this.f7762u = f7732f;
        }
        return this.f7762u != i;
    }

    private void m10732f() {
        if (this.f7758q != null) {
            this.f7758q.setPressed(false);
            refreshDrawableState();
            this.f7758q = null;
        }
    }

    private void m10733f(int i) {
        View leftmostChild = getLeftmostChild();
        while (leftmostChild != null && leftmostChild.getRight() + i <= 0) {
            this.f7754m = (m10739i(this.f7763v) ? leftmostChild.getMeasuredWidth() : this.f7759r + leftmostChild.getMeasuredWidth()) + this.f7754m;
            m10703a(this.f7763v, leftmostChild);
            removeViewInLayout(leftmostChild);
            this.f7763v++;
            leftmostChild = getLeftmostChild();
        }
        View rightmostChild = getRightmostChild();
        while (rightmostChild != null && rightmostChild.getLeft() + i >= getWidth()) {
            m10703a(this.f7764w, rightmostChild);
            removeViewInLayout(rightmostChild);
            this.f7764w += f7731e;
            rightmostChild = getRightmostChild();
        }
    }

    private void m10734g() {
        if (this.f7740D != null) {
            this.f7740D.onRelease();
        }
        if (this.f7741E != null) {
            this.f7741E.onRelease();
        }
    }

    private void m10735g(int i) {
        int childCount = getChildCount();
        if (childCount > 0) {
            this.f7754m += i;
            int i2 = this.f7754m;
            for (int i3 = f7732f; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                int paddingLeft = getPaddingLeft() + i2;
                int paddingTop = getPaddingTop();
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                i2 += childAt.getMeasuredWidth() + this.f7759r;
            }
        }
    }

    private View getLeftmostChild() {
        return getChildAt(f7732f);
    }

    private int getRenderHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int getRenderWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private View getRightmostChild() {
        return getChildAt(getChildCount() + f7731e);
    }

    private View m10736h(int i) {
        return (i < this.f7763v || i > this.f7764w) ? null : getChildAt(i - this.f7763v);
    }

    private void m10737h() {
        if (this.f7766y != null && this.f7749b != null && this.f7749b.getCount() - (this.f7764w + 1) < this.f7767z && !this.f7737A) {
            this.f7737A = true;
            this.f7766y.m10754a();
        }
    }

    private boolean m10738i() {
        return (this.f7749b == null || this.f7749b.isEmpty() || this.f7762u <= 0) ? false : true;
    }

    private boolean m10739i(int i) {
        return i == this.f7749b.getCount() + f7731e;
    }

    private void m10740j(int i) {
        if (this.f7740D != null && this.f7741E != null) {
            int i2 = this.f7750c + i;
            if (this.f7748a != null && !this.f7748a.isFinished()) {
                return;
            }
            if (i2 < 0) {
                this.f7740D.onPull(((float) Math.abs(i)) / ((float) getRenderWidth()));
                if (!this.f7741E.isFinished()) {
                    this.f7741E.onRelease();
                }
            } else if (i2 > this.f7762u) {
                this.f7741E.onPull(((float) Math.abs(i)) / ((float) getRenderWidth()));
                if (!this.f7740D.isFinished()) {
                    this.f7740D.onRelease();
                }
            }
        }
    }

    private void setCurrentScrollState(ag agVar) {
        if (!(this.f7739C == agVar || this.f7738B == null)) {
            this.f7738B.m10753a(agVar);
        }
        this.f7739C = agVar;
    }

    public void m10741a(int i) {
        this.f7748a.startScroll(this.f7751d, f7732f, i - this.f7751d, f7732f);
        setCurrentScrollState(ag.SCROLL_STATE_FLING);
        requestLayout();
    }

    public void m10742a(ah ahVar, int i) {
        this.f7766y = ahVar;
        this.f7767z = i;
    }

    protected boolean m10743a(MotionEvent motionEvent) {
        this.f7743G = !this.f7748a.isFinished();
        this.f7748a.forceFinished(true);
        setCurrentScrollState(ag.SCROLL_STATE_IDLE);
        m10732f();
        if (!this.f7743G) {
            int c = m10721c((int) motionEvent.getX(), (int) motionEvent.getY());
            if (c >= 0) {
                this.f7758q = getChildAt(c);
                if (this.f7758q != null) {
                    this.f7758q.setPressed(true);
                    refreshDrawableState();
                }
            }
        }
        return true;
    }

    protected boolean m10744a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.f7748a.fling(this.f7751d, f7732f, (int) (-f), f7732f, f7732f, this.f7762u, f7732f, f7732f);
        setCurrentScrollState(ag.SCROLL_STATE_FLING);
        requestLayout();
        return true;
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        m10705a(canvas);
    }

    protected void dispatchSetPressed(boolean z) {
    }

    public ListAdapter getAdapter() {
        return this.f7749b;
    }

    public int getFirstVisiblePosition() {
        return this.f7763v;
    }

    public int getLastVisiblePosition() {
        return this.f7764w;
    }

    protected float getLeftFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        return this.f7750c == 0 ? 0.0f : this.f7750c < horizontalFadingEdgeLength ? ((float) this.f7750c) / ((float) horizontalFadingEdgeLength) : C2020f.f10933c;
    }

    protected float getRightFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        return this.f7750c == this.f7762u ? 0.0f : this.f7762u - this.f7750c < horizontalFadingEdgeLength ? ((float) (this.f7762u - this.f7750c)) / ((float) horizontalFadingEdgeLength) : C2020f.f10933c;
    }

    public View getSelectedView() {
        return m10736h(this.f7765x);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m10718b(canvas);
    }

    @SuppressLint({"WrongCall"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f7749b != null) {
            int i5;
            invalidate();
            if (this.f7756o) {
                i5 = this.f7750c;
                m10715b();
                removeAllViewsInLayout();
                this.f7751d = i5;
                this.f7756o = false;
            }
            if (this.f7761t != null) {
                this.f7751d = this.f7761t.intValue();
                this.f7761t = null;
            }
            if (this.f7748a.computeScrollOffset()) {
                this.f7751d = this.f7748a.getCurrX();
            }
            if (this.f7751d < 0) {
                this.f7751d = f7732f;
                if (this.f7740D.isFinished()) {
                    this.f7740D.onAbsorb((int) m10725d());
                }
                this.f7748a.forceFinished(true);
                setCurrentScrollState(ag.SCROLL_STATE_IDLE);
            } else if (this.f7751d > this.f7762u) {
                this.f7751d = this.f7762u;
                if (this.f7741E.isFinished()) {
                    this.f7741E.onAbsorb((int) m10725d());
                }
                this.f7748a.forceFinished(true);
                setCurrentScrollState(ag.SCROLL_STATE_IDLE);
            }
            i5 = this.f7750c - this.f7751d;
            m10733f(i5);
            m10729e(i5);
            m10735g(i5);
            this.f7750c = this.f7751d;
            if (m10730e()) {
                onLayout(z, i, i2, i3, i4);
            } else if (!this.f7748a.isFinished()) {
                ViewCompat.postOnAnimation(this, this.f7747K);
            } else if (this.f7739C == ag.SCROLL_STATE_FLING) {
                setCurrentScrollState(ag.SCROLL_STATE_IDLE);
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f7742F = i2;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f7761t = Integer.valueOf(bundle.getInt(f7735i));
            super.onRestoreInstanceState(bundle.getParcelable(f7736j));
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable(f7736j, super.onSaveInstanceState());
        bundle.putInt(f7735i, this.f7750c);
        return bundle;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.f7748a == null || this.f7748a.isFinished()) {
                setCurrentScrollState(ag.SCROLL_STATE_IDLE);
            }
            m10712a(Boolean.valueOf(false));
            m10734g();
        } else if (motionEvent.getAction() == 3) {
            m10732f();
            m10734g();
            m10712a(Boolean.valueOf(false));
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.f7749b != null) {
            this.f7749b.unregisterDataSetObserver(this.f7746J);
        }
        if (listAdapter != null) {
            this.f7737A = false;
            this.f7749b = listAdapter;
            this.f7749b.registerDataSetObserver(this.f7746J);
        }
        m10716b(this.f7749b.getViewTypeCount());
        m10723c();
    }

    public void setDivider(Drawable drawable) {
        this.f7760s = drawable;
        if (drawable != null) {
            setDividerWidth(drawable.getIntrinsicWidth());
        } else {
            setDividerWidth(f7732f);
        }
    }

    public void setDividerWidth(int i) {
        this.f7759r = i;
        requestLayout();
        invalidate();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f7745I = onClickListener;
    }

    public void setOnScrollStateChangedListener(af afVar) {
        this.f7738B = afVar;
    }

    public void setSelection(int i) {
        this.f7765x = i;
    }
}
