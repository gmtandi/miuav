package org.p122a.p123a.p171m;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3235b;

@C2913c
/* renamed from: org.a.a.m.m */
abstract class C3215m<T, C, E extends C3108i<T, C>> {
    private final T f15668a;
    private final Set<E> f15669b;
    private final LinkedList<E> f15670c;
    private final LinkedList<C3217k<E>> f15671d;

    C3215m(T t) {
        this.f15668a = t;
        this.f15669b = new HashSet();
        this.f15670c = new LinkedList();
        this.f15671d = new LinkedList();
    }

    public final T m17829a() {
        return this.f15668a;
    }

    protected abstract E m17830a(C c);

    public void m17831a(E e, boolean z) {
        C3234a.m17886a((Object) e, "Pool entry");
        C3235b.m17896a(this.f15669b.remove(e), "Entry %s has not been leased from this pool", e);
        if (z) {
            this.f15670c.addFirst(e);
        }
    }

    public void m17832a(C3217k<E> c3217k) {
        if (c3217k != null) {
            this.f15671d.add(c3217k);
        }
    }

    public boolean m17833a(E e) {
        C3234a.m17886a((Object) e, "Pool entry");
        return this.f15670c.remove(e) || this.f15669b.remove(e);
    }

    public int m17834b() {
        return this.f15669b.size();
    }

    public E m17835b(Object obj) {
        if (!this.f15670c.isEmpty()) {
            Iterator it;
            C3108i c3108i;
            if (obj != null) {
                it = this.f15670c.iterator();
                while (it.hasNext()) {
                    c3108i = (C3108i) it.next();
                    if (obj.equals(c3108i.m17543l())) {
                        it.remove();
                        this.f15669b.add(c3108i);
                        return c3108i;
                    }
                }
            }
            it = this.f15670c.iterator();
            while (it.hasNext()) {
                c3108i = (C3108i) it.next();
                if (c3108i.m17543l() == null) {
                    it.remove();
                    this.f15669b.add(c3108i);
                    return c3108i;
                }
            }
        }
        return null;
    }

    public void m17836b(C3217k<E> c3217k) {
        if (c3217k != null) {
            this.f15671d.remove(c3217k);
        }
    }

    public int m17837c() {
        return this.f15671d.size();
    }

    public E m17838c(C c) {
        E a = m17830a((Object) c);
        this.f15669b.add(a);
        return a;
    }

    public int m17839d() {
        return this.f15670c.size();
    }

    public int m17840e() {
        return this.f15670c.size() + this.f15669b.size();
    }

    public E m17841f() {
        return !this.f15670c.isEmpty() ? (C3108i) this.f15670c.getLast() : null;
    }

    public C3217k<E> m17842g() {
        return (C3217k) this.f15671d.poll();
    }

    public void m17843h() {
        Iterator it = this.f15671d.iterator();
        while (it.hasNext()) {
            ((C3217k) it.next()).cancel(true);
        }
        this.f15671d.clear();
        it = this.f15670c.iterator();
        while (it.hasNext()) {
            ((C3108i) it.next()).m17537f();
        }
        this.f15670c.clear();
        for (C3108i f : this.f15669b) {
            f.m17537f();
        }
        this.f15669b.clear();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[route: ");
        stringBuilder.append(this.f15668a);
        stringBuilder.append("][leased: ");
        stringBuilder.append(this.f15669b.size());
        stringBuilder.append("][available: ");
        stringBuilder.append(this.f15670c.size());
        stringBuilder.append("][pending: ");
        stringBuilder.append(this.f15671d.size());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
