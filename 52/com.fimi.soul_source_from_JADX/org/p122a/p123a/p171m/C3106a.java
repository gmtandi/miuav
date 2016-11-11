package org.p122a.p123a.p171m;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.p122a.p123a.p150a.C2914d;
import org.p122a.p123a.p157d.C2993c;
import org.p122a.p123a.p180o.C3234a;
import org.p122a.p123a.p180o.C3235b;

@C2914d
/* renamed from: org.a.a.m.a */
public abstract class C3106a<T, C, E extends C3108i<T, C>> implements C3104g<T, E>, C3105h<T> {
    private final Lock f15379a;
    private final C3130f<T, C> f15380b;
    private final Map<T, C3215m<T, C, E>> f15381c;
    private final Set<E> f15382d;
    private final LinkedList<E> f15383e;
    private final LinkedList<C3217k<E>> f15384f;
    private final Map<T, Integer> f15385g;
    private volatile boolean f15386h;
    private volatile int f15387i;
    private volatile int f15388j;

    public C3106a(C3130f<T, C> c3130f, int i, int i2) {
        this.f15380b = (C3130f) C3234a.m17886a((Object) c3130f, "Connection factory");
        this.f15387i = C3234a.m17890b(i, "Max per route value");
        this.f15388j = C3234a.m17890b(i2, "Max total value");
        this.f15379a = new ReentrantLock();
        this.f15381c = new HashMap();
        this.f15382d = new HashSet();
        this.f15383e = new LinkedList();
        this.f15384f = new LinkedList();
        this.f15385g = new HashMap();
    }

    private E m17505a(T t, Object obj, long j, TimeUnit timeUnit, C3217k<E> c3217k) {
        Date date = null;
        if (j > 0) {
            date = new Date(System.currentTimeMillis() + timeUnit.toMillis(j));
        }
        this.f15379a.lock();
        C3215m c = m17507c(t);
        C3108i c3108i = null;
        while (c3108i == null) {
            C3235b.m17895a(!this.f15386h, "Connection pool shut down");
            while (true) {
                E b = c.m17835b(obj);
                if (b != null) {
                    if (!b.m17536e() && !b.m17535a(System.currentTimeMillis())) {
                        break;
                    }
                    b.m17537f();
                    this.f15383e.remove(b);
                    c.m17831a(b, false);
                } else {
                    break;
                }
            }
            if (b != null) {
                this.f15383e.remove(b);
                this.f15382d.add(b);
                this.f15379a.unlock();
                return b;
            }
            int i;
            int d = m17508d(t);
            int max = Math.max(0, (c.m17840e() + 1) - d);
            if (max > 0) {
                for (i = 0; i < max; i++) {
                    C3108i f = c.m17841f();
                    if (f == null) {
                        break;
                    }
                    f.m17537f();
                    this.f15383e.remove(f);
                    c.m17833a(f);
                }
            }
            if (c.m17840e() < d) {
                i = Math.max(this.f15388j - this.f15382d.size(), 0);
                if (i > 0) {
                    if (this.f15383e.size() > i - 1 && !this.f15383e.isEmpty()) {
                        C3108i c3108i2 = (C3108i) this.f15383e.removeLast();
                        c3108i2.m17537f();
                        m17507c(c3108i2.m17539h()).m17833a(c3108i2);
                    }
                    E c2 = c.m17838c(this.f15380b.m17637a(t));
                    this.f15382d.add(c2);
                    this.f15379a.unlock();
                    return c2;
                }
            }
            try {
                c.m17832a((C3217k) c3217k);
                this.f15384f.add(c3217k);
                boolean a = c3217k.m17846a(date);
                c.m17836b((C3217k) c3217k);
                this.f15384f.remove(c3217k);
                if (!a && date != null && date.getTime() <= System.currentTimeMillis()) {
                    break;
                }
            } catch (Throwable th) {
                this.f15379a.unlock();
            }
        }
        throw new TimeoutException("Timeout waiting for connection");
    }

    private C3215m<T, C, E> m17507c(T t) {
        C3215m<T, C, E> c3215m = (C3215m) this.f15381c.get(t);
        if (c3215m != null) {
            return c3215m;
        }
        C3215m c3216b = new C3216b(this, t, t);
        this.f15381c.put(t, c3216b);
        return c3216b;
    }

    private int m17508d(T t) {
        Integer num = (Integer) this.f15385g.get(t);
        return num != null ? num.intValue() : this.f15387i;
    }

    private void m17509g() {
        Iterator it = this.f15381c.entrySet().iterator();
        while (it.hasNext()) {
            C3215m c3215m = (C3215m) ((Entry) it.next()).getValue();
            if (c3215m.m17840e() + c3215m.m17837c() == 0) {
                it.remove();
            }
        }
    }

    public Future<E> m17510a(T t, Object obj, C2993c<E> c2993c) {
        C3234a.m17886a((Object) t, "Route");
        C3235b.m17895a(!this.f15386h, "Connection pool shut down");
        return new C3218c(this, this.f15379a, c2993c, t, obj);
    }

