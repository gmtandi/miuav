package it.sephiroth.android.library.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import it.sephiroth.android.library.C2811R;
import java.util.ArrayList;

public class ExpandableHListView extends HListView {
    public static final int aW = 0;
    public static final int aX = 1;
    public static final int aY = 2;
    public static final long aZ = 4294967295L;
    private static final int[] bA;
    private static final int[] bB;
    private static final int[] bC;
    private static final int[][] bD;
    private static final int[] bE;
    public static final int ba = -1;
    private static final long bh = 4294967295L;
    private static final long bi = 9223372032559808512L;
    private static final long bj = Long.MIN_VALUE;
    private static final long bk = 32;
    private static final long bl = 63;
    private static final long bm = -1;
    private static final long bn = 2147483647L;
    private static final int bw = -2;
    private static final int[] bz;
    private Drawable bF;
    private final Rect bG;
    private final Rect bH;
    private int bI;
    private int bJ;
    private int bK;
    private int bL;
    private ak bM;
    private al bN;
    private aj bO;
    private ai bP;
    private ExpandableHListConnector bo;
    private ExpandableListAdapter bp;
    private int bq;
    private int br;
    private int bs;
    private int bt;
    private int bu;
    private int bv;
    private Drawable bx;
    private Drawable by;

    class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        ArrayList<GroupMetadata> f14403a;

        static {
            CREATOR = new am();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f14403a = new ArrayList();
            parcel.readList(this.f14403a, ExpandableHListConnector.class.getClassLoader());
        }

