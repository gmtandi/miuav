package org.p004c.p187f;

import java.util.Comparator;
import org.p004c.p005e.p006a.C3491h;

/* renamed from: org.c.f.n */
class C3548n implements Comparator<T> {
    final /* synthetic */ C3491h f16103a;
    final /* synthetic */ C3320j f16104b;

    C3548n(C3320j c3320j, C3491h c3491h) {
        this.f16104b = c3320j;
        this.f16103a = c3491h;
    }

    public int compare(T t, T t2) {
        return this.f16103a.m19042a(this.f16104b.m18345d((Object) t), this.f16104b.m18345d((Object) t2));
    }
}
