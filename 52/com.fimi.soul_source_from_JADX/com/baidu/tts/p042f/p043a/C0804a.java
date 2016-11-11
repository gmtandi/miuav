package com.baidu.tts.p042f.p043a;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import java.util.concurrent.ThreadFactory;

/* renamed from: com.baidu.tts.f.a.a */
public class C0804a implements ThreadFactory {
    private String f4552a;
    private int f4553b;

    public C0804a(String str) {
        this.f4552a = str;
    }

    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        this.f4553b++;
        String str = this.f4552a + "(" + this.f4553b + ")";
        thread.setName(str);
        LoggerProxy.m6515d("NameThreadFactory", "threadName=" + str);
        return thread;
    }
}
