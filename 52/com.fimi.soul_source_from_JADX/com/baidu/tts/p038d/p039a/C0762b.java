package com.baidu.tts.p038d.p039a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.model.ModelFileBags;
import com.baidu.tts.loopj.AsyncHttpClient;
import com.baidu.tts.loopj.ResponseHandlerInterface;
import com.baidu.tts.loopj.SyncHttpClient;
import com.baidu.tts.p025i.C0699a;
import com.baidu.tts.p041e.C0799l;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p042f.p043a.C0804a;
import com.baidu.tts.p044g.p045a.C0807c;
import com.baidu.tts.p049k.C0822a;
import com.baidu.tts.tools.FileTools;
import com.baidu.tts.tools.StringTool;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;

/* renamed from: com.baidu.tts.d.a.b */
public class C0762b extends C0699a {
    private volatile C0758a f4268a;
    private C0768i f4269b;
    private C0766f f4270c;
    private C0764d f4271f;
    private C0767h f4272g;
    private ThreadPoolExecutor f4273h;
    private C0822a f4274i;

    /* renamed from: com.baidu.tts.d.a.b.a */
    public class C0761a implements Callable<Void> {
        final /* synthetic */ C0762b f4265a;
        private C0763c f4266b;
        private SyncHttpClient f4267c;

        /* renamed from: com.baidu.tts.d.a.b.a.1 */
        class C07601 extends C0759g {
            final /* synthetic */ String f4263a;
            final /* synthetic */ C0761a f4264b;

            C07601(C0761a c0761a, File file, C0763c c0763c, String str) {
                this.f4264b = c0761a;
                this.f4263a = str;
                super(file, c0763c);
            }

            public void onFailure(int i, Header[] headerArr, Throwable th, File file) {
                LoggerProxy.m6515d("DownloadEngine", "1isInterrupted=" + Thread.currentThread().isInterrupted());
                if (this.f4264b.f4265a.m6141A()) {
                    super.onFailure(i, headerArr, th, file);
                }
            }

            public void onProgress(long j, long j2) {
                if (this.f4264b.f4265a.m6141A()) {
                    super.onProgress(j, j2);
                }
            }

            public void onSuccess(int i, Header[] headerArr, File file) {
                LoggerProxy.m6515d("DownloadEngine", "2isInterrupted=" + Thread.currentThread().isInterrupted() + "--fileId=" + this.f4263a);
                if (this.f4264b.f4265a.m6141A()) {
                    super.onSuccess(i, headerArr, file);
                }
            }
        }

        public C0761a(C0762b c0762b, C0763c c0763c) {
            this.f4265a = c0762b;
            this.f4266b = c0763c;
        }

        public Void m6564a() {
            this.f4266b.m6593d();
            String a = this.f4266b.m6587a();
            LoggerProxy.m6515d("DownloadEngine", "DownloadWork start fileId=" + a);
            if (StringTool.isEmpty(a)) {
                this.f4266b.m6589a(C0807c.m6758a().m6762a(C0802n.MODEL_REQUEST_ERROR, "fileId is null"));
            } else {
                Set hashSet = new HashSet();
                hashSet.add(a);
                ModelFileBags modelFileBags = (ModelFileBags) this.f4265a.f4274i.m6819a(hashSet).get();
                if (modelFileBags != null) {
                    String url = modelFileBags.getUrl(0);
                    if (url != null) {
                        this.f4267c = new SyncHttpClient();
                        this.f4267c.setURLEncodingEnabled(false);
                        this.f4267c.setTimeout(C0799l.DEFAULT.m6748b());
                        this.f4267c.setMaxRetriesAndTimeout(5, AsyncHttpClient.DEFAULT_RETRY_SLEEP_TIME_MILLIS);
                        ResponseHandlerInterface c07601 = new C07601(this, FileTools.getFile(this.f4266b.m6591b()), this.f4266b, a);
                        c07601.setUseSynchronousMode(true);
                        LoggerProxy.m6515d("DownloadEngine", "before get fileId=" + a);
                        this.f4267c.get(url, c07601);
                    } else {
                        this.f4266b.m6589a(C0807c.m6758a().m6762a(C0802n.MODEL_REQUEST_ERROR, "url is null"));
                    }
                } else {
                    this.f4266b.m6589a(C0807c.m6758a().m6762a(C0802n.MODEL_REQUEST_ERROR, "urlbags is null"));
                }
            }
            LoggerProxy.m6515d("DownloadEngine", "DownloadWork end");
            return null;
        }