    protected abstract E m17511a(T t, C c);

    public C3222l m17512a(T t) {
        C3234a.m17886a((Object) t, "Route");
        this.f15379a.lock();
        C3222l c3222l;
        try {
            C3215m c = m17507c(t);
            c3222l = new C3222l(c.m17834b(), c.m17837c(), c.m17839d(), m17508d(t));
            return c3222l;
        } finally {
            c3222l = this.f15379a;
            c3222l.unlock();
        }
    }

    public void m17513a(int i) {
        C3234a.m17890b(i, "Max value");
        this.f15379a.lock();
        try {
            this.f15388j = i;
        } finally {
            this.f15379a.unlock();
        }
    }

    public void m17514a(long j, TimeUnit timeUnit) {
        long j2 = 0;
        C3234a.m17886a((Object) timeUnit, "Time unit");
        long toMillis = timeUnit.toMillis(j);
        if (toMillis >= 0) {
            j2 = toMillis;
        }
        m17519a(new C3220d(this, System.currentTimeMillis() - j2));
    }

    public void m17515a(T t, int i) {
        C3234a.m17886a((Object) t, "Route");
        C3234a.m17890b(i, "Max per route value");
        this.f15379a.lock();
        try {
            this.f15385g.put(t, Integer.valueOf(i));
        } finally {
            this.f15379a.unlock();
        }
    }

    protected void m17517a(E e) {
    }

    public void m17518a(E e, boolean z) {
        this.f15379a.lock();
        try {
            if (this.f15382d.remove(e)) {
                C3215m c = m17507c(e.m17539h());
                c.m17831a(e, z);
                if (!z || this.f15386h) {
                    e.m17537f();
                } else {
                    this.f15383e.addFirst(e);
                    m17525b((C3108i) e);
                }
                C3217k g = c.m17842g();
                if (g != null) {
                    this.f15384f.remove(g);
                } else {
                    g = (C3217k) this.f15384f.poll();
                }
                if (g != null) {
                    g.m17845a();
                }
            }
            this.f15379a.unlock();
        } catch (Throwable th) {
            this.f15379a.unlock();
        }
    }

    protected void m17519a(C3219j<T, C> c3219j) {
        this.f15379a.lock();
        try {
            Iterator it = this.f15383e.iterator();
            while (it.hasNext()) {
                C3108i c3108i = (C3108i) it.next();
                c3219j.m17850a(c3108i);
                if (c3108i.m17536e()) {
                    m17507c(c3108i.m17539h()).m17833a(c3108i);
                    it.remove();
                }
            }
            m17509g();
        } finally {
            this.f15379a.unlock();
        }
    }

    public boolean m17520a() {
        return this.f15386h;
    }

    public int m17521b(T t) {
        C3234a.m17886a((Object) t, "Route");
        this.f15379a.lock();
        try {
            int d = m17508d(t);
            return d;
        } finally {
            this.f15379a.unlock();
        }
    }

    public Future<E> m17522b(T t, Object obj) {
        return m17510a(t, obj, null);
    }

    public void m17523b() {
        if (!this.f15386h) {
            this.f15386h = true;
            this.f15379a.lock();
            try {
                Iterator it = this.f15383e.iterator();
                while (it.hasNext()) {
                    ((C3108i) it.next()).m17537f();
                }
                for (C3108i f : this.f15382d) {
                    f.m17537f();
                }
                for (C3215m h : this.f15381c.values()) {
                    h.m17843h();
                }
                this.f15381c.clear();
                this.f15382d.clear();
                this.f15383e.clear();
            } finally {
                this.f15379a.unlock();
            }
        }
    }

    public void m17524b(int i) {
        C3234a.m17890b(i, "Max per route value");
        this.f15379a.lock();
        try {
            this.f15387i = i;
        } finally {
            this.f15379a.unlock();
        }
    }

    protected void m17525b(E e) {
    }

    protected void m17526b(C3219j<T, C> c3219j) {
        this.f15379a.lock();
        try {
            for (C3108i a : this.f15382d) {
                c3219j.m17850a(a);
            }
        } finally {
            this.f15379a.unlock();
        }
    }

    public int m17527c() {
        this.f15379a.lock();
        try {
            int i = this.f15388j;
            return i;
        } finally {
            this.f15379a.unlock();
        }
    }

    public int m17528d() {
        this.f15379a.lock();
        try {
            int i = this.f15387i;
            return i;
        } finally {
            this.f15379a.unlock();
        }
    }

    public C3222l m17529e() {
        this.f15379a.lock();
        try {
            C3222l c3222l = new C3222l(this.f15382d.size(), this.f15384f.size(), this.f15383e.size(), this.f15388j);
            return c3222l;
        } finally {
            this.f15379a.unlock();
        }
    }

    public void m17530f() {
        m17519a(new C3221e(this, System.currentTimeMillis()));
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[leased: ");
        stringBuilder.append(this.f15382d);
        stringBuilder.append("][available: ");
        stringBuilder.append(this.f15383e);
        stringBuilder.append("][pending: ");
        stringBuilder.append(this.f15384f);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
