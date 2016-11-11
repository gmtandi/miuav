package it.sephiroth.android.library.widget;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.ViewConfiguration;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Checkable;
import android.widget.ListAdapter;
import com.fimi.kernel.p084e.C1186y;
import com.fimi.soul.drone.p116g.C1543c;
import com.fimi.soul.view.photodraweeview.C2020f;
import com.hoho.android.usbserial.driver.UsbId;
import com.tencent.mm.sdk.platformtools.Util;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import it.p074a.p075a.C2799f;
import it.sephiroth.android.library.C2811R;
import it.sephiroth.android.library.p143a.C2815c;
import it.sephiroth.android.library.p143a.C2818b;
import it.sephiroth.android.library.p143a.p144a.C2812a;
import it.sephiroth.android.library.p143a.p144a.C2813b;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;
import org.codehaus.jackson.org.objectweb.asm.Type;

@TargetApi(11)
public abstract class AbsHListView extends AdapterView<ListAdapter> implements OnGlobalLayoutListener, OnTouchModeChangeListener {
    public static final int f14343a = 0;
    private static final String aW = "AbsListView";
    protected static final int aj = 3;
    static final Interpolator ap;
    public static final int[] aq;
    public static final int f14344b = 1;
    private static final int bd = 20;
    private static final int be = -1;
    private static final int bf = 0;
    private static final int bg = 1;
    private static final int bx = -1;
    public static final int f14345c = 2;
    public static final int f14346d = -1;
    public static final int f14347e = 0;
    public static final int f14348f = 1;
    public static final int f14349g = 2;
    public static final int f14350h = 3;
    public static final int f14351i = 4;
    public static final int f14352j = 5;
    public static final int f14353k = 6;
    public static final int f14354l = 0;
    public static final int f14355m = 1;
    public static final int f14356n = 2;
    public static final int f14357o = 3;
    public static final int f14358p = 4;
    public static final int f14359q = 5;
    public static final int f14360r = 6;
    protected C2823c f14361A;
    protected ListAdapter f14362B;
    boolean f14363C;
    boolean f14364D;
    Drawable f14365E;
    int f14366F;
    protected Rect f14367G;
    protected final C2837p f14368H;
    int f14369I;
    int f14370J;
    int f14371K;
    int f14372L;
    protected Rect f14373M;
    protected int f14374N;
    View f14375O;
    View f14376P;
    protected boolean f14377Q;
    protected boolean f14378R;
    protected int f14379S;
    int f14380T;
    int f14381U;
    int f14382V;
    int f14383W;
    protected int f14384Z;
    private VelocityTracker aX;
    private C2828g aY;
    private C2831j aZ;
    int aa;
    int ab;
    protected C2833l ac;
    protected int ad;
    protected boolean ae;
    boolean af;
    boolean ag;
    protected int ah;
    protected int ai;
    protected Runnable ak;
    protected final boolean[] al;
    int am;
    int an;
    protected boolean ao;
    private int bA;
    private int bB;
    private int bC;
    private boolean bD;
    private int bE;
    private int bF;
    private C2830i bG;
    private int bH;
    private int bI;
    private int bJ;
    private SavedState bK;
    private float bL;
    private boolean ba;
    private Rect bb;
    private ContextMenuInfo bc;
    private int bh;
    private C2826e bi;
    private Runnable bj;
    private C2825d bk;
    private C2832k bl;
    private Runnable bm;
    private int bn;
    private int bo;
    private boolean bp;
    private int bq;
    private int br;
    private Runnable bs;
    private int bt;
    private int bu;
    private float bv;
    private int bw;
    private ab by;
    private ab bz;
    C2815c f14385s;
    protected int f14386t;
    public Object f14387u;
    Object f14388v;
    int f14389w;
    protected SparseArrayCompat<Boolean> f14390x;
    LongSparseArray<Integer> f14391y;
    protected int f14392z;

    public class LayoutParams extends android.view.ViewGroup.LayoutParams {
        public int f14323a;
        public boolean f14324b;
        public boolean f14325c;
        public int f14326d;
        public long f14327e;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f14327e = -1;
        }

