package com.p017b.p018a;

import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;

/* renamed from: com.b.a.p */
class C0635p extends C0632u {
    private int f3891g;
    private int f3892h;
    private int f3893i;
    private boolean f3894j;

    public C0635p(C0638s... c0638sArr) {
        super(c0638sArr);
        this.f3894j = true;
    }

    public C0635p m5638a() {
        ArrayList arrayList = this.e;
        int size = this.e.size();
        C0638s[] c0638sArr = new C0638s[size];
        for (int i = 0; i < size; i++) {
            c0638sArr[i] = (C0638s) ((C0636q) arrayList.get(i)).m5656f();
        }
        return new C0635p(c0638sArr);
    }

    public Object m5639a(float f) {
        return Integer.valueOf(m5640b(f));
    }

    public int m5640b(float f) {
        int i = 1;
        if (this.a == 2) {
            if (this.f3894j) {
                this.f3894j = false;
                this.f3891g = ((C0638s) this.e.get(0)).m5665g();
                this.f3892h = ((C0638s) this.e.get(1)).m5665g();
                this.f3893i = this.f3892h - this.f3891g;
            }
            if (this.d != null) {
                f = this.d.getInterpolation(f);
            }
            return this.f == null ? this.f3891g + ((int) (((float) this.f3893i) * f)) : ((Number) this.f.m5558a(f, Integer.valueOf(this.f3891g), Integer.valueOf(this.f3892h))).intValue();
        } else if (f <= 0.0f) {
            r0 = (C0638s) this.e.get(0);
            r1 = (C0638s) this.e.get(1);
            r2 = r0.m5665g();
            r3 = r1.m5665g();
            r0 = r0.m5652c();
            r4 = r1.m5652c();
            r1 = r1.m5653d();
            if (r1 != null) {
                f = r1.getInterpolation(f);
            }
            r0 = (f - r0) / (r4 - r0);
            return this.f == null ? ((int) (r0 * ((float) (r3 - r2)))) + r2 : ((Number) this.f.m5558a(r0, Integer.valueOf(r2), Integer.valueOf(r3))).intValue();
        } else if (f >= C2020f.f10933c) {
            r0 = (C0638s) this.e.get(this.a - 2);
            r1 = (C0638s) this.e.get(this.a - 1);
            r2 = r0.m5665g();
            r3 = r1.m5665g();
            r0 = r0.m5652c();
            r4 = r1.m5652c();
            r1 = r1.m5653d();
            if (r1 != null) {
                f = r1.getInterpolation(f);
            }
            r0 = (f - r0) / (r4 - r0);
            return this.f == null ? ((int) (r0 * ((float) (r3 - r2)))) + r2 : ((Number) this.f.m5558a(r0, Integer.valueOf(r2), Integer.valueOf(r3))).intValue();
        } else {
            C0638s c0638s = (C0638s) this.e.get(0);
            while (i < this.a) {
                r0 = (C0638s) this.e.get(i);
                if (f < r0.m5652c()) {
                    r1 = r0.m5653d();
                    if (r1 != null) {
                        f = r1.getInterpolation(f);
                    }
                    float c = (f - c0638s.m5652c()) / (r0.m5652c() - c0638s.m5652c());
                    r2 = c0638s.m5665g();
                    int g = r0.m5665g();
                    return this.f == null ? ((int) (((float) (g - r2)) * c)) + r2 : ((Number) this.f.m5558a(c, Integer.valueOf(r2), Integer.valueOf(g))).intValue();
                } else {
                    i++;
                    c0638s = r0;
                }
            }
            return ((Number) ((C0636q) this.e.get(this.a - 1)).m5651b()).intValue();
        }
    }

    public /* synthetic */ C0632u m5641b() {
        return m5638a();
    }

    public /* synthetic */ Object clone() {
        return m5638a();
    }
}