        SavedState(Parcelable parcelable, ArrayList<GroupMetadata> arrayList) {
            super(parcelable);
            this.f14403a = arrayList;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeList(this.f14403a);
        }
    }

    static {
        bz = new int[aW];
        int[] iArr = new int[aX];
        iArr[aW] = 16842920;
        bA = iArr;
        iArr = new int[aX];
        iArr[aW] = 16842921;
        bB = iArr;
        bC = new int[]{16842920, 16842921};
        bD = new int[][]{bz, bA, bB, bC};
        iArr = new int[aX];
        iArr[aW] = 16842918;
        bE = iArr;
    }

    public ExpandableHListView(Context context) {
        this(context, null);
    }

    public ExpandableHListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C2811R.attr.hlv_expandableListViewStyle);
    }

    public ExpandableHListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.bG = new Rect();
        this.bH = new Rect();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2811R.styleable.ExpandableHListView, i, aW);
        setGroupIndicator(obtainStyledAttributes.getDrawable(3));
        setChildIndicator(obtainStyledAttributes.getDrawable(4));
        this.br = obtainStyledAttributes.getDimensionPixelSize(5, aW);
        this.bq = obtainStyledAttributes.getDimensionPixelSize(6, aW);
        this.bs = obtainStyledAttributes.getInt(aW, aW);
        this.bt = obtainStyledAttributes.getInt(aX, aW);
        this.bv = obtainStyledAttributes.getDimensionPixelSize(7, aW);
        this.bu = obtainStyledAttributes.getDimensionPixelSize(8, aW);
        this.bF = obtainStyledAttributes.getDrawable(aY);
        obtainStyledAttributes.recycle();
    }

    private void m16249D() {
        if (this.bx != null) {
            this.bI = this.bx.getIntrinsicWidth();
            this.bJ = this.bx.getIntrinsicHeight();
            return;
        }
        this.bI = aW;
        this.bJ = aW;
    }

    private void m16250E() {
        if (this.by != null) {
            this.bK = this.by.getIntrinsicWidth();
            this.bL = this.by.getIntrinsicHeight();
            return;
        }
        this.bK = aW;
        this.bL = aW;
    }

    private long m16251a(af afVar) {
        return afVar.f14473f == aX ? this.bp.getChildId(afVar.f14470c, afVar.f14471d) : this.bp.getGroupId(afVar.f14470c);
    }

    private Drawable m16252a(ae aeVar) {
        int i = aX;
        int i2 = aW;
        if (aeVar.f14463a.f14473f == aY) {
            Drawable drawable = this.bx;
            if (drawable == null || !drawable.isStateful()) {
                return drawable;
            }
            int i3 = (aeVar.f14464b == null || aeVar.f14464b.f14395c == aeVar.f14464b.f14394b) ? aX : aW;
            if (!aeVar.m16288b()) {
                i = aW;
            }
            if (i3 != 0) {
                i2 = aY;
            }
            drawable.setState(bD[i | i2]);
            return drawable;
        }
        Drawable drawable2 = this.by;
        if (drawable2 != null && drawable2.isStateful()) {
            drawable2.setState(aeVar.f14463a.f14472e == aeVar.f14464b.f14395c ? bE : bz);
        }
        return drawable2;
    }

    public static int m16253b(long j) {
        return j == bh ? aY : (j & bj) == bj ? aX : aW;
    }

    public static int m16254c(long j) {
        return j == bh ? ba : (int) ((bi & j) >> 32);
    }

    public static int m16255d(long j) {
        return (j != bh && (j & bj) == bj) ? (int) (j & bh) : ba;
    }

    public static long m16256i(int i, int i2) {
        return (bj | ((((long) i) & bn) << 32)) | (((long) i2) & bm);
    }

    public static long m16257n(int i) {
        return (((long) i) & bn) << 32;
    }

    private boolean m16258r(int i) {
        return i < getHeaderViewsCount() || i >= this.aO - getFooterViewsCount();
    }

    private int m16259s(int i) {
        return i - getHeaderViewsCount();
    }

    private int m16260t(int i) {
        return getHeaderViewsCount() + i;
    }

    public int m16261a(long j) {
        af a = af.m16292a(j);
        ae a2 = this.bo.m16175a(a);
        a.m16296b();
        int i = a2.f14463a.f14472e;
        a2.m16287a();
        return m16260t(i);
    }

    void m16262a(Canvas canvas, Rect rect, int i) {
        int i2 = this.av + i;
        if (i2 >= 0) {
            ae a = this.bo.m16174a(m16259s(i2));
            if (a.f14463a.f14473f == aX || (a.m16288b() && a.f14464b.f14395c != a.f14464b.f14394b)) {
                Drawable drawable = this.bF;
                drawable.setBounds(rect);
                drawable.draw(canvas);
                a.m16287a();
                return;
            }
            a.m16287a();
        }
        super.m16227a(canvas, rect, i2);
    }

    public boolean m16263a(View view, int i, long j) {
        return m16258r(i) ? super.m16132a(view, i, j) : m16267d(view, m16259s(i), j);
    }

    ContextMenuInfo m16264b(View view, int i, long j) {
        if (m16258r(i)) {
            return new C2842v(view, i, j);
        }
        ae a = this.bo.m16174a(m16259s(i));
        af afVar = a.f14463a;
        long a2 = m16251a(afVar);
        long a3 = afVar.m16295a();
        a.m16287a();
        return new ah(view, a3, a2);
    }

    public boolean m16265b(int i, int i2, boolean z) {
        af a = af.m16290a(i, i2);
        ae a2 = this.bo.m16175a(a);
        if (a2 == null) {
            if (!z) {
                return false;
            }
            m16268j(i);
            a2 = this.bo.m16175a(a);
            if (a2 == null) {
                throw new IllegalStateException("Could not find child");
            }
        }
        super.setSelection(m16260t(a2.f14463a.f14472e));
        a.m16296b();
        a2.m16287a();
        return true;
    }

    public boolean m16266c(int i, boolean z) {
        af a = af.m16291a(aY, i, ba, ba);
        ae a2 = this.bo.m16175a(a);
        a.m16296b();
        boolean b = this.bo.m16181b(a2);
        if (this.bN != null) {
            this.bN.m16300a(i);
        }
        if (z) {
            int headerViewsCount = a2.f14463a.f14472e + getHeaderViewsCount();
            m16145e(this.bp.getChildrenCount(i) + headerViewsCount, headerViewsCount);
        }
        a2.m16287a();
        return b;
    }

    boolean m16267d(View view, int i, long j) {
        boolean z;
        ae a = this.bo.m16174a(i);
        long a2 = m16251a(a.f14463a);
        if (a.f14463a.f14473f == aY) {
            if (this.bO != null) {
                if (this.bO.m16298a(this, view, a.f14463a.f14470c, a2)) {
                    a.m16287a();
                    return true;
                }
            }
            if (a.m16288b()) {
                this.bo.m16178a(a);
                playSoundEffect(aW);
                if (this.bM != null) {
                    this.bM.m16299a(a.f14463a.f14470c);
                }
            } else {
                this.bo.m16181b(a);
                playSoundEffect(aW);
                if (this.bN != null) {
                    this.bN.m16300a(a.f14463a.f14470c);
                }
                int headerViewsCount = a.f14463a.f14472e + getHeaderViewsCount();
                m16145e(this.bp.getChildrenCount(a.f14463a.f14470c) + headerViewsCount, headerViewsCount);
            }
            z = true;
        } else if (this.bP != null) {
            playSoundEffect(aW);
            return this.bP.m16297a(this, view, a.f14463a.f14470c, a.f14463a.f14471d, a2);
        } else {
            z = false;
        }
        a.m16287a();
        return z;
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.by != null || this.bx != null) {
            int headerViewsCount = getHeaderViewsCount();
            int footerViewsCount = ((this.aO - getFooterViewsCount()) - headerViewsCount) + ba;
            int right = getRight();
            Rect rect = this.bG;
            int childCount = getChildCount();
            int i = this.av - headerViewsCount;
            headerViewsCount = -4;
            int i2 = aW;
            int i3 = i;
            while (i2 < childCount) {
                if (i3 >= 0) {
                    if (i3 <= footerViewsCount) {
                        View childAt = getChildAt(i2);
                        int left = childAt.getLeft();
                        int right2 = childAt.getRight();
                        if (right2 >= 0 && left <= right) {
                            ae a = this.bo.m16174a(i3);
                            if (a.f14463a.f14473f != headerViewsCount) {
                                if (a.f14463a.f14473f == aX) {
                                    rect.top = childAt.getTop() + this.bu;
                                    rect.bottom = childAt.getBottom() + this.bu;
                                } else {
                                    rect.top = childAt.getTop() + this.bq;
                                    rect.bottom = childAt.getBottom() + this.bq;
                                }
                                headerViewsCount = a.f14463a.f14473f;
                            }
                            if (rect.top != rect.bottom) {
                                if (a.f14463a.f14473f == aX) {
                                    rect.left = this.bv + left;
                                    rect.right = this.bv + right2;
                                } else {
                                    rect.left = this.br + left;
                                    rect.right = this.br + right2;
                                }
                                Drawable a2 = m16252a(a);
                                if (a2 != null) {
                                    if (a.f14463a.f14473f == aX) {
                                        Gravity.apply(this.bt, this.bK, this.bL, rect, this.bH);
                                    } else {
                                        Gravity.apply(this.bs, this.bI, this.bJ, rect, this.bH);
                                    }
                                    a2.setBounds(this.bH);
                                    a2.draw(canvas);
                                }
                            }
                            a.m16287a();
                        }
                    } else {
                        return;
                    }
                }
                i2 += aX;
                i3 += aX;
            }
        }
    }

    public ListAdapter getAdapter() {
        return super.getAdapter();
    }

    public ExpandableListAdapter getExpandableListAdapter() {
        return this.bp;
    }

    public long getSelectedId() {
        long selectedPosition = getSelectedPosition();
        if (selectedPosition == bh) {
            return bm;
        }
        int c = m16254c(selectedPosition);
        return m16253b(selectedPosition) == 0 ? this.bp.getGroupId(c) : this.bp.getChildId(c, m16255d(selectedPosition));
    }

    public long getSelectedPosition() {
        return m16270l(getSelectedItemPosition());
    }

    public boolean m16268j(int i) {
        return m16266c(i, false);
    }

    public boolean m16269k(int i) {
        boolean b = this.bo.m16180b(i);
        if (this.bM != null) {
            this.bM.m16299a(i);
        }
        return b;
    }

    public long m16270l(int i) {
        if (m16258r(i)) {
            return bh;
        }
        ae a = this.bo.m16174a(m16259s(i));
        long a2 = a.f14463a.m16295a();
        a.m16287a();
        return a2;
    }

    public boolean m16271m(int i) {
        return this.bo.m16183d(i);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ExpandableHListView.class.getName());
    }

    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ExpandableHListView.class.getName());
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            if (this.bo != null && savedState.f14403a != null) {
                this.bo.m16177a(savedState.f14403a);
                return;
            }
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void onRtlPropertiesChanged(int i) {
        m16249D();
        m16250E();
    }

    public Parcelable onSaveInstanceState() {
        return new SavedState(super.onSaveInstanceState(), this.bo != null ? this.bo.m16179b() : null);
    }

    public void setAdapter(ExpandableListAdapter expandableListAdapter) {
        this.bp = expandableListAdapter;
        if (expandableListAdapter != null) {
            this.bo = new ExpandableHListConnector(expandableListAdapter);
        } else {
            this.bo = null;
        }
        super.setAdapter(this.bo);
    }

    public void setAdapter(ListAdapter listAdapter) {
        throw new RuntimeException("For ExpandableListView, use setAdapter(ExpandableListAdapter) instead of setAdapter(ListAdapter)");
    }

    public void setChildDivider(Drawable drawable) {
        this.bF = drawable;
    }

    public void setChildIndicator(Drawable drawable) {
        this.by = drawable;
        m16250E();
    }

    public void setGroupIndicator(Drawable drawable) {
        this.bx = drawable;
        m16249D();
    }

    public void setOnChildClickListener(ai aiVar) {
        this.bP = aiVar;
    }

    public void setOnGroupClickListener(aj ajVar) {
        this.bO = ajVar;
    }

    public void setOnGroupCollapseListener(ak akVar) {
        this.bM = akVar;
    }

    public void setOnGroupExpandListener(al alVar) {
        this.bN = alVar;
    }

    public void setOnItemClickListener(C2843x c2843x) {
        super.setOnItemClickListener(c2843x);
    }

    public void setSelectedGroup(int i) {
        af a = af.m16289a(i);
        ae a2 = this.bo.m16175a(a);
        a.m16296b();
        super.setSelection(m16260t(a2.f14463a.f14472e));
        a2.m16287a();
    }
}
