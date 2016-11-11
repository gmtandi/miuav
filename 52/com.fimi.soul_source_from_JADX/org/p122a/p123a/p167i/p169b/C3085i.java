package org.p122a.p123a.p167i.p169b;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.http.HttpResponse;
import org.p122a.p123a.p150a.C2913c;
import org.p122a.p123a.p152c.p156c.C2946d;
import org.p122a.p123a.p180o.C3237d;

@C2913c
/* renamed from: org.a.a.i.b.i */
class C3085i implements InvocationHandler {
    private final HttpResponse f15278a;

    C3085i(HttpResponse httpResponse) {
        this.f15278a = httpResponse;
    }

    public static C2946d m17377a(HttpResponse httpResponse) {
        return (C2946d) Proxy.newProxyInstance(C3085i.class.getClassLoader(), new Class[]{C2946d.class}, new C3085i(httpResponse));
    }

    public void m17378a() {
        C3237d.m17904b(this.f15278a.getEntity());
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        if (method.getName().equals("close")) {
            m17378a();
            return null;
        }
        try {
            return method.invoke(this.f15278a, objArr);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                throw cause;
            }
            throw e;
        }
    }
}
