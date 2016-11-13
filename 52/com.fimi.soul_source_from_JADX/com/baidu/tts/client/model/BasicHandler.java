package com.baidu.tts.client.model;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p041e.C0799l;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class BasicHandler<T> {
    private FutureTask<T> f4221a;

    public BasicHandler(FutureTask<T> futureTask) {
        this.f4221a = futureTask;
    }

    public boolean cancel() {
        return this.f4221a.cancel(true);
    }

    public T get() {
        T t = null;
        try {
            LoggerProxy.m6515d("BasicHandler", "before get");
            t = this.f4221a.get(C0799l.DEFAULT.m6747a(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            LoggerProxy.m6515d("BasicHandler", e.toString());
        } catch (ExecutionException e2) {
            LoggerProxy.m6515d("BasicHandler", e2.getCause().toString());
        } catch (TimeoutException e3) {
            LoggerProxy.m6515d("BasicHandler", e3.toString());
        }
        return t;
    }

    public void start() {
        new Thread(this.f4221a).start();
    }
}
