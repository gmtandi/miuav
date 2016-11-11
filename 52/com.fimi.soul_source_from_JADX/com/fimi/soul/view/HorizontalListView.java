package com.fimi.soul.view;

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
    private static final int f10346e = -1;
    private static final int f10347f = 0;
    private static final float f10348g = 30.0f;
    private static final float f10349h = 0.009f;
    private static final String f10350i = "BUNDLE_ID_CURRENT_X";
    private static final String f10351j = "BUNDLE_ID_PARENT_STATE";
    private boolean f10352A;
    private am f10353B;
    private an f10354C;
    private EdgeEffectCompat f10355D;
    private EdgeEffectCompat f10356E;
    private int f10357F;
    private boolean f10358G;
    private boolean f10359H;
    private OnClickListener f10360I;
    private DataSetObserver f10361J;
    private Runnable f10362K;
    protected Scroller f10363a;
    protected ListAdapter f10364b;
    protected int f10365c;
    protected int f10366d;
    private final aj f10367k;
    private GestureDetector f10368l;
    private int f10369m;
    private List<Queue<View>> f10370n;
    private boolean f10371o;
    private Rect f10372p;
    private View f10373q;
    private int f10374r;
    private Drawable f10375s;
    private Integer f10376t;
    private int f10377u;
    private int f10378v;
    private int f10379w;
    private int f10380x;
    private ao f10381y;
    private int f10382z;

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10363a = new Scroller(getContext());
        this.f10367k = new aj();
        this.f10370n = new ArrayList();
        this.f10371o = false;
        this.f10372p = new Rect();
        this.f10373q = null;
        this.f10374r = f10347f;
        this.f10375s = null;
        this.f10376t = null;
        this.f10377u = Integer.MAX_VALUE;
        this.f10381y = null;
        this.f10382z = f10347f;
        this.f10352A = false;
        this.f10353B = null;
        this.f10354C = an.SCROLL_STATE_IDLE;
        this.f10358G = false;
        this.f10359H = false;
        this.f10361J = new ah(this);
        this.f10362K = new ai(this);
        this.f10355D = new EdgeEffectCompat(context);
        this.f10356E = new EdgeEffectCompat(context);
        this.f10368l = new GestureDetector(context, this.f10367k);
        m12586a();
        m12600b();
        m12589a(context, attributeSet);
        setWillNotDraw(false);
        if (VERSION.SDK_INT >= 11) {
            ak.m12738a(this.f10363a, f10349h);
        }
    }

    private void m12586a() {
        setOnTouchListener(new ag(this));
    }

    private void m12587a(int i, int i2) {
        while ((i + i2) + this.f10374r < getWidth() && this.f10379w + 1 < this.f10364b.getCount()) {
            this.f10379w++;
            if (this.f10378v < 0) {
                this.f10378v = this.f10379w;
            }
            View view = this.f10364b.getView(this.f10379w, m12607c(this.f10379w), this);
            m12593a(view, (int) f10346e);
            i += (this.f10379w == 0 ? f10347f : this.f10374r) + view.getMeasuredWidth();
            m12622h();
        }
    }

    private void m12588a(int i, View view) {
        int itemViewType = this.f10364b.getItemViewType(i);
        if (m12611d(itemViewType)) {
            ((Queue) this.f10370n.get(itemViewType)).offer(view);
        }
    }

    private void m12589a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1205R.styleable.HorizontalListView);
            Drawable drawable = obtainStyledAttributes.getDrawable(1);
            if (drawable != null) {
                setDivider(drawable);
            }
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(3, f10347f);
            if (dimensionPixelSize != 0) {
                setDividerWidth(dimensionPixelSize);
            }
            obtainStyledAttributes.recycle();
        }
    }

    private void m12590a(Canvas canvas) {
        int save;
        int height;
        if (this.f10355D != null && !this.f10355D.isFinished() && m12623i()) {
            save = canvas.save();
            height = getHeight();
            canvas.rotate(-90.0f, 0.0f, 0.0f);
            canvas.translate((float) ((-height) + getPaddingBottom()), 0.0f);
            this.f10355D.setSize(getRenderHeight(), getRenderWidth());
            if (this.f10355D.draw(canvas)) {
                invalidate();
            }
            canvas.restoreToCount(save);
        } else if (this.f10356E != null && !this.f10356E.isFinished() && m12623i()) {
            save = canvas.save();
            height = getWidth();
            canvas.rotate(90.0f, 0.0f, 0.0f);
            canvas.translate((float) getPaddingTop(), (float) (-height));
            this.f10356E.setSize(getRenderHeight(), getRenderWidth());
            if (this.f10356E.draw(canvas)) {
                invalidate();
            }
            canvas.restoreToCount(save);
        }
    }

    private void m12591a(Canvas canvas, Rect rect) {
        if (this.f10375s != null) {
            this.f10375s.setBounds(rect);
            this.f10375s.draw(canvas);
        }
    }

    private void m12592a(View view) {
        LayoutParams b = m12599b(view);
        view.measure(b.width > 0 ? MeasureSpec.makeMeasureSpec(b.width, 1073741824) : MeasureSpec.makeMeasureSpec(f10347f, f10347f), ViewGroup.getChildMeasureSpec(this.f10357F, getPaddingTop() + getPaddingBottom(), b.height));
    }

    private void m12593a(View view, int i) {
        addViewInLayout(view, i, m12599b(view), true);
        m12592a(view);
    }

    private void m12597a(Boolean bool) {
        if (this.f10359H != bool.booleanValue()) {
            View view = this;
            while (view.getParent() instanceof View) {
                if ((view.getParent() instanceof ListView) || (view.getParent() instanceof ScrollView)) {
                    view.getParent().requestDisallowInterceptTouchEvent(bool.booleanValue());
                    this.f10359H = bool.booleanValue();
                    return;
                }
                view = (View) view.getParent();
            }
        }
    }

    private LayoutParams m12599b(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams == null ? new LayoutParams(-2, f10346e) : layoutParams;
    }

    private void m12600b() {
        this.f10378v = f10346e;
        this.f10379w = f10346e;
        this.f10369m = f10347f;
        this.f10365c = f10347f;
        this.f10366d = f10347f;
        this.f10377u = Integer.MAX_VALUE;
        setCurrentScrollState(an.SCROLL_STATE_IDLE);
    }

    private void m12601b(int i) {
        this.f10370n.clear();
        for (int i2 = f10347f; i2 < i; i2++) {
            this.f10370n.add(new LinkedList());
        }
    }

    private void m12602b(int i, int i2) {
        while ((i + i2) - this.f10374r > 0 && this.f10378v >= 1) {
            this.f10378v += f10346e;
            View view = this.f10364b.getView(this.f10378v, m12607c(this.f10378v), this);
            m12593a(view, (int) f10347f);
            i -= this.f10378v == 0 ? view.getMeasuredWidth() : this.f10374r + view.getMeasuredWidth();
            this.f10369m -= i + i2 == 0 ? view.getMeasuredWidth() : this.f10374r + view.getMeasuredWidth();
        }
    }

    private void m12603b(Canvas canvas) {
        int childCount = getChildCount();
        Rect rect = this.f10372p;
        this.f10372p.top = getPaddingTop();
        this.f10372p.bottom = this.f10372p.top + getRenderHeight();
        for (int i = f10347f; i < childCount; i++) {
            if (i != childCount + f10346e || !m12624i(this.f10379w)) {
                View childAt = getChildAt(i);
                rect.left = childAt.getRight();
                rect.right = childAt.getRight() + this.f10374r;
                if (rect.left < getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                }
                if (rect.right > getWidth() - getPaddingRight()) {
                    rect.right = getWidth() - getPaddingRight();
                }
                m12591a(canvas, rect);
                if (i == 0 && childAt.getLeft() > getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                    rect.right = childAt.getLeft();
                    m12591a(canvas, rect);
                }
            }
        }
    }

    private int m12606c(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = f10347f; i3 < childCount; i3++) {
            getChildAt(i3).getHitRect(this.f10372p);
            if (this.f10372p.contains(i, i2)) {
                return i3;
            }
        }
        return f10346e;
    }

    private View m12607c(int i) {
        int itemViewType = this.f10364b.getItemViewType(i);
        return m12611d(itemViewType) ? (View) ((Queue) this.f10370n.get(itemViewType)).poll() : null;
    }

    private void m12608c() {
        m12600b();
        removeAllViewsInLayout();
        requestLayout();
    }

    private float m12610d() {
        return VERSION.SDK_INT >= 14 ? al.m12739a(this.f10363a) : f10348g;
    }

    private boolean m12611d(int i) {
        return i < this.f10370n.size();
    }

    private void m12614e(int i) {
        int i2 = f10347f;
        View rightmostChild = getRightmostChild();
        m12587a(rightmostChild != null ? rightmostChild.getRight() : f10347f, i);
        rightmostChild = getLeftmostChild();
        if (rightmostChild != null) {
            i2 = rightmostChild.getLeft();
        }
        m12602b(i2, i);
    }

    private boolean m12615e() {
        if (!m12624i(this.f10379w)) {
            return false;
        }
        View rightmostChild = getRightmostChild();
        if (rightmostChild == null) {
            return false;
        }
        int i = this.f10377u;
        this.f10377u = ((rightmostChild.getRight() - getPaddingLeft()) + this.f10365c) - getRenderWidth();
        if (this.f10377u < 0) {
            this.f10377u = f10347f;
        }
        return this.f10377u != i;
    }

    private void m12617f() {
        if (this.f10373q != null) {
            this.f10373q.setPressed(false);
            refreshDrawableState();
            this.f10373q = null;
        }
    }

    private void m12618f(int i) {
        View leftmostChild = getLeftmostChild();
        while (leftmostChild != null && leftmostChild.getRight() + i <= 0) {
            this.f10369m = (m12624i(this.f10378v) ? leftmostChild.getMeasuredWidth() : this.f10374r + leftmostChild.getMeasuredWidth()) + this.f10369m;
            m12588a(this.f10378v, leftmostChild);
            removeViewInLayout(leftmostChild);
            this.f10378v++;
            leftmostChild = getLeftmostChild();
        }
        View rightmostChild = getRightmostChild();
        while (rightmostChild != null && rightmostChild.getLeft() + i >= getWidth()) {
            m12588a(this.f10379w, rightmostChild);
            removeViewInLayout(rightmostChild);
            this.f10379w += f10346e;
            rightmostChild = getRightmostChild();
        }
    }

    private void m12619g() {
        if (this.f10355D != null) {
            this.f10355D.onRelease();
        }
        if (this.f10356E != null) {
            this.f10356E.onRelease();
        }
    }

    private void m12620g(int i) {
        int childCount = getChildCount();
        if (childCount > 0) {
            this.f10369m += i;
            int i2 = this.f10369m;
            for (int i3 = f10347f; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                int paddingLeft = getPaddingLeft() + i2;
                int paddingTop = getPaddingTop();
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                i2 += childAt.getMeasuredWidth() + this.f10374r;
            }
        }
    }

    private View getLeftmostChild() {
        return getChildAt(f10347f);
    }

    private int getRenderHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int getRenderWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private View getRightmostChild() {
        return getChildAt(getChildCount() + f10346e);
    }

    private View m12621h(int i) {
        return (i < this.f10378v || i > this.f10379w) ? null : getChildAt(i - this.f10378v);
    }

    private void m12622h() {
        if (this.f10381y != null && this.f10364b != null && this.f10364b.getCount() - (this.f10379w + 1) < this.f10382z && !this.f10352A) {
            this.f10352A = true;
            this.f10381y.m12741a();
        }
    }

    private boolean m12623i() {
        return (this.f10364b == null || this.f10364b.isEmpty() || this.f10377u <= 0) ? false : true;
    }

    private boolean m12624i(int i) {
        return i == this.f10364b.getCount() + f10346e;
    }

    private void m12625j(int i) {
        if (this.f10355D != null && this.f10356E != null) {
            int i2 = this.f10365c + i;
            if (this.f10363a != null && !this.f10363a.isFinished()) {
                return;
            }
            if (i2 < 0) {
                this.f10355D.onPull(((float) Math.abs(i)) / ((float) getRenderWidth()));
                if (!this.f10356E.isFinished()) {
                    this.f10356E.onRelease();
                }
            } else if (i2 > this.f10377u) {
                this.f10356E.onPull(((float) Math.abs(i)) / ((float) getRenderWidth()));
                if (!this.f10355D.isFinished()) {
                    this.f10355D.onRelease();
                }
            }
        }
    }

    private void setCurrentScrollState(an anVar) {
        if (!(this.f10354C == anVar || this.f10353B == null)) {
            this.f10353B.m12740a(anVar);
        }
        this.f10354C = anVar;
    }

    public void m12626a(int i) {
        this.f10363a.startScroll(this.f10366d, f10347f, i - this.f10366d, f10347f);
        setCurrentScrollState(an.SCROLL_STATE_FLING);
        requestLayout();
    }

    public void m12627a(ao aoVar, int i) {
        this.f10381y = aoVar;
        this.f10382z = i;
    }

    protected boolean m12628a(MotionEvent motionEvent) {
        this.f10358G = !this.f10363a.isFinished();
        this.f10363a.forceFinished(true);
        setCurrentScrollState(an.SCROLL_STATE_IDLE);
        m12617f();
        if (!this.f10358G) {
            int c = m12606c((int) motionEvent.getX(), (int) motionEvent.getY());
            if (c >= 0) {
                this.f10373q = getChildAt(c);
                if (this.f10373q != null) {
                    this.f10373q.setPressed(true);
                    refreshDrawableState();
                }
            }
        }
        return true;
    }

    protected boolean m12629a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.f10363a.fling(this.f10366d, f10347f, (int) (-f), f10347f, f10347f, this.f10377u, f10347f, f10347f);
        setCurrentScrollState(an.SCROLL_STATE_FLING);
        requestLayout();
        return true;
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        m12590a(canvas);
    }

    protected void dispatchSetPressed(boolean z) {
    }

    public ListAdapter getAdapter() {
        return this.f10364b;
    }

    public int getFirstVisiblePosition() {
        return this.f10378v;
    }

    public int getLastVisiblePosition() {
        return this.f10379w;
    }

    protected float getLeftFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        return this.f10365c == 0 ? 0.0f : this.f10365c < horizontalFadingEdgeLength ? ((float) this.f10365c) / ((float) horizontalFadingEdgeLength) : C2020f.f10933c;
    }

    protected float getRightFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        return this.f10365c == this.f10377u ? 0.0f : this.f10377u - this.f10365c < horizontalFadingEdgeLength ? ((float) (this.f10377u - this.f10365c)) / ((float) horizontalFadingEdgeLength) : C2020f.f10933c;
    }

    public View getSelectedView() {
        return m12621h(this.f10380x);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m12603b(canvas);
    }

    @SuppressLint({"WrongCall"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f10364b != null) {
            int i5;
            invalidate();
            if (this.f10371o) {
                i5 = this.f10365c;
                m12600b();
                removeAllViewsInLayout();
                this.f10366d = i5;
                this.f10371o = false;
            }
            if (this.f10376t != null) {
                this.f10366d = this.f10376t.intValue();
                this.f10376t = null;
            }
            if (this.f10363a.computeScrollOffset()) {
                this.f10366d = this.f10363a.getCurrX();
            }
            if (this.f10366d < 0) {
                this.f10366d = f10347f;
                if (this.f10355D.isFinished()) {
                    this.f10355D.onAbsorb((int) m12610d());
                }
                this.f10363a.forceFinished(true);
                setCurrentScrollState(an.SCROLL_STATE_IDLE);
            } else if (this.f10366d > this.f10377u) {
                this.f10366d = this.f10377u;
                if (this.f10356E.isFinished()) {
                    this.f10356E.onAbsorb((int) m12610d());
                }
                this.f10363a.forceFinished(true);
                setCurrentScrollState(an.SCROLL_STATE_IDLE);
            }
            i5 = this.f10365c - this.f10366d;
            m12618f(i5);
            m12614e(i5);
            m12620g(i5);
            this.f10365c = this.f10366d;
            if (m12615e()) {
                onLayout(z, i, i2, i3, i4);
            } else if (!this.f10363a.isFinished()) {
                ViewCompat.postOnAnimation(this, this.f10362K);
            } else if (this.f10354C == an.SCROLL_STATE_FLING) {
                setCurrentScrollState(an.SCROLL_STATE_IDLE);
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f10357F = i2;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.f10376t = Integer.valueOf(bundle.getInt(f10350i));
            super.onRestoreInstanceState(bundle.getParcelable(f10351j));
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable(f10351j, super.onSaveInstanceState());
        bundle.putInt(f10350i, this.f10365c);
        return bundle;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.f10363a == null || this.f10363a.isFinished()) {
                setCurrentScrollState(an.SCROLL_STATE_IDLE);
            }
            m12597a(Boolean.valueOf(false));
            m12619g();
        } else if (motionEvent.getAction() == 3) {
            m12617f();
            m12619g();
            m12597a(Boolean.valueOf(false));
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.f10364b != null) {
            this.f10364b.unregisterDataSetObserver(this.f10361J);
        }
        if (listAdapter != null) {
            this.f10352A = false;
            this.f10364b = listAdapter;
            this.f10364b.registerDataSetObserver(this.f10361J);
        }
        m12601b(this.f10364b.getViewTypeCount());
        m12608c();
    }

    public void setDivider(Drawable drawable) {
        this.f10375s = drawable;
        if (drawable != null) {
            setDividerWidth(drawable.getIntrinsicWidth());
        } else {
            setDividerWidth(f10347f);
        }
    }

    public void setDividerWidth(int i) {
        this.f10374r = i;
        requestLayout();
        invalidate();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f10360I = onClickListener;
    }

    public void setOnScrollStateChangedListener(am amVar) {
        this.f10353B = amVar;
    }

    public void setSelection(int i) {
        this.f10380x = i;
    }
}
