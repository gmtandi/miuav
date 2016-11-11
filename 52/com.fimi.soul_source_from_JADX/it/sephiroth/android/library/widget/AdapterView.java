package it.sephiroth.android.library.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.CapturedViewProperty;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityRecord;
import android.widget.Adapter;
import it.sephiroth.android.library.widget.AdapterView$it.sephiroth.android.library.widget.aa;

public abstract class AdapterView<T extends Adapter> extends ViewGroup {
    static final int aC = 0;
    static final int aD = 1;
    static final int aE = 100;
    public static final int aR = -1;
    public static final long aS = Long.MIN_VALUE;
    public static final String ar = "AdapterView";
    public static final boolean as = false;
    public static final int at = -1;
    public static final int au = -2;
    private int f14338a;
    protected boolean aA;
    int aB;
    protected boolean aF;
    C2845z aG;
    C2843x aH;
    C2844y aI;
    public boolean aJ;
    @ExportedProperty(category = "list")
    protected int aK;
    protected long aL;
    @ExportedProperty(category = "list")
    protected int aM;
    protected long aN;
    @ExportedProperty(category = "list")
    protected int aO;
    protected int aP;
    AccessibilityManager aQ;
    protected int aT;
    protected long aU;
    protected boolean aV;
    @ExportedProperty(category = "scrolling")
    protected int av;
    protected int aw;
    protected int ax;
    protected long ay;
    protected long az;
    private View f14339b;
    private boolean f14340c;
    private boolean f14341d;
    private aa f14342e;

    public AdapterView(Context context) {
        super(context);
        this.av = aC;
        this.ay = aS;
        this.aA = as;
        this.aF = as;
        this.aK = at;
        this.aL = aS;
        this.aM = at;
        this.aN = aS;
        this.aT = at;
        this.aU = aS;
        this.aV = as;
    }

