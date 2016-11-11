package org.p004c.p198b.p202d;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p007b.C3493a;
import org.p004c.p005e.p007b.C3495d;
import org.p004c.p187f.p192a.C3526f;

/* renamed from: org.c.b.d.b */
public class C3421b extends C3319s {
    private final List<Throwable> f15923a;
    private final Class<?> f15924b;

    public C3421b(Class<?> cls, Throwable th) {
        if (cls == null) {
            throw new NullPointerException("Test class cannot be null");
        }
        this.f15924b = cls;
        this.f15923a = m18705a(th);
    }

    private List<Throwable> m18705a(Throwable th) {
        if (th instanceof InvocationTargetException) {
            return m18705a(th.getCause());
        }
        if (th instanceof C3526f) {
            return ((C3526f) th).m19199a();
        }
        if (th instanceof C3432d) {
            return ((C3432d) th).m18733a();
        }
        return Arrays.asList(new Throwable[]{th});
    }

    private void m18706a(Throwable th, C3495d c3495d) {
        C3507d b = m18707b(th);
        c3495d.m19060b(b);
        c3495d.m19054a(new C3493a(b, th));
        c3495d.m19064d(b);
    }

    private C3507d m18707b(Throwable th) {
        return C3507d.m19085a(this.f15924b, "initializationError");
    }

    public void m18708a(C3495d c3495d) {
        for (Throwable a : this.f15923a) {
            m18706a(a, c3495d);
        }
    }

    public C3507d m18709d() {
        C3507d a = C3507d.m19084a(this.f15924b);
        for (Throwable b : this.f15923a) {
            a.m19092a(m18707b(b));
        }
        return a;
    }
}
