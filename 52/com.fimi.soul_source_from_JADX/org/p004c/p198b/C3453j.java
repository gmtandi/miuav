package org.p004c.p198b;

import java.lang.reflect.Method;
import java.util.Comparator;

/* renamed from: org.c.b.j */
class C3453j implements Comparator<Method> {
    C3453j() {
    }

    public int m18814a(Method method, Method method2) {
        int compareTo = method.getName().compareTo(method2.getName());
        return compareTo != 0 ? compareTo : method.toString().compareTo(method2.toString());
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m18814a((Method) obj, (Method) obj2);
    }
}
