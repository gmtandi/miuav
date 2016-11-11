package org.p004c.p005e.p007b;

import java.util.ArrayList;
import java.util.List;
import org.p004c.p005e.C3507d;

/* renamed from: org.c.e.b.l */
abstract class C3496l {
    private final List<C0133b> f16022a;
    final /* synthetic */ C3495d f16023c;

    C3496l(C3495d c3495d) {
        this(c3495d, c3495d.f16020a);
    }

    C3496l(C3495d c3495d, List<C0133b> list) {
        this.f16023c = c3495d;
        this.f16022a = list;
    }

    void m19065a() {
        int size = this.f16022a.size();
        List arrayList = new ArrayList(size);
        List arrayList2 = new ArrayList(size);
        for (C0133b c0133b : this.f16022a) {
            try {
                m19066a(c0133b);
                arrayList.add(c0133b);
            } catch (Throwable e) {
                arrayList2.add(new C3493a(C3507d.f16043b, e));
            }
        }
        this.f16023c.m19051a(arrayList, arrayList2);
    }

    protected abstract void m19066a(C0133b c0133b);
}
