package org.p004c.p198b.p200b;

import org.p184b.C3275p;
import org.p184b.C3295u;
import org.p184b.C3300k;
import org.p184b.C3315n;

/* renamed from: org.c.b.b.c */
public class C3396c<T extends Throwable> extends C3295u<T> {
    private final C3275p<String> f15892a;

    public C3396c(C3275p<String> c3275p) {
        this.f15892a = c3275p;
    }

    @C3315n
    public static <T extends Throwable> C3275p<T> m18649a(C3275p<String> c3275p) {
        return new C3396c(c3275p);
    }

    protected void m18650a(T t, C3300k c3300k) {
        c3300k.m18222a("message ");
        this.f15892a.m18106a(t.getMessage(), c3300k);
    }

    public void m18651a(C3300k c3300k) {
        c3300k.m18222a("exception with message ");
        c3300k.m18225a(this.f15892a);
    }

    protected boolean m18652a(T t) {
        return this.f15892a.m18107a(t.getMessage());
    }

    protected /* synthetic */ void m18653b(Object obj, C3300k c3300k) {
        m18650a((Throwable) obj, c3300k);
    }

    protected /* synthetic */ boolean m18654b(Object obj) {
        return m18652a((Throwable) obj);
    }
}
