package com.baidu.tts.aop.ttslistener;

import android.text.TextUtils;
import com.baidu.tts.aop.AInterceptor;
import com.baidu.tts.aop.AInterceptorHandler;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p034l.C0828h;
import java.lang.reflect.Method;

public class ProgressCorrectInterceptor extends AInterceptor {
    protected Object m6089a(Object obj, Method method, Object[] objArr) {
        C0828h c0828h = (C0828h) objArr[0];
        if (c0828h != null) {
            Object b = c0828h.m6867e().m6872b();
            if (!TextUtils.isEmpty(b)) {
                int length = b.length();
                int c = c0828h.m6863c();
                int i = c > length ? c - length : 0;
                LoggerProxy.m6515d("ProgressCorrectInterceptor", "prefixLength=" + length + "--progress=" + c);
                c0828h = (C0828h) c0828h.m6300y();
                c0828h.m6864c(i);
                objArr[0] = c0828h;
            }
        }
        return AInterceptorHandler.DEFAULT;
    }

    protected void m6090a() {
        this.a.add("onSynthesizeDataArrived");
        this.a.add("onPlayProgressUpdate");
    }
}
