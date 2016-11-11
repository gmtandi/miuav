package com.baidu.tts.p038d;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.model.Conditions;
import com.baidu.tts.client.model.DownloadHandler;
import com.baidu.tts.client.model.ModelBags;
import com.baidu.tts.client.model.ModelFileBags;
import com.baidu.tts.database.C0782a;
import com.baidu.tts.p025i.C0673b;
import com.baidu.tts.p038d.p039a.C0762b;
import com.baidu.tts.p038d.p039a.C0763c;
import com.baidu.tts.p038d.p040b.C0770a;
import com.baidu.tts.p038d.p040b.C0771b;
import com.baidu.tts.p038d.p040b.C0772c;
import com.baidu.tts.p038d.p040b.C0773d;
import com.baidu.tts.p041e.C0799l;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p044g.p045a.C0807c;
import com.baidu.tts.p049k.C0822a;
import com.baidu.tts.tools.DataTool;
import com.baidu.tts.tools.StringTool;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* renamed from: com.baidu.tts.d.c */
public class C0777c implements C0673b {
    private static volatile C0777c f4307a;
    private C0822a f4308b;
    private C0770a f4309c;
    private C0762b f4310d;
    private ExecutorService f4311e;

    /* renamed from: com.baidu.tts.d.c.a */
    public class C0776a implements Callable<C0769a> {
        final /* synthetic */ C0777c f4305a;
        private DownloadHandler f4306b;

        public C0776a(C0777c c0777c, DownloadHandler downloadHandler) {
            this.f4305a = c0777c;
            this.f4306b = downloadHandler;
        }

        public C0769a m6683a() {
            C0769a c0769a = new C0769a();
            C0782a e = this.f4305a.f4308b.m6830e();
            String modelId = this.f4306b.getModelId();
            C0773d b = this.f4305a.f4309c.m6619b(modelId);
            try {
                b.m6661c(this.f4306b);
                if (!b.m6656a(e)) {
                    Conditions conditions = new Conditions();
                    conditions.appendId(modelId);
                    ModelBags modelBags = (ModelBags) this.f4305a.f4308b.m6818a(conditions).get();
                    if (modelBags == null || modelBags.isEmpty()) {
                        b.m6652a(this.f4306b, C0807c.m6758a().m6762a(C0802n.MODEL_BAGS_EMPTY, "modelId=" + modelId));
                        return c0769a;
                    }
                    b.m6653a(modelBags, e);
                }
                Set<String> f = b.m6664f();
                if (DataTool.isSetEmpty(f)) {
                    b.m6652a(this.f4306b, C0807c.m6758a().m6762a(C0802n.MODEL_DB_MODEL_INVALID, "modelId=" + modelId));
                    return c0769a;
                }
                for (String str : f) {
                    C0772c c = this.f4305a.f4309c.m6621c(str);
                    if (!c.m6646a(e)) {
                        Set hashSet = new HashSet();
                        hashSet.add(str);
                        ModelFileBags modelFileBags = (ModelFileBags) this.f4305a.f4308b.m6819a(hashSet).get();
                        if (modelFileBags == null || modelFileBags.isEmpty()) {
                            b.m6652a(this.f4306b, C0807c.m6758a().m6762a(C0802n.MODEL_FILE_BAG_EMPTY, "fileId=" + str));
                            return c0769a;
                        }
                        modelFileBags.generateAbsPath(this.f4305a.f4308b.m6829d());
                        c.m6645a(modelFileBags, e);
                    }
                }
                b.m6662d();
                f = b.m6657b();
                if (DataTool.isSetEmpty(f)) {
                    b.m6652a(this.f4306b, C0807c.m6758a().m6762a(C0802n.MODEL_DB_MODEL_FILE_PATHS_INVALID, "modelId=" + modelId));
                    return c0769a;
                }
                for (String str2 : f) {
                    if (!StringTool.isEmpty(str2)) {
                        C0771b a = this.f4305a.f4309c.m6615a(str2);
                        a.m6631a(modelId);
                        boolean a2 = a.m6632a(e);
                        String c2 = a.m6636c();
                        LoggerProxy.m6515d("Downloader", "isNeedDownload=" + a2 + "--fileId=" + c2);
                        if (a2) {
                            if (a.m6639e()) {
                                a.m6640f();
                            }
                            C0763c c0763c = new C0763c();
                            c0763c.m6590a(a);
                            if (Thread.currentThread().isInterrupted()) {
                                return null;
                            }
                            LoggerProxy.m6515d("Downloader", "before download fileId=" + c2);
                            a.m6630a(this.f4305a.f4310d.m6569a(c0763c));
                            c0769a.m6611a(true);
                        } else {
                            c0769a.m6610a(str2, a.m6638d());
                        }
                    }
                }
                if (!c0769a.m6612a() && c0769a.m6613b()) {
                    this.f4306b.updateProgress(b);
                    b.m6652a(this.f4306b, C0807c.m6758a().m6762a(C0802n.MODEL_EXISTS, "modelId=" + modelId));
                }
                return c0769a;
            } catch (Exception e2) {
                LoggerProxy.m6515d("Downloader", "exception=" + e2.toString());
                b.m6652a(this.f4306b, C0807c.m6758a().m6762a(C0802n.MODEL_CHECK_EXCEPTION, "modelId=" + modelId));
                return c0769a;
            }
        }

        public /* synthetic */ Object call() {
            return m6683a();
        }
    }

    static {
        f4307a = null;
    }

    private C0777c() {
        this.f4309c = C0770a.m6614a();
        this.f4310d = new C0762b();
    }

    public static C0777c m6684a() {
        if (f4307a == null) {
            synchronized (C0777c.class) {
                if (f4307a == null) {
                    f4307a = new C0777c();
                }
            }
        }
        return f4307a;
    }

    private synchronized ExecutorService m6688h() {
        if (this.f4311e == null) {
            this.f4311e = Executors.newSingleThreadExecutor();
        }
        return this.f4311e;
    }

    public synchronized DownloadHandler m6689a(DownloadHandler downloadHandler) {
        LoggerProxy.m6515d("Downloader", "download handler=" + downloadHandler);
        downloadHandler.setCheckFuture(m6688h().submit(new C0776a(this, downloadHandler)));
        return downloadHandler;
    }

    public void m6690a(C0822a c0822a) {
        this.f4308b = c0822a;
        this.f4309c.m6617a(this.f4308b.m6830e());
        this.f4310d.m6571a(this.f4308b);
    }

    public synchronized TtsError m6691b() {
        return null;
    }

    public synchronized void m6692c() {
        this.f4310d.m6145c();
    }

    public synchronized void m6693d() {
        this.f4310d.m6146d();
    }

    public synchronized void m6694e() {
        LoggerProxy.m6515d("Downloader", "enter stop");
        this.f4309c.m6622c();
        if (this.f4311e != null) {
            if (!this.f4311e.isShutdown()) {
                this.f4311e.shutdownNow();
                this.f4310d.m6147e();
                LoggerProxy.m6515d("Downloader", "after engine stop");
            }
            try {
                LoggerProxy.m6515d("Downloader", "before awaitTermination");
                LoggerProxy.m6515d("Downloader", "after awaitTermination isTermination=" + this.f4311e.awaitTermination(C0799l.DEFAULT.m6747a(), TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
            }
            this.f4311e = null;
        }
        LoggerProxy.m6515d("Downloader", "end stop");
    }

    public synchronized void m6695f() {
    }

    public synchronized void m6696g() {
        m6688h();
        this.f4310d.m6157y();
    }
}
