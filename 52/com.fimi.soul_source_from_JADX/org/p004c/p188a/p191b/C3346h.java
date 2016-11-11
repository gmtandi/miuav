package org.p004c.p188a.p191b;

import java.util.Comparator;
import org.p004c.p005e.C3507d;

/* renamed from: org.c.a.b.h */
class C3346h implements Comparator<C3507d> {
    final /* synthetic */ C3343e f15852a;

    private C3346h(C3343e c3343e) {
        this.f15852a = c3343e;
    }

    private Long m18455a(C3507d c3507d) {
        Long a = this.f15852a.m18444a(c3507d);
        return a == null ? Long.valueOf(0) : a;
    }

    public int m18456a(C3507d c3507d, C3507d c3507d2) {
        if (this.f15852a.m18449b(c3507d)) {
            return -1;
        }
        if (this.f15852a.m18449b(c3507d2)) {
            return 1;
        }
        int compareTo = m18455a(c3507d2).compareTo(m18455a(c3507d));
        return compareTo == 0 ? this.f15852a.m18450c(c3507d).compareTo(this.f15852a.m18450c(c3507d2)) : compareTo;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m18456a((C3507d) obj, (C3507d) obj2);
    }
}
