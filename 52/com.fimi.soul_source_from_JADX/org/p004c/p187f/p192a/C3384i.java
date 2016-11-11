package org.p004c.p187f.p192a;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.p004c.p005e.C3319s;
import org.p004c.p198b.p202d.C3421b;

/* renamed from: org.c.f.a.i */
public abstract class C3384i {
    private final Set<Class<?>> f15883a;

    public C3384i() {
        this.f15883a = new HashSet();
    }

    private List<C3319s> m18609a(Class<?>[] clsArr) {
        List arrayList = new ArrayList();
        for (Class c : clsArr) {
            C3319s c2 = m18613c(c);
            if (c2 != null) {
                arrayList.add(c2);
            }
        }
        return arrayList;
    }

    public List<C3319s> m18610a(Class<?> cls, List<Class<?>> list) {
        return m18611a((Class) cls, (Class[]) list.toArray(new Class[0]));
    }

    public List<C3319s> m18611a(Class<?> cls, Class<?>[] clsArr) {
        m18614d(cls);
        try {
            List<C3319s> a = m18609a((Class[]) clsArr);
            return a;
        } finally {
            m18615e(cls);
        }
    }

    public abstract C3319s m18612a(Class<?> cls);

    public C3319s m18613c(Class<?> cls) {
        try {
            return m18612a((Class) cls);
        } catch (Throwable th) {
            return new C3421b(cls, th);
        }
    }

    Class<?> m18614d(Class<?> cls) {
        if (this.f15883a.add(cls)) {
            return cls;
        }
        throw new C3526f(String.format("class '%s' (possibly indirectly) contains itself as a SuiteClass", new Object[]{cls.getName()}));
    }

    void m18615e(Class<?> cls) {
        this.f15883a.remove(cls);
    }
}
