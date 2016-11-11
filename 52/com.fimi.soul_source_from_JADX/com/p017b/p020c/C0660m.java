package com.p017b.p020c;

import android.view.View;
import com.p017b.p018a.C0616a;
import com.p017b.p018a.C0620b;
import com.p017b.p018a.as;
import com.p017b.p018a.az;
import java.util.ArrayList;

/* renamed from: com.b.c.m */
class C0660m implements az, C0620b {
    final /* synthetic */ C0658k f4007a;

    private C0660m(C0658k c0658k) {
        this.f4007a = c0658k;
    }

    public void m5943a(C0616a c0616a) {
        if (this.f4007a.f4002j != null) {
            this.f4007a.f4002j.m5564a(c0616a);
        }
    }

    public void m5944b(C0616a c0616a) {
        if (this.f4007a.f4002j != null) {
            this.f4007a.f4002j.m5565b(c0616a);
        }
        this.f4007a.f4005y.remove(c0616a);
        if (this.f4007a.f4005y.isEmpty()) {
            this.f4007a.f4002j = null;
        }
    }

    public void m5945c(C0616a c0616a) {
        if (this.f4007a.f4002j != null) {
            this.f4007a.f4002j.m5566c(c0616a);
        }
    }

    public void m5946d(C0616a c0616a) {
        if (this.f4007a.f4002j != null) {
            this.f4007a.f4002j.m5567d(c0616a);
        }
    }

    public void onAnimationUpdate(as asVar) {
        float A = asVar.m5517A();
        C0662o c0662o = (C0662o) this.f4007a.f4005y.get(asVar);
        if ((c0662o.f4011a & 511) != 0) {
            View view = (View) this.f4007a.f3995c.get();
            if (view != null) {
                view.invalidate();
            }
        }
        ArrayList arrayList = c0662o.f4012b;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                C0661n c0661n = (C0661n) arrayList.get(i);
                this.f4007a.m5912c(c0661n.f4008a, c0661n.f4009b + (c0661n.f4010c * A));
            }
        }
        View view2 = (View) this.f4007a.f3995c.get();
        if (view2 != null) {
            view2.invalidate();
        }
    }
}
