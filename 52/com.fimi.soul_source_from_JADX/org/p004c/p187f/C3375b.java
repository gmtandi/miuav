package org.p004c.p187f;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.p004c.C3383a;
import org.p004c.C3550f;
import org.p004c.C3568n;
import org.p004c.C3569o;
import org.p004c.C3570p;
import org.p004c.C3571q;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p007b.C3495d;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p198b.p202d.p204b.C3408a;
import org.p004c.p198b.p202d.p205c.C3422a;
import org.p004c.p198b.p202d.p205c.C3423b;
import org.p004c.p198b.p202d.p205c.C3424c;
import org.p004c.p198b.p202d.p205c.C3428g;
import org.p004c.p198b.p202d.p205c.C3429h;
import org.p004c.p198b.p202d.p205c.C3430i;
import org.p004c.p207d.C3460r;
import org.p004c.p207d.C3470i;
import org.p004c.p207d.C3472k;

/* renamed from: org.c.f.b */
public class C3375b extends C3320j<C3524d> {
    private final ConcurrentHashMap<C3524d, C3507d> f15870a;

    public C3375b(Class<?> cls) {
        super(cls);
        this.f15870a = new ConcurrentHashMap();
    }

    private Class<? extends Throwable> m18546a(C3570p c3570p) {
        return (c3570p == null || c3570p.m19300a() == C3571q.class) ? null : c3570p.m19300a();
    }

    private C3377k m18547a(C3524d c3524d, List<C3460r> list, Object obj, C3377k c3377k) {
        for (C3470i c3470i : m18551e(obj)) {
            if (!list.contains(c3470i)) {
                c3377k = c3470i.m18946a(c3377k, c3524d, obj);
            }
        }
        return c3377k;
    }

    private C3377k m18548a(C3524d c3524d, List<C3460r> list, C3377k c3377k) {
        return list.isEmpty() ? c3377k : new C3472k(c3377k, list, m18569c(c3524d));
    }

    private boolean m18549b(C3570p c3570p) {
        return m18546a(c3570p) != null;
    }

    private long m18550c(C3570p c3570p) {
        return c3570p == null ? 0 : c3570p.m19301b();
    }

    private List<C3470i> m18551e(Object obj) {
        return m18556a(obj);
    }

    private C3377k m18552e(C3524d c3524d, Object obj, C3377k c3377k) {
        List b = m18564b(obj);
        return m18548a(c3524d, b, m18547a(c3524d, b, obj, c3377k));
    }

    private void m18553i(List<Throwable> list) {
        C3408a.f15916d.m18688a(m18348g(), (List) list);
    }

    private boolean m18554j() {
        return m18348g().m19221d().getConstructors().length == 1;
    }

    protected List<C3524d> m18555a() {
        return m18348g().m19217b(C3570p.class);
    }

    protected List<C3470i> m18556a(Object obj) {
        List<C3470i> b = m18348g().m19218b(obj, C3569o.class, C3470i.class);
        b.addAll(m18348g().m19213a(obj, C3569o.class, C3470i.class));
        return b;
    }

    protected C3377k m18557a(C3524d c3524d) {
        try {
            Object a = new C3538c(this).m18672a();
            return m18552e(c3524d, a, m18575d(c3524d, a, m18570c(c3524d, a, m18565b(c3524d, a, m18559a(c3524d, a, m18558a(c3524d, a))))));
        } catch (Throwable th) {
            return new C3423b(th);
        }
    }

    protected C3377k m18558a(C3524d c3524d, Object obj) {
        return new C3428g(c3524d, obj);
    }

    protected C3377k m18559a(C3524d c3524d, Object obj, C3377k c3377k) {
        C3570p c3570p = (C3570p) c3524d.m19184a(C3570p.class);
        return m18549b(c3570p) ? new C3422a(c3377k, m18546a(c3570p)) : c3377k;
    }

