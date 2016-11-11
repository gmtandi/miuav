package com.baidu.tts.aop.tts;

import com.baidu.tts.aop.AInterceptor;
import com.baidu.tts.aop.AInterceptorHandler;
import com.baidu.tts.auth.C0689a;
import com.baidu.tts.auth.C0693b.C0691a;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p034l.C0831j;
import com.baidu.tts.p041e.C0800m;
import com.baidu.tts.p051n.p052a.C0854c;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import org.codehaus.jackson.smile.SmileConstants;

public class OfflineAuthNotificationInterceptor extends AInterceptor {
    private AtomicInteger f4045b;

    /* renamed from: com.baidu.tts.aop.tts.OfflineAuthNotificationInterceptor.1 */
    /* synthetic */ class C06821 {
        static final /* synthetic */ int[] f4044a;

        static {
            f4044a = new int[C0800m.values().length];
            try {
                f4044a[C0800m.MIX.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4044a[C0800m.OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public OfflineAuthNotificationInterceptor() {
        this.f4045b = new AtomicInteger(-1);
    }

    private Object m6083a(C0854c c0854c, C0831j c0831j, C0829i c0829i) {
        m6084a(C0689a.m6095a().m6102a(c0831j.m6886d()), c0829i);
        return AInterceptorHandler.DEFAULT;
    }

    private void m6084a(C0691a c0691a, C0829i c0829i) {
        if (c0691a.m6113d()) {
            int a = c0691a.m6106a();
            c0829i.m6871a(String.format(Locale.US, "\u767e\u5ea6\u8bed\u97f3\u8bd5\u7528\u670d\u52a1%d\u5929\u540e\u5230\u671f\uff0c", new Object[]{Integer.valueOf(a)}));
        }
        if (c0691a.m6115f()) {
            c0829i.m6871a("\u767e\u5ea6\u8bed\u97f3\u8bd5\u7528\u670d\u52a1\u5df2\u7ecf\u5230\u671f\uff0c\u8bf7\u53ca\u65f6\u66f4\u65b0\u6388\u6743\uff0c");
        }
        c0829i.m6869a();
    }

    protected Object m6085a(Object obj, Method method, Object[] objArr) {
        C0854c c0854c = (C0854c) obj;
        if (c0854c.m6946q()) {
            C0800m mode = c0854c.getMode();
            if (mode == null) {
                c0854c.m6945p();
                return AInterceptorHandler.END;
            }
            switch (C06821.f4044a[mode.ordinal()]) {
                case SmileConstants.TOKEN_MISC_INTEGER_64 /*1*/:
                case SmileConstants.TOKEN_MISC_INTEGER_BIG /*2*/:
                    int incrementAndGet = this.f4045b.incrementAndGet();
                    LoggerProxy.m6515d("OfflineAuthNotificationInterceptor", "currentCount=" + incrementAndGet);
                    if (incrementAndGet % 20 == 0) {
                        C0829i c0829i = (C0829i) objArr[0];
                        C0831j ttsParams = c0854c.getTtsParams();
                        if (ttsParams != null) {
                            return m6083a(c0854c, ttsParams, c0829i);
                        }
                        c0854c.m6945p();
                        return AInterceptorHandler.END;
                    }
                    break;
            }
            return AInterceptorHandler.DEFAULT;
        }
        c0854c.m6945p();
        return AInterceptorHandler.END;
    }

    protected void m6086a() {
        this.a.add("speak");
    }
}
