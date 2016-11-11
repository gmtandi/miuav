package org.p004c.p188a.p193c;

import java.util.List;
import org.p004c.p005e.C3515n;
import org.p004c.p005e.p007b.C0133b;
import org.p004c.p005e.p007b.C3493a;

/* renamed from: org.c.a.c.a */
class C3349a {
    private final List<C3493a> f15854a;

    public C3349a(List<C3493a> list) {
        this.f15854a = list;
    }

    public C3515n m18461a() {
        C3515n c3515n = new C3515n();
        C0133b g = c3515n.m19146g();
        for (C3493a a : this.f15854a) {
            try {
                g.m228a(a);
            } catch (Exception e) {
                throw new RuntimeException("I can't believe this happened");
            }
        }
        return c3515n;
    }
}
