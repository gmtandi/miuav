package com.amap.api.mapcore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import org.codehaus.jackson.org.objectweb.asm.Opcodes;

class az extends ViewGroup {
    private ab f1414a;

    /* renamed from: com.amap.api.mapcore.az.a */
    public class C0302a extends LayoutParams {
        public FPoint f1644a;
        public int f1645b;
        public int f1646c;
        public int f1647d;

        public C0302a(int i, int i2, FPoint fPoint, int i3, int i4, int i5) {
            super(i, i2);
            this.f1644a = null;
            this.f1645b = 0;
            this.f1646c = 0;
            this.f1647d = 51;
            this.f1644a = fPoint;
            this.f1645b = i3;
            this.f1646c = i4;
            this.f1647d = i5;
        }

        public C0302a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f1644a = null;
            this.f1645b = 0;
            this.f1646c = 0;
            this.f1647d = 51;
        }

        public C0302a(LayoutParams layoutParams) {
            super(layoutParams);
            this.f1644a = null;
            this.f1645b = 0;
            this.f1646c = 0;
            this.f1647d = 51;
        }
    }

    public az(Context context) {
        super(context);
    }

    public az(Context context, ab abVar) {
        super(context);
        this.f1414a = abVar;
        setBackgroundColor(-1);
    }

    private void m2073a(View view, int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 & 7;
        int i7 = i5 & Opcodes.IREM;
        if (i6 == 5) {
            i3 -= i;
        } else if (i6 == 1) {
            i3 -= i / 2;
        }
        if (i7 == 80) {
            i4 -= i2;
        } else if (i7 == 17) {
            i4 -= i2 / 2;
        } else if (i7 == 16) {
            i4 = (i4 / 2) - (i2 / 2);
        }
        view.layout(i3, i4, i3 + i, i4 + i2);
    }

    private void m2074a(View view, int i, int i2, int[] iArr) {
        if (view instanceof ListView) {
            View view2 = (View) view.getParent();
            if (view2 != null) {
                iArr[0] = view2.getWidth();
                iArr[1] = view2.getHeight();
            }
        }
        if (i <= 0 || i2 <= 0) {
            view.measure(0, 0);
        }
        if (i == -2) {
            iArr[0] = view.getMeasuredWidth();
        } else if (i == -1) {
            iArr[0] = getMeasuredWidth();
        } else {
            iArr[0] = i;
        }
        if (i2 == -2) {
            iArr[1] = view.getMeasuredHeight();
        } else if (i2 == -1) {
            iArr[1] = getMeasuredHeight();
        } else {
            iArr[1] = i2;
        }
    }

    private void m2075a(View view, LayoutParams layoutParams) {
        int[] iArr = new int[2];
        m2074a(view, layoutParams.width, layoutParams.height, iArr);
        if (view instanceof ar) {
            m2073a(view, iArr[0], iArr[1], 20, (this.f1414a.m2219I().y - 80) - iArr[1], 51);
            return;
        }
        m2073a(view, iArr[0], iArr[1], 0, 0, 51);
    }

    private void m2076a(View view, C0302a c0302a) {
        int[] iArr = new int[2];
        m2074a(view, c0302a.width, c0302a.height, iArr);
        if (view instanceof bs) {
            m2073a(view, iArr[0], iArr[1], getWidth() - iArr[0], getHeight(), c0302a.f1647d);
        } else if (view instanceof as) {
            m2073a(view, iArr[0], iArr[1], getWidth() - iArr[0], iArr[1], c0302a.f1647d);
        } else if (view instanceof C0328r) {
            m2073a(view, iArr[0], iArr[1], 0, 0, c0302a.f1647d);
        } else if (c0302a.f1644a != null) {
            IPoint iPoint = new IPoint();
            this.f1414a.m2289c().map2Win(c0302a.f1644a.f3693x, c0302a.f1644a.f3694y, iPoint);
            iPoint.f3714x += c0302a.f1645b;
            iPoint.f3715y += c0302a.f1646c;
            m2073a(view, iArr[0], iArr[1], iPoint.f3714x, iPoint.f3715y, c0302a.f1647d);
            if (view.getVisibility() == 0) {
                m2077a();
            }
        }
    }

    protected void m2077a() {
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null) {
                if (childAt.getLayoutParams() instanceof C0302a) {
                    m2076a(childAt, (C0302a) childAt.getLayoutParams());
                } else {
                    m2075a(childAt, childAt.getLayoutParams());
                }
            }
        }
    }
}
