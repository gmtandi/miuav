package it.sephiroth.android.library.widget;

import android.view.View;
import com.fimi.kernel.p084e.C1186y;
import org.codehaus.jackson.org.objectweb.asm.Type;
import org.codehaus.jackson.smile.SmileConstants;

/* renamed from: it.sephiroth.android.library.widget.g */
class C2828g implements Runnable {
    private static final int f14538e = 40;
    final /* synthetic */ AbsHListView f14539a;
    private final as f14540b;
    private int f14541c;
    private final Runnable f14542d;

    C2828g(AbsHListView absHListView) {
        this.f14539a = absHListView;
        this.f14542d = new C2829h(this);
        this.f14540b = new as(absHListView.getContext());
    }

    void m16375a() {
        if (this.f14540b.m16323a(this.f14539a.getScrollX(), 0, 0, 0, 0, 0)) {
            this.f14539a.f14384Z = 6;
            this.f14539a.invalidate();
            this.f14539a.f14385s.m16044a((Runnable) this);
            return;
        }
        this.f14539a.f14384Z = -1;
        this.f14539a.m16135b(0);
    }

    void m16376a(int i) {
        int i2 = i < 0 ? Integer.MAX_VALUE : 0;
        this.f14541c = i2;
        this.f14540b.m16319a(null);
        this.f14540b.m16317a(i2, 0, i, 0, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE);
        this.f14539a.f14384Z = 4;
        this.f14539a.f14385s.m16044a((Runnable) this);
    }

    void m16377a(int i, int i2, boolean z) {
        int i3 = i < 0 ? Integer.MAX_VALUE : 0;
        this.f14541c = i3;
        this.f14540b.m16319a(z ? AbsHListView.ap : null);
        this.f14540b.m16316a(i3, 0, i, 0, i2);
        this.f14539a.f14384Z = 4;
        this.f14539a.f14385s.m16044a((Runnable) this);
    }

    void m16378b() {
        this.f14539a.f14384Z = -1;
        this.f14539a.removeCallbacks(this);
        this.f14539a.removeCallbacks(this.f14542d);
        this.f14539a.m16135b(0);
        this.f14539a.m16087J();
        this.f14540b.m16338l();
        this.f14539a.overScrollBy(0, 0, 0, 0, 0, 0, 0, 0, false);
    }

    void m16379b(int i) {
        this.f14540b.m16319a(null);
        this.f14540b.m16318a(this.f14539a.getScrollX(), 0, i, 0, C1186y.f5353a, Integer.MAX_VALUE, 0, 0, this.f14539a.getWidth(), 0);
        this.f14539a.f14384Z = 6;
        this.f14539a.invalidate();
        this.f14539a.f14385s.m16044a((Runnable) this);
    }

    void m16380c() {
        this.f14539a.postDelayed(this.f14542d, 40);
    }

    void m16381c(int i) {
        this.f14540b.m16314a(this.f14539a.getScrollX(), 0, this.f14539a.an);
        int overScrollMode = this.f14539a.getOverScrollMode();
        if (overScrollMode == 0 || (overScrollMode == 1 && !this.f14539a.m16081D())) {
            this.f14539a.f14384Z = 6;
            overScrollMode = (int) this.f14540b.m16330d();
            if (i > 0) {
                this.f14539a.by.m16275a(overScrollMode);
            } else {
                this.f14539a.bz.m16275a(overScrollMode);
            }
        } else {
            this.f14539a.f14384Z = -1;
            if (this.f14539a.ac != null) {
                this.f14539a.ac.m16384a();
            }
        }
        this.f14539a.invalidate();
        this.f14539a.f14385s.m16044a((Runnable) this);
    }

    public void run() {
        int scrollX;
        int i;
        int i2 = 1;
        int i3 = 0;
        switch (this.f14539a.f14384Z) {
            case Type.BYTE /*3*/:
                if (this.f14540b.m16321a()) {
                    return;
                }
                break;
            case SmileConstants.HEADER_BIT_HAS_RAW_BINARY /*4*/:
                break;
            case Type.FLOAT /*6*/:
                as asVar = this.f14540b;
                if (asVar.m16336j()) {
                    scrollX = this.f14539a.getScrollX();
                    int b = asVar.m16324b();
                    if (this.f14539a.overScrollBy(b - scrollX, 0, scrollX, 0, 0, 0, this.f14539a.an, 0, false)) {
                        i = (scrollX > 0 || b <= 0) ? 0 : 1;
                        if (scrollX >= 0 && b < 0) {
                            i3 = 1;
                        }
                        if (i == 0 && i3 == 0) {
                            m16375a();
                            return;
                        }
                        i = (int) asVar.m16330d();
                        if (i3 != 0) {
                            i = -i;
                        }
                        asVar.m16338l();
                        m16376a(i);
                        return;
                    }
                    this.f14539a.invalidate();
                    this.f14539a.f14385s.m16044a((Runnable) this);
                    return;
                }
                m16378b();
                return;
            default:
                m16378b();
                return;
        }
        if (this.f14539a.aJ) {
            this.f14539a.m16153h();
        }
        if (this.f14539a.aO == 0 || this.f14539a.getChildCount() == 0) {
            m16378b();
            return;
        }
        int min;
        as asVar2 = this.f14540b;
        boolean j = asVar2.m16336j();
        int b2 = asVar2.m16324b();
        i = this.f14541c - b2;
        if (i > 0) {
            this.f14539a.f14379S = this.f14539a.av;
            this.f14539a.f14380T = this.f14539a.getChildAt(0).getLeft();
            min = Math.min(((this.f14539a.getWidth() - this.f14539a.getPaddingRight()) - this.f14539a.getPaddingLeft()) - 1, i);
        } else {
            scrollX = this.f14539a.getChildCount() - 1;
            this.f14539a.f14379S = this.f14539a.av + scrollX;
            this.f14539a.f14380T = this.f14539a.getChildAt(scrollX).getLeft();
            min = Math.max(-(((this.f14539a.getWidth() - this.f14539a.getPaddingRight()) - this.f14539a.getPaddingLeft()) - 1), i);
        }
        View childAt = this.f14539a.getChildAt(this.f14539a.f14379S - this.f14539a.av);
        i = childAt != null ? childAt.getLeft() : 0;
        boolean g = this.f14539a.m16152g(min, min);
        if (!g || min == 0) {
            i2 = 0;
        }
        if (i2 != 0) {
            if (childAt != null) {
                this.f14539a.overScrollBy(-(min - (childAt.getLeft() - i)), 0, this.f14539a.getScrollX(), 0, 0, 0, this.f14539a.an, 0, false);
            }
            if (j) {
                m16381c(min);
            }
        } else if (j && i2 == 0) {
            if (g) {
                this.f14539a.invalidate();
            }
            this.f14541c = b2;
            this.f14539a.f14385s.m16044a((Runnable) this);
        } else {
            m16378b();
        }
    }
}
