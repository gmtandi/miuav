package com.p017b.p018a;

import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;

/* renamed from: com.b.a.n */
class C0633n extends C0632u {
    private float f3887g;
    private float f3888h;
    private float f3889i;
    private boolean f3890j;

    public C0633n(C0637r... c0637rArr) {
        super(c0637rArr);
        this.f3890j = true;
    }

    public C0633n m5632a() {
        ArrayList arrayList = this.e;
        int size = this.e.size();
        C0637r[] c0637rArr = new C0637r[size];
        for (int i = 0; i < size; i++) {
            c0637rArr[i] = (C0637r) ((C0636q) arrayList.get(i)).m5656f();
        }
        return new C0633n(c0637rArr);
    }

    public Object m5633a(float f) {
        return Float.valueOf(m5634b(f));
    }

    public float m5634b(float f) {
        int i = 1;
        if (this.a == 2) {
            if (this.f3890j) {
                this.f3890j = false;
                this.f3887g = ((C0637r) this.e.get(0)).m5660g();
                this.f3888h = ((C0637r) this.e.get(1)).m5660g();
                this.f3889i = this.f3888h - this.f3887g;
            }
            if (this.d != null) {
                f = this.d.getInterpolation(f);
            }
            return this.f == null ? this.f3887g + (this.f3889i * f) : ((Number) this.f.m5558a(f, Float.valueOf(this.f3887g), Float.valueOf(this.f3888h))).floatValue();
        } else if (f <= 0.0f) {
            r0 = (C0637r) this.e.get(0);
            r1 = (C0637r) this.e.get(1);
            r2 = r0.m5660g();
            r3 = r1.m5660g();
            r0 = r0.m5652c();
            r4 = r1.m5652c();
            r1 = r1.m5653d();
            if (r1 != null) {
                f = r1.getInterpolation(f);
            }
            r0 = (f - r0) / (r4 - r0);
            return this.f == null ? (r0 * (r3 - r2)) + r2 : ((Number) this.f.m5558a(r0, Float.valueOf(r2), Float.valueOf(r3))).floatValue();
        } else if (f >= C2020f.f10933c) {
            r0 = (C0637r) this.e.get(this.a - 2);
            r1 = (C0637r) this.e.get(this.a - 1);
            r2 = r0.m5660g();
            r3 = r1.m5660g();
            r0 = r0.m5652c();
            r4 = r1.m5652c();
            r1 = r1.m5653d();
            if (r1 != null) {
                f = r1.getInterpolation(f);
            }
            r0 = (f - r0) / (r4 - r0);
            return this.f == null ? (r0 * (r3 - r2)) + r2 : ((Number) this.f.m5558a(r0, Float.valueOf(r2), Float.valueOf(r3))).floatValue();
        } else {
            C0637r c0637r = (C0637r) this.e.get(0);
            while (i < this.a) {
                r0 = (C0637r) this.e.get(i);
                if (f < r0.m5652c()) {
                    r1 = r0.m5653d();
                    if (r1 != null) {
                        f = r1.getInterpolation(f);
                    }
                    float c = (f - c0637r.m5652c()) / (r0.m5652c() - c0637r.m5652c());
                    r2 = c0637r.m5660g();
                    r0 = r0.m5660g();
                    return this.f == null ? ((r0 - r2) * c) + r2 : ((Number) this.f.m5558a(c, Float.valueOf(r2), Float.valueOf(r0))).floatValue();
                } else {
                    i++;
                    c0637r = r0;
                }
            }
            return ((Number) ((C0636q) this.e.get(this.a - 1)).m5651b()).floatValue();
        }
    }

    public /* synthetic */ C0632u m5635b() {
        return m5632a();
    }

    public /* synthetic */ Object clone() {
        return m5632a();
    }
}
