package org.p004c.p198b.p202d;

import java.lang.annotation.Annotation;
import org.p004c.p005e.C0130c;
import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p006a.C0128e;
import org.p004c.p005e.p006a.C0129g;
import org.p004c.p005e.p006a.C3323a;
import org.p004c.p005e.p006a.C3490f;
import org.p004c.p005e.p006a.C3491h;
import org.p004c.p005e.p007b.C3495d;
import org.p122a.p123a.C2915a;
import p001b.p002b.C0115j;
import p001b.p002b.C0116p;
import p001b.p002b.C0136k;
import p001b.p002b.C0138m;
import p001b.p002b.C0139n;
import p001b.p003a.C0120d;

/* renamed from: org.c.b.d.e */
public class C3433e extends C3319s implements C0128e, C0129g {
    private volatile C0115j f15947a;

    public C3433e(C0115j c0115j) {
        m18738b(c0115j);
    }

    public C3433e(Class<?> cls) {
        this(new C0116p(cls.asSubclass(C0136k.class)));
    }

    private C0115j m18734a() {
        return this.f15947a;
    }

    private static String m18735a(C0116p c0116p) {
        String str;
        if (c0116p.m139a() == 0) {
            str = C2915a.f14760f;
        } else {
            str = String.format(" [example: %s]", new Object[]{c0116p.m140a(0)});
        }
        return String.format("TestSuite with %s tests%s", new Object[]{Integer.valueOf(r1), str});
    }

    private static C3507d m18736a(C0115j c0115j) {
        int i = 0;
        if (c0115j instanceof C0136k) {
            C0136k c0136k = (C0136k) c0115j;
            return C3507d.m19086a(c0136k.getClass(), c0136k.m288j(), C3433e.m18737a(c0136k));
        } else if (!(c0115j instanceof C0116p)) {
            return c0115j instanceof C0130c ? ((C0130c) c0115j).m213d() : c0115j instanceof C0120d ? C3433e.m18736a(((C0120d) c0115j).m192b()) : C3507d.m19084a(c0115j.getClass());
        } else {
            C0116p c0116p = (C0116p) c0115j;
            C3507d a = C3507d.m19090a(c0116p.m146c() == null ? C3433e.m18735a(c0116p) : c0116p.m146c(), new Annotation[0]);
            int d = c0116p.m147d();
            while (i < d) {
                a.m19092a(C3433e.m18736a(c0116p.m140a(i)));
                i++;
            }
            return a;
        }
    }

    private static Annotation[] m18737a(C0136k c0136k) {
        try {
            return c0136k.getClass().getMethod(c0136k.m288j(), new Class[0]).getDeclaredAnnotations();
        } catch (SecurityException e) {
            return new Annotation[0];
        } catch (NoSuchMethodException e2) {
            return new Annotation[0];
        }
    }

    private void m18738b(C0115j c0115j) {
        this.f15947a = c0115j;
    }

    public void m18739a(C3323a c3323a) {
        if (m18734a() instanceof C0128e) {
            ((C0128e) m18734a()).m211a(c3323a);
        } else if (m18734a() instanceof C0116p) {
            C0116p c0116p = (C0116p) m18734a();
            C0115j c0116p2 = new C0116p(c0116p.m146c());
            int d = c0116p.m147d();
            for (int i = 0; i < d; i++) {
                C0115j a = c0116p.m140a(i);
                if (c3323a.m18372a(C3433e.m18736a(a))) {
                    c0116p2.m141a(a);
                }
            }
            m18738b(c0116p2);
            if (c0116p2.m147d() == 0) {
                throw new C3490f();
            }
        }
    }

    public void m18740a(C3491h c3491h) {
        if (m18734a() instanceof C0129g) {
            ((C0129g) m18734a()).m212a(c3491h);
        }
    }

    public void m18741a(C3495d c3495d) {
        C0139n c0139n = new C0139n();
        c0139n.m305a(m18742b(c3495d));
        m18734a().m129a(c0139n);
    }

    public C0138m m18742b(C3495d c3495d) {
        return new C3435g(null);
    }

    public C3507d m18743d() {
        return C3433e.m18736a(m18734a());
    }
}
