package com.baidu.tts.aop.tts;

import com.baidu.tts.aop.AInterceptor;
import com.baidu.tts.aop.AInterceptorHandler;
import com.baidu.tts.aop.ttslistener.TtsListener;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import com.baidu.tts.p051n.p052a.C0854c;
import com.baidu.tts.tools.ResourceTools;
import java.lang.reflect.Method;

public class ArgsCheckInterceptor extends AInterceptor {
    private Object m6077a(Object obj, C0829i c0829i, C0802n c0802n) {
        C0828h b = C0828h.m6852b(c0829i);
        b.m6855a(C0807c.m6758a().m6765b(c0802n));
        m6078a(obj, b);
        return AInterceptorHandler.END;
    }

    private void m6078a(Object obj, C0828h c0828h) {
        TtsListener ttsListener = ((C0854c) obj).getTtsListener();
        if (ttsListener != null) {
            ttsListener.onError(c0828h);
        }
    }

    protected Object m6079a(Object obj, Method method, Object[] objArr) {
        C0829i c0829i = (C0829i) objArr[0];
        String c = c0829i.m6874c();
        LoggerProxy.m6515d("ArgsCheckInterceptor", "text=" + c);
        return ResourceTools.isTextValid(c) == null ? AInterceptorHandler.DEFAULT : m6077a(obj, c0829i, C0802n.TEXT_IS_TOO_LONG);
    }

    protected void m6080a() {
        this.a.add("speak");
        this.a.add("synthesize");
    }
}
