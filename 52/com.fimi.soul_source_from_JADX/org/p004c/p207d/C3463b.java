package org.p004c.p207d;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.p004c.p187f.p192a.C3404g;
import org.p122a.p123a.C2915a;
import org.p184b.C3275p;

/* renamed from: org.c.d.b */
public class C3463b extends C3462z {
    private List<Throwable> f15976a;

    public C3463b() {
        this.f15976a = new ArrayList();
    }

    public <T> T m18912a(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Throwable th) {
            m18916a(th);
            return null;
        }
    }

    protected void m18913a() {
        C3404g.m18670a(this.f15976a);
    }

    public <T> void m18914a(T t, C3275p<T> c3275p) {
        m18915a(C2915a.f14760f, t, c3275p);
    }

    public <T> void m18915a(String str, T t, C3275p<T> c3275p) {
        m18912a(new C3464c(this, str, t, c3275p));
    }

    public void m18916a(Throwable th) {
        this.f15976a.add(th);
    }
}
