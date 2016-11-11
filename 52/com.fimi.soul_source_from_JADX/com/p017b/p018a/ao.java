package com.p017b.p018a;

import android.util.Log;
import com.p017b.p019b.C0617d;
import com.p017b.p019b.C0618b;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.b.a.ao */
class ao extends al {
    C0635p f3792h;
    int f3793i;
    private C0618b f3794j;

    public ao(C0617d c0617d, C0635p c0635p) {
        super(null);
        this.d = Integer.TYPE;
        this.e = c0635p;
        this.f3792h = (C0635p) this.e;
        if (c0617d instanceof C0618b) {
            this.f3794j = (C0618b) this.b;
        }
    }

    public ao(C0617d c0617d, int... iArr) {
        super(null);
        m5490a(iArr);
        if (c0617d instanceof C0618b) {
            this.f3794j = (C0618b) this.b;
        }
    }

    public ao(String str, C0635p c0635p) {
        super(null);
        this.d = Integer.TYPE;
        this.e = c0635p;
        this.f3792h = (C0635p) this.e;
    }

    public ao(String str, int... iArr) {
        super(null);
        m5490a(iArr);
    }

    public /* synthetic */ al m5487a() {
        return m5493e();
    }

    void m5488a(float f) {
        this.f3793i = this.f3792h.m5640b(f);
    }

    void m5489a(Class cls) {
        if (this.b == null) {
            super.m5467a(cls);
        }
    }

    public void m5490a(int... iArr) {
        super.m5471a(iArr);
        this.f3792h = (C0635p) this.e;
    }

    public /* synthetic */ Object clone() {
        return m5493e();
    }

    Object m5491d() {
        return Integer.valueOf(this.f3793i);
    }

    void m5492d(Object obj) {
        if (this.f3794j != null) {
            this.f3794j.m5400a(obj, this.f3793i);
        } else if (this.b != null) {
            this.b.m5396a(obj, Integer.valueOf(this.f3793i));
        } else if (this.c != null) {
            try {
                this.g[0] = Integer.valueOf(this.f3793i);
                this.c.invoke(obj, this.g);
            } catch (InvocationTargetException e) {
                Log.e("PropertyValuesHolder", e.toString());
            } catch (IllegalAccessException e2) {
                Log.e("PropertyValuesHolder", e2.toString());
            }
        }
    }

    public ao m5493e() {
        ao aoVar = (ao) super.m5463a();
        aoVar.f3792h = (C0635p) aoVar.e;
        return aoVar;
    }
}
