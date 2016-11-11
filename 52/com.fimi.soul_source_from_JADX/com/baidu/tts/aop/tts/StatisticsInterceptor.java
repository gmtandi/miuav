package com.baidu.tts.aop.tts;

import com.baidu.tts.aop.AInterceptor;
import com.baidu.tts.aop.AInterceptorHandler;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import java.lang.reflect.Method;

public class StatisticsInterceptor extends AInterceptor {
    protected Object m6087a(Object obj, Method method, Object[] objArr) {
        LoggerProxy.m6515d("StatisticsInterceptor", "statistics");
        return AInterceptorHandler.DEFAULT;
    }

    protected void m6088a() {
        this.a.add("speak");
        this.a.add("synthesize");
    }
}
