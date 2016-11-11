package org.p004c.p198b.p202d;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3507d;
import org.p004c.p005e.p006a.C0128e;
import org.p004c.p005e.p006a.C0129g;
import org.p004c.p005e.p006a.C3323a;
import org.p004c.p005e.p006a.C3490f;
import org.p004c.p005e.p006a.C3491h;
import org.p004c.p005e.p007b.C3493a;
import org.p004c.p005e.p007b.C3495d;

@Deprecated
/* renamed from: org.c.b.d.h */
public class C3436h extends C3319s implements C0128e, C0129g {
    private final List<Method> f15949a;
    private C3445q f15950b;

    public C3436h(Class<?> cls) {
        this.f15950b = new C3445q(cls);
        this.f15949a = m18752a();
        m18759b();
    }

    private void m18751a(C3495d c3495d, C3507d c3507d, Throwable th) {
        c3495d.m19060b(c3507d);
        c3495d.m19054a(new C3493a(c3507d, th));
        c3495d.m19064d(c3507d);
    }

    protected List<Method> m18752a() {
        return this.f15950b.m18788a();
    }

    protected C3446r m18753a(Method method) {
        return new C3446r(method, this.f15950b);
    }

    protected void m18754a(Method method, C3495d c3495d) {
        C3507d c = m18761c(method);
        try {
            new C3439k(m18766f(), m18753a(method), c3495d, c).m18772a();
        } catch (InvocationTargetException e) {
            m18751a(c3495d, c, e.getCause());
        } catch (Throwable e2) {
            m18751a(c3495d, c, e2);
        }
    }

    public void m18755a(C3323a c3323a) {
        Iterator it = this.f15949a.iterator();
        while (it.hasNext()) {
            if (!c3323a.m18372a(m18761c((Method) it.next()))) {
                it.remove();
            }
        }
        if (this.f15949a.isEmpty()) {
            throw new C3490f();
        }
    }

    public void m18756a(C3491h c3491h) {
        Collections.sort(this.f15949a, new C3438j(this, c3491h));
    }

    public void m18757a(C3495d c3495d) {
        new C3407a(c3495d, this.f15950b, m18763d(), new C3437i(this, c3495d)).m18678b();
    }

    protected String m18758b(Method method) {
        return method.getName();
    }

    protected void m18759b() {
        C3443o c3443o = new C3443o(this.f15950b);
        c3443o.m18780c();
        c3443o.m18781d();
    }

    protected void m18760b(C3495d c3495d) {
        for (Method a : this.f15949a) {
            m18754a(a, c3495d);
        }
    }

    protected C3507d m18761c(Method method) {
        return C3507d.m19086a(m18767g().m18793e(), m18758b(method), m18764d(method));
    }

    protected Annotation[] m18762c() {
        return this.f15950b.m18793e().getAnnotations();
    }

    public C3507d m18763d() {
        C3507d a = C3507d.m19090a(m18765e(), m18762c());
        for (Method c : this.f15949a) {
            a.m19092a(m18761c(c));
        }
        return a;
    }

    protected Annotation[] m18764d(Method method) {
        return method.getAnnotations();
    }

    protected String m18765e() {
        return m18767g().m18794f();
    }

    protected Object m18766f() {
        return m18767g().m18792d().newInstance(new Object[0]);
    }

    protected C3445q m18767g() {
        return this.f15950b;
    }
}
