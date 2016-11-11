package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.android.volley.v */
public class C0600v {
    private static final int f3673f = 4;
    private AtomicInteger f3674a;
    private final Map<String, Queue<C0570r<?>>> f3675b;
    private final Set<C0570r<?>> f3676c;
    private final PriorityBlockingQueue<C0570r<?>> f3677d;
    private final PriorityBlockingQueue<C0570r<?>> f3678e;
    private final C0554b f3679g;
    private final C0563k f3680h;
    private final ac f3681i;
    private C0564l[] f3682j;
    private C0556d f3683k;
    private List<C0603y> f3684l;

    public C0600v(C0554b c0554b, C0563k c0563k) {
        this(c0554b, c0563k, f3673f);
    }

    public C0600v(C0554b c0554b, C0563k c0563k, int i) {
        this(c0554b, c0563k, i, new C0559g(new Handler(Looper.getMainLooper())));
    }

    public C0600v(C0554b c0554b, C0563k c0563k, int i, ac acVar) {
        this.f3674a = new AtomicInteger();
        this.f3675b = new HashMap();
        this.f3676c = new HashSet();
        this.f3677d = new PriorityBlockingQueue();
        this.f3678e = new PriorityBlockingQueue();
        this.f3684l = new ArrayList();
        this.f3679g = c0554b;
        this.f3680h = c0563k;
        this.f3682j = new C0564l[i];
        this.f3681i = acVar;
    }

    public <T> C0570r<T> m5279a(C0570r<T> c0570r) {
        c0570r.m5102a(this);
        synchronized (this.f3676c) {
            this.f3676c.add(c0570r);
        }
        c0570r.m5099a(m5287c());
        c0570r.m5106a("add-to-queue");
        if (c0570r.m5132w()) {
            synchronized (this.f3675b) {
                String j = c0570r.m5119j();
                if (this.f3675b.containsKey(j)) {
                    Queue queue = (Queue) this.f3675b.get(j);
                    if (queue == null) {
                        queue = new LinkedList();
                    }
                    queue.add(c0570r);
                    this.f3675b.put(j, queue);
                    if (ah.f3498b) {
                        ah.m5056a("Request for cacheKey=%s is in flight, putting on hold.", j);
                    }
                } else {
                    this.f3675b.put(j, null);
                    this.f3677d.add(c0570r);
                }
            }
        } else {
            this.f3678e.add(c0570r);
        }
        return c0570r;
    }

    public void m5280a() {
        m5284b();
        this.f3683k = new C0556d(this.f3677d, this.f3678e, this.f3679g, this.f3681i);
        this.f3683k.start();
        for (int i = 0; i < this.f3682j.length; i++) {
            C0564l c0564l = new C0564l(this.f3678e, this.f3680h, this.f3679g, this.f3681i);
            this.f3682j[i] = c0564l;
            c0564l.start();
        }
    }

    public void m5281a(C0601x c0601x) {
        synchronized (this.f3676c) {
            for (C0570r c0570r : this.f3676c) {
                if (c0601x.m5289a(c0570r)) {
                    c0570r.m5121l();
                }
            }
        }
    }

    public <T> void m5282a(C0603y<T> c0603y) {
        synchronized (this.f3684l) {
            this.f3684l.add(c0603y);
        }
    }

    public void m5283a(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Cannot cancelAll with a null tag");
        }
        m5281a(new C0602w(this, obj));
    }

    public void m5284b() {
        if (this.f3683k != null) {
            this.f3683k.m5075a();
        }
        for (int i = 0; i < this.f3682j.length; i++) {
            if (this.f3682j[i] != null) {
                this.f3682j[i].m5089a();
            }
        }
    }

    <T> void m5285b(C0570r<T> c0570r) {
        synchronized (this.f3676c) {
            this.f3676c.remove(c0570r);
        }
        synchronized (this.f3684l) {
            for (C0603y a : this.f3684l) {
                a.m5291a(c0570r);
            }
        }
        if (c0570r.m5132w()) {
            synchronized (this.f3675b) {
                Queue queue = (Queue) this.f3675b.remove(c0570r.m5119j());
                if (queue != null) {
                    if (ah.f3498b) {
                        ah.m5056a("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r2);
                    }
                    this.f3677d.addAll(queue);
                }
            }
        }
    }

    public <T> void m5286b(C0603y<T> c0603y) {
        synchronized (this.f3684l) {
            this.f3684l.remove(c0603y);
        }
    }

    public int m5287c() {
        return this.f3674a.incrementAndGet();
    }

    public C0554b m5288d() {
        return this.f3679g;
    }
}
