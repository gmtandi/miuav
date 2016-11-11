package org.p004c.p005e.p006a;

import java.util.Comparator;
import org.p004c.p005e.C3507d;

/* renamed from: org.c.e.a.h */
public class C3491h implements Comparator<C3507d> {
    public static final C3491h f16016a;
    private final Comparator<C3507d> f16017b;

    static {
        f16016a = new C3491h(new C3492i());
    }

    public C3491h(Comparator<C3507d> comparator) {
        this.f16017b = comparator;
    }

    public int m19042a(C3507d c3507d, C3507d c3507d2) {
        return this.f16017b.compare(c3507d, c3507d2);
    }

    public void m19043a(Object obj) {
        if (obj instanceof C0129g) {
            ((C0129g) obj).m212a(this);
        }
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m19042a((C3507d) obj, (C3507d) obj2);
    }
}
