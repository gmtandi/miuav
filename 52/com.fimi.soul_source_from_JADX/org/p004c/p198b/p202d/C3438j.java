package org.p004c.p198b.p202d;

import java.lang.reflect.Method;
import java.util.Comparator;
import org.p004c.p005e.p006a.C3491h;

/* renamed from: org.c.b.d.j */
class C3438j implements Comparator<Method> {
    final /* synthetic */ C3491h f15953a;
    final /* synthetic */ C3436h f15954b;

    C3438j(C3436h c3436h, C3491h c3491h) {
        this.f15954b = c3436h;
        this.f15953a = c3491h;
    }

    public int m18768a(Method method, Method method2) {
        return this.f15953a.m19042a(this.f15954b.m18761c(method), this.f15954b.m18761c(method2));
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m18768a((Method) obj, (Method) obj2);
    }
}
