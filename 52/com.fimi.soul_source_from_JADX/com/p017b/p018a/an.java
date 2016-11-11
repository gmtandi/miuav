package com.p017b.p018a;

import android.util.Log;
import com.p017b.p019b.C0617d;
import com.p017b.p019b.C0619a;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.b.a.an */
class an extends al {
    C0633n f3789h;
    float f3790i;
    private C0619a f3791j;

    public an(C0617d c0617d, C0633n c0633n) {
        super(null);
        this.d = Float.TYPE;
        this.e = c0633n;
        this.f3789h = (C0633n) this.e;
        if (c0617d instanceof C0619a) {
            this.f3791j = (C0619a) this.b;
        }
    }

    public an(C0617d c0617d, float... fArr) {
        super(null);
        m5483a(fArr);
        if (c0617d instanceof C0619a) {
            this.f3791j = (C0619a) this.b;
        }
    }

    public an(String str, C0633n c0633n) {
        super(null);
        this.d = Float.TYPE;
        this.e = c0633n;
        this.f3789h = (C0633n) this.e;
    }

    public an(String str, float... fArr) {
        super(null);
        m5483a(fArr);
    }

    public /* synthetic */ al m5480a() {
        return m5486e();
    }

    void m5481a(float f) {
        this.f3790i = this.f3789h.m5634b(f);
    }

    void m5482a(Class cls) {
        if (this.b == null) {
            super.m5467a(cls);
        }
    }

    public void m5483a(float... fArr) {
        super.m5470a(fArr);
        this.f3789h = (C0633n) this.e;
    }

    public /* synthetic */ Object clone() {
        return m5486e();
    }

    Object m5484d() {
        return Float.valueOf(this.f3790i);
    }

    void m5485d(Object obj) {
        if (this.f3791j != null) {
            this.f3791j.m5407a(obj, this.f3790i);
        } else if (this.b != null) {
            this.b.m5396a(obj, Float.valueOf(this.f3790i));
        } else if (this.c != null) {
            try {
                this.g[0] = Float.valueOf(this.f3790i);
                this.c.invoke(obj, this.g);
            } catch (InvocationTargetException e) {
                Log.e("PropertyValuesHolder", e.toString());
            } catch (IllegalAccessException e2) {
                Log.e("PropertyValuesHolder", e2.toString());
            }
        }
    }

    public an m5486e() {
        an anVar = (an) super.m5463a();
        anVar.f3789h = (C0633n) anVar.e;
        return anVar;
    }
}
