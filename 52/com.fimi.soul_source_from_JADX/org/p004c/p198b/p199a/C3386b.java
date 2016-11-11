package org.p004c.p198b.p199a;

import java.lang.reflect.Modifier;
import org.p004c.p005e.C3319s;
import org.p004c.p005e.C3519r;
import org.p004c.p187f.p192a.C3384i;
import org.p004c.p187f.p192a.C3526f;

/* renamed from: org.c.b.a.b */
public class C3386b extends C3384i {
    private static final String f15885a = "Custom runner class %s should have a public constructor with signature %s(Class testClass)";
    private final C3384i f15886b;

    public C3386b(C3384i c3384i) {
        this.f15886b = c3384i;
    }

    private Class<?> m18622b(Class<?> cls) {
        return (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) ? null : cls.getEnclosingClass();
    }

    public C3319s m18623a(Class<?> cls) {
        Class cls2 = cls;
        while (cls2 != null) {
            C3519r c3519r = (C3519r) cls2.getAnnotation(C3519r.class);
            if (c3519r != null) {
                return m18624a(c3519r.m19160a(), cls);
            }
            cls2 = m18622b(cls2);
        }
        return null;
    }

    public C3319s m18624a(Class<? extends C3319s> cls, Class<?> cls2) {
        try {
            return (C3319s) cls.getConstructor(new Class[]{Class.class}).newInstance(new Object[]{cls2});
        } catch (NoSuchMethodException e) {
            try {
                return (C3319s) cls.getConstructor(new Class[]{Class.class, C3384i.class}).newInstance(new Object[]{cls2, this.f15886b});
            } catch (NoSuchMethodException e2) {
                String simpleName = cls.getSimpleName();
                throw new C3526f(String.format(f15885a, new Object[]{simpleName, simpleName}));
            }
        }
    }
}
