package com.baidu.tts.p027b.p029b.p035a;

import com.baidu.tts.aop.tts.TtsError;
import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.p027b.p029b.C0677a;
import com.baidu.tts.p027b.p029b.C0745b;
import com.baidu.tts.p027b.p029b.p036b.C0740c;
import com.baidu.tts.p034l.C0823a;
import com.baidu.tts.p034l.C0828h;
import com.baidu.tts.p037c.C0747a;
import com.baidu.tts.p041e.C0799l;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.baidu.tts.b.b.a.f */
public class C0737f extends C0731a {
    private ThreadPoolExecutor f4173c;
    private C0740c f4174f;
    private C0739h f4175g;
    private C0733d f4176h;
    private C0738g f4177i;
    private C0734e f4178j;

    /* renamed from: com.baidu.tts.b.b.a.f.1 */
    class C07351 implements C0677a {
        final /* synthetic */ C0737f f4170a;

        C07351(C0737f c0737f) {
            this.f4170a = c0737f;
        }

        public void m6438a(C0828h c0828h) {
            this.f4170a.m6407b(c0828h);
        }

        public void m6439b(C0828h c0828h) {
            this.f4170a.m6408c(c0828h);
        }

        public void m6440c(C0828h c0828h) {
            this.f4170a.m6409d(c0828h);
        }
    }

    /* renamed from: com.baidu.tts.b.b.a.f.a */
    class C0736a implements Runnable {
        final /* synthetic */ C0737f f4171a;
        private C0828h f4172b;

        public C0736a(C0737f c0737f, C0828h c0828h) {
            this.f4171a = c0737f;
            this.f4172b = c0828h;
        }

        public void run() {
            LoggerProxy.m6515d("PlayQueueMachine", "enter run");
            this.f4171a.f4174f.m6468a(this.f4172b);
            LoggerProxy.m6515d("PlayQueueMachine", "end run");
        }
    }

    public C0737f() {
        this.f4175g = new C0739h(this);
        this.f4176h = new C0733d(this);
        this.f4177i = new C0738g(this);
        this.f4178j = new C0734e(this);
        this.b = this.f4175g;
        this.f4174f = C0745b.m6510a().m6512b();
    }

    int m6442b(float f, float f2) {
        return this.f4174f.m6465a(f, f2);
    }

    int m6443b(int i) {
        return this.f4174f.m6466a(i);
    }

    void m6444b(C0677a c0677a) {
        this.a = c0677a;
    }

    <T> void m6445b(T t) {
        this.f4174f.m6470a(((C0823a) t).m6831a());
    }

    void m6446e(C0828h c0828h) {
        this.f4173c.execute(new C0736a(this, c0828h));
    }

    public boolean m6447m() {
        return this.b == this.f4178j;
    }

    public boolean m6448n() {
        return Thread.currentThread().isInterrupted() || this.b == this.f4176h;
    }

    public C0739h m6449p() {
        return this.f4175g;
    }

    public C0733d m6450q() {
        return this.f4176h;
    }

    public C0738g m6451r() {
        return this.f4177i;
    }

    public C0734e m6452s() {
        return this.f4178j;
    }

    TtsError m6453t() {
        this.f4174f.m6469a(new C07351(this));
        return this.f4174f.m6467a();
    }

    void m6454u() {
        this.f4173c = new C0747a(100, "PlayExecutorPoolThread");
    }

    void m6455v() {
        this.f4174f.m6472c();
        if (this.f4173c != null) {
            if (!this.f4173c.isShutdown()) {
                this.f4173c.shutdownNow();
            }
            try {
                LoggerProxy.m6515d("PlayQueueMachine", "before await");
                LoggerProxy.m6515d("PlayQueueMachine", "after await isTer=" + this.f4173c.awaitTermination(C0799l.DEFAULT.m6747a(), TimeUnit.MILLISECONDS));
            } catch (InterruptedException e) {
                LoggerProxy.m6515d("PlayQueueMachine", "InterruptedException");
            }
            this.f4173c = null;
        }
    }

    void m6456w() {
        this.f4174f.m6473d();
    }

    void m6457x() {
        this.f4174f.m6471b();
    }
}
