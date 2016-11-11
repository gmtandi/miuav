package org.p184b.p185a;

import org.codehaus.jackson.util.MinimalPrettyPrinter;
import org.p184b.C3275p;
import org.p184b.C3276b;
import org.p184b.C3300k;

/* renamed from: org.b.a.p */
abstract class C3279p<T> extends C3276b<T> {
    private final Iterable<C3275p<? super T>> f15786a;

    public C3279p(Iterable<C3275p<? super T>> iterable) {
        this.f15786a = iterable;
    }

    public abstract void m18122a(C3300k c3300k);

    public void m18123a(C3300k c3300k, String str) {
        c3300k.m18226b("(", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, ")", this.f15786a);
    }

    public abstract boolean m18124a(Object obj);

    protected boolean m18125a(Object obj, boolean z) {
        for (C3275p a : this.f15786a) {
            if (a.m18107a(obj) == z) {
                return z;
            }
        }
        return !z;
    }
}
