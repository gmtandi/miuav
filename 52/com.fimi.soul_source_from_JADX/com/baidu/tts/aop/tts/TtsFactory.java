package com.baidu.tts.aop.tts;

import com.baidu.tts.aop.AProxyFactory;
import com.baidu.tts.aop.IInterceptor;
import com.baidu.tts.aop.IInterceptorHandler;
import com.baidu.tts.p051n.p052a.C0854c;
import java.util.ArrayList;
import java.util.List;

public class TtsFactory extends AProxyFactory<ITts> {
    public IInterceptorHandler createInterceptorHandler() {
        IInterceptorHandler ttsInterceptorHandler = new TtsInterceptorHandler();
        ttsInterceptorHandler.registerMethods();
        return ttsInterceptorHandler;
    }

    public List<IInterceptor> createInterceptors() {
        List<IInterceptor> arrayList = new ArrayList();
        arrayList.add(new ArgsCheckInterceptor());
        arrayList.add(new StatisticsInterceptor());
        arrayList.add(new OfflineAuthNotificationInterceptor());
        arrayList.add(new CallbackInterceptor());
        return arrayList;
    }

    public ITts createProxied() {
        return new C0854c();
    }
}
