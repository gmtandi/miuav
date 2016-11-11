package org.p004c.p005e;

import org.p004c.p005e.p007b.C0133b;
import org.p004c.p005e.p007b.C3495d;
import org.p004c.p198b.C3450g;
import org.p004c.p198b.C3454k;
import org.p004c.p198b.C3455l;
import org.p004c.p198b.p202d.C3433e;
import p001b.p002b.C0115j;
import p001b.p008c.C0144c;

/* renamed from: org.c.e.k */
public class C3513k {
    private final C3495d f16055a;

    public C3513k() {
        this.f16055a = new C3495d();
    }

    public static C3515n m19121a(C3336a c3336a, Class<?>... clsArr) {
        return new C3513k().m19131b(c3336a, clsArr);
    }

    public static C3515n m19122a(Class<?>... clsArr) {
        return C3513k.m19121a(C3513k.m19124b(), (Class[]) clsArr);
    }

    public static void m19123a(String... strArr) {
        System.exit(new C3513k().m19127a(new C3454k(), strArr).m19145f() ? 0 : 1);
    }

    static C3336a m19124b() {
        return new C3336a();
    }

    public String m19125a() {
        return C0144c.m352a();
    }

    public C3515n m19126a(C0115j c0115j) {
        return m19129a(new C3433e(c0115j));
    }

    C3515n m19127a(C3450g c3450g, String... strArr) {
        c3450g.m18809a().println("JUnit version " + C0144c.m352a());
        C3511i a = C3511i.m19111a(strArr);
        m19130a(new C3455l(c3450g));
        return m19128a(a.m19117a(C3513k.m19124b()));
    }

    public C3515n m19128a(C3340l c3340l) {
        return m19129a(c3340l.m18438a());
    }

    public C3515n m19129a(C3319s c3319s) {
        C3515n c3515n = new C3515n();
        C0133b g = c3515n.m19146g();
        this.f16055a.m19063d(g);
        try {
            this.f16055a.m19056a(c3319s.m18317d());
            c3319s.m18316a(this.f16055a);
            this.f16055a.m19057a(c3515n);
            return c3515n;
        } finally {
            m19133b(g);
        }
    }

    public void m19130a(C0133b c0133b) {
        this.f16055a.m19055a(c0133b);
    }

    public C3515n m19131b(C3336a c3336a, Class<?>... clsArr) {
        return m19128a(C3340l.m18431a(c3336a, (Class[]) clsArr));
    }

    public C3515n m19132b(Class<?>... clsArr) {
        return m19131b(C3513k.m19124b(), clsArr);
    }

    public void m19133b(C0133b c0133b) {
        this.f16055a.m19059b(c0133b);
    }
}