        public LayoutParams(int i, int i2, int i3) {
            super(i, i2);
            this.f14327e = -1;
            this.f14323a = i3;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f14327e = -1;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f14327e = -1;
        }
    }

    class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR;
        long f14328a;
        long f14329b;
        int f14330c;
        int f14331d;
        int f14332e;
        String f14333f;
        boolean f14334g;
        int f14335h;
        SparseArrayCompat<Boolean> f14336i;
        LongSparseArray<Integer> f14337j;

        static {
            CREATOR = new C2839r();
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f14328a = parcel.readLong();
            this.f14329b = parcel.readLong();
            this.f14330c = parcel.readInt();
            this.f14331d = parcel.readInt();
            this.f14332e = parcel.readInt();
            this.f14333f = parcel.readString();
            this.f14334g = parcel.readByte() != null;
            this.f14335h = parcel.readInt();
            this.f14336i = m16058b(parcel);
            this.f14337j = m16053a(parcel);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private LongSparseArray<Integer> m16053a(Parcel parcel) {
            int readInt = parcel.readInt();
            if (readInt <= 0) {
                return null;
            }
            LongSparseArray longSparseArray = new LongSparseArray(readInt);
            m16055a(longSparseArray, parcel, readInt);
            return longSparseArray;
        }

        private void m16054a(LongSparseArray<Integer> longSparseArray, Parcel parcel) {
            int i = AbsHListView.f14354l;
            int size = longSparseArray != null ? longSparseArray.size() : AbsHListView.f14354l;
            parcel.writeInt(size);
            while (i < size) {
                parcel.writeLong(longSparseArray.keyAt(i));
                parcel.writeInt(((Integer) longSparseArray.valueAt(i)).intValue());
                i += AbsHListView.f14355m;
            }
        }

        private void m16055a(LongSparseArray<Integer> longSparseArray, Parcel parcel, int i) {
            while (i > 0) {
                longSparseArray.put(parcel.readLong(), Integer.valueOf(parcel.readInt()));
                i += AbsHListView.f14346d;
            }
        }

        private void m16056a(SparseArrayCompat<Boolean> sparseArrayCompat, Parcel parcel) {
            if (sparseArrayCompat == null) {
                parcel.writeInt(AbsHListView.f14346d);
                return;
            }
            int size = sparseArrayCompat.size();
            parcel.writeInt(size);
            for (int i = AbsHListView.f14354l; i < size; i += AbsHListView.f14355m) {
                parcel.writeInt(sparseArrayCompat.keyAt(i));
                parcel.writeByte((byte) (((Boolean) sparseArrayCompat.valueAt(i)).booleanValue() ? AbsHListView.f14355m : AbsHListView.f14354l));
            }
        }

        private void m16057a(SparseArrayCompat<Boolean> sparseArrayCompat, Parcel parcel, int i) {
            while (i > 0) {
                sparseArrayCompat.append(parcel.readInt(), Boolean.valueOf(parcel.readByte() == (byte) 1));
                i += AbsHListView.f14346d;
            }
        }

        private SparseArrayCompat<Boolean> m16058b(Parcel parcel) {
            int readInt = parcel.readInt();
            if (readInt < 0) {
                return null;
            }
            SparseArrayCompat sparseArrayCompat = new SparseArrayCompat(readInt);
            m16057a(sparseArrayCompat, parcel, readInt);
            return sparseArrayCompat;
        }

        public String toString() {
            return "AbsListView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.f14328a + " firstId=" + this.f14329b + " viewLeft=" + this.f14330c + " position=" + this.f14331d + " width=" + this.f14332e + " filter=" + this.f14333f + " checkState=" + this.f14336i + "}";
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeLong(this.f14328a);
            parcel.writeLong(this.f14329b);
            parcel.writeInt(this.f14330c);
            parcel.writeInt(this.f14331d);
            parcel.writeInt(this.f14332e);
            parcel.writeString(this.f14333f);
            parcel.writeByte((byte) (this.f14334g ? AbsHListView.f14355m : AbsHListView.f14354l));
            parcel.writeInt(this.f14335h);
            m16056a(this.f14336i, parcel);
            m16054a(this.f14337j, parcel);
        }
    }

    static {
        ap = new LinearInterpolator();
        int[] iArr = new int[f14355m];
        iArr[f14354l] = f14354l;
        aq = iArr;
    }

    public AbsHListView(Context context) {
        super(context);
        this.f14386t = f14354l;
        this.f14392z = f14354l;
        this.f14364D = false;
        this.f14366F = f14346d;
        this.f14367G = new Rect();
        this.f14368H = new C2837p(this);
        this.f14369I = f14354l;
        this.f14370J = f14354l;
        this.f14371K = f14354l;
        this.f14372L = f14354l;
        this.f14373M = new Rect();
        this.f14374N = f14354l;
        this.f14384Z = f14346d;
        this.ad = f14354l;
        this.ba = true;
        this.ah = f14346d;
        this.bc = null;
        this.bh = f14346d;
        this.bq = f14354l;
        this.bv = C2020f.f10933c;
        this.al = new boolean[f14355m];
        this.bw = f14346d;
        this.bC = f14354l;
        m16079B();
    }

    public AbsHListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C2811R.attr.hlv_absHListViewStyle);
    }

    public AbsHListView(Context context, AttributeSet attributeSet, int i) {
        Drawable drawable;
        boolean z;
        boolean z2;
        boolean z3;
        int i2;
        int color;
        boolean z4 = true;
        int i3 = f14354l;
        super(context, attributeSet, i);
        this.f14386t = f14354l;
        this.f14392z = f14354l;
        this.f14364D = false;
        this.f14366F = f14346d;
        this.f14367G = new Rect();
        this.f14368H = new C2837p(this);
        this.f14369I = f14354l;
        this.f14370J = f14354l;
        this.f14371K = f14354l;
        this.f14372L = f14354l;
        this.f14373M = new Rect();
        this.f14374N = f14354l;
        this.f14384Z = f14346d;
        this.ad = f14354l;
        this.ba = true;
        this.ah = f14346d;
        this.bc = null;
        this.bh = f14346d;
        this.bq = f14354l;
        this.bv = C2020f.f10933c;
        this.al = new boolean[f14355m];
        this.bw = f14346d;
        this.bC = f14354l;
        m16079B();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2811R.styleable.AbsHListView, i, f14354l);
        if (obtainStyledAttributes != null) {
            drawable = obtainStyledAttributes.getDrawable(f14354l);
            z = obtainStyledAttributes.getBoolean(f14355m, false);
            z2 = obtainStyledAttributes.getBoolean(f14360r, false);
            z3 = obtainStyledAttributes.getBoolean(f14356n, true);
            i2 = obtainStyledAttributes.getInt(7, f14354l);
            color = obtainStyledAttributes.getColor(f14357o, f14354l);
            z4 = obtainStyledAttributes.getBoolean(f14359q, true);
            i3 = obtainStyledAttributes.getInt(f14358p, f14354l);
            obtainStyledAttributes.recycle();
        } else {
            i2 = f14354l;
            z3 = true;
            z2 = false;
            z = false;
            drawable = null;
            color = f14354l;
        }
        if (drawable != null) {
            setSelector(drawable);
        }
        this.f14364D = z;
        setStackFromRight(z2);
        setScrollingCacheEnabled(z3);
        setTranscriptMode(i2);
        setCacheColorHint(color);
        setSmoothScrollbarEnabled(z4);
        setChoiceMode(i3);
    }

    private void m16079B() {
        setClickable(true);
        setFocusableInTouchMode(true);
        setWillNotDraw(false);
        setAlwaysDrawnWithCacheEnabled(false);
        setScrollingCacheEnabled(true);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.br = viewConfiguration.getScaledTouchSlop();
        this.bt = viewConfiguration.getScaledMinimumFlingVelocity();
        this.bu = viewConfiguration.getScaledMaximumFlingVelocity();
        this.am = viewConfiguration.getScaledOverscrollDistance();
        this.an = viewConfiguration.getScaledOverflingDistance();
        this.f14385s = C2818b.m16051a(this);
    }

    private void m16080C() {
        int i = this.av;
        int childCount = getChildCount();
        boolean z = VERSION.SDK_INT >= 11 ? f14355m : false;
        for (int i2 = f14354l; i2 < childCount; i2 += f14355m) {
            View childAt = getChildAt(i2);
            int i3 = i + i2;
            if (childAt instanceof Checkable) {
                ((Checkable) childAt).setChecked(((Boolean) this.f14390x.get(i3, Boolean.valueOf(false))).booleanValue());
            } else if (z) {
                childAt.setActivated(((Boolean) this.f14390x.get(i3, Boolean.valueOf(false))).booleanValue());
            }
        }
    }

    private boolean m16081D() {
        int childCount = getChildCount();
        return childCount == 0 ? true : childCount != this.aO ? false : getChildAt(f14354l).getLeft() >= this.f14373M.left && getChildAt(childCount + f14346d).getRight() <= getWidth() - this.f14373M.right;
    }

    private void m16082E() {
        setSelector(getResources().getDrawable(17301602));
    }

    private void m16083F() {
        if (this.aX == null) {
            this.aX = VelocityTracker.obtain();
        } else {
            this.aX.clear();
        }
    }

    private void m16084G() {
        if (this.aX == null) {
            this.aX = VelocityTracker.obtain();
        }
    }

    private void m16085H() {
        if (this.aX != null) {
            this.aX.recycle();
            this.aX = null;
        }
    }

    private void m16086I() {
        if (this.af && !this.f14377Q && !this.f14385s.m16045a()) {
            setChildrenDrawnWithCacheEnabled(true);
            setChildrenDrawingCacheEnabled(true);
            this.f14378R = true;
            this.f14377Q = true;
        }
    }

    private void m16087J() {
        if (!this.f14385s.m16045a()) {
            if (this.bs == null) {
                this.bs = new C2821b(this);
            }
            post(this.bs);
        }
    }

    private void m16088K() {
        if (this.by != null) {
            this.by.m16279b();
            this.bz.m16279b();
        }
    }

    public static int m16089a(Rect rect, Rect rect2, int i) {
        int width;
        int height;
        int width2;
        int height2;
        switch (i) {
            case f14355m /*1*/:
            case f14356n /*2*/:
                width = rect.right + (rect.width() / f14356n);
                height = rect.top + (rect.height() / f14356n);
                width2 = (rect2.width() / f14356n) + rect2.left;
                height2 = rect2.top + (rect2.height() / f14356n);
                break;
            case Opcodes.SIPUSH /*17*/:
                width = rect.left;
                height = rect.top + (rect.height() / f14356n);
                width2 = rect2.right;
                height2 = rect2.top + (rect2.height() / f14356n);
                break;
            case C1543c.aW /*33*/:
                width = rect.left + (rect.width() / f14356n);
                height = rect.top;
                width2 = (rect2.width() / f14356n) + rect2.left;
                height2 = rect2.bottom;
                break;
            case UsbId.ARDUINO_MEGA_2560_R3 /*66*/:
                width = rect.right;
                height = rect.top + (rect.height() / f14356n);
                width2 = rect2.left;
                height2 = rect2.top + (rect2.height() / f14356n);
                break;
            case Opcodes.IXOR /*130*/:
                width = rect.left + (rect.width() / f14356n);
                height = rect.bottom;
                width2 = (rect2.width() / f14356n) + rect2.left;
                height2 = rect2.top;
                break;
            default:
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT, FOCUS_FORWARD, FOCUS_BACKWARD}.");
        }
        width2 -= width;
        height2 -= height;
        return (height2 * height2) + (width2 * width2);
    }

    static View m16091a(ArrayList<View> arrayList, int i) {
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        for (int i2 = f14354l; i2 < size; i2 += f14355m) {
            View view = (View) arrayList.get(i2);
            if (((LayoutParams) view.getLayoutParams()).f14326d == i) {
                arrayList.remove(i2);
                return view;
            }
        }
        return (View) arrayList.remove(size + f14346d);
    }

    private void m16093a(int i, int i2, int i3, int i4) {
        this.f14367G.set(i - this.f14369I, i2 - this.f14370J, this.f14371K + i3, this.f14372L + i4);
    }

    private void m16094a(Canvas canvas) {
        if (!this.f14367G.isEmpty()) {
            Drawable drawable = this.f14365E;
            drawable.setBounds(this.f14367G);
            drawable.draw(canvas);
        }
    }

    private void m16099b(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (motionEvent.getPointerId(action) == this.bw) {
            action = action == 0 ? f14355m : f14354l;
            this.f14382V = (int) motionEvent.getX(action);
            this.f14383W = (int) motionEvent.getY(action);
            this.ab = f14354l;
            this.bw = motionEvent.getPointerId(action);
        }
    }

    private boolean m16115j(int i) {
        int i2 = i - this.f14382V;
        int abs = Math.abs(i2);
        boolean z = getScrollX() != 0;
        if (!z && abs <= this.br) {
            return false;
        }
        m16086I();
        if (z) {
            this.f14384Z = f14359q;
            this.ab = f14354l;
        } else {
            this.f14384Z = f14357o;
            this.ab = i2 > 0 ? this.br : -this.br;
        }
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.bi);
        }
        setPressed(false);
        View childAt = getChildAt(this.f14379S - this.av);
        if (childAt != null) {
            childAt.setPressed(false);
        }
        m16135b((int) f14355m);
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        m16116k(i);
        return true;
    }

    private void m16116k(int i) {
        int i2 = f14354l;
        int i3 = i - this.f14382V;
        int i4 = i3 - this.ab;
        int i5 = this.aa != C1186y.f5353a ? i - this.aa : i4;
        int childCount;
        int left;
        if (this.f14384Z == f14357o) {
            if (i != this.aa) {
                if (Math.abs(i3) > this.br) {
                    ViewParent parent = getParent();
                    if (parent != null) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    }
                }
                childCount = this.f14379S >= 0 ? this.f14379S - this.av : getChildCount() / f14356n;
                View childAt = getChildAt(childCount);
                left = childAt != null ? childAt.getLeft() : f14354l;
                boolean g = i5 != 0 ? m16152g(i4, i5) : false;
                View childAt2 = getChildAt(childCount);
                if (childAt2 != null) {
                    childCount = childAt2.getLeft();
                    if (g) {
                        i4 = (-i5) - (childCount - left);
                        overScrollBy(i4, f14354l, getScrollX(), f14354l, f14354l, f14354l, this.am, f14354l, true);
                        if (Math.abs(this.am) == Math.abs(getScrollX()) && this.aX != null) {
                            this.aX.clear();
                        }
                        i5 = getOverScrollMode();
                        if (i5 == 0 || (i5 == f14355m && !m16081D())) {
                            this.bC = f14354l;
                            this.f14384Z = f14359q;
                            if (i3 > 0) {
                                this.by.m16274a(((float) i4) / ((float) getWidth()));
                                if (!this.bz.m16277a()) {
                                    this.bz.m16281c();
                                }
                                invalidate(this.by.m16273a(false));
                            } else if (i3 < 0) {
                                this.bz.m16274a(((float) i4) / ((float) getWidth()));
                                if (!this.by.m16277a()) {
                                    this.by.m16281c();
                                }
                                invalidate(this.bz.m16273a(true));
                            }
                        }
                    }
                    this.f14382V = i;
                }
                this.aa = i;
            }
        } else if (this.f14384Z == f14359q && i != this.aa) {
            int i6;
            childCount = getScrollX();
            left = childCount - i5;
            int i7 = i > this.aa ? f14355m : f14346d;
            if (this.bC == 0) {
                this.bC = i7;
            }
            i4 = -i5;
            if ((left >= 0 || childCount < 0) && (left <= 0 || childCount > 0)) {
                i6 = f14354l;
            } else {
                i4 = -childCount;
                i6 = i5 + i4;
            }
            if (i4 != 0) {
                overScrollBy(i4, f14354l, getScrollX(), f14354l, f14354l, f14354l, this.am, f14354l, true);
                i5 = getOverScrollMode();
                if (i5 == 0 || (i5 == f14355m && !m16081D())) {
                    if (i3 > 0) {
                        this.by.m16274a(((float) i4) / ((float) getWidth()));
                        if (!this.bz.m16277a()) {
                            this.bz.m16281c();
                        }
                        invalidate(this.by.m16273a(false));
                    } else if (i3 < 0) {
                        this.bz.m16274a(((float) i4) / ((float) getWidth()));
                        if (!this.by.m16277a()) {
                            this.by.m16281c();
                        }
                        invalidate(this.bz.m16273a(true));
                    }
                }
            }
            if (i6 != 0) {
                if (getScrollX() != 0) {
                    this.f14385s.m16043a((int) f14354l);
                    m16160n();
                }
                m16152g(i6, i6);
                this.f14384Z = f14357o;
                i5 = m16150g(i);
                this.ab = f14354l;
                View childAt3 = getChildAt(i5 - this.av);
                if (childAt3 != null) {
                    i2 = childAt3.getLeft();
                }
                this.f14380T = i2;
                this.f14382V = i;
                this.f14379S = i5;
            }
            this.aa = i;
            this.bC = i7;
        }
    }

    public int m16118a(int i, int i2) {
        Rect rect = this.bb;
        if (rect == null) {
            this.bb = new Rect();
            rect = this.bb;
        }
        for (int childCount = getChildCount() + f14346d; childCount >= 0; childCount += f14346d) {
            View childAt = getChildAt(childCount);
            if (childAt.getVisibility() == 0) {
                childAt.getHitRect(rect);
                if (rect.contains(i, i2)) {
                    return this.av + childCount;
                }
            }
        }
        return f14346d;
    }

    @SuppressLint({"NewApi"})
    protected View m16119a(int i, boolean[] zArr) {
        zArr[f14354l] = false;
        View d = this.f14368H.m16402d(i);
        if (d == null) {
            d = this.f14368H.m16404e(i);
            View view;
            if (d != null) {
                view = this.f14362B.getView(i, d, this);
                if (VERSION.SDK_INT >= 16 && view.getImportantForAccessibility() == 0) {
                    view.setImportantForAccessibility(f14355m);
                }
                if (view != d) {
                    this.f14368H.m16396a(d, i);
                    if (this.bo != 0) {
                        view.setDrawingCacheBackgroundColor(this.bo);
                        d = view;
                    }
                    d = view;
                } else {
                    zArr[f14354l] = true;
                    view.onFinishTemporaryDetach();
                    d = view;
                }
            } else {
                view = this.f14362B.getView(i, null, this);
                if (VERSION.SDK_INT >= 16 && view.getImportantForAccessibility() == 0) {
                    view.setImportantForAccessibility(f14355m);
                }
                if (this.bo != 0) {
                    view.setDrawingCacheBackgroundColor(this.bo);
                }
                d = view;
            }
            if (this.f14363C) {
                android.view.ViewGroup.LayoutParams layoutParams = d.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = (LayoutParams) generateDefaultLayoutParams();
                } else {
                    LayoutParams layoutParams2 = !checkLayoutParams(layoutParams) ? (LayoutParams) generateLayoutParams(layoutParams) : (LayoutParams) layoutParams;
                }
                layoutParams.f14327e = this.f14362B.getItemId(i);
                d.setLayoutParams(layoutParams);
            }
            if (this.aQ.isEnabled() && this.bG == null) {
                this.bG = new C2830i(this);
            }
        }
        return d;
    }

    public LayoutParams m16120a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void m16121a() {
        if (this.f14390x != null) {
            this.f14390x.clear();
        }
        if (this.f14391y != null) {
            this.f14391y.clear();
        }
        this.f14389w = f14354l;
    }

    public void m16122a(int i, int i2, int i3) {
        if (this.ac == null) {
            this.ac = new C2833l(this);
        }
        this.ac.m16387a(i, i2, i3);
    }

    public void m16123a(int i, int i2, boolean z) {
        if (this.aY == null) {
            this.aY = new C2828g(this);
        }
        int i3 = this.av;
        int childCount = getChildCount();
        int i4 = i3 + childCount;
        int paddingLeft = getPaddingLeft();
        int width = getWidth() - getPaddingRight();
        if (i == 0 || this.aO == 0 || childCount == 0 || ((i3 == 0 && getChildAt(f14354l).getLeft() == paddingLeft && i < 0) || (i4 == this.aO && getChildAt(childCount + f14346d).getRight() == width && i > 0))) {
            this.aY.m16378b();
            if (this.ac != null) {
                this.ac.m16384a();
                return;
            }
            return;
        }
        m16135b((int) f14356n);
        this.aY.m16377a(i, i2, z);
    }

    protected void m16124a(int i, View view) {
        if (i != f14346d) {
            this.f14366F = i;
        }
        Rect rect = this.f14367G;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if (view instanceof C2840s) {
            ((C2840s) view).m16410a(rect);
        }
        m16093a(rect.left, rect.top, rect.right, rect.bottom);
        boolean z = this.bp;
        if (view.isEnabled() != z) {
            this.bp = !z;
            if (getSelectedItemPosition() != f14346d) {
                refreshDrawableState();
            }
        }
    }

    public void m16125a(int i, boolean z) {
        if (this.f14386t != 0) {
            if (VERSION.SDK_INT >= 11 && z && this.f14386t == f14357o && this.f14387u == null) {
                if (this.f14388v == null || !((C2813b) this.f14388v).m16007a()) {
                    throw new IllegalStateException("AbsListView: attempted to start selection mode for CHOICE_MODE_MULTIPLE_MODAL but no choice mode callback was supplied. Call setMultiChoiceModeListener to set a callback.");
                }
                this.f14387u = startActionMode((C2813b) this.f14388v);
            }
            boolean booleanValue;
            if (this.f14386t == f14356n || (VERSION.SDK_INT >= 11 && this.f14386t == f14357o)) {
                booleanValue = ((Boolean) this.f14390x.get(i, Boolean.valueOf(false))).booleanValue();
                this.f14390x.put(i, Boolean.valueOf(z));
                if (this.f14391y != null && this.f14362B.hasStableIds()) {
                    if (z) {
                        this.f14391y.put(this.f14362B.getItemId(i), Integer.valueOf(i));
                    } else {
                        this.f14391y.delete(this.f14362B.getItemId(i));
                    }
                }
                if (booleanValue != z) {
                    if (z) {
                        this.f14389w += f14355m;
                    } else {
                        this.f14389w += f14346d;
                    }
                }
                if (this.f14387u != null) {
                    ((C2813b) this.f14388v).m16005a((ActionMode) this.f14387u, i, this.f14362B.getItemId(i), z);
                }
            } else {
                booleanValue = this.f14391y != null && this.f14362B.hasStableIds();
                if (z || m16130a(i)) {
                    this.f14390x.clear();
                    if (booleanValue) {
                        this.f14391y.clear();
                    }
                }
                if (z) {
                    this.f14390x.put(i, Boolean.valueOf(true));
                    if (booleanValue) {
                        this.f14391y.put(this.f14362B.getItemId(i), Integer.valueOf(i));
                    }
                    this.f14389w = f14355m;
                } else if (this.f14390x.size() == 0 || !((Boolean) this.f14390x.valueAt(f14354l)).booleanValue()) {
                    this.f14389w = f14354l;
                }
            }
            if (!this.aF && !this.aV) {
                this.aJ = true;
                m16067A();
                requestLayout();
            }
        }
    }

    public void m16126a(View view, View view2) {
        this.f14375O = view;
        this.f14376P = view2;
    }

    @SuppressLint({"NewApi"})
    public void m16127a(List<View> list) {
        int childCount = getChildCount();
        C2838q a = this.f14368H.f14574b;
        for (int i = f14354l; i < childCount; i += f14355m) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams != null && this.f14368H.m16399b(layoutParams.f14323a)) {
                list.add(childAt);
                if (VERSION.SDK_INT >= 14) {
                    childAt.setAccessibilityDelegate(null);
                }
                if (a != null) {
                    a.m16407a(childAt);
                }
            }
        }
        this.f14368H.m16397a((List) list);
        removeAllViewsInLayout();
    }

    protected abstract void m16128a(boolean z);

    public boolean m16129a(float f, float f2, int i) {
        int a = m16118a((int) f, (int) f2);
        if (a != f14346d) {
            long itemId = this.f14362B.getItemId(a);
            View childAt = getChildAt(a - this.av);
            if (childAt != null) {
                this.bc = m16134b(childAt, a, itemId);
                return super.showContextMenuForChild(this);
            }
        }
        return m16129a(f, f2, i);
    }

    public boolean m16130a(int i) {
        return (this.f14386t == 0 || this.f14390x == null) ? false : ((Boolean) this.f14390x.get(i, Boolean.valueOf(false))).booleanValue();
    }

    @TargetApi(14)
    protected boolean m16131a(MotionEvent motionEvent) {
        return VERSION.SDK_INT >= 14 && (motionEvent.getButtonState() & f14356n) != 0 && m16129a(motionEvent.getX(), motionEvent.getY(), motionEvent.getMetaState());
    }

    public boolean m16132a(View view, int i, long j) {
        boolean z;
        boolean z2 = true;
        boolean z3 = false;
        if (this.f14386t != 0) {
            if (this.f14386t == f14356n || (VERSION.SDK_INT >= 11 && this.f14386t == f14357o && this.f14387u != null)) {
                boolean z4 = !((Boolean) this.f14390x.get(i, Boolean.valueOf(false))).booleanValue();
                this.f14390x.put(i, Boolean.valueOf(z4));
                if (this.f14391y != null && this.f14362B.hasStableIds()) {
                    if (z4) {
                        this.f14391y.put(this.f14362B.getItemId(i), Integer.valueOf(i));
                    } else {
                        this.f14391y.delete(this.f14362B.getItemId(i));
                    }
                }
                if (z4) {
                    this.f14389w += f14355m;
                } else {
                    this.f14389w += f14346d;
                }
                if (this.f14387u != null) {
                    ((C2813b) this.f14388v).m16005a((ActionMode) this.f14387u, i, j, z4);
                } else {
                    z3 = true;
                }
                z = z3;
                z3 = true;
            } else if (this.f14386t == f14355m) {
                if (!((Boolean) this.f14390x.get(i, Boolean.valueOf(false))).booleanValue()) {
                    this.f14390x.clear();
                    this.f14390x.put(i, Boolean.valueOf(true));
                    if (this.f14391y != null && this.f14362B.hasStableIds()) {
                        this.f14391y.clear();
                        this.f14391y.put(this.f14362B.getItemId(i), Integer.valueOf(i));
                    }
                    this.f14389w = f14355m;
                } else if (this.f14390x.size() == 0 || !((Boolean) this.f14390x.valueAt(f14354l)).booleanValue()) {
                    this.f14389w = f14354l;
                }
                z3 = true;
                z = true;
            } else {
                z = true;
            }
            if (z3) {
                m16080C();
            }
            boolean z5 = z;
            z = true;
            z2 = z5;
        } else {
            z = false;
        }
        return z2 ? z | super.m16069a(view, i, j) : z;
    }

    public void addTouchables(ArrayList<View> arrayList) {
        int childCount = getChildCount();
        int i = this.av;
        ListAdapter listAdapter = this.f14362B;
        if (listAdapter != null) {
            for (int i2 = f14354l; i2 < childCount; i2 += f14355m) {
                View childAt = getChildAt(i2);
                if (listAdapter.isEnabled(i + i2)) {
                    arrayList.add(childAt);
                }
                childAt.addTouchables(arrayList);
            }
        }
    }

    public long m16133b(int i, int i2) {
        int a = m16118a(i, i2);
        return a >= 0 ? this.f14362B.getItemId(a) : Long.MIN_VALUE;
    }

    ContextMenuInfo m16134b(View view, int i, long j) {
        return new C2842v(view, i, j);
    }

    void m16135b(int i) {
        if (i != this.bq && this.aZ != null) {
            this.bq = i;
            this.aZ.m16382a(this, i);
        }
    }

    @ExportedProperty
    public boolean m16136b() {
        return this.ba;
    }

    protected void m16137c() {
        if (this.aZ != null) {
            this.aZ.m16383a(this, this.av, getChildCount(), this.aO);
        }
        onScrollChanged(f14354l, f14354l, f14354l, f14354l);
    }

    public void m16138c(int i) {
        if (this.ac == null) {
            this.ac = new C2833l(this);
        }
        this.ac.m16385a(i);
    }

    public void m16139c(int i, int i2) {
        this.bE = i;
        this.bF = i2;
    }

    boolean m16140c(View view, int i, long j) {
        if (VERSION.SDK_INT < 11 || this.f14386t != f14357o) {
            boolean a = this.aI != null ? this.aI.m16412a(this, view, i, j) : false;
            if (!a) {
                this.bc = m16134b(view, i, j);
                a = super.showContextMenuForChild(this);
            }
            if (!a) {
                return a;
            }
            performHapticFeedback(f14354l);
            return a;
        }
        if (this.f14387u == null) {
            ActionMode startActionMode = startActionMode((C2813b) this.f14388v);
            this.f14387u = startActionMode;
            if (startActionMode != null) {
                m16125a(i, true);
                performHapticFeedback(f14354l);
            }
        }
        return true;
    }

    public boolean checkInputConnectionProxy(View view) {
        return false;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    protected int computeHorizontalScrollExtent() {
        int childCount = getChildCount();
        if (childCount <= 0) {
            return f14354l;
        }
        if (!this.ba) {
            return f14355m;
        }
        int i = childCount * 100;
        View childAt = getChildAt(f14354l);
        int left = childAt.getLeft();
        int width = childAt.getWidth();
        if (width > 0) {
            i += (left * 100) / width;
        }
        childAt = getChildAt(childCount + f14346d);
        childCount = childAt.getRight();
        width = childAt.getWidth();
        return width > 0 ? i - (((childCount - getWidth()) * 100) / width) : i;
    }

    protected int computeHorizontalScrollOffset() {
        int i = f14354l;
        int i2 = this.av;
        int childCount = getChildCount();
        if (i2 < 0 || childCount <= 0) {
            return f14354l;
        }
        int width;
        if (this.ba) {
            View childAt = getChildAt(f14354l);
            childCount = childAt.getLeft();
            width = childAt.getWidth();
            return width > 0 ? Math.max(((i2 * 100) - ((childCount * 100) / width)) + ((int) (((((float) getScrollX()) / ((float) getWidth())) * ((float) this.aO)) * 100.0f)), f14354l) : f14354l;
        } else {
            width = this.aO;
            if (i2 != 0) {
                i = i2 + childCount == width ? width : (childCount / f14356n) + i2;
            }
            return (int) (((((float) i) / ((float) width)) * ((float) childCount)) + ((float) i2));
        }
    }

    protected int computeHorizontalScrollRange() {
        if (!this.ba) {
            return this.aO;
        }
        int max = Math.max(this.aO * 100, f14354l);
        return getScrollX() != 0 ? max + Math.abs((int) (((((float) getScrollX()) / ((float) getWidth())) * ((float) this.aO)) * 100.0f)) : max;
    }

    protected void m16141d(int i) {
        int firstVisiblePosition = i < 0 ? getFirstVisiblePosition() : i > 0 ? getLastVisiblePosition() : f14346d;
        if (firstVisiblePosition > f14346d) {
            View childAt = getChildAt(firstVisiblePosition - getFirstVisiblePosition());
            if (childAt != null) {
                Rect rect = new Rect();
                if (childAt.getGlobalVisibleRect(rect)) {
                    float height = ((float) (rect.height() * rect.width())) / ((float) (childAt.getHeight() * childAt.getWidth()));
                    if (i < 0 && height < 0.75f) {
                        firstVisiblePosition += f14355m;
                    } else if (i > 0 && height < 0.75f) {
                        firstVisiblePosition += f14346d;
                    }
                }
                m16138c(Math.max(f14354l, Math.min(getCount(), firstVisiblePosition + i)));
            }
        }
    }

    public void m16142d(int i, int i2) {
        if (this.ac == null) {
            this.ac = new C2833l(this);
        }
        this.ac.m16388b(i, i2);
    }

    @ExportedProperty
    public boolean m16143d() {
        return this.af;
    }

    protected void dispatchDraw(Canvas canvas) {
        boolean z = this.f14364D;
        if (!z) {
            m16094a(canvas);
        }
        super.dispatchDraw(canvas);
        if (z) {
            m16094a(canvas);
        }
    }

    protected void dispatchSetPressed(boolean z) {
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.by != null) {
            int save;
            int i;
            int height;
            int scrollX = getScrollX();
            if (!this.by.m16277a()) {
                save = canvas.save();
                i = this.f14373M.top + this.bE;
                height = (getHeight() - i) - (this.f14373M.bottom + this.bF);
                int min = Math.min(f14354l, this.bA + scrollX);
                canvas.rotate(-90.0f);
                canvas.translate((float) ((-getHeight()) + i), (float) min);
                this.by.m16276a(height, height);
                if (this.by.m16278a(canvas)) {
                    this.by.m16280b(min, i);
                    invalidate();
                }
                canvas.restoreToCount(save);
            }
            if (!this.bz.m16277a()) {
                save = canvas.save();
                i = this.f14373M.left + this.bE;
                height = (getHeight() - i) - (this.f14373M.right + this.bF);
                scrollX = Math.max(getWidth(), scrollX + this.bB);
                canvas.rotate(90.0f);
                canvas.translate((float) (-i), (float) (-scrollX));
                this.bz.m16276a(height, height);
                if (this.bz.m16278a(canvas)) {
                    invalidate();
                }
                canvas.restoreToCount(save);
            }
        }
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        m16159m();
    }

    public void m16144e(int i) {
        int childCount = getChildCount();
        for (int i2 = f14354l; i2 < childCount; i2 += f14355m) {
            getChildAt(i2).offsetLeftAndRight(i);
        }
    }

    public void m16145e(int i, int i2) {
        if (this.ac == null) {
            this.ac = new C2833l(this);
        }
        this.ac.m16386a(i, i2);
    }

    public boolean m16146e() {
        return this.ae;
    }

    protected abstract int m16147f(int i);

    void m16148f() {
        if (getChildCount() > 0) {
            m16151g();
            requestLayout();
            invalidate();
        }
    }

    public void m16149f(int i, int i2) {
        m16123a(i, i2, false);
    }

    protected int m16150g(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return f14346d;
        }
        int f = m16147f(i);
        return f == f14346d ? (this.av + childCount) + f14346d : f;
    }

    protected void m16151g() {
        removeAllViewsInLayout();
        this.av = f14354l;
        this.aJ = false;
        this.ak = null;
        this.aA = false;
        this.bK = null;
        this.aT = f14346d;
        this.aU = Long.MIN_VALUE;
        setSelectedPositionInt(f14346d);
        setNextSelectedPositionInt(f14346d);
        this.ad = f14354l;
        this.f14366F = f14346d;
        this.f14367G.setEmpty();
        invalidate();
    }

    boolean m16152g(int i, int i2) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return true;
        }
        int left = getChildAt(f14354l).getLeft();
        int right = getChildAt(childCount + f14346d).getRight();
        Rect rect = this.f14373M;
        int i3 = f14354l - left;
        int width = right - (getWidth() - f14354l);
        int width2 = (getWidth() - getPaddingRight()) - getPaddingLeft();
        int max = i < 0 ? Math.max(-(width2 + f14346d), i) : Math.min(width2 + f14346d, i);
        int max2 = i2 < 0 ? Math.max(-(width2 + f14346d), i2) : Math.min(width2 + f14346d, i2);
        int i4 = this.av;
        if (i4 == 0) {
            this.bA = left - rect.left;
        } else {
            this.bA += max2;
        }
        if (i4 + childCount == this.aO) {
            this.bB = rect.right + right;
        } else {
            this.bB += max2;
        }
        Object obj = (i4 != 0 || left < rect.left || max2 < 0) ? null : f14355m;
        Object obj2 = (i4 + childCount != this.aO || right > getWidth() - rect.right || max2 > 0) ? null : f14355m;
        if (obj != null || obj2 != null) {
            return max2 != 0;
        } else {
            boolean z = max2 < 0;
            boolean isInTouchMode = isInTouchMode();
            if (isInTouchMode) {
                m16161o();
            }
            int headerViewsCount = getHeaderViewsCount();
            int footerViewsCount = this.aO - getFooterViewsCount();
            int i5 = f14354l;
            int width3;
            if (!z) {
                width3 = getWidth() - max2;
                right = f14354l;
                for (width2 = childCount + f14346d; width2 >= 0; width2 += f14346d) {
                    View childAt = getChildAt(width2);
                    if (childAt.getLeft() <= width3) {
                        break;
                    }
                    i5 = right + f14355m;
                    right = i4 + width2;
                    if (right >= headerViewsCount && right < footerViewsCount) {
                        this.f14368H.m16396a(childAt, right);
                    }
                    right = i5;
                    i5 = width2;
                }
            } else {
                int i6 = -max2;
                right = f14354l;
                width2 = f14354l;
                while (width2 < childCount) {
                    View childAt2 = getChildAt(width2);
                    if (childAt2.getRight() >= i6) {
                        break;
                    }
                    width3 = right + f14355m;
                    right = i4 + width2;
                    if (right >= headerViewsCount && right < footerViewsCount) {
                        this.f14368H.m16396a(childAt2, right);
                    }
                    width2 += f14355m;
                    right = width3;
                }
            }
            this.f14381U = this.f14380T + max;
            this.aV = true;
            if (right > 0) {
                detachViewsFromParent(i5, right);
                this.f14368H.m16403d();
            }
            if (!awakenScrollBars()) {
                invalidate();
            }
            m16144e(max2);
            if (z) {
                this.av = right + this.av;
            }
            max2 = Math.abs(max2);
            if (i3 < max2 || width < max2) {
                m16128a(z);
            }
            if (!isInTouchMode && this.aM != f14346d) {
                max2 = this.aM - this.av;
                if (max2 >= 0 && max2 < getChildCount()) {
                    m16124a(this.aM, getChildAt(max2));
                }
            } else if (this.f14366F != f14346d) {
                max2 = this.f14366F - this.av;
                if (max2 >= 0 && max2 < getChildCount()) {
                    m16124a((int) f14346d, getChildAt(max2));
                }
            } else {
                this.f14367G.setEmpty();
            }
            this.aV = false;
            m16137c();
            return false;
        }
    }

    protected android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, f14346d, f14354l);
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return m16120a(attributeSet);
    }

    protected android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @ExportedProperty(category = "drawing")
    public int getCacheColorHint() {
        return this.bo;
    }

    public int getCheckedItemCount() {
        return this.f14389w;
    }

    public long[] getCheckedItemIds() {
        int i = f14354l;
        if (this.f14386t == 0 || this.f14391y == null || this.f14362B == null) {
            return new long[f14354l];
        }
        LongSparseArray longSparseArray = this.f14391y;
        int size = longSparseArray.size();
        long[] jArr = new long[size];
        while (i < size) {
            jArr[i] = longSparseArray.keyAt(i);
            i += f14355m;
        }
        return jArr;
    }

    public int getCheckedItemPosition() {
        return (this.f14386t == f14355m && this.f14390x != null && this.f14390x.size() == f14355m) ? this.f14390x.keyAt(f14354l) : f14346d;
    }

    public SparseArrayCompat<Boolean> getCheckedItemPositions() {
        return this.f14386t != 0 ? this.f14390x : null;
    }

    public int getChoiceMode() {
        return this.f14386t;
    }

    protected ContextMenuInfo getContextMenuInfo() {
        return this.bc;
    }

    public void getFocusedRect(Rect rect) {
        View selectedView = getSelectedView();
        if (selectedView == null || selectedView.getParent() != this) {
            super.getFocusedRect(rect);
            return;
        }
        selectedView.getFocusedRect(rect);
        offsetDescendantRectToMyCoords(selectedView, rect);
    }

    protected int getFooterViewsCount() {
        return f14354l;
    }

    protected int getHeaderViewsCount() {
        return f14354l;
    }

    protected float getHorizontalScrollFactor() {
        if (this.bL == 0.0f) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(C2811R.attr.hlv_listPreferredItemWidth, typedValue, true)) {
                this.bL = typedValue.getDimension(getContext().getResources().getDisplayMetrics());
            } else {
                throw new IllegalStateException("Expected theme to define hlv_listPreferredItemWidth.");
            }
        }
        return this.bL;
    }

    protected int getHorizontalScrollbarHeight() {
        return super.getHorizontalScrollbarHeight();
    }

    protected float getLeftFadingEdgeStrength() {
        int childCount = getChildCount();
        float leftFadingEdgeStrength = super.getLeftFadingEdgeStrength();
        if (childCount == 0) {
            return leftFadingEdgeStrength;
        }
        if (this.av > 0) {
            return C2020f.f10933c;
        }
        childCount = getChildAt(f14354l).getLeft();
        return childCount < getPaddingLeft() ? ((float) (-(childCount - getPaddingLeft()))) / ((float) getHorizontalFadingEdgeLength()) : leftFadingEdgeStrength;
    }

    public int getListPaddingBottom() {
        return this.f14373M.bottom;
    }

    public int getListPaddingLeft() {
        return this.f14373M.left;
    }

    public int getListPaddingRight() {
        return this.f14373M.right;
    }

    public int getListPaddingTop() {
        return this.f14373M.top;
    }

    protected float getRightFadingEdgeStrength() {
        int childCount = getChildCount();
        float rightFadingEdgeStrength = super.getRightFadingEdgeStrength();
        if (childCount == 0) {
            return rightFadingEdgeStrength;
        }
        if ((this.av + childCount) + f14346d < this.aO + f14346d) {
            return C2020f.f10933c;
        }
        childCount = getChildAt(childCount + f14346d).getRight();
        int width = getWidth();
        return childCount > width - getPaddingRight() ? ((float) ((childCount - width) + getPaddingRight())) / ((float) getHorizontalFadingEdgeLength()) : rightFadingEdgeStrength;
    }

    @ExportedProperty
    public View getSelectedView() {
        return (this.aO <= 0 || this.aM < 0) ? null : getChildAt(this.aM - this.av);
    }

    public Drawable getSelector() {
        return this.f14365E;
    }

    public int getSolidColor() {
        return this.bo;
    }

    public int getTranscriptMode() {
        return this.bn;
    }

    protected void m16153h() {
    }

    protected void m16154h(int i, int i2) {
    }

    protected void m16155i() {
        int i;
        int i2 = f14355m;
        int i3 = f14354l;
        if (this.f14375O != null) {
            i = this.av > 0 ? f14355m : f14354l;
            if (i == 0 && getChildCount() > 0) {
                i = getChildAt(f14354l).getLeft() < this.f14373M.left ? f14355m : f14354l;
            }
            this.f14375O.setVisibility(i != 0 ? f14354l : f14358p);
        }
        if (this.f14376P != null) {
            int childCount = getChildCount();
            i = this.av + childCount < this.aO ? f14355m : f14354l;
            if (i != 0 || childCount <= 0) {
                i2 = i;
            } else if (getChildAt(childCount + f14346d).getRight() <= getRight() - this.f14373M.right) {
                i2 = f14354l;
            }
            View view = this.f14376P;
            if (i2 == 0) {
                i3 = f14358p;
            }
            view.setVisibility(i3);
        }
    }

    boolean m16156j() {
        switch (this.f14384Z) {
            case f14355m /*1*/:
            case f14356n /*2*/:
                return true;
            default:
                return false;
        }
    }

    @TargetApi(11)
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (this.f14365E != null) {
            this.f14365E.jumpToCurrentState();
        }
    }

    protected boolean m16157k() {
        return (hasFocus() && !isInTouchMode()) || m16156j();
    }

    protected void m16158l() {
        if (isEnabled() && isClickable()) {
            Drawable drawable = this.f14365E;
            Rect rect = this.f14367G;
            if (drawable == null) {
                return;
            }
            if ((isFocused() || m16156j()) && !rect.isEmpty()) {
                View childAt = getChildAt(this.aM - this.av);
                if (childAt != null) {
                    if (!childAt.hasFocusable()) {
                        childAt.setPressed(true);
                    } else {
                        return;
                    }
                }
                setPressed(true);
                boolean isLongClickable = isLongClickable();
                drawable = drawable.getCurrent();
                if (drawable != null && (drawable instanceof TransitionDrawable)) {
                    if (isLongClickable) {
                        ((TransitionDrawable) drawable).startTransition(ViewConfiguration.getLongPressTimeout());
                    } else {
                        ((TransitionDrawable) drawable).resetTransition();
                    }
                }
                if (isLongClickable && !this.aJ) {
                    if (this.bk == null) {
                        this.bk = new C2825d();
                    }
                    this.bk.m16372a();
                    postDelayed(this.bk, (long) ViewConfiguration.getLongPressTimeout());
                }
            }
        }
    }

    void m16159m() {
        if (this.f14365E == null) {
            return;
        }
        if (m16157k()) {
            this.f14365E.setState(getDrawableState());
        } else {
            this.f14365E.setState(aq);
        }
    }

    @TargetApi(11)
    protected void m16160n() {
        if (this.f14385s.m16045a() && (getParent() instanceof View)) {
            ((View) getParent()).invalidate();
        }
    }

    protected void m16161o() {
        if (this.aM != f14346d) {
            if (this.f14392z != f14358p) {
                this.ah = this.aM;
            }
            if (this.aK >= 0 && this.aK != this.aM) {
                this.ah = this.aK;
            }
            setSelectedPositionInt(f14346d);
            setNextSelectedPositionInt(f14346d);
            this.ad = f14354l;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnTouchModeChangeListener(this);
        if (this.f14362B != null && this.f14361A == null) {
            this.f14361A = new C2823c(this);
            this.f14362B.registerDataSetObserver(this.f14361A);
            this.aJ = true;
            this.aP = this.aO;
            this.aO = this.f14362B.getCount();
        }
        this.ao = true;
    }

    @SuppressLint({"Override"})
    protected int[] onCreateDrawableState(int i) {
        if (this.bp) {
            return super.onCreateDrawableState(i);
        }
        int i2 = ENABLED_STATE_SET[f14354l];
        Object onCreateDrawableState = super.onCreateDrawableState(i + f14355m);
        int length = onCreateDrawableState.length + f14346d;
        while (length >= 0) {
            if (onCreateDrawableState[length] == i2) {
                break;
            }
            length += f14346d;
        }
        length = f14346d;
        if (length < 0) {
            return onCreateDrawableState;
        }
        System.arraycopy(onCreateDrawableState, length + f14355m, onCreateDrawableState, length, (onCreateDrawableState.length - length) + f14346d);
        return onCreateDrawableState;
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return null;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f14368H.m16398b();
        getViewTreeObserver().removeOnTouchModeChangeListener(this);
        if (!(this.f14362B == null || this.f14361A == null)) {
            this.f14362B.unregisterDataSetObserver(this.f14361A);
            this.f14361A = null;
        }
        if (this.aY != null) {
            removeCallbacks(this.aY);
        }
        if (this.ac != null) {
            this.ac.m16384a();
        }
        if (this.bs != null) {
            removeCallbacks(this.bs);
        }
        if (this.bl != null) {
            removeCallbacks(this.bl);
        }
        if (this.bm != null) {
            removeCallbacks(this.bm);
            this.bm = null;
        }
        this.ao = false;
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z && this.aM < 0 && !isInTouchMode()) {
            if (!(this.ao || this.f14362B == null)) {
                this.aJ = true;
                this.aP = this.aO;
                this.aO = this.f14362B.getCount();
            }
            m16165s();
        }
    }

    @TargetApi(12)
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & f14356n) != 0) {
            switch (motionEvent.getAction()) {
                case Type.DOUBLE /*8*/:
                    if (this.f14384Z == f14346d) {
                        float axisValue = motionEvent.getAxisValue(10);
                        if (axisValue != 0.0f) {
                            int horizontalScrollFactor = (int) (axisValue * getHorizontalScrollFactor());
                            if (!m16152g(horizontalScrollFactor, horizontalScrollFactor)) {
                                return true;
                            }
                        }
                    }
                    break;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @TargetApi(14)
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(AbsHListView.class.getName());
    }

    @SuppressLint({"Override"})
    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(AbsHListView.class.getName());
        if (isEnabled()) {
            if (getFirstVisiblePosition() > 0) {
                accessibilityNodeInfo.addAction(Opcodes.ACC_ANNOTATION);
            }
            if (getLastVisiblePosition() < getCount() + f14346d) {
                accessibilityNodeInfo.addAction(Opcodes.ACC_SYNTHETIC);
            }
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (this.ac != null) {
            this.ac.m16384a();
        }
        if (!this.ao) {
            return false;
        }
        switch (action & Util.MASK_8BIT) {
            case f14354l /*0*/:
                action = this.f14384Z;
                if (action == f14360r || action == f14359q) {
                    this.ab = f14354l;
                    return true;
                }
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                this.bw = motionEvent.getPointerId(f14354l);
                int f = m16147f(x);
                if (action != f14358p && f >= 0) {
                    this.f14380T = getChildAt(f - this.av).getLeft();
                    this.f14382V = x;
                    this.f14383W = y;
                    this.f14379S = f;
                    this.f14384Z = f14354l;
                    m16087J();
                }
                this.aa = C1186y.f5353a;
                m16083F();
                this.aX.addMovement(motionEvent);
                return action == f14358p;
            case f14355m /*1*/:
            case f14357o /*3*/:
                this.f14384Z = f14346d;
                this.bw = f14346d;
                m16085H();
                m16135b((int) f14354l);
                return false;
            case f14356n /*2*/:
                switch (this.f14384Z) {
                    case f14354l /*0*/:
                        action = motionEvent.findPointerIndex(this.bw);
                        if (action == f14346d) {
                            this.bw = motionEvent.getPointerId(f14354l);
                            action = f14354l;
                        }
                        action = (int) motionEvent.getX(action);
                        m16084G();
                        this.aX.addMovement(motionEvent);
                        return m16115j(action);
                    default:
                        return false;
                }
            case f14360r /*6*/:
                m16099b(motionEvent);
                return false;
            default:
                return false;
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (i) {
            case Opcodes.FLOAD /*23*/:
            case UsbId.ARDUINO_MEGA_2560_R3 /*66*/:
                if (!isEnabled()) {
                    return true;
                }
                if (isClickable() && isPressed() && this.aM >= 0 && this.f14362B != null && this.aM < this.f14362B.getCount()) {
                    View childAt = getChildAt(this.aM - this.av);
                    if (childAt != null) {
                        m16132a(childAt, this.aM, this.aN);
                        childAt.setPressed(false);
                    }
                    setPressed(false);
                    return true;
                }
        }
        return super.onKeyUp(i, keyEvent);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.aF = true;
        if (z) {
            int childCount = getChildCount();
            for (int i5 = f14354l; i5 < childCount; i5 += f14355m) {
                getChildAt(i5).forceLayout();
            }
            this.f14368H.m16393a();
        }
        m16153h();
        this.aF = false;
        this.ai = (i3 - i) / f14357o;
    }

    protected void onMeasure(int i, int i2) {
        if (this.f14365E == null) {
            m16082E();
        }
        Rect rect = this.f14373M;
        rect.left = this.f14369I + getPaddingLeft();
        rect.top = this.f14370J + getPaddingTop();
        rect.right = this.f14371K + getPaddingRight();
        rect.bottom = this.f14372L + getPaddingBottom();
        if (this.bn == f14355m) {
            int childCount = getChildCount();
            int width = getWidth() - getPaddingRight();
            View childAt = getChildAt(childCount + f14346d);
            boolean z = childCount + this.av >= this.bJ && (childAt != null ? childAt.getRight() : width) <= width;
            this.bD = z;
        }
    }

    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        if (getScrollX() != i) {
            onScrollChanged(i, getScrollY(), getScrollX(), getScrollY());
            this.f14385s.m16043a(i);
            m16160n();
            awakenScrollBars();
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.aJ = true;
        this.az = (long) savedState.f14332e;
        if (savedState.f14328a >= 0) {
            this.aA = true;
            this.bK = savedState;
            this.ay = savedState.f14328a;
            this.ax = savedState.f14331d;
            this.aw = savedState.f14330c;
            this.aB = f14354l;
        } else if (savedState.f14329b >= 0) {
            setSelectedPositionInt(f14346d);
            setNextSelectedPositionInt(f14346d);
            this.f14366F = f14346d;
            this.aA = true;
            this.bK = savedState;
            this.ay = savedState.f14329b;
            this.ax = savedState.f14331d;
            this.aw = savedState.f14330c;
            this.aB = f14355m;
        }
        if (savedState.f14336i != null) {
            this.f14390x = savedState.f14336i;
        }
        if (savedState.f14337j != null) {
            this.f14391y = savedState.f14337j;
        }
        this.f14389w = savedState.f14335h;
        if (VERSION.SDK_INT >= 11 && savedState.f14334g && this.f14386t == f14357o && this.f14388v != null) {
            this.f14387u = startActionMode((C2813b) this.f14388v);
        }
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        boolean z = true;
        int i = f14354l;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.bK != null) {
            savedState.f14328a = this.bK.f14328a;
            savedState.f14329b = this.bK.f14329b;
            savedState.f14330c = this.bK.f14330c;
            savedState.f14331d = this.bK.f14331d;
            savedState.f14332e = this.bK.f14332e;
            savedState.f14333f = this.bK.f14333f;
            savedState.f14334g = this.bK.f14334g;
            savedState.f14335h = this.bK.f14335h;
            savedState.f14336i = this.bK.f14336i;
            savedState.f14337j = this.bK.f14337j;
            return savedState;
        }
        boolean z2 = (getChildCount() <= 0 || this.aO <= 0) ? f14354l : true;
        long selectedItemId = getSelectedItemId();
        savedState.f14328a = selectedItemId;
        savedState.f14332e = getWidth();
        if (selectedItemId >= 0) {
            savedState.f14330c = this.ad;
            savedState.f14331d = getSelectedItemPosition();
            savedState.f14329b = -1;
        } else if (!z2 || this.av <= 0) {
            savedState.f14330c = f14354l;
            savedState.f14329b = -1;
            savedState.f14331d = f14354l;
        } else {
            savedState.f14330c = getChildAt(f14354l).getLeft();
            int i2 = this.av;
            if (i2 >= this.aO) {
                i2 = this.aO + f14346d;
            }
            savedState.f14331d = i2;
            savedState.f14329b = this.f14362B.getItemId(i2);
        }
        savedState.f14333f = null;
        if (VERSION.SDK_INT < 11 || this.f14386t != f14357o || this.f14387u == null) {
            z = false;
        }
        savedState.f14334g = z;
        if (this.f14390x != null) {
            try {
                savedState.f14336i = this.f14390x.clone();
            } catch (NoSuchMethodError e) {
                e.printStackTrace();
                savedState.f14336i = new SparseArrayCompat();
            }
        }
        if (this.f14391y != null) {
            LongSparseArray longSparseArray = new LongSparseArray();
            int size = this.f14391y.size();
            while (i < size) {
                longSparseArray.put(this.f14391y.keyAt(i), this.f14391y.valueAt(i));
                i += f14355m;
            }
            savedState.f14337j = longSparseArray;
        }
        savedState.f14335h = this.f14389w;
        return savedState;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (getChildCount() > 0) {
            this.aJ = true;
            m16067A();
        }
    }

    @SuppressLint({"Override"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = f14354l;
        if (isEnabled()) {
            if (this.ac != null) {
                this.ac.m16384a();
            }
            if (!this.ao) {
                return false;
            }
            int action = motionEvent.getAction();
            m16084G();
            this.aX.addMovement(motionEvent);
            int x;
            int y;
            int a;
            Handler handler;
            switch (action & Util.MASK_8BIT) {
                case f14354l /*0*/:
                    switch (this.f14384Z) {
                        case f14360r /*6*/:
                            this.aY.m16378b();
                            if (this.ac != null) {
                                this.ac.m16384a();
                            }
                            this.f14384Z = f14359q;
                            this.f14383W = (int) motionEvent.getY();
                            action = (int) motionEvent.getX();
                            this.aa = action;
                            this.f14382V = action;
                            this.ab = f14354l;
                            this.bw = motionEvent.getPointerId(f14354l);
                            this.bC = f14354l;
                            break;
                        default:
                            this.bw = motionEvent.getPointerId(f14354l);
                            x = (int) motionEvent.getX();
                            y = (int) motionEvent.getY();
                            a = m16118a(x, y);
                            if (!this.aJ) {
                                if (this.f14384Z == f14358p || a < 0 || !((ListAdapter) getAdapter()).isEnabled(a)) {
                                    if (this.f14384Z == f14358p) {
                                        m16086I();
                                        this.f14384Z = f14357o;
                                        this.ab = f14354l;
                                        action = m16147f(x);
                                        this.aY.m16380c();
                                        if (action >= 0) {
                                            this.f14380T = getChildAt(action - this.av).getLeft();
                                        }
                                        this.f14382V = x;
                                        this.f14383W = y;
                                        this.f14379S = action;
                                        this.aa = C1186y.f5353a;
                                        break;
                                    }
                                }
                                this.f14384Z = f14354l;
                                if (this.bj == null) {
                                    this.bj = new C2827f(this);
                                }
                                postDelayed(this.bj, (long) ViewConfiguration.getTapTimeout());
                                action = a;
                                if (action >= 0) {
                                    this.f14380T = getChildAt(action - this.av).getLeft();
                                }
                                this.f14382V = x;
                                this.f14383W = y;
                                this.f14379S = action;
                                this.aa = C1186y.f5353a;
                            }
                            action = a;
                            if (action >= 0) {
                                this.f14380T = getChildAt(action - this.av).getLeft();
                            }
                            this.f14382V = x;
                            this.f14383W = y;
                            this.f14379S = action;
                            this.aa = C1186y.f5353a;
                            break;
                    }
                    if (!m16131a(motionEvent) || this.f14384Z != 0) {
                        return true;
                    }
                    removeCallbacks(this.bj);
                    return true;
                case f14355m /*1*/:
                    switch (this.f14384Z) {
                        case f14354l /*0*/:
                        case f14355m /*1*/:
                        case f14356n /*2*/:
                            a = this.f14379S;
                            View childAt = getChildAt(a - this.av);
                            float x2 = motionEvent.getX();
                            boolean z = (x2 <= ((float) this.f14373M.left) || x2 >= ((float) (getWidth() - this.f14373M.right))) ? f14354l : true;
                            if (!(childAt == null || childAt.hasFocusable() || !z)) {
                                if (this.f14384Z != 0) {
                                    childAt.setPressed(false);
                                }
                                if (this.bl == null) {
                                    this.bl = new C2832k();
                                }
                                C2832k c2832k = this.bl;
                                c2832k.f14548a = a;
                                c2832k.m16372a();
                                this.ah = a;
                                if (this.f14384Z == 0 || this.f14384Z == f14355m) {
                                    Handler handler2 = getHandler();
                                    if (handler2 != null) {
                                        handler2.removeCallbacks(this.f14384Z == 0 ? this.bj : this.bi);
                                    }
                                    this.f14392z = f14354l;
                                    if (this.aJ || !this.f14362B.isEnabled(a)) {
                                        this.f14384Z = f14346d;
                                        m16159m();
                                        return true;
                                    }
                                    this.f14384Z = f14355m;
                                    setSelectedPositionInt(this.f14379S);
                                    m16153h();
                                    childAt.setPressed(true);
                                    m16124a(this.f14379S, childAt);
                                    setPressed(true);
                                    if (this.f14365E != null) {
                                        Drawable current = this.f14365E.getCurrent();
                                        if (current != null && (current instanceof TransitionDrawable)) {
                                            ((TransitionDrawable) current).resetTransition();
                                        }
                                    }
                                    if (this.bm != null) {
                                        removeCallbacks(this.bm);
                                    }
                                    this.bm = new C2820a(this, childAt, c2832k);
                                    postDelayed(this.bm, (long) ViewConfiguration.getPressedStateDuration());
                                    return true;
                                } else if (!this.aJ && this.f14362B.isEnabled(a)) {
                                    c2832k.run();
                                }
                            }
                            this.f14384Z = f14346d;
                            m16159m();
                            break;
                        case f14357o /*3*/:
                            action = getChildCount();
                            if (action <= 0) {
                                this.f14384Z = f14346d;
                                m16135b((int) f14354l);
                                break;
                            }
                            a = getChildAt(f14354l).getLeft();
                            x = getChildAt(action + f14346d).getRight();
                            y = this.f14373M.left;
                            int width = getWidth() - this.f14373M.right;
                            if (this.av == 0 && a >= y && this.av + action < this.aO && x <= getWidth() - width) {
                                this.f14384Z = f14346d;
                                m16135b((int) f14354l);
                                break;
                            }
                            VelocityTracker velocityTracker = this.aX;
                            velocityTracker.computeCurrentVelocity(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, (float) this.bu);
                            int xVelocity = (int) (velocityTracker.getXVelocity(this.bw) * this.bv);
                            if (Math.abs(xVelocity) > this.bt && ((this.av != 0 || a != y - this.am) && (action + this.av != this.aO || x != this.am + width))) {
                                if (this.aY == null) {
                                    this.aY = new C2828g(this);
                                }
                                m16135b((int) f14356n);
                                this.aY.m16376a(-xVelocity);
                                break;
                            }
                            this.f14384Z = f14346d;
                            m16135b((int) f14354l);
                            if (this.aY != null) {
                                this.aY.m16378b();
                            }
                            if (this.ac != null) {
                                this.ac.m16384a();
                                break;
                            }
                            break;
                        case f14359q /*5*/:
                            if (this.aY == null) {
                                this.aY = new C2828g(this);
                            }
                            VelocityTracker velocityTracker2 = this.aX;
                            velocityTracker2.computeCurrentVelocity(XiaomiOAuthConstants.SCOPE_ACCESS_MI_ROUTER, (float) this.bu);
                            action = (int) velocityTracker2.getXVelocity(this.bw);
                            m16135b((int) f14356n);
                            if (Math.abs(action) <= this.bt) {
                                this.aY.m16375a();
                                break;
                            }
                            this.aY.m16379b(-action);
                            break;
                    }
                    setPressed(false);
                    if (this.by != null) {
                        this.by.m16281c();
                        this.bz.m16281c();
                    }
                    invalidate();
                    handler = getHandler();
                    if (handler != null) {
                        handler.removeCallbacks(this.bi);
                    }
                    m16085H();
                    this.bw = f14346d;
                    return true;
                case f14356n /*2*/:
                    action = motionEvent.findPointerIndex(this.bw);
                    if (action == f14346d) {
                        this.bw = motionEvent.getPointerId(f14354l);
                    } else {
                        i = action;
                    }
                    action = (int) motionEvent.getX(i);
                    if (this.aJ) {
                        m16153h();
                    }
                    switch (this.f14384Z) {
                        case f14354l /*0*/:
                        case f14355m /*1*/:
                        case f14356n /*2*/:
                            m16115j(action);
                            return true;
                        case f14357o /*3*/:
                        case f14359q /*5*/:
                            m16116k(action);
                            return true;
                        default:
                            return true;
                    }
                case f14357o /*3*/:
                    switch (this.f14384Z) {
                        case f14359q /*5*/:
                            if (this.aY == null) {
                                this.aY = new C2828g(this);
                            }
                            this.aY.m16375a();
                            break;
                        case f14360r /*6*/:
                            break;
                        default:
                            this.f14384Z = f14346d;
                            setPressed(false);
                            View childAt2 = getChildAt(this.f14379S - this.av);
                            if (childAt2 != null) {
                                childAt2.setPressed(false);
                            }
                            m16087J();
                            handler = getHandler();
                            if (handler != null) {
                                handler.removeCallbacks(this.bi);
                            }
                            m16085H();
                            break;
                    }
                    if (this.by != null) {
                        this.by.m16281c();
                        this.bz.m16281c();
                    }
                    this.bw = f14346d;
                    return true;
                case f14359q /*5*/:
                    action = motionEvent.getActionIndex();
                    a = motionEvent.getPointerId(action);
                    x = (int) motionEvent.getX(action);
                    action = (int) motionEvent.getY(action);
                    this.ab = f14354l;
                    this.bw = a;
                    this.f14382V = x;
                    this.f14383W = action;
                    action = m16118a(x, action);
                    if (action >= 0) {
                        this.f14380T = getChildAt(action - this.av).getLeft();
                        this.f14379S = action;
                    }
                    this.aa = x;
                    return true;
                case f14360r /*6*/:
                    m16099b(motionEvent);
                    action = this.f14382V;
                    i = m16118a(action, this.f14383W);
                    if (i >= 0) {
                        this.f14380T = getChildAt(i - this.av).getLeft();
                        this.f14379S = i;
                    }
                    this.aa = action;
                    return true;
                default:
                    return true;
            }
        }
        z = (isClickable() || isLongClickable()) ? true : f14354l;
        return z;
    }

    public void onTouchModeChanged(boolean z) {
        if (z) {
            m16161o();
            if (getWidth() > 0 && getChildCount() > 0) {
                m16153h();
            }
            m16159m();
            return;
        }
        int i = this.f14384Z;
        if (i == f14359q || i == f14360r) {
            if (this.aY != null) {
                this.aY.m16378b();
            }
            if (this.ac != null) {
                this.ac.m16384a();
            }
            if (getScrollX() != 0) {
                this.f14385s.m16043a((int) f14354l);
                m16088K();
                invalidate();
            }
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        int i = isInTouchMode() ? f14354l : f14355m;
        if (!z) {
            setChildrenDrawingCacheEnabled(false);
            if (this.aY != null) {
                removeCallbacks(this.aY);
                this.aY.m16378b();
                if (this.ac != null) {
                    this.ac.m16384a();
                }
                if (getScrollX() != 0) {
                    this.f14385s.m16043a((int) f14354l);
                    m16088K();
                    invalidate();
                }
            }
            if (i == f14355m) {
                this.ah = this.aM;
            }
        } else if (!(i == this.bh || this.bh == f14346d)) {
            if (i == f14355m) {
                m16165s();
            } else {
                m16161o();
                this.f14392z = f14354l;
                m16153h();
            }
        }
        this.bh = i;
    }

    protected int m16162p() {
        int i = this.aM;
        if (i < 0) {
            i = this.ah;
        }
        return Math.min(Math.max(f14354l, i), this.aO + f14346d);
    }

    @TargetApi(16)
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        if (super.performAccessibilityAction(i, bundle)) {
            return true;
        }
        switch (i) {
            case Opcodes.ACC_SYNTHETIC /*4096*/:
                if (!isEnabled() || getLastVisiblePosition() >= getCount() + f14346d) {
                    return false;
                }
                m16149f((getWidth() - this.f14373M.left) - this.f14373M.right, C2799f.f14282t);
                return true;
            case Opcodes.ACC_ANNOTATION /*8192*/:
                if (!isEnabled() || this.av <= 0) {
                    return false;
                }
                m16149f(-((getWidth() - this.f14373M.left) - this.f14373M.right), C2799f.f14282t);
                return true;
            default:
                return false;
        }
    }

    public void m16163q() {
        this.aJ = true;
        m16067A();
        requestLayout();
        invalidate();
    }

    protected boolean m16164r() {
        if (this.aM >= 0 || !m16165s()) {
            return false;
        }
        m16159m();
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            m16085H();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    public void requestLayout() {
        if (!this.aV && !this.aF) {
            super.requestLayout();
        }
    }

    boolean m16165s() {
        boolean z = true;
        int childCount = getChildCount();
        if (childCount <= 0) {
            return false;
        }
        int left;
        int i;
        boolean z2;
        boolean z3 = this.f14373M.left;
        int right = (getRight() - getLeft()) - this.f14373M.right;
        int i2 = this.av;
        int i3 = this.ah;
        if (i3 >= i2 && i3 < i2 + childCount) {
            View childAt = getChildAt(i3 - this.av);
            left = childAt.getLeft();
            childCount = childAt.getRight();
            if (left < z3) {
                left = getHorizontalFadingEdgeLength() + z3;
            } else if (childCount > right) {
                left = (right - childAt.getMeasuredWidth()) - getHorizontalFadingEdgeLength();
            }
            i = left;
            z2 = true;
        } else if (i3 < i2) {
            boolean left2;
            right = f14354l;
            z2 = false;
            while (right < childCount) {
                boolean z4;
                left2 = getChildAt(right).getLeft();
                if (right != 0) {
                    z4 = z3;
                    z3 = z2;
                    z2 = z4;
                } else if (i2 > 0 || left2 < z3) {
                    z2 = getHorizontalFadingEdgeLength() + z3;
                    z3 = left2;
                } else {
                    z2 = z3;
                    z3 = left2;
                }
                if (left2 >= z2) {
                    left = i2 + right;
                    break;
                }
                right += f14355m;
                z4 = z2;
                z2 = z3;
                z3 = z4;
            }
            left2 = z2;
            left = i2;
            i = i3;
            i3 = left;
            z2 = true;
        } else {
            int i4 = this.aO;
            left = (i2 + childCount) + f14346d;
            int i5 = childCount + f14346d;
            i = f14354l;
            while (i5 >= 0) {
                int i6;
                View childAt2 = getChildAt(i5);
                i3 = childAt2.getLeft();
                int right2 = childAt2.getRight();
                if (i5 != childCount + f14346d) {
                    i6 = right;
                    right = i;
                    i = i6;
                } else if (i2 + childCount < i4 || right2 > right) {
                    i = right - getHorizontalFadingEdgeLength();
                    right = i3;
                } else {
                    i = right;
                    right = i3;
                }
                if (right2 <= i) {
                    i = i3;
                    i3 = i2 + i5;
                    z2 = false;
                    break;
                }
                i5 += f14346d;
                i6 = i;
                i = right;
                right = i6;
            }
            i3 = left;
            z2 = false;
        }
        this.ah = f14346d;
        removeCallbacks(this.aY);
        if (this.ac != null) {
            this.ac.m16384a();
        }
        this.f14384Z = f14346d;
        m16087J();
        this.aw = i;
        left = m16070b(i3, z2);
        if (left < i2 || left > getLastVisiblePosition()) {
            left = f14346d;
        } else {
            this.f14392z = f14358p;
            m16159m();
            setSelectionInt(left);
            m16137c();
        }
        m16135b((int) f14354l);
        if (left < 0) {
            z = false;
        }
        return z;
    }

    public void sendAccessibilityEvent(int i) {
        if (i == Opcodes.ACC_SYNTHETIC) {
            int firstVisiblePosition = getFirstVisiblePosition();
            int lastVisiblePosition = getLastVisiblePosition();
            if (this.bH != firstVisiblePosition || this.bI != lastVisiblePosition) {
                this.bH = firstVisiblePosition;
                this.bI = lastVisiblePosition;
            } else {
                return;
            }
        }
        super.sendAccessibilityEvent(i);
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter != null) {
            this.f14363C = this.f14362B.hasStableIds();
            if (this.f14386t != 0 && this.f14363C && this.f14391y == null) {
                this.f14391y = new LongSparseArray();
            }
        }
        if (this.f14390x != null) {
            this.f14390x.clear();
        }
        if (this.f14391y != null) {
            this.f14391y.clear();
        }
    }

    public void setCacheColorHint(int i) {
        if (i != this.bo) {
            this.bo = i;
            int childCount = getChildCount();
            for (int i2 = f14354l; i2 < childCount; i2 += f14355m) {
                getChildAt(i2).setDrawingCacheBackgroundColor(i);
            }
            this.f14368H.m16406f(i);
        }
    }

    @TargetApi(11)
    public void setChoiceMode(int i) {
        this.f14386t = i;
        if (VERSION.SDK_INT >= 11 && this.f14387u != null) {
            if (VERSION.SDK_INT >= 11) {
                ((ActionMode) this.f14387u).finish();
            }
            this.f14387u = null;
        }
        if (this.f14386t != 0) {
            if (this.f14390x == null) {
                this.f14390x = new SparseArrayCompat();
            }
            if (this.f14391y == null && this.f14362B != null && this.f14362B.hasStableIds()) {
                this.f14391y = new LongSparseArray();
            }
            if (VERSION.SDK_INT >= 11 && this.f14386t == f14357o) {
                m16121a();
                setLongClickable(true);
            }
        }
    }

    public void setDrawSelectorOnTop(boolean z) {
        this.f14364D = z;
    }

    public void setFriction(float f) {
        if (this.aY == null) {
            this.aY = new C2828g(this);
        }
        this.aY.f14540b.m16325b(f);
    }

    @TargetApi(11)
    public void setMultiChoiceModeListener(C2812a c2812a) {
        if (VERSION.SDK_INT >= 11) {
            if (this.f14388v == null) {
                this.f14388v = new C2813b(this);
            }
            ((C2813b) this.f14388v).m16006a(c2812a);
            return;
        }
        Log.e(aW, "setMultiChoiceModeListener not supported for this version of Android");
    }

    public void setOnScrollListener(C2831j c2831j) {
        this.aZ = c2831j;
        m16137c();
    }

    public void setOverScrollMode(int i) {
        if (i == f14356n) {
            this.by = null;
            this.bz = null;
        } else if (this.by == null) {
            Context context = getContext();
            this.by = new ab(context, f14355m);
            this.bz = new ab(context, f14355m);
        }
        super.setOverScrollMode(i);
    }

    public void setRecyclerListener(C2838q c2838q) {
        this.f14368H.f14574b = c2838q;
    }

    public void setScrollingCacheEnabled(boolean z) {
        if (this.af && !z) {
            m16087J();
        }
        this.af = z;
    }

    public abstract void setSelectionInt(int i);

    public void setSelector(int i) {
        setSelector(getResources().getDrawable(i));
    }

    public void setSelector(Drawable drawable) {
        if (this.f14365E != null) {
            this.f14365E.setCallback(null);
            unscheduleDrawable(this.f14365E);
        }
        this.f14365E = drawable;
        Rect rect = new Rect();
        drawable.getPadding(rect);
        this.f14369I = rect.left;
        this.f14370J = rect.top;
        this.f14371K = rect.right;
        this.f14372L = rect.bottom;
        drawable.setCallback(this);
        m16159m();
    }

    public void setSmoothScrollbarEnabled(boolean z) {
        this.ba = z;
    }

    public void setStackFromRight(boolean z) {
        if (this.ae != z) {
            this.ae = z;
            m16148f();
        }
    }

    public void setTranscriptMode(int i) {
        this.bn = i;
    }

    public void setVelocityScale(float f) {
        this.bv = f;
    }

    public boolean showContextMenuForChild(View view) {
        boolean z = false;
        int a = m16068a(view);
        if (a < 0) {
            return false;
        }
        long itemId = this.f14362B.getItemId(a);
        if (this.aI != null) {
            z = this.aI.m16412a(this, view, a, itemId);
        }
        if (z) {
            return z;
        }
        this.bc = m16134b(getChildAt(a - this.av), a, itemId);
        return super.showContextMenuForChild(view);
    }

    void m16166t() {
        this.f14390x.clear();
        int i = f14354l;
        boolean z = false;
        while (i < this.f14391y.size()) {
            int max;
            boolean z2;
            long keyAt = this.f14391y.keyAt(i);
            int intValue = ((Integer) this.f14391y.valueAt(i)).intValue();
            if (keyAt != this.f14362B.getItemId(intValue)) {
                boolean z3;
                int min = Math.min(intValue + bd, this.aO);
                for (max = Math.max(f14354l, intValue - 20); max < min; max += f14355m) {
                    if (keyAt == this.f14362B.getItemId(max)) {
                        this.f14390x.put(max, Boolean.valueOf(true));
                        this.f14391y.setValueAt(i, Integer.valueOf(max));
                        z3 = true;
                        break;
                    }
                }
                z3 = false;
                if (!z3) {
                    this.f14391y.delete(keyAt);
                    max = i + f14346d;
                    this.f14389w += f14346d;
                    if (!(VERSION.SDK_INT <= 11 || this.f14387u == null || this.f14388v == null)) {
                        ((C2813b) this.f14388v).m16005a((ActionMode) this.f14387u, intValue, keyAt, false);
                    }
                    i = max;
                    z = true;
                }
                max = i;
                z2 = z;
            } else {
                this.f14390x.put(intValue, Boolean.valueOf(true));
                max = i;
                z2 = z;
            }
            z = z2;
            i = max + f14355m;
        }
        if (z && this.f14387u != null && VERSION.SDK_INT > 11) {
            ((ActionMode) this.f14387u).invalidate();
        }
    }

    protected void m16167u() {
        int i = this.aO;
        int i2 = this.bJ;
        this.bJ = this.aO;
        if (!(this.f14386t == 0 || this.f14362B == null || !this.f14362B.hasStableIds())) {
            m16166t();
        }
        this.f14368H.m16401c();
        if (i > 0) {
            int width;
            int bottom;
            if (this.aA) {
                this.aA = false;
                this.bK = null;
                if (this.bn == f14356n) {
                    this.f14392z = f14357o;
                    return;
                }
                if (this.bn == f14355m) {
                    if (this.bD) {
                        this.bD = false;
                        this.f14392z = f14357o;
                        return;
                    }
                    int childCount = getChildCount();
                    width = getWidth() - getPaddingRight();
                    View childAt = getChildAt(childCount + f14346d);
                    bottom = childAt != null ? childAt.getBottom() : width;
                    if (childCount + this.av < i2 || bottom > width) {
                        awakenScrollBars();
                    } else {
                        this.f14392z = f14357o;
                        return;
                    }
                }
                switch (this.aB) {
                    case f14354l /*0*/:
                        if (isInTouchMode()) {
                            this.f14392z = f14359q;
                            this.ax = Math.min(Math.max(f14354l, this.ax), i + f14346d);
                            return;
                        }
                        bottom = m16078z();
                        if (bottom >= 0 && m16070b(bottom, true) == bottom) {
                            this.ax = bottom;
                            if (this.az == ((long) getWidth())) {
                                this.f14392z = f14359q;
                            } else {
                                this.f14392z = f14356n;
                            }
                            setNextSelectedPositionInt(bottom);
                            return;
                        }
                    case f14355m /*1*/:
                        this.f14392z = f14359q;
                        this.ax = Math.min(Math.max(f14354l, this.ax), i + f14346d);
                        return;
                }
            }
            if (!isInTouchMode()) {
                bottom = getSelectedItemPosition();
                if (bottom >= i) {
                    bottom = i + f14346d;
                }
                if (bottom < 0) {
                    bottom = f14354l;
                }
                width = m16070b(bottom, true);
                if (width >= 0) {
                    setNextSelectedPositionInt(width);
                    return;
                }
                bottom = m16070b(bottom, false);
                if (bottom >= 0) {
                    setNextSelectedPositionInt(bottom);
                    return;
                }
            } else if (this.ah >= 0) {
                return;
            }
        }
        this.f14392z = this.ae ? f14357o : f14355m;
        this.aM = f14346d;
        this.aN = Long.MIN_VALUE;
        this.aK = f14346d;
        this.aL = Long.MIN_VALUE;
        this.aA = false;
        this.bK = null;
        this.f14366F = f14346d;
        m16077y();
    }

    public boolean verifyDrawable(Drawable drawable) {
        return this.f14365E == drawable || super.verifyDrawable(drawable);
    }
}
