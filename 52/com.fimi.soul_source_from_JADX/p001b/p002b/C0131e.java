package p001b.p002b;

import java.util.Iterator;
import java.util.List;
import org.p004c.C3568n;
import org.p004c.p005e.C0130c;
import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3340l;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p006a.C0128e;
import org.p004c.p005e.p006a.C0129g;
import org.p004c.p005e.p006a.C3323a;
import org.p004c.p005e.p006a.C3491h;

/* renamed from: b.b.e */
public class C0131e implements C0115j, C0128e, C0129g, C0130c {
    private final Class<?> f142a;
    private final C3319s f143b;
    private final C0132f f144c;

    public C0131e(Class<?> cls) {
        this(cls, C0132f.m223a());
    }

    public C0131e(Class<?> cls, C0132f c0132f) {
        this.f144c = c0132f;
        this.f142a = cls;
        this.f143b = C3340l.m18434b(cls).m18438a();
    }

    private C3507d m214a(C3507d c3507d) {
        if (m215b(c3507d)) {
            return C3507d.f16042a;
        }
        C3507d g = c3507d.m19099g();
        Iterator it = c3507d.m19094b().iterator();
        while (it.hasNext()) {
            C3507d a = m214a((C3507d) it.next());
            if (!a.m19098f()) {
                g.m19092a(a);
            }
        }
        return g;
    }

    private boolean m215b(C3507d c3507d) {
        return c3507d.m19093b(C3568n.class) != null;
    }

    public int m216a() {
        return this.f143b.m18318h();
    }

    public void m217a(C0139n c0139n) {
        this.f143b.m18316a(this.f144c.m225a(c0139n, this));
    }

    public void m218a(C3323a c3323a) {
        c3323a.m18371a(this.f143b);
    }

    public void m219a(C3491h c3491h) {
        c3491h.m19043a(this.f143b);
    }

    public List<C0115j> m220b() {
        return this.f144c.m227c(m222d());
    }

    public Class<?> m221c() {
        return this.f142a;
    }

    public C3507d m222d() {
        return m214a(this.f143b.m18317d());
    }

    public String toString() {
        return this.f142a.getName();
    }
}
