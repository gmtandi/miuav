package org.p004c.p188a.p191b;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3340l;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.C3513k;
import org.p004c.p005e.C3515n;
import org.p004c.p187f.C3321o;
import org.p004c.p198b.p201c.C3401c;
import org.p004c.p198b.p202d.C3433e;
import org.p122a.p123a.C2915a;
import p001b.p002b.C0116p;

/* renamed from: org.c.a.b.b */
public class C3339b {
    private static final String f15841a = "malformed JUnit 3 test class: ";
    private final C3343e f15842b;

    private C3339b(File file) {
        this.f15842b = C3343e.m18440a(file);
    }

    public static C3339b m18416a(File file) {
        return new C3339b(file);
    }

    @Deprecated
    public static C3339b m18417a(String str) {
        return C3339b.m18416a(new File(str));
    }

    private C3340l m18418a(List<C3507d> list) {
        List arrayList = new ArrayList();
        for (C3507d a : list) {
            arrayList.add(m18419a(a));
        }
        return new C3341c(this, arrayList);
    }

    private C3319s m18419a(C3507d c3507d) {
        if (c3507d.toString().equals("TestSuite with 0 tests")) {
            return C3321o.m18350a();
        }
        if (c3507d.toString().startsWith(f15841a)) {
            return new C3433e(new C0116p(m18421b(c3507d)));
        }
        Class i = c3507d.m19101i();
        if (i == null) {
            throw new RuntimeException("Can't build a runner from description [" + c3507d + "]");
        }
        String k = c3507d.m19103k();
        return k == null ? C3340l.m18428a(i).m18438a() : C3340l.m18429a(i, k).m18438a();
    }

    private void m18420a(C3507d c3507d, C3507d c3507d2, List<C3507d> list) {
        if (!c3507d2.m19094b().isEmpty()) {
            Iterator it = c3507d2.m19094b().iterator();
            while (it.hasNext()) {
                m18420a(c3507d2, (C3507d) it.next(), list);
            }
        } else if (c3507d2.toString().equals("warning(junit.framework.TestSuite$1)")) {
            list.add(C3507d.m19090a(f15841a + c3507d, new Annotation[0]));
        } else {
            list.add(c3507d2);
        }
    }

    private Class<?> m18421b(C3507d c3507d) {
        try {
            return Class.forName(c3507d.toString().replace(f15841a, C2915a.f14760f));
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private List<C3507d> m18422d(C3340l c3340l) {
        List<C3507d> arrayList = new ArrayList();
        m18420a(null, c3340l.m18438a().m18317d(), arrayList);
        return arrayList;
    }

    public C3515n m18423a(Class<?> cls) {
        return m18424a(C3340l.m18428a((Class) cls));
    }

    public C3515n m18424a(C3340l c3340l) {
        return m18425a(c3340l, new C3513k());
    }

    public C3515n m18425a(C3340l c3340l, C3513k c3513k) {
        c3513k.m19130a(this.f15842b.m18445a());
        return c3513k.m19129a(m18426b(c3340l).m18438a());
    }

    public C3340l m18426b(C3340l c3340l) {
        if (c3340l instanceof C3401c) {
            return c3340l;
        }
        List d = m18422d(c3340l);
        Collections.sort(d, this.f15842b.m18447b());
        return m18418a(d);
    }

    public List<C3507d> m18427c(C3340l c3340l) {
        return m18422d(m18426b(c3340l));
    }
}
