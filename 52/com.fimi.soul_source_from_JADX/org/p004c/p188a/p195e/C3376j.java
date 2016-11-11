package org.p004c.p188a.p195e;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.p004c.p187f.C3375b;
import org.p004c.p187f.p192a.C3377k;
import org.p004c.p187f.p192a.C3524d;
import org.p004c.p187f.p192a.C3528l;

/* renamed from: org.c.a.e.j */
public class C3376j extends C3375b {
    public C3376j(Class<?> cls) {
        super(cls);
    }

    private void m18581a(Class<? extends C3356e> cls, List<Throwable> list) {
        Constructor[] constructors = cls.getConstructors();
        if (constructors.length != 1) {
            list.add(new Error("ParameterSupplier " + cls.getName() + " must have only one constructor (either empty or taking only a TestClass)"));
            return;
        }
        Class[] parameterTypes = constructors[0].getParameterTypes();
        if (parameterTypes.length != 0 && !parameterTypes[0].equals(C3528l.class)) {
            list.add(new Error("ParameterSupplier " + cls.getName() + " constructor must take either nothing or a single TestClass instance"));
        }
    }

    private void m18582i(List<Throwable> list) {
        for (Field field : m18348g().m19221d().getDeclaredFields()) {
            if (field.getAnnotation(C3366a.class) != null || field.getAnnotation(C3369b.class) != null) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    list.add(new Error("DataPoint field " + field.getName() + " must be static"));
                }
                if (!Modifier.isPublic(field.getModifiers())) {
                    list.add(new Error("DataPoint field " + field.getName() + " must be public"));
                }
            }
        }
    }

    private void m18583j(List<Throwable> list) {
        for (Method method : m18348g().m19221d().getDeclaredMethods()) {
            if (method.getAnnotation(C3366a.class) != null || method.getAnnotation(C3369b.class) != null) {
                if (!Modifier.isStatic(method.getModifiers())) {
                    list.add(new Error("DataPoint method " + method.getName() + " must be static"));
                }
                if (!Modifier.isPublic(method.getModifiers())) {
                    list.add(new Error("DataPoint method " + method.getName() + " must be public"));
                }
            }
        }
    }

    protected List<C3524d> m18584a() {
        List<C3524d> arrayList = new ArrayList(super.m18555a());
        Collection b = m18348g().m19217b(C3382o.class);
        arrayList.removeAll(b);
        arrayList.addAll(b);
        return arrayList;
    }

    public C3377k m18585a(C3524d c3524d) {
        return new C3378k(c3524d, m18348g());
    }

    protected void m18586a(List<Throwable> list) {
        super.m18561a((List) list);
        m18582i(list);
        m18583j(list);
    }

    protected void m18587b(List<Throwable> list) {
        m18577e((List) list);
    }

    protected void m18588c(List<Throwable> list) {
        for (C3524d c3524d : m18584a()) {
            if (c3524d.m19184a(C3382o.class) != null) {
                c3524d.m19192b(false, list);
                c3524d.m19191b((List) list);
            } else {
                c3524d.m19185a(false, (List) list);
            }
            Iterator it = C3371d.m18529a(c3524d.m19194d()).iterator();
            while (it.hasNext()) {
                C3372f c3372f = (C3372f) ((C3371d) it.next()).m18541d(C3372f.class);
                if (c3372f != null) {
                    m18581a(c3372f.m18543a(), list);
                }
            }
        }
    }
}
