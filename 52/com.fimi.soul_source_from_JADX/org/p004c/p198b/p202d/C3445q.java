package org.p004c.p198b.p202d;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.p004c.C3457b;
import org.p004c.C3550f;
import org.p004c.C3561g;
import org.p004c.C3570p;
import org.p004c.p198b.C3451h;

@Deprecated
/* renamed from: org.c.b.d.q */
public class C3445q {
    private final Class<?> f15965a;

    public C3445q(Class<?> cls) {
        this.f15965a = cls;
    }

    private boolean m18784a(Method method, Method method2) {
        if (!method2.getName().equals(method.getName()) || method2.getParameterTypes().length != method.getParameterTypes().length) {
            return false;
        }
        for (int i = 0; i < method2.getParameterTypes().length; i++) {
            if (!method2.getParameterTypes()[i].equals(method.getParameterTypes()[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean m18785a(Method method, List<Method> list) {
        for (Method a : list) {
            if (m18784a(method, a)) {
                return true;
            }
        }
        return false;
    }

    private boolean m18786b(Class<? extends Annotation> cls) {
        return cls.equals(C3550f.class) || cls.equals(C3561g.class);
    }

    private List<Class<?>> m18787c(Class<?> cls) {
        List arrayList = new ArrayList();
        Class superclass;
        while (superclass != null) {
            arrayList.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return arrayList;
    }

    public List<Method> m18788a() {
        return m18789a(C3570p.class);
    }

    public List<Method> m18789a(Class<? extends Annotation> cls) {
        List arrayList = new ArrayList();
        for (Class a : m18787c(this.f15965a)) {
            for (Method method : C3451h.m18812a(a)) {
                if (!(method.getAnnotation(cls) == null || m18785a(method, arrayList))) {
                    arrayList.add(method);
                }
            }
        }
        if (m18786b(cls)) {
            Collections.reverse(arrayList);
        }
        return arrayList;
    }

    List<Method> m18790b() {
        return m18789a(C3561g.class);
    }

    List<Method> m18791c() {
        return m18789a(C3457b.class);
    }

    public Constructor<?> m18792d() {
        return this.f15965a.getConstructor(new Class[0]);
    }

    public Class<?> m18793e() {
        return this.f15965a;
    }

    public String m18794f() {
        return this.f15965a.getName();
    }
}
