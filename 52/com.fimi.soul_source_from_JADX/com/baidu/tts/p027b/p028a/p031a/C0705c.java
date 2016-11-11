package com.baidu.tts.p027b.p028a.p031a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p027b.p028a.C0675b;
import com.baidu.tts.p027b.p028a.p032b.C0711b;
import com.baidu.tts.p034l.C0825e;
import com.baidu.tts.p034l.C0826f;
import com.baidu.tts.p034l.C0827g;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p034l.C0829i;
import com.baidu.tts.p041e.C0799l;
import com.baidu.tts.p041e.C0802n;
import com.baidu.tts.p042f.p043a.C0804a;
import com.baidu.tts.p044g.p045a.C0807c;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* renamed from: com.baidu.tts.b.a.a.c */
public class C0705c extends C0701a {
    private ExecutorService f4098f;
    private C0709h f4099g;
    private C0706e f4100h;
    private C0708g f4101i;
    private C0707f f4102j;

    /* renamed from: com.baidu.tts.b.a.a.c.1 */
    class C07031 implements C0675b {
        final /* synthetic */ C0705c f4095a;

        C07031(C0705c c0705c) {
            this.f4095a = c0705c;
        }

        public void m6203a(C0828h c0828h) {
        }

        public void m6204b(C0828h c0828h) {
        }

        public void m6205c(C0828h c0828h) {
            this.f4095a.m6178b(c0828h);
        }

        public void m6206d(C0828h c0828h) {
        }

        public void m6207e(C0828h c0828h) {
        }
    }

    /* renamed from: com.baidu.tts.b.a.a.c.a */
    class C0704a implements Callable<Void> {
        final /* synthetic */ C0705c f4096a;
        private C0829i f4097b;

        public C0704a(C0705c c0705c, C0829i c0829i) {
            this.f4096a = c0705c;
            this.f4097b = c0829i;
        }

        public Void m6208a() {
            try {
                this.f4096a.m6174a(C0828h.m6852b(this.f4097b));
                TtsError a = this.f4096a.a.m6258a(this.f4097b);
                if (a == null) {
                    this.f4096a.m6179c(C0828h.m6852b(this.f4097b));
                } else {
                    this.f4096a.m6181e(C0828h.m6850a(this.f4097b, a));
                }
            } catch (InterruptedException e) {
            }
            return null;
        }

        public /* synthetic */ Object call() {
            return m6208a();
        }
    }

    public C0705c() {
        this.f4099g = new C0709h(this);
        this.f4100h = new C0706e(this);
        this.f4101i = new C0708g(this);
        this.f4102j = new C0707f(this);
        this.c = this.f4099g;
    }

    private void m6209a(boolean z) {
        if (z) {
            m6180d(null);
            return;
        }
        C0828h c0828h = new C0828h();
        c0828h.m6855a(C0807c.m6758a().m6765b(C0802n.TTS_ENGINE_STOP_FAILURE));
        m6180d(c0828h);
    }

    int m6210b(C0826f c0826f) {
        return this.a.m6255a(c0826f);
    }

    int m6211b(C0827g c0827g) {
        return this.a.m6256a(c0827g);
    }

    void m6212b(C0711b c0711b) {
        this.a = c0711b;
    }

    void m6213b(C0675b c0675b) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        if (!this.b.contains(c0675b)) {
            this.b.add(c0675b);
        }
    }

    void m6214b(C0829i c0829i) {
        this.f4098f.submit(new C0704a(this, c0829i));
    }

    <T> void m6215b(T t) {
        this.a.m6260a((Object) t);
    }

    int m6216c(C0825e c0825e) {
        return this.a.m6254a(c0825e);
    }

    int m6217d(C0825e c0825e) {
        return this.a.m6261b(c0825e);
    }

    public boolean m6218m() {
        return this.c == this.f4102j;
    }

    public boolean m6219n() {
        return Thread.currentThread().isInterrupted() || this.c == this.f4100h;
    }

    public C0709h m6220o() {
        return this.f4099g;
    }

    public C0706e m6221p() {
        return this.f4100h;
    }

    public C0708g m6222q() {
        return this.f4101i;
    }

    public C0707f m6223r() {
        return this.f4102j;
    }

    TtsError m6224s() {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.a.m6259a(new C07031(this));
        return this.a.m6257a();
    }

    void m6225t() {
        this.f4098f = Executors.newSingleThreadExecutor(new C0804a("EngineExecutorPoolThread"));
    }

    void m6226u() {
    }

    void m6227v() {
    }

    void m6228w() {
        if (this.f4098f != null) {
            if (!this.f4098f.isShutdown()) {
                this.f4098f.shutdownNow();
            }
            try {
                LoggerProxy.m6515d("EngineExecutor", "before awaitTermination");
                boolean awaitTermination = this.f4098f.awaitTermination(C0799l.DEFAULT.m6747a(), TimeUnit.MILLISECONDS);
                LoggerProxy.m6515d("EngineExecutor", "after awaitTermination isTermination=" + awaitTermination);
                m6209a(awaitTermination);
            } catch (InterruptedException e) {
                m6209a(false);
            }
            this.f4098f = null;
        }
    }

    void m6229x() {
        this.a.m6262b();
        this.b = null;
    }
}
