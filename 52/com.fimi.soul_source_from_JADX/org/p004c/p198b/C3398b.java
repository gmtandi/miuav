package org.p004c.p198b;

import org.p184b.C3274r;
import org.p184b.C3275p;
import org.p184b.C3300k;
import org.p184b.C3318s;

/* renamed from: org.c.b.b */
public class C3398b extends RuntimeException implements C3274r {
    private static final long serialVersionUID = 2;
    private final String f15894a;
    private final boolean f15895b;
    private final Object f15896c;
    private final C3275p<?> f15897d;

    @Deprecated
    public C3398b(Object obj, C3275p<?> c3275p) {
        this(null, true, obj, c3275p);
    }

    @Deprecated
    public C3398b(String str) {
        this(str, false, null, null);
    }

    @Deprecated
    public C3398b(String str, Object obj, C3275p<?> c3275p) {
        this(str, true, obj, c3275p);
    }

    @Deprecated
    public C3398b(String str, Throwable th) {
        this(str, false, null, null);
        initCause(th);
    }

    @Deprecated
    public C3398b(String str, boolean z, Object obj, C3275p<?> c3275p) {
        this.f15894a = str;
        this.f15896c = obj;
        this.f15897d = c3275p;
        this.f15895b = z;
        if (obj instanceof Throwable) {
            initCause((Throwable) obj);
        }
    }

    public void m18659a(C3300k c3300k) {
        if (this.f15894a != null) {
            c3300k.m18222a(this.f15894a);
        }
        if (this.f15895b) {
            if (this.f15894a != null) {
                c3300k.m18222a(": ");
            }
            c3300k.m18222a("got: ");
            c3300k.m18221a(this.f15896c);
            if (this.f15897d != null) {
                c3300k.m18222a(", expected: ");
                c3300k.m18225a(this.f15897d);
            }
        }
    }

    public String getMessage() {
        return C3318s.m18313c(this);
    }
}
