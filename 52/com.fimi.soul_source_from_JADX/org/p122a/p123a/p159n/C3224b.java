package org.p122a.p123a.p159n;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.p122a.p123a.p150a.C2913c;

@C2913c
/* renamed from: org.a.a.n.b */
final class C3224b<E> {
    private final LinkedList<E> f15693a;
    private final Map<Class<?>, E> f15694b;

    public C3224b() {
        this.f15693a = new LinkedList();
        this.f15694b = new HashMap();
    }

    private void m17858c(E e) {
        Object remove = this.f15694b.remove(e.getClass());
        if (remove != null) {
            this.f15693a.remove(remove);
        }
        this.f15694b.put(e.getClass(), e);
    }

    public LinkedList<E> m17859a() {
        return new LinkedList(this.f15693a);
    }

    public C3224b<E> m17860a(E e) {
        if (e != null) {
            m17858c(e);
            this.f15693a.addFirst(e);
        }
        return this;
    }

    public C3224b<E> m17861a(Collection<E> collection) {
        if (collection != null) {
            for (E a : collection) {
                m17860a((Object) a);
            }
        }
        return this;
    }

    public C3224b<E> m17862a(E... eArr) {
        if (eArr != null) {
            for (Object a : eArr) {
                m17860a(a);
            }
        }
        return this;
    }

    public C3224b<E> m17863b(E e) {
        if (e != null) {
            m17858c(e);
            this.f15693a.addLast(e);
        }
        return this;
    }

    public C3224b<E> m17864b(Collection<E> collection) {
        if (collection != null) {
            for (E b : collection) {
                m17863b((Object) b);
            }
        }
        return this;
    }

    public C3224b<E> m17865b(E... eArr) {
        if (eArr != null) {
            for (Object b : eArr) {
                m17863b(b);
            }
        }
        return this;
    }
}
