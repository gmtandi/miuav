package org.p004c.p187f;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.p004c.C3457b;
import org.p004c.C3561g;
import org.p004c.C3562h;
import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p006a.C0128e;
import org.p004c.p005e.p006a.C0129g;
import org.p004c.p005e.p006a.C3323a;
import org.p004c.p005e.p006a.C3490f;
import org.p004c.p005e.p006a.C3491h;
import org.p004c.p005e.p007b.C3495d;
import org.p004c.p005e.p007b.C3504m;
import org.p004c.p187f.p192a.C3347j;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p187f.p192a.C3526f;
import org.p004c.p187f.p192a.C3528l;
import org.p004c.p190g.C3552j;
import org.p004c.p190g.C3553c;
import org.p004c.p190g.C3559i;
import org.p004c.p198b.C3398b;
import org.p004c.p198b.p202d.p203a.C3403a;
import org.p004c.p198b.p202d.p204b.C3408a;
import org.p004c.p198b.p202d.p205c.C3429h;
import org.p004c.p198b.p202d.p205c.C3430i;
import org.p004c.p207d.C3460r;
import org.p004c.p207d.C3472k;

/* renamed from: org.c.f.j */
public abstract class C3320j<T> extends C3319s implements C0128e, C0129g {
    private static final List<C3552j> f15826a;
    private final Object f15827b;
    private final C3528l f15828c;
    private volatile Collection<T> f15829d;
    private volatile C3347j f15830e;

    static {
        f15826a = Arrays.asList(new C3552j[]{new C3553c(), new C3559i()});
    }

    protected C3320j(Class<?> cls) {
        this.f15827b = new Object();
        this.f15829d = null;
        this.f15830e = new C3545k(this);
        this.f15828c = m18330a((Class) cls);
        m18323b();
    }

    private boolean m18320a() {
        for (Object c : m18328j()) {
            if (!m18343c(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean m18321a(C3323a c3323a, T t) {
        return c3323a.m18372a(m18345d((Object) t));
    }

    private Comparator<? super T> m18322b(C3491h c3491h) {
        return new C3548n(this, c3491h);
    }

    private void m18323b() {
        List arrayList = new ArrayList();
        m18333a(arrayList);
        if (!arrayList.isEmpty()) {
            throw new C3526f(arrayList);
        }
    }

    private void m18324b(List<Throwable> list) {
        if (m18348g().m19221d() != null) {
            for (C3552j a : f15826a) {
                list.addAll(a.m19266a(m18348g()));
            }
        }
    }

    private C3377k m18325c(C3377k c3377k) {
        Iterable e = m18346e();
        return e.isEmpty() ? c3377k : new C3472k(c3377k, e, m18344d());
    }

    private void m18326c(List<Throwable> list) {
        C3408a.f15913a.m18688a(m18348g(), (List) list);
        C3408a.f15915c.m18688a(m18348g(), (List) list);
    }

    private void m18327d(C3495d c3495d) {
        C3347j c3347j = this.f15830e;
        try {
            for (Object c3547m : m18328j()) {
                c3347j.m18458a(new C3547m(this, c3547m, c3495d));
            }
        } finally {
            c3347j.m18457a();
        }
    }

    private Collection<T> m18328j() {
        if (this.f15829d == null) {
            synchronized (this.f15827b) {
                if (this.f15829d == null) {
                    this.f15829d = Collections.unmodifiableCollection(m18341c());
                }
            }
        }
        return this.f15829d;
    }

    protected C3377k m18329a(C3377k c3377k) {
        List b = this.f15828c.m19217b(C3561g.class);
        return b.isEmpty() ? c3377k : new C3430i(c3377k, b, null);
    }

    protected C3528l m18330a(Class<?> cls) {
        return new C3528l(cls);
    }

    protected void m18331a(Class<? extends Annotation> cls, boolean z, List<Throwable> list) {
        for (C3524d a : m18348g().m19217b((Class) cls)) {
            a.m19185a(z, (List) list);
        }
    }

    protected abstract void m18332a(T t, C3495d c3495d);

    protected void m18333a(List<Throwable> list) {
        m18331a(C3561g.class, true, (List) list);
        m18331a(C3457b.class, true, (List) list);
        m18326c((List) list);
        m18324b((List) list);
    }

    public void m18334a(C3323a c3323a) {
        synchronized (this.f15827b) {
            Collection arrayList = new ArrayList(m18328j());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (m18321a(c3323a, next)) {
                    try {
                        c3323a.m18371a(next);
                    } catch (C3490f e) {
                        it.remove();
                    }
                } else {
                    it.remove();
                }
            }
            this.f15829d = Collections.unmodifiableCollection(arrayList);
            if (this.f15829d.isEmpty()) {
                throw new C3490f();
            }
        }
    }

    public void m18335a(C3491h c3491h) {
        synchronized (this.f15827b) {
            for (Object a : m18328j()) {
                c3491h.m19043a(a);
            }
            Collection arrayList = new ArrayList(m18328j());
            Collections.sort(arrayList, m18322b(c3491h));
            this.f15829d = Collections.unmodifiableCollection(arrayList);
        }
    }

    public void m18336a(C3495d c3495d) {
        C3403a c3403a = new C3403a(c3495d, m18344d());
        try {
            m18339b(c3495d).m18589a();
        } catch (C3398b e) {
            c3403a.m18667a(e);
        } catch (C3504m e2) {
            throw e2;
        } catch (Throwable th) {
            c3403a.m18666a(th);
        }
    }

    public void m18337a(C3347j c3347j) {
        this.f15830e = c3347j;
    }

    protected final void m18338a(C3377k c3377k, C3507d c3507d, C3495d c3495d) {
        C3403a c3403a = new C3403a(c3495d, c3507d);
        c3403a.m18668b();
        try {
            c3377k.m18589a();
        } catch (C3398b e) {
            c3403a.m18667a(e);
        } catch (Throwable th) {
            c3403a.m18666a(th);
        } finally {
            c3403a.m18665a();
        }
    }

    protected C3377k m18339b(C3495d c3495d) {
        C3377k c = m18342c(c3495d);
        return !m18320a() ? m18325c(m18340b(m18329a(c))) : c;
    }

    protected C3377k m18340b(C3377k c3377k) {
        List b = this.f15828c.m19217b(C3457b.class);
        return b.isEmpty() ? c3377k : new C3429h(c3377k, b, null);
    }

    protected abstract List<T> m18341c();

    protected C3377k m18342c(C3495d c3495d) {
        return new C3546l(this, c3495d);
    }

    protected boolean m18343c(T t) {
        return false;
    }

    public C3507d m18344d() {
        C3507d a = C3507d.m19090a(m18347f(), m18349i());
        for (Object d : m18328j()) {
            a.m19092a(m18345d(d));
        }
        return a;
    }

    protected abstract C3507d m18345d(T t);

    protected List<C3460r> m18346e() {
        List<C3460r> b = this.f15828c.m19218b(null, C3562h.class, C3460r.class);
        b.addAll(this.f15828c.m19213a(null, C3562h.class, C3460r.class));
        return b;
    }

    protected String m18347f() {
        return this.f15828c.m19222e();
    }

    public final C3528l m18348g() {
        return this.f15828c;
    }

    protected Annotation[] m18349i() {
        return this.f15828c.m19215a();
    }
}
