package com.baidu.tts.aop;

import java.util.List;

public abstract class AProxyFactory<T> implements IProxyFactory<T> {
    public T makeProxy() {
        T createProxied = createProxied();
        IInterceptorHandler createInterceptorHandler = createInterceptorHandler();
        List createInterceptors = createInterceptors();
        return (createInterceptorHandler == null || createInterceptors == null) ? createProxied : createInterceptorHandler.bind(createProxied, createInterceptors);
    }
}
