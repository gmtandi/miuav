package org.p004c.p188a.p195e;

import java.util.ArrayList;
import java.util.List;
import org.p004c.C3459c;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p187f.p192a.C3528l;
import org.p004c.p188a.p195e.p196a.C3361d;
import org.p004c.p188a.p195e.p196a.C3364g;
import org.p004c.p198b.C3398b;

/* renamed from: org.c.a.e.k */
public class C3378k extends C3377k {
    private int f15871a;
    private final C3524d f15872b;
    private final C3528l f15873c;
    private List<C3398b> f15874d;

    public C3378k(C3524d c3524d, C3528l c3528l) {
        this.f15871a = 0;
        this.f15874d = new ArrayList();
        this.f15872b = c3524d;
        this.f15873c = c3528l;
    }

    private C3377k m18591a(C3524d c3524d, C3361d c3361d, Object obj) {
        return new C3381n(this, c3361d, c3524d, obj);
    }

    private C3528l m18593c() {
        return this.f15873c;
    }

    private boolean m18594d() {
        C3382o c3382o = (C3382o) this.f15872b.m19194d().getAnnotation(C3382o.class);
        return c3382o == null ? false : c3382o.m18608a();
    }

    public void m18595a() {
        m18597a(C3361d.m18499a(this.f15872b.m19194d(), m18593c()));
        Object obj = this.f15872b.m19184a(C3382o.class) != null ? 1 : null;
        if (this.f15871a == 0 && obj != null) {
            C3459c.m18848a("Never found parameters that satisfied method assumptions.  Violated assumptions: " + this.f15874d);
        }
    }

    protected void m18596a(Throwable th, Object... objArr) {
        if (objArr.length == 0) {
            throw th;
        }
        throw new C3364g(th, this.f15872b.m19190b(), objArr);
    }

    protected void m18597a(C3361d c3361d) {
        if (c3361d.m18504a()) {
            m18601c(c3361d);
        } else {
            m18600b(c3361d);
        }
    }

    protected void m18598a(C3398b c3398b) {
        this.f15874d.add(c3398b);
    }

    protected void m18599b() {
        this.f15871a++;
    }

    protected void m18600b(C3361d c3361d) {
        for (C3359g a : c3361d.m18508c()) {
            m18597a(c3361d.m18503a(a));
        }
    }

    protected void m18601c(C3361d c3361d) {
        new C3379l(this, m18593c().m19221d(), c3361d).m18602a(this.f15872b).m18589a();
    }
}
