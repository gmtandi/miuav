package org.p004c.p188a.p195e.p196a;

import org.p004c.C3486d;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p188a.p195e.C3359g;
import org.p004c.p188a.p195e.C3366a;
import org.p004c.p188a.p195e.C3374i;

/* renamed from: org.c.a.e.a.c */
class C3360c extends C3359g {
    private final C3524d f15860a;

    private C3360c(C3524d c3524d) {
        this.f15860a = c3524d;
    }

    public Object m18496a() {
        try {
            return this.f15860a.m19183a(null, new Object[0]);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("unexpected: argument length is checked");
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("unexpected: getMethods returned an inaccessible method");
        } catch (Throwable th) {
            Object obj = th;
            C3366a c3366a = (C3366a) this.f15860a.m19184a(C3366a.class);
            boolean z = c3366a == null || !C3357a.m18485b(c3366a.m18522b(), obj);
            C3486d.m19030a(z);
            C3374i c3374i = new C3374i(obj);
        }
    }

    public String m18497b() {
        return this.f15860a.m19190b();
    }
}
