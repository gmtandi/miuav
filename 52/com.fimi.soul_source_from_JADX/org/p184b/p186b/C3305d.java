package org.p184b.p186b;

import java.util.Iterator;
import org.p184b.C3274r;

/* renamed from: org.b.b.d */
public class C3305d<T> implements Iterator<C3274r> {
    private Iterator<T> f15815a;

    public C3305d(Iterator<T> it) {
        this.f15815a = it;
    }

    public C3274r m18244a() {
        return new C3304c(this.f15815a.next());
    }

    public boolean hasNext() {
        return this.f15815a.hasNext();
    }

    public /* synthetic */ Object next() {
        return m18244a();
    }

    public void remove() {
        this.f15815a.remove();
    }
}
