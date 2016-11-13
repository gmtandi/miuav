package com.baidu.tts.aop;

import java.lang.reflect.InvocationHandler;
import java.util.List;

public interface IInterceptorHandler extends InvocationHandler {
    Object bind(Object obj, List<IInterceptor> list);

    boolean canIntercept(String str);

    void registerMethod(String str);

    void registerMethods();

    void unregisterMethod(String str);
}
