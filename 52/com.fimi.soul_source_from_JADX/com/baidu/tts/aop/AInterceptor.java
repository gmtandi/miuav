package com.baidu.tts.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class AInterceptor implements IInterceptor {
    protected List<String> f4040a;

    public AInterceptor() {
        this.f4040a = new ArrayList();
        m6074a();
    }

    private boolean m6072a(String str) {
        return this.f4040a.contains(str);
    }

    protected abstract Object m6073a(Object obj, Method method, Object[] objArr);

    protected abstract void m6074a();

    public Object after(Object obj, Method method, Object[] objArr, Object obj2) {
        return AInterceptorHandler.DEFAULT;
    }

    public Object before(Object obj, Method method, Object[] objArr) {
        return m6072a(method.getName()) ? m6073a(obj, method, objArr) : AInterceptorHandler.DEFAULT;
    }
}
