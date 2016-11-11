package org.p184b.p186b;

import java.lang.reflect.Method;

/* renamed from: org.b.b.b */
public class C3303b {
    private final String f15811a;
    private final int f15812b;
    private final int f15813c;

    public C3303b(String str, int i, int i2) {
        this.f15811a = str;
        this.f15812b = i;
        this.f15813c = i2;
    }

    public Class<?> m18240a(Class<?> cls) {
        Class superclass;
        while (superclass != Object.class) {
            for (Method method : superclass.getDeclaredMethods()) {
                if (m18241a(method)) {
                    return m18242b(method);
                }
            }
            superclass = superclass.getSuperclass();
        }
        throw new Error("Cannot determine correct type for " + this.f15811a + "() method.");
    }

    protected boolean m18241a(Method method) {
        return method.getName().equals(this.f15811a) && method.getParameterTypes().length == this.f15812b && !method.isSynthetic();
    }

    protected Class<?> m18242b(Method method) {
        return method.getParameterTypes()[this.f15813c];
    }
}
