package p001b.p002b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/* renamed from: b.b.n */
public class C0139n {
    protected List<C0137l> f152a;
    protected List<C0137l> f153b;
    protected List<C0138m> f154c;
    protected int f155d;
    private boolean f156e;

    public C0139n() {
        this.f152a = new ArrayList();
        this.f153b = new ArrayList();
        this.f154c = new ArrayList();
        this.f155d = 0;
        this.f156e = false;
    }

    private synchronized List<C0138m> m298i() {
        List<C0138m> arrayList;
        arrayList = new ArrayList();
        arrayList.addAll(this.f154c);
        return arrayList;
    }

    public synchronized int m299a() {
        return this.f153b.size();
    }

    public void m300a(C0115j c0115j) {
        for (C0138m a : m298i()) {
            a.m294a(c0115j);
        }
    }

    public synchronized void m301a(C0115j c0115j, C0125b c0125b) {
        this.f152a.add(new C0137l(c0115j, c0125b));
        for (C0138m a : m298i()) {
            a.m295a(c0115j, c0125b);
        }
    }

    public void m302a(C0115j c0115j, C0123i c0123i) {
        try {
            c0123i.m199a();
        } catch (C0125b e) {
            m301a(c0115j, e);
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            m303a(c0115j, th);
        }
    }

    public synchronized void m303a(C0115j c0115j, Throwable th) {
        this.f153b.add(new C0137l(c0115j, th));
        for (C0138m a : m298i()) {
            a.m296a(c0115j, th);
        }
    }

    protected void m304a(C0136k c0136k) {
        m307b((C0115j) c0136k);
        m302a((C0115j) c0136k, new C0140o(this, c0136k));
        m300a((C0115j) c0136k);
    }

    public synchronized void m305a(C0138m c0138m) {
        this.f154c.add(c0138m);
    }

    public synchronized Enumeration<C0137l> m306b() {
        return Collections.enumeration(this.f153b);
    }

    public void m307b(C0115j c0115j) {
        int a = c0115j.m128a();
        synchronized (this) {
            this.f155d = a + this.f155d;
        }
        for (C0138m b : m298i()) {
            b.m297b(c0115j);
        }
    }

    public synchronized void m308b(C0138m c0138m) {
        this.f154c.remove(c0138m);
    }

    public synchronized int m309c() {
        return this.f152a.size();
    }

    public synchronized Enumeration<C0137l> m310d() {
        return Collections.enumeration(this.f152a);
    }

    public synchronized int m311e() {
        return this.f155d;
    }

    public synchronized boolean m312f() {
        return this.f156e;
    }

    public synchronized void m313g() {
        this.f156e = true;
    }

    public synchronized boolean m314h() {
        boolean z;
        z = m309c() == 0 && m299a() == 0;
        return z;
    }
}
