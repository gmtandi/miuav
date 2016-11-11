package org.p004c.p198b.p200b;

import java.lang.reflect.Method;
import org.p004c.p198b.C3451h;
import org.p184b.C3276b;

@Deprecated
/* renamed from: org.c.b.b.d */
public abstract class C3397d<T> extends C3276b<T> {
    private Class<?> f15893a;

    protected C3397d() {
        this.f15893a = C3397d.m18655a(getClass());
    }

    protected C3397d(Class<T> cls) {
        this.f15893a = cls;
    }

    private static Class<?> m18655a(Class<?> cls) {
        Class superclass;
        while (superclass != Object.class) {
            for (Method method : C3451h.m18812a(superclass)) {
                if (C3397d.m18656a(method)) {
                    return method.getParameterTypes()[0];
                }
            }
            superclass = superclass.getSuperclass();
        }
        throw new Error("Cannot determine correct type for matchesSafely() method.");
    }

    private static boolean m18656a(Method method) {
        return method.getName().equals("matchesSafely") && method.getParameterTypes().length == 1 && !method.isSynthetic();
    }

    public final boolean m18657a(Object obj) {
        return obj != null && this.f15893a.isInstance(obj) && m18658b(obj);
    }

    public abstract boolean m18658b(T t);
}
