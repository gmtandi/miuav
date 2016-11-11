package com.p017b.p020c;

import android.view.View;
import com.p017b.p018a.C0616a;
import com.p017b.p018a.C0620b;
import com.p017b.p018a.as;
import com.p017b.p018a.az;
import java.util.ArrayList;

/* renamed from: com.b.c.f */
class C0653f implements az, C0620b {
    final /* synthetic */ C0651d f3971a;

    private C0653f(C0651d c0651d) {
        this.f3971a = c0651d;
    }

    public void m5870a(C0616a c0616a) {
        if (this.f3971a.f3966i != null) {
            this.f3971a.f3966i.m5564a(c0616a);
        }
    }

    public void m5871b(C0616a c0616a) {
        if (this.f3971a.f3966i != null) {
            this.f3971a.f3966i.m5565b(c0616a);
        }
        this.f3971a.f3969x.remove(c0616a);
        if (this.f3971a.f3969x.isEmpty()) {
            this.f3971a.f3966i = null;
        }
    }

    public void m5872c(C0616a c0616a) {
        if (this.f3971a.f3966i != null) {
            this.f3971a.f3966i.m5566c(c0616a);
        }
    }

    public void m5873d(C0616a c0616a) {
        if (this.f3971a.f3966i != null) {
            this.f3971a.f3966i.m5567d(c0616a);
        }
    }

    public void onAnimationUpdate(as asVar) {
        float A = asVar.m5517A();
        C0655h c0655h = (C0655h) this.f3971a.f3969x.get(asVar);
        if ((c0655h.f3975a & 511) != 0) {
            View view = (View) this.f3971a.f3959b.get();
            if (view != null) {
                view.invalidate();
            }
        }
        ArrayList arrayList = c0655h.f3976b;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                C0654g c0654g = (C0654g) arrayList.get(i);
                this.f3971a.m5839c(c0654g.f3972a, c0654g.f3973b + (c0654g.f3974c * A));
            }
        }
        View view2 = (View) this.f3971a.f3959b.get();
        if (view2 != null) {
            view2.invalidate();
        }
    }
}