        public void m6565b() {
            if (this.f4267c != null) {
                this.f4267c.stop();
            }
        }

        public C0763c m6566c() {
            return this.f4266b;
        }

        public /* synthetic */ Object call() {
            return m6564a();
        }
    }

    public C0762b() {
        this.f4269b = new C0768i(this);
        this.f4270c = new C0766f(this);
        this.f4271f = new C0764d(this);
        this.f4272g = new C0767h(this);
        this.f4268a = this.f4269b;
        m6143b();
    }

    public C0758a m6568a() {
        return this.f4268a;
    }

    public C0765e m6569a(C0763c c0763c) {
        return this.f4268a.m6556a(c0763c);
    }

    public void m6570a(C0758a c0758a) {
        this.f4268a = c0758a;
    }

    public void m6571a(C0822a c0822a) {
        this.f4274i = c0822a;
    }

    C0765e m6572b(C0763c c0763c) {
        C0761a c0761a = new C0761a(this, c0763c);
        c0763c.m6592c();
        LoggerProxy.m6515d("DownloadEngine", "before submit");
        Future future = null;
        try {
            future = this.f4273h.submit(c0761a);
        } catch (Throwable e) {
            LoggerProxy.m6515d("DownloadEngine", "submit exception");
            c0763c.m6589a(C0807c.m6758a().m6763a(C0802n.MODEL_FILE_DOWNLOAD_EXCEPTION, e));
        }
        C0765e c0765e = new C0765e();
        c0765e.m6601a(future);
        c0765e.m6600a(c0761a);
        return c0765e;
    }

    protected TtsError m6573g() {
        return this.f4268a.m6559b();
    }

    protected void m6574h() {
        this.f4268a.m6557a();
    }

    protected void m6575i() {
        this.f4268a.m6560c();
    }

    protected void m6576j() {
        this.f4268a.m6561d();
    }

    protected void m6577k() {
        this.f4268a.m6562e();
    }

    protected void m6578l() {
        this.f4268a.m6563f();
    }

    public boolean m6579m() {
        return this.f4268a == this.f4272g;
    }

    public boolean m6580n() {
        return Thread.currentThread().isInterrupted() || this.f4268a == this.f4270c;
    }

    public C0768i m6581o() {
        return this.f4269b;
    }

    public C0766f m6582p() {
        return this.f4270c;
    }

    public C0764d m6583q() {
        return this.f4271f;
    }

    public C0767h m6584r() {
        return this.f4272g;
    }

    void m6585s() {
        this.f4273h = (ThreadPoolExecutor) Executors.newFixedThreadPool(5, new C0804a("downloadPoolThread"));
    }

    void m6586t() {
        LoggerProxy.m6515d("DownloadEngine", "enter stop");
        if (this.f4273h != null) {
            if (!this.f4273h.isShutdown()) {
                this.f4273h.shutdownNow();
            }
            try {
                LoggerProxy.m6515d("DownloadEngine", "before awaitTermination");
                LoggerProxy.m6515d("DownloadEngine", "after awaitTermination isTermination=" + this.f4273h.awaitTermination(C0799l.DEFAULT.m6747a(), TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
            }
            this.f4273h = null;
        }
        LoggerProxy.m6515d("DownloadEngine", "end stop");
    }
}
