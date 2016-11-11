package org.p004c.p188a.p195e;

import java.util.List;
import org.p004c.C3486d;
import org.p004c.p187f.C3375b;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p188a.p195e.p196a.C3361d;

/* renamed from: org.c.a.e.l */
class C3379l extends C3375b {
    final /* synthetic */ C3361d f15875a;
    final /* synthetic */ C3378k f15876b;

    C3379l(C3378k c3378k, Class cls, C3361d c3361d) {
        this.f15876b = c3378k;
        this.f15875a = c3361d;
        super(cls);
    }

    public C3377k m18602a(C3524d c3524d) {
        return new C3380m(this, super.m18557a(c3524d));
    }

    protected C3377k m18603a(C3524d c3524d, Object obj) {
        return this.f15876b.m18591a(c3524d, this.f15875a, obj);
    }

    protected void m18604a(List<Throwable> list) {
    }

    public Object m18605b() {
        Object[] d = this.f15875a.m18509d();
        if (!this.f15876b.m18594d()) {
            C3486d.m19031a(d);
        }
        return m18348g().m19223f().newInstance(d);
    }
}
