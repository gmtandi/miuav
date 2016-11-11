package org.p004c.p198b.p202d;

import java.lang.reflect.Method;
import java.util.List;
import org.p004c.C3383a;
import org.p004c.C3550f;
import org.p004c.C3568n;
import org.p004c.C3570p;
import org.p004c.C3571q;

@Deprecated
/* renamed from: org.c.b.d.r */
public class C3446r {
    private final Method f15966a;
    private C3445q f15967b;

    public C3446r(Method method, C3445q c3445q) {
        this.f15966a = method;
        this.f15967b = c3445q;
    }

    public void m18795a(Object obj) {
        this.f15966a.invoke(obj, new Object[0]);
    }

    public boolean m18796a() {
        return this.f15966a.getAnnotation(C3568n.class) != null;
    }

    boolean m18797a(Throwable th) {
        return !m18799c().isAssignableFrom(th.getClass());
    }

    public long m18798b() {
        C3570p c3570p = (C3570p) this.f15966a.getAnnotation(C3570p.class);
        return c3570p == null ? 0 : c3570p.m19301b();
    }

    protected Class<? extends Throwable> m18799c() {
        C3570p c3570p = (C3570p) this.f15966a.getAnnotation(C3570p.class);
        return (c3570p == null || c3570p.m19300a() == C3571q.class) ? null : c3570p.m19300a();
    }

    boolean m18800d() {
        return m18799c() != null;
    }

    List<Method> m18801e() {
        return this.f15967b.m18789a(C3550f.class);
    }

    List<Method> m18802f() {
        return this.f15967b.m18789a(C3383a.class);
    }
}
