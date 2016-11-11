package org.p004c.p198b;

import java.lang.reflect.Method;
import java.util.Comparator;

/* renamed from: org.c.b.i */
class C3452i implements Comparator<Method> {
    C3452i() {
    }

    public int m18813a(Method method, Method method2) {
        int hashCode = method.getName().hashCode();
        int hashCode2 = method2.getName().hashCode();
        return hashCode != hashCode2 ? hashCode < hashCode2 ? -1 : 1 : C3451h.f15970b.compare(method, method2);
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m18813a((Method) obj, (Method) obj2);
    }
}