    public AdapterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.av = aC;
        this.ay = aS;
        this.aA = as;
        this.aF = as;
        this.aK = at;
        this.aL = aS;
        this.aM = at;
        this.aN = aS;
        this.aT = at;
        this.aU = aS;
        this.aV = as;
    }

    @TargetApi(16)
    public AdapterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.av = aC;
        this.ay = aS;
        this.aA = as;
        this.aF = as;
        this.aK = at;
        this.aL = aS;
        this.aM = at;
        this.aN = aS;
        this.aT = at;
        this.aU = aS;
        this.aV = as;
        if (VERSION.SDK_INT >= 16 && getImportantForAccessibility() == 0) {
            setImportantForAccessibility(aD);
        }
        if (!isInEditMode()) {
            this.aQ = (AccessibilityManager) getContext().getSystemService("accessibility");
        }
    }

    private void m16060a() {
        if (this.aG != null) {
            int selectedItemPosition = getSelectedItemPosition();
            if (selectedItemPosition >= 0) {
                View selectedView = getSelectedView();
                this.aG.m16414a(this, selectedView, selectedItemPosition, getAdapter().getItemId(selectedItemPosition));
                return;
            }
            this.aG.m16413a(this);
        }
    }

    @SuppressLint({"WrongCall"})
    private void m16062a(boolean z) {
        if (m16074v()) {
            z = as;
        }
        if (z) {
            if (this.f14339b != null) {
                this.f14339b.setVisibility(aC);
                setVisibility(8);
            } else {
                setVisibility(aC);
            }
            if (this.aJ) {
                onLayout(as, getLeft(), getTop(), getRight(), getBottom());
                return;
            }
            return;
        }
        if (this.f14339b != null) {
            this.f14339b.setVisibility(8);
        }
        setVisibility(aC);
    }

    private void m16063b() {
        if (this.aQ.isEnabled() && getSelectedItemPosition() >= 0) {
            sendAccessibilityEvent(4);
        }
    }

    private boolean m16066c() {
        Adapter adapter = getAdapter();
        if (adapter == null) {
            return as;
        }
        int count = adapter.getCount();
        return count > 0 ? (getFirstVisiblePosition() > 0 || getLastVisiblePosition() < count + at) ? true : as : as;
    }

    public void m16067A() {
        if (getChildCount() > 0) {
            this.aA = true;
            this.az = (long) this.f14338a;
            View childAt;
            if (this.aM >= 0) {
                childAt = getChildAt(this.aM - this.av);
                this.ay = this.aL;
                this.ax = this.aK;
                if (childAt != null) {
                    this.aw = childAt.getLeft();
                }
                this.aB = aC;
                return;
            }
            childAt = getChildAt(aC);
            Adapter adapter = getAdapter();
            if (this.av < 0 || this.av >= adapter.getCount()) {
                this.ay = -1;
            } else {
                this.ay = adapter.getItemId(this.av);
            }
            this.ax = this.av;
            if (childAt != null) {
                this.aw = childAt.getLeft();
            }
            this.aB = aD;
        }
    }

    public int m16068a(View view) {
        while (true) {
            try {
                View view2 = (View) view.getParent();
                if (view2.equals(this)) {
                    break;
                }
                view = view2;
            } catch (ClassCastException e) {
                return at;
            }
        }
        int childCount = getChildCount();
        for (int i = aC; i < childCount; i += aD) {
            if (getChildAt(i).equals(view)) {
                return i + this.av;
            }
        }
        return at;
    }

    public boolean m16069a(View view, int i, long j) {
        if (this.aH == null) {
            return as;
        }
        playSoundEffect(aC);
        if (view != null) {
            view.sendAccessibilityEvent(aD);
        }
        this.aH.m16411a(this, view, i, j);
        return true;
    }

    public void addView(View view) {
        throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
    }

    public void addView(View view, int i) {
        throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
    }

    public void addView(View view, LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
    }

    protected int m16070b(int i, boolean z) {
        return i;
    }

    protected boolean canAnimate() {
        return (!super.canAnimate() || this.aO <= 0) ? as : true;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        View selectedView = getSelectedView();
        return (selectedView != null && selectedView.getVisibility() == 0 && selectedView.dispatchPopulateAccessibilityEvent(accessibilityEvent)) ? true : as;
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    public abstract T getAdapter();

    @CapturedViewProperty
    public int getCount() {
        return this.aO;
    }

    public View getEmptyView() {
        return this.f14339b;
    }

    public int getFirstVisiblePosition() {
        return this.av;
    }

    public int getLastVisiblePosition() {
        return (this.av + getChildCount()) + at;
    }

    public final C2843x getOnItemClickListener() {
        return this.aH;
    }

    public final C2844y getOnItemLongClickListener() {
        return this.aI;
    }

    public final C2845z getOnItemSelectedListener() {
        return this.aG;
    }

    public Object getSelectedItem() {
        Adapter adapter = getAdapter();
        int selectedItemPosition = getSelectedItemPosition();
        return (adapter == null || adapter.getCount() <= 0 || selectedItemPosition < 0) ? null : adapter.getItem(selectedItemPosition);
    }

    @CapturedViewProperty
    public long getSelectedItemId() {
        return this.aL;
    }

    @CapturedViewProperty
    public int getSelectedItemPosition() {
        return this.aK;
    }

    public abstract View getSelectedView();

    public Object m16071h(int i) {
        Adapter adapter = getAdapter();
        return (adapter == null || i < 0) ? null : adapter.getItem(i);
    }

    public long m16072i(int i) {
        Adapter adapter = getAdapter();
        return (adapter == null || i < 0) ? aS : adapter.getItemId(i);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f14342e);
    }

    @TargetApi(14)
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(AdapterView.class.getName());
        accessibilityEvent.setScrollable(m16066c());
        View selectedView = getSelectedView();
        if (selectedView != null) {
            accessibilityEvent.setEnabled(selectedView.isEnabled());
        }
        accessibilityEvent.setCurrentItemIndex(getSelectedItemPosition());
        accessibilityEvent.setFromIndex(getFirstVisiblePosition());
        accessibilityEvent.setToIndex(getLastVisiblePosition());
        accessibilityEvent.setItemCount(getCount());
    }

    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(AdapterView.class.getName());
        accessibilityNodeInfo.setScrollable(m16066c());
        View selectedView = getSelectedView();
        if (selectedView != null) {
            accessibilityNodeInfo.setEnabled(selectedView.isEnabled());
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f14338a = getWidth();
    }

    @TargetApi(14)
    public boolean onRequestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        if (!super.onRequestSendAccessibilityEvent(view, accessibilityEvent)) {
            return as;
        }
        AccessibilityRecord obtain = AccessibilityEvent.obtain();
        onInitializeAccessibilityEvent(obtain);
        view.dispatchPopulateAccessibilityEvent(obtain);
        accessibilityEvent.appendRecord(obtain);
        return true;
    }

    public void removeAllViews() {
        throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
    }

    public void removeView(View view) {
        throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
    }

    public void removeViewAt(int i) {
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
    }

    public abstract void setAdapter(T t);

    @TargetApi(16)
    public void setEmptyView(View view) {
        boolean z = true;
        this.f14339b = view;
        if (VERSION.SDK_INT >= 16 && view != null && view.getImportantForAccessibility() == 0) {
            view.setImportantForAccessibility(aD);
        }
        Adapter adapter = getAdapter();
        if (!(adapter == null || adapter.isEmpty())) {
            z = as;
        }
        m16062a(z);
    }

    public void setFocusable(boolean z) {
        boolean z2 = true;
        Adapter adapter = getAdapter();
        boolean z3 = (adapter == null || adapter.getCount() == 0) ? true : as;
        this.f14340c = z;
        if (!z) {
            this.f14341d = as;
        }
        if (!z || (z3 && !m16074v())) {
            z2 = as;
        }
        super.setFocusable(z2);
    }

    public void setFocusableInTouchMode(boolean z) {
        boolean z2 = true;
        Adapter adapter = getAdapter();
        boolean z3 = (adapter == null || adapter.getCount() == 0) ? true : aC;
        this.f14341d = z;
        if (z) {
            this.f14340c = true;
        }
        if (!z || (z3 && !m16074v())) {
            z2 = as;
        }
        super.setFocusableInTouchMode(z2);
    }

    protected void setNextSelectedPositionInt(int i) {
        this.aK = i;
        this.aL = m16072i(i);
        if (this.aA && this.aB == 0 && i >= 0) {
            this.ax = i;
            this.ay = this.aL;
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
    }

    public void setOnItemClickListener(C2843x c2843x) {
        this.aH = c2843x;
    }

    public void setOnItemLongClickListener(C2844y c2844y) {
        if (!isLongClickable()) {
            setLongClickable(true);
        }
        this.aI = c2844y;
    }

    public void setOnItemSelectedListener(C2845z c2845z) {
        this.aG = c2845z;
    }

    protected void setSelectedPositionInt(int i) {
        this.aM = i;
        this.aN = m16072i(i);
    }

    public abstract void setSelection(int i);

    void m16073u() {
        boolean z;
        int i = this.aO;
        if (i > 0) {
            int z2;
            boolean z3;
            if (this.aA) {
                this.aA = as;
                z2 = m16078z();
                if (z2 >= 0 && m16070b(z2, true) == z2) {
                    setNextSelectedPositionInt(z2);
                    z3 = true;
                    if (!z3) {
                        z2 = getSelectedItemPosition();
                        if (z2 >= i) {
                            z2 = i + at;
                        }
                        if (z2 < 0) {
                            z2 = aC;
                        }
                        i = m16070b(z2, true);
                        z2 = i >= 0 ? m16070b(z2, as) : i;
                        if (z2 >= 0) {
                            setNextSelectedPositionInt(z2);
                            m16077y();
                            z = true;
                        }
                    }
                    z = z3;
                }
            }
            z3 = as;
            if (z3) {
                z2 = getSelectedItemPosition();
                if (z2 >= i) {
                    z2 = i + at;
                }
                if (z2 < 0) {
                    z2 = aC;
                }
                i = m16070b(z2, true);
                if (i >= 0) {
                }
                if (z2 >= 0) {
                    setNextSelectedPositionInt(z2);
                    m16077y();
                    z = true;
                }
            }
            z = z3;
        } else {
            z = as;
        }
        if (!z) {
            this.aM = at;
            this.aN = aS;
            this.aK = at;
            this.aL = aS;
            this.aA = as;
            m16077y();
        }
    }

    boolean m16074v() {
        return as;
    }

    protected void m16075w() {
        boolean z = as;
        Adapter adapter = getAdapter();
        boolean z2 = (adapter == null || adapter.getCount() == 0) ? aD : as;
        boolean z3 = (!z2 || m16074v()) ? aD : as;
        z2 = (z3 && this.f14341d) ? true : as;
        super.setFocusableInTouchMode(z2);
        z2 = (z3 && this.f14340c) ? true : as;
        super.setFocusable(z2);
        if (this.f14339b != null) {
            if (adapter == null || adapter.isEmpty()) {
                z = true;
            }
            m16062a(z);
        }
    }

    void m16076x() {
        if (this.aG == null && !this.aQ.isEnabled()) {
            return;
        }
        if (this.aF || this.aV) {
            if (this.f14342e == null) {
                this.f14342e = new aa();
            }
            post(this.f14342e);
            return;
        }
        m16060a();
        m16063b();
    }

    protected void m16077y() {
        if (this.aM != this.aT || this.aN != this.aU) {
            m16076x();
            this.aT = this.aM;
            this.aU = this.aN;
        }
    }

    int m16078z() {
        int i = this.aO;
        if (i == 0) {
            return at;
        }
        long j = this.ay;
        int i2 = this.ax;
        if (j == aS) {
            return at;
        }
        int min = Math.min(i + at, Math.max(aC, i2));
        long uptimeMillis = SystemClock.uptimeMillis() + 100;
        Object obj = null;
        Adapter adapter = getAdapter();
        if (adapter == null) {
            return at;
        }
        int i3 = min;
        int i4 = min;
        while (SystemClock.uptimeMillis() <= uptimeMillis) {
            if (adapter.getItemId(i4) != j) {
                Object obj2 = min == i + at ? aD : aC;
                Object obj3 = i3 == 0 ? aD : null;
                if (obj2 != null && obj3 != null) {
                    break;
                } else if (obj3 != null || (r0 != null && obj2 == null)) {
                    min += aD;
                    obj = null;
                    i4 = min;
                } else if (obj2 != null || (r0 == null && obj3 == null)) {
                    i3 += at;
                    obj = aD;
                    i4 = i3;
                }
            } else {
                return i4;
            }
        }
        return at;
    }
}
