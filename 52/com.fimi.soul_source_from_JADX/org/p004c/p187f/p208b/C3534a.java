package org.p004c.p187f.p208b;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import org.p004c.p005e.p007b.C3495d;
import org.p004c.p187f.C3375b;
import org.p004c.p187f.C3542g;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p187f.p192a.C3523b;
import org.p004c.p187f.p192a.C3524d;

/* renamed from: org.c.f.b.a */
public class C3534a extends C3375b {
    private final Object[] f16083a;
    private final String f16084b;

    public C3534a(C3537d c3537d) {
        super(c3537d.m19245b().m19221d());
        this.f16083a = c3537d.m19246c().toArray(new Object[c3537d.m19246c().size()]);
        this.f16084b = c3537d.m19244a();
    }

    private Object m19230j() {
        return m18348g().m19223f().newInstance(this.f16083a);
    }

    private Object m19231k() {
        List<C3523b> l = m19232l();
        if (l.size() != this.f16083a.length) {
            throw new Exception("Wrong number of parameters and @Parameter fields. @Parameter fields counted: " + l.size() + ", available parameters: " + this.f16083a.length + ".");
        }
        Object newInstance = m18348g().m19221d().newInstance();
        for (C3523b d : l) {
            Field d2 = d.m19178d();
            int a = ((C3542g) d2.getAnnotation(C3542g.class)).m19258a();
            try {
                d2.set(newInstance, this.f16083a[a]);
            } catch (Throwable e) {
                throw new Exception(m18348g().m19222e() + ": Trying to set " + d2.getName() + " with the value " + this.f16083a[a] + " that is not the right type (" + this.f16083a[a].getClass().getSimpleName() + " instead of " + d2.getType().getSimpleName() + ").", e);
            }
        }
        return newInstance;
    }

    private List<C3523b> m19232l() {
        return m18348g().m19220c(C3542g.class);
    }

    private boolean m19233m() {
        return !m19232l().isEmpty();
    }

    public Object m19234b() {
        return m19233m() ? m19231k() : m19230j();
    }

    protected C3377k m19235b(C3495d c3495d) {
        return m18342c(c3495d);
    }

    protected void m19236b(List<Throwable> list) {
        m18577e((List) list);
        if (m19233m()) {
            m18578f(list);
        }
    }

    protected String m19237d(C3524d c3524d) {
        return c3524d.m19190b() + m19238f();
    }

    protected String m19238f() {
        return this.f16084b;
    }

    protected void m19239h(List<Throwable> list) {
        super.m18580h(list);
        if (m19233m()) {
            int a;
            List<C3523b> l = m19232l();
            int[] iArr = new int[l.size()];
            for (C3523b d : l) {
                a = ((C3542g) d.m19178d().getAnnotation(C3542g.class)).m19258a();
                if (a < 0 || a > l.size() - 1) {
                    list.add(new Exception("Invalid @Parameter value: " + a + ". @Parameter fields counted: " + l.size() + ". Please use an index between 0 and " + (l.size() - 1) + "."));
                } else {
                    iArr[a] = iArr[a] + 1;
                }
            }
            for (a = 0; a < iArr.length; a++) {
                int i = iArr[a];
                if (i == 0) {
                    list.add(new Exception("@Parameter(" + a + ") is never used."));
                } else if (i > 1) {
                    list.add(new Exception("@Parameter(" + a + ") is used more than once (" + i + ")."));
                }
            }
        }
    }

    protected Annotation[] m19240i() {
        return new Annotation[0];
    }
}