    protected void m18561a(List<Throwable> list) {
        super.m18333a((List) list);
        m18576d((List) list);
        m18566b((List) list);
        m18579g(list);
        m18580h(list);
        m18553i(list);
    }

    protected void m18562a(C3524d c3524d, C3495d c3495d) {
        C3507d c = m18569c(c3524d);
        if (m18567b(c3524d)) {
            c3495d.m19062c(c);
        } else {
            m18338a(m18557a(c3524d), c, c3495d);
        }
    }

    protected Object m18563b() {
        return m18348g().m19223f().newInstance(new Object[0]);
    }

    protected List<C3460r> m18564b(Object obj) {
        List<C3460r> b = m18348g().m19218b(obj, C3569o.class, C3460r.class);
        b.addAll(m18348g().m19213a(obj, C3569o.class, C3460r.class));
        return b;
    }

    @Deprecated
    protected C3377k m18565b(C3524d c3524d, Object obj, C3377k c3377k) {
        long c = m18550c((C3570p) c3524d.m19184a(C3570p.class));
        return c <= 0 ? c3377k : C3424c.m18717b().m18726a(c, TimeUnit.MILLISECONDS).m18725a(c3377k);
    }

    protected void m18566b(List<Throwable> list) {
        m18577e((List) list);
        m18578f(list);
    }

    protected boolean m18567b(C3524d c3524d) {
        return c3524d.m19184a(C3568n.class) != null;
    }

    protected List<C3524d> m18568c() {
        return m18555a();
    }

    protected C3507d m18569c(C3524d c3524d) {
        C3507d c3507d = (C3507d) this.f15870a.get(c3524d);
        if (c3507d != null) {
            return c3507d;
        }
        c3507d = C3507d.m19086a(m18348g().m19221d(), m18573d(c3524d), c3524d.m19189a());
        this.f15870a.putIfAbsent(c3524d, c3507d);
        return c3507d;
    }

    protected C3377k m18570c(C3524d c3524d, Object obj, C3377k c3377k) {
        List b = m18348g().m19217b(C3550f.class);
        return b.isEmpty() ? c3377k : new C3430i(c3377k, b, obj);
    }

    protected void m18571c(List<Throwable> list) {
        m18331a(C3570p.class, false, (List) list);
    }

    protected /* synthetic */ boolean m18572c(Object obj) {
        return m18567b((C3524d) obj);
    }

    protected String m18573d(C3524d c3524d) {
        return c3524d.m19190b();
    }

    protected /* synthetic */ C3507d m18574d(Object obj) {
        return m18569c((C3524d) obj);
    }

    protected C3377k m18575d(C3524d c3524d, Object obj, C3377k c3377k) {
        List b = m18348g().m19217b(C3383a.class);
        return b.isEmpty() ? c3377k : new C3429h(c3377k, b, obj);
    }

    protected void m18576d(List<Throwable> list) {
        if (m18348g().m19225h()) {
            list.add(new Exception("The inner class " + m18348g().m19222e() + " is not static."));
        }
    }

    protected void m18577e(List<Throwable> list) {
        if (!m18554j()) {
            list.add(new Exception("Test class should have exactly one public constructor"));
        }
    }

    protected void m18578f(List<Throwable> list) {
        if (!m18348g().m19225h() && m18554j() && m18348g().m19223f().getParameterTypes().length != 0) {
            list.add(new Exception("Test class should have exactly one public zero-argument constructor"));
        }
    }

    @Deprecated
    protected void m18579g(List<Throwable> list) {
        m18331a(C3383a.class, false, (List) list);
        m18331a(C3550f.class, false, (List) list);
        m18571c((List) list);
        if (m18555a().size() == 0) {
            list.add(new Exception("No runnable methods"));
        }
    }

    protected void m18580h(List<Throwable> list) {
        C3408a.f15914b.m18688a(m18348g(), (List) list);
    }
}
