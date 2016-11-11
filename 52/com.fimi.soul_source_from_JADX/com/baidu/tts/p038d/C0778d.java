package com.baidu.tts.p038d;

import com.baidu.tts.client.model.DownloadHandler;
import com.baidu.tts.p049k.C0822a;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: com.baidu.tts.d.d */
public class C0778d {
    private C0777c f4312a;
    private ConcurrentMap<String, DownloadHandler> f4313b;

    public C0778d() {
        this.f4313b = new ConcurrentHashMap();
        this.f4312a = C0777c.m6684a();
    }

    private void m6697c() {
        for (DownloadHandler stop : this.f4313b.values()) {
            stop.stop();
        }
    }

    public DownloadHandler m6698a(C0775b c0775b) {
        if (c0775b != null && c0775b.m6681b()) {
            DownloadHandler a = m6699a(c0775b.m6678a());
            if (a != null) {
                a.reset(c0775b);
                return this.f4312a.m6689a(a);
            }
        }
        return null;
    }

    public synchronized DownloadHandler m6699a(String str) {
        DownloadHandler downloadHandler;
        try {
            downloadHandler = (DownloadHandler) this.f4313b.get(str);
            if (downloadHandler == null) {
                downloadHandler = new DownloadHandler();
                this.f4313b.put(str, downloadHandler);
            }
        } catch (Exception e) {
            downloadHandler = null;
        }
        return downloadHandler;
    }

    public void m6700a() {
        this.f4312a.m6696g();
    }

    public void m6701a(C0822a c0822a) {
        this.f4312a.m6690a(c0822a);
    }

    public void m6702b() {
        m6697c();
        this.f4312a.m6694e();
    }
}
