package org.p004c.p187f.p192a;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/* renamed from: org.c.f.a.d */
public class C3524d extends C3522c<C3524d> {
    private final Method f16070a;

    public C3524d(Method method) {
        if (method == null) {
            throw new NullPointerException("FrameworkMethod cannot be created without an underlying method.");
        }
        this.f16070a = method;
    }

    private Class<?>[] m19182j() {
        return this.f16070a.getParameterTypes();
    }

    public Object m19183a(Object obj, Object... objArr) {
        return new C3525e(this, obj, objArr).m18672a();
    }

    public <T extends Annotation> T m19184a(Class<T> cls) {
        return this.f16070a.getAnnotation(cls);
    }

    public void m19185a(boolean z, List<Throwable> list) {
        m19192b(z, list);
        if (this.f16070a.getParameterTypes().length != 0) {
            list.add(new Exception("Method " + this.f16070a.getName() + " should have no parameters"));
        }
    }

    @Deprecated
    public boolean m19186a(Type type) {
        return m19182j().length == 0 && (type instanceof Class) && ((Class) type).isAssignableFrom(this.f16070a.getReturnType());
    }

    public boolean m19188a(C3524d c3524d) {
        if (!c3524d.m19190b().equals(m19190b()) || c3524d.m19182j().length != m19182j().length) {
            return false;
        }
        for (int i = 0; i < c3524d.m19182j().length; i++) {
            if (!c3524d.m19182j()[i].equals(m19182j()[i])) {
                return false;
            }
        }
        return true;
    }

    public Annotation[] m19189a() {
        return this.f16070a.getAnnotations();
    }

    public String m19190b() {
        return this.f16070a.getName();
    }

    public void m19191b(List<Throwable> list) {
        new C3527h(this.f16070a).m19204a(list);
    }

    public void m19192b(boolean z, List<Throwable> list) {
        if (m19169g() != z) {
            list.add(new Exception("Method " + this.f16070a.getName() + "() " + (z ? "should" : "should not") + " be static"));
        }
        if (!m19170h()) {
            list.add(new Exception("Method " + this.f16070a.getName() + "() should be public"));
        }
        if (this.f16070a.getReturnType() != Void.TYPE) {
            list.add(new Exception("Method " + this.f16070a.getName() + "() should be void"));
        }
    }

    protected int m19193c() {
        return this.f16070a.getModifiers();
    }

    public Method m19194d() {
        return this.f16070a;
    }

    public Class<?> m19195e() {
        return m19197i();
    }

    public boolean equals(Object obj) {
        return !C3524d.class.isInstance(obj) ? false : ((C3524d) obj).f16070a.equals(this.f16070a);
    }

    public Class<?> m19196f() {
        return this.f16070a.getDeclaringClass();
    }

    public int hashCode() {
        return this.f16070a.hashCode();
    }

    public Class<?> m19197i() {
        return this.f16070a.getReturnType();
    }

    public String toString() {
        return this.f16070a.toString();
    }
}
