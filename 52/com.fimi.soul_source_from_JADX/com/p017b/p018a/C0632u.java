package com.p017b.p018a;

import android.view.animation.Interpolator;
import com.fimi.soul.view.photodraweeview.C2020f;
import java.util.ArrayList;
import java.util.Arrays;
import org.codehaus.jackson.util.MinimalPrettyPrinter;

/* renamed from: com.b.a.u */
class C0632u {
    int f3881a;
    C0636q f3882b;
    C0636q f3883c;
    Interpolator f3884d;
    ArrayList<C0636q> f3885e;
    ar f3886f;

    public C0632u(C0636q... c0636qArr) {
        this.f3881a = c0636qArr.length;
        this.f3885e = new ArrayList();
        this.f3885e.addAll(Arrays.asList(c0636qArr));
        this.f3882b = (C0636q) this.f3885e.get(0);
        this.f3883c = (C0636q) this.f3885e.get(this.f3881a - 1);
        this.f3884d = this.f3883c.m5653d();
    }

    public static C0632u m5625a(float... fArr) {
        int i = 1;
        int length = fArr.length;
        C0637r[] c0637rArr = new C0637r[Math.max(length, 2)];
        if (length == 1) {
            c0637rArr[0] = (C0637r) C0636q.m5646b(0.0f);
            c0637rArr[1] = (C0637r) C0636q.m5643a((float) C2020f.f10933c, fArr[0]);
        } else {
            c0637rArr[0] = (C0637r) C0636q.m5643a(0.0f, fArr[0]);
            while (i < length) {
                c0637rArr[i] = (C0637r) C0636q.m5643a(((float) i) / ((float) (length - 1)), fArr[i]);
                i++;
            }
        }
        return new C0633n(c0637rArr);
    }

    public static C0632u m5626a(int... iArr) {
        int i = 1;
        int length = iArr.length;
        C0638s[] c0638sArr = new C0638s[Math.max(length, 2)];
        if (length == 1) {
            c0638sArr[0] = (C0638s) C0636q.m5642a(0.0f);
            c0638sArr[1] = (C0638s) C0636q.m5644a((float) C2020f.f10933c, iArr[0]);
        } else {
            c0638sArr[0] = (C0638s) C0636q.m5644a(0.0f, iArr[0]);
            while (i < length) {
                c0638sArr[i] = (C0638s) C0636q.m5644a(((float) i) / ((float) (length - 1)), iArr[i]);
                i++;
            }
        }
        return new C0635p(c0638sArr);
    }

    public static C0632u m5627a(C0636q... c0636qArr) {
        int i = 0;
        int length = c0636qArr.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            if (c0636qArr[i5] instanceof C0637r) {
                i4 = 1;
            } else if (c0636qArr[i5] instanceof C0638s) {
                i3 = 1;
            } else {
                i2 = 1;
            }
        }
        if (i4 != 0 && i3 == 0 && r0 == 0) {
            C0637r[] c0637rArr = new C0637r[length];
            while (i < length) {
                c0637rArr[i] = (C0637r) c0636qArr[i];
                i++;
            }
            return new C0633n(c0637rArr);
        } else if (i3 == 0 || i4 != 0 || r0 != 0) {
            return new C0632u(c0636qArr);
        } else {
            C0638s[] c0638sArr = new C0638s[length];
            for (int i6 = 0; i6 < length; i6++) {
                c0638sArr[i6] = (C0638s) c0636qArr[i6];
            }
            return new C0635p(c0638sArr);
        }
    }

    public static C0632u m5628a(Object... objArr) {
        int i = 1;
        int length = objArr.length;
        C0636q[] c0636qArr = new C0639t[Math.max(length, 2)];
        if (length == 1) {
            c0636qArr[0] = (C0639t) C0636q.m5647c(0.0f);
            c0636qArr[1] = (C0639t) C0636q.m5645a((float) C2020f.f10933c, objArr[0]);
        } else {
            c0636qArr[0] = (C0639t) C0636q.m5645a(0.0f, objArr[0]);
            while (i < length) {
                c0636qArr[i] = (C0639t) C0636q.m5645a(((float) i) / ((float) (length - 1)), objArr[i]);
                i++;
            }
        }
        return new C0632u(c0636qArr);
    }

    public Object m5629a(float f) {
        if (this.f3881a == 2) {
            if (this.f3884d != null) {
                f = this.f3884d.getInterpolation(f);
            }
            return this.f3886f.m5558a(f, this.f3882b.m5651b(), this.f3883c.m5651b());
        } else if (f <= 0.0f) {
            r0 = (C0636q) this.f3885e.get(1);
            r1 = r0.m5653d();
            if (r1 != null) {
                f = r1.getInterpolation(f);
            }
            r1 = this.f3882b.m5652c();
            return this.f3886f.m5558a((f - r1) / (r0.m5652c() - r1), this.f3882b.m5651b(), r0.m5651b());
        } else if (f >= C2020f.f10933c) {
            r0 = (C0636q) this.f3885e.get(this.f3881a - 2);
            r1 = this.f3883c.m5653d();
            if (r1 != null) {
                f = r1.getInterpolation(f);
            }
            r1 = r0.m5652c();
            return this.f3886f.m5558a((f - r1) / (this.f3883c.m5652c() - r1), r0.m5651b(), this.f3883c.m5651b());
        } else {
            C0636q c0636q = this.f3882b;
            int i = 1;
            while (i < this.f3881a) {
                r0 = (C0636q) this.f3885e.get(i);
                if (f < r0.m5652c()) {
                    r1 = r0.m5653d();
                    if (r1 != null) {
                        f = r1.getInterpolation(f);
                    }
                    r1 = c0636q.m5652c();
                    return this.f3886f.m5558a((f - r1) / (r0.m5652c() - r1), c0636q.m5651b(), r0.m5651b());
                }
                i++;
                c0636q = r0;
            }
            return this.f3883c.m5651b();
        }
    }

    public void m5630a(ar arVar) {
        this.f3886f = arVar;
    }

    public C0632u m5631b() {
        ArrayList arrayList = this.f3885e;
        int size = this.f3885e.size();
        C0636q[] c0636qArr = new C0636q[size];
        for (int i = 0; i < size; i++) {
            c0636qArr[i] = ((C0636q) arrayList.get(i)).m5656f();
        }
        return new C0632u(c0636qArr);
    }

    public /* synthetic */ Object clone() {
        return m5631b();
    }

    public String toString() {
        String str = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        int i = 0;
        while (i < this.f3881a) {
            String str2 = str + ((C0636q) this.f3885e.get(i)).m5651b() + "  ";
            i++;
            str = str2;
        }
        return str;
    }
}
