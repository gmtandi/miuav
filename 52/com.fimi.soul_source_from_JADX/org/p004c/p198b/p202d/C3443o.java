package org.p004c.p198b.p202d;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.p004c.C3383a;
import org.p004c.C3457b;
import org.p004c.C3550f;
import org.p004c.C3561g;
import org.p004c.C3570p;

@Deprecated
/* renamed from: org.c.b.d.o */
public class C3443o {
    private final List<Throwable> f15963a;
    private C3445q f15964b;

    public C3443o(C3445q c3445q) {
        this.f15963a = new ArrayList();
        this.f15964b = c3445q;
    }

    private void m18777a(Class<? extends Annotation> cls, boolean z) {
        for (Method method : this.f15964b.m18789a(cls)) {
            if (Modifier.isStatic(method.getModifiers()) != z) {
                this.f15963a.add(new Exception("Method " + method.getName() + "() " + (z ? "should" : "should not") + " be static"));
            }
            if (!Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
                this.f15963a.add(new Exception("Class " + method.getDeclaringClass().getName() + " should be public"));
            }
            if (!Modifier.isPublic(method.getModifiers())) {
                this.f15963a.add(new Exception("Method " + method.getName() + " should be public"));
            }
            if (method.getReturnType() != Void.TYPE) {
                this.f15963a.add(new Exception("Method " + method.getName() + " should be void"));
            }
            if (method.getParameterTypes().length != 0) {
                this.f15963a.add(new Exception("Method " + method.getName() + " should have no parameters"));
            }
        }
    }

    public void m18778a() {
        m18777a(C3383a.class, false);
        m18777a(C3550f.class, false);
        m18777a(C3570p.class, false);
        if (this.f15964b.m18789a(C3570p.class).size() == 0) {
            this.f15963a.add(new Exception("No runnable methods"));
        }
    }

    public void m18779b() {
        m18777a(C3561g.class, true);
        m18777a(C3457b.class, true);
    }

    public List<Throwable> m18780c() {
        m18782e();
        m18779b();
        m18778a();
        return this.f15963a;
    }

    public void m18781d() {
        if (!this.f15963a.isEmpty()) {
            throw new C3432d(this.f15963a);
        }
    }

    public void m18782e() {
        try {
            this.f15964b.m18792d();
        } catch (Throwable e) {
            this.f15963a.add(new Exception("Test class should have public zero-argument constructor", e));
        }
    }
}
