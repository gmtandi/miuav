package com.baidu.tts.aop;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public abstract class AInterceptorHandler implements IInterceptorHandler {
    public static final Object DEFAULT;
    public static final Object END;
    protected List<IInterceptor> f4041a;
    protected Object f4042b;
    protected List<String> f4043c;

    static {
        DEFAULT = Integer.valueOf(0);
        END = Integer.valueOf(1);
    }

    public AInterceptorHandler() {
        this.f4043c = new ArrayList();
    }

    protected Object m6075a(Object obj, Method method, Object[] objArr) {
        Object obj2 = DEFAULT;
        int size = this.f4041a.size();
        Object obj3 = obj2;
        for (int i = 0; i < size; i++) {
            obj3 = ((IInterceptor) this.f4041a.get(i)).before(obj, method, objArr);
            if (obj3.equals(END)) {
                break;
            }
        }
        return obj3;
    }

    protected Object m6076a(Object obj, Method method, Object[] objArr, Object obj2) {
        Object obj3 = DEFAULT;
        int size = this.f4041a.size() - 1;
        while (size >= 0) {
            Object after = ((IInterceptor) this.f4041a.get(size)).after(obj, method, objArr, obj2);
            size--;
            obj3 = after;
        }
        return obj3;
    }

    public Object bind(Object obj, List<IInterceptor> list) {
        this.f4042b = obj;
        this.f4041a = list;
        Class cls = this.f4042b.getClass();
        Object newProxyInstance = Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), this);
        LoggerProxy.m6515d("AInterceptorHandler", "proxy=" + newProxyInstance);
        return newProxyInstance;
    }

    public boolean canIntercept(String str) {
        return this.f4043c.contains(str);
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        if (!canIntercept(method.getName())) {
            return method.invoke(this.f4042b, objArr);
        }
        if (m6075a(this.f4042b, method, objArr).equals(END)) {
            return null;
        }
        Object invoke = method.invoke(this.f4042b, objArr);
        LoggerProxy.m6515d("AInterceptorHandler", "afterResult=" + m6076a(this.f4042b, method, objArr, invoke));
        return invoke;
    }

    public void registerMethod(String str) {
        if (str != null) {
            this.f4043c.add(str);
        }
    }

    public void unregisterMethod(String str) {
        if (str != null) {
            this.f4043c.remove(str);
        }
    }
}
