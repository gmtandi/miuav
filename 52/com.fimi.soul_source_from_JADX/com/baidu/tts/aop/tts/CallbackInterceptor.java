package com.baidu.tts.aop.tts;

import com.baidu.tts.aop.AInterceptor;
import com.baidu.tts.aop.AInterceptorHandler;
import com.baidu.tts.aop.ttslistener.TtsListener;
import com.baidu.tts.aop.ttslistener.TtsListenerFactory;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import java.lang.reflect.Method;

public class CallbackInterceptor extends AInterceptor {
    protected Object m6081a(Object obj, Method method, Object[] objArr) {
        LoggerProxy.m6515d("CallbackInterceptor", "method=" + method.getName());
        objArr[0] = (TtsListener) new TtsListenerFactory((TtsListener) objArr[0]).makeProxy();
        return AInterceptorHandler.DEFAULT;
    }

    protected void m6082a() {
        this.a.add("setTtsListener");
    }
}
