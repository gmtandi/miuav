package it.sephiroth.android.library.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ArrayAdapter;
import android.widget.Checkable;
import android.widget.ListAdapter;
import android.widget.RemoteViews.RemoteView;
import com.fimi.kernel.p084e.C1186y;
import com.tencent.mm.sdk.platformtools.Util;
import it.sephiroth.android.library.C2811R;
import it.sephiroth.android.library.widget.AbsHListView.LayoutParams;
import java.util.ArrayList;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

@RemoteView
public class HListView extends AbsHListView {
    private static final float aW = 0.33f;
    private static final int aX = 2;
    private static final String aY = "HListView";
    static final int bb = -1;
    private ArrayList<ap> aZ;
    private ArrayList<ap> ba;
    Drawable bc;
    int bd;
    int be;
    Drawable bf;
    Drawable bg;
    private boolean bh;
    private boolean bi;
    private boolean bj;
    private boolean bk;
    private boolean bl;
    private boolean bm;
    private final Rect bn;
    private Paint bo;
    private final ao bp;
    private aq bq;

    public HListView(Context context) {
        this(context, null);
    }

    public HListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C2811R.attr.hlv_listViewStyle);
    }

    public HListView(Context context, AttributeSet attributeSet, int i) {
        Object[] textArray;
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        int dimensionPixelSize;
        boolean z;
        int i2 = bb;
        boolean z2 = true;
        super(context, attributeSet, i);
        this.aZ = new ArrayList();
        this.ba = new ArrayList();
        this.bl = true;
        this.bm = false;
        this.bn = new Rect();
        this.bp = new ao();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2811R.styleable.HListView, i, 0);
        if (obtainStyledAttributes != null) {
            textArray = obtainStyledAttributes.getTextArray(0);
            drawable = obtainStyledAttributes.getDrawable(1);
            drawable2 = obtainStyledAttributes.getDrawable(5);
            drawable3 = obtainStyledAttributes.getDrawable(6);
            dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(aX, 0);
            z = obtainStyledAttributes.getBoolean(3, true);
            z2 = obtainStyledAttributes.getBoolean(4, true);
            i2 = obtainStyledAttributes.getInteger(7, bb);
            obtainStyledAttributes.recycle();
        } else {
            drawable3 = null;
            drawable2 = null;
            drawable = null;
            textArray = null;
            dimensionPixelSize = 0;
            z = true;
        }
        if (textArray != null) {
            setAdapter(new ArrayAdapter(context, 17367043, textArray));
        }
        if (drawable != null) {
            setDivider(drawable);
        }
        if (drawable2 != null) {
            setOverscrollHeader(drawable2);
        }
        if (drawable3 != null) {
            setOverscrollFooter(drawable3);
        }
        if (dimensionPixelSize != 0) {
            setDividerWidth(dimensionPixelSize);
        }
        this.bj = z;
        this.bk = z2;
        this.be = i2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m16185D() {
        /*
        r5 = this;
        r0 = 0;
        r2 = r5.getChildCount();
        if (r2 <= 0) goto L_0x0027;
    L_0x0007:
        r1 = r5.ae;
        if (r1 != 0) goto L_0x0028;
    L_0x000b:
        r1 = r5.getChildAt(r0);
        r1 = r1.getLeft();
        r2 = r5.M;
        r2 = r2.left;
        r1 = r1 - r2;
        r2 = r5.av;
        if (r2 == 0) goto L_0x001f;
    L_0x001c:
        r2 = r5.bd;
        r1 = r1 - r2;
    L_0x001f:
        if (r1 >= 0) goto L_0x0048;
    L_0x0021:
        if (r0 == 0) goto L_0x0027;
    L_0x0023:
        r0 = -r0;
        r5.m16144e(r0);
    L_0x0027:
        return;
    L_0x0028:
        r1 = r2 + -1;
        r1 = r5.getChildAt(r1);
        r1 = r1.getRight();
        r3 = r5.getWidth();
        r4 = r5.M;
        r4 = r4.right;
        r3 = r3 - r4;
        r1 = r1 - r3;
        r3 = r5.av;
        r2 = r2 + r3;
        r3 = r5.aO;
        if (r2 >= r3) goto L_0x0046;
    L_0x0043:
        r2 = r5.bd;
        r1 = r1 + r2;
    L_0x0046:
        if (r1 > 0) goto L_0x0021;
    L_0x0048:
        r0 = r1;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sephiroth.android.library.widget.HListView.D():void");
    }

    private boolean m16186E() {
        return this.av > 0 || getChildAt(0).getLeft() > getScrollX() + this.M.left;
    }

    private boolean m16187F() {
        int childCount = getChildCount();
        return (childCount + this.av) + bb < this.aO + bb || getChildAt(childCount + bb).getRight() < (getScrollX() + getWidth()) - this.M.right;
    }

    private int m16188a(int i, View view, int i2) {
        view.getDrawingRect(this.bn);
        offsetDescendantRectToMyCoords(view, this.bn);
        int i3;
        if (i != 33) {
            int width = getWidth() - this.M.right;
            if (this.bn.bottom <= width) {
                return 0;
            }
            i3 = this.bn.right - width;
            return i2 < this.aO + bb ? i3 + getArrowScrollPreviewLength() : i3;
        } else if (this.bn.left >= this.M.left) {
            return 0;
        } else {
            i3 = this.M.left - this.bn.left;
            return i2 > 0 ? i3 + getArrowScrollPreviewLength() : i3;
        }
    }

    private View m16189a(int i, int i2, boolean z, int i3, boolean z2) {
        View c;
        if (!this.aJ) {
            c = this.H.m16400c(i);
            if (c != null) {
                m16194a(c, i, i2, z, i3, z2, true);
                return c;
            }
        }
        c = m16119a(i, this.al);
        m16194a(c, i, i2, z, i3, z2, this.al[0]);
        return c;
    }

    private View m16190a(View view, View view2, int i, int i2, int i3) {
        View a;
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        int i4 = this.aM;
        int d = m16204d(i2, horizontalFadingEdgeLength, i4);
        int c = m16202c(i2, horizontalFadingEdgeLength, i4);
        if (i > 0) {
            View a2 = m16189a(i4 + bb, view.getLeft(), true, this.M.top, false);
            int i5 = this.bd;
            a = m16189a(i4, a2.getRight() + i5, true, this.M.top, true);
            if (a.getRight() > c) {
                int min = Math.min(Math.min(a.getLeft() - d, a.getRight() - c), (i3 - i2) / aX);
                a2.offsetLeftAndRight(-min);
                a.offsetLeftAndRight(-min);
            }
            if (this.ae) {
                m16207i(this.aM + 1, a.getRight() + i5);
                m16185D();
                m16212k(this.aM - 2, a.getLeft() - i5);
            } else {
                m16212k(this.aM - 2, a.getLeft() - i5);
                m16185D();
                m16207i(this.aM + 1, a.getRight() + i5);
            }
        } else if (i < 0) {
            if (view2 != null) {
                a = m16189a(i4, view2.getLeft(), true, this.M.top, true);
            } else {
                a = m16189a(i4, view.getLeft(), false, this.M.top, true);
            }
            if (a.getLeft() < d) {
                a.offsetLeftAndRight(Math.min(Math.min(d - a.getLeft(), c - a.getRight()), (i3 - i2) / aX));
            }
            m16191a(a, i4);
        } else {
            int left = view.getLeft();
            a = m16189a(i4, left, true, this.M.top, true);
            if (left < i2 && a.getRight() < i2 + 20) {
                a.offsetLeftAndRight(i2 - a.getLeft());
            }
            m16191a(a, i4);
        }
        return a;
    }

    private void m16191a(View view, int i) {
        int i2 = this.bd;
        if (this.ae) {
            m16207i(i + 1, view.getRight() + i2);
            m16185D();
            m16212k(i + bb, view.getLeft() - i2);
            return;
        }
        m16212k(i + bb, view.getLeft() - i2);
        m16185D();
        m16207i(i + 1, i2 + view.getRight());
    }

    private void m16192a(View view, int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = (LayoutParams) generateDefaultLayoutParams();
            view.setLayoutParams(layoutParams);
        }
        layoutParams.f14323a = this.B.getItemViewType(i);
        layoutParams.f14325c = true;
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, this.M.top + this.M.bottom, layoutParams.height);
        int i3 = layoutParams.width;
        view.measure(i3 > 0 ? MeasureSpec.makeMeasureSpec(i3, 1073741824) : MeasureSpec.makeMeasureSpec(0, 0), childMeasureSpec);
    }

    private void m16193a(View view, int i, int i2, boolean z) {
        boolean z2 = true;
        if (i2 == bb) {
            throw new IllegalArgumentException("newSelectedPosition needs to be valid");
        }
        View childAt;
        int i3;
        boolean z3;
        int i4 = this.aM - this.av;
        int i5 = i2 - this.av;
        if (i == 33) {
            childAt = getChildAt(i5);
            i3 = i5;
            i5 = i4;
            z3 = true;
        } else {
            childAt = view;
            view = getChildAt(i5);
            i3 = i4;
            z3 = false;
        }
        int childCount = getChildCount();
        if (childAt != null) {
            boolean z4 = !z && z3;
            childAt.setSelected(z4);
            m16200b(childAt, i3, childCount);
        }
        if (view != null) {
            if (z || z3) {
                z2 = false;
            }
            view.setSelected(z2);
            m16200b(view, i5, childCount);
        }
    }

    @TargetApi(11)
    private void m16194a(View view, int i, int i2, boolean z, int i3, boolean z2, boolean z3) {
        int childMeasureSpec;
        boolean z4 = z2 && m16157k();
        Object obj = z4 != view.isSelected() ? 1 : null;
        int i4 = this.Z;
        boolean z5 = i4 > 0 && i4 < 3 && this.S == i;
        Object obj2 = z5 != view.isPressed() ? 1 : null;
        Object obj3 = (z3 && obj == null && !view.isLayoutRequested()) ? null : 1;
        ViewGroup.LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = layoutParams == null ? (LayoutParams) generateDefaultLayoutParams() : layoutParams;
        layoutParams2.f14323a = this.B.getItemViewType(i);
        if ((!z3 || layoutParams2.f14325c) && !(layoutParams2.f14324b && layoutParams2.f14323a == -2)) {
            layoutParams2.f14325c = false;
            if (layoutParams2.f14323a == -2) {
                layoutParams2.f14324b = true;
            }
            addViewInLayout(view, z ? bb : 0, layoutParams2, true);
        } else {
            attachViewToParent(view, z ? bb : 0, layoutParams2);
        }
        if (obj != null) {
            view.setSelected(z4);
        }
        if (obj2 != null) {
            view.setPressed(z5);
        }
        if (!(this.t == 0 || this.x == null)) {
            if (view instanceof Checkable) {
                ((Checkable) view).setChecked(((Boolean) this.x.get(i, Boolean.valueOf(false))).booleanValue());
            } else if (VERSION.SDK_INT >= 11) {
                view.setActivated(((Boolean) this.x.get(i, Boolean.valueOf(false))).booleanValue());
            }
        }
        if (obj3 != null) {
            childMeasureSpec = ViewGroup.getChildMeasureSpec(this.N, this.M.top + this.M.bottom, layoutParams2.height);
            i4 = layoutParams2.width;
            view.measure(i4 > 0 ? MeasureSpec.makeMeasureSpec(i4, 1073741824) : MeasureSpec.makeMeasureSpec(0, 0), childMeasureSpec);
        } else {
            cleanupLayoutState(view);
        }
        i4 = view.getMeasuredWidth();
        childMeasureSpec = view.getMeasuredHeight();
        if (!z) {
            i2 -= i4;
        }
        if (obj3 != null) {
            view.layout(i2, i3, i4 + i2, childMeasureSpec + i3);
        } else {
            view.offsetLeftAndRight(i2 - view.getLeft());
            view.offsetTopAndBottom(i3 - view.getTop());
        }
        if (this.Q && !view.isDrawingCacheEnabled()) {
            view.setDrawingCacheEnabled(true);
        }
        if (VERSION.SDK_INT >= 11 && z3 && ((LayoutParams) view.getLayoutParams()).f14326d != i) {
            view.jumpDrawablesToCurrentState();
        }
    }

    private void m16195a(View view, ArrayList<ap> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (((ap) arrayList.get(i)).f14479a == view) {
                arrayList.remove(i);
                return;
            }
        }
    }

    private void m16196a(ArrayList<ap> arrayList) {
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                LayoutParams layoutParams = (LayoutParams) ((ap) arrayList.get(i)).f14479a.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.f14324b = false;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.TargetApi(11)
    private boolean m16197a(int r9, int r10, android.view.KeyEvent r11) {
        /*
        r8 = this;
        r7 = 2;
        r6 = 130; // 0x82 float:1.82E-43 double:6.4E-322;
        r5 = 33;
        r1 = 0;
        r2 = 1;
        r0 = r8.B;
        if (r0 == 0) goto L_0x000f;
    L_0x000b:
        r0 = r8.ao;
        if (r0 != 0) goto L_0x0010;
    L_0x000f:
        return r1;
    L_0x0010:
        r0 = r8.aJ;
        if (r0 == 0) goto L_0x0017;
    L_0x0014:
        r8.m16244h();
    L_0x0017:
        r0 = android.os.Build.VERSION.SDK_INT;
        r3 = 11;
        if (r0 < r3) goto L_0x000f;
    L_0x001d:
        r4 = r11.getAction();
        if (r4 == r2) goto L_0x0026;
    L_0x0023:
        switch(r9) {
            case 19: goto L_0x002b;
            case 20: goto L_0x005b;
            case 21: goto L_0x008b;
            case 22: goto L_0x0098;
            case 23: goto L_0x00a5;
            case 62: goto L_0x00c3;
            case 66: goto L_0x00a5;
            case 92: goto L_0x00eb;
            case 93: goto L_0x011b;
            case 122: goto L_0x014b;
            case 123: goto L_0x0163;
            default: goto L_0x0026;
        };
    L_0x0026:
        r0 = r1;
    L_0x0027:
        if (r0 == 0) goto L_0x017b;
    L_0x0029:
        r1 = r2;
        goto L_0x000f;
    L_0x002b:
        r0 = r11.hasNoModifiers();
        if (r0 == 0) goto L_0x0045;
    L_0x0031:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x0027;
    L_0x0037:
        r3 = r10;
    L_0x0038:
        r10 = r3 + -1;
        if (r3 <= 0) goto L_0x0027;
    L_0x003c:
        r3 = r8.m16248q(r5);
        if (r3 == 0) goto L_0x0027;
    L_0x0042:
        r0 = r2;
        r3 = r10;
        goto L_0x0038;
    L_0x0045:
        r0 = r11.hasModifiers(r7);
        if (r0 == 0) goto L_0x0026;
    L_0x004b:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x0057;
    L_0x0051:
        r0 = r8.m16247p(r5);
        if (r0 == 0) goto L_0x0059;
    L_0x0057:
        r0 = r2;
        goto L_0x0027;
    L_0x0059:
        r0 = r1;
        goto L_0x0027;
    L_0x005b:
        r0 = r11.hasNoModifiers();
        if (r0 == 0) goto L_0x0075;
    L_0x0061:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x0027;
    L_0x0067:
        r3 = r10;
    L_0x0068:
        r10 = r3 + -1;
        if (r3 <= 0) goto L_0x0027;
    L_0x006c:
        r3 = r8.m16248q(r6);
        if (r3 == 0) goto L_0x0027;
    L_0x0072:
        r0 = r2;
        r3 = r10;
        goto L_0x0068;
    L_0x0075:
        r0 = r11.hasModifiers(r7);
        if (r0 == 0) goto L_0x0026;
    L_0x007b:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x0087;
    L_0x0081:
        r0 = r8.m16247p(r6);
        if (r0 == 0) goto L_0x0089;
    L_0x0087:
        r0 = r2;
        goto L_0x0027;
    L_0x0089:
        r0 = r1;
        goto L_0x0027;
    L_0x008b:
        r0 = r11.hasNoModifiers();
        if (r0 == 0) goto L_0x0026;
    L_0x0091:
        r0 = 17;
        r0 = r8.m16218m(r0);
        goto L_0x0027;
    L_0x0098:
        r0 = r11.hasNoModifiers();
        if (r0 == 0) goto L_0x0026;
    L_0x009e:
        r0 = 66;
        r0 = r8.m16218m(r0);
        goto L_0x0027;
    L_0x00a5:
        r0 = r11.hasNoModifiers();
        if (r0 == 0) goto L_0x0026;
    L_0x00ab:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x0027;
    L_0x00b1:
        r3 = r11.getRepeatCount();
        if (r3 != 0) goto L_0x0027;
    L_0x00b7:
        r3 = r8.getChildCount();
        if (r3 <= 0) goto L_0x0027;
    L_0x00bd:
        r8.m16158l();
        r0 = r2;
        goto L_0x0027;
    L_0x00c3:
        r0 = r11.hasNoModifiers();
        if (r0 == 0) goto L_0x00d8;
    L_0x00c9:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x00d5;
    L_0x00cf:
        r0 = r8.m16246o(r6);
        if (r0 == 0) goto L_0x00d5;
    L_0x00d5:
        r0 = r2;
        goto L_0x0027;
    L_0x00d8:
        r0 = r11.hasModifiers(r2);
        if (r0 == 0) goto L_0x00d5;
    L_0x00de:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x00d5;
    L_0x00e4:
        r0 = r8.m16246o(r5);
        if (r0 == 0) goto L_0x00d5;
    L_0x00ea:
        goto L_0x00d5;
    L_0x00eb:
        r0 = r11.hasNoModifiers();
        if (r0 == 0) goto L_0x0103;
    L_0x00f1:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x00fd;
    L_0x00f7:
        r0 = r8.m16246o(r5);
        if (r0 == 0) goto L_0x0100;
    L_0x00fd:
        r0 = r2;
        goto L_0x0027;
    L_0x0100:
        r0 = r1;
        goto L_0x0027;
    L_0x0103:
        r0 = r11.hasModifiers(r7);
        if (r0 == 0) goto L_0x0026;
    L_0x0109:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x0115;
    L_0x010f:
        r0 = r8.m16247p(r5);
        if (r0 == 0) goto L_0x0118;
    L_0x0115:
        r0 = r2;
        goto L_0x0027;
    L_0x0118:
        r0 = r1;
        goto L_0x0027;
    L_0x011b:
        r0 = r11.hasNoModifiers();
        if (r0 == 0) goto L_0x0133;
    L_0x0121:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x012d;
    L_0x0127:
        r0 = r8.m16246o(r6);
        if (r0 == 0) goto L_0x0130;
    L_0x012d:
        r0 = r2;
        goto L_0x0027;
    L_0x0130:
        r0 = r1;
        goto L_0x0027;
    L_0x0133:
        r0 = r11.hasModifiers(r7);
        if (r0 == 0) goto L_0x0026;
    L_0x0139:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x0145;
    L_0x013f:
        r0 = r8.m16247p(r6);
        if (r0 == 0) goto L_0x0148;
    L_0x0145:
        r0 = r2;
        goto L_0x0027;
    L_0x0148:
        r0 = r1;
        goto L_0x0027;
    L_0x014b:
        r0 = r11.hasNoModifiers();
        if (r0 == 0) goto L_0x0026;
    L_0x0151:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x015d;
    L_0x0157:
        r0 = r8.m16247p(r5);
        if (r0 == 0) goto L_0x0160;
    L_0x015d:
        r0 = r2;
        goto L_0x0027;
    L_0x0160:
        r0 = r1;
        goto L_0x0027;
    L_0x0163:
        r0 = r11.hasNoModifiers();
        if (r0 == 0) goto L_0x0026;
    L_0x0169:
        r0 = r8.m16164r();
        if (r0 != 0) goto L_0x0175;
    L_0x016f:
        r0 = r8.m16247p(r6);
        if (r0 == 0) goto L_0x0178;
    L_0x0175:
        r0 = r2;
        goto L_0x0027;
    L_0x0178:
        r0 = r1;
        goto L_0x0027;
    L_0x017b:
        switch(r4) {
            case 0: goto L_0x0180;
            case 1: goto L_0x0186;
            case 2: goto L_0x018c;
            default: goto L_0x017e;
        };
    L_0x017e:
        goto L_0x000f;
    L_0x0180:
        r1 = super.onKeyDown(r9, r11);
        goto L_0x000f;
    L_0x0186:
        r1 = super.onKeyUp(r9, r11);
        goto L_0x000f;
    L_0x018c:
        r1 = super.onKeyMultiple(r9, r10, r11);
        goto L_0x000f;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sephiroth.android.library.widget.HListView.a(int, int, android.view.KeyEvent):boolean");
    }

    private View m16198b(int i, int i2, int i3) {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        int i4 = this.aM;
        int d = m16204d(i2, horizontalFadingEdgeLength, i4);
        int c = m16202c(i3, horizontalFadingEdgeLength, i4);
        View a = m16189a(i4, i, true, this.M.top, true);
        if (a.getRight() > c) {
            a.offsetLeftAndRight(-Math.min(a.getLeft() - d, a.getRight() - c));
        } else if (a.getLeft() < d) {
            a.offsetLeftAndRight(Math.min(d - a.getLeft(), c - a.getRight()));
        }
        m16191a(a, i4);
        if (this.ae) {
            m16216l(getChildCount());
        } else {
            m16213k(getChildCount());
        }
        return a;
    }

    private View m16199b(View view, int i) {
        int i2 = i + bb;
        View a = m16119a(i2, this.al);
        m16194a(a, i2, view.getLeft() - this.bd, false, this.M.top, false, this.al[0]);
        return a;
    }

    private void m16200b(View view, int i, int i2) {
        int width = view.getWidth();
        m16208i(view);
        if (view.getMeasuredWidth() != width) {
            m16210j(view);
            int measuredWidth = view.getMeasuredWidth() - width;
            for (width = i + 1; width < i2; width++) {
                getChildAt(width).offsetLeftAndRight(measuredWidth);
            }
        }
    }

    private boolean m16201b(View view, View view2) {
        if (view == view2) {
            return true;
        }
        ViewParent parent = view.getParent();
        boolean z = (parent instanceof ViewGroup) && m16201b((View) parent, view2);
        return z;
    }

    private int m16202c(int i, int i2, int i3) {
        return i3 != this.aO + bb ? i - i2 : i;
    }

    private View m16203c(View view, int i) {
        int i2 = i + 1;
        View a = m16119a(i2, this.al);
        m16194a(a, i2, this.bd + view.getRight(), true, this.M.top, false, this.al[0]);
        return a;
    }

    private int m16204d(int i, int i2, int i3) {
        return i3 > 0 ? i + i2 : i;
    }

    private View m16205g(View view) {
        HListView parent = view.getParent();
        while ((parent instanceof View) && parent != this) {
            View view2 = parent;
            parent = parent.getParent();
            view = view2;
        }
        return !(parent instanceof View) ? null : view;
    }

    private int getArrowScrollPreviewLength() {
        return Math.max(aX, getHorizontalFadingEdgeLength());
    }

    private boolean m16206h(View view) {
        int i;
        ArrayList arrayList = this.aZ;
        int size = arrayList.size();
        for (i = 0; i < size; i++) {
            if (view == ((ap) arrayList.get(i)).f14479a) {
                return true;
            }
        }
        arrayList = this.ba;
        size = arrayList.size();
        for (i = 0; i < size; i++) {
            if (view == ((ap) arrayList.get(i)).f14479a) {
                return true;
            }
        }
        return false;
    }

    private View m16207i(int i, int i2) {
        View view = null;
        int right = getRight() - getLeft();
        int i3 = i2;
        int i4 = i;
        while (i3 < right && i4 < this.aO) {
            boolean z = i4 == this.aM;
            View a = m16189a(i4, i3, true, this.M.top, z);
            i3 = a.getRight() + this.bd;
            if (!z) {
                a = view;
            }
            i4++;
            view = a;
        }
        m16154h(this.av, (this.av + getChildCount()) + bb);
        return view;
    }

    private void m16208i(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, bb);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.N, this.M.top + this.M.bottom, layoutParams.height);
        int i = layoutParams.width;
        view.measure(i > 0 ? MeasureSpec.makeMeasureSpec(i, 1073741824) : MeasureSpec.makeMeasureSpec(0, 0), childMeasureSpec);
    }

    private View m16209j(int i) {
        this.av = Math.min(this.av, this.aM);
        this.av = Math.min(this.av, this.aO + bb);
        if (this.av < 0) {
            this.av = 0;
        }
        return m16207i(this.av, i);
    }

    private void m16210j(View view) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i = this.M.top;
        measuredHeight += i;
        int left = view.getLeft();
        view.layout(left, i, measuredWidth + left, measuredHeight);
    }

    private int m16211k(View view) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (m16201b(view, getChildAt(i))) {
                return i + this.av;
            }
        }
        throw new IllegalArgumentException("newFocus is not a child of any of the children of the list!");
    }

    private View m16212k(int i, int i2) {
        View view = null;
        int i3 = i2;
        int i4 = i;
        while (i3 > 0 && i4 >= 0) {
            boolean z = i4 == this.aM;
            View a = m16189a(i4, i3, false, this.M.top, z);
            i3 = a.getLeft() - this.bd;
            if (!z) {
                a = view;
            }
            i4 += bb;
            view = a;
        }
        this.av = i4 + 1;
        m16154h(this.av, (this.av + getChildCount()) + bb);
        return view;
    }

    private void m16213k(int i) {
        if ((this.av + i) + bb == this.aO + bb && i > 0) {
            int right = ((getRight() - getLeft()) - this.M.right) - getChildAt(i + bb).getRight();
            View childAt = getChildAt(0);
            int left = childAt.getLeft();
            if (right <= 0) {
                return;
            }
            if (this.av > 0 || left < this.M.top) {
                if (this.av == 0) {
                    right = Math.min(right, this.M.top - left);
                }
                m16144e(right);
                if (this.av > 0) {
                    m16212k(this.av + bb, childAt.getLeft() - this.bd);
                    m16185D();
                }
            }
        }
    }

    private int m16214l(View view) {
        view.getDrawingRect(this.bn);
        offsetDescendantRectToMyCoords(view, this.bn);
        int right = (getRight() - getLeft()) - this.M.right;
        return this.bn.right < this.M.left ? this.M.left - this.bn.right : this.bn.left > right ? this.bn.left - right : 0;
    }

    private View m16215l(int i, int i2) {
        int i3 = i2 - i;
        int p = m16162p();
        View a = m16189a(p, i, true, this.M.top, true);
        this.av = p;
        int measuredWidth = a.getMeasuredWidth();
        if (measuredWidth <= i3) {
            a.offsetLeftAndRight((i3 - measuredWidth) / aX);
        }
        m16191a(a, p);
        if (this.ae) {
            m16216l(getChildCount());
        } else {
            m16213k(getChildCount());
        }
        return a;
    }

    private void m16216l(int i) {
        if (this.av == 0 && i > 0) {
            int right = (getRight() - getLeft()) - this.M.right;
            int left = getChildAt(0).getLeft() - this.M.left;
            View childAt = getChildAt(i + bb);
            int right2 = childAt.getRight();
            int i2 = (this.av + i) + bb;
            if (left <= 0) {
                return;
            }
            if (i2 < this.aO + bb || right2 > right) {
                if (i2 == this.aO + bb) {
                    left = Math.min(left, right2 - right);
                }
                m16144e(-left);
                if (i2 < this.aO + bb) {
                    m16207i(i2 + 1, childAt.getRight() + this.bd);
                    m16185D();
                }
            } else if (i2 == this.aO + bb) {
                m16185D();
            }
        }
    }

    private View m16217m(int i, int i2) {
        View i3;
        View k;
        boolean z = i == this.aM;
        View a = m16189a(i, i2, true, this.M.top, z);
        this.av = i;
        int i4 = this.bd;
        if (this.ae) {
            i3 = m16207i(i + 1, a.getRight() + i4);
            m16185D();
            k = m16212k(i + bb, a.getLeft() - i4);
            i4 = getChildCount();
            if (i4 > 0) {
                m16216l(i4);
            }
        } else {
            k = m16212k(i + bb, a.getLeft() - i4);
            m16185D();
            i3 = m16207i(i + 1, i4 + a.getRight());
            i4 = getChildCount();
            if (i4 > 0) {
                m16213k(i4);
            }
        }
        return z ? a : k != null ? k : i3;
    }

    private boolean m16218m(int i) {
        if (i == 17 || i == 66) {
            int childCount = getChildCount();
            if (this.bm && childCount > 0 && this.aM != bb) {
                View selectedView = getSelectedView();
                if (selectedView != null && selectedView.hasFocus() && (selectedView instanceof ViewGroup)) {
                    View findFocus = selectedView.findFocus();
                    selectedView = FocusFinder.getInstance().findNextFocus((ViewGroup) selectedView, findFocus, i);
                    if (selectedView != null) {
                        findFocus.getFocusedRect(this.bn);
                        offsetDescendantRectToMyCoords(findFocus, this.bn);
                        offsetRectIntoDescendantCoords(selectedView, this.bn);
                        if (selectedView.requestFocus(i, this.bn)) {
                            return true;
                        }
                    }
                    selectedView = FocusFinder.getInstance().findNextFocus((ViewGroup) getRootView(), findFocus, i);
                    if (selectedView != null) {
                        return m16201b(selectedView, (View) this);
                    }
                }
            }
            return false;
        }
        throw new IllegalArgumentException("direction must be one of {View.FOCUS_LEFT, View.FOCUS_RIGHT}");
    }

    private int m16219n(int i, int i2) {
        int width = getWidth() - this.M.right;
        int i3 = this.M.left;
        int childCount = getChildCount();
        int i4;
        if (i == Opcodes.IXOR) {
            i4 = childCount + bb;
            if (i2 != bb) {
                i4 = i2 - this.av;
            }
            i3 = this.av + i4;
            View childAt = getChildAt(i4);
            i4 = i3 < this.aO + bb ? width - getArrowScrollPreviewLength() : width;
            if (childAt.getRight() <= i4) {
                return 0;
            }
            if (i2 != bb && i4 - childAt.getLeft() >= getMaxScrollAmount()) {
                return 0;
            }
            i4 = childAt.getRight() - i4;
            if (this.av + childCount == this.aO) {
                i4 = Math.min(i4, getChildAt(childCount + bb).getRight() - width);
            }
            return Math.min(i4, getMaxScrollAmount());
        }
        i4 = i2 != bb ? i2 - this.av : 0;
        width = this.av + i4;
        View childAt2 = getChildAt(i4);
        i4 = width > 0 ? getArrowScrollPreviewLength() + i3 : i3;
        if (childAt2.getLeft() >= i4) {
            return 0;
        }
        if (i2 != bb && childAt2.getRight() - i4 >= getMaxScrollAmount()) {
            return 0;
        }
        i4 -= childAt2.getLeft();
        if (this.av == 0) {
            i4 = Math.min(i4, i3 - getChildAt(0).getLeft());
        }
        return Math.min(i4, getMaxScrollAmount());
    }

    private boolean m16220n(int i) {
        if (getChildCount() <= 0) {
            return false;
        }
        View selectedView;
        int i2;
        View findFocus;
        View selectedView2 = getSelectedView();
        int i3 = this.aM;
        int r = m16221r(i);
        int n = m16219n(i, r);
        ao s = this.bm ? m16222s(i) : null;
        if (s != null) {
            r = s.m16303a();
            n = s.m16305b();
        }
        boolean z = s != null;
        if (r != bb) {
            m16193a(selectedView2, i, r, s != null);
            setSelectedPositionInt(r);
            setNextSelectedPositionInt(r);
            selectedView = getSelectedView();
            if (this.bm && s == null) {
                View focusedChild = getFocusedChild();
                if (focusedChild != null) {
                    focusedChild.clearFocus();
                }
            }
            m16077y();
            z = true;
            i2 = r;
        } else {
            int i4 = i3;
            selectedView = selectedView2;
            i2 = i4;
        }
        if (n > 0) {
            m16223t(i == 33 ? n : -n);
            z = true;
        }
        if (this.bm && s == null && selectedView != null && selectedView.hasFocus()) {
            findFocus = selectedView.findFocus();
            if (!m16201b(findFocus, (View) this) || m16214l(findFocus) > 0) {
                findFocus.clearFocus();
            }
        }
        if (r != bb || selectedView == null || m16201b(selectedView, (View) this)) {
            findFocus = selectedView;
        } else {
            m16161o();
            this.ah = bb;
            findFocus = null;
        }
        if (!z) {
            return false;
        }
        if (findFocus != null) {
            m16124a(i2, findFocus);
            this.ad = findFocus.getLeft();
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        m16137c();
        return true;
    }

    private int m16221r(int i) {
        int i2 = this.av;
        int i3;
        int lastVisiblePosition;
        if (i == Opcodes.IXOR) {
            i3 = this.aM != bb ? this.aM + 1 : i2;
            if (i3 >= this.B.getCount()) {
                return bb;
            }
            if (i3 < i2) {
                i3 = i2;
            }
            lastVisiblePosition = getLastVisiblePosition();
            ListAdapter adapter = getAdapter();
            while (i3 <= lastVisiblePosition) {
                if (adapter.isEnabled(i3) && getChildAt(i3 - i2).getVisibility() == 0) {
                    return i3;
                }
                i3++;
            }
        } else {
            i3 = (getChildCount() + i2) + bb;
            lastVisiblePosition = this.aM != bb ? this.aM + bb : (getChildCount() + i2) + bb;
            if (lastVisiblePosition < 0 || lastVisiblePosition >= this.B.getCount()) {
                return bb;
            }
            if (lastVisiblePosition <= i3) {
                i3 = lastVisiblePosition;
            }
            ListAdapter adapter2 = getAdapter();
            while (i3 >= i2) {
                if (adapter2.isEnabled(i3) && getChildAt(i3 - i2).getVisibility() == 0) {
                    return i3;
                }
                i3 += bb;
            }
        }
        return bb;
    }

    private ao m16222s(int i) {
        View findNextFocusFromRect;
        int i2 = 1;
        View selectedView = getSelectedView();
        if (selectedView == null || !selectedView.hasFocus()) {
            if (i == Opcodes.IXOR) {
                if (this.av <= 0) {
                    i2 = 0;
                }
                i2 = (i2 != 0 ? getArrowScrollPreviewLength() : 0) + this.M.left;
                if (selectedView != null && selectedView.getLeft() > i2) {
                    i2 = selectedView.getLeft();
                }
                this.bn.set(i2, 0, i2, 0);
            } else {
                if ((this.av + getChildCount()) + bb >= this.aO) {
                    i2 = 0;
                }
                i2 = (getWidth() - this.M.right) - (i2 != 0 ? getArrowScrollPreviewLength() : 0);
                if (selectedView != null && selectedView.getRight() < i2) {
                    i2 = selectedView.getRight();
                }
                this.bn.set(i2, 0, i2, 0);
            }
            findNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect(this, this.bn, i);
        } else {
            findNextFocusFromRect = FocusFinder.getInstance().findNextFocus(this, selectedView.findFocus(), i);
        }
        if (findNextFocusFromRect != null) {
            int r;
            int k = m16211k(findNextFocusFromRect);
            if (!(this.aM == bb || k == this.aM)) {
                r = m16221r(i);
                if (r != bb && ((i == Opcodes.IXOR && r < k) || (i == 33 && r > k))) {
                    return null;
                }
            }
            r = m16188a(i, findNextFocusFromRect, k);
            int maxScrollAmount = getMaxScrollAmount();
            if (r < maxScrollAmount) {
                findNextFocusFromRect.requestFocus(i);
                this.bp.m16304a(k, r);
                return this.bp;
            } else if (m16214l(findNextFocusFromRect) < maxScrollAmount) {
                findNextFocusFromRect.requestFocus(i);
                this.bp.m16304a(k, maxScrollAmount);
                return this.bp;
            }
        }
        return null;
    }

    private void m16223t(int i) {
        m16144e(i);
        int width = getWidth() - this.M.right;
        int i2 = this.M.left;
        C2837p c2837p = this.H;
        int childCount;
        View childAt;
        View childAt2;
        if (i < 0) {
            childCount = getChildCount();
            childAt = getChildAt(childCount + bb);
            while (childAt.getRight() < width) {
                int i3 = (this.av + childCount) + bb;
                if (i3 >= this.aO + bb) {
                    break;
                }
                childAt = m16203c(childAt, i3);
                childCount++;
            }
            if (childAt.getBottom() < width) {
                m16144e(width - childAt.getRight());
            }
            childAt2 = getChildAt(0);
            while (childAt2.getRight() < i2) {
                if (c2837p.m16399b(((LayoutParams) childAt2.getLayoutParams()).f14323a)) {
                    detachViewFromParent(childAt2);
                    c2837p.m16396a(childAt2, this.av);
                } else {
                    removeViewInLayout(childAt2);
                }
                childAt = getChildAt(0);
                this.av++;
                childAt2 = childAt;
            }
            return;
        }
        childAt = getChildAt(0);
        while (childAt.getLeft() > i2 && this.av > 0) {
            childAt = m16199b(childAt, this.av);
            this.av += bb;
        }
        if (childAt.getLeft() > i2) {
            m16144e(i2 - childAt.getLeft());
        }
        childCount = getChildCount() + bb;
        i2 = childCount;
        childAt2 = getChildAt(childCount);
        while (childAt2.getLeft() > width) {
            if (c2837p.m16399b(((LayoutParams) childAt2.getLayoutParams()).f14323a)) {
                detachViewFromParent(childAt2);
                c2837p.m16396a(childAt2, this.av + i2);
            } else {
                removeViewInLayout(childAt2);
            }
            childCount = i2 + bb;
            i2 = childCount;
            childAt2 = getChildAt(childCount);
        }
    }

    @ExportedProperty(category = "list")
    protected boolean m16224B() {
        return true;
    }

    public void m16225C() {
        int size = this.aZ.size();
        if (size > 0) {
            this.aK = 0;
        } else if (this.B != null) {
            setSelection(size);
        } else {
            this.aK = size;
            this.z = aX;
        }
    }

    final int m16226a(int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        ListAdapter listAdapter = this.B;
        if (listAdapter == null) {
            return this.M.left + this.M.right;
        }
        int i7 = this.M.left + this.M.right;
        int i8 = (this.bd <= 0 || this.bc == null) ? 0 : this.bd;
        if (i3 == bb) {
            i3 = listAdapter.getCount() + bb;
        }
        C2837p c2837p = this.H;
        boolean B = m16224B();
        boolean[] zArr = this.al;
        while (i2 <= i3) {
            View a = m16119a(i2, zArr);
            m16192a(a, i2, i);
            int i9 = i2 > 0 ? i7 + i8 : i7;
            if (B && c2837p.m16399b(((LayoutParams) a.getLayoutParams()).f14323a)) {
                c2837p.m16396a(a, (int) bb);
            }
            i7 = a.getMeasuredWidth() + i9;
            if (i7 >= i4) {
                return (i5 < 0 || i2 <= i5 || i6 <= 0 || i7 == i4) ? i4 : i6;
            } else {
                if (i5 >= 0 && i2 >= i5) {
                    i6 = i7;
                }
                i2++;
            }
        }
        return i7;
    }

    void m16227a(Canvas canvas, Rect rect, int i) {
        Drawable drawable = this.bc;
        drawable.setBounds(rect);
        drawable.draw(canvas);
    }

    void m16228a(Canvas canvas, Drawable drawable, Rect rect) {
        int minimumWidth = drawable.getMinimumWidth();
        canvas.save();
        canvas.clipRect(rect);
        if (rect.right - rect.left < minimumWidth) {
            rect.left = rect.right - minimumWidth;
        }
        drawable.setBounds(rect);
        drawable.draw(canvas);
        canvas.restore();
    }

    public void m16229a(View view, Object obj, boolean z) {
        if (this.B == null || (this.B instanceof ar)) {
            ap apVar = new ap();
            apVar.f14479a = view;
            apVar.f14480b = obj;
            apVar.f14481c = z;
            this.aZ.add(apVar);
            if (this.B != null && this.A != null) {
                this.A.onChanged();
                return;
            }
            return;
        }
        throw new IllegalStateException("Cannot add header view to list -- setAdapter has already been called.");
    }

    protected void m16230a(boolean z) {
        int i = 0;
        int childCount = getChildCount();
        if (z) {
            if (childCount > 0) {
                i = getChildAt(childCount + bb).getRight() + this.bd;
            }
            m16207i(childCount + this.av, i);
            m16213k(getChildCount());
            return;
        }
        m16212k(this.av + bb, childCount > 0 ? getChildAt(0).getLeft() - this.bd : getWidth() - 0);
        m16216l(getChildCount());
    }

    final int[] m16231a(int i, int i2, int i3, int i4, int i5, int i6) {
        ListAdapter listAdapter = this.B;
        if (listAdapter == null) {
            int[] iArr = new int[aX];
            iArr[0] = this.M.left + this.M.right;
            iArr[1] = this.M.top + this.M.bottom;
            return iArr;
        }
        int i7 = this.M.left + this.M.right;
        int i8 = this.M.top + this.M.bottom;
        int i9 = (this.bd <= 0 || this.bc == null) ? 0 : this.bd;
        if (i3 == bb) {
            i3 = listAdapter.getCount() + bb;
        }
        C2837p c2837p = this.H;
        boolean B = m16224B();
        boolean[] zArr = this.al;
        int i10 = 0;
        int i11 = 0;
        while (i2 <= i3) {
            View a = m16119a(i2, zArr);
            m16192a(a, i2, i);
            if (B && c2837p.m16399b(((LayoutParams) a.getLayoutParams()).f14323a)) {
                c2837p.m16396a(a, (int) bb);
            }
            i10 = Math.max(i10, a.getMeasuredWidth() + i9);
            i2++;
            i11 = Math.max(i11, a.getMeasuredHeight());
        }
        i11 += i8;
        iArr = new int[aX];
        iArr[0] = Math.min(i10 + i7, i4);
        iArr[1] = Math.min(i11, i5);
        return iArr;
    }

    protected int m16232b(int i, boolean z) {
        ListAdapter listAdapter = this.B;
        if (listAdapter == null || isInTouchMode()) {
            return bb;
        }
        int count = listAdapter.getCount();
        if (this.bl) {
            return (i < 0 || i >= count) ? bb : i;
        } else {
            if (z) {
                i = Math.max(0, i);
                while (i < count && !listAdapter.isEnabled(i)) {
                    i++;
                }
            } else {
                i = Math.min(i, count + bb);
                while (i >= 0 && !listAdapter.isEnabled(i)) {
                    i += bb;
                }
            }
            return (i < 0 || i >= count) ? bb : i;
        }
    }

    void m16233b(Canvas canvas, Drawable drawable, Rect rect) {
        int minimumWidth = drawable.getMinimumWidth();
        canvas.save();
        canvas.clipRect(rect);
        if (rect.right - rect.left < minimumWidth) {
            rect.right = minimumWidth + rect.left;
        }
        drawable.setBounds(rect);
        drawable.draw(canvas);
        canvas.restore();
    }

    public void m16234b(View view) {
        m16229a(view, null, true);
    }

    public void m16235b(View view, Object obj, boolean z) {
        ap apVar = new ap();
        apVar.f14479a = view;
        apVar.f14480b = obj;
        apVar.f14481c = z;
        this.ba.add(apVar);
        if (this.B != null && this.A != null) {
            this.A.onChanged();
        }
    }

    public void m16236c(int i) {
        super.m16138c(i);
    }

    public boolean m16237c(View view) {
        if (this.aZ.size() <= 0) {
            return false;
        }
        boolean z;
        if (this.B == null || !((ar) this.B).m16309a(view)) {
            z = false;
        } else {
            if (this.A != null) {
                this.A.onChanged();
            }
            z = true;
        }
        m16195a(view, this.aZ);
        return z;
    }

    protected boolean canAnimate() {
        return super.canAnimate() && this.aO > 0;
    }

    public void m16238d(int i) {
        super.m16141d(i);
    }

    public void m16239d(View view) {
        m16235b(view, null, true);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void dispatchDraw(android.graphics.Canvas r26) {
        /*
        r25 = this;
        r0 = r25;
        r2 = r0.Q;
        if (r2 == 0) goto L_0x000b;
    L_0x0006:
        r2 = 1;
        r0 = r25;
        r0.R = r2;
    L_0x000b:
        r0 = r25;
        r8 = r0.bd;
        r0 = r25;
        r9 = r0.bf;
        r0 = r25;
        r10 = r0.bg;
        if (r9 == 0) goto L_0x0140;
    L_0x0019:
        r2 = 1;
        r7 = r2;
    L_0x001b:
        if (r10 == 0) goto L_0x0144;
    L_0x001d:
        r2 = 1;
        r6 = r2;
    L_0x001f:
        if (r8 <= 0) goto L_0x0148;
    L_0x0021:
        r0 = r25;
        r2 = r0.bc;
        if (r2 == 0) goto L_0x0148;
    L_0x0027:
        r2 = 1;
        r5 = r2;
    L_0x0029:
        if (r5 != 0) goto L_0x002f;
    L_0x002b:
        if (r7 != 0) goto L_0x002f;
    L_0x002d:
        if (r6 == 0) goto L_0x018d;
    L_0x002f:
        r0 = r25;
        r11 = r0.bn;
        r2 = r25.getPaddingTop();
        r11.top = r2;
        r2 = r25.getBottom();
        r3 = r25.getTop();
        r2 = r2 - r3;
        r3 = r25.getPaddingBottom();
        r2 = r2 - r3;
        r11.bottom = r2;
        r12 = r25.getChildCount();
        r0 = r25;
        r2 = r0.aZ;
        r13 = r2.size();
        r0 = r25;
        r14 = r0.aO;
        r0 = r25;
        r2 = r0.ba;
        r2 = r2.size();
        r2 = r14 - r2;
        r15 = r2 + -1;
        r0 = r25;
        r0 = r0.bj;
        r16 = r0;
        r0 = r25;
        r0 = r0.bk;
        r17 = r0;
        r0 = r25;
        r0 = r0.av;
        r18 = r0;
        r0 = r25;
        r0 = r0.bl;
        r19 = r0;
        r0 = r25;
        r0 = r0.B;
        r20 = r0;
        r2 = r25.isOpaque();
        if (r2 == 0) goto L_0x014c;
    L_0x0089:
        r2 = super.isOpaque();
        if (r2 != 0) goto L_0x014c;
    L_0x008f:
        r2 = 1;
        r4 = r2;
    L_0x0091:
        if (r4 == 0) goto L_0x00b3;
    L_0x0093:
        r0 = r25;
        r2 = r0.bo;
        if (r2 != 0) goto L_0x00b3;
    L_0x0099:
        r0 = r25;
        r2 = r0.bh;
        if (r2 == 0) goto L_0x00b3;
    L_0x009f:
        r2 = new android.graphics.Paint;
        r2.<init>();
        r0 = r25;
        r0.bo = r2;
        r0 = r25;
        r2 = r0.bo;
        r3 = r25.getCacheColorHint();
        r2.setColor(r3);
    L_0x00b3:
        r0 = r25;
        r0 = r0.bo;
        r21 = r0;
        r3 = 0;
        r2 = 0;
        r22 = r25.getRight();
        r23 = r25.getLeft();
        r22 = r22 - r23;
        r2 = r22 - r2;
        r22 = r25.getScrollX();
        r22 = r22 + r2;
        r0 = r25;
        r2 = r0.ae;
        if (r2 != 0) goto L_0x0191;
    L_0x00d3:
        r3 = 0;
        r2 = r25.getScrollX();
        if (r12 <= 0) goto L_0x00ea;
    L_0x00da:
        if (r2 >= 0) goto L_0x00ea;
    L_0x00dc:
        if (r7 == 0) goto L_0x0150;
    L_0x00de:
        r7 = 0;
        r11.right = r7;
        r11.left = r2;
        r0 = r25;
        r1 = r26;
        r0.m16228a(r1, r9, r11);
    L_0x00ea:
        r2 = 0;
        r24 = r2;
        r2 = r3;
        r3 = r24;
    L_0x00f0:
        if (r3 >= r12) goto L_0x0171;
    L_0x00f2:
        if (r16 != 0) goto L_0x00f8;
    L_0x00f4:
        r7 = r18 + r3;
        if (r7 < r13) goto L_0x013d;
    L_0x00f8:
        if (r17 != 0) goto L_0x00fe;
    L_0x00fa:
        r7 = r18 + r3;
        if (r7 >= r15) goto L_0x013d;
    L_0x00fe:
        r0 = r25;
        r2 = r0.getChildAt(r3);
        r2 = r2.getRight();
        if (r5 == 0) goto L_0x013d;
    L_0x010a:
        r0 = r22;
        if (r2 >= r0) goto L_0x013d;
    L_0x010e:
        if (r6 == 0) goto L_0x0114;
    L_0x0110:
        r7 = r12 + -1;
        if (r3 == r7) goto L_0x013d;
    L_0x0114:
        if (r19 != 0) goto L_0x0130;
    L_0x0116:
        r7 = r18 + r3;
        r0 = r20;
        r7 = r0.isEnabled(r7);
        if (r7 == 0) goto L_0x0161;
    L_0x0120:
        r7 = r12 + -1;
        if (r3 == r7) goto L_0x0130;
    L_0x0124:
        r7 = r18 + r3;
        r7 = r7 + 1;
        r0 = r20;
        r7 = r0.isEnabled(r7);
        if (r7 == 0) goto L_0x0161;
    L_0x0130:
        r11.left = r2;
        r7 = r2 + r8;
        r11.right = r7;
        r0 = r25;
        r1 = r26;
        r0.m16227a(r1, r11, r3);
    L_0x013d:
        r3 = r3 + 1;
        goto L_0x00f0;
    L_0x0140:
        r2 = 0;
        r7 = r2;
        goto L_0x001b;
    L_0x0144:
        r2 = 0;
        r6 = r2;
        goto L_0x001f;
    L_0x0148:
        r2 = 0;
        r5 = r2;
        goto L_0x0029;
    L_0x014c:
        r2 = 0;
        r4 = r2;
        goto L_0x0091;
    L_0x0150:
        if (r5 == 0) goto L_0x00ea;
    L_0x0152:
        r2 = 0;
        r11.right = r2;
        r2 = -r8;
        r11.left = r2;
        r2 = -1;
        r0 = r25;
        r1 = r26;
        r0.m16227a(r1, r11, r2);
        goto L_0x00ea;
    L_0x0161:
        if (r4 == 0) goto L_0x013d;
    L_0x0163:
        r11.left = r2;
        r7 = r2 + r8;
        r11.right = r7;
        r0 = r26;
        r1 = r21;
        r0.drawRect(r11, r1);
        goto L_0x013d;
    L_0x0171:
        r3 = r25.getRight();
        r4 = r25.getScrollX();
        r3 = r3 + r4;
        if (r6 == 0) goto L_0x018d;
    L_0x017c:
        r4 = r18 + r12;
        if (r4 != r14) goto L_0x018d;
    L_0x0180:
        if (r3 <= r2) goto L_0x018d;
    L_0x0182:
        r11.left = r2;
        r11.right = r3;
        r0 = r25;
        r1 = r26;
        r0.m16233b(r1, r10, r11);
    L_0x018d:
        super.dispatchDraw(r26);
        return;
    L_0x0191:
        r14 = r25.getScrollX();
        if (r12 <= 0) goto L_0x01af;
    L_0x0197:
        if (r7 == 0) goto L_0x01af;
    L_0x0199:
        r11.left = r14;
        r2 = 0;
        r0 = r25;
        r2 = r0.getChildAt(r2);
        r2 = r2.getLeft();
        r11.right = r2;
        r0 = r25;
        r1 = r26;
        r0.m16228a(r1, r9, r11);
    L_0x01af:
        if (r7 == 0) goto L_0x01fa;
    L_0x01b1:
        r2 = 1;
    L_0x01b2:
        if (r2 >= r12) goto L_0x020c;
    L_0x01b4:
        if (r16 != 0) goto L_0x01ba;
    L_0x01b6:
        r7 = r18 + r2;
        if (r7 < r13) goto L_0x01f7;
    L_0x01ba:
        if (r17 != 0) goto L_0x01c0;
    L_0x01bc:
        r7 = r18 + r2;
        if (r7 >= r15) goto L_0x01f7;
    L_0x01c0:
        r0 = r25;
        r7 = r0.getChildAt(r2);
        r7 = r7.getLeft();
        if (r7 <= r3) goto L_0x01f7;
    L_0x01cc:
        if (r19 != 0) goto L_0x01e8;
    L_0x01ce:
        r9 = r18 + r2;
        r0 = r20;
        r9 = r0.isEnabled(r9);
        if (r9 == 0) goto L_0x01fc;
    L_0x01d8:
        r9 = r12 + -1;
        if (r2 == r9) goto L_0x01e8;
    L_0x01dc:
        r9 = r18 + r2;
        r9 = r9 + 1;
        r0 = r20;
        r9 = r0.isEnabled(r9);
        if (r9 == 0) goto L_0x01fc;
    L_0x01e8:
        r9 = r7 - r8;
        r11.left = r9;
        r11.right = r7;
        r7 = r2 + -1;
        r0 = r25;
        r1 = r26;
        r0.m16227a(r1, r11, r7);
    L_0x01f7:
        r2 = r2 + 1;
        goto L_0x01b2;
    L_0x01fa:
        r2 = 0;
        goto L_0x01b2;
    L_0x01fc:
        if (r4 == 0) goto L_0x01f7;
    L_0x01fe:
        r9 = r7 - r8;
        r11.left = r9;
        r11.right = r7;
        r0 = r26;
        r1 = r21;
        r0.drawRect(r11, r1);
        goto L_0x01f7;
    L_0x020c:
        if (r12 <= 0) goto L_0x018d;
    L_0x020e:
        if (r14 <= 0) goto L_0x018d;
    L_0x0210:
        if (r6 == 0) goto L_0x0224;
    L_0x0212:
        r2 = r25.getRight();
        r11.left = r2;
        r2 = r2 + r14;
        r11.right = r2;
        r0 = r25;
        r1 = r26;
        r0.m16233b(r1, r10, r11);
        goto L_0x018d;
    L_0x0224:
        if (r5 == 0) goto L_0x018d;
    L_0x0226:
        r0 = r22;
        r11.left = r0;
        r2 = r22 + r8;
        r11.right = r2;
        r2 = -1;
        r0 = r25;
        r1 = r26;
        r0.m16227a(r1, r11, r2);
        goto L_0x018d;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sephiroth.android.library.widget.HListView.dispatchDraw(android.graphics.Canvas):void");
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean dispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
        return (dispatchKeyEvent || getFocusedChild() == null || keyEvent.getAction() != 0) ? dispatchKeyEvent : onKeyDown(keyEvent.getKeyCode(), keyEvent);
    }

    protected boolean drawChild(Canvas canvas, View view, long j) {
        boolean drawChild = super.drawChild(canvas, view, j);
        if (this.R) {
            this.R = false;
        }
        return drawChild;
    }

    public boolean m16240e(View view) {
        if (this.ba.size() <= 0) {
            return false;
        }
        boolean z;
        if (this.B == null || !((ar) this.B).m16311b(view)) {
            z = false;
        } else {
            if (this.A != null) {
                this.A.onChanged();
            }
            z = true;
        }
        m16195a(view, this.ba);
        return z;
    }

    protected int m16241f(int i) {
        int childCount = getChildCount();
        if (childCount > 0) {
            int i2;
            if (this.ae) {
                for (i2 = childCount + bb; i2 >= 0; i2 += bb) {
                    if (i >= getChildAt(i2).getLeft()) {
                        return i2 + this.av;
                    }
                }
            } else {
                for (i2 = 0; i2 < childCount; i2++) {
                    if (i <= getChildAt(i2).getRight()) {
                        return i2 + this.av;
                    }
                }
            }
        }
        return bb;
    }

    public int[] m16242f(View view) {
        m16208i(view);
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int[] iArr = new int[aX];
        iArr[0] = measuredWidth;
        iArr[1] = measuredHeight;
        return iArr;
    }

    protected void m16243g() {
        m16196a(this.aZ);
        m16196a(this.ba);
        super.m16151g();
        this.z = 0;
    }

    public ListAdapter getAdapter() {
        return this.B;
    }

    @Deprecated
    public long[] getCheckItemIds() {
        if (this.B != null && this.B.hasStableIds()) {
            return getCheckedItemIds();
        }
        if (this.t == 0 || this.x == null || this.B == null) {
            return new long[0];
        }
        SparseArrayCompat sparseArrayCompat = this.x;
        int size = sparseArrayCompat.size();
        Object obj = new long[size];
        ListAdapter listAdapter = this.B;
        int i = 0;
        int i2 = 0;
        while (i < size) {
            int i3;
            if (((Boolean) sparseArrayCompat.valueAt(i)).booleanValue()) {
                i3 = i2 + 1;
                obj[i2] = listAdapter.getItemId(sparseArrayCompat.keyAt(i));
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        if (i2 == size) {
            return obj;
        }
        Object obj2 = new long[i2];
        System.arraycopy(obj, 0, obj2, 0, i2);
        return obj2;
    }

    public Drawable getDivider() {
        return this.bc;
    }

    public int getDividerWidth() {
        return this.bd;
    }

    public int getFooterViewsCount() {
        return this.ba.size();
    }

    public int getHeaderViewsCount() {
        return this.aZ.size();
    }

    public boolean getItemsCanFocus() {
        return this.bm;
    }

    public int getMaxScrollAmount() {
        return (int) (aW * ((float) (getRight() - getLeft())));
    }

    public Drawable getOverscrollFooter() {
        return this.bg;
    }

    public Drawable getOverscrollHeader() {
        return this.bf;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void m16244h() {
        /*
        r17 = this;
        r0 = r17;
        r10 = r0.aV;
        if (r10 != 0) goto L_0x0024;
    L_0x0006:
        r1 = 1;
        r0 = r17;
        r0.aV = r1;
        super.m16153h();	 Catch:{ all -> 0x0103 }
        r17.invalidate();	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.B;	 Catch:{ all -> 0x0103 }
        if (r1 != 0) goto L_0x0025;
    L_0x0017:
        r17.m16243g();	 Catch:{ all -> 0x0103 }
        r17.m16137c();	 Catch:{ all -> 0x0103 }
        if (r10 != 0) goto L_0x0024;
    L_0x001f:
        r1 = 0;
        r0 = r17;
        r0.aV = r1;
    L_0x0024:
        return;
    L_0x0025:
        r0 = r17;
        r1 = r0.M;	 Catch:{ all -> 0x0103 }
        r5 = r1.left;	 Catch:{ all -> 0x0103 }
        r1 = r17.getRight();	 Catch:{ all -> 0x0103 }
        r2 = r17.getLeft();	 Catch:{ all -> 0x0103 }
        r1 = r1 - r2;
        r0 = r17;
        r2 = r0.M;	 Catch:{ all -> 0x0103 }
        r2 = r2.right;	 Catch:{ all -> 0x0103 }
        r6 = r1 - r2;
        r11 = r17.getChildCount();	 Catch:{ all -> 0x0103 }
        r4 = 0;
        r2 = 0;
        r1 = 0;
        r3 = 0;
        r7 = 0;
        r0 = r17;
        r8 = r0.z;	 Catch:{ all -> 0x0103 }
        switch(r8) {
            case 1: goto L_0x00b0;
            case 2: goto L_0x009b;
            case 3: goto L_0x00b0;
            case 4: goto L_0x00b0;
            case 5: goto L_0x00b0;
            default: goto L_0x004c;
        };	 Catch:{ all -> 0x0103 }
    L_0x004c:
        r0 = r17;
        r1 = r0.aM;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r3 = r0.av;	 Catch:{ all -> 0x0103 }
        r3 = r1 - r3;
        if (r3 < 0) goto L_0x0060;
    L_0x0058:
        if (r3 >= r11) goto L_0x0060;
    L_0x005a:
        r0 = r17;
        r2 = r0.getChildAt(r3);	 Catch:{ all -> 0x0103 }
    L_0x0060:
        r1 = 0;
        r0 = r17;
        r1 = r0.getChildAt(r1);	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r8 = r0.aK;	 Catch:{ all -> 0x0103 }
        if (r8 < 0) goto L_0x0076;
    L_0x006d:
        r0 = r17;
        r4 = r0.aK;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r8 = r0.aM;	 Catch:{ all -> 0x0103 }
        r4 = r4 - r8;
    L_0x0076:
        r3 = r3 + r4;
        r0 = r17;
        r3 = r0.getChildAt(r3);	 Catch:{ all -> 0x0103 }
        r9 = r1;
    L_0x007e:
        r0 = r17;
        r12 = r0.aJ;	 Catch:{ all -> 0x0103 }
        if (r12 == 0) goto L_0x0087;
    L_0x0084:
        r17.m16167u();	 Catch:{ all -> 0x0103 }
    L_0x0087:
        r0 = r17;
        r1 = r0.aO;	 Catch:{ all -> 0x0103 }
        if (r1 != 0) goto L_0x00b2;
    L_0x008d:
        r17.m16243g();	 Catch:{ all -> 0x0103 }
        r17.m16137c();	 Catch:{ all -> 0x0103 }
        if (r10 != 0) goto L_0x0024;
    L_0x0095:
        r1 = 0;
        r0 = r17;
        r0.aV = r1;
        goto L_0x0024;
    L_0x009b:
        r0 = r17;
        r8 = r0.aK;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r9 = r0.av;	 Catch:{ all -> 0x0103 }
        r8 = r8 - r9;
        if (r8 < 0) goto L_0x033c;
    L_0x00a6:
        if (r8 >= r11) goto L_0x033c;
    L_0x00a8:
        r0 = r17;
        r3 = r0.getChildAt(r8);	 Catch:{ all -> 0x0103 }
        r9 = r1;
        goto L_0x007e;
    L_0x00b0:
        r9 = r1;
        goto L_0x007e;
    L_0x00b2:
        r0 = r17;
        r1 = r0.aO;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r8 = r0.B;	 Catch:{ all -> 0x0103 }
        r8 = r8.getCount();	 Catch:{ all -> 0x0103 }
        if (r1 == r8) goto L_0x010c;
    L_0x00c0:
        r1 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0103 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0103 }
        r2.<init>();	 Catch:{ all -> 0x0103 }
        r3 = "The content of the adapter has changed but ListView did not receive a notification. Make sure the content of your adapter is not modified from a background thread, but only from the UI thread. [in ListView(";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0103 }
        r3 = r17.getId();	 Catch:{ all -> 0x0103 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0103 }
        r3 = ", ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0103 }
        r3 = r17.getClass();	 Catch:{ all -> 0x0103 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0103 }
        r3 = ") with Adapter(";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r3 = r0.B;	 Catch:{ all -> 0x0103 }
        r3 = r3.getClass();	 Catch:{ all -> 0x0103 }
        r2 = r2.append(r3);	 Catch:{ all -> 0x0103 }
        r3 = ")]";
        r2 = r2.append(r3);	 Catch:{ all -> 0x0103 }
        r2 = r2.toString();	 Catch:{ all -> 0x0103 }
        r1.<init>(r2);	 Catch:{ all -> 0x0103 }
        throw r1;	 Catch:{ all -> 0x0103 }
    L_0x0103:
        r1 = move-exception;
        if (r10 != 0) goto L_0x010b;
    L_0x0106:
        r2 = 0;
        r0 = r17;
        r0.aV = r2;
    L_0x010b:
        throw r1;
    L_0x010c:
        r0 = r17;
        r1 = r0.aK;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r0.setSelectedPositionInt(r1);	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r13 = r0.av;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r14 = r0.H;	 Catch:{ all -> 0x0103 }
        r1 = 0;
        if (r12 == 0) goto L_0x0131;
    L_0x0120:
        r8 = 0;
    L_0x0121:
        if (r8 >= r11) goto L_0x0134;
    L_0x0123:
        r0 = r17;
        r15 = r0.getChildAt(r8);	 Catch:{ all -> 0x0103 }
        r16 = r13 + r8;
        r14.m16396a(r15, r16);	 Catch:{ all -> 0x0103 }
        r8 = r8 + 1;
        goto L_0x0121;
    L_0x0131:
        r14.m16395a(r11, r13);	 Catch:{ all -> 0x0103 }
    L_0x0134:
        r8 = r17.getFocusedChild();	 Catch:{ all -> 0x0103 }
        if (r8 == 0) goto L_0x0338;
    L_0x013a:
        if (r12 == 0) goto L_0x0144;
    L_0x013c:
        r0 = r17;
        r12 = r0.m16206h(r8);	 Catch:{ all -> 0x0103 }
        if (r12 == 0) goto L_0x014e;
    L_0x0144:
        r7 = r17.findFocus();	 Catch:{ all -> 0x0103 }
        if (r7 == 0) goto L_0x014d;
    L_0x014a:
        r7.onStartTemporaryDetach();	 Catch:{ all -> 0x0103 }
    L_0x014d:
        r1 = r8;
    L_0x014e:
        r17.requestFocus();	 Catch:{ all -> 0x0103 }
        r8 = r7;
        r7 = r1;
    L_0x0153:
        r17.detachAllViewsFromParent();	 Catch:{ all -> 0x0103 }
        r14.m16403d();	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.z;	 Catch:{ all -> 0x0103 }
        switch(r1) {
            case 1: goto L_0x0247;
            case 2: goto L_0x020c;
            case 3: goto L_0x0235;
            case 4: goto L_0x0258;
            case 5: goto L_0x0224;
            case 6: goto L_0x0269;
            default: goto L_0x0160;
        };	 Catch:{ all -> 0x0103 }
    L_0x0160:
        if (r11 != 0) goto L_0x0293;
    L_0x0162:
        r0 = r17;
        r1 = r0.ae;	 Catch:{ all -> 0x0103 }
        if (r1 != 0) goto L_0x0272;
    L_0x0168:
        r1 = 0;
        r2 = 1;
        r0 = r17;
        r1 = r0.m16232b(r1, r2);	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r0.setSelectedPositionInt(r1);	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.m16209j(r5);	 Catch:{ all -> 0x0103 }
        r2 = r1;
    L_0x017c:
        r14.m16405e();	 Catch:{ all -> 0x0103 }
        if (r2 == 0) goto L_0x02f7;
    L_0x0181:
        r0 = r17;
        r1 = r0.bm;	 Catch:{ all -> 0x0103 }
        if (r1 == 0) goto L_0x02ef;
    L_0x0187:
        r1 = r17.hasFocus();	 Catch:{ all -> 0x0103 }
        if (r1 == 0) goto L_0x02ef;
    L_0x018d:
        r1 = r2.hasFocus();	 Catch:{ all -> 0x0103 }
        if (r1 != 0) goto L_0x02ef;
    L_0x0193:
        if (r2 != r7) goto L_0x019d;
    L_0x0195:
        if (r8 == 0) goto L_0x019d;
    L_0x0197:
        r1 = r8.requestFocus();	 Catch:{ all -> 0x0103 }
        if (r1 != 0) goto L_0x01a3;
    L_0x019d:
        r1 = r2.requestFocus();	 Catch:{ all -> 0x0103 }
        if (r1 == 0) goto L_0x02df;
    L_0x01a3:
        r1 = 1;
    L_0x01a4:
        if (r1 != 0) goto L_0x02e2;
    L_0x01a6:
        r1 = r17.getFocusedChild();	 Catch:{ all -> 0x0103 }
        if (r1 == 0) goto L_0x01af;
    L_0x01ac:
        r1.clearFocus();	 Catch:{ all -> 0x0103 }
    L_0x01af:
        r1 = -1;
        r0 = r17;
        r0.m16124a(r1, r2);	 Catch:{ all -> 0x0103 }
    L_0x01b5:
        r1 = r2.getLeft();	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r0.ad = r1;	 Catch:{ all -> 0x0103 }
    L_0x01bd:
        if (r8 == 0) goto L_0x01c8;
    L_0x01bf:
        r1 = r8.getWindowToken();	 Catch:{ all -> 0x0103 }
        if (r1 == 0) goto L_0x01c8;
    L_0x01c5:
        r8.onFinishTemporaryDetach();	 Catch:{ all -> 0x0103 }
    L_0x01c8:
        r1 = 0;
        r0 = r17;
        r0.z = r1;	 Catch:{ all -> 0x0103 }
        r1 = 0;
        r0 = r17;
        r0.aJ = r1;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.ak;	 Catch:{ all -> 0x0103 }
        if (r1 == 0) goto L_0x01e6;
    L_0x01d8:
        r0 = r17;
        r1 = r0.ak;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r0.post(r1);	 Catch:{ all -> 0x0103 }
        r1 = 0;
        r0 = r17;
        r0.ak = r1;	 Catch:{ all -> 0x0103 }
    L_0x01e6:
        r1 = 0;
        r0 = r17;
        r0.aA = r1;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.aM;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r0.setNextSelectedPositionInt(r1);	 Catch:{ all -> 0x0103 }
        r17.m16155i();	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.aO;	 Catch:{ all -> 0x0103 }
        if (r1 <= 0) goto L_0x0200;
    L_0x01fd:
        r17.m16077y();	 Catch:{ all -> 0x0103 }
    L_0x0200:
        r17.m16137c();	 Catch:{ all -> 0x0103 }
        if (r10 != 0) goto L_0x0024;
    L_0x0205:
        r1 = 0;
        r0 = r17;
        r0.aV = r1;
        goto L_0x0024;
    L_0x020c:
        if (r3 == 0) goto L_0x021b;
    L_0x020e:
        r1 = r3.getLeft();	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.m16198b(r1, r5, r6);	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x021b:
        r0 = r17;
        r1 = r0.m16215l(r5, r6);	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x0224:
        r0 = r17;
        r1 = r0.ax;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r2 = r0.aw;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.m16217m(r1, r2);	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x0235:
        r0 = r17;
        r1 = r0.aO;	 Catch:{ all -> 0x0103 }
        r1 = r1 + -1;
        r0 = r17;
        r1 = r0.m16212k(r1, r6);	 Catch:{ all -> 0x0103 }
        r17.m16185D();	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x0247:
        r1 = 0;
        r0 = r17;
        r0.av = r1;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.m16209j(r5);	 Catch:{ all -> 0x0103 }
        r17.m16185D();	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x0258:
        r1 = r17.m16162p();	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r2 = r0.aw;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.m16217m(r1, r2);	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x0269:
        r1 = r17;
        r1 = r1.m16190a(r2, r3, r4, r5, r6);	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x0272:
        r0 = r17;
        r1 = r0.aO;	 Catch:{ all -> 0x0103 }
        r1 = r1 + -1;
        r2 = 0;
        r0 = r17;
        r1 = r0.m16232b(r1, r2);	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r0.setSelectedPositionInt(r1);	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.aO;	 Catch:{ all -> 0x0103 }
        r1 = r1 + -1;
        r0 = r17;
        r1 = r0.m16212k(r1, r6);	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x0293:
        r0 = r17;
        r1 = r0.aM;	 Catch:{ all -> 0x0103 }
        if (r1 < 0) goto L_0x02b7;
    L_0x0299:
        r0 = r17;
        r1 = r0.aM;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r3 = r0.aO;	 Catch:{ all -> 0x0103 }
        if (r1 >= r3) goto L_0x02b7;
    L_0x02a3:
        r0 = r17;
        r1 = r0.aM;	 Catch:{ all -> 0x0103 }
        if (r2 != 0) goto L_0x02b2;
    L_0x02a9:
        r0 = r17;
        r1 = r0.m16217m(r1, r5);	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x02b2:
        r5 = r2.getLeft();	 Catch:{ all -> 0x0103 }
        goto L_0x02a9;
    L_0x02b7:
        r0 = r17;
        r1 = r0.av;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r2 = r0.aO;	 Catch:{ all -> 0x0103 }
        if (r1 >= r2) goto L_0x02d5;
    L_0x02c1:
        r0 = r17;
        r1 = r0.av;	 Catch:{ all -> 0x0103 }
        if (r9 != 0) goto L_0x02d0;
    L_0x02c7:
        r0 = r17;
        r1 = r0.m16217m(r1, r5);	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x02d0:
        r5 = r9.getLeft();	 Catch:{ all -> 0x0103 }
        goto L_0x02c7;
    L_0x02d5:
        r1 = 0;
        r0 = r17;
        r1 = r0.m16217m(r1, r5);	 Catch:{ all -> 0x0103 }
        r2 = r1;
        goto L_0x017c;
    L_0x02df:
        r1 = 0;
        goto L_0x01a4;
    L_0x02e2:
        r1 = 0;
        r2.setSelected(r1);	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.G;	 Catch:{ all -> 0x0103 }
        r1.setEmpty();	 Catch:{ all -> 0x0103 }
        goto L_0x01b5;
    L_0x02ef:
        r1 = -1;
        r0 = r17;
        r0.m16124a(r1, r2);	 Catch:{ all -> 0x0103 }
        goto L_0x01b5;
    L_0x02f7:
        r0 = r17;
        r1 = r0.Z;	 Catch:{ all -> 0x0103 }
        if (r1 <= 0) goto L_0x032b;
    L_0x02fd:
        r0 = r17;
        r1 = r0.Z;	 Catch:{ all -> 0x0103 }
        r2 = 3;
        if (r1 >= r2) goto L_0x032b;
    L_0x0304:
        r0 = r17;
        r1 = r0.S;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r2 = r0.av;	 Catch:{ all -> 0x0103 }
        r1 = r1 - r2;
        r0 = r17;
        r1 = r0.getChildAt(r1);	 Catch:{ all -> 0x0103 }
        if (r1 == 0) goto L_0x031e;
    L_0x0315:
        r0 = r17;
        r2 = r0.S;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r0.m16124a(r2, r1);	 Catch:{ all -> 0x0103 }
    L_0x031e:
        r1 = r17.hasFocus();	 Catch:{ all -> 0x0103 }
        if (r1 == 0) goto L_0x01bd;
    L_0x0324:
        if (r8 == 0) goto L_0x01bd;
    L_0x0326:
        r8.requestFocus();	 Catch:{ all -> 0x0103 }
        goto L_0x01bd;
    L_0x032b:
        r1 = 0;
        r0 = r17;
        r0.ad = r1;	 Catch:{ all -> 0x0103 }
        r0 = r17;
        r1 = r0.G;	 Catch:{ all -> 0x0103 }
        r1.setEmpty();	 Catch:{ all -> 0x0103 }
        goto L_0x031e;
    L_0x0338:
        r8 = r7;
        r7 = r1;
        goto L_0x0153;
    L_0x033c:
        r9 = r1;
        goto L_0x007e;
        */
        throw new UnsupportedOperationException("Method not decompiled: it.sephiroth.android.library.widget.HListView.h():void");
    }

    public boolean isOpaque() {
        boolean z = (this.R && this.bh && this.bi) || super.isOpaque();
        if (z) {
            int paddingLeft = this.M != null ? this.M.left : getPaddingLeft();
            View childAt = getChildAt(0);
            if (childAt == null || childAt.getLeft() > paddingLeft) {
                return false;
            }
            paddingLeft = getWidth() - (this.M != null ? this.M.right : getPaddingRight());
            childAt = getChildAt(getChildCount() + bb);
            if (childAt == null || childAt.getRight() < paddingLeft) {
                return false;
            }
        }
        return z;
    }

    public void m16245j(int i, int i2) {
        if (this.B != null) {
            if (isInTouchMode()) {
                this.ah = i;
            } else {
                i = m16232b(i, true);
                if (i >= 0) {
                    setNextSelectedPositionInt(i);
                }
            }
            if (i >= 0) {
                this.z = 4;
                this.aw = this.M.left + i2;
                if (this.aA) {
                    this.ax = i;
                    this.ay = this.B.getItemId(i);
                }
                if (this.ac != null) {
                    this.ac.m16384a();
                }
                requestLayout();
            }
        }
    }

    boolean m16246o(int i) {
        int max;
        boolean z;
        if (i == 33) {
            max = Math.max(0, (this.aM - getChildCount()) + bb);
            z = false;
        } else if (i == Opcodes.IXOR) {
            max = Math.min(this.aO + bb, (this.aM + getChildCount()) + bb);
            z = true;
        } else {
            max = bb;
            z = false;
        }
        if (max >= 0) {
            max = m16232b(max, z);
            if (max >= 0) {
                this.z = 4;
                this.aw = getPaddingLeft() + getHorizontalFadingEdgeLength();
                if (z && max > this.aO - getChildCount()) {
                    this.z = 3;
                }
                if (!z && max < getChildCount()) {
                    this.z = 1;
                }
                setSelectionInt(max);
                m16137c();
                if (awakenScrollBars()) {
                    return true;
                }
                invalidate();
                return true;
            }
        }
        return false;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                m16234b(getChildAt(i));
            }
            removeAllViews();
        }
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        int i2 = 0;
        super.onFocusChanged(z, i, rect);
        ListAdapter listAdapter = this.B;
        int i3 = bb;
        if (!(listAdapter == null || !z || rect == null)) {
            rect.offset(getScrollX(), getScrollY());
            if (listAdapter.getCount() < getChildCount() + this.av) {
                this.z = 0;
                m16244h();
            }
            Rect rect2 = this.bn;
            int i4 = Integer.MAX_VALUE;
            int childCount = getChildCount();
            int i5 = this.av;
            int i6 = 0;
            while (i6 < childCount) {
                int a;
                if (listAdapter.isEnabled(i5 + i6)) {
                    View childAt = getChildAt(i6);
                    childAt.getDrawingRect(rect2);
                    offsetDescendantRectToMyCoords(childAt, rect2);
                    a = AbsHListView.m16089a(rect, rect2, i);
                    if (a < i4) {
                        i2 = i6;
                        i4 = a;
                        a = childAt.getLeft();
                    } else {
                        a = i2;
                        i2 = i3;
                    }
                } else {
                    a = i2;
                    i2 = i3;
                }
                i6++;
                i3 = i2;
                i2 = a;
            }
        }
        if (i3 >= 0) {
            m16245j(this.av + i3, i2);
        } else {
            requestLayout();
        }
    }

    public void onGlobalLayout() {
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(HListView.class.getName());
    }

    @TargetApi(14)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(HListView.class.getName());
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return m16197a(i, 1, keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return m16197a(i, i2, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return m16197a(i, 1, keyEvent);
    }

    @TargetApi(11)
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        this.aO = this.B == null ? 0 : this.B.getCount();
        if (this.aO > 0 && (mode == 0 || mode2 == 0)) {
            View a = m16119a(0, this.al);
            m16192a(a, 0, i2);
            i3 = a.getMeasuredWidth();
            i4 = a.getMeasuredHeight();
            if (VERSION.SDK_INT >= 11) {
                i5 = combineMeasuredStates(0, a.getMeasuredState());
            }
            if (m16224B() && this.H.m16399b(((LayoutParams) a.getLayoutParams()).f14323a)) {
                this.H.m16396a(a, (int) bb);
            }
        }
        int i6 = i3;
        mode2 = mode2 == 0 ? (i4 + (this.M.top + this.M.bottom)) + getHorizontalScrollbarHeight() : (mode2 != C1186y.f5353a || this.aO <= 0 || this.be <= bb) ? VERSION.SDK_INT >= 11 ? size2 | (ViewCompat.MEASURED_STATE_MASK & i5) : size2 : m16231a(i2, this.be, this.be, size, size2, bb)[1];
        if (mode == 0) {
            size = ((this.M.left + this.M.right) + i6) + (getHorizontalFadingEdgeLength() * aX);
        }
        if (mode == C1186y.f5353a) {
            size = m16226a(i2, 0, (int) bb, size, (int) bb);
        }
        setMeasuredDimension(size, mode2);
        this.N = i2;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (getChildCount() > 0) {
            View focusedChild = getFocusedChild();
            if (focusedChild != null) {
                int indexOfChild = this.av + indexOfChild(focusedChild);
                int left = focusedChild.getLeft() - Math.max(0, focusedChild.getRight() - (i - getPaddingLeft()));
                if (this.bq == null) {
                    this.bq = new aq();
                }
                post(this.bq.m16306a(indexOfChild, left));
            }
        }
        super.onSizeChanged(i, i2, i3, i4);
    }

    boolean m16247p(int i) {
        boolean z = true;
        int b;
        if (i == 33) {
            if (this.aM != 0) {
                b = m16232b(0, true);
                if (b >= 0) {
                    this.z = 1;
                    setSelectionInt(b);
                    m16137c();
                }
            }
            z = false;
        } else {
            if (i == Opcodes.IXOR && this.aM < this.aO + bb) {
                b = m16232b(this.aO + bb, true);
                if (b >= 0) {
                    this.z = 3;
                    setSelectionInt(b);
                    m16137c();
                }
            }
            z = false;
        }
        if (z && !awakenScrollBars()) {
            awakenScrollBars();
            invalidate();
        }
        return z;
    }

    boolean m16248q(int i) {
        try {
            this.aF = true;
            boolean n = m16220n(i);
            if (n) {
                playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
            }
            this.aF = false;
            return n;
        } catch (Throwable th) {
            this.aF = false;
        }
    }

    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        int i = rect.left;
        rect.offset(view.getLeft(), view.getTop());
        rect.offset(-view.getScrollX(), -view.getScrollY());
        int width = getWidth();
        int scrollX = getScrollX();
        int i2 = scrollX + width;
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (m16186E() && (this.aM > 0 || i > horizontalFadingEdgeLength)) {
            scrollX += horizontalFadingEdgeLength;
        }
        i = getChildAt(getChildCount() + bb).getRight();
        if (m16187F() && (this.aM < this.aO + bb || rect.right < i - horizontalFadingEdgeLength)) {
            i2 -= horizontalFadingEdgeLength;
        }
        if (rect.right > i2 && rect.left > scrollX) {
            i2 = Math.min(rect.width() > width ? (rect.left - scrollX) + 0 : (rect.right - i2) + 0, i - i2);
        } else if (rect.left >= scrollX || rect.right >= i2) {
            i2 = 0;
        } else {
            i2 = Math.max(rect.width() > width ? 0 - (i2 - rect.right) : 0 - (scrollX - rect.left), getChildAt(0).getLeft() - scrollX);
        }
        boolean z2 = i2 != 0;
        if (z2) {
            m16223t(-i2);
            m16124a((int) bb, view);
            this.ad = view.getTop();
            invalidate();
        }
        return z2;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (!(this.B == null || this.A == null)) {
            this.B.unregisterDataSetObserver(this.A);
        }
        m16243g();
        this.H.m16398b();
        if (this.aZ.size() > 0 || this.ba.size() > 0) {
            this.B = new ar(this.aZ, this.ba, listAdapter);
        } else {
            this.B = listAdapter;
        }
        this.aT = bb;
        this.aU = Long.MIN_VALUE;
        super.setAdapter(listAdapter);
        if (this.B != null) {
            this.bl = this.B.areAllItemsEnabled();
            this.aP = this.aO;
            this.aO = this.B.getCount();
            m16075w();
            this.A = new C2823c(this);
            this.B.registerDataSetObserver(this.A);
            this.H.m16394a(this.B.getViewTypeCount());
            int b = this.ae ? m16232b(this.aO + bb, false) : m16232b(0, true);
            setSelectedPositionInt(b);
            setNextSelectedPositionInt(b);
            if (this.aO == 0) {
                m16077y();
            }
        } else {
            this.bl = true;
            m16075w();
            m16077y();
        }
        requestLayout();
    }

    public void setCacheColorHint(int i) {
        boolean z = (i >>> 24) == Util.MASK_8BIT;
        this.bh = z;
        if (z) {
            if (this.bo == null) {
                this.bo = new Paint();
            }
            this.bo.setColor(i);
        }
        super.setCacheColorHint(i);
    }

    public void setDivider(Drawable drawable) {
        boolean z = false;
        if (drawable != null) {
            this.bd = drawable.getIntrinsicWidth();
        } else {
            this.bd = 0;
        }
        this.bc = drawable;
        if (drawable == null || drawable.getOpacity() == bb) {
            z = true;
        }
        this.bi = z;
        requestLayout();
        invalidate();
    }

    public void setDividerWidth(int i) {
        this.bd = i;
        requestLayout();
        invalidate();
    }

    public void setFooterDividersEnabled(boolean z) {
        this.bk = z;
        invalidate();
    }

    public void setHeaderDividersEnabled(boolean z) {
        this.bj = z;
        invalidate();
    }

    public void setItemsCanFocus(boolean z) {
        this.bm = z;
        if (!z) {
            setDescendantFocusability(393216);
        }
    }

    public void setOverscrollFooter(Drawable drawable) {
        this.bg = drawable;
        invalidate();
    }

    public void setOverscrollHeader(Drawable drawable) {
        this.bf = drawable;
        if (getScrollX() < 0) {
            invalidate();
        }
    }

    public void setSelection(int i) {
        m16245j(i, 0);
    }

    public void setSelectionInt(int i) {
        Object obj = 1;
        setNextSelectedPositionInt(i);
        int i2 = this.aM;
        if (i2 < 0 || !(i == i2 + bb || i == i2 + 1)) {
            obj = null;
        }
        if (this.ac != null) {
            this.ac.m16384a();
        }
        m16244h();
        if (obj != null) {
            awakenScrollBars();
        }
    }
}
