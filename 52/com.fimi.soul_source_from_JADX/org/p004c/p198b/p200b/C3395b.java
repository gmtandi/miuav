package org.p004c.p198b.p200b;

import org.p184b.C3275p;
import org.p184b.C3295u;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.c.b.b.b */
public class C3395b<T extends Throwable> extends C3295u<T> {
    private final C3275p<? extends Throwable> f15891a;

    public C3395b(C3275p<? extends Throwable> c3275p) {
        this.f15891a = c3275p;
    }

    @C3315n
    public static <T extends Throwable> C3275p<T> m18643a(C3275p<? extends Throwable> c3275p) {
        return new C3395b(c3275p);
    }

    protected void m18644a(T t, C3300k c3300k) {
        c3300k.m18222a("cause ");
        this.f15891a.m18106a(t.getCause(), c3300k);
    }

    public void m18645a(C3300k c3300k) {
        c3300k.m18222a("exception with cause ");
        c3300k.m18225a(this.f15891a);
    }

    protected boolean m18646a(T t) {
        return this.f15891a.m18107a(t.getCause());
    }

    protected /* synthetic */ void m18647b(Object obj, C3300k c3300k) {
        m18644a((Throwable) obj, c3300k);
    }

    protected /* synthetic */ boolean m18648b(Object obj) {
        return m18646a((Throwable) obj);
    }
}
