package com.baidu.tts.p038d.p039a;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p038d.p039a.C0762b.C0761a;
import java.util.concurrent.Future;

/* renamed from: com.baidu.tts.d.a.e */
public class C0765e {
    private Future<Void> f4276a;
    private C0761a f4277b;

    public void m6599a() {
        LoggerProxy.m6515d("EngineDownloadHandler", "before stop");
        try {
            LoggerProxy.m6515d("EngineDownloadHandler", "stop fileId=" + this.f4277b.m6566c().m6587a());
        } catch (Exception e) {
        }
        if (this.f4276a != null) {
            LoggerProxy.m6515d("EngineDownloadHandler", "unDone = " + this.f4276a.cancel(true));
        }
        if (this.f4277b != null) {
            this.f4277b.m6565b();
        }
        LoggerProxy.m6515d("EngineDownloadHandler", "after stop");
    }

    public void m6600a(C0761a c0761a) {
        this.f4277b = c0761a;
    }

    public void m6601a(Future<Void> future) {
        this.f4276a = future;
    }
}
